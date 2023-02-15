package presentation;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import objects.Recipes;
import objects.User;
import persistence.UserDAOImpl;
import persistence.UsersDAO;

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
		JEditorPane editorPane = new JEditorPane();
		contentPanel.add(editorPane);
		
		{
			UsersDAO db = new UserDAOImpl();
			User currentUser = db.getCurrentUser();
			for (Recipes r: db.getRecipes(currentUser)) {
				if(r.getName().equals(name)) {
					editorPane.setText(r.toString());
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 503, 612, 33);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		}
		{
			JButton btnNewButton = new JButton("Save and Exit");
			btnNewButton.setBounds(639, 503, 197, 23);
			getContentPane().add(btnNewButton);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String newText = editorPane.getText();
				}
			});
		}
	}

}
