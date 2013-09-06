import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JSplitPane;
import javax.swing.JSeparator;


public class Concurent {

	private JFrame frmConcurrent;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Concurent window = new Concurent();
					window.frmConcurrent.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Concurent() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConcurrent = new JFrame();
		frmConcurrent.setTitle("Concurrent");
		frmConcurrent.setBounds(100, 100, 450, 460);
		frmConcurrent.getContentPane().setLayout(null);
		
		JLabel lblEvent = new JLabel("Event/sequence:");
		lblEvent.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblEvent.setBounds(20, 310, 124, 14);
		frmConcurrent.getContentPane().add(lblEvent);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(154, 306, 103, 20);
		frmConcurrent.getContentPane().add(comboBox);
		
		JLabel lblConcurrentSetOf = new JLabel("Concurrent set of events:");
		lblConcurrentSetOf.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblConcurrentSetOf.setBounds(10, 136, 240, 14);
		frmConcurrent.getContentPane().add(lblConcurrentSetOf);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(20, 161, 374, 100);
		frmConcurrent.getContentPane().add(textPane);
		
		JButton btnAdd = new JButton("Add in con.");
		btnAdd.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnAdd.setBounds(305, 306, 89, 23);
		frmConcurrent.getContentPane().add(btnAdd);
		
		JButton btnNewButton = new JButton("Sequence");
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton.setBounds(154, 272, 103, 23);
		frmConcurrent.getContentPane().add(btnNewButton);
		
		JLabel label = new JLabel("Name");
		label.setFont(new Font("Calibri", Font.PLAIN, 11));
		label.setBounds(10, 15, 46, 14);
		frmConcurrent.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(51, 11, 86, 20);
		frmConcurrent.getContentPane().add(textField);
		
		JButton btnCreateConcurrent = new JButton("Create concurrent");
		btnCreateConcurrent.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnCreateConcurrent.setBounds(10, 46, 127, 23);
		frmConcurrent.getContentPane().add(btnCreateConcurrent);
		
		JLabel label_1 = new JLabel("Here the name of the current sequence");
		label_1.setFont(new Font("Calibri", Font.BOLD, 11));
		label_1.setBounds(10, 97, 260, 14);
		frmConcurrent.getContentPane().add(label_1);
		
		JButton btnNewButton_1 = new JButton("Save");
		btnNewButton_1.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton_1.setBounds(169, 366, 89, 23);
		frmConcurrent.getContentPane().add(btnNewButton_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 80, 397, 6);
		frmConcurrent.getContentPane().add(separator);
	}
}
