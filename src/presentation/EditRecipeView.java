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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(675, 762);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		
		JLabel lblCarbscal = new JLabel("Carbs(g)");
		lblCarbscal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCarbscal.setBounds(112, 196, 157, 59);
		contentPane.add(lblCarbscal);

		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(226, 628, 207, 59);
		contentPane.add(btnNewButton);
		
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
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(283, 77, 248, 30);
		contentPane.add(editorPane);
		editorPane.setText(recipe.getName());
		
		JEditorPane editorPane_1 = new JEditorPane();
		editorPane_1.setBounds(283, 141, 248, 30);
		contentPane.add(editorPane_1);
		editorPane_1.setText(Integer.toString(recipe.getProtein()));
		
		JEditorPane editorPane_2 = new JEditorPane();
		editorPane_2.setBounds(283, 214, 248, 30);
		contentPane.add(editorPane_2);
		editorPane_2.setText(Integer.toString(recipe.getCarbs()));
		
		JEditorPane editorPane_3 = new JEditorPane();
		editorPane_3.setBounds(283, 266, 248, 140);
		contentPane.add(editorPane_3);
		editorPane_3.setText(recipe.getIngredients());
		
		JEditorPane editorPane_4 = new JEditorPane();
		editorPane_4.setBounds(283, 430, 248, 162);
		contentPane.add(editorPane_4);
		editorPane_4.setText(recipe.getInstructions());
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
				String name = editorPane.getText();
				int protein=0;
				try
				{
				    protein = Integer.parseInt(editorPane_1.getText());
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
				    carbs = Integer.parseInt(editorPane_2.getText());
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
					ingredients=new ArrayList<> (Arrays.asList(editorPane_3.getText().split("\n")));
					String instructions = editorPane_4.getText();
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

