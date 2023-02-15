package presentation;

import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import objects.Recipes;
import persistence.DAO;
import persistence.RecipesDAOImpl;

import javax.swing.BoxLayout;
import java.awt.TextArea;
import java.awt.Font;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class ViewRecipe extends JDialog {

	private final JPanel contentPanel= new JPanel();


	public void NewScreen(String name) {
		try {
			ViewRecipe dialog = new ViewRecipe(name);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewRecipe(String name) {
		setSize(862, 574);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 836, 503);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		{
			TextArea textArea = new TextArea();
			textArea.setFont(new Font("Dialog", Font.PLAIN, 23));
			textArea.setEditable(false);
			contentPanel.add(textArea);
			DAO<Recipes> db = new RecipesDAOImpl();
			textArea.setText(db.get(name).toString());
			for (Recipes r: db.getAll()) {
				if(r.getName().equals(name)) {
					textArea.setText(r.toString());
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
			JButton btnNewButton = new JButton("Save To My Collection");
			btnNewButton.setBounds(639, 503, 197, 23);
			getContentPane().add(btnNewButton);
		}
	}

}
