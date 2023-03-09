package presentation;

import java.util.ArrayList;

import java.awt.Font;
import java.awt.Window;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import businessLogic.UserActivity;
import objects.Recipes;
import persistence.UsersStubDB;
import persistence.DatabaseAccess;
import persistence.DAO;
import persistence.UsersDAO;

/**
 * 
 */
@SuppressWarnings("serial")
public class FavouritesWindow extends JFrame {

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
					FavouritesWindow frame = new FavouritesWindow();
					//Make the frame visible.
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Adds the current user's saved recipes to the list section to display them.
	 */
	public void favouriteRecipes() {
		//Create a new list model for the user's recipes.
		DefaultListModel<String> model = new DefaultListModel<String>();		
		//get a new instance of the user database.
		DatabaseAccess access = new DatabaseAccess();
		UsersDAO db = access.usersDB();
		//UsersDAO db = new UsersStubDB();		
		//save a reference of the user's recipes.
		ArrayList<Recipes> recipes = new ArrayList<Recipes>(); 
		//System.out.println(UserActivity.getCurrentUser().getName());
		
		recipes = db.getRecipes(UserActivity.getCurrentUser());
		//System.out.println("user colleciton accessed"+UserActivity.getCurrentUser().getName());
		//Add all the user's recipes to the list model.
		for(Recipes r: recipes) {
			if (r.favourite == 1)
			{
				model.addElement(r.getName());
			}
			//System.out.println("in db"+r.getName());
		}
		
		//Set the model for the list section to be the one that was 
		list.setModel(model);
	}

	/**
	 * Create the frame.
	 */
	public FavouritesWindow() {
		setTitle("RIMA - Favourite Recipes");
		//Set the application to exit when closed.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 		
		//Set the bounds of the window.
		setBounds(100, 100, 401, 310);		

		setLocationRelativeTo(null);
		//Create a new content pane.
		contentPane = new JPanel(); 		
		//Set an invisible border for the content pane.
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		//Replace the frame's content pane with the one that was just set up.
		setContentPane(contentPane);		
		//Set the content pane's layout manager to null for full customization.
		contentPane.setLayout(null);
		
		//Create a new section for an item list.
		list= new JList<String>(); 		
		//Set the background colour of the list section.
		list.setBackground(new Color(255, 255, 255));
		//Set the bounds of the list section
		list.setBounds(10, 11, 365, 222);
		
		//Add the current user's saved recipes to the list section to display them.
		favouriteRecipes();
		
		//Set up what to do when an item in the list is selected.
		list.getSelectionModel().addListSelectionListener(e-> {
			//Get the selected list item
			String name = (String) list.getSelectedValue();
			//Create a ViewRecipe window for the selected list item/recipe.
			
			ViewRecipeUserCollection newWindow = new ViewRecipeUserCollection(name);
			//Set up the ViewRecipe window and make it visible.
			newWindow.NewScreen(name);
			contentPane.setVisible(false);
			Window win = SwingUtilities.getWindowAncestor(contentPane);
			win.dispose();
		});
		
		//Add the list section to the content pane.
		contentPane.add(list);
		
		//Set up the font and bounds of the back button.
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		backButton.setBounds(294, 244, 81, 18);
		
		//add the back button to the content pane.
		contentPane.add(backButton);
		
		//Set up what to do when the back button is pressed.
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Create a HomePage window
				UserRecipeCollection userRecipeCollection = new UserRecipeCollection();
				
				//Make the HomePage window visible and the UserRecipeCollection window invisible.
				userRecipeCollection.setVisible(true);
				contentPane.setVisible(false);
				
				//Close the UserRecipeCollection Window.
				Window win = SwingUtilities.getWindowAncestor(contentPane);
				win.dispose();				
			}
		});
	}

}
