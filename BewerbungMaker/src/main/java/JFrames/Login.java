package JFrames;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import sql.DB;

public class Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	public static JTextField emailTextField;
	private JButton loginBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor for the Login Frame
	 */
	@SuppressWarnings("deprecation")
	public Login() {
		setTitle("Login");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 265);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 438, 225);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel emailLabel = new JLabel("E-Mail :");

		emailLabel.setBounds(23, 90, 91, 16);
		panel.add(emailLabel);

		JLabel passwordLabel = new JLabel("Password :");

		passwordLabel.setBounds(23, 132, 91, 16);
		panel.add(passwordLabel);

		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		passwordField.setBounds(126, 132, 130, 21);
		panel.add(passwordField);

		emailTextField = new JTextField();
		emailTextField.setHorizontalAlignment(SwingConstants.LEFT);
		emailTextField.setBounds(126, 90, 130, 26);
		panel.add(emailTextField);
		emailTextField.setColumns(10);

		loginBtn = new JButton("Login");

		loginBtn.setBounds(197, 181, 79, 29);
		panel.add(loginBtn);
		/**
		 * ActionListener for the Login Button
		 */

		JButton exitBtn = new JButton("Exit");

		exitBtn.setBounds(100, 181, 79, 29);
		panel.add(exitBtn);
		/**
		 * ActionListener for the Exit Button
		 */
		exitBtn.addActionListener(l -> {
			dispose();
			// System.exit(0);
		});

		JCheckBox showPasswordBox = new JCheckBox("Show Password");
		showPasswordBox.setBounds(280, 128, 128, 23);
		panel.add(showPasswordBox);

		final JLabel projektTitle = new JLabel("");
		projektTitle.setForeground(Color.WHITE);
		projektTitle.setBackground(Color.WHITE);
		projektTitle.setVerticalAlignment(SwingConstants.TOP);
		projektTitle.setEnabled(false);
		projektTitle.setBorder(new EmptyBorder(0, 0, 0, 0));
		projektTitle.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		projektTitle.setHorizontalAlignment(SwingConstants.CENTER);
		projektTitle.setBounds(94, 16, 233, 31);
		panel.add(projektTitle);

		JLabel iconLabel = new JLabel("");
		iconLabel.setBounds(358, 6, 50, 65);
		panel.add(iconLabel);

		JLabel projektTitle2 = new JLabel("BewerbungMaker");
		projektTitle2.setVerticalAlignment(SwingConstants.TOP);
		projektTitle2.setHorizontalAlignment(SwingConstants.CENTER);
		projektTitle2.setForeground(Color.WHITE);
		projektTitle2.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		projektTitle2.setEnabled(false);
		projektTitle2.setBorder(new EmptyBorder(0, 0, 0, 0));
		projektTitle2.setBackground(Color.WHITE);
		projektTitle2.setBounds(73, 40, 273, 31);
		panel.add(projektTitle2);

		/**
		 * ActionListener for the Show Password Check Box to hide or show the character
		 * of the password
		 */
		showPasswordBox.addActionListener(l -> {

			if (showPasswordBox.isSelected())
				passwordField.setEchoChar((char) 0);
			else
				passwordField.setEchoChar('●');

		});

		
		loginBtn.addActionListener(l -> {

			try {

				if (!DB.sqlLogin(emailTextField.getText(), passwordField.getText()).equals("NF")) {
					String user_id=DB.sqlLogin(emailTextField.getText(), passwordField.getText());
					AdminFrame aF = new AdminFrame(user_id);
					aF.setVisible(true);
					dispose();
				} else
					JOptionPane.showMessageDialog(null, "Username or password is wrong !", "Error",
							JOptionPane.ERROR_MESSAGE);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		});

	}
}
