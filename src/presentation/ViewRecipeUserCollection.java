package presentation;

import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.UserActivity;
import objects.Recipes;
import objects.User;
import persistence.UsersStubDB;
import persistence.DatabaseAccess;
import persistence.DAO;
import persistence.UsersDAO;
import persistence.UsersDB;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;

/**
 * 
 */
@SuppressWarnings("serial")
public class ViewRecipeUserCollection extends JDialog {

	private final JPanel contentPanel= new JPanel();

	/**
	 * 
	 * @param name
	 */
	public void NewScreen(String name, int page) {
		try {
			ViewRecipeUserCollection dialog = new ViewRecipeUserCollection(name, page);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewRecipeUserCollection(String name,int page) {
		setBounds(100, 100, 862, 574);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 836, 503);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		User currentUser = UserActivity.getCurrentUser();
		
		/*The user database is accessed through a data access object and the details of 
		 * the recipe in the user's personal collection that has the specified name is displayed.*/ 
		DatabaseAccess access = new DatabaseAccess();
		UsersDAO db = access.usersDB();
		Recipes r = db.getRecipe(currentUser,name);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 820, 487);
		contentPanel.add(scrollPane);
		//Create a text area where the information on the recipe with the specified name will be displayed
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);
		textArea.setText(r.toString());
		
		// Creates a new button. When clicked user adds the recipe to Favourites.
		JButton btnFavourite;
		// Checks if the selected recipe is already a favourite
		if (r.favourite == 0)
		{
			// Button displays "Favourite" is the recipe is not currently a favourite recipe
			btnFavourite = new JButton("Favourite");
			btnFavourite.setBounds(354, 503, 155, 23);
			getContentPane().add(btnFavourite);
			btnFavourite.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					db.editFavorites(r,1);
					//r.isFavourite();
					UserRecipeCollection collection = new UserRecipeCollection();
					collection.setVisible(true);
					contentPanel.setVisible(false);
					Window win = SwingUtilities.getWindowAncestor(contentPanel);
					win.dispose();
				}
			});
		}
		// Checks if the selected recipe is already a favourite
		else if (r.favourite == 1)
		{
			// Button displays "Not Favourite" is the recipe is currently a favourite recipe
			btnFavourite = new JButton("Not Favourite");
			btnFavourite.setBounds(354, 503, 155, 23);
			getContentPane().add(btnFavourite);
			btnFavourite.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					db.editFavorites(r,0);
					//r.notFavourite();
					UserRecipeCollection collection = new UserRecipeCollection();
					collection.setVisible(true);
					contentPanel.setVisible(false);
					Window win = SwingUtilities.getWindowAncestor(contentPanel);
					win.dispose();
				}
			});
		}	
		
		/*Creates a new button. When clicked, the user is redirected to 
		the Edit Recipe page where they can modify the recipe's information. */
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(632, 503, 63, 23);
		getContentPane().add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					EditRecipeView editview= new EditRecipeView(r);
					editview.setVisible(true);
					contentPanel.setVisible(false);
					Window win = SwingUtilities.getWindowAncestor(contentPanel);
					win.dispose();
				}
		});
		// Creates a new button. When clicked, the recipe is removed from the user's personal collection.
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(519, 503, 103, 23);
		getContentPane().add(btnRemove);
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					db.removeRecipes(currentUser, r);
					UserRecipeCollection collection = new UserRecipeCollection();
					collection.setVisible(true);
					contentPanel.setVisible(false);
					Window win = SwingUtilities.getWindowAncestor(contentPanel);
					win.dispose();
				}
		});
		// Creates a new button. When clicked user is redirected back to the list of recipes.
		JButton btnReturn = new JButton("Return to List");
		btnReturn.setBounds(705, 503, 131, 23);
		getContentPane().add(btnReturn);
		
		
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					UserRecipeCollection collection = new UserRecipeCollection();
					collection.setVisible(true);
					contentPanel.setVisible(false);
					Window win = SwingUtilities.getWindowAncestor(contentPanel);
					win.dispose();
				}
		});
		JButton plannerButton = new JButton("Add to Plan");
		plannerButton.setBounds(10, 503, 131, 23);
		getContentPane().add(plannerButton);
		if(page ==0) {
			plannerButton.setVisible(false);
		}
		plannerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					UserMealPlanner.setRecipe(name);
					contentPanel.setVisible(false);
					Window win = SwingUtilities.getWindowAncestor(contentPanel);
					win.dispose();
				}
		});

	}
}