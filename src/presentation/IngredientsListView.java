package presentation;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import businessLogic.IngredientActions;
import businessLogic.UserActivity;
import objects.Ingredient;
import objects.User;

@SuppressWarnings("serial")
public class IngredientsListView extends JFrame {

	//Object for the previous frame, A, that used the content pane from this ingredients list view frame, B, to save a copy of itself in B so that B can modify A (for back button and title purposes).
	private JFrame previousFrame;
	//A's original content pane.
	private Container previousPane;
	
	//Container and Panel objects.
	private Container contentPane;
	private JPanel listPane;
	private JPanel buttonPane;
	
	//Component objects.
	private final JButton backButton = new JButton("Back");
	private final JButton addButton = new JButton("Add Ingredient");
	private final JButton editButton = new JButton("Edit");
	private final JButton removeButton = new JButton("Remove");
	private final JButton[] buttons = {backButton, addButton, editButton, removeButton};
	private JList<String> ingredientsList = new JList<String>();
	
	//Current user.
	private User user;
	
	//Current user's ingredients.
	ArrayList<Ingredient> ingredients;
	
	//Selected ingredient.
	private Ingredient selectedIngredient = null;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Create a new frame.
					ViewProfile frame = new ViewProfile();
					//Make the frame visible.
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Adds the current user's info (user name and allergies) to the user info label.
	 */
	public void ingredientsListSetup() {
		String[] ingredientNames;
		
		if(ingredients != null) {
			//Initialize ingredientNames.
			ingredientNames = new String[ingredients.size()];
			
			//Add ingredient names to ingredientNames.
			for(int i = 0; i<ingredientNames.length; i++) {
				ingredientNames[i] = ingredients.get(i).getName();
			}
			
			//Build ingredientsList using ingredientNames.
			ingredientsList = new JList<String>(ingredientNames);
			//Set up List dimensions.
			ingredientsList.setPrototypeCellValue("Using this to set the ingredients list cell width for now ;)"); //Since other methods didn't work.
			ingredientsList.setPreferredSize(new Dimension(200, 300));
		}
	}
	
	
	/**
	 * Create the frame.
	 */
	public IngredientsListView() {
		//Retrieve current user and ingredients.
		user = UserActivity.getCurrentUser();
		if(user != null) ingredients = IngredientActions.getIngredients();
		
		//Set title.
		setTitle("RIMA - User Ingredients");
		//Set the application to exit when closed.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 		
		//Set the size and pop up location of the window.
		setSize(450, 450);	
		setLocationRelativeTo(null);
		
		//Get content pane.
		contentPane = getContentPane();		
		
		//Create a new info pane.
		listPane = new JPanel();
		//Set the info pane's layout manager to the vertical box layout.
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
		//Make an invisible border for the info pane.
		listPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		//Create a new pane for buttons.
		buttonPane = new JPanel();
		//Set an invisible border for the button pane.
		buttonPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//Set the button pane's layout manager to the horizontal box layout.
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
				
		//Add user ingredients to list.
		ingredientsListSetup();			
		
		//Align list.
		ingredientsList.setAlignmentX(CENTER_ALIGNMENT);	
		
		//Add labels to the info pane.
		listPane.add(Box.createVerticalGlue());
		listPane.add(ingredientsList);
		listPane.add(Box.createVerticalGlue());
		
		//Set up the button fonts.
		for(JButton button:buttons) {
			button.setFont(new Font("Tahoma", Font.PLAIN, 10));
		}
		
		//Disable edit and remove buttons due to unselected list item.
		editButton.setEnabled(false);
		removeButton.setEnabled(false);
		
		//Add buttons to button pane.
		buttonPane.add(Box.createHorizontalGlue());
		buttonPane.add(backButton);
		buttonPane.add(Box.createRigidArea(new Dimension(5, 0)));
		buttonPane.add(editButton);
		buttonPane.add(Box.createRigidArea(new Dimension(5, 0)));
		buttonPane.add(removeButton);
		buttonPane.add(Box.createRigidArea(new Dimension(5, 0)));
		buttonPane.add(addButton);
		
		//Add info and button panes to content pane.
		contentPane.add(listPane);
		contentPane.add(buttonPane, BorderLayout.PAGE_END);
		
		//Add components that need to be enabled and disabled during events to an array.
		JComponent[] componentsToToggle = {backButton, addButton, ingredientsList};
		
		//Set up what to do when the back button is pressed.
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Create a HomePage window
				HomePage homePage = new HomePage();
						
				//Make the HomePage window visible and the UserRecipeCollection window invisible.
				homePage.setVisible(true);
				contentPane.setVisible(false);
						
				//Close the UserRecipeCollection Window.
				Window win = SwingUtilities.getWindowAncestor(contentPane);
				win.dispose();	
			}
		});
		
		//Set up what to do when the add button is pressed.
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Set selected ingredient to null and deselect ingredient.
				selectedIngredient = null;
				ingredientsList.clearSelection();
				
				//Create an EditIngredientView window
				EditIngredientView editView = new EditIngredientView(componentsToToggle, selectedIngredient);
						
				//Make the HomePage window visible and the UserRecipeCollection window invisible.
				editView.setVisible(true);
				
				//Disable buttons and ingredients list.
				for(JButton button:buttons) {
					button.setEnabled(false);
				}
				ingredientsList.setEnabled(false);
								
			}
		});
		
		//Set up what to do when an item in the ingredient list is selected.
		ingredientsList.addListSelectionListener(new ListSelectionListener() {
		    public void valueChanged(ListSelectionEvent e) {
		        if (!e.getValueIsAdjusting()){
		            editButton.setEnabled(true);
		            removeButton.setEnabled(true);
		            String selectedValue = (String) ingredientsList.getSelectedValue();
		            if(ingredients != null) {
		            	for(Ingredient ingredient:ingredients) {
		            		if(ingredient.getName().equals(selectedValue)) {
		            			selectedIngredient = ingredient;
		            		}
		            	}
		            }
		        }
		    }
		});
		
		//Set up what to do when the edit button is pressed.
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Create an EditIngredientView window
				EditIngredientView editView = new EditIngredientView(componentsToToggle, selectedIngredient);
						
				//Make the HomePage window visible and the UserRecipeCollection window invisible.
				editView.setVisible(true);
				
				//Disable buttons and ingredients list.
				for(JButton button:buttons) {
					button.setEnabled(false);
				}
				ingredientsList.setEnabled(false);
								
			}
		});
		
		//Set up what to do when the remove button is pressed.
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Set selected ingredient to null;
				selectedIngredient = null;
				
				//Remove ingredient from list.
				ingredientsList.remove(ingredientsList.getSelectedIndex());
				
				//Remove ingredient from user collection and from user in database.
				user.removeIngredientFromCollection(selectedIngredient);
				IngredientActions.removeIngredient(selectedIngredient);
										
			}
		});
		
	}
	
	//Constructor for another frame to change to this class's content pane instead of closing itself and opening this as a new frame.
	public IngredientsListView(JFrame frame, Container pane) {
		
		this();
		
		//Save previous frame for back button and changed title functionality.
		previousFrame = frame;
		previousPane = pane;
		//Change the title of the frame that used this class's content pane.
		previousFrame.setTitle("RIMA - User Ingredients");
		
		//Remove action listener created by default constructor.
		for(ActionListener l : backButton.getActionListeners()) {
			backButton.removeActionListener(l);
		}
		
		//Set up what to do when the back button is pressed.
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				previousFrame.setTitle("RIMA - Home");
				previousFrame.setContentPane(previousPane);	
				previousFrame.validate();
			}
		});
	}
}
