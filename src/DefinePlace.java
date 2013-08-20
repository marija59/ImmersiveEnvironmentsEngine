import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;


public class DefinePlace {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DefinePlace window = new DefinePlace();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DefinePlace() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Name");
		label.setForeground(Color.WHITE);
		label.setBounds(26, 24, 27, 14);
		frame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(58, 21, 353, 20);
		frame.getContentPane().add(textField);
		
		JButton button = new JButton("Add Place");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnectDatabase cdb = new ConnectDatabase();
		        System.out.println("After Connect Database!");

		        try {
		            int val = cdb.st.executeUpdate("INSERT INTO places (PlaceName) VALUES ('"+textField.getText()+"')");
		            System.out.println("1 row affected");		           
		        } catch (SQLException ex) {
		        	System.out.println("SQL statement is not executed!"+ex);
		        } 
			}
		});
		button.setBackground(Color.GRAY);
		button.setBounds(195, 46, 79, 23);
		frame.getContentPane().add(button);
	}

}
