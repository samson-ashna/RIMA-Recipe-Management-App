package presentation;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import businessLogic.UserActivity;
import objects.User;
import persistence.DatabaseAccess;
import persistence.UsersDAO;
import objects.Planner;
import objects.Recipes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Window;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import java.awt.Scrollbar;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

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
	private JPanel planner;


	private JFrame frame;
	private JLabel label;
	private ImageIcon icon;
	private JTable table;
	JTextArea lunchText;
	JTextArea breakfastText;
	JTextArea dinnerText;

	
	
	//Button objects.
	JButton logoutButton = new JButton("Log Out");	
	JButton userRecipesButton = new JButton("My Collection");
	JButton ingredientsButton = new JButton("My Ingredients");
	JButton newRecipesButton = new JButton("Find New Recipes");
	JButton viewProfileButton = new JButton("View Profile");
	
	//Label objects.
	JLabel welcomeLabel = new JLabel("");
	private JButton mealPlannerButton = new JButton("Meal Planner");
	private JComboBox comboBox;
	private JList list;
	DatabaseAccess access = new DatabaseAccess();
	UsersDAO db = access.usersDB();
	DefaultListModel<String> model = new DefaultListModel<String>();		

	
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
	public void favouriteRecipes() {
		ArrayList<Recipes> recipes = new ArrayList<Recipes>(); 
		recipes = db.getFavoriteList(UserActivity.getCurrentUser());
		//Add all the user's favourite recipes to the list model.
		for(Recipes r: recipes) {
			model.addElement(r.getName());
		}
		
		//Set the model for the list section to be the one that was 
		list.setModel(model);
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
				collection.page =0;
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
		breakfast.setLayout(null);
		JLabel breakfastLabel1 = new JLabel("Breakfast");
		breakfastLabel1.setBounds(116, 5, 99, 14);
		breakfast.add(breakfastLabel1);
		breakfast.setBounds(40, 45, 280, 400);
		breakfast.setBackground(Color.WHITE);

		label.add(breakfast);
		
		breakfastText = new JTextArea();
		breakfastText.setLineWrap(true);
		breakfastText.setBounds(10, 30, 260, 359);
		breakfast.add(breakfastText);
		// Lunch Panel
		lunch = new JPanel();
		lunch.setLayout(null);
		JLabel lunchLabel1 = new JLabel("Lunch");
		lunchLabel1.setBounds(125, 5, 79, 14);
		lunch.add(lunchLabel1);
		lunch.setBounds(340, 45, 280, 400);
		lunch.setBackground(Color.WHITE);

		label.add(lunch);
		
		lunchText = new JTextArea();
		lunchText.setBounds(10, 41, 260, 359);
		lunch.add(lunchText);

		// Dinner Panel
		dinner = new JPanel();
		dinner.setLayout(null);
		JLabel dinnerLabel1 = new JLabel("Dinner");
		dinnerLabel1.setBounds(123, 5, 63, 14);
		dinner.add(dinnerLabel1);
		dinner.setBounds(640, 45, 280, 400);
		dinner.setBackground(Color.WHITE);

		label.add(dinner);
		
		dinnerText = new JTextArea();
		dinnerText.setBounds(10, 30, 260, 359);
		dinner.add(dinnerText);

		// Favourites Panel
		favourites = new JPanel();
		favourites.setLayout(null);
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBounds(31, 52, 222, 439);
		favourites.add(textArea);
		textArea.setVisible(false);
		JLabel favouritesLabel1 = new JLabel("Favourites");
		favouritesLabel1.setBounds(114, 5, 118, 14);
		favourites.add(favouritesLabel1);
		favourites.setBounds(940, 45, 280, 600);
		favourites.setBackground(Color.WHITE);

		label.add(favourites);
		JButton btnNewButton = new JButton("Back to List");
		btnNewButton.setBounds(137, 566, 133, 23);
		btnNewButton.setVisible(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewButton.setVisible(false);
				textArea.setVisible(false);
				favouriteRecipes();
			}
		});
		favourites.add(btnNewButton);

		
		list = new JList();
		list.setBounds(23, 43, 230, 510);
		favourites.add(list);
		list.getSelectionModel().addListSelectionListener(e-> {
			//Get the selected list item
			String name = (String) list.getSelectedValue();
			//Create a ViewRecipe window for the selected list item/recipe.
			Recipes r = db.getRecipe(UserActivity.currentUser,name);
			if(r != null) {
				model.clear();
				textArea.setVisible(true);
				textArea.setText(r.toString());
				btnNewButton.setVisible(true);

			}
		});

		// Meal Planner Panel
		planner = new JPanel();
		planner.setLayout(null);
		JLabel plannerLabel1 = new JLabel("Select A Day To View Recipes");
		plannerLabel1.setBounds(10,11,275,14);
		planner.add(plannerLabel1);
		planner.setBounds(40, 465, 880, 180);
		planner.setBackground(Color.WHITE);
		table = new JTable();
		table.setEnabled(false);
		table.setSize(652, 128);
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		 User u = UserActivity.getCurrentUser();
		 HashMap<String, Planner> p = u.getWeekPlanner();
		 
		 comboBox = new JComboBox();
		 comboBox.addItemListener(new ItemListener() {
		 	public void itemStateChanged(ItemEvent e) {
		 		breakfastText.setText(null);
		 		lunchText.setText(null);
		 		dinnerText.setText(null);

		 		String day = day = comboBox.getSelectedItem().toString();
		 		DatabaseAccess access = new DatabaseAccess();
				UsersDAO db = access.usersDB();
				User currentUser = UserActivity.currentUser;
				Recipes rBreakfast = db.getRecipe(currentUser,currentUser.getWeekPlanner().get(day).breakfast);
				if( currentUser.getWeekPlanner().get(day).breakfast != ""){
					if(rBreakfast  == null) {
						breakfastText.setText(currentUser.getWeekPlanner().get(day).breakfast+"\n recipe not avaialable in collection.");
					}else {
						breakfastText.setText(rBreakfast.toString());
					}
				}
				if(currentUser.getWeekPlanner().get(day).lunch != "") {
					Recipes rLunch = db.getRecipe(currentUser,currentUser.getWeekPlanner().get(day).lunch);
					if( rLunch  == null) {
						lunchText.setText(currentUser.getWeekPlanner().get(day).lunch+"\n recipe not avaialable in collection.");
					}else {
						lunchText.setText( rLunch.toString());
					}
				}
				if(currentUser.getWeekPlanner().get(day).dinner != "") {
					Recipes rDinner = db.getRecipe(currentUser,currentUser.getWeekPlanner().get(day).dinner);
					if(rDinner  == null ) {
						dinnerText.setText(currentUser.getWeekPlanner().get(day).dinner+"\n recipe not avaialable in collection.");
					}else {
						dinnerText.setText(rDinner .toString());
					}
				}
				
		 	}
		 });
		 comboBox.setBounds(23,47,136,22);
		 String WhichDayofWeek[] = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
		 for(String d: WhichDayofWeek) {
			  comboBox.addItem(d);
		 }
		
		 planner.add(comboBox);
		 table.setModel(new DefaultTableModel (
			new Object[][] {
		 		{"Day", "Breakfast", "Lunch", "Dinner"},
		 		{"Monday", p.get("Monday").breakfast, p.get("Monday").lunch, p.get("Monday").dinner},
		 		{"Tuesday", p.get("Tuesday").breakfast, p.get("Tuesday").lunch, p.get("Tuesday").dinner},
		 		{"Wednesday",p.get("Wednesday").breakfast, p.get("Wednesday").lunch, p.get("Wednesday").dinner},
		 		{"Thursday", p.get("Thursday").breakfast, p.get("Thursday").lunch, p.get("Thursday").dinner},
		 		{"Friday", p.get("Friday").breakfast, p.get("Friday").lunch, p.get("Friday").dinner},
		 		{"Saturday", p.get("Saturday").breakfast, p.get("Saturday").lunch, p.get("Saturday").dinner},
		 		{"Sunday", p.get("Sunday").breakfast, p.get("Sunday").lunch, p.get("Sunday").dinner},
		 	},
		 	new String[] {
		 		"Day", "Breakfast", "Lunch", "Dinner"
		 	}


		));
		table.setLocation(192, 22);
		//JCalendar calendar = new JCalendar();
		//calendar.setBounds(40, 222, 233, 153);
		//panel_3_calendar.add(calendar);
		//planner.add(calendar);
		planner.add(table);

		label.add(planner);
		
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
		frame.getContentPane().add(label);
		
		frame.setSize(1280,720);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		favouriteRecipes();
		
		
		//optionsPane.add(mealPlannerButton_1);
	}
}

