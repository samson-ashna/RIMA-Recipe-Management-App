package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import businessLogic.IngredientActions;
import businessLogic.UserActivity;
import objects.Ingredient;
import objects.User;
import persistence.UsersStubDB;

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
	private final JButton[] buttons = {backButton, addButton};
	private JList<String> ingredientsList = new JList<String>();
	
	//Popup menu items
	private JPopupMenu menu = new JPopupMenu();
	private JMenuItem editButton = new JMenuItem("Edit");
	private JMenuItem removeButton = new JMenuItem("Remove");
	
	//Current user.
	private User user;


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
		//Ingredient arraylist for user's ingredients.
		ArrayList<Ingredient> ingredients;
		String[] ingredientNames;
		
		//Save the current user.
		user = UserActivity.getCurrentUser();
		
		if(user != null) {
			ingredients = IngredientActions.getIngredients();
		}else {
			return;
		}
		
		if(ingredients != null) {
			//Initialize ingredientNames.
			ingredientNames = new String[ingredients.size()];
			
			//Add ingredient names to ingredientNames.
			for(int i = 0; i<ingredientNames.length; i++) {
				ingredientNames[i] = ingredients.get(i).getName();
			}
			
			//Build ingredientsList using ingredientNames.
			ingredientsList = new JList<String>(ingredientNames);
		}
	}
	
	
	/**
	 * Create the frame.
	 */
	public IngredientsListView() {
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
		addButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		//Add buttons to button pane.
		buttonPane.add(Box.createHorizontalGlue());
		buttonPane.add(backButton);
		buttonPane.add(Box.createRigidArea(new Dimension(5, 0)));
		buttonPane.add(addButton);
		
		//Set up menu item fonts.
		editButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		removeButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		//Set up popup menu.
		menu.add(editButton);
		menu.add(removeButton);
		
		//Add info and button panes to content pane.
		contentPane.add(listPane);
		contentPane.add(buttonPane, BorderLayout.PAGE_END);
		
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
		
		//Set up what to do when the back button is pressed.
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Create an EditIngredientView window
				EditIngredientView editView = new EditIngredientView(buttons);
						
				//Make the HomePage window visible and the UserRecipeCollection window invisible.
				editView.setVisible(true);
				
				//Disable buttons.
				backButton.setEnabled(false);
				addButton.setEnabled(false);
								
			}
		});
		
		//Set up what to do when list item is left clicked.
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
