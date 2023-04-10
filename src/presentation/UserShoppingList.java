package presentation;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

import businessLogic.UserActivity;
import persistence.DatabaseAccess;
import persistence.UsersDAO;

import java.awt.Color;
import java.awt.event.ActionListener;
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
	 * Create the frame.
	 */
	public UserShoppingList() {

		setTitle("RIMA - Shopping List");
		setResizable(false);
		setBounds(0, 0, 450, 450);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(143, 188, 143));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_4_usefulbuttons = new JPanel();
		panel_4_usefulbuttons.setBackground(new Color(143, 188, 143));
		panel_4_usefulbuttons.setLayout(null);
		panel_4_usefulbuttons.setBounds(20, 350, 400, 58);
		contentPane.add(panel_4_usefulbuttons);
		
		JButton btnHomepage = new JButton("Home");
		btnHomepage.setForeground(new Color(255, 255, 255));
        btnHomepage.setBackground(new Color(59, 89, 182));
		btnHomepage.setBounds(330, 20, 70, 27);
		panel_4_usefulbuttons.add(btnHomepage);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 20, 400, 300);
		contentPane.add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		list.setModel(model);
		for(String n: UserActivity.currentUser.getShoppingList()) {
			model.addElement(n);
		}

		JButton btnRemoveFromList = new JButton("Remove");
		btnRemoveFromList.setForeground(new Color(255, 255, 255));
        btnRemoveFromList.setBackground(new Color(59, 89, 182));
		btnRemoveFromList.setBounds(230, 20, 90, 27);
		panel_4_usefulbuttons.add(btnRemoveFromList);

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
		
		ingredientInfo = new JTextField();
		ingredientInfo.setBounds(20, 330, 400, 31);
		contentPane.add(ingredientInfo);
		
		JButton btnAddToList = new JButton("Add");
		btnAddToList.setForeground(new Color(255, 255, 255));
        btnAddToList.setBackground(new Color(59, 89, 182));
		btnAddToList.setBounds(150, 20, 70, 27);
		panel_4_usefulbuttons.add(btnAddToList);

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