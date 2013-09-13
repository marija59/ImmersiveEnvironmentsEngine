import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;


public class HardwareItem {

	private JFrame frmHardware;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HardwareItem window = new HardwareItem();
					window.frmHardware.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HardwareItem() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHardware = new JFrame();
		frmHardware.setTitle("Hardware Item");
		frmHardware.setBounds(100, 100, 450, 300);
		frmHardware.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHardware.getContentPane().setLayout(null);
		
		JLabel lblModalityType = new JLabel("Modality type");
		lblModalityType.setBounds(10, 11, 89, 14);
		frmHardware.getContentPane().add(lblModalityType);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(109, 8, 206, 20);
		frmHardware.getContentPane().add(comboBox);
		
		JLabel lblHardwaresoftwareType = new JLabel("Hardware type");
		lblHardwaresoftwareType.setBounds(10, 40, 151, 14);
		frmHardware.getContentPane().add(lblHardwaresoftwareType);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(109, 39, 206, 20);
		frmHardware.getContentPane().add(comboBox_1);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 75, 46, 14);
		frmHardware.getContentPane().add(lblName);
		
		textField = new JTextField();
		textField.setBounds(109, 72, 206, 20);
		frmHardware.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(10, 112, 70, 14);
		frmHardware.getContentPane().add(lblDescription);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(109, 107, 206, 74);
		frmHardware.getContentPane().add(textArea);
	}
}
