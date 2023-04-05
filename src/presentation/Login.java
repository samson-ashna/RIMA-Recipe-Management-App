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
import persistence.DatabaseAccess;
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
	private JButton logInButton;
	

	protected JFrame frame;
    private ImageIcon icon;
	private ImageIcon logo;
    private JLabel lbl;
	private JLabel lblError;
	
    private JButton start;
    private JButton end;

	private final JLabel lblPassword = new JLabel("Password");
	private final JLabel lbluserName = new JLabel("User Name");


	/**
	 * Create the frame.
	 */
	public Login() {
		
		// Background Image
		icon = new ImageIcon(this.getClass().getResource("/res/background.jpg"));
		lbl = new JLabel(icon);
		lbl.setSize(1280, 720);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		frame = this;
		frame.setSize(1280, 720);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		//Creates a back button. When clicked user is redirected to main page. 
		backButton = new JButton("Back");
		frame.getContentPane().add(backButton);
		backButton.setBackground(new Color(59, 89, 182));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		backButton.setBounds(674, 424, 76, 26);
		
		
		//Creates a button for login. When clicked, if the entered information is correct, user is redirected to home page.
		logInButton = new JButton("Login");
		frame.getContentPane().add(logInButton);
		logInButton.setBackground(new Color(59, 89, 182));
        logInButton.setForeground(Color.WHITE);
        logInButton.setFocusPainted(false);
        logInButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		logInButton.setBounds(522, 424, 76, 26);
		
		
		//Creates a text field where user can enter password
		passwordField = new JPasswordField();
		frame.getContentPane().add(passwordField);
		passwordField.setBounds(469, 359, 327, 26);
		
		//label for password

		lblPassword.setForeground(new Color(255, 255, 255));
		frame.getContentPane().add(lblPassword);
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblPassword.setBounds(584, 308, 76, 51);
		
		
		//Creates a test field where user can enter user name
		textField = new JTextField();
		frame.getContentPane().add(textField);
		textField.setBounds(469, 271, 327, 26);
		textField.setColumns(10);
		
		// label for user name
		
		lbluserName.setForeground(new Color(255, 255, 255));
		frame.getContentPane().add(lbluserName);
		lbluserName.setHorizontalAlignment(SwingConstants.CENTER);
		lbluserName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbluserName.setBounds(562, 220, 123, 51);
		
		
		//Creates label for displaying error messages.
		lblError = new JLabel();
		frame.getContentPane().add(lblError);
		lblError.setForeground(new Color(255, 255, 255));
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setBounds(550, 463, 185, 14);
		

		
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
						UserActivity.setCurrentUser(db.get(username));
						db.get(username).loggedIn = true;
						HomePage homePage = new HomePage();
						homePage.setVisible(true);
						frame.setVisible(false);
						frame.dispose();
						contentPane.setVisible(false);
						Window win = SwingUtilities.getWindowAncestor(contentPane);
						win.dispose();
					}
				}else {
					lblError.setText("Username does not exist!");
				}
			}
		});
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main mainPage = new Main();
				mainPage.frame.setVisible(true);
				frame.setVisible(false);
				frame.dispose();
				contentPane.setVisible(false);
				Window win = SwingUtilities.getWindowAncestor(contentPane);
				win.dispose();
			}
		});
		frame.getContentPane().add(lbl);
		frame.setVisible(true);
		
	}
}