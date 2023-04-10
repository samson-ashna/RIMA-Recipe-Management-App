package presentation;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import businessLogic.UserActivity;
import objects.Ingredient;
import persistence.DatabaseAccess;
import persistence.UsersDAO;


@SuppressWarnings("serial")
public class IngredientView extends JFrame {
	JComponent[] componentsToToggle;
	Ingredient ingredient;
	
	//Container and Panel objects.
	private Container contentPane;
	private JPanel infoPane;
	private JPanel buttonPane;
	
	//Component objects.
	private final JButton backButton = new JButton("Back");
	private JLabel displName = new JLabel();
	private JLabel displCost =  new JLabel();
	private JLabel displExpiration = new JLabel();
	private JLabel displCarbs = new JLabel();
	private JLabel displProtein = new JLabel();
	
	//Panel objects.
	private JPanel namePane = new JPanel();
	private JPanel costPane =  new JPanel();
	private JPanel expirationPane = new JPanel();
	private JPanel carbsPane = new JPanel();
	private JPanel proteinPane = new JPanel();
	private JButton addToShoppingLstButton = new JButton("Add to Shopping List");
	
	private void displayUserInfo() {
		if(ingredient == null) {
			displName.setText("Error: ingredient null.");
			return;
		}
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);
		DecimalFormat costFormat = new DecimalFormat("$###,###,##0.00");
		String expirationText = format.format(ingredient.getExpiration());
		
		if(ingredient.getExpiration().isBefore(LocalDate.now())) {
			expirationText = expirationText + "<br>[Expired]";
		}else if (ingredient.getExpiration().isBefore(LocalDate.now().plusWeeks(1))){
			expirationText = expirationText + "<br>[Expires within 7 days]";
		}
		
		displName.setText("<html><div align=\"center\"><span style='font-size:14pt;'>Name</span><br>"+ingredient.getName()+"</div></html>");
		displCost.setText("<html><div align=\"center\"><span style='font-size:14pt;'>Cost</span><br>"+costFormat.format(ingredient.getCost())+"</div></html>");
		displExpiration.setText("<html><div align=\"center\"><span style='font-size:14pt;'>Expiration Date</span><br>"+expirationText+"</div></html>");
		displCarbs.setText("<html><div align=\"center\"><span style='font-size:14pt;'>Carbs (g)</span><br>"+ingredient.getCarbs()+"</div></html>");
		displProtein.setText("<html><div align=\"center\"><span style='font-size:14pt;'>Protein (g)</span><br>"+ingredient.getProtein()+"</div></html>");
	}
	
	public IngredientView(JComponent[] components, Ingredient selectedIngredient) {
		componentsToToggle = components;
		ingredient = selectedIngredient;
		
		setTitle("RIMA - Ingredient View");
		//Set the application to exit when closed.
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 		
		//Set the size and pop up location of the window.
		setSize(300, 350);	
		setLocationRelativeTo(null);
		//Get content pane.
		contentPane = getContentPane();		
		
		//Create a new info pane.
		infoPane = new JPanel();
		infoPane.setBounds(0, 0, 284, 278);
		//Set the info pane's layout manager to the vertical box layout.
		infoPane.setLayout(new BoxLayout(infoPane, BoxLayout.PAGE_AXIS));
		//Make an invisible border for the info pane.
		infoPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				
		//Add user info to labels.
		displayUserInfo();	
		
		//Add labels to panes.
		namePane.add(displName);
		costPane.add(displCost);
		expirationPane.add(displExpiration);
		carbsPane.add(displCarbs);
		proteinPane.add(displProtein);
		
		namePane.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int)namePane.getMinimumSize().getHeight()));
		costPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int)costPane.getMinimumSize().getHeight()));
		expirationPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int)expirationPane.getMinimumSize().getHeight()));
		carbsPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int)carbsPane.getMinimumSize().getHeight()));
		proteinPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, (int)proteinPane.getMinimumSize().getHeight()));
		
		//Align panes.
		namePane.setAlignmentX(CENTER_ALIGNMENT);	
		costPane.setAlignmentX(CENTER_ALIGNMENT);	
		expirationPane.setAlignmentX(CENTER_ALIGNMENT);	
		carbsPane.setAlignmentX(CENTER_ALIGNMENT);	
		proteinPane.setAlignmentX(CENTER_ALIGNMENT);
		
		//Add labels to the info pane.
		infoPane.add(Box.createVerticalGlue());
		infoPane.add(namePane);
		infoPane.add(Box.createRigidArea(new Dimension(0, 10)));
		infoPane.add(costPane);
		infoPane.add(Box.createRigidArea(new Dimension(0, 10)));
		infoPane.add(expirationPane);
		infoPane.add(Box.createRigidArea(new Dimension(0, 10)));
		infoPane.add(carbsPane);
		infoPane.add(Box.createRigidArea(new Dimension(0, 10)));
		infoPane.add(proteinPane);
		infoPane.add(Box.createVerticalGlue());
				
		//Create a new pane for buttons.
		buttonPane = new JPanel();
		buttonPane.setBounds(0, 278, 284, 33);
		//Set an invisible border for the button pane.
		buttonPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//Set the button pane's layout manager to the horizontal box layout.
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		
		//Set up the button fonts.
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		addToShoppingLstButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		//Add buttons to button pane.
		buttonPane.add(Box.createHorizontalGlue());
		buttonPane.add(backButton);
		buttonPane.add(addToShoppingLstButton);
		getContentPane().setLayout(null);
		
		
		//Add info and button panes to content pane.
		contentPane.add(infoPane);
		contentPane.add(buttonPane);
		
		//Set up what to do when the back button is pressed.
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for(JComponent component:componentsToToggle) {
					component.setEnabled(true);
				}
				
				//Close the UserRecipeCollection Window.
				Window win = SwingUtilities.getWindowAncestor(contentPane);
				win.dispose();				
			}
		});
		
		//Set up what to do when window is closed
		this.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent e) {
		    	//Re-enable ingredient view components.
				for(JComponent component:componentsToToggle) {
					component.setEnabled(true);
				}
				
		    }
		});
		addToShoppingLstButton.setSize(5,5);
		DatabaseAccess access = new DatabaseAccess();
		UsersDAO db = access.usersDB();
		addToShoppingLstButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserActivity.currentUser.addToShoppingLst(selectedIngredient.getName());
				db.editShoppingList(UserActivity.currentUser.shoppingItems(),UserActivity.currentUser.getName() );
				addToShoppingLstButton.setText("Added to Shopping List");
				HomePage.shoppingLstSetUp();
			}
		});
		addToShoppingLstButton.setVisible(false);
		if(!UserActivity.currentUser.getShoppingList().contains(selectedIngredient.getName())) {
			addToShoppingLstButton.setVisible(true);
		}
	}
}
