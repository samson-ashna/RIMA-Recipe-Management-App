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
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;

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
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 8));
		panel.setBounds(10, 57, 562, 269);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Placeholder:Recipe #1");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(23, 72, 196, 22);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Add Recipes to Start Planning");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(23, 24, 318, 37);
		panel.add(lblNewLabel_1_1);
		
		JButton btnNewButton = new JButton("Select");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(210, 68, 131, 30);
		panel.add(btnNewButton);
		
		String quantity[] = {"1","2","3","4","5"};
		JComboBox comboBox = new JComboBox(quantity);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox.setEditable(true);
		comboBox.setMaximumRowCount(20);
		comboBox.setBounds(414, 68, 82, 30);
		panel.add(comboBox);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Servings Quantity");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel_1_1_1.setBounds(366, 24, 175, 37);
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Placeholder:Recipe #2");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_2.setBounds(23, 109, 196, 22);
		panel.add(lblNewLabel_1_2);
		
		JButton btnNewButton_1 = new JButton("Select");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1.setBounds(277, 197, 131, 30);
		panel.add(btnNewButton_1);
		
		String quantity2[] = {"1","2","3","4","5"};
		JComboBox comboBox_1 = new JComboBox(quantity2);
		comboBox_1.setMaximumRowCount(20);
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_1.setEditable(true);
		comboBox_1.setBounds(414, 145, 82, 30);
		panel.add(comboBox_1);
		
		JLabel lblNewLabel_1_3 = new JLabel("Placeholder:Recipe #3");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_3.setBounds(23, 149, 196, 22);
		panel.add(lblNewLabel_1_3);
		
		JButton btnNewButton_2 = new JButton("Select");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1.setBounds(210, 105, 131, 30);
		panel.add(btnNewButton_2);
		
		String quantity3[] = {"1","2","3","4","5"};
		JComboBox comboBox_2 = new JComboBox(quantity3);
		comboBox_2.setMaximumRowCount(20);
		comboBox_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_2.setEditable(true);
		comboBox_2.setBounds(414, 105, 82, 30);
		panel.add(comboBox_2);
		
		JButton btnNewButton_3 = new JButton("Select");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_3.setBounds(210, 142, 131, 30);
		panel.add(btnNewButton_3);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Placeholder:Recipe #4");
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_3_1.setBounds(23, 184, 196, 22);
		panel.add(lblNewLabel_1_3_1);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("Placeholder:Recipe #5");
		lblNewLabel_1_3_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_3_2.setBounds(23, 217, 196, 22);
		panel.add(lblNewLabel_1_3_2);
		
		JButton btnNewButton_3_1 = new JButton("Select");
		btnNewButton_3_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_3_1.setBounds(210, 180, 131, 30);
		panel.add(btnNewButton_3_1);
		
		JButton btnNewButton_3_2 = new JButton("Select");
		btnNewButton_3_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_3_2.setBounds(210, 217, 131, 30);
		panel.add(btnNewButton_3_2);
		
		String quantity4[] = {"1","2","3","4","5"};
		JComboBox comboBox_1_1 = new JComboBox(quantity4);
		comboBox_1_1.setMaximumRowCount(20);
		comboBox_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_1_1.setEditable(true);
		comboBox_1_1.setBounds(414, 180, 82, 30);
		panel.add(comboBox_1_1);
		
		String quantity5[] = {"1","2","3","4","5"};
		JComboBox comboBox_1_2 = new JComboBox(quantity5);
		comboBox_1_2.setMaximumRowCount(20);
		comboBox_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_1_2.setEditable(true);
		comboBox_1_2.setBounds(414, 217, 82, 30);
		panel.add(comboBox_1_2);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 8));
		panel_1.setBounds(576, 57, 398, 269);
		frame.getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 8));
		panel_2.setBounds(10, 333, 562, 231);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnSearchForRecipes = new JButton("Search For More Recipes");
		btnSearchForRecipes.setBounds(139, 158, 258, 47);
		btnSearchForRecipes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_2.add(btnSearchForRecipes);
		
		JLabel lblNewLabel_1_4 = new JLabel("Estimated Total Cost of Required Ingredients");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_4.setBounds(20, 42, 379, 22);
		panel_2.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Estimated Nutritional Value");
		lblNewLabel_1_4_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_4_1.setBounds(20, 93, 377, 22);
		panel_2.add(lblNewLabel_1_4_1);
		
		textField = new JTextField();
		textField.setBounds(395, 40, 145, 36);
		panel_2.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(395, 90, 145, 36);
		panel_2.add(textField_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 8));
		panel_3.setBounds(978, 58, 278, 507);
		frame.getContentPane().add(panel_3);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBorder(new LineBorder(new Color(0, 0, 0), 8));
		panel_2_1.setBounds(576, 333, 398, 231);
		frame.getContentPane().add(panel_2_1);
		
		JPanel panel_2_2 = new JPanel();
		panel_2_2.setBorder(new LineBorder(new Color(0, 0, 0), 8));
		panel_2_2.setBounds(10, 568, 1246, 68);
		frame.getContentPane().add(panel_2_2);
		
		JLabel lblNewLabel = new JLabel("RIMA's Meal Planner");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel.setBounds(10, 11, 1246, 43);
		frame.getContentPane().add(lblNewLabel);
	}
}