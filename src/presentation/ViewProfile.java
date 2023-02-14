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
public class ViewProfile extends JFrame {

	//Container and Panel objects.
	private Container contentPane;
	private JPanel infoPane;
	private JPanel buttonPane;
	
	//Objects for displaying user info.
	private UserDAOImpl userDAO;
	private User currentUser;
	private JLabel userInfo;
	
	//Button objects
	private final JButton backButton = new JButton("Back");
	private final JButton editProfileButton = new JButton("Edit Profile");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Create a new frame.
					ViewProfile frame = new ViewProfile();
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
	public ViewProfile() {
		
		//Set the application to exit when closed.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 		
		//Set the bounds of the window.
		setBounds(100, 100, 319, 270);		
		
		//Get content pane.
		contentPane = getContentPane();		
		
		//Create a new info pane.
		infoPane = new JPanel(); 
		//Set the content pane's layout manager to the vertical box layout.
		infoPane.setLayout(new BoxLayout(infoPane, BoxLayout.PAGE_AXIS));
		
		//Create new label for displaying user info.
		userInfo = new JLabel();
		//Set an invisible border for the label.
		userInfo.setBorder(new EmptyBorder(5, 5, 5, 5));
		//Add user info to the label.
		displayUserInfo();		
		//Add the label to the content pane.
		infoPane.add(userInfo);
				
		//Create a new pane for buttons.
		buttonPane = new JPanel(); 		
		//Set an invisible border for the button pane.
		buttonPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//Set the button pane's layout manager to the horizontal box layout.
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		
		//Set up the font of the edit profile button.
		editProfileButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		//Right align buttons.		
		buttonPane.add(Box.createHorizontalGlue());
		//Add the edit profile button to the button pane.
		buttonPane.add(editProfileButton);		
		
		//Set up what to do when the back button is pressed.
		editProfileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ToDo			
			}
		});	
		
		//Set up the font of the back button.
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		//Add space between buttons.
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		//Add the back button to the button pane.
		buttonPane.add(backButton);		
				
		//Set up what to do when the back button is pressed.
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Create a HomePage window
				HomePage homePage = new HomePage();
						
				//Make the HomePage window visible and the UserRecipeCollection window invisible.
				homePage.setVisible(true);
				contentPane.setVisible(false);
						
				//Close the UserRecipeCollection Window.
				Window win = SwingUtilities.getWindowAncestor(contentPane);
				win.dispose();				
			}
		});
		
		//Add the button and info panes to the content pane.
		contentPane.add(infoPane);
		contentPane.add(buttonPane, BorderLayout.PAGE_END);

	}
}
