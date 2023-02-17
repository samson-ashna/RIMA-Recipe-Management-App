package presentation;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import objects.Recipes;
import javax.swing.JEditorPane;

@SuppressWarnings("serial")
public class EditRecipeView extends JDialog {
	//panel object
	private JPanel contentPane= new JPanel();
	public void NewScreen(Recipes r) {
		try {
			EditRecipeView editView = new EditRecipeView(r);
			editView.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			editView.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the frame.
	 */
	public EditRecipeView(Recipes recipe) {
		setTitle("RIMA - Edit Recipe");
		//set the application to exit when closed.
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		//Set the size and pop up location of the window.
		setSize(675, 762);
		setLocationRelativeTo(null);
		//Get content pane.
		contentPane = new JPanel();
		//Make an invisible border for the content pane.
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Create label for recipe name 
		JLabel nameRecipe = new JLabel("Recipe Name");
		nameRecipe.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nameRecipe.setBounds(112, 58, 157, 59);
		contentPane.add(nameRecipe);
		
		//Create label for ingredient 
		JLabel lblIngredients = new JLabel("Ingredients");
		lblIngredients.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIngredients.setBounds(112, 249, 157, 59);
		contentPane.add(lblIngredients);
		
		//Create label for recipe instruction
		JLabel lblInstruction = new JLabel("Instruction");
		lblInstruction.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblInstruction.setBounds(112, 409, 157, 59);
		contentPane.add(lblInstruction);
		
		//Create label for protein information.
		JLabel proteinInfo = new JLabel("Protein (g)");
		proteinInfo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		proteinInfo.setBounds(112, 124, 157, 59);
		contentPane.add(proteinInfo);
		
		//Create label for carbohydrates information
		JLabel carbsInfo = new JLabel("Carbs(g)");
		carbsInfo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		carbsInfo.setBounds(112, 196, 157, 59);
		contentPane.add(carbsInfo);
		
		//Create "save" button.
		JButton save = new JButton("Save");
		save.setFont(new Font("Tahoma", Font.PLAIN, 20));
		save.setBounds(226, 628, 207, 59);
		contentPane.add(save);
		
		//Creates labels for showing error to user when they enter strings as values of protein and carbs attribute.
		JLabel error2 = new JLabel("");
		error2.setForeground(new Color(255, 0, 0));
		error2.setBounds(120, 241, 301, 14);
		contentPane.add(error2);
		
		JLabel error1 = new JLabel("");
		error1.setForeground(new Color(255, 0, 0));
		error1.setBounds(120, 169, 185, 14);
		contentPane.add(error1);
		
		//Creates a back button. Returns to the user recipe collection page.
		JButton backButton = new JButton("Back");
		backButton.setBounds(542, 11, 89, 23);
		contentPane.add(backButton);
		
		//Creates editor panes where user can enter recipe info;name, protein, carbs, ingredient and instructions.
		JEditorPane editorPaneName = new JEditorPane();
		editorPaneName.setBounds(283, 77, 248, 30);
		contentPane.add(editorPaneName);
		editorPaneName.setText(recipe.getName());
		
		JEditorPane editorPaneProtein = new JEditorPane();
		editorPaneProtein.setBounds(283, 141, 248, 30);
		contentPane.add(editorPaneProtein);
		editorPaneProtein.setText(Integer.toString(recipe.getProtein()));
		
		JEditorPane editorPaneCarbs = new JEditorPane();
		editorPaneCarbs.setBounds(283, 214, 248, 30);
		contentPane.add(editorPaneCarbs);
		editorPaneCarbs.setText(Integer.toString(recipe.getCarbs()));
		
		JEditorPane editorIngredient = new JEditorPane();
		editorIngredient.setBounds(283, 266, 248, 140);
		contentPane.add(editorIngredient);
		editorIngredient.setText(recipe.getIngredients());
		
		JEditorPane editorInstruction = new JEditorPane();
		editorInstruction.setBounds(283, 430, 248, 162);
		contentPane.add(editorInstruction);
		editorInstruction.setText(recipe.getInstructions());
		//label to let the user know to separate ingredient items by new line
		JLabel lblNewLabel = new JLabel("Separate by new line");
		lblNewLabel.setBounds(112, 294, 126, 14);
		contentPane.add(lblNewLabel);
		
		//Sets up what happens when back button is pressed. 
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserRecipeCollection collection = new UserRecipeCollection();
				collection.setVisible(true);
				contentPane.setVisible(false);
				Window win = SwingUtilities.getWindowAncestor(contentPane);
				win.dispose();
			}
		});
		
		//Sets up what happens when save button is pressed.
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//incorrectValues is set to true if user enters strings as values for protein and carbs fields.
				boolean incorrectValues = false;
				//read recipe name
				String name = editorPaneName.getText();
				//read values entered for protein and carbs fields
				int protein=0;
				try
				{
				    protein = Integer.parseInt(editorPaneProtein.getText());
				}
				catch (NumberFormatException e1)
				{
					incorrectValues = true;
					error1.setText("Must be an integer!");
				}
				int carbs=0;
				try
				{
				    carbs = Integer.parseInt(editorPaneCarbs.getText());
				    error2.setVisible(false);
				}
				catch (NumberFormatException e21)
				{
					incorrectValues = true;
					error2.setText("Must be an integer!");
					
				}
				/*if user has not entered strings for protein and carbs, ingredient list 
				and instruction is read and recipe is modified.
				*/
				if(!incorrectValues) {
					ArrayList<String> ingredients = new ArrayList<String>();
					ingredients=new ArrayList<> (Arrays.asList(editorIngredient.getText().split("\n")));
					String instructions = editorInstruction.getText();
					
					recipe.setName(name);
					recipe.setProtein(protein);
					recipe.setCarbs(carbs);
					recipe.setIngredients(ingredients);
					recipe.setInstructions(instructions);
										
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

