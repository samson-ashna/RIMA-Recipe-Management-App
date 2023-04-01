package presentation;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;
import java.awt.Window;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import businessLogic.UserActivity;
import objects.Recipes;
import persistence.DatabaseAccess;
import persistence.UsersDAO;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JList;

public class UserShoppingList extends JFrame {

	private JPanel contentPane;
	private JTextField ingredientInfo;
	DefaultListModel<String> model = new DefaultListModel<String>();
	DatabaseAccess access = new DatabaseAccess();
	UsersDAO db = access.usersDB();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserShoppingList frame = new UserShoppingList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserShoppingList() {

		setResizable(false);
		setBounds(0, 0, 1280, 680);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_4_usefulbuttons = new JPanel();
		panel_4_usefulbuttons.setLayout(null);
		panel_4_usefulbuttons.setBorder(new LineBorder(new Color(0, 0, 0), 8));
		panel_4_usefulbuttons.setBounds(10, 568, 1246, 68);
		contentPane.add(panel_4_usefulbuttons);
		
		JButton btnHomepage = new JButton("<-- Back to HomePage");
		btnHomepage.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnHomepage.setBounds(976, 20, 234, 31);
		panel_4_usefulbuttons.add(btnHomepage);
		
		/*JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExit.setBounds(977, 20, 234, 31);
		panel_4_usefulbuttons.add(btnExit);*/
		
		/**
		 * Adds the current user's shopping list to the list section to display them.
		 */
		/*public void ingredientList() {
			//Create a new list model for the user's recipes.
			DefaultListModel<String> model = new DefaultListModel<String>();		
			//get a new instance of the user database.
			DatabaseAccess access = new DatabaseAccess();
			UsersDAO db = access.usersDB();
			ArrayList<String> ingredients = new ArrayList<String>(); 
			ingredients = db.getFavoriteList(UserActivity.getCurrentUser());
			//Add all the user's favourite recipes to the list model.
			for(Recipes r: recipes) {
				model.addElement(r.getName());
			}
			
			//Set the model for the list section to be the one that was 
			list.setModel(model);
		}*/
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 35, 873, 499);
		contentPane.add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		list.setModel(model);
		for(String n: UserActivity.currentUser.getShoppingList()) {
			model.addElement(n);
		}
		JButton btnRemoveFromList = new JButton("Remove Selected Ingredient");
		btnRemoveFromList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Create a SaveShoppingListView window
				String name = (String) list.getSelectedValue();
				UserActivity.currentUser.removeFromShoppingLst(name);
				db.editShoppingList(UserActivity.currentUser.shoppingItems(),UserActivity.currentUser.getName() );
				model.removeElement(name);
				HomePage.shoppingLstSetUp();

			}
		});
		btnRemoveFromList.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnRemoveFromList.setBounds(27, 20, 300, 31);
		panel_4_usefulbuttons.add(btnRemoveFromList);
		
		
		
		JLabel lblName = new JLabel("Ingredient & Quantity");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(947, 181, 258, 59);
		contentPane.add(lblName);
		
		ingredientInfo = new JTextField();
		ingredientInfo.setBounds(957, 260, 240, 41);
		contentPane.add(ingredientInfo);
		
		JButton btnAddToList = new JButton("Add to List");
		btnAddToList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean incorrectValues = false;
				String name = ingredientInfo.getText();
				if(!name.equals("")) {
					
					UserActivity.currentUser.addToShoppingLst(name);
					db.editShoppingList(UserActivity.currentUser.shoppingItems(),UserActivity.currentUser.getName() );
					model.addElement(name);
					HomePage.shoppingLstSetUp();

				}
				
			}
		});
	
		btnAddToList.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAddToList.setBounds(975, 347, 207, 59);
		contentPane.add(btnAddToList);
		
		


		btnHomepage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HomePage.shoppingLstSetUp();
				dispose();
		
			}
		});
		
		// create a class to get the name and quantity of a shopping item
		class ShoppingItem {
	        private String name;
	        private int quantity;

	        public ShoppingItem(String name, int quantity) {
	            this.name = name;
	            this.quantity = quantity;
	        }

	        public String getName() {
	            return name;
	        }

	        public void setName(String name) {
	            this.name = name;
	        }

	        public int getQuantity() {
	            return quantity;
	        }
	    }
	}
}
