package presentation;

import java.awt.Font;
import java.awt.Window;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Container;

import domain.UserActivity;
import objects.User;
import persistence.UserDAOImpl;
import persistence.UsersDAO;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SignUpPage extends JFrame {

	//Container and panel objects.
	private Container contentPane;
	private JPanel textPane;
	private JPanel buttonPane;
	
	//Component objects
	private final JLabel nameLabel = new JLabel("Username");
	private final JLabel passLabel = new JLabel("Password");;
	private final JLabel confirmPassLabel = new JLabel("Confirm Password");;
	private final JLabel allergiesLabel = new JLabel("Allergies");;
	private JTextField enterName = new JTextField();
	private JTextField enterPass = new JPasswordField();
	private JTextField enterPassAgain = new JPasswordField();
	private final JButton registerButton = new JButton("Register");
	private final JButton cancelButton = new JButton("Cancel");
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Create a new frame
					SignUpPage frame = new SignUpPage();
					//Make the frame visible.
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
	public SignUpPage() {
		setTitle("RIMA - Register Account");
		//Set the application to exit when closed.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 		
		//Set the size and pop up location of the window.
		setSize(478, 384);
		setLocationRelativeTo(null);		
		
		//Get content pane.
		contentPane = getContentPane();
		
		//Create a new text pane.
		textPane = new JPanel(); 
		textPane.setBounds(0, 0, 462, 275);
		//Set an invisible border for the text pane.
		textPane.setBorder(new EmptyBorder(5, 5, 5, 5));	
		//Set the text pane's layout manager to the vertical box layout.
		textPane.setLayout(new BoxLayout(textPane, BoxLayout.PAGE_AXIS));
		
		//Set up header fonts
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		passLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		confirmPassLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		allergiesLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		//Set up text field sizes and prevent resizing.
		enterName.setSize(281, 26);
		enterName.setMaximumSize(enterName.getSize());
		enterPass.setSize(281, 26);
		enterPass.setMaximumSize(enterPass.getSize());
		enterPassAgain.setSize(281, 26);
		enterPassAgain.setMaximumSize(enterPassAgain.getSize());
		
		//Center align labels and text fields.
		nameLabel.setAlignmentX(CENTER_ALIGNMENT);
		passLabel.setAlignmentX(CENTER_ALIGNMENT);
		confirmPassLabel.setAlignmentX(CENTER_ALIGNMENT);
		allergiesLabel.setAlignmentX(CENTER_ALIGNMENT);
		enterName.setAlignmentX(CENTER_ALIGNMENT);
		enterPass.setAlignmentX(CENTER_ALIGNMENT);
		enterPassAgain.setAlignmentX(CENTER_ALIGNMENT);
		
		//Add labels and text fields to text pane.
		textPane.add(Box.createVerticalGlue());
		textPane.add(nameLabel);
		textPane.add(Box.createRigidArea(new Dimension(0, 10)));
		textPane.add(enterName);
		textPane.add(Box.createRigidArea(new Dimension(0, 20)));
		textPane.add(passLabel);
		textPane.add(Box.createRigidArea(new Dimension(0, 10)));
		textPane.add(enterPass);
		textPane.add(Box.createRigidArea(new Dimension(0, 20)));
		textPane.add(confirmPassLabel);
		textPane.add(Box.createRigidArea(new Dimension(0, 10)));
		textPane.add(enterPassAgain);
		textPane.add(Box.createRigidArea(new Dimension(0, 20)));
		textPane.add(allergiesLabel);
		textPane.add(Box.createVerticalGlue());
		
		//Create a new button pane.
		buttonPane = new JPanel(); 
		buttonPane.setBounds(0, 300, 462, 45);
		//Set an invisible border for the text pane.
		buttonPane.setBorder(new EmptyBorder(10, 10, 10, 10));	
		//Set the text pane's layout manager to the vertical box layout.
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
		
		//Set up button fonts.
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		//Add buttons to button pane.		
		buttonPane.add(Box.createHorizontalGlue());
		buttonPane.add(registerButton);
		buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
		buttonPane.add(cancelButton);
		buttonPane.add(Box.createHorizontalGlue());
		getContentPane().setLayout(null);
		
		//Add text and button panes to the content pane.
		contentPane.add(textPane);
		
		JCheckBoxMenuItem chckbxmntmNewCheckItem = new JCheckBoxMenuItem("");
		textPane.add(chckbxmntmNewCheckItem);
		contentPane.add(buttonPane);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(255, 0, 0));
		label.setBounds(81, 269, 322, 31);
		getContentPane().add(label);
		
		//Set up what to do when the cancel button is pressed.
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Create a HomePage window
				Main mainPage = new Main();
						
				//Make the HomePage window visible and the UserRecipeCollection window invisible.
				mainPage.frame.setVisible(true);
				contentPane.setVisible(false);
						
				//Close the UserRecipeCollection Window.
				Window win = SwingUtilities.getWindowAncestor(contentPane);
				win.dispose();				
			}
		});	
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Create a HomePage window
				String name = enterName.getText();
				String password = enterPass.getText();
				
				if(!UserActivity.checkUserName(name)) {
					if(!enterPassAgain.getText().equals(password)) {
						label.setText("Passwords do not match!");
					}else {
						User newUser = new User(name, password);
						UsersDAO userDAO = new UserDAOImpl();
						userDAO.add(newUser);
						newUser.loggedIn = true;
						HomePage homePage = new HomePage();
						homePage.setVisible(true);
						contentPane.setVisible(false);
						Window win = SwingUtilities.getWindowAncestor(contentPane);
						win.dispose();
					}
				}else {
					label.setText("User name already exits.Select another!");
					label.setVisible(true);
				}		
			}
		});	
	}
}
