package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import businessLogic.SaveRecipe;
import businessLogic.UserActivity;
import objects.Recipes;
import objects.User;
import persistence.DatabaseAccess;
import persistence.UsersDAO;

@SuppressWarnings("serial")
public class EditIngredientView extends JFrame{
	
	//Ingredients list buttons for re-enabling upon return.
	private JButton[] listButtons;
	
	//Pane objects
	private Container contentPane;
	
	private JPanel optionsPane = new JPanel();
	private JPanel buttonPane = new JPanel();
	
	private JPanel namePane = new JPanel();
	private JPanel proteinPane = new JPanel();
	private JPanel carbsPane = new JPanel();
	private JPanel costPane = new JPanel();
	private JPanel expirationPane = new JPanel();
	private JPanel[] panes = {namePane, costPane, expirationPane, proteinPane, carbsPane, buttonPane};

	//Text field objects
	private JTextField nameField = new JTextField("");
	private JTextField proteinField = new JTextField("");
	private JTextField carbsField = new JTextField("");
	private JTextField costField = new JTextField("");
	
	//Label objects.
	private JLabel nameLabel = new JLabel("Ingredient Name");
	private JLabel proteinLabel = new JLabel("Protein (g)");
	private JLabel carbsLabel = new JLabel("Carbs (g)");
	private JLabel costLabel = new JLabel("Price");
	private JLabel expirationLabel = new JLabel("Expiration Date");
	
	//Button Objects
	private JButton cancelButton = new JButton("Cancel");
	private JButton saveButton  = new JButton("Save");
	
	//Current user
	private User currentUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaveRecipesView frame = new SaveRecipesView();
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
	public EditIngredientView(JButton[] buttons) {
		//Save previous frame's buttons to re-enable them.
		listButtons = buttons;
		
		//Set frame title.
		setTitle("RIMA - Edit Ingredient");
		//Set the application to exit when closed.
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 		
		//Set the size and pop up location of the window.
		setSize(450, 450);	
		setLocationRelativeTo(null);
		
		//Get content pane.
		contentPane = getContentPane();		
		
		//set the Layout and border of the panels.
		for(JPanel panel : panes) {
			//Set an invisible border for each panel.
			panel.setBorder(new EmptyBorder(5, 5, 5, 5));
			//Set each panel's layout manager to the horizontal box layout.
			panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		}
		
		//set the layout and border of the optionsPanel
		optionsPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		optionsPane.setLayout(new BoxLayout(optionsPane, BoxLayout.PAGE_AXIS));
				
		//Add ingredient object's attributes to text fields.
		textFieldsSetup();
		
		//Add components to respective panes.
		namePane.add(nameLabel);
		namePane.add(Box.createRigidArea(new Dimension(20, 0)));
		namePane.add(nameField);
		
		costPane.add(costLabel);
		costPane.add(Box.createRigidArea(new Dimension(20, 0)));
		costPane.add(costField);

		//Add input object here.
		expirationPane.add(expirationLabel);
		expirationPane.add(Box.createRigidArea(new Dimension(20, 0)));	
		
		proteinPane.add(proteinLabel);
		proteinPane.add(Box.createRigidArea(new Dimension(20, 0)));
		proteinPane.add(proteinField);
		
		carbsPane.add(carbsLabel);
		carbsPane.add(Box.createRigidArea(new Dimension(20, 0)));
		carbsPane.add(carbsField);		
		
		//Set up the button fonts.
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		saveButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		//Add buttons to button pane.
		buttonPane.add(Box.createHorizontalGlue());
		buttonPane.add(cancelButton);
		buttonPane.add(Box.createRigidArea(new Dimension(5, 0)));
		buttonPane.add(saveButton);
		buttonPane.add(Box.createHorizontalGlue());
		
		//Build options pane.
		optionsPane.add(Box.createVerticalGlue());
		for(int i = 0; i<panes.length-1; i++) {
			optionsPane.add(panes[i]);
		}
		optionsPane.add(Box.createVerticalGlue());
		
		//Build content pane.
		contentPane.add(optionsPane);
		contentPane.add(buttonPane, BorderLayout.PAGE_END);
		
		//Set up what to do when the back button is pressed.
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Close the UserRecipeCollection Window.
				Window win = SwingUtilities.getWindowAncestor(contentPane);
				win.dispose();	
				
				reenableButtons();
			}
		});

		//Sets up save button to add ingredient to list or edit it when pressed and close window.
		/*saveButton.addActionListener(new ActionListener() {
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
								userAllergies.replace(box.getText(), 1);
							}else if(!box.isSelected()) {
								userAllergies.replace(box.getText(), 0);
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
		});		*/
	}
	
	private void textFieldsSetup() {
		nameField.setSize(281, 26);
		nameField.setMaximumSize(nameField.getSize());
		costField.setSize(281, 26);
		costField.setMaximumSize(costField.getSize());
		proteinField.setSize(281, 26);
		proteinField.setMaximumSize(proteinField.getSize());
		carbsField.setSize(281, 26);
		carbsField.setMaximumSize(carbsField.getSize());
		
		currentUser = UserActivity.getCurrentUser();
		
		//Ensure user is logged in.
		if(currentUser != null) {
			
		//If no user logged in, close window.
		} else {
			//Close the UserRecipeCollection Window.
			Window win = SwingUtilities.getWindowAncestor(contentPane);
			win.dispose();
		}
	}
	
	private void reenableButtons() {
		for(JButton button:listButtons) {
			button.setEnabled(true);
		}
	}
}
