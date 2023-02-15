package presentation;

import java.awt.*;
import javax.swing.*;
import javax.swing.*;
import javax.swing.border.*;
import domain.*;
import objects.*;
import persistence.*;
import java.awt.event.*;
import java.util.*;

@SuppressWarnings("serial")
public class SaveRecipesView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaveRecipesView frame = new SaveRecipesView();
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
	public SaveRecipesView() {
		setTitle("RIMA - Save Recipe");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(675, 762);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(327, 71, 216, 41);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		
		JLabel lblNewLabel = new JLabel("Recipe Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(112, 58, 157, 59);
		contentPane.add(lblNewLabel);
		
		JLabel lblIngredients = new JLabel("Ingredients");
		lblIngredients.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIngredients.setBounds(112, 249, 157, 59);
		contentPane.add(lblIngredients);
		
		
		JLabel lblInstruction = new JLabel("Instruction");
		lblInstruction.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblInstruction.setBounds(112, 409, 157, 59);
		contentPane.add(lblInstruction);
		
		JLabel lblProteing = new JLabel("Protein (g)");
		lblProteing.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblProteing.setBounds(112, 124, 157, 59);
		contentPane.add(lblProteing);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(327, 139, 216, 36);
		contentPane.add(textField_3);
		
		
		JLabel lblCarbscal = new JLabel("Carbs(g)");
		lblCarbscal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCarbscal.setBounds(112, 196, 157, 59);
		contentPane.add(lblCarbscal);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(327, 207, 216, 36);
		contentPane.add(textField_4);
		
		
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(226, 628, 207, 59);
		contentPane.add(btnNewButton);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(327, 270, 216, 120);
		contentPane.add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setLineWrap(true);
		textArea_1.setBounds(327, 430, 221, 171);
		contentPane.add(textArea_1);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1.setBounds(120, 241, 301, 14);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setVisible(false);
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setForeground(new Color(255, 0, 0));
		lblNewLabel_2.setBounds(120, 169, 185, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.setBounds(542, 11, 89, 23);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserRecipeCollection collection = new UserRecipeCollection();
				collection.setVisible(true);
				contentPane.setVisible(false);
				Window win = SwingUtilities.getWindowAncestor(contentPane);
				win.dispose();
			}
		});
		
		
		lblNewLabel_2.setVisible(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean incorrectValues = false;
				String name = textField.getText();
				int protein=0;
				try
				{
				    protein = Integer.parseInt(textField_3.getText());
				    lblNewLabel_2.setVisible(false);
				}
				catch (NumberFormatException e1)
				{
					incorrectValues = true;
					lblNewLabel_2.setText("Must be an integer!");
					lblNewLabel_2.setVisible(true);
				}
				int carbs=0;
				try
				{
				    carbs = Integer.parseInt(textField_4.getText());
				    lblNewLabel_1.setVisible(false);
				}
				catch (NumberFormatException e21)
				{
					incorrectValues = true;
					lblNewLabel_1.setText("Must be an integer!");
					lblNewLabel_1.setVisible(true);
					
				}
				if(!incorrectValues) {
					ArrayList<String> ingredients = new ArrayList<String>();
					ingredients=new ArrayList<> (Arrays.asList(textArea.getText().split("\n")));
					String instructions = textArea_1.getText();
					Recipes newRecipe;
					newRecipe = new Recipes(name, protein, carbs);
					newRecipe.setIngredients(ingredients);
					newRecipe.setInstructions(instructions);
					SaveRecipe saveRecipe = new SaveRecipe(UserActivity.getCurrentUser());
					saveRecipe.save(newRecipe);
					
					UserRecipeCollection back = new UserRecipeCollection();
					back.setVisible(true);
					contentPane.setVisible(false);
					Window win = SwingUtilities.getWindowAncestor(contentPane);
					win.dispose();
				}
				
			}
		});
	}
}
