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
import persistence.DatabaseAccess;
import persistence.DAO;
import persistence.UsersDAO;
import javax.swing.JMenuBar;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * 
 */
@SuppressWarnings("serial")
public class RecipeCollection extends JFrame {

	//Content pane object.
	private JPanel contentPane;
	
	//List section object
	private JList<String> list;
	
	//Button objects
	private final JButton backButton = new JButton("Back");
	private final JButton addRecipeButton = new JButton("Add Custom Recipe");
	private final JButton favourites = new JButton("Favourites");
	private JTextField searchField;
	private String searchCategory;
	DefaultListModel<String> model = new DefaultListModel<String>();
	static int page=0;
	static int recipeView =0;
	DatabaseAccess access = new DatabaseAccess();
	UsersDAO db = access.usersDB();
	DAO<Recipes> dbGuest = access.recipesDB();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Create a new frame.
					RecipeCollection frame = new RecipeCollection();
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
		
		//save a reference of the user's recipes.
		ArrayList<Recipes> recipes = new ArrayList<Recipes>(); 

		if(page==0) {
			recipes = db.getRecipes(UserActivity.getCurrentUser());
		}else {
			recipes = dbGuest.getAll();
		}
	
		//Add all the user's recipes to the list model.
		for(Recipes r: recipes) {
			model.addElement(r.getName());
		}
		
		//Set the model for the list section to be the one that was 
		list.setModel(model);
	}
	public void searchUserRecipe(String searchedItem) {
		ArrayList<Recipes> recipes = new ArrayList<Recipes>(); 
		if(page==0) {
			recipes = db.getRecipes(UserActivity.getCurrentUser());
		}else {
			recipes = dbGuest.getAll();
		}
		
		for(Recipes r: recipes) {
			if(searchCategory.equals("Name") && r.getName().equalsIgnoreCase(searchedItem)) {
				model.addElement(r.getName());
			}else if(searchCategory.equals("Ingredient") && r.getIngredients().toLowerCase().contains(searchedItem.toLowerCase())) {
				model.addElement(r.getName());
			}else if(searchCategory.contains(r.mealTime) || r.mealTime.contains(searchCategory)) {
				model.addElement(r.getName());
			}
		}
		list.setModel(model);
	}

	/**
	 * Create the frame.
	 */
	public RecipeCollection() {
		setTitle("RIMA - User Recipes");

		setResizable(false);
		//Set the application to exit when closed.
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 		
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
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 53, 637, 441);
		contentPane.add(scrollPane);
		
		//Create a new section for an item list.
		list= new JList<String>(); 		
		scrollPane.setViewportView(list);
		//Set the background colour of the list section.
		list.setBackground(new Color(255, 255, 255));
		//Add the current user's saved recipes to the list section to display them.
		list.setModel(model);
		
		addUserRecipes();
		
		
		//Set up what to do when an item in the list is selected.
		list.getSelectionModel().addListSelectionListener(e-> {
			//Get the selected list item
			String name = (String) list.getSelectedValue();
			//Create a ViewRecipe window for the selected list item/recipe.
			if(page==0) {
			ViewRecipeUserCollection newWindow = new ViewRecipeUserCollection(name,recipeView);
			//Set up the ViewRecipe window and make it visible.
			newWindow.NewScreen(name,recipeView);
			}else {
				ViewRecipeDB newWindow = new ViewRecipeDB(name);
				newWindow.NewScreen(name);
			}
			contentPane.setVisible(false);
			Window win = SwingUtilities.getWindowAncestor(contentPane);
			win.dispose();
		});
		
		//Set up the font and bounds of the back button.
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		backButton.setBounds(528, 505, 98, 34);
		backButton.setForeground(new Color(255, 255, 255));
        backButton.setBackground(new Color(59, 89, 182));
		
		//add the back button to the content pane.
		contentPane.add(backButton);
		
		//Set up what to do when the back button is pressed.
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Create a HomePage window
				contentPane.setVisible(false);
				//Close the UserRecipeCollection Window.
				Window win = SwingUtilities.getWindowAncestor(contentPane);
				win.dispose();				
			}
		});
		
		//Set up the font and bounds of the Favourite button
		favourites.setFont(new Font("Tahoma", Font.PLAIN, 16));
		favourites.setForeground(new Color(255, 255, 255));
        favourites.setBackground(new Color(59, 89, 182));
		favourites.setBounds(176, 505, 116, 35);
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
		if(page==1) {favourites.setVisible(false);}
		searchField = new JTextField();
		searchField.setBounds(221, 2, 184, 40);
		contentPane.add(searchField);
		searchField.setColumns(10);
		//Set up the font and bounds of the add button.
		addRecipeButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		addRecipeButton.setForeground(new Color(255, 255, 255));
        addRecipeButton.setBackground(new Color(59, 89, 182));
		addRecipeButton.setBounds(309, 505, 200, 35);
				
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
		
		
		
		JButton searchButton = new JButton("Search");
		searchButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		searchButton.setForeground(new Color(255, 255, 255));
        searchButton.setBackground(new Color(59, 89, 182));
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.removeAllElements();
				searchUserRecipe(searchField.getText());
				
			}
		});
		searchButton.setBounds(415, 3, 89, 35);
		contentPane.add(searchButton);
		
		JButton btnNewButton_1 = new JButton("All Recipes");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
        btnNewButton_1.setBackground(new Color(59, 89, 182));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.removeAllElements();
				addUserRecipes();
			}
		});
		btnNewButton_1.setBounds(514, 3, 132, 35);
		contentPane.add(btnNewButton_1);
		
		JMenuBar searchBar = new JMenuBar();
		searchBar.setBounds(54, 0, 237, 42);
		contentPane.add(searchBar);
		
		JMenu categories = new JMenu("Search Categories");
		searchBar.add(categories);
		
		JMenuItem mntmName = new JMenuItem("Name");
		mntmName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchCategory = "Name";
				categories.setText("Search by Name");
				
			}
		});
		categories.add(mntmName);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Ingredient");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchCategory = "Ingredient";
				categories.setText("Search by ingredient");
			}
		});
		categories.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("Meal");
		categories.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Breakfast");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchCategory = "Breakfast";
				categories.setText("Breakfast Recipes");
				searchField.setText("Breakfast Recipes");
				model.removeAllElements();
				searchUserRecipe(searchField.getText());
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Lunch");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchCategory = "Lunch";
				categories.setText("Lunch Recipes");
				searchField.setText("Lunch Recipes");
				model.removeAllElements();
				searchUserRecipe(searchField.getText());
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Dinner");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchCategory = "Dinner";
				categories.setText("Dinner Recipes");
				searchField.setText("Dinner Recipes");
				model.removeAllElements();
				searchUserRecipe(searchField.getText());
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);
				
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
		if(page==1) {
			addRecipeButton.setVisible(false);
		}
	}
}
