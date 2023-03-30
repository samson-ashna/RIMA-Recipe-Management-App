package presentation;

import java.awt.Font;
import java.awt.Window;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.TextArea;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import businessLogic.SaveRecipe;
import businessLogic.UserActivity;
import objects.Recipes;
import persistence.DAO;
import persistence.RecipesStubDB;
import persistence.UsersDAO;
import persistence.DatabaseAccess;

/**
 * 
 */
@SuppressWarnings("serial")
public class ViewRecipeDB extends JDialog {

	//Panel object
	private final JPanel contentPanel= new JPanel();

	/**
	 * 
	 * @param name
	 */
	public void NewScreen(String name) {
		try {
			ViewRecipeDB dialog = new ViewRecipeDB(name);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewRecipeDB(String name) {
		setTitle("RIMA - View Recipe");
		//Set the size and pop up location of the window.
		setSize(1280, 720);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 1232, 619);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));

		//Create a text area where the information on the recipe with the specified name will be displayed
		TextArea textArea = new TextArea();
		textArea.setFont(new Font("Dialog", Font.PLAIN, 23));
		textArea.setEditable(false);
		contentPanel.add(textArea);
		//Recipe database is accessed through the data access object and the recipe information is displayed in the text area.
		DatabaseAccess access = new DatabaseAccess();
		DAO<Recipes> db = access.recipesDB();
		textArea.setText(db.get(name).toString());
		for (Recipes r: db.getAll()) {
			if(r.getName().equals(name)) {
				textArea.setText(r.toString());
			}
		}
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 503, 120, 33);
		getContentPane().add(buttonPane);
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		//Creates a new button. When clicked, the user is redirected back to the list of recipes. 
		JButton btnBack = new JButton("Return to list");
		btnBack.setBounds(1082, 630, 150, 23);
		getContentPane().add(btnBack);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecipeList lst = new RecipeList();
				lst.getContentPane().setVisible(true);
				contentPanel.setVisible(false);
				Window win = SwingUtilities.getWindowAncestor(contentPanel);
				win.dispose();
			}
		});
		/*Create a new button. When clicked, the recipe is saved to the user's personal collection where it can be viewed and edited.
		If there is no logged in user, a message is displayed in the button*/
		JButton btnSave = new JButton("Save To My Collection");
		btnSave.setBounds(889, 630, 171, 23);
		getContentPane().add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(UserActivity.getCurrentUser() != null) {
					SaveRecipe saveRecipe = new SaveRecipe(UserActivity.getCurrentUser());
					saveRecipe.save(db.get(name));
					btnSave.setText("Saved to Collection");
				}else {
					btnSave.setText("Log in to save recipe!");
				}
			}
		});
		
	}
}
