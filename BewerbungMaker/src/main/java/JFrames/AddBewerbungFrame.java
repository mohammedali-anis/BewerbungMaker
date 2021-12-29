package JFrames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sql.DB;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class AddBewerbungFrame extends JFrame {

	private JPanel contentPane;
	private static JTextField firstnameField;
	private static JTextField lastnameField;
	private static JTextField mobileField;
	private static JTextField emailField;
	private static JButton addBtn;
	public static String user_id;
	private JTextField firmaField;
	private JTextField sexField;
	private JTextField telField;
	private JTextField streetField;
	private JTextField noField;
	private JTextField plzField;
	private JTextField cityField;
	private JTextField dateField;
	private JTextField postionField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBewerbungFrame frame = new AddBewerbungFrame(user_id);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public AddBewerbungFrame(String user_id) throws SQLException {

		setResizable(false);

		System.err.println(user_id);
		setTitle("Edit User");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 358, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 537, 424);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel firstnameLabel = new JLabel("First Name :");
		firstnameLabel.setBounds(20, 116, 92, 16);
		panel.add(firstnameLabel);

		JLabel lastnameLabel = new JLabel("Last Name :");
		lastnameLabel.setBounds(20, 144, 74, 16);
		panel.add(lastnameLabel);

		JLabel mobileLabel = new JLabel("Mobile :");
		mobileLabel.setBounds(20, 210, 74, 16);
		panel.add(mobileLabel);

		firstnameField = new JTextField();
		firstnameField.setBounds(106, 116, 130, 26);
		panel.add(firstnameField);
		firstnameField.setColumns(10);

		lastnameField = new JTextField();
		lastnameField.setColumns(10);
		lastnameField.setBounds(106, 144, 130, 26);
		panel.add(lastnameField);

		mobileField = new JTextField();
		mobileField.setColumns(10);
		mobileField.setBounds(106, 210, 130, 26);
		panel.add(mobileField);

		addBtn = new JButton("Add");
//		updateBtn.setIcon(new ImageIcon(EditUserFrame.class.getResource("/code/icons/edit.png")));
		addBtn.setBounds(42, 389, 117, 29);
		panel.add(addBtn);

		JButton closeBtn = new JButton("Close");
//		closeBtn.setIcon(new ImageIcon(EditUserFrame.class.getResource("/code/icons/cancel.png")));
		closeBtn.setBounds(164, 389, 117, 29);
		panel.add(closeBtn);

		closeBtn.addActionListener(l -> {
			dispose();

		});

		JLabel emailLabel = new JLabel("E-Mail :");
		emailLabel.setBounds(20, 177, 74, 16);
		panel.add(emailLabel);

		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(106, 177, 130, 26);
		panel.add(emailField);

		DefaultListModel model = new DefaultListModel();
		getContentPane().add(panel);

		JLabel lblFirma = new JLabel("Firma :");
		lblFirma.setBounds(20, 27, 92, 16);
		panel.add(lblFirma);

		firmaField = new JTextField();
		firmaField.setColumns(10);
		firmaField.setBounds(106, 27, 130, 26);
		panel.add(firmaField);

		JLabel sexLabel = new JLabel("Sex :");
		sexLabel.setBounds(20, 83, 92, 16);
		panel.add(sexLabel);

		sexField = new JTextField();
		sexField.setColumns(10);
		sexField.setBounds(106, 83, 130, 26);
		panel.add(sexField);

		JLabel telLabel = new JLabel("Tel. :");
		telLabel.setBounds(20, 243, 74, 16);
		panel.add(telLabel);

		telField = new JTextField();
		telField.setColumns(10);
		telField.setBounds(106, 243, 130, 26);
		panel.add(telField);

		JLabel streetLabel_1 = new JLabel("Street :");
		streetLabel_1.setBounds(20, 276, 74, 16);
		panel.add(streetLabel_1);

		streetField = new JTextField();
		streetField.setColumns(10);
		streetField.setBounds(106, 276, 130, 26);
		panel.add(streetField);

		JLabel noLabel = new JLabel("No :");
		noLabel.setBounds(248, 281, 33, 16);
		panel.add(noLabel);

		noField = new JTextField();
		noField.setColumns(10);
		noField.setBounds(285, 276, 47, 26);
		panel.add(noField);

		JLabel plzLabel = new JLabel("PLZ :");
		plzLabel.setBounds(20, 309, 74, 16);
		panel.add(plzLabel);

		plzField = new JTextField();
		plzField.setColumns(10);
		plzField.setBounds(106, 309, 130, 26);
		panel.add(plzField);

		JLabel cityLabel = new JLabel("City :");
		cityLabel.setBounds(248, 314, 33, 16);
		panel.add(cityLabel);

		cityField = new JTextField();
		cityField.setColumns(10);
		cityField.setBounds(285, 309, 47, 26);
		panel.add(cityField);

		JLabel dateLabel = new JLabel("Date :");
		dateLabel.setBounds(20, 342, 92, 16);
		panel.add(dateLabel);

		dateField = new JTextField();
		dateField.setColumns(10);
		dateField.setBounds(106, 342, 130, 26);
		panel.add(dateField);

		JLabel postionLabel = new JLabel("Postion :");
		postionLabel.setBounds(20, 55, 92, 16);
		panel.add(postionLabel);

		postionField = new JTextField();
		postionField.setColumns(10);
		postionField.setBounds(106, 55, 130, 26);
		panel.add(postionField);

//		ArrayList<String> dataList = sqlConnection.sqlGetUserData(user_id, sectionList);
//		firstnameField.setText(dataList.get(0));
//		lastnameField.setText(dataList.get(1));
//		usernameField.setText(dataList.get(2));
//		emailField.setText(dataList.get(3));
//		roleComboBox.setSelectedItem(dataList.get(4));
//		passwordField.setText(dataList.get(5));
//		confirmPasswordField.setText(dataList.get(5));
		/**
		 * ActionListener for updateBtn updates the edited User to the DB using
		 * sqlConnection.sqlUpdateUser
		 * 
		 * @param user_id
		 */
		addBtn.addActionListener(l -> {

			String Name = firmaField.getText();
			String position = postionField.getText();
			String Sex = sexField.getText();
			String firstName = firstnameField.getText();
			String lastName = lastnameField.getText();
			String email = emailField.getText();

			String mobile = mobileField.getText();
			String tel = telField.getText();
			String street = streetField.getText();
			String plz = plzField.getText();
			String no = noField.getText();

			String city = cityField.getText();
			String date = dateField.getText();

			try {
				DB.sqlAddBewerbung(Name, position, Sex, firstName, lastName, email, mobile, tel, street, plz, no, city,
						date, user_id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dispose();

		});

	}
}
