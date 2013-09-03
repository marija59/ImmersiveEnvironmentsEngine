import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;


public class Agent {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Agent window = new Agent();
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
	public Agent() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 752);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Dynamics (Position, Speed, Acceleration...)");
		label.setBounds(59, 35, 224, 14);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Embodied actuation 1,2,3...");
		label_1.setBounds(69, 64, 150, 14);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Lenght");
		label_2.setBounds(102, 89, 81, 14);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("Position");
		label_3.setBounds(102, 114, 46, 14);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Speed");
		label_4.setBounds(102, 139, 46, 14);
		frame.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("Acceleration");
		label_5.setBounds(102, 164, 95, 14);
		frame.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("Sound");
		label_6.setBounds(69, 189, 46, 14);
		frame.getContentPane().add(label_6);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(185, 86, 86, 20);
		frame.getContentPane().add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(185, 111, 86, 20);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(185, 136, 86, 20);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(185, 161, 86, 20);
		frame.getContentPane().add(textField_3);
		
		JLabel label_7 = new JLabel("msec");
		label_7.setBounds(277, 89, 46, 14);
		frame.getContentPane().add(label_7);
		
		JLabel label_8 = new JLabel("Lenght");
		label_8.setBounds(102, 214, 46, 14);
		frame.getContentPane().add(label_8);
		
		JLabel label_9 = new JLabel("Volume");
		label_9.setBounds(102, 239, 46, 14);
		frame.getContentPane().add(label_9);
		
		JLabel label_10 = new JLabel("Type");
		label_10.setBounds(102, 260, 46, 14);
		frame.getContentPane().add(label_10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("Ambient\r\nDialog\r\nMusic\r\nSpot\r\nOther");
		comboBox.setBounds(185, 257, 86, 20);
		frame.getContentPane().add(comboBox);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(185, 236, 86, 20);
		frame.getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(185, 211, 86, 20);
		frame.getContentPane().add(textField_5);
		
		JLabel label_11 = new JLabel("Animation");
		label_11.setBounds(69, 291, 87, 14);
		frame.getContentPane().add(label_11);
		
		JLabel label_12 = new JLabel("Type");
		label_12.setBounds(102, 316, 46, 14);
		frame.getContentPane().add(label_12);
		
		JLabel label_13 = new JLabel("Length");
		label_13.setBounds(102, 341, 46, 14);
		frame.getContentPane().add(label_13);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setToolTipText("Film\r\n3D\r\nSimulator\r\nOther");
		comboBox_1.setBounds(185, 313, 86, 20);
		frame.getContentPane().add(comboBox_1);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(185, 338, 86, 20);
		frame.getContentPane().add(textField_6);
		
		JLabel label_14 = new JLabel("Light");
		label_14.setBounds(69, 366, 46, 14);
		frame.getContentPane().add(label_14);
		
		JLabel label_15 = new JLabel("Type");
		label_15.setBounds(102, 383, 46, 14);
		frame.getContentPane().add(label_15);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(185, 380, 86, 20);
		frame.getContentPane().add(comboBox_2);
		
		JLabel label_16 = new JLabel("Special Effect");
		label_16.setBounds(69, 428, 100, 14);
		frame.getContentPane().add(label_16);
		
		JLabel label_17 = new JLabel("Type");
		label_17.setBounds(102, 452, 46, 14);
		frame.getContentPane().add(label_17);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setToolTipText("Wind\r\nFog\r\nSmell\r\nTactile\r\nOther");
		comboBox_3.setBounds(185, 449, 86, 20);
		frame.getContentPane().add(comboBox_3);
		
		JLabel label_18 = new JLabel("msec");
		label_18.setBounds(277, 214, 46, 14);
		frame.getContentPane().add(label_18);
		
		JLabel label_19 = new JLabel("msec");
		label_19.setBounds(277, 341, 46, 14);
		frame.getContentPane().add(label_19);
	}
}
