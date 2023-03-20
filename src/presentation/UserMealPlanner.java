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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;

import businessLogic.UserActivity;
import objects.Recipes;
import persistence.DatabaseAccess;
import persistence.UsersDAO;

import com.toedter.calendar.JCalendar;
import javax.swing.JTextArea;

public class UserMealPlanner {

	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	String day="Sunday";
	String date;
	String time,time2;
	static JTextArea textArea = new JTextArea();

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
		panel_recipeplanning.setBounds(10, 57, 562, 269);
		frame.getContentPane().add(panel_recipeplanning);
		panel_recipeplanning.setLayout(null);
		
		// created labels and buttons for the first panel that selects recipes listed out
		JLabel lblNewLabel_1 = new JLabel("Placeholder:Recipe #1");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(23, 72, 196, 22);
		panel_recipeplanning.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Add Recipes to Start Planning");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(23, 24, 318, 37);
		panel_recipeplanning.add(lblNewLabel_1_1);
		
		JButton btnNewButton = new JButton("Select");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setBounds(210, 68, 131, 30);
		panel_recipeplanning.add(btnNewButton);
		
		// created a drop menu which selects the quantity of servings being made for the recipe
		String quantity[] = {"1","2","3","4","5"};
		JComboBox<?> comboBox = new JComboBox<Object>(quantity);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox.setEditable(true);
		comboBox.setMaximumRowCount(20);
		comboBox.setBounds(414, 68, 82, 30);
		panel_recipeplanning.add(comboBox);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Servings Quantity");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel_1_1_1.setBounds(366, 24, 175, 37);
		panel_recipeplanning.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Placeholder:Recipe #2");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(23, 109, 196, 22);
		panel_recipeplanning.add(lblNewLabel_1_2);
		
		JButton btnNewButton_1 = new JButton("Select");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1.setBounds(277, 197, 131, 30);
		panel_recipeplanning.add(btnNewButton_1);
		
		String quantity2[] = {"1","2","3","4","5"};
		JComboBox<?> comboBox_1 = new JComboBox<Object>(quantity2);
		comboBox_1.setMaximumRowCount(20);
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_1.setEditable(true);
		comboBox_1.setBounds(414, 145, 82, 30);
		panel_recipeplanning.add(comboBox_1);
		
		JLabel lblNewLabel_1_3 = new JLabel("Placeholder:Recipe #3");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_3.setBounds(23, 145, 196, 22);
		panel_recipeplanning.add(lblNewLabel_1_3);
		
		JButton btnNewButton_2 = new JButton("Select");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1.setBounds(210, 105, 131, 30);
		panel_recipeplanning.add(btnNewButton_2);
		
		String quantity3[] = {"1","2","3","4","5"};
		JComboBox<?> comboBox_2 = new JComboBox<Object>(quantity3);
		comboBox_2.setMaximumRowCount(20);
		comboBox_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_2.setEditable(true);
		comboBox_2.setBounds(414, 105, 82, 30);
		panel_recipeplanning.add(comboBox_2);
		
		JButton btnNewButton_3 = new JButton("Select");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_3.setBounds(210, 142, 131, 30);
		panel_recipeplanning.add(btnNewButton_3);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Placeholder:Recipe #4");
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_3_1.setBounds(23, 182, 196, 22);
		panel_recipeplanning.add(lblNewLabel_1_3_1);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("Placeholder:Recipe #5");
		lblNewLabel_1_3_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_3_2.setBounds(23, 217, 196, 22);
		panel_recipeplanning.add(lblNewLabel_1_3_2);
		
		JButton btnNewButton_3_1 = new JButton("Select");
		btnNewButton_3_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_3_1.setBounds(210, 180, 131, 30);
		panel_recipeplanning.add(btnNewButton_3_1);
		
		JButton btnNewButton_3_2 = new JButton("Select");
		btnNewButton_3_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_3_2.setBounds(210, 217, 131, 30);
		panel_recipeplanning.add(btnNewButton_3_2);
		
		String quantity4[] = {"1","2","3","4","5"};
		JComboBox<?> comboBox_1_1 = new JComboBox<Object>(quantity4);
		comboBox_1_1.setMaximumRowCount(20);
		comboBox_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_1_1.setEditable(true);
		comboBox_1_1.setBounds(414, 180, 82, 30);
		panel_recipeplanning.add(comboBox_1_1);
		
		String quantity5[] = {"1","2","3","4","5"};
		JComboBox<?> comboBox_1_2 = new JComboBox<Object>(quantity5);
		comboBox_1_2.setMaximumRowCount(20);
		comboBox_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_1_2.setEditable(true);
		comboBox_1_2.setBounds(414, 217, 82, 30);
		panel_recipeplanning.add(comboBox_1_2);
		
		//create a new panel for managing weekly meals
		JPanel panel_1_weeklyManager = new JPanel();
		panel_1_weeklyManager.setBorder(new LineBorder(new Color(0, 0, 0), 8));
		panel_1_weeklyManager.setBounds(576, 96, 342, 445);
		frame.getContentPane().add(panel_1_weeklyManager);
		panel_1_weeklyManager.setLayout(null);
		
		JButton btnAddSaved = new JButton("Select Recipe From Collection");
		btnAddSaved.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserRecipeCollection collection = new UserRecipeCollection();
				collection.page =1;
				collection.setVisible(true);
//				frame.setVisible(false);
//				Window win = SwingUtilities.getWindowAncestor(frame.getContentPane());
//				win.dispose();
			}
		});
		btnAddSaved.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAddSaved.setBounds(41, 323, 249, 30);
		panel_1_weeklyManager.add(btnAddSaved);
		
		JLabel lblWeeklyManagerTitle = new JLabel("Weekly Manager");
		lblWeeklyManagerTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblWeeklyManagerTitle.setBounds(83, 27, 175, 37);
		panel_1_weeklyManager.add(lblWeeklyManagerTitle);
		
		JLabel lblSelectDayofWeek = new JLabel("Select the Day & TIme of the Week Below:");
		lblSelectDayofWeek.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelectDayofWeek.setBounds(31, 75, 284, 37);
		panel_1_weeklyManager.add(lblSelectDayofWeek);
		
		JButton addButton = new JButton("Add");
		
		String WhichDayofWeek[] = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
		JComboBox<?> comboBox_3_dayofWeek = new JComboBox<Object>(WhichDayofWeek);
		comboBox_3_dayofWeek.setMaximumRowCount(20);
		comboBox_3_dayofWeek.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_3_dayofWeek.setBounds(105, 123, 134, 30);
		panel_1_weeklyManager.add(comboBox_3_dayofWeek);
		comboBox_3_dayofWeek.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					day = comboBox_3_dayofWeek.getSelectedItem().toString();
				}
			}
		});
		
		
		textArea.setBounds(69, 244, 209, 30);
		panel_1_weeklyManager.add(textArea);
		
		JLabel lblNewLabel_2 = new JLabel("Enter Name of Recipe");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(31, 209, 249, 24);
		panel_1_weeklyManager.add(lblNewLabel_2);
		
		
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatabaseAccess access = new DatabaseAccess();
				UsersDAO db = access.usersDB();
				db.editPlanner(UserActivity.currentUser,day, time,textArea.getText());
			}
		});
		addButton.setBounds(105, 376, 134, 23);
		panel_1_weeklyManager.add(addButton);
		
		JLabel lblNewLabel_3_1 = new JLabel("Or");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3_1.setBounds(31, 285, 46, 14);
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
		comboBox_3.setBounds(105, 164, 134, 34);
		panel_1_weeklyManager.add(comboBox_3);
		comboBox_3.addItem("Breakfast");
		comboBox_3.addItem("Lunch");
		comboBox_3.addItem("Dinner");
		
		// created another panel for provides more recipe information to the user
		JPanel panel_2_recipeinfo = new JPanel();
		panel_2_recipeinfo.setBorder(new LineBorder(new Color(0, 0, 0), 8));
		panel_2_recipeinfo.setBounds(10, 333, 562, 231);
		frame.getContentPane().add(panel_2_recipeinfo);
		panel_2_recipeinfo.setLayout(null);
		
		JButton btnSearchForRecipes = new JButton("Search For More Recipes");
		btnSearchForRecipes.setBounds(139, 158, 258, 47);
		btnSearchForRecipes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_2_recipeinfo.add(btnSearchForRecipes);
		
		JLabel lblNewLabel_1_4 = new JLabel("Estimated Total Cost of Required Ingredients");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_4.setBounds(20, 42, 379, 22);
		panel_2_recipeinfo.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Estimated Nutritional Value");
		lblNewLabel_1_4_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_4_1.setBounds(20, 93, 377, 22);
		panel_2_recipeinfo.add(lblNewLabel_1_4_1);
		
		textField = new JTextField();
		textField.setBounds(395, 40, 145, 36);
		panel_2_recipeinfo.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(395, 90, 145, 36);
		panel_2_recipeinfo.add(textField_1);
		
		// created a panel that allows the user to select a certain date from the calendar 
		// to modify recipes for in the future
		JPanel panel_3_calendar = new JPanel();
		panel_3_calendar.setBorder(new LineBorder(new Color(0, 0, 0), 8));
		panel_3_calendar.setBounds(922, 58, 334, 507);
		frame.getContentPane().add(panel_3_calendar);
		panel_3_calendar.setLayout(null);
		
		JLabel lblCalendarTitle = new JLabel("Calendar");
		lblCalendarTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblCalendarTitle.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblCalendarTitle.setBounds(82, 30, 175, 37);
		panel_3_calendar.add(lblCalendarTitle);
		
		// More Swing custom components downloaded:
		// http://www.java2s.com/Code/Jar/j/Downloadjcalendar14jar.htm
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				date = dateChooser.getDate().toString();
//				System.out.println(date);
			}
		});
		dateChooser.setBounds(92, 124, 146, 31);
		panel_3_calendar.add(dateChooser);
		
		
		JLabel lblChooseTheDate = new JLabel("Choose the Date...");
		lblChooseTheDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblChooseTheDate.setBounds(40, 91, 155, 22);
		panel_3_calendar.add(lblChooseTheDate);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(40, 289, 251, 37);
		panel_3_calendar.add(textArea_1);
		
		JButton btnEditRecipeOn = new JButton("Add");
		btnEditRecipeOn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				date = dateChooser.getDate().toString();
				UserActivity.currentUser.addToPlanner(date,time2,textArea_1.getText());
				
			}
		});
		btnEditRecipeOn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnEditRecipeOn.setBounds(93, 421, 133, 30);
		panel_3_calendar.add(btnEditRecipeOn);
		
		JLabel lblOrSelectHere = new JLabel("Enter Recipe Name");
		lblOrSelectHere.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblOrSelectHere.setBounds(40, 256, 251, 22);
		panel_3_calendar.add(lblOrSelectHere);
		
		
		
		JButton btnNewButton_4 = new JButton("Select Recipe From Collection");
		btnNewButton_4.setBounds(66, 379, 205, 23);
		panel_3_calendar.add(btnNewButton_4);
		
		JLabel lblNewLabel_3 = new JLabel("Or");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(35, 337, 46, 14);
		panel_3_calendar.add(lblNewLabel_3);
		
		JComboBox<String> comboBox_3_1 = new JComboBox<String>();
		comboBox_3_1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					time2 = comboBox_3_1.getSelectedItem().toString();
				}
			}
		});
		comboBox_3_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_3_1.setBounds(92, 200, 134, 34);
		panel_3_calendar.add(comboBox_3_1);
		comboBox_3_1.addItem("Breakfast");
		comboBox_3_1.addItem("Lunch");
		comboBox_3_1.addItem("Dinner");

		
		JLabel lblChooseTheMeal = new JLabel("Choose the Meal");
		lblChooseTheMeal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblChooseTheMeal.setBounds(40, 166, 155, 22);
		panel_3_calendar.add(lblChooseTheMeal);
		
		// the last panel has all the necessary buttons for the user to navigate through the app
		JPanel panel_4_usefulbuttons = new JPanel();
		panel_4_usefulbuttons.setBorder(new LineBorder(new Color(0, 0, 0), 8));
		panel_4_usefulbuttons.setBounds(10, 568, 1246, 68);
		frame.getContentPane().add(panel_4_usefulbuttons);
		panel_4_usefulbuttons.setLayout(null);
		
		JButton btnHomepage = new JButton("<-- Back to HomePage");
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
		btnHomepage.setBounds(184, 20, 234, 31);
		panel_4_usefulbuttons.add(btnHomepage);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox_3_dayofWeek.setSelectedItem("Choose the day...");
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnReset.setBounds(500, 20, 234, 31);
		panel_4_usefulbuttons.add(btnReset);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExit.setBounds(814, 20, 234, 31);
		panel_4_usefulbuttons.add(btnExit);
		
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
}
