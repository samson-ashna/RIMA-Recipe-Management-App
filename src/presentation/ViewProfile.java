package presentation;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import objects.Recipes;
import objects.User;
import persistence.DAO;
import persistence.UserDAOImpl;

@SuppressWarnings("serial")
public class ViewProfile extends JFrame {

	//Content pane object.
	private JPanel contentPane;
	
	//List section object
	private JList<String> list;
	
	//Button objects
	private final JButton backButton = new JButton("Back");

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

	/**
	 * Create the frame.
	 */
	public ViewProfile() {
		
		//Set the application to exit when closed.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 		
		//Set the bounds of the window.
		setBounds(100, 100, 319, 270);		
		//Create a new content pane.
		contentPane = new JPanel(); 		
		//Set an invisible border for the content pane.
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		//Replace the frame's content pane with the one that was just set up.
		setContentPane(contentPane);		
		//Set the content pane's layout manager to null for full customization.
		contentPane.setLayout(null);

		
		//Set up the font and bounds of the back button.
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		backButton.setBounds(225, 211, 65, 18);
		
		//add the back button to the content pane.
		contentPane.add(backButton);
		
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
	}
}
