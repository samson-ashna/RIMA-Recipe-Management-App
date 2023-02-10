package presentation;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class SaveRecipesView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaveRecipesView frame = new SaveRecipesView();
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
	public SaveRecipesView() {
		setTitle("Save Recipe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 762);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(327, 71, 216, 41);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Recipe Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(112, 58, 157, 59);
		contentPane.add(lblNewLabel);
		
		JLabel lblIngredients = new JLabel("Ingredients");
		lblIngredients.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIngredients.setBounds(112, 249, 157, 59);
		contentPane.add(lblIngredients);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(327, 413, 216, 178);
		contentPane.add(textField_1);
		
		JLabel lblInstruction = new JLabel("Instruction");
		lblInstruction.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblInstruction.setBounds(112, 409, 157, 59);
		contentPane.add(lblInstruction);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(327, 267, 216, 121);
		contentPane.add(textField_2);
		
		JLabel lblProteing = new JLabel("Protein (g)");
		lblProteing.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblProteing.setBounds(112, 124, 157, 59);
		contentPane.add(lblProteing);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(327, 139, 216, 36);
		contentPane.add(textField_3);
		
		JLabel lblCarbscal = new JLabel("Carbs(g)");
		lblCarbscal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCarbscal.setBounds(112, 196, 157, 59);
		contentPane.add(lblCarbscal);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(327, 207, 216, 36);
		contentPane.add(textField_4);
		
		JButton btnNewButton = new JButton("Enter");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(233, 628, 207, 59);
		contentPane.add(btnNewButton);
	}

}
