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
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import objects.Recipes;
import objects.User;
import persistence.DAO;
import persistence.UserDAOImpl;

@SuppressWarnings("serial")
public class EditProfileView extends JFrame {

	//Container and Panel objects.
	private Container contentPane;
	private JPanel editButtonsPane;
	private JPanel cancelButtonPane;
	
	//Objects for displaying user info.
	private UserDAOImpl userDAO;
	private User currentUser;
	private JLabel userInfo;
	
	//Button objects
	private final JButton cancelButton = new JButton("Cancel");
	private final JButton editAllergiesButton = new JButton("Edit Allergies");
	private final JButton editNameButton = new JButton("Change Username");
	private final JButton editPassButton = new JButton("Change Password");

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

	//Adds the current user's info to the user info label.
	public void displayUserInfo() {
		//Get current user.
		userDAO = new UserDAOImpl();
		currentUser = userDAO.getCurrentUser();
		
		//Add user info to label.
		userInfo.setText("temp");
		
	}
	
	/**
	 * Create the frame.
	 */
	public EditProfileView() {
		setTitle("RIMA - Edit Profile");
		//Set the application to exit when closed.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 		
		//Set the bounds of the window.
		setSize(319, 270);	
		setLocationRelativeTo(null);
		
		//Get content pane.
		contentPane = getContentPane();		
		
		//Create a new edit buttons pane.
		editButtonsPane = new JPanel(); 
		//Set an invisible border for the edit buttons pane.
		editButtonsPane.setBorder(new EmptyBorder(5, 5, 5, 5));		
		//Set the edit buttons pane's layout manager to the vertical box layout.
		editButtonsPane.setLayout(new BoxLayout(editButtonsPane, BoxLayout.PAGE_AXIS));
		
		editAllergiesButton.setAlignmentX(CENTER_ALIGNMENT);
		editNameButton.setAlignmentX(CENTER_ALIGNMENT);
		editPassButton.setAlignmentX(CENTER_ALIGNMENT);
		
		//Add the edit buttons to the edit buttons pane.
		editButtonsPane.add(Box.createVerticalGlue());
		editButtonsPane.add(editAllergiesButton);
		editButtonsPane.add(Box.createRigidArea(new Dimension(0, 10)));
		editButtonsPane.add(editNameButton);
		editButtonsPane.add(Box.createRigidArea(new Dimension(0, 10)));
		editButtonsPane.add(editPassButton);		
		editButtonsPane.add(Box.createVerticalGlue());
				
		//Create a new pane for the cancel button.
		cancelButtonPane = new JPanel(); 		
		//Set an invisible border for the button pane.
		cancelButtonPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//Set the button pane's layout manager to the horizontal box layout.
		cancelButtonPane.setLayout(new BoxLayout(cancelButtonPane, BoxLayout.LINE_AXIS));
		
		//Set up the font of the edit profile button.
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		//Right align buttons.		
		cancelButtonPane.add(Box.createHorizontalGlue());
		//Add the edit profile button to the button pane.
		cancelButtonPane.add(cancelButton);		
		
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
		
		//Add the button and info panes to the content pane.
		contentPane.add(editButtonsPane);
		contentPane.add(cancelButtonPane, BorderLayout.PAGE_END);

	}
}
