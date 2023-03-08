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

	protected JFrame frame;
    private ImageIcon icon;
	private ImageIcon logo;
    private JLabel lbl;
	
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

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		frame = new JFrame("RIMA - Login");
		frame.setSize(1280, 720);
		frame.getContentPane().setLayout(null);
		
		//Creates a back button. When clicked user is redirected to main page. 
		backButton = new JButton("Back");
		frame.getContentPane().add(backButton);
		backButton.setForeground(new Color(64, 0, 64));
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		backButton.setBackground(Color.WHITE);
		backButton.setBounds(674, 374, 76, 26);
		//contentPane.add(lblError);
		
		//Creates a button for login. When clicked, if the entered information is correct, user is redirected to home page.
		JButton logInButton = new JButton("Login");
		frame.getContentPane().add(logInButton);
		logInButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		logInButton.setBackground(new Color(255, 255, 255));
		logInButton.setForeground(new Color(64, 0, 64));
		logInButton.setBounds(522, 374, 76, 26);
				//contentPane.add(lblPassword);
				
				//Creates a text field where user can enter password
				passwordField = new JPasswordField();
				frame.getContentPane().add(passwordField);
				passwordField.setBounds(469, 309, 338, 33);
				
				//label for password
				JLabel lblPassword = new JLabel("Password");
				lblPassword.setForeground(new Color(255, 255, 255));
				frame.getContentPane().add(lblPassword);
				lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
				lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 17));
				lblPassword.setBounds(584, 258, 76, 51);
				//contentPane.add(lbluserName);
				
				//Creates a test field where user can enter user name
				textField = new JTextField();
				frame.getContentPane().add(textField);
				textField.setBounds(469, 221, 327, 26);
				//contentPane.add(textField);
				textField.setColumns(10);
				
				// label for user name
				JLabel lbluserName = new JLabel("User Name");
				lbluserName.setForeground(new Color(255, 255, 255));
				frame.getContentPane().add(lbluserName);
				lbluserName.setHorizontalAlignment(SwingConstants.CENTER);
				lbluserName.setFont(new Font("Tahoma", Font.PLAIN, 17));
				lbluserName.setBounds(562, 170, 123, 51);
				//contentPane.add(passwordField);
				
				//Creates label for displaying error messages.
				JLabel lblError = new JLabel("");
				frame.getContentPane().add(lblError);
				lblError.setForeground(new Color(255, 255, 255));
				lblError.setHorizontalAlignment(SwingConstants.CENTER);
				lblError.setBounds(550, 425, 185, 14);
				lbl = new JLabel(icon);
				lbl.setText("");
				lbl.setSize(1280, 720);
				frame.getContentPane().add(lbl);
		//contentPane.add(logInButton);
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
		//contentPane.add(backButton);
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
}