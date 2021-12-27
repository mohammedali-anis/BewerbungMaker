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
	private static String user_id;
	private JTextField firmaField;
	private JTextField sexField;
	private JTextField telField;
	private JTextField streetField;
	private JTextField noField;
	private JTextField plzField;
	private JTextField cityField;
	private JTextField dateField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBewerbungFrame frame = new AddBewerbungFrame();
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
	public AddBewerbungFrame() throws SQLException {
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
		firstnameLabel.setBounds(20, 89, 92, 16);
		panel.add(firstnameLabel);

		JLabel lastnameLabel = new JLabel("Last Name :");
		lastnameLabel.setBounds(20, 117, 74, 16);
		panel.add(lastnameLabel);

		JLabel mobileLabel = new JLabel("Mobile :");
		mobileLabel.setBounds(20, 183, 74, 16);
		panel.add(mobileLabel);

		firstnameField = new JTextField();
		firstnameField.setBounds(106, 89, 130, 26);
		panel.add(firstnameField);
		firstnameField.setColumns(10);

		lastnameField = new JTextField();
		lastnameField.setColumns(10);
		lastnameField.setBounds(106, 117, 130, 26);
		panel.add(lastnameField);

		mobileField = new JTextField();
		mobileField.setColumns(10);
		mobileField.setBounds(106, 183, 130, 26);
		panel.add(mobileField);

		addBtn = new JButton("Add");
//		updateBtn.setIcon(new ImageIcon(EditUserFrame.class.getResource("/code/icons/edit.png")));
		addBtn.setBounds(42, 362, 117, 29);
		panel.add(addBtn);

		JButton closeBtn = new JButton("Close");
//		closeBtn.setIcon(new ImageIcon(EditUserFrame.class.getResource("/code/icons/cancel.png")));
		closeBtn.setBounds(164, 362, 117, 29);
		panel.add(closeBtn);

		closeBtn.addActionListener(l -> {
			dispose();

		});

		JLabel emailLabel = new JLabel("E-Mail :");
		emailLabel.setBounds(20, 150, 74, 16);
		panel.add(emailLabel);

		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(106, 150, 130, 26);
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
		sexLabel.setBounds(20, 56, 92, 16);
		panel.add(sexLabel);

		sexField = new JTextField();
		sexField.setColumns(10);
		sexField.setBounds(106, 56, 130, 26);
		panel.add(sexField);

		JLabel telLabel = new JLabel("Tel. :");
		telLabel.setBounds(20, 216, 74, 16);
		panel.add(telLabel);

		telField = new JTextField();
		telField.setColumns(10);
		telField.setBounds(106, 216, 130, 26);
		panel.add(telField);

		JLabel streetLabel_1 = new JLabel("Street :");
		streetLabel_1.setBounds(20, 249, 74, 16);
		panel.add(streetLabel_1);

		streetField = new JTextField();
		streetField.setColumns(10);
		streetField.setBounds(106, 249, 130, 26);
		panel.add(streetField);

		JLabel noLabel = new JLabel("No :");
		noLabel.setBounds(248, 254, 33, 16);
		panel.add(noLabel);

		noField = new JTextField();
		noField.setColumns(10);
		noField.setBounds(285, 249, 47, 26);
		panel.add(noField);

		JLabel plzLabel = new JLabel("PLZ :");
		plzLabel.setBounds(20, 282, 74, 16);
		panel.add(plzLabel);

		plzField = new JTextField();
		plzField.setColumns(10);
		plzField.setBounds(106, 282, 130, 26);
		panel.add(plzField);

		JLabel cityLabel = new JLabel("City :");
		cityLabel.setBounds(248, 287, 33, 16);
		panel.add(cityLabel);

		cityField = new JTextField();
		cityField.setColumns(10);
		cityField.setBounds(285, 282, 47, 26);
		panel.add(cityField);

		JLabel dateLabel = new JLabel("Date :");
		dateLabel.setBounds(20, 315, 92, 16);
		panel.add(dateLabel);

		dateField = new JTextField();
		dateField.setColumns(10);
		dateField.setBounds(106, 315, 130, 26);
		panel.add(dateField);

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
				DB.sqlAddBewerbung(Name, Sex, firstName, lastName, email, mobile, tel, street, plz, no, city, date);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dispose();

		});

	}
}
