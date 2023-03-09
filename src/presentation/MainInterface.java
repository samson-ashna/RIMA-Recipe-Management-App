package presentation;

import java.awt.Font;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainInterface extends JFrame {
    protected static JFrame frame;
    private ImageIcon icon;
	private ImageIcon logo;
    private JLabel label;
	
    private JButton findRecipes;
    private JButton end;

    public static void main(String[] args) {

        new MainInterface();
        
    }

    public MainInterface() {

        // Background Image
		icon = new ImageIcon(this.getClass().getResource("/res/background.jpg"));
        label = new JLabel(icon);
        label.setSize(1280, 720);



        // Find Recipes
        findRecipes = new JButton("Find Recipes");
        findRecipes.setBackground(new Color(59, 89, 182));
        findRecipes.setForeground(Color.WHITE);
        findRecipes.setFocusPainted(false);
        findRecipes.setFont(new Font("Tahoma", Font.BOLD, 12));
		findRecipes.setBounds(560, 380, 156, 27);
		findRecipes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RecipeList list = new RecipeList();
				list.frame.setVisible(true);
				frame.setVisible(false);
				frame.dispose();
			}
		});

        // Add Components
        label.add(findRecipes);


        // Setup
        frame = new JFrame("RIMA - Welcome!");
        frame.add(label);
        frame.setSize(1280, 720);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
