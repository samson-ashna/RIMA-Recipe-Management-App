package presentation;

import java.awt.Font;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 
 */
public class Main {

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
		new Main();
	}

	/**
	 * Create the application.
	 */
	public Main() {

		// Background Image
		icon = new ImageIcon(this.getClass().getResource("/res/menu.jpg"));
        label = new JLabel(icon);
        label.setSize(1280, 720);

		// Login
		JButton btnLogIn = new JButton("Login");
		btnLogIn.setBounds(560, 380, 156, 27);
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});

		// Guest Login
		JButton btnGuest = new JButton("Continue as Guest");
		btnGuest.setBounds(560, 420, 156, 35);
		btnGuest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecipeList listRecipes = new RecipeList();
				listRecipes.setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});

		// User Profile
		JButton btnSignUp = new JButton("Create User Profile");
		btnSignUp.setBounds(560, 470, 156, 27);
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUpPage signUp = new SignUpPage();
				signUp.setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});

		// Setup
		label.add(btnLogIn);
		label.add(btnGuest);
		label.add(btnSignUp);

		frame = new JFrame("RIMA - Welcome!");
		frame.add(label);
		frame.setSize(1280, 720);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

}
