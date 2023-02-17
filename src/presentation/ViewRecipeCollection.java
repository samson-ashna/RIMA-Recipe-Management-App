package presentation;

import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.UserActivity;
import objects.Recipes;
import objects.User;
import persistence.UserDAOImpl;
import persistence.UsersDAO;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ViewRecipeCollection extends JDialog {

	private final JPanel contentPanel= new JPanel();


	public void NewScreen(String name) {
		try {
			ViewRecipeCollection dialog = new ViewRecipeCollection(name);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewRecipeCollection(String name) {
		setBounds(100, 100, 862, 574);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 836, 503);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		contentPanel.add(textArea);
		UsersDAO db = new UserDAOImpl();
		User currentUser = UserActivity.getCurrentUser();	
		Recipes r = db.getRecipe(currentUser,name);
		textArea.setText(r.toString());
//		for (Recipes r: db.getRecipes(currentUser)) {
//			if(r.getName().equals(name)) {
//				textArea.setText(r.toString());
//			}
//		}
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 503, 507, 33);
		getContentPane().add(buttonPane);
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.setBounds(632, 503, 63, 23);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Remove");
		btnNewButton_2.setBounds(519, 503, 103, 23);
		getContentPane().add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					db.removeRecipes(currentUser, r);
					UserRecipeCollection collection = new UserRecipeCollection();
					collection.setVisible(true);
					contentPanel.setVisible(false);
					Window win = SwingUtilities.getWindowAncestor(contentPanel);
					win.dispose();
				}
		});
		JButton btnNewButton = new JButton("Return to List");
		btnNewButton.setBounds(705, 503, 131, 23);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
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
