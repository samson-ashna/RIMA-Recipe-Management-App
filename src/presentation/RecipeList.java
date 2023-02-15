package presentation;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import domain.*;
import objects.*;
import persistence.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class RecipeList extends JFrame {

	private JPanel contentPane;
	
	private JList<String> list;
	private final JButton btnNewButton = new JButton("Back");

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(319, 270);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		list= new JList<String>();
		list.setBackground(new Color(255, 255, 255));
		list.setBounds(10, 11, 280, 195);
		addRecipes();
		list.getSelectionModel().addListSelectionListener(e-> {
			String name = (String) list.getSelectedValue();
			ViewRecipe newWindow = new ViewRecipe(name);
			newWindow.NewScreen(name);
		});
		contentPane.add(list);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton.setBounds(225, 211, 65, 18);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
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
