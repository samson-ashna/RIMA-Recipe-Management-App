package objects;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JCalendar;

public class UserMealPlanner {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

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
		
		JPanel panel_recipeplanning = new JPanel();
		panel_recipeplanning.setBorder(new LineBorder(new Color(0, 0, 0), 8));
		panel_recipeplanning.setBounds(10, 57, 562, 269);
		frame.getContentPane().add(panel_recipeplanning);
		panel_recipeplanning.setLayout(null);
		
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
		
		String quantity[] = {"1","2","3","4","5"};
		JComboBox comboBox = new JComboBox(quantity);
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
		JComboBox comboBox_1 = new JComboBox(quantity2);
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
		JComboBox comboBox_2 = new JComboBox(quantity3);
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
		JComboBox comboBox_1_1 = new JComboBox(quantity4);
		comboBox_1_1.setMaximumRowCount(20);
		comboBox_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_1_1.setEditable(true);
		comboBox_1_1.setBounds(414, 180, 82, 30);
		panel_recipeplanning.add(comboBox_1_1);
		
		String quantity5[] = {"1","2","3","4","5"};
		JComboBox comboBox_1_2 = new JComboBox(quantity5);
		comboBox_1_2.setMaximumRowCount(20);
		comboBox_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_1_2.setEditable(true);
		comboBox_1_2.setBounds(414, 217, 82, 30);
		panel_recipeplanning.add(comboBox_1_2);
		
		
		JPanel panel_1_weeklyManager = new JPanel();
		panel_1_weeklyManager.setBorder(new LineBorder(new Color(0, 0, 0), 8));
		panel_1_weeklyManager.setBounds(576, 96, 342, 445);
		frame.getContentPane().add(panel_1_weeklyManager);
		panel_1_weeklyManager.setLayout(null);
		
		JButton btnAddSaved = new JButton("Add Saved Recipes");
		btnAddSaved.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddSaved.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAddSaved.setBounds(71, 306, 209, 30);
		panel_1_weeklyManager.add(btnAddSaved);
		
		JButton btnNewButton_1_1 = new JButton("Create New Recipe");
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1_1.setBounds(71, 345, 209, 30);
		panel_1_weeklyManager.add(btnNewButton_1_1);
		
		JButton btnNewButton_3_3 = new JButton("Save Recipe Link");
		btnNewButton_3_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_3_3.setBounds(71, 384, 209, 30);
		panel_1_weeklyManager.add(btnNewButton_3_3);
		
		JLabel lblWeeklyManagerTitle = new JLabel("Weekly Manager");
		lblWeeklyManagerTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblWeeklyManagerTitle.setBounds(83, 27, 175, 37);
		panel_1_weeklyManager.add(lblWeeklyManagerTitle);
		
		JLabel lblSelectDayofWeek = new JLabel("Select the Day of the Week Below:");
		lblSelectDayofWeek.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSelectDayofWeek.setBounds(31, 90, 284, 22);
		panel_1_weeklyManager.add(lblSelectDayofWeek);
		
		String WhichDayofWeek[] = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
		JComboBox comboBox_3_dayofWeek = new JComboBox(WhichDayofWeek);
		comboBox_3_dayofWeek.setMaximumRowCount(20);
		comboBox_3_dayofWeek.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_3_dayofWeek.setBounds(105, 136, 134, 30);
		panel_1_weeklyManager.add(comboBox_3_dayofWeek);
		
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
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		dateChooser.setBounds(92, 124, 146, 31);
		panel_3_calendar.add(dateChooser);
		
		
		JLabel lblChooseTheDate = new JLabel("Choose the Date...");
		lblChooseTheDate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblChooseTheDate.setBounds(40, 91, 155, 22);
		panel_3_calendar.add(lblChooseTheDate);
		
		JButton btnEditRecipeOn = new JButton("Edit Recipe on Selected Day");
		btnEditRecipeOn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEditRecipeOn.setBounds(40, 424, 251, 30);
		panel_3_calendar.add(btnEditRecipeOn);
		
		JLabel lblOrSelectHere = new JLabel("OR Select Here:");
		lblOrSelectHere.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblOrSelectHere.setBounds(40, 181, 155, 22);
		panel_3_calendar.add(lblOrSelectHere);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(40, 222, 233, 153);
		panel_3_calendar.add(calendar);
		
		JPanel panel_4_usefulbuttons = new JPanel();
		panel_4_usefulbuttons.setBorder(new LineBorder(new Color(0, 0, 0), 8));
		panel_4_usefulbuttons.setBounds(10, 568, 1246, 68);
		frame.getContentPane().add(panel_4_usefulbuttons);
		panel_4_usefulbuttons.setLayout(null);
		
		JButton btnHomepage = new JButton("<-- Back to HomePage");
		btnHomepage.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
//				HomePage homepage = new HomePage();
//				HomePage.main(null);
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
		
		JLabel lblNewLabel = new JLabel("RIMA's Meal Planner");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel.setBounds(10, 11, 1246, 43);
		frame.getContentPane().add(lblNewLabel);
	}
}
