package presentation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import businessLogic.IngredientActions;
import businessLogic.UserActivity;
import objects.Ingredient;
import objects.User;
@SuppressWarnings("serial")
public class EditIngredientView extends JFrame{
	
	//Ingredients view list and buttons for re-enabling upon return.
	private JComponent[] componentsToToggle;
	//List model to edit.\
	DefaultListModel<String> listModel;
	IngredientsListView previousFrame;
	
	//Pane objects
	private Container contentPane;
	
	private JPanel optionsPane = new JPanel();
	private JPanel buttonPane = new JPanel();
	
	private JPanel namePane = new JPanel();
	private JPanel proteinPane = new JPanel();
	private JPanel carbsPane = new JPanel();
	private JPanel costPane = new JPanel();
	private JPanel expirationPane = new JPanel();
	private JPanel[] panes = {namePane, costPane, expirationPane, carbsPane, proteinPane, buttonPane};

	//Text field objects
	private JTextField nameField = new JTextField("");
	private JTextField proteinField = new JTextField("");
	private JTextField carbsField = new JTextField("");
	private JTextField costField = new JTextField("");
	
	//Label objects.
	private JLabel nameLabel = new JLabel("Ingredient Name");
	private JLabel proteinLabel = new JLabel("Protein (g)");
	private JLabel carbsLabel = new JLabel("Carbs (g)");
	private JLabel costLabel = new JLabel("Cost");
	private JLabel expirationLabel = new JLabel("Expiration Date");
	private JLabel errorLabel = new JLabel("");
	
	//Date pane
	private JPanel datePane = new JPanel();
	private JComboBox<String> dayBox;
	private JComboBox<String> monthBox;
	private JComboBox<String> yearBox;
	
	//ComboBox options;
	static String[] days;
	static final String[] months = {"January", "February", "March", "April",
									"May", "June", "July", "August",
									"September", "October", "November", "December"};
	static String[] years;
	
	//Button Objects
	private JButton cancelButton = new JButton("Cancel");
	private JButton saveButton  = new JButton("Save");
	
	//Current user
	private User currentUser;
	
	//Ingredient getting edited
	private Ingredient ingredient;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditIngredientView frame = new EditIngredientView(null, null, null, null);
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
	public EditIngredientView(JComponent[] components, Ingredient selectedIngredient, DefaultListModel<String> list, IngredientsListView listFrame) {
		//Save previous frame's buttons to re-enable them.
		componentsToToggle = components;
		ingredient = selectedIngredient;
		listModel = list;
		previousFrame = listFrame;
		
		//Set frame title.
		setTitle("RIMA - Edit Ingredient");
		//Set the application to exit when closed.
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 		
		//Set the size and pop up location of the window.
		setSize(450, 450);	
		setLocationRelativeTo(null);
		
		//Get content pane.
		contentPane = getContentPane();		
		
		//set the Layout and border of the panels.
		for(JPanel panel : panes) {
			//Set an invisible border for each panel.
			panel.setBorder(new EmptyBorder(5, 5, 5, 5));
			//Set each panel's layout manager to the horizontal box layout.
			panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		}
		
		//set the layout and border of the optionsPanel
		optionsPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		optionsPane.setLayout(new BoxLayout(optionsPane, BoxLayout.PAGE_AXIS));
				
		//Add ingredient object's attributes to text fields.
		textFieldsSetup();
		
		//Set up error label font and colour.
		errorLabel.setForeground(new Color(255, 0, 0));
		errorLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		comboBoxSetup();
		
		//Add components to respective panes.
		namePane.add(nameLabel);
		namePane.add(Box.createRigidArea(new Dimension(20, 0)));
		namePane.add(nameField);
		
		costPane.add(costLabel);
		costPane.add(Box.createRigidArea(new Dimension(20, 0)));
		costPane.add(costField);

		//Add input object here.
		expirationPane.add(expirationLabel);
		expirationPane.add(Box.createRigidArea(new Dimension(20, 0)));	
		expirationPane.add(datePane);
		
		proteinPane.add(proteinLabel);
		proteinPane.add(Box.createRigidArea(new Dimension(20, 0)));
		proteinPane.add(proteinField);
		
		carbsPane.add(carbsLabel);
		carbsPane.add(Box.createRigidArea(new Dimension(20, 0)));
		carbsPane.add(carbsField);		
		
		//Set up the button fonts.
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		saveButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		
		//Add buttons to button pane.
		buttonPane.add(Box.createHorizontalGlue());
		buttonPane.add(cancelButton);
		buttonPane.add(Box.createRigidArea(new Dimension(5, 0)));
		buttonPane.add(saveButton);
		buttonPane.add(Box.createHorizontalGlue());
		
		//Build options pane.
		optionsPane.add(Box.createVerticalGlue());
		for(int i = 0; i<panes.length-1; i++) {
			optionsPane.add(panes[i]);
		}
		optionsPane.add(Box.createRigidArea(new Dimension(0, 5)));
		optionsPane.add(errorLabel);
		optionsPane.add(Box.createVerticalGlue());
		
		//Build content pane.
		contentPane.add(optionsPane);
		contentPane.add(buttonPane, BorderLayout.PAGE_END);
		
		//Set up what to do when the back button is pressed.
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Close the UserRecipeCollection Window.
				Window win = SwingUtilities.getWindowAncestor(contentPane);
				win.dispose();	
				
				//Re-enable ingredient view components.
				for(JComponent component:componentsToToggle) {
					component.setEnabled(true);
				}
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

		//Sets up save button to add ingredient to list or edit it when pressed and close window.
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Format for dates.
				DateTimeFormatter format = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);;
				
				//Ingredient to update.
				Ingredient newIngredient = new Ingredient("", 0, LocalDate.of(Year.now().getValue(), 1, 1), 0, 0, "");
				
				//Get the current user.
				currentUser = UserActivity.getCurrentUser();
				
				//Flag for whether a change has been made.
				boolean change = false;
				
				//Reset error label.
				errorLabel.setText("");
				
				//Check that the user is logged in.
				if(currentUser == null) return;
				
				//If the name field is blank, inform the user and do nothing.
				if(nameField.getText().equals("")){
					errorLabel.setText("Name can't be blank!");
					return;
				}
				
				//If the cost field is blank, inform the user and do nothing.
				if(costField.getText().equals("")){
					errorLabel.setText("Cost can't be blank!");
					return;
				}	
				
				//If the carbs field is blank, inform the user and do nothing.
				if(carbsField.getText().equals("")){
					errorLabel.setText("Carbs can't be blank!");
					return;
				}
				
				//If the protein field is blank, inform the user and do nothing.
				if(proteinField.getText().equals("")){
					errorLabel.setText("Protein can't be blank!");
					return;
				}	
				
				//If an ingredient was given to be edited and it corresponds to the current user. (EDITING)
				if(ingredient != null /*&& currentUser.getName().equals(ingredient.getUser())*/) {
					//Set up an ingredient to modify
					newIngredient = new Ingredient(ingredient.getName(), ingredient.getCost(), ingredient.getExpiration(), ingredient.getProtein(), ingredient.getCarbs(), ingredient.getUser());
						
					//If the new ingredient name exists and isn't it's own, do nothing and inform the user that the ingredient exists.
					if (IngredientActions.checkName(nameField.getText()) && !nameField.getText().equalsIgnoreCase(ingredient.getName())){
						errorLabel.setText("Ingredient name already exists!");
						return;
					}

					//If the name field differs from the ingredient's name, change the name.
					if(!nameField.getText().equals(ingredient.getName())) {
						newIngredient.setName(nameField.getText());
						change = true;
					}
						
					//Field for which field is being checked.
					String entry = "Cost";
					try {
						//If the cost has changed and isn't blank, update it.
						if(Double.parseDouble(costField.getText()) != ingredient.getCost()){
							newIngredient.setCost(Double.parseDouble(costField.getText()));
							change = true;
						}
							
						entry = "Date";
						//If the expiration has changed and isn't blank, update it.
						if(!format.format(ingredient.getExpiration()).equals(dayBox.getSelectedItem() + " " + monthBox.getSelectedItem() + " " + yearBox.getSelectedItem())){
							newIngredient.setExpiration(LocalDate.parse((dayBox.getSelectedItem() + " " + monthBox.getSelectedItem() + " " + yearBox.getSelectedItem()), format));
							change = true;
						}
						
						entry = "Protein";
						//If the protein has changed and isn't blank, update it.
						if(Integer.parseInt(proteinField.getText()) != ingredient.getProtein()){
							newIngredient.setProtein(Integer.parseInt(proteinField.getText()));
							change = true;
						}
						
						entry = "Carbs";
						//If the carbs have changed and the field isn't blank, update.
						if(Integer.parseInt(carbsField.getText()) != ingredient.getCarbs()){
							newIngredient.setCarbs(Integer.parseInt(carbsField.getText()));
							change = true;
						}
						
							
					} catch (Exception ex) {
						errorLabel.setText(entry + " entry invalid!");
						ex.printStackTrace();
						return;
					}
					
					if(change) currentUser.removeIngredientFromCollection(ingredient);
					
				//If there is no ingredient to edit. (ADDING)
				} else if(ingredient == null){
					//If the new ingredient name exists, do nothing and inform the user that the ingredient exists.
					if (IngredientActions.checkName(nameField.getText())){
						errorLabel.setText("Ingredient name already exists!");
						return;
					}
					
					//Otherwise set ingredient name.
					newIngredient.setName(nameField.getText());
						
					//Identifier for which field is being checked.
					String entry = "Cost";
					try {
						//Update other fields.
						newIngredient.setCost(Double.parseDouble(costField.getText()));
						entry = "Date";
						newIngredient.setExpiration(LocalDate.parse((dayBox.getSelectedItem() + " " + monthBox.getSelectedItem() + " " + yearBox.getSelectedItem()),format));
						entry = "Protein";
						newIngredient.setProtein(Integer.parseInt(proteinField.getText()));
						entry = "Carbs";
						newIngredient.setCarbs(Integer.parseInt(carbsField.getText()));
					} catch (Exception ex) {
						errorLabel.setText(entry + " entry invalid!");
						return;
					}
					
					//Update the ingredient in the database and in the user's collection.
					change = true;
				}
					
				if(change) {
					//Update the ingredient in the database and in the user's collection.
					newIngredient.setUser(currentUser);
					currentUser.addIngredientToCollection(newIngredient);
					IngredientActions.updateIngredients();
					
					//Close the UserRecipeCollection Window.
					Window win = SwingUtilities.getWindowAncestor(contentPane);
					win.dispose();
					
					//Re-enable ingredient view components.
					for(JComponent component:componentsToToggle) {
						component.setEnabled(true);
					}
					
					//Update ingredientsList.
					listModel.removeAllElements();
					for(Ingredient ingredient:currentUser.getIngredients()) {
						listModel.addElement(ingredient.getName());
					}
					JList list = (JList)componentsToToggle[componentsToToggle.length-1];
					list.ensureIndexIsVisible(listModel.getSize());
				}
			}
		});
		
		monthBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(days);
            	switch((String)monthBox.getSelectedItem()){
            		case "January":
            			model = new DefaultComboBoxModel<>(days);
            			break;
            		case "February":
            			if(Year.now().getValue()%4 == 0) {
            				model = new DefaultComboBoxModel<>(Arrays.copyOfRange(days, 0, 29));
            			}else {
            				model = new DefaultComboBoxModel<>(Arrays.copyOfRange(days, 0, 28));
            			}
            			break;
            		case "March":
            			model = new DefaultComboBoxModel<>(days);
            			break;
            		case "April":
            			model = new DefaultComboBoxModel<>(Arrays.copyOfRange(days, 0, 30));
            			break;
            		case "May":
            			model = new DefaultComboBoxModel<>(days);
            			break;
            		case "June":
            			model = new DefaultComboBoxModel<>(Arrays.copyOfRange(days, 0, 30));
            			break;
            		case "July":
            			model = new DefaultComboBoxModel<>(days);
            			break;
            		case "August":
            			model = new DefaultComboBoxModel<>(days);
            			break;
            		case "September":
            			model = new DefaultComboBoxModel<>(Arrays.copyOfRange(days, 0, 30));
            			break;
            		case "October":
            			model = new DefaultComboBoxModel<>(days);
            			break;
            		case "November":
            			model = new DefaultComboBoxModel<>(Arrays.copyOfRange(days, 0, 30));
            			break;
            		case "December":
            			model = new DefaultComboBoxModel<>(days);
            			break;
            	}
            	dayBox.setModel(model);
            }
        });
	}
	
	private void textFieldsSetup() {
		//Save the current user.
		currentUser = UserActivity.getCurrentUser();
		DecimalFormat format = new DecimalFormat("######0.00");
		
		//Do nothing and close the window if the current user is null.
		if(currentUser == null) {
			//Close the UserRecipeCollection Window.
			Window win = SwingUtilities.getWindowAncestor(contentPane);
			win.dispose();
			return;
		}
		
		//Set text field sizes and max sizes.
		nameField.setSize(281, 26);
		nameField.setMaximumSize(nameField.getSize());
		costField.setSize(281, 26);
		costField.setMaximumSize(costField.getSize());
		proteinField.setSize(281, 26);
		proteinField.setMaximumSize(proteinField.getSize());
		carbsField.setSize(281, 26);
		carbsField.setMaximumSize(carbsField.getSize());
		
		//If ingredient is not null, meaning an ingredient is being edited instead of created, add current ingredient info to text fields.
		if(ingredient != null) {
			nameField.setText(ingredient.getName());
			costField.setText(format.format(ingredient.getCost()));
			proteinField.setText(""+ingredient.getProtein());
			carbsField.setText(""+ingredient.getCarbs());
		}
		
	}
	
	private void comboBoxSetup() {
		//Set up days values
		days = new String[31];
		for(int i = 1; i<32; i++) {
			days[i-1] = ""+i; 
		}
			
		//Set up years values.
		int currentYear = Year.now().getValue();
		years = new String[20];
		for(int i = 0; i<20; i++) {
			years[i] = ""+(currentYear+i);
		}
		
		//Create date boxes.
		dayBox = new JComboBox<String>(days);
		monthBox = new JComboBox<String>(months);
		yearBox = new JComboBox<String>(years);
		
		//Set maximum size of date boxes
		dayBox.setMaximumSize(new Dimension((int)dayBox.getMinimumSize().getWidth(), (int)dayBox.getMinimumSize().getHeight()));
		monthBox.setMaximumSize(new Dimension((int)monthBox.getMinimumSize().getWidth(), (int)monthBox.getMinimumSize().getHeight()));
		yearBox.setMaximumSize(new Dimension((int)yearBox.getMinimumSize().getWidth(), (int)yearBox.getMinimumSize().getHeight()));
		
		//Change selected date items if ingredient isn't null.
		if(ingredient != null) {
			
			//set selected month
			monthBox.setSelectedIndex(ingredient.getExpiration().getMonthValue()-1);
			
			//Set selected day
			dayBox.setSelectedIndex(ingredient.getExpiration().getDayOfMonth()-1);
			
			//set selected year.
			yearBox.setSelectedIndex(ingredient.getExpiration().getYear()-Year.now().getValue());
		}
		
		datePane.setLayout(new BoxLayout(datePane, BoxLayout.LINE_AXIS));
		
		datePane.add(dayBox);
		datePane.add(Box.createRigidArea(new Dimension(0, 5)));
		datePane.add(monthBox);
		datePane.add(Box.createRigidArea(new Dimension(0, 5)));
		datePane.add(yearBox);
	}
}
