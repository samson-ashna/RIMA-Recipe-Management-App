package presentation;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import objects.Recipes;
import repository.RecipeRepository;

import javax.swing.JList;
import java.awt.Color;

@SuppressWarnings("serial")
public class RecipeList extends JFrame {

	private JPanel contentPane;
	
	private JList<String> list;

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
		ArrayList<Recipes> recipes = RecipeRepository.getRecipes();
		for(Recipes r: recipes) {
			model.addElement(r.getName());
		}
		list.setModel(model);
	}
	/**
	 * Create the frame.
	 */
	public RecipeList() {
		setTitle("Recipes List");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 316, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		list= new JList<String>();
		list.setBackground(new Color(255, 255, 255));
		list.setBounds(10, 11, 280, 189);
		addRecipes();
		list.getSelectionModel().addListSelectionListener(e-> {
			String name = (String) list.getSelectedValue();
			ViewRecipe newWindow = new ViewRecipe(name);
			newWindow.NewScreen(name);
		});

		contentPane.add(list);
		
	}
}
