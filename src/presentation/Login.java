package presentation;

import java.awt.*;
import javax.swing.*;
import domain.*;
import persistence.*;
import java.awt.event.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnNewButton_2;

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
	 * Create the frame.
	 */
	public Login() {
		setTitle("RIMA - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(429, 336);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(169, 39, 76, 51);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(67, 93, 281, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(169, 114, 76, 51);
		contentPane.add(lblPassword);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(112, 209, 185, 14);
		contentPane.add(lblNewLabel_1);
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
				String password = passwordField.getText();
				if(UserActivity.checkUserName(username)) {
					if(!UserActivity.checkPassword(username, password)) {
						lblNewLabel_1.setText("Incorrect Password!");
					}else {
						UsersDAO userDAO = new UserDAOImpl();
						userDAO.get(username).loggedIn = true;
						HomePage homePage = new HomePage();
						homePage.setVisible(true);
						contentPane.setVisible(false);
						Window win = SwingUtilities.getWindowAncestor(contentPane);
						win.dispose();
					}
				}else {
					lblNewLabel_1.setText("Username does not exist!");
				}
			}
		});
		
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setForeground(new Color(64, 0, 64));
		btnNewButton.setBounds(112, 243, 76, 26);
		contentPane.add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(67, 165, 281, 33);
		contentPane.add(passwordField);
		
		btnNewButton_2 = new JButton("Back");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main mainPage = new Main();
				mainPage.frame.setVisible(true);
				contentPane.setVisible(false);
				Window win = SwingUtilities.getWindowAncestor(contentPane);
				win.dispose();
			}
		});
		btnNewButton_2.setForeground(new Color(64, 0, 64));
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setBounds(221, 243, 76, 26);
		contentPane.add(btnNewButton_2);
	}
}
