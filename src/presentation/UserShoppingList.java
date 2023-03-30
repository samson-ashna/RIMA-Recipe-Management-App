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

import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserShoppingList extends JFrame {

	private JPanel contentPane;

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
		btnHomepage.setBounds(32, 20, 234, 31);
		panel_4_usefulbuttons.add(btnHomepage);
		
		JButton btnDisplayList = new JButton("Display List");
		btnDisplayList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDisplayList.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDisplayList.setBounds(347, 20, 234, 31);
		panel_4_usefulbuttons.add(btnDisplayList);
		
		/*JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExit.setBounds(977, 20, 234, 31);
		panel_4_usefulbuttons.add(btnExit);*/
		
		JButton btnAddtoList = new JButton("Add Ingredient");
		btnAddtoList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Create a SaveShoppingListView window
				SaveShoppingListView addShoppingListPage = new SaveShoppingListView();
						
				//Make the SaveRecipesView window visible.
				addShoppingListPage.setVisible(true);	
				contentPane.setVisible(false);
				
				Window win = SwingUtilities.getWindowAncestor(contentPane);
				win.dispose();
			}
		});
		btnAddtoList.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAddtoList.setBounds(661, 20, 234, 31);
		panel_4_usefulbuttons.add(btnAddtoList);
		
		


		btnHomepage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
		
			}
		});
	}
}
