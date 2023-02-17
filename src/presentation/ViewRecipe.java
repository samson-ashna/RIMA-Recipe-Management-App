package presentation;

import java.awt.Font;
import java.awt.Window;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.TextArea;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import domain.SaveRecipe;
import domain.UserActivity;
import objects.Recipes;
import persistence.DAO;
import persistence.RecipesDAOImpl;

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
		setTitle("RIMA - View Recipe");
		setSize(862, 574);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 836, 503);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		DAO<Recipes> db = new RecipesDAOImpl();
		{
			TextArea textArea = new TextArea();
			textArea.setFont(new Font("Dialog", Font.PLAIN, 23));
			textArea.setEditable(false);
			contentPanel.add(textArea);
			
			textArea.setText(db.get(name).toString());
			for (Recipes r: db.getAll()) {
				if(r.getName().equals(name)) {
					textArea.setText(r.toString());
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 503, 478, 33);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		}
		{
			JButton btnNewButton = new JButton("Save To My Collection");
			btnNewButton.setBounds(493, 503, 171, 23);
			getContentPane().add(btnNewButton);
			{
				JButton btnNewButton_1 = new JButton("Return to list");
				btnNewButton_1.setBounds(686, 503, 150, 23);
				getContentPane().add(btnNewButton_1);
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						RecipeList lst = new RecipeList();
						lst.setVisible(true);
						contentPanel.setVisible(false);
						Window win = SwingUtilities.getWindowAncestor(contentPanel);
						win.dispose();
					}
				});
				
			}
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(UserActivity.getCurrentUser() != null) {
						SaveRecipe saveRecipe = new SaveRecipe(UserActivity.getCurrentUser());
						saveRecipe.save(db.get(name));
						btnNewButton.setText("Saved to Collection");
					}else {
						btnNewButton.setText("Log in to save recipe!");
					}
				}
			});
			
		}
	}

}
