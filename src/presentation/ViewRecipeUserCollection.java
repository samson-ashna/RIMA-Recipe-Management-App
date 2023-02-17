package presentation;

import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.UserActivity;
import objects.Recipes;
import objects.User;
import persistence.UserDAOImpl;
import persistence.UsersDAO;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

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
	public void NewScreen(String name) {
		try {
			ViewRecipeUserCollection dialog = new ViewRecipeUserCollection(name);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewRecipeUserCollection(String name) {
		setBounds(100, 100, 862, 574);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 836, 503);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		//Create a text area where the information on the recipe with the specified name will be displayed
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		contentPanel.add(textArea);
		
		User currentUser = UserActivity.getCurrentUser();
		
		/*The user database is accessed through a data access object and the details of 
		 * the recipe in the user's personal collection that has the specified name is displayed.*/ 
		UsersDAO db = new UserDAOImpl();
		Recipes r = db.getRecipe(currentUser,name);
		textArea.setText(r.toString());
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 503, 507, 33);
		getContentPane().add(buttonPane);
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
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
		
	}
}
