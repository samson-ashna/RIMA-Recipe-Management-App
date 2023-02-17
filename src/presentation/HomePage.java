package presentation;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import businessLogic.UserActivity;

import java.awt.EventQueue;
import java.awt.Window;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


@SuppressWarnings("serial")
public class HomePage extends JFrame {
	
	//Panel object
	static JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
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
	public HomePage() {
		setTitle("RIMA - Home");
		//Set application to exit when closed.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Set size and pop up location of the window.
		setSize(450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//Creates a new button. When clicked, user is redirected to their personal recipe collection
		JButton btnNewButton = new JButton("My Collection");
		btnNewButton.setBounds(167, 50, 117, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserRecipeCollection collection = new UserRecipeCollection();
				collection.setVisible(true);
				contentPane.setVisible(false);
				Window win = SwingUtilities.getWindowAncestor(contentPane);
				win.dispose();
			}
		});
		contentPane.add(btnNewButton);
		
		//Create s new button. When clicked, the user is redirected to the app's recipe databse.
		JButton btnNewButton_1 = new JButton("Find New Recipes");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecipeList listRecipes = new RecipeList();
				listRecipes.setVisible(true);
				contentPane.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(138, 100, 174, 42);
		contentPane.add(btnNewButton_1);
		
		//Create new button for View Profile.
		JButton viewProfileButton = new JButton("View Profile");
		
		//Set up the bounds of the back button.
		viewProfileButton.setBounds(167, 175, 117, 23);
				
		//Add the back button to the content pane.
		contentPane.add(viewProfileButton);
		
		//Creates a "log out" button. When clicked user is redirected to the main window of the application.
		JButton btnNewButton_2 = new JButton("Log Out");
		btnNewButton_2.setBounds(337, 11, 89, 23);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserActivity.getCurrentUser().loggedIn = false;
				Main mainPage = new Main();
				mainPage.frame.setVisible(true);
				contentPane.setVisible(false);
				Window win = SwingUtilities.getWindowAncestor(contentPane);
				win.dispose();
			}
		});
		//Label for displaying user name
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(140, 15, 153, 14);
		contentPane.add(lblNewLabel);
		lblNewLabel.setText("Welcome "+ UserActivity.getCurrentUser().getName());
		
				
		//Set up what to do when the back button is pressed.
		viewProfileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Create a Profile window
				ViewProfile viewProfile = new ViewProfile();
						
				//Make the Profile window visible and the HomePage window invisible.
				viewProfile.setVisible(true);
				contentPane.setVisible(false);
						
				//Close the UserRecipeCollection Window.
				Window win = SwingUtilities.getWindowAncestor(contentPane);
				win.dispose();				
			}
		});
		
		
	}
}
