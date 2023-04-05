package presentation;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import businessLogic.UserActivity;

import java.awt.Window;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.awt.event.ActionEvent;
import java.awt.Container;
import objects.User;

import java.awt.Color;

/**
 * 
 */
@SuppressWarnings("serial")
public class ViewProfile extends JFrame {

	//Container and Panel objects.
	private Container contentPane;
	private Container editProfileContentPane;
	private JPanel infoPane;
	private JPanel buttonPane;
	
	//Component objects.
	private JButton backButton = new JButton("Back");
	private JButton editProfileButton = new JButton("Edit Profile");
	private JLabel displName = new JLabel();
	private JLabel allergyInfo =  new JLabel();
	private JLabel label = new JLabel();
	private JFrame frame;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Adds the current user's info (user name and allergies) to the user info label.
	 */
	public void displayUserInfo() {
		contentPane = getContentPane();		

		User currentUser = UserActivity.getCurrentUser();		
		if(currentUser != null) {
			
			displName.setText(currentUser.getName());
			Hashtable<String,Integer> userAllergies = currentUser.getUserAllergies().getAllergies();
			ArrayList<String> allergyNames = currentUser.getUserAllergies().getAllergyNames();
			String allergies="";
			for(String allergy : allergyNames) {
				if(userAllergies.get(allergy).intValue() == 1) {
					allergies+=allergy+",";
				}
			}
			allergyInfo.setText("<html>Allergies<br>"+"\n"+allergies);
		}else {
			displName.setText("Error: User not logged in.");
		}
		
	}
	
	
	/**
	 * Create the frame.
	 */
	public ViewProfile() {
		
		frame = this;
		frame.setTitle("RIMA - User Profile");
		frame.setSize(450, 450);	
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(143, 188, 143));

		label = new JLabel();
		
		// contentPane = getContentPane();		
		JPanel userPanel = new JPanel();

		//Create a new info pane.
		infoPane = new JPanel();
		infoPane.setBounds(15, 50, 405, 325);
		
		displayUserInfo();

		
		//Align labels.
		displName.setAlignmentX(CENTER_ALIGNMENT);
		allergyInfo.setAlignmentX(CENTER_ALIGNMENT);		
		


		displName.setBounds(0, 0, 50, 50);
		allergyInfo.setBounds(0, 20, 50, 50);

		userPanel.add(displName);
		infoPane.add(allergyInfo);
		infoPane.setBackground(Color.WHITE);
				
		//Create a new pane for buttons.
		buttonPane = new JPanel();
		buttonPane.setOpaque(false);
		buttonPane.setBounds(15, 377, 405, 40);

		//Set up the button fonts.
		
		editProfileButton.setForeground(new Color(255, 255, 255));
        editProfileButton.setBackground(new Color(59, 89, 182));
		editProfileButton.setBounds(400, 400, 160, 23);

		backButton.setForeground(new Color(255, 255, 255));
        backButton.setBackground(new Color(59, 89, 182));
		
		//Add buttons to button pane.
		buttonPane.add(Box.createHorizontalGlue());
		buttonPane.add(backButton);
		buttonPane.add(Box.createRigidArea(new Dimension(5, 0)));
		buttonPane.add(editProfileButton);
		
		//Add info and button panes to content pane.
		
		userPanel.setBounds(15, 10, 405, 30);

		label.add(userPanel);
		label.add(infoPane);
		label.add(buttonPane);
		

		frame.add(label);
		frame.setVisible(true);
		
		//Set up what to do when the back button is pressed.
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			contentPane.setVisible(false);
				
		//Close the UserRecipeCollection Window.
		Window win = SwingUtilities.getWindowAncestor(contentPane);
		win.dispose();				
			}
		});
		
		//Set up what to do when the back button is pressed.
		editProfileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				showEditProfileView();
				
			}
		});				

	}

	private void showEditProfileView() {
		
		EditProfileView editProfileView = new EditProfileView();
		
		editProfileContentPane = editProfileView.getContentPane();
		
		setContentPane(editProfileContentPane);
		validate();
	}
}