package presentation;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import businessLogic.UserActivity;
import objects.Recipes;
import persistence.UsersStubDB;
import persistence.DatabaseAccess;
import persistence.DAO;
import persistence.UsersDAO;

/**
 * 
 */
@SuppressWarnings("serial")
public class Login extends JFrame {
	
	//panel object, text fields and button object
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton backButton;

	protected static JFrame frame;
    private ImageIcon icon;
	private ImageIcon logo;
    private JLabel label;
	
    private JButton start;
    private JButton end;

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
		
		// Background Image
		icon = new ImageIcon(this.getClass().getResource("/res/background.jpg"));
		label = new JLabel(icon);
		label.setSize(1280, 720);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// label for user name
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
				UserActivity activity = new UserActivity();
				String username = textField.getText();
				String password = passwordField.getText();
				//checks to see if user name already exists.
				if(activity.checkUserName(username)) {
					//checks to see if password matches user name.
					if(!activity.checkPassword(username, password)) {
						lblError.setText("Incorrect Password!");
					}else {
						DatabaseAccess access = new DatabaseAccess();
						UsersDAO db = access.usersDB();
						//UsersDAO userDAO = new UsersStubDB();
						UserActivity.setCurrentUser(db.get(username));
						db.get(username).loggedIn = true;
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

		// Setup
		label.add(lbluserName);
		label.add(textField);
		label.add(lblPassword);
		label.add(passwordField);
		label.add(logInButton);
		label.add(backButton);
		label.add(lblError);

		frame = new JFrame("RIMA - Login");
		frame.add(label);
		frame.setSize(1280, 720);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
}
