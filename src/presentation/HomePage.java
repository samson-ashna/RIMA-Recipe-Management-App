package presentation;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import businessLogic.UserActivity;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Window;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 */
@SuppressWarnings("serial")
public class HomePage extends JFrame {
	
	//Current HomePage instance for passing to instance of next frame in ActionListener to make next frame's back button work.
	private final HomePage homePage = this;
	
	//Panel objects.
	private JPanel breakfast;
	private JPanel lunch;
	private JPanel dinner;
	private JPanel calendar;
	private JPanel favourites;


	private JFrame frame;
	private JLabel label;
	private ImageIcon icon;

	
	
	//Button objects.
	JButton logoutButton = new JButton("Log Out");	
	JButton userRecipesButton = new JButton("My Collection");
	JButton ingredientsButton = new JButton("My Ingredients");
	JButton newRecipesButton = new JButton("Find New Recipes");
	JButton viewProfileButton = new JButton("View Profile");
	
	//Label objects.
	JLabel welcomeLabel = new JLabel("");
	private JButton mealPlannerButton = new JButton("Meal Planner");
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
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
	public HomePage() {

		frame = new JFrame("RIMA - Home");

		// Background
		//icon = new ImageIcon(this.getClass().getResource("/res/background.jpg"));
		label = new JLabel();
		label.setSize(1280, 720);
		
		// Logout Button
		logoutButton = new JButton("Logout");
		logoutButton.setForeground(new Color(255, 255, 255));
        logoutButton.setBackground(new Color(59, 89, 182));
        logoutButton.setBounds(1005, 653, 160, 23);
		label.add(logoutButton);

		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.frame.setVisible(true);
				UserActivity.getCurrentUser().loggedIn = false;
				UserActivity.setCurrentUser(null);
				frame.setVisible(false);
				frame.dispose();
			}
		});	

		// User Recipes Button
		userRecipesButton = new JButton("My Collection");
		userRecipesButton.setForeground(new Color(255, 255, 255));
        userRecipesButton.setBackground(new Color(59, 89, 182));
        userRecipesButton.setBounds(150, 10, 160, 23);
		label.add(userRecipesButton);

		userRecipesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserRecipeCollection collection = new UserRecipeCollection();
				collection.setVisible(true);
				//contentPane.setVisible(false);
				frame.dispose();
			}
		});

		// Ingredients Button
		ingredientsButton = new JButton("My Ingredients");
		ingredientsButton.setForeground(new Color(255, 255, 255));
        ingredientsButton.setBackground(new Color(59, 89, 182));
        ingredientsButton.setBounds(350, 10, 160, 23);
		label.add(ingredientsButton);

		ingredientsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IngredientsListView ingredients = new IngredientsListView();
				ingredients.setVisible(true);
				//contentPane.setVisible(false);
				frame.dispose();
			}
		});

		// New Recipes Button
		newRecipesButton = new JButton("Find New Recipes");
		newRecipesButton.setForeground(new Color(255, 255, 255));
        newRecipesButton.setBackground(new Color(59, 89, 182));
        newRecipesButton.setBounds(550, 10, 160, 23);
		label.add(newRecipesButton);

		newRecipesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecipeList listRecipes = new RecipeList();
				listRecipes.setVisible(true);
				//contentPane.setVisible(false);
				frame.dispose();
			}
		});

		// View Profile Button
		viewProfileButton = new JButton("View Profile");
		viewProfileButton.setForeground(new Color(255, 255, 255));
        viewProfileButton.setBackground(new Color(59, 89, 182));
        viewProfileButton.setBounds(750, 10, 160, 23);
		label.add(viewProfileButton);

		viewProfileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewProfile profile = new ViewProfile();
				profile.setVisible(true);
				frame.dispose();
			}
		});	

		// Meal Planner Button
		mealPlannerButton = new JButton("Meal Planner");
		mealPlannerButton.setForeground(new Color(255, 255, 255));
        mealPlannerButton.setBackground(new Color(59, 89, 182));
        mealPlannerButton.setBounds(950, 10, 160, 23);
		label.add(mealPlannerButton);

		mealPlannerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserMealPlanner planner = new UserMealPlanner();
				planner.frame.setVisible(true);
				frame.dispose();
			}
		});	
		
		// Breakfast Panel
		breakfast = new JPanel();
		JLabel breakfastLabel1 = new JLabel("Breakfast");
		breakfast.add(breakfastLabel1);
		breakfast.setBounds(40, 45, 280, 400);
		breakfast.setBackground(Color.WHITE);

		label.add(breakfast);

		// Lunch Panel
		lunch = new JPanel();
		JLabel lunchLabel1 = new JLabel("Lunch");
		lunch.add(lunchLabel1);
		lunch.setBounds(340, 45, 280, 400);
		lunch.setBackground(Color.WHITE);

		label.add(lunch);

		// Dinner Panel
		dinner = new JPanel();
		JLabel dinnerLabel1 = new JLabel("Dinner");
		dinner.add(dinnerLabel1);
		dinner.setBounds(640, 45, 280, 400);
		dinner.setBackground(Color.WHITE);

		label.add(dinner);

		// Favourites
		favourites = new JPanel();
		JLabel favouritesLabel1 = new JLabel("Favourites");
		favourites.add(favouritesLabel1);
		favourites.setBounds(940, 45, 280, 600);
		favourites.setBackground(Color.WHITE);

		label.add(favourites);
		
		// // //Set ingredients button to redirect to the user's personal ingredient collection when pushed.
		// ingredientsButton.addActionListener(new ActionListener() {
		// 	public void actionPerformed(ActionEvent e) {
		// 		IngredientsListView ingredientView = new IngredientsListView(homePage, contentPane);
				
		// 		//Save the home page window's content pane.
		// 		//ingredientsListPane = ingredientView.getContentPane();
		// 		IngredientsListView.frame.setVisible(true);
				
		// 		//Set the content pane to be the home page window's content pane.
		// 		//setContentPane(ingredientsListPane);
		// 		//validate();
		// 	}
		// });
		// getContentPane().setLayout(null);
		
		// //Add components to content pane.
		// contentPane.add(logoutPane);
		// contentPane.add(optionsPane);
		// viewProfileButton.setAlignmentX(CENTER_ALIGNMENT);
		// //optionsPane.add(Box.createRigidArea(new Dimension(0, 20)));
		// optionsPane.add(viewProfileButton);
		
		// //Set view profile button to redirect to the user's profile when pushed.
		// viewProfileButton.addActionListener(new ActionListener() {
		// 	public void actionPerformed(ActionEvent e) {
		// //Create a Profile window
		// ViewProfile viewProfile = new ViewProfile();
				
		// //Make the Profile window visible and the HomePage window invisible.
		// viewProfile.setVisible(true);
		// contentPane.setVisible(false);
				
		// //Close the old window.
		// Window win = SwingUtilities.getWindowAncestor(contentPane);
		// win.dispose();				
		// 	}
		// });
		
		

		// Add Objects
		// frame.add(label);
		// frame.add(welcomeLabel);
		// frame.add(userRecipesButton);
		// frame.add(newRecipesButton);
		// frame.add(logoutButton);
		// frame.add(ingredientsButton);
		// frame.add(viewProfileButton);
		// Setup
		
		
		frame.getContentPane().setBackground(new Color(143, 188, 143));
		frame.add(label);
		
		frame.setSize(1280,720);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		//optionsPane.add(mealPlannerButton_1);
	}
}
