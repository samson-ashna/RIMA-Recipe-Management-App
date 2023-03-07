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
		icon = new ImageIcon(this.getClass().getResource("/res/main_menu.jpg"));
        label = new JLabel(icon);
        label.setSize(1280, 720);

		// Login
		JButton btnLogIn = new JButton("Login");
		btnLogIn.setBounds(162, 73, 89, 23);
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
		btnGuest.setBounds(134, 165, 156, 35);
		btnGuest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecipeList listRecipes = new RecipeList();
				listRecipes.setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});

		// Welcome
		JLabel lblNewLabel = new JLabel("Welcome");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(162, 24, 122, 23);


		// User Profile
		JButton btnSignUp = new JButton("Create User Profile");
		btnSignUp.setBounds(134, 116, 156, 27);
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
		label.add(lblNewLabel);
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
