package JFrames;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import sql.DB;

public class AdminFrame extends JFrame {

	private JPanel panel;
	private JTable bewerbungTable;
	private JScrollPane scrollPane;

	private JButton ManageBewerbungBtn;
	private JButton logoutBtn;
	private JButton deleteUserBtn;

	private JTable sectionTable;
	private JScrollPane sectionScrollPane;
	private JLabel welcomeLabel;
	private JButton editUserBtn;
	private static String username;
	public static String bewerbungID;
	public static String user_id;

	File[] folderArr;
	String absolutePath;
	File file;
	String path;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminFrame frame = new AdminFrame(user_id);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor for the Admin Frame
	 * 
	 * @param AUsername the USer name of the Admin loged in
	 */

	@SuppressWarnings("serial")
	public AdminFrame(String user_id) throws SQLException {
		this.user_id = user_id;

		setTitle("Admin Page");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setResizable(false);
		panel = new JPanel();
		setSize(1039, 500);

		/**
		 * User table which shows all Users
		 * 
		 */
		DefaultTableModel tableModel = new DefaultTableModel(new String[] { "Bewerbung ID", "Name", "Postion", "Sex",
				"First Name", "Last Name", "E-Mail", "Mobile", "Tel.", "Street", "No.", "PLZ", "City", "Date" }, 0);

		bewerbungTable = new JTable(tableModel);
		bewerbungTable.getColumnModel().getColumn(0).setPreferredWidth(25);
		bewerbungTable.getColumnModel().getColumn(3).setPreferredWidth(130);

		panel.add(new JScrollPane(bewerbungTable));

		/** Set editable=false; */
		bewerbungTable.setDefaultEditor(Object.class, null);

		panel.setLayout(null);

		scrollPane = new JScrollPane(bewerbungTable);
		scrollPane.setBounds(35, 98, 895, 156);
		panel.add(scrollPane);
		getContentPane().add(panel);
		bewerbungTable.setRowSelectionAllowed(true);
		bewerbungTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		/** Disable Draging Columns */
		bewerbungTable.getTableHeader().setReorderingAllowed(false);

		bewerbungTable.getTableHeader().setOpaque(false);

		sql.DB.sqlUserTable(tableModel);

		DefaultTableCellRenderer renderer;
		renderer = (DefaultTableCellRenderer) bewerbungTable.getTableHeader().getDefaultRenderer();
		renderer.setHorizontalAlignment(JLabel.CENTER);

		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.CENTER);

		for (int columnIndex = 0; columnIndex < bewerbungTable.getModel().getColumnCount(); columnIndex++) {
			bewerbungTable.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
		}

		/**
		 * Adding all Buttons to the Frame
		 * 
		 */

		ManageBewerbungBtn = new JButton("Manage Bewerbung");

		ManageBewerbungBtn.setBounds(35, 425, 145, 29);
		panel.add(ManageBewerbungBtn);

		JLabel iconLabel = new JLabel("");

		iconLabel.setBounds(729, 17, 50, 65);
		panel.add(iconLabel);

		JButton addUserBtn = new JButton("Add User");

		addUserBtn.setBounds(35, 266, 91, 29);
		addUserBtn.setVisible(false);
		panel.add(addUserBtn);

		deleteUserBtn = new JButton("Delete Selected User");

		deleteUserBtn.setBounds(768, 266, 172, 29);
		deleteUserBtn.setVisible(false);
		panel.add(deleteUserBtn);

		JButton refreshBtn = new JButton("Refresh");

		refreshBtn.setBounds(942, 161, 91, 29);
		refreshBtn.setVisible(false);
		panel.add(refreshBtn);

		/**
		 * ActionListener for the Refresh Button to keep track of changes done in the DB
		 * Loads the most recent Version of the User table of the db into the Frame
		 * table
		 */
		refreshBtn.addActionListener(l -> {
			tableModel.setRowCount(0);

			try {
				sql.DB.sqlUserTable(tableModel);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});

		/**
		 * To get the User ID when clicking on the Row via MousAdapter
		 * 
		 */

		bewerbungTable.addMouseListener(new MouseAdapter() {

			public void mouseReleased(MouseEvent e) {
				bewerbungID = bewerbungTable.getValueAt(bewerbungTable.getSelectedRow(), 0).toString();
				System.out.println(bewerbungID);

			}
		});

		/**
		 * Sectionmanagement table
		 * 
		 */

		DefaultTableModel SectionTableModel = new DefaultTableModel(
				new String[] { "Section ID", "Section", "Subsection" }, 0);

		sectionTable = new JTable(SectionTableModel);

		panel.add(new JScrollPane(sectionTable));

		/** Set editable=false; */
		sectionTable.setDefaultEditor(Object.class, null);

		panel.setLayout(null);

		sectionScrollPane = new JScrollPane(sectionTable);
		sectionScrollPane.setBounds(35, 98, 895, 156);
		panel.add(sectionScrollPane);
		getContentPane().add(panel);
		sectionTable.setRowSelectionAllowed(true);
		sectionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		/** Disable Draging Columns */

		sectionTable.getTableHeader().setReorderingAllowed(false);

		sectionTable.getTableHeader().setOpaque(false);
//		sqlConnection.sqlSectionTable(SectionTableModel);

		DefaultTableCellRenderer renderer2;
		renderer2 = (DefaultTableCellRenderer) sectionTable.getTableHeader().getDefaultRenderer();
		renderer2.setHorizontalAlignment(JLabel.CENTER);

		DefaultTableCellRenderer rightRenderer2 = new DefaultTableCellRenderer();
		rightRenderer2.setHorizontalAlignment(JLabel.CENTER);

		for (int columnIndex = 0; columnIndex < sectionTable.getModel().getColumnCount(); columnIndex++) {
			sectionTable.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer2);
		}

		sectionScrollPane.setVisible(false);

		sectionTable.setRowSelectionAllowed(true);

		/**
		 * To get the Section ID when clicking on the Row via MousAdapter
		 * 
		 */
//		sectionTable.addMouseListener(new MouseAdapter() {
//
//			public void mouseReleased(MouseEvent e) {
//				section_id = sectionTable.getValueAt(sectionTable.getSelectedRow(), 0).toString();
//				System.out.println(section_id);
//
//				section_type = sectionTable.getValueAt(sectionTable.getSelectedRow(), 1).toString();
//				System.out.println(section_type);
//			}
//		});

		logoutBtn = new JButton("Logout");

		logoutBtn.setBounds(664, 425, 117, 29);

		/**
		 * ActionListener for LogoutButton let the User retunr to the LoginFrame
		 */
		logoutBtn.addActionListener(l -> {

			Login login = new Login();
			dispose();
			login.setVisible(true);

		});
		panel.add(logoutBtn);

		welcomeLabel = new JLabel("New label");
		welcomeLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		welcomeLabel.setForeground(Color.BLACK);
		welcomeLabel.setBounds(18, 22, 377, 46);

		welcomeLabel.setText(username + " (Admin)");
		panel.add(welcomeLabel);

		editUserBtn = new JButton("Edit Selected User");

		editUserBtn.setBounds(417, 266, 155, 29);
		editUserBtn.setVisible(false);
		panel.add(editUserBtn);

		JButton gnrBTN = new JButton("Generate");
		gnrBTN.setVisible(false);
		gnrBTN.setBounds(438, 310, 117, 29);
		panel.add(gnrBTN);

		bewerbungTable.setVisible(false);
		scrollPane.setVisible(false);
		/**
		 * ActionListener for ManageUserButton makes all buttons needed to manage Users
		 * visible
		 */

		ManageBewerbungBtn.addActionListener(l -> {

			scrollPane.setVisible(true);
			bewerbungTable.setVisible(true);
			addUserBtn.setVisible(true);
			deleteUserBtn.setVisible(true);
			refreshBtn.setVisible(true);
			editUserBtn.setVisible(true);
			gnrBTN.setVisible(true);

			sectionScrollPane.setVisible(false);
			sectionTable.setVisible(false);

			addUserBtn.addActionListener(w -> {

//				register.setVisible(true);
				try {
					AddBewerbungFrame addBeFr = new AddBewerbungFrame(user_id);
					addBeFr.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			});

		});

		/**
		 * ActionListener for editUSerButton which opens an new EditUserFrame via the
		 * constructor of EditUserFrame
		 * 
		 * @param user_id of the User selected in the table
		 */

		editUserBtn.addActionListener(l -> {

//			EditUserFrame edituserframe = null;
			try {
//				edituserframe = new EditUserFrame(user_id);
//				edituserframe.setVisible(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				// e1.printStackTrace();
				JOptionPane.showMessageDialog(panel, "Please select a user", "Warning", JOptionPane.WARNING_MESSAGE);
			}

		});

		/**
		 * ActionListener for deleteUSerButton which opens an new deleteUserFrame via
		 * the constructor of DeleteUserFrame
		 * 
		 * @param user_id of the User selected in the table
		 */

		deleteUserBtn.addActionListener(w -> {
//			DeleteUserFrame deleteuser = null;
			if (bewerbungID != null && !bewerbungID.isEmpty()) {
//				deleteuser = new DeleteUserFrame(user_id, username);
//				deleteuser.setVisible(true);
			} else
				JOptionPane.showMessageDialog(panel, "Please select a user", "Warning", JOptionPane.WARNING_MESSAGE);

		});

		/**
		 * ActionListener for exportButton which exports the current state of the DB
		 * into a csv File by using the dbBackUp.backup
		 */

		gnrBTN.addActionListener(l -> {

			HashMap<String, String> company;
			HashMap<String, String> me;
			String Sex, respect;

			try {
				company = DB.getInfo(bewerbungID).get(0);
				me = DB.getInfo(bewerbungID).get(1);

				if (company.get("Sex").equalsIgnoreCase("Male")) {
					Sex = "Herr";
					respect = "geehrter Herr " + company.get("LastName");
				} else {

					Sex = "Frau";
					respect = "geehrte Frau " + company.get("LastName");
				}

				LocalDateTime myDateObj = LocalDateTime.now();
				System.out.println("Before formatting: " + myDateObj);
				DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd.MM.yyyy");
				String date = myDateObj.format(myFormatObj);

				API.GoogleDocAPI.run(company.get("CompanyName"), company.get("City"), company.get("PLZ"),
						company.get("No"), company.get("Street"), company.get("LastName"), company.get("FirstName"),
						Sex, me.get("City"), me.get("PLZ"), me.get("No"), me.get("Street"), me.get("LastName"),
						System.lineSeparator() + me.get("FirstName"), me.get("Email"), me.get("Mobile"), me.get("City"),
						me.get("PLZ"), me.get("No"), me.get("Street"), me.get("LastName"), me.get("FirstName"), date,
						company.get("Position"), respect);
			} catch (SQLException | GeneralSecurityException | IOException e1) {
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});

	}
}