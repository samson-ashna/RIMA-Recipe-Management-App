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
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

/**
 * 
 */
@SuppressWarnings("serial")
public class UserRecipeCollection extends JFrame {

	//Content pane object.
	private JPanel contentPane;
	
	//List section object
	private JList<String> list;
	private String category;
	
	//Button objects
	private final JButton backButton = new JButton("Back");
	private final JButton addRecipeButton = new JButton("Add Custom Recipe");
	private final JButton favourites = new JButton("Favourites");
	private JTextField searchField;
	private String searchCategory;
	DefaultListModel<String> model = new DefaultListModel<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Create a new frame.
					UserRecipeCollection frame = new UserRecipeCollection();
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
	public void addUserRecipes() {
		//Create a new list model for the user's recipes.
				
		//get a new instance of the user database.
		DatabaseAccess access = new DatabaseAccess();
		UsersDAO db = access.usersDB();
		
		//save a reference of the user's recipes.
		ArrayList<Recipes> recipes = new ArrayList<Recipes>(); 

		recipes = db.getRecipes(UserActivity.getCurrentUser());
	
		//Add all the user's recipes to the list model.
		for(Recipes r: recipes) {
			model.addElement(r.getName());
		}
		
		//Set the model for the list section to be the one that was 
		list.setModel(model);
	}
	public void searchUserRecipe(String searchedItem) {
		DatabaseAccess access = new DatabaseAccess();
		UsersDAO db = access.usersDB();	
		ArrayList<Recipes> recipes = new ArrayList<Recipes>(); 
		recipes = db.getRecipes(UserActivity.getCurrentUser());
		for(Recipes r: recipes) {
			if(searchCategory.equals("Name") && r.getName().equals(searchedItem)) {
				model.addElement(r.getName());
			}else if(searchCategory.equals("Ingredient") && r.getIngredients().contains(searchedItem)) {
				model.addElement(r.getName());
			}else if(r.mealTime.equals(searchCategory)) {
				model.addElement(r.getName());
			}
		}
		list.setModel(model);
	}

	/**
	 * Create the frame.
	 */
	public UserRecipeCollection() {
		setTitle("RIMA - User Recipes");
		//Set the application to exit when closed.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 		
		//Set the bounds of the window.
		setBounds(100, 100, 671, 591);		

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
		list.setBounds(10, 57, 637, 437);
		//Search.add()
		//Add the current user's saved recipes to the list section to display them.
		list.setModel(model);
		addUserRecipes();
		
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
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		backButton.setBounds(528, 518, 98, 21);
		
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
		
		//Set up the font and bounds of the Favourite button
		favourites.setFont(new Font("Tahoma", Font.PLAIN, 16));
		favourites.setBounds(176, 517, 116, 23);
		//add the Favourite button to the content pane
		contentPane.add(favourites);
		//Set up what to do when the Favourite button is pressed
		favourites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Create a allFavourites window
				FavouritesWindow allFavourites = new FavouritesWindow();
				//Make the allFavourites window visible
				allFavourites.setVisible(true);
				contentPane.setVisible(false);
				Window win = SwingUtilities.getWindowAncestor(contentPane);
				win.dispose();
			}
		});
		
		searchField = new JTextField();
		searchField.setBounds(225, 8, 184, 40);
		contentPane.add(searchField);
		searchField.setColumns(10);
		//Set up the font and bounds of the add button.
		addRecipeButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		addRecipeButton.setBounds(309, 517, 200, 23);
				
		//add the add button to the content pane.
		contentPane.add(addRecipeButton);
		JComboBox<String> comboBox1 = new JComboBox<String>();
		comboBox1.addItem("Select MealTime:");
		comboBox1.addItem("Breakfast");
		comboBox1.addItem("Lunch");
		comboBox1.addItem("Dinner");
		comboBox1.addItem("Lunch/Dinner");
		comboBox1.addItem("Breakfast/Lunch/Dinner");
		
		contentPane.add(comboBox1);
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("Search By:");
		comboBox.addItem("Name");
		comboBox.addItem("Meal Time");
		comboBox.addItem("Ingredient");
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					searchCategory = comboBox.getSelectedItem().toString();
					if(searchCategory.equals("Meal Time")) {
						searchField.setText(searchCategory);
					}
				}
			}
		});
		
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox.setBounds(10, 11, 205, 35);
		contentPane.add(comboBox);
		
		
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.removeAllElements();
				searchCategory = comboBox.getSelectedItem().toString();
				if(searchCategory.equals("Meal Time")) {		
					comboBox1.addItemListener(new ItemListener() {
						public void itemStateChanged(ItemEvent e) {
							
							if(e.getStateChange()==ItemEvent.SELECTED) {
								category = comboBox1.getSelectedItem().toString();
								searchCategory = category;
								searchField.setText(category);
						
							}
						}
					});
					
					JOptionPane.showMessageDialog(null, comboBox1);
				}
				searchUserRecipe(searchField.getText());
				
			}
		});
		btnNewButton.setBounds(420, 11, 89, 35);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("All Recipes");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.removeAllElements();
				addUserRecipes();
			}
		});
		btnNewButton_1.setBounds(515, 11, 132, 35);
		contentPane.add(btnNewButton_1);
				
		//Set up what to do when the add button is pressed.
		addRecipeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Create a SaveRecipesView window
				SaveRecipesView addRecipePage = new SaveRecipesView();
						
				//Make the SaveRecipesView window visible.
				addRecipePage.setVisible(true);	
				contentPane.setVisible(false);
				
				Window win = SwingUtilities.getWindowAncestor(contentPane);
				win.dispose();
			}
		});
	}
}
