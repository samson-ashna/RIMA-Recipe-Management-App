package presentation;

import java.awt.Font;
import java.awt.Window;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Container;

import objects.Recipes;
import objects.User;
import persistence.UsersStubDB;
import persistence.DatabaseAccess;
import persistence.DAO;
import persistence.UsersDAO;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import businessLogic.UserActivity;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * 
 */
@SuppressWarnings("serial")
public class SignUpPage extends JFrame {

	private JFrame frame;
	private ImageIcon icon;
	private JLabel label;

	//Container and panel objects.
	private Container contentPane;
	private JPanel textPane;
	private JPanel buttonPane;
	
	//Component objects
	private final JLabel nameLabel = new JLabel("Username");
	private final JLabel passLabel = new JLabel("Password");
	private final JLabel confirmPassLabel = new JLabel("Confirm Password");
	private final JLabel allergiesLabel = new JLabel("Allergies");
	private JTextField enterName = new JTextField();
	private JTextField enterPass = new JPasswordField();
	private JTextField enterPassAgain = new JPasswordField();
	private final JButton registerButton = new JButton("Register");
	private final JButton cancelButton = new JButton("Cancel");
	private final JCheckBoxMenuItem eggAllergy = new JCheckBoxMenuItem("Eggs");
	private final JCheckBoxMenuItem milkAllergy = new JCheckBoxMenuItem("Milk");
	private final JCheckBoxMenuItem peanutAllergy = new JCheckBoxMenuItem("Peanuts");
	private final JCheckBoxMenuItem seafoodAllergy = new JCheckBoxMenuItem("Seafood");
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Create a new frame
					SignUpPage frame = new SignUpPage();
					//Make the frame visible.
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
	public SignUpPage() {

		// Setup
		setTitle("RIMA - Register Account");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 		
		setSize(1280, 720);
		setLocationRelativeTo(null);	

		// Background Image
		icon = new ImageIcon(this.getClass().getResource("/res/background.jpg"));
		label = new JLabel(icon);
		label.setSize(1280, 720);

		// Text Pane
		textPane = new JPanel(); 
		textPane.setOpaque(false);
		textPane.setBounds(0, 0, 462, 275);
		textPane.setBorder(new EmptyBorder(5, 5, 5, 5));	
		textPane.setLayout(new BoxLayout(textPane, BoxLayout.PAGE_AXIS));
		
		// Header fonts
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		passLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		confirmPassLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		allergiesLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		// Set up text field sizes and prevent resizing.
		enterName.setSize(281, 26);
		enterName.setMaximumSize(enterName.getSize());
		enterPass.setSize(281, 26);
		enterPass.setMaximumSize(enterPass.getSize());
		enterPassAgain.setSize(281, 26);
		enterPassAgain.setMaximumSize(enterPassAgain.getSize());
		
		//Center align labels and text fields.
		nameLabel.setAlignmentX(CENTER_ALIGNMENT);
		passLabel.setAlignmentX(CENTER_ALIGNMENT);
		confirmPassLabel.setAlignmentX(CENTER_ALIGNMENT);
		allergiesLabel.setAlignmentX(CENTER_ALIGNMENT);
		enterName.setAlignmentX(CENTER_ALIGNMENT);
		enterPass.setAlignmentX(CENTER_ALIGNMENT);
		enterPassAgain.setAlignmentX(CENTER_ALIGNMENT);

		// Username
		textPane.add(Box.createVerticalGlue());
		textPane.add(nameLabel);
		textPane.add(Box.createRigidArea(new Dimension(0, 10)));
		textPane.add(enterName);
		textPane.add(Box.createRigidArea(new Dimension(0, 20)));

		// Password
		textPane.add(passLabel);
		textPane.add(Box.createRigidArea(new Dimension(0, 10)));
		textPane.add(enterPass);
		
		// Confirm Password
		textPane.add(Box.createRigidArea(new Dimension(0, 20)));
		textPane.add(confirmPassLabel);
		textPane.add(Box.createRigidArea(new Dimension(0, 10)));
		textPane.add(enterPassAgain);
		textPane.add(Box.createRigidArea(new Dimension(0, 20)));
		
		// Allergies
		textPane.add(allergiesLabel);
		textPane.add(Box.createVerticalGlue());
		
		//Create a new button pane.
		buttonPane = new JPanel(); 
		buttonPane.setBounds(10, 516, 462, 45);
		//Set an invisible border for the text pane.
		buttonPane.setBorder(new EmptyBorder(10, 10, 10, 10));	
		//Set the text pane's layout manager to the vertical box layout.
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		getContentPane().setLayout(null);
		
		//Add text and button panes to the content pane.
		contentPane = getContentPane();
		contentPane.add(textPane);
		
		label.setHorizontalAlignment(SwingConstants.CENTER);
		//label.setForeground(new Color(255, 0, 0));
		//label.setBounds(78, 411, 322, 25);
		getContentPane().add(label);
		registerButton.setBounds(100, 435, 114, 25);
		registerButton.setBackground(new Color(59, 89, 182));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(registerButton);
		
		cancelButton.setBounds(247, 435, 114, 25);
		cancelButton.setBackground(new Color(59, 89, 182));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		getContentPane().add(cancelButton);
		
		//Set up button fonts.
		eggAllergy.setBounds(190, 266, 96, 31);
		eggAllergy.setForeground(Color.WHITE);
		getContentPane().add(eggAllergy);

		milkAllergy.setBounds(190, 308, 96, 25);
		milkAllergy.setForeground(Color.WHITE);
		getContentPane().add(milkAllergy);

		
		peanutAllergy.setBounds(190, 344, 96, 25);
		peanutAllergy.setForeground(Color.WHITE);
		getContentPane().add(peanutAllergy);
		
		seafoodAllergy.setBounds(190, 380, 96, 31);
		seafoodAllergy.setForeground(Color.WHITE);
		getContentPane().add(seafoodAllergy);
		
		
		// Add components to label
		eggAllergy.setOpaque(false);
		milkAllergy.setOpaque(false);
		peanutAllergy.setOpaque(false);
		seafoodAllergy.setOpaque(false);

		label.add(eggAllergy);
		label.add(milkAllergy);
		label.add(peanutAllergy);
		label.add(seafoodAllergy);
		label.add(registerButton);
		label.add(cancelButton);

		//Set up what to do when the cancel button is pressed.
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Create a HomePage window
				Main mainPage = new Main();
						
				//Make the HomePage window visible and the UserRecipeCollection window invisible.
				mainPage.frame.setVisible(true);
				contentPane.setVisible(false);
						
				//Close the UserRecipeCollection Window.
				Window win = SwingUtilities.getWindowAncestor(contentPane);
				win.dispose();				
			}
		});	
		


		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserActivity activity = new UserActivity();
				//Create a HomePage window
				String name = enterName.getText();
				String password = enterPass.getText();
				//Message to show so the field is not left empty
				if(name.length()==0) {
					label.setText("Need to enter a username!");
				}else if (!activity.checkUserName(name)) {
					if(!enterPassAgain.getText().equals(password) || password.isEmpty()) {
						label.setText("Passwords do not match or you have not entered password!");
					}else {
						
						//create a new login for a new user if no username or password matches in the system
						User newUser = new User(name, password);
						DatabaseAccess access = new DatabaseAccess();
						UsersDAO db = access.usersDB();
						//UsersDAO userDAO = new UsersStubDB();
						db.add(newUser);
						UserActivity.setCurrentUser(newUser);
						newUser.loggedIn = true;
						
						//Add the selected allergies by the user to their allergy information. 
						if(eggAllergy.isSelected()){
							db.editAllergy(newUser, "Eggs",1);
							//newUser.allergens.getAllergies().replace("Eggs", 1);
						}
						if(milkAllergy.isSelected()){
							db.editAllergy(newUser, "Milk",1);
							//newUser.allergens.getAllergies().replace("Milk", 1);
						}
						if(peanutAllergy.isSelected()){
							db.editAllergy(newUser, "Peanuts",1);
							//newUser.allergens.getAllergies().replace("Peanuts", 1);
						}
						if(seafoodAllergy.isSelected()){
							db.editAllergy(newUser, "Seafood",1);
							//newUser.allergens.getAllergies().replace("Seafood", 1);
						}
						HomePage homePage = new HomePage();
						homePage.setVisible(true);
						contentPane.setVisible(false);
						Window win = SwingUtilities.getWindowAncestor(contentPane);
						win.dispose();
					}
				}
				
				else {
					//other username accounts can be chosen if user knows the required username and password

					label.setText("User name already exists. Select another!");
					label.setVisible(true);
				}		
			}
		});	
	}
}
