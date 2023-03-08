package presentation;

import java.util.ArrayList;
import java.util.Arrays;

import java.awt.Font;
import java.awt.Window;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import businessLogic.SaveRecipe;
import businessLogic.UserActivity;
import objects.Recipes;

/**
 * 
 */
@SuppressWarnings("serial")
public class FavouritesWindow extends JFrame {
	//Panel object
	private JPanel contentPane;
	//text field objects
	private JTextField userName;
	private JTextField proteinInfo;
	private JTextField carbsInfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FavouritesWindow frame = new FavouritesWindow();
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
	public FavouritesWindow() {
		setTitle("RIMA - View Favourites");
		//Set the application to exit when closed
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//Set the size and pop up location of the window.
		setSize(675, 762);
		setLocationRelativeTo(null);
		//creates a new content pane
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		//Creates labels for recipe name, ingredients, instruction, protein and carbs information
		JLabel lblName = new JLabel("Recipe Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(112, 58, 157, 59);
		contentPane.add(lblName);
		
		JLabel lblIngredients = new JLabel("Ingredients");
		lblIngredients.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIngredients.setBounds(112, 249, 157, 59);
		contentPane.add(lblIngredients);
		
		
		JLabel lblInstruction = new JLabel("Instruction");
		lblInstruction.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblInstruction.setBounds(112, 409, 157, 59);
		contentPane.add(lblInstruction);
		
		JLabel lblProtein = new JLabel("Protein (g)");
		lblProtein.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblProtein.setBounds(112, 124, 157, 59);
		contentPane.add(lblProtein);
		JLabel lblCarbs = new JLabel("Carbs(g)");
		lblCarbs.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCarbs.setBounds(112, 196, 157, 59);
		contentPane.add(lblCarbs);
		
		//Creates text fields where user can enter the new recipe's name, protein, carbs, ingredients, and instruction.
		userName = new JTextField();
		userName.setBounds(327, 71, 216, 41);
		contentPane.add(userName);
		userName.setColumns(10);
		
		proteinInfo = new JTextField();
		proteinInfo.setColumns(10);
		proteinInfo.setBounds(327, 139, 216, 36);
		contentPane.add(proteinInfo);
		
		carbsInfo = new JTextField();
		carbsInfo.setColumns(10);
		carbsInfo.setBounds(327, 207, 216, 36);
		contentPane.add(carbsInfo);
		
		JTextArea ingredientInfo = new JTextArea();
		ingredientInfo.setBounds(327, 270, 216, 120);
		contentPane.add(ingredientInfo);
		
		JTextArea instructionInfo = new JTextArea();
		instructionInfo.setLineWrap(true);
		instructionInfo.setBounds(327, 430, 221, 171);
		contentPane.add(instructionInfo);
			
		//Creates label for error messages for when user enters string instead of integers for protein and carbs fields.		
		JLabel lblError1 = new JLabel("");
		lblError1.setForeground(new Color(255, 0, 0));
		lblError1.setBounds(120, 241, 301, 14);
		contentPane.add(lblError1);
		
		JLabel lblError2 = new JLabel("");
		lblError2.setForeground(new Color(255, 0, 0));
		lblError2.setBounds(120, 169, 185, 14);
		contentPane.add(lblError2);
		
		//Creates a back button. When clicked, user is redirected to their recipe colleciton
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
		
		//Creates a save button.When clicked, a new recipe object is created and added to the user's personal collection of recipes.
		JButton save = new JButton("Save");
		save.setFont(new Font("Tahoma", Font.PLAIN, 20));
		save.setBounds(226, 628, 207, 59);
		contentPane.add(save);
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean incorrectValues = false;
				String name = userName.getText();
				int protein=0;
				try
				{
				    protein = Integer.parseInt(proteinInfo.getText());
				}
				catch (NumberFormatException e1)
				{
					incorrectValues = true;
					lblError2.setText("Must be an integer!");
				}
				int carbs=0;
				try
				{
				    carbs = Integer.parseInt(carbsInfo.getText());
				}
				catch (NumberFormatException e21)
				{
					incorrectValues = true;
					lblError1.setText("Must be an integer!");
					
				}
				/*if user has not entered strings for protein and carbs, ingredient list 
				and instruction is read and a new recipe is created and saved to the user's personal collection.
				*/
				if(!incorrectValues) {
		
					String ingredients=ingredientInfo.getText();
					String instructions = instructionInfo.getText();
					Recipes newRecipe = null;
					newRecipe = new Recipes(name, protein, carbs);
					newRecipe.setIngredients(ingredients);
					newRecipe.setInstructions(instructions);
					SaveRecipe saveRecipe = new SaveRecipe(UserActivity.getCurrentUser());
					saveRecipe.save(newRecipe);
					//User is then redirected back to their recipe collection page.
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
