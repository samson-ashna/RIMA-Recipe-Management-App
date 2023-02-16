package presentation;

import java.awt.Dimension;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import domain.UserActivity;

import objects.User;

import persistence.UserDAOImpl;

@SuppressWarnings("serial")
public class EditProfileView extends JFrame {

	//Container and Panel objects.
	private Container contentPane;
	private JPanel editOptionsPane;
	private JPanel buttonPane;
	
	//Objects for displaying user info.
	private UserDAOImpl userDAO;
	private User currentUser;	
	
	//Label and Button objects
	private final JButton cancelButton = new JButton("Cancel");
	private final JButton saveButton = new JButton("Save Changes");
	private final JLabel editAllergiesLabel = new JLabel("Allergies");
	private final JLabel editNameLabel = new JLabel("Username");
	private final JLabel editPass = new JLabel("Change Password");
	private final JLabel confirmPass = new JLabel("Confirm Password");
	private final JLabel errorLabel = new JLabel();
	
	//Text area objects
	private JTextField nameField = new JTextField("");
	private JList allergyList = new JList();
	private JPasswordField enterPass = new JPasswordField("");
	private JPasswordField enterPassAgain = new JPasswordField("");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Create a new frame.
					EditProfileView frame = new EditProfileView();
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
	public EditProfileView() {
		setTitle("RIMA - Edit Profile");
		//Set the application to exit when closed.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 		
		//Set the bounds of the window.
		setSize(350, 350);	
		setLocationRelativeTo(null);
		
		//Get content pane.
		contentPane = getContentPane();		
		
		//Create a new edit buttons pane.
		editOptionsPane = new JPanel(); 
		//Set an invisible border for the edit buttons pane.
		editOptionsPane.setBorder(new EmptyBorder(5, 5, 5, 5));		
		//Set the edit buttons pane's layout manager to the vertical box layout.
		editOptionsPane.setLayout(new BoxLayout(editOptionsPane, BoxLayout.PAGE_AXIS));
		
		//Centre align components.
		editAllergiesLabel.setAlignmentX(CENTER_ALIGNMENT);
		allergyList.setAlignmentX(CENTER_ALIGNMENT);
		editNameLabel.setAlignmentX(CENTER_ALIGNMENT);
		nameField.setAlignmentX(CENTER_ALIGNMENT);
		editPass.setAlignmentX(CENTER_ALIGNMENT);
		confirmPass.setAlignmentX(CENTER_ALIGNMENT);
		enterPass.setAlignmentX(CENTER_ALIGNMENT);
		enterPassAgain.setAlignmentX(CENTER_ALIGNMENT);
		errorLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		//Set text field sizes.
		nameField.setSize(281, 26);
		nameField.setMaximumSize(nameField.getSize());
		enterPass.setSize(281, 26);
		enterPass.setMaximumSize(nameField.getSize());
		enterPassAgain.setSize(281, 26);
		enterPassAgain.setMaximumSize(nameField.getSize());
		
		//Add current username to name field.
		currentUser = UserActivity.getCurrentUser();		
		if(currentUser != null) {
			nameField.setText(currentUser.getName());
		}
		
		//Set up error label font and colour.
		errorLabel.setForeground(new Color(255, 0, 0));
		errorLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		//Add the edit components to the edit options pane.
		editOptionsPane.add(Box.createVerticalGlue());
		editOptionsPane.add(editNameLabel);
		editOptionsPane.add(Box.createRigidArea(new Dimension(0, 10)));
		editOptionsPane.add(nameField);
		editOptionsPane.add(Box.createRigidArea(new Dimension(0, 20)));
		editOptionsPane.add(editAllergiesLabel);
		editOptionsPane.add(Box.createRigidArea(new Dimension(0, 10)));
		editOptionsPane.add(allergyList);
		editOptionsPane.add(Box.createRigidArea(new Dimension(0, 20)));
		editOptionsPane.add(editPass);	
		editOptionsPane.add(Box.createRigidArea(new Dimension(0, 10)));
		editOptionsPane.add(enterPass);
		editOptionsPane.add(Box.createRigidArea(new Dimension(0, 10)));
		editOptionsPane.add(confirmPass);	
		editOptionsPane.add(Box.createRigidArea(new Dimension(0, 10)));
		editOptionsPane.add(enterPassAgain);
		editOptionsPane.add(Box.createRigidArea(new Dimension(0, 10)));
		editOptionsPane.add(errorLabel);
		editOptionsPane.add(Box.createVerticalGlue());
				
		//Create a new pane for the cancel button.
		buttonPane = new JPanel(); 		
		//Set an invisible border for the button pane.
		buttonPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//Set the button pane's layout manager to the horizontal box layout.
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		
		//Set up the font of the buttons.
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		saveButton.setFont(new Font("Tahoma", Font.PLAIN, 10));

		//Add buttons to button pane	
		buttonPane.add(Box.createHorizontalGlue());
		buttonPane.add(saveButton);
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPane.add(cancelButton);
		buttonPane.add(Box.createHorizontalGlue());
		
		
		//Set up what to do when the cancel button is pressed.
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Create a HomePage window
				ViewProfile viewProfile = new ViewProfile();
						
				//Make the HomePage window visible and the UserRecipeCollection window invisible.
				viewProfile.setVisible(true);
				contentPane.setVisible(false);
						
				//Close the UserRecipeCollection Window.
				Window win = SwingUtilities.getWindowAncestor(contentPane);
				win.dispose();				
			}
		});
		
		//Set up what to do when the save changes button is pressed.
				saveButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//Get the current user.
						currentUser = UserActivity.getCurrentUser();
						String newPass = new String(enterPass.getPassword());
						String confirmPass = new String(enterPassAgain.getPassword());
						errorLabel.setText("");
		
						
						//Check that the user is logged in and that either the name field has changed or the password field has text.
						if(currentUser != null
							&& !(nameField.getText().equals(currentUser.getName()) && newPass.equals(""))) {
							
							//If the new username exists and isn't the current user's, do nothing and inform the user that the username exists.
							if (UserActivity.checkUserName(nameField.getText()) && !nameField.getText().equals(currentUser.getName())){
								errorLabel.setText("Username already exists!");
								return;
							//If the password fields don't match, do nothing and inform the user.
							}else if (!newPass.equals(confirmPass)) {
								errorLabel.setText("Password confirmation does not match!");
								return;
							//If the password isn't new and a new username wasn't entered do nothing.
							}else if(UserActivity.checkPassword(currentUser.getName(), newPass) && nameField.getText().equals(currentUser.getName())) {
								return;
							//Otherwise, update the user's name and password.
							}else {
								currentUser.setName(nameField.getText());
								currentUser.setPassword(newPass);
							}
							
							//Create a HomePage window
							ViewProfile viewProfile = new ViewProfile();
									
							//Make the HomePage window visible and the UserRecipeCollection window invisible.
							viewProfile.setVisible(true);
							contentPane.setVisible(false);
									
							//Close the UserRecipeCollection Window.
							Window win = SwingUtilities.getWindowAncestor(contentPane);
							win.dispose();
						}			
					}
				});
		
		//Add the button and info panes to the content pane.
		contentPane.add(editOptionsPane);
		contentPane.add(buttonPane, BorderLayout.PAGE_END);

	}
}
