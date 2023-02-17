package presentation;

import java.util.ArrayList;

import java.awt.Font;
import java.awt.Window;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import businessLogic.UserActivity;
import objects.Recipes;
import persistence.DAO;
import persistence.RecipesDAOImpl;

/**
 * 
 */
@SuppressWarnings("serial")
public class RecipeList extends JFrame {

	//Content pane object
	private JPanel contentPane;
	//List section object
	private JList<String> list;
	//Button object
	private final JButton btnBack = new JButton("Back");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecipeList frame = new RecipeList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Adds the recipes in the app's database to the list section to display them
	 */
	public void addRecipes() {
		DefaultListModel<String> model = new DefaultListModel<String>();
		DAO<Recipes> db = new RecipesDAOImpl();
		ArrayList<Recipes> recipes = db.getAll();
		for(Recipes r: recipes) {
			model.addElement(r.getName());
		}
		list.setModel(model);
	}
	/**
	 * Create the frame.
	 */
	public RecipeList() {
		setTitle("RIMA - Recipes List");
		//Sets the application to exit when closed
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Sest the bounds of the window
		setSize(319, 270);
		setLocationRelativeTo(null);
		//Creates a new content pane
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//Replaces the frame's content pane with the one that was just set up.
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//Creates a new section for an item list
		list= new JList<String>();
		list.setBackground(new Color(255, 255, 255));
		list.setBounds(10, 11, 280, 195);
		//Adds the recipes in the app's database to the list section to display them
		addRecipes();
		//Sets up what to do when an item in the list is selected. When selected the recipe's detailed information is displayed.
		list.getSelectionModel().addListSelectionListener(e-> {
			String name = (String) list.getSelectedValue();
			ViewRecipeDB newWindow = new ViewRecipeDB(name);
			newWindow.NewScreen(name);
			contentPane.setVisible(false);
			Window win = SwingUtilities.getWindowAncestor(contentPane);
			win.dispose();
		});
		contentPane.add(list);
		//Creates a back button. When clicked, user is redirected to the main page.
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnBack.setBounds(225, 211, 65, 18);
		contentPane.add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(UserActivity.getCurrentUser() == null) {
					Main.frame.setVisible(true);
				}else {
					HomePage.contentPane.setVisible(true);
				}
				contentPane.setVisible(false);
				Window win = SwingUtilities.getWindowAncestor(contentPane);
				win.dispose();
				
			}
		});
		
	}
}
