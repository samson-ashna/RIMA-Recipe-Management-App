package presentation;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import domain.UserActivity;
import persistence.UserDAOImpl;
import persistence.UsersDAO;

@SuppressWarnings("serial")
public class Login extends JFrame {
	
	//panel object, text fields and button object
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton backButton;

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
		//Set the application to exit when closed
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Set the size and pop up location of the window.
		setSize(429, 336);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//label for user name
		JLabel lbluserName = new JLabel("User Name");
		lbluserName.setHorizontalAlignment(SwingConstants.CENTER);
		lbluserName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbluserName.setBounds(169, 39, 76, 51);
		contentPane.add(lbluserName);
		//Creates a test field where user can enter user name
		textField = new JTextField();
		textField.setBounds(67, 93, 281, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		//label for password
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPassword.setBounds(169, 114, 76, 51);
		contentPane.add(lblPassword);
		//Creates a text field where user can enter password
		passwordField = new JPasswordField();
		passwordField.setBounds(67, 165, 281, 33);
		contentPane.add(passwordField);
		//Creates label for displaying error messages.
		JLabel lblError = new JLabel("");
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setBounds(112, 209, 185, 14);
		contentPane.add(lblError);
		//Creates a button for login. When clicked, if the entered information is correct, user is redirected to home page.
		JButton logInButton = new JButton("Login");
		logInButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		logInButton.setBackground(new Color(255, 255, 255));
		logInButton.setForeground(new Color(64, 0, 64));
		logInButton.setBounds(112, 243, 76, 26);
		contentPane.add(logInButton);
		logInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
				String password = passwordField.getText();
				//checks to see if user name already exists.
				if(UserActivity.checkUserName(username)) {
					//checks to see if password matches user name.
					if(!UserActivity.checkPassword(username, password)) {
						lblError.setText("Incorrect Password!");
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
					lblError.setText("Username does not exist!");
				}
			}
		});
				
		//Creates a back button. When clicked user is redirected to main page. 
		backButton = new JButton("Back");
		backButton.setForeground(new Color(64, 0, 64));
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		backButton.setBackground(Color.WHITE);
		backButton.setBounds(221, 243, 76, 26);
		contentPane.add(backButton);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main mainPage = new Main();
				mainPage.frame.setVisible(true);
				contentPane.setVisible(false);
				Window win = SwingUtilities.getWindowAncestor(contentPane);
				win.dispose();
			}
		});
		
	}
}
