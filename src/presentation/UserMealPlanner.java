package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import businessLogic.UserActivity;
import objects.Planner;
import objects.Recipes;
import objects.User;
import persistence.DatabaseAccess;
import persistence.UsersDAO;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class UserMealPlanner {

	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	String day="Sunday";
	String date;
	String time,time2;
	static JTextArea textArea = new JTextArea();
	DatabaseAccess access = new DatabaseAccess();
	UsersDAO db = access.usersDB();
	DefaultListModel<String> model = new DefaultListModel<String>();	
	JList<String> list = new JList<String>();
	HashMap<String, Planner> p = UserActivity.currentUser.getWeekPlanner();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserMealPlanner window = new UserMealPlanner();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserMealPlanner() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(143, 188, 143));
		frame.setBounds(0, 0, 1280, 680);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		
		JPanel panel_recipeplanning = new JPanel();
		panel_recipeplanning.setBorder(new LineBorder(new Color(0, 0, 0), 8));
		panel_recipeplanning.setBounds(10, 58, 562, 269);
		frame.getContentPane().add(panel_recipeplanning);
		panel_recipeplanning.setLayout(null);
		
		// created labels and buttons for the first panel that selects recipes listed out
		JLabel lblNewLabel_1 = new JLabel("Placeholder:Recipe #1");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
        lblNewLabel_1.setBackground(new Color(59, 89, 182));
		lblNewLabel_1.setBounds(23, 72, 196, 22);
		panel_recipeplanning.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Add Recipes to View Nutrition Information");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1.setBounds(23, 24, 361, 37);
		panel_recipeplanning.add(lblNewLabel_1_1);
		
		// created a drop menu which selects the quantity of servings being made for the recipe
		String quantity[] = {"1","2","3","4","5"};
		JComboBox<?> comboBox = new JComboBox<Object>(quantity);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox.setEditable(true);
		comboBox.setMaximumRowCount(20);
		comboBox.setBounds(414, 68, 82, 30);
		panel_recipeplanning.add(comboBox);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Servings Quantity");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1_1_1.setBounds(387, 24, 175, 37);
		panel_recipeplanning.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Placeholder:Recipe #2");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(23, 109, 196, 22);
		panel_recipeplanning.add(lblNewLabel_1_2);
		
		String quantity2[] = {"1","2","3","4","5"};
		JComboBox<?> comboBox_1 = new JComboBox<Object>(quantity2);
		comboBox_1.setMaximumRowCount(20);
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_1.setEditable(true);
		comboBox_1.setBounds(414, 105, 82, 30);
		panel_recipeplanning.add(comboBox_1);
		
		JLabel lblNewLabel_1_3 = new JLabel("Placeholder:Recipe #3");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_3.setBounds(23, 145, 196, 22);
		panel_recipeplanning.add(lblNewLabel_1_3);
		
		JButton btnNewButton_2 = new JButton("Select");
		btnNewButton_2.setForeground(new Color(255, 255, 255));
        btnNewButton_2.setBackground(new Color(59, 89, 182));
		panel_recipeplanning.add(btnNewButton_2);
		
		String quantity3[] = {"1","2","3","4","5"};
		JComboBox<?> comboBox_2 = new JComboBox<Object>(quantity3);
		comboBox_2.setMaximumRowCount(20);
		comboBox_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_2.setEditable(true);
		comboBox_2.setBounds(414, 213, 82, 30);
		panel_recipeplanning.add(comboBox_2);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Placeholder:Recipe #4");
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_3_1.setBounds(23, 182, 196, 22);
		panel_recipeplanning.add(lblNewLabel_1_3_1);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("Placeholder:Recipe #5");
		lblNewLabel_1_3_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_3_2.setBounds(23, 217, 196, 22);
		panel_recipeplanning.add(lblNewLabel_1_3_2);
		
		String quantity4[] = {"1","2","3","4","5"};
		JComboBox<?> comboBox_1_3 = new JComboBox<Object>(quantity4);
		comboBox_1_3.setMaximumRowCount(20);
		comboBox_1_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_1_3.setEditable(true);
		comboBox_1_3.setBounds(414, 180, 82, 30);
		panel_recipeplanning.add(comboBox_1_3);
		
		String quantity5[] = {"1","2","3","4","5"};
		JComboBox<?> comboBox_1_2 = new JComboBox<Object>(quantity5);
		comboBox_1_2.setMaximumRowCount(20);
		comboBox_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_1_2.setEditable(true);
		comboBox_1_2.setBounds(414, 141, 82, 30);
		panel_recipeplanning.add(comboBox_1_2);
		
		JComboBox<String> comboBox_4 = new JComboBox<String>();
		comboBox_4.setBounds(210, 75, 128, 22);
		panel_recipeplanning.add(comboBox_4);
		comboBox_4.addItem("Select");
		for(Recipes r: db.getRecipes(UserActivity.currentUser) ) {
			comboBox_4.addItem(r.getName());
		}
		
		JComboBox<String> comboBox_4_1 = new JComboBox<String>();
		comboBox_4_1.setBounds(210, 112, 128, 22);
		panel_recipeplanning.add(comboBox_4_1);
		comboBox_4_1.addItem("Select");
		for(Recipes r: db.getRecipes(UserActivity.currentUser) ) {
			comboBox_4_1.addItem(r.getName());
		}
		JComboBox<String> comboBox_4_2 = new JComboBox<String>();
		comboBox_4_2.setBounds(210, 142, 128, 22);
		panel_recipeplanning.add(comboBox_4_2);
		comboBox_4_2.addItem("Select");
		for(Recipes r: db.getRecipes(UserActivity.currentUser) ) {
			comboBox_4_2.addItem(r.getName());
		}
		
		JComboBox<String> comboBox_4_3 = new JComboBox<String>();
		comboBox_4_3.setBounds(210, 178, 128, 22);
		panel_recipeplanning.add(comboBox_4_3);
		comboBox_4_3.addItem("Select");
		for(Recipes r: db.getRecipes(UserActivity.currentUser) ) {
			comboBox_4_3.addItem(r.getName());
		}		
		JComboBox<String> comboBox_4_4 = new JComboBox<String>();
		comboBox_4_4.setBounds(210, 215, 128, 22);
		panel_recipeplanning.add(comboBox_4_4);
		comboBox_4_4.addItem("Select");
		for(Recipes r: db.getRecipes(UserActivity.currentUser) ) {
			comboBox_4_4.addItem(r.getName());
		}
		
		
		//create a new panel for managing weekly meals
		JPanel panel_1_weeklyManager = new JPanel();
		panel_1_weeklyManager.setBorder(new LineBorder(new Color(0, 0, 0), 8));
		panel_1_weeklyManager.setBounds(576, 58, 342, 506);
		frame.getContentPane().add(panel_1_weeklyManager);
		panel_1_weeklyManager.setLayout(null);
		
		JButton btnAddSaved = new JButton("Select Recipe From Collection");
		btnAddSaved.setForeground(new Color(255, 255, 255));
        btnAddSaved.setBackground(new Color(59, 89, 182));
		btnAddSaved.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecipeCollection.page =0;
				RecipeCollection.recipeView =1;
				RecipeCollection collection = new RecipeCollection();
				collection.setVisible(true);
			}
		});
		btnAddSaved.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAddSaved.setBounds(58, 361, 220, 38);
		panel_1_weeklyManager.add(btnAddSaved);
		
		JLabel lblWeeklyManagerTitle = new JLabel("Weekly Meal Planner");
		lblWeeklyManagerTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblWeeklyManagerTitle.setBounds(58, 27, 234, 37);
		panel_1_weeklyManager.add(lblWeeklyManagerTitle);
		
		JLabel lblSelectDayofWeek = new JLabel("Select the Day & Meal of the Week Below:");
		lblSelectDayofWeek.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelectDayofWeek.setBounds(31, 95, 284, 37);
		panel_1_weeklyManager.add(lblSelectDayofWeek);
		
		JButton addButton = new JButton("Add");
		addButton.setForeground(new Color(255, 255, 255));
        addButton.setBackground(new Color(59, 89, 182));
		
		String WhichDayofWeek[] = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
		JComboBox<?> comboBox_3_dayofWeek = new JComboBox<Object>(WhichDayofWeek);
		comboBox_3_dayofWeek.setMaximumRowCount(20);
		comboBox_3_dayofWeek.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_3_dayofWeek.setBounds(105, 143, 134, 30);
		panel_1_weeklyManager.add(comboBox_3_dayofWeek);
		comboBox_3_dayofWeek.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					day = comboBox_3_dayofWeek.getSelectedItem().toString();
				}
			}
		});
		
		
		textArea.setBounds(58, 255, 220, 30);
		panel_1_weeklyManager.add(textArea);
		
		JLabel lblNewLabel_2 = new JLabel("Enter Name of Recipe");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(31, 233, 249, 24);
		panel_1_weeklyManager.add(lblNewLabel_2);
		
		
		// created a panel that allows the user to select a certain date from the calendar 
		// to modify recipes for in the future
		JPanel panel_3_calendar = new JPanel();
		panel_3_calendar.setBorder(new LineBorder(new Color(0, 0, 0), 8));
		panel_3_calendar.setBounds(922, 58, 334, 507);
		frame.getContentPane().add(panel_3_calendar);
		panel_3_calendar.setLayout(null);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 83, 285, 400);
		panel_3_calendar.add(scrollPane);
		
		scrollPane.setViewportView(list);
		showPlan();
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.get(day).addToPlanner(day, time,textArea.getText());
				db.editPlanner(UserActivity.currentUser,day, time,textArea.getText());
				showPlan();
			}
		});
		addButton.setBounds(105, 410, 134, 23);
		panel_1_weeklyManager.add(addButton);
		
		JLabel lblNewLabel_3_1 = new JLabel("Or");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_1.setBounds(31, 296, 46, 14);
		panel_1_weeklyManager.add(lblNewLabel_3_1);
		
		JComboBox<String> comboBox_3 = new JComboBox<String>();
		comboBox_3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					time = comboBox_3.getSelectedItem().toString();
				}
			}
		});
		comboBox_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_3.setBounds(105, 188, 134, 34);
		panel_1_weeklyManager.add(comboBox_3);
		
		JButton randomButton = new JButton("Get Recommended Recipe");
		randomButton.setForeground(new Color(255, 255, 255));
        randomButton.setBackground(new Color(59, 89, 182));
		randomButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		randomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("");
				ArrayList<Recipes> recipes = new ArrayList<Recipes>();
				boolean add = true;
				User currentUser = UserActivity.currentUser;
				for(Recipes recipe: db.getRecipes(UserActivity.currentUser)) {
					if(recipe.mealTime.contains(time)) {
						if (currentUser.getUserAllergies().getAllergies().get("Eggs") !=null && currentUser.getUserAllergies().getAllergies().get("Eggs")==1 && (recipe.getIngredients().toLowerCase().contains("egg"))){
							add =false;
						}
						if (currentUser.getUserAllergies().getAllergies().get("Milk")!=null && currentUser.getUserAllergies().getAllergies().get("Milk")==1 && (recipe.getIngredients().toLowerCase().contains("milk"))){
							add =false;
						}
						if (currentUser.getUserAllergies().getAllergies().get("Peanuts") !=null && currentUser.getUserAllergies().getAllergies().get("Peanuts")==1 && (recipe.getIngredients().contains("nut"))){
							add =false;
						}
						if (currentUser.getUserAllergies().getAllergies().get("Seafood") !=null && currentUser.getUserAllergies().getAllergies().get("Seafood")==1 && (recipe.getIngredients().contains("fish")|| recipe.getIngredients().contains("shellfish"))){
							add =false;
						}
						if(add == true) {recipes.add(recipe);}
						add=true;
					}
				}	
				Random rand = new Random();
				if(recipes.size() >0) {
					int n = rand.nextInt(recipes.size());
					textArea.setText(recipes.get(n).getName());
				}
			}
			
		});
		randomButton.setBounds(58, 309, 220, 41);
		panel_1_weeklyManager.add(randomButton);
		
		JLabel lblNewLabel_5 = new JLabel("Add Recipes to Planner");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(81, 70, 197, 14);
		panel_1_weeklyManager.add(lblNewLabel_5);
		
		JButton removeButton = new JButton("Remove From Planner");
		removeButton.setForeground(new Color(255, 255, 255));
        removeButton.setBackground(new Color(59, 89, 182));
		removeButton.setBounds(74, 444, 197, 30);
		panel_1_weeklyManager.add(removeButton);
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.get(day).removeFromPlanner(day,time,textArea.getText());
				db.editPlanner(UserActivity.currentUser,day, time,textArea.getText());
				showPlan();
			}
		});
		comboBox_3.addItem("Breakfast");
		comboBox_3.addItem("Lunch");
		comboBox_3.addItem("Dinner");
		
		// created another panel for provides more recipe information to the user
		JPanel panel_2_recipeinfo = new JPanel();
		panel_2_recipeinfo.setBorder(new LineBorder(new Color(0, 0, 0), 8));
		panel_2_recipeinfo.setBounds(10, 333, 562, 231);
		frame.getContentPane().add(panel_2_recipeinfo);
		panel_2_recipeinfo.setLayout(null);
		
		JButton calculateRecipes = new JButton("Calculate");
		calculateRecipes.setForeground(new Color(255, 255, 255));
        calculateRecipes.setBackground(new Color(59, 89, 182));
		calculateRecipes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int carbs=0;
				int protein=0;
				ArrayList<String> selectedRecipes = new ArrayList<String>();
				ArrayList<String> quantities = new ArrayList<String>();
				selectedRecipes.add(comboBox_4.getSelectedItem().toString());
				selectedRecipes.add(comboBox_4_1.getSelectedItem().toString());
				selectedRecipes.add(comboBox_4_2.getSelectedItem().toString());
				selectedRecipes.add(comboBox_4_3.getSelectedItem().toString());
				selectedRecipes.add(comboBox_4_4.getSelectedItem().toString());
				quantities.add(comboBox.getSelectedItem().toString());
				quantities.add(comboBox_1.getSelectedItem().toString());
				quantities.add(comboBox_1_2.getSelectedItem().toString());
				quantities.add(comboBox_1_3.getSelectedItem().toString());
				quantities.add(comboBox_2.getSelectedItem().toString());
				for(int i=0;i<selectedRecipes.size();i++) {
					if(selectedRecipes.get(i) != "Select") {
						carbs += db.getRecipe(UserActivity.currentUser,selectedRecipes.get(i)).getCarbs() * Integer.parseInt(quantities.get(i));
						protein += db.getRecipe(UserActivity.currentUser,selectedRecipes.get(i)).getProtein() * Integer.parseInt(quantities.get(i));
					}
				}
				textField.setText(Integer.toString(carbs));
				textField_1.setText(Integer.toString(protein));
			}
		});
		calculateRecipes.setBounds(139, 158, 258, 47);
		calculateRecipes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_2_recipeinfo.add(calculateRecipes);
		
		JLabel lblNewLabel_1_4 = new JLabel("Total Carbs (g):");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_4.setBounds(20, 42, 379, 22);
		panel_2_recipeinfo.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Estimated Nutritional Value");
		lblNewLabel_1_4_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_4_1.setBounds(20, 11, 377, 22);
		panel_2_recipeinfo.add(lblNewLabel_1_4_1);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(395, 40, 145, 36);
		panel_2_recipeinfo.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(395, 90, 145, 36);
		panel_2_recipeinfo.add(textField_1);
		
		JLabel lblNewLabel_4 = new JLabel("Total Protein (g):");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(20, 93, 175, 25);
		panel_2_recipeinfo.add(lblNewLabel_4);
		
		JLabel lblWeeklyManagerTitle_1 = new JLabel("Weekly Meal Planner");
		lblWeeklyManagerTitle_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblWeeklyManagerTitle_1.setBounds(57, 29, 234, 37);
		panel_3_calendar.add(lblWeeklyManagerTitle_1);
		
		
		
		
		
		// the last panel has all the necessary buttons for the user to navigate through the app
		JPanel panel_4_usefulbuttons = new JPanel();
		panel_4_usefulbuttons.setBorder(new LineBorder(new Color(0, 0, 0), 8));
		panel_4_usefulbuttons.setBounds(10, 568, 1246, 68);
		frame.getContentPane().add(panel_4_usefulbuttons);
		panel_4_usefulbuttons.setLayout(null);
		
		JButton btnHomepage = new JButton("<-- Back to HomePage");
		btnHomepage.setForeground(new Color(255, 255, 255));
        btnHomepage.setBackground(new Color(59, 89, 182));
		btnHomepage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomePage homePage = new HomePage();
				homePage.setVisible(true);
				frame.setVisible(false);
				Window win = SwingUtilities.getWindowAncestor(frame.getContentPane());
				win.dispose();
			}	
			
		});
		
		btnHomepage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnHomepage.setBounds(980, 26, 234, 31);
		panel_4_usefulbuttons.add(btnHomepage);
		
		// title of the page
		JLabel lblNewLabel = new JLabel("RIMA's Meal Planner");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel.setBounds(10, 11, 1246, 43);
		frame.getContentPane().add(lblNewLabel);
	}
	public static void setRecipe(String r) {
		textArea.setText(r);
		
	}
	public void showPlan() {
		model.clear();
		String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
		for(String d:days) {
			model.addElement(d);
			model.addElement("        "+"Breakfast:");
			for(String b:p.get(d).breakfast) {
				model.addElement("             "+b);

			}
			model.addElement("        "+"Lunch:");
			for(String b:p.get(d).lunch) {
				model.addElement("             "+b);

			}
			model.addElement("        "+"Dinner:");
			for(String b:p.get(d).dinner) {
				model.addElement("             "+b);

			}
		}
		list.setModel(model);
	}
}
