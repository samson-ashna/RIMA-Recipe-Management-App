package presentation;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import domain.UserActivity;

import java.awt.Font;

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
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(162, 73, 89, 23);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});
		
		JButton btnNewButton_1 = new JButton("Continue as Guest");
		btnNewButton_1.setBounds(134, 165, 156, 35);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecipeList listRecipes = new RecipeList();
				listRecipes.setVisible(true);
//				frame.setVisible(false);
//				frame.dispose();
			}
		});
		
		JLabel lblNewLabel = new JLabel("Welcome");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(162, 24, 122, 23);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_2 = new JButton("Create User Profile");
		btnNewButton_2.setBounds(134, 116, 156, 27);
		frame.getContentPane().add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUpPage signUp = new SignUpPage();
				signUp.setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});
	}
}
