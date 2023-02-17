package presentation;



import java.awt.Font;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

	protected static JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("RIMA - Welcome!");
		frame.setSize(450, 300);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Creates a log in button. When clicked user is redirected to log in page.
		JButton btnLogIn = new JButton("Login");
		btnLogIn.setBounds(162, 73, 89, 23);
		frame.getContentPane().add(btnLogIn);
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		//Creates a new button. When clicked user is redirected to the page where they can view recipes in the app's database.
		JButton btnGuest = new JButton("Continue as Guest");
		btnGuest.setBounds(134, 165, 156, 35);
		frame.getContentPane().add(btnGuest);
		btnGuest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecipeList listRecipes = new RecipeList();
				listRecipes.setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		//label for welcome message.
		JLabel lblNewLabel = new JLabel("Welcome");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(162, 24, 122, 23);
		frame.getContentPane().add(lblNewLabel);
		
		//Creates a new button. When clicked user is redirected to sign up page.
		JButton btnSignUp = new JButton("Create User Profile");
		btnSignUp.setBounds(134, 116, 156, 27);
		frame.getContentPane().add(btnSignUp);
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUpPage signUp = new SignUpPage();
				signUp.setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});
	}
}
