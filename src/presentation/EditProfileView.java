package presentation;

import java.awt.Dimension;
import java.awt.Container;
import java.awt.Font;
import java.awt.Window;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import businessLogic.UserActivity;
import objects.User;
import persistence.UsersDAO;
import persistence.DatabaseAccess;

/**
 * 
 */
@SuppressWarnings("serial")
public class EditProfileView extends JFrame {

	//Container and Panel objects.
	private Container contentPane;
	private JPanel editOptionsPane;
	private JPanel buttonPane;
	
	//Objects for displaying user info.
	private User currentUser;
	private Hashtable<String,Integer> userAllergies;
	private ArrayList<String> allergyNames;
	
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
	private ArrayList<JCheckBox> allergyBoxes = new ArrayList<JCheckBox>();
	private JPasswordField enterPass = new JPasswordField("");
	private JPasswordField enterPassAgain = new JPasswordField("");
	
	/**
	 * Set up allergy check list.
	 */
	public void allergiesSetUp() {
		currentUser = UserActivity.getCurrentUser();
		if(currentUser != null) {
			userAllergies = currentUser.getUserAllergies().getAllergies();
			allergyNames = currentUser.getUserAllergies().getAllergyNames();
			
			for(String allergy : allergyNames) {
				allergyBoxes.add(new JCheckBox(allergy));
			}
			
			for(JCheckBox box : allergyBoxes) {
				box.setAlignmentX(CENTER_ALIGNMENT);
				for(String userAllergy : allergyNames) {
					if(box.getText().equals(userAllergy) && userAllergies.get(userAllergy).intValue() == 1){
						box.setSelected(true);
					}
				}
			}
		}
	}
	
	/**
	 * Create the frame.
	 */
	public EditProfileView() {
		setTitle("RIMA - Edit Profile");
		//Set the application to exit when closed.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 		
		//Set the bounds of the window.
		setSize(350, 450);	
		setLocationRelativeTo(null);
		
		//Get content pane.
		contentPane = getContentPane();		
		
		//Create a new edit options pane.
		editOptionsPane = new JPanel(); 
		//Set an invisible border for the edit options pane.
		editOptionsPane.setBorder(new EmptyBorder(5, 5, 5, 5));		
		//Set the edit options pane's layout manager to the vertical box layout.
		editOptionsPane.setLayout(new BoxLayout(editOptionsPane, BoxLayout.PAGE_AXIS));
		
		//Centre align components.
		editAllergiesLabel.setAlignmentX(CENTER_ALIGNMENT);
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
		
		//Set up allergy check list.
		allergiesSetUp();
		
		//Add the edit components to the edit options pane.
		editOptionsPane.add(Box.createVerticalGlue());
		editOptionsPane.add(editNameLabel);
		editOptionsPane.add(Box.createRigidArea(new Dimension(0, 10)));
		editOptionsPane.add(nameField);
		editOptionsPane.add(Box.createRigidArea(new Dimension(0, 20)));
		editOptionsPane.add(editAllergiesLabel);
		editOptionsPane.add(Box.createRigidArea(new Dimension(0, 10)));
		for(JCheckBox box : allergyBoxes) {
			editOptionsPane.add(box);
		}		
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
						DatabaseAccess access = new DatabaseAccess();
						UsersDAO db = access.usersDB();
						//Get the current user.
						currentUser = UserActivity.getCurrentUser();
						
						//Flag for whether a change has been made.
						boolean change = false;
						
						//Save text in password fields.
						String newPass = new String(enterPass.getPassword());
						String confirmPass = new String(enterPassAgain.getPassword());
						
						//Reset error label.
						errorLabel.setText("");
						
						//Determine whether check boxes have changed.
						boolean allergyChange = false;
						for(JCheckBox box : allergyBoxes) {
							if((userAllergies.get(box.getText()).intValue() == 1 && !box.isSelected()) || (userAllergies.get(box.getText()).intValue() == 0 && box.isSelected())){
									allergyChange = true;
							}
						}
						UserActivity activity = new UserActivity();
						//Check that the user is logged in.
						if(currentUser != null) {
							String currentName = currentUser.getName();
							userAllergies = currentUser.getUserAllergies().getAllergies();
							allergyNames = currentUser.getUserAllergies().getAllergyNames();
							
							//If the new username exists and isn't the current user's, do nothing and inform the user that the username exists.
							if (activity.checkUserName(nameField.getText()) && !nameField.getText().equals(currentUser.getName())){
								errorLabel.setText("Username already exists!");
							//Otherwise update the user's name
							}else if(!activity.checkUserName(nameField.getText()) && !nameField.getText().equals(currentUser.getName())){
								currentUser.setName(nameField.getText());
								change = true;
								db.edit(currentName,nameField.getText(),null);
							}
								
							//If the password fields don't match, do nothing and inform the user.
							if (!newPass.equals(confirmPass)) {
								errorLabel.setText("Password confirmation does not match!");
							//If the password is new, isn't blank, and the passwords match, update the user's password.
							}else if(!activity.checkPassword(currentUser.getName(), newPass) && !newPass.equals("") && newPass.equals(confirmPass)) {
								currentUser.setPassword(newPass);
								change = true;
								db.edit(currentUser.getName(),null,newPass);
							}
							
							//If the allergies have changed, update the user's allergies.
							if(allergyChange) {
								for(JCheckBox box : allergyBoxes) {
									if(box.isSelected()){
										db.editAllergy(currentUser,box.getText(),1);
									}else if(!box.isSelected()) {
										db.editAllergy(currentUser,box.getText(),0);
									}
								}
								change = true;
							}
							
							if(change) {
								
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
					}
				});
		
		//Add the button and info panes to the content pane.
		contentPane.add(editOptionsPane);
		contentPane.add(buttonPane, BorderLayout.PAGE_END);

	}
}