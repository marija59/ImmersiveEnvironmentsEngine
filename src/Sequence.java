import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JSeparator;


public class Sequence {

	private JFrame frmTimer;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sequence window = new Sequence();
					window.frmTimer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Sequence() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTimer = new JFrame();
		frmTimer.setTitle("Timer - Sequence");
		frmTimer.setBounds(100, 100, 433, 497);
		frmTimer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTimer.getContentPane().setLayout(null);
		
		JLabel lblStarts = new JLabel("Starts");
		lblStarts.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblStarts.setBounds(10, 129, 46, 14);
		frmTimer.getContentPane().add(lblStarts);
		
		JLabel lblTriggeredByEvent = new JLabel("Triggered by event/info");
		lblTriggeredByEvent.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblTriggeredByEvent.setBounds(20, 154, 128, 14);
		frmTimer.getContentPane().add(lblTriggeredByEvent);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(176, 150, 103, 20);
		frmTimer.getContentPane().add(comboBox);
		
		JLabel lblSequenceOfControllable = new JLabel("Sequence of controllable events:");
		lblSequenceOfControllable.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblSequenceOfControllable.setBounds(10, 180, 240, 14);
		frmTimer.getContentPane().add(lblSequenceOfControllable);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(123, 372, 103, 20);
		frmTimer.getContentPane().add(comboBox_1);
		
		JLabel lblEvent = new JLabel("Event/concurent events");
		lblEvent.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblEvent.setBounds(10, 376, 138, 14);
		frmTimer.getContentPane().add(lblEvent);
		
		JLabel lblNewLabel = new JLabel("Time before");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 349, 70, 14);
		frmTimer.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(123, 345, 103, 20);
		frmTimer.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblMsec = new JLabel("msec");
		lblMsec.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblMsec.setBounds(233, 349, 46, 14);
		frmTimer.getContentPane().add(lblMsec);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(20, 205, 374, 100);
		frmTimer.getContentPane().add(textPane);
		
		JButton btnAddInSeq = new JButton("Add in seq");
		btnAddInSeq.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnAddInSeq.setBounds(295, 372, 89, 23);
		frmTimer.getContentPane().add(btnAddInSeq);
		
		JButton btnNewButton = new JButton("Concurrent");
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton.setBounds(123, 316, 103, 23);
		frmTimer.getContentPane().add(btnNewButton);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblName.setBounds(10, 11, 46, 14);
		frmTimer.getContentPane().add(lblName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(51, 7, 86, 20);
		frmTimer.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Create sequence");
		btnNewButton_1.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton_1.setBounds(10, 42, 127, 23);
		frmTimer.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Here the name of the current sequence");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 93, 260, 14);
		frmTimer.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton_2 = new JButton("Save");
		btnNewButton_2.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton_2.setBounds(181, 425, 89, 23);
		frmTimer.getContentPane().add(btnNewButton_2);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 76, 397, 6);
		frmTimer.getContentPane().add(separator);
	}
}
