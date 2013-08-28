import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;


public class Time {

	private JFrame frmOnceUponA;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Time window = new Time();
					window.frmOnceUponA.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Time() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOnceUponA = new JFrame();
		frmOnceUponA.setTitle("Once upon a TIME...");
		frmOnceUponA.setBounds(100, 100, 612, 709);
		frmOnceUponA.getContentPane().setLayout(null);
		
		JLabel lblBeforeUser = new JLabel("BEFORE (Inicialize environment)");
		lblBeforeUser.setBounds(23, 11, 198, 14);
		frmOnceUponA.getContentPane().add(lblBeforeUser);
		
		JLabel lblInteraction = new JLabel("INTERACTIVE NARRATIVE");
		lblInteraction.setBounds(23, 176, 159, 14);
		frmOnceUponA.getContentPane().add(lblInteraction);
		
		JLabel lblAfter = new JLabel("AFTER (And they lived happily ever after... or ...)");
		lblAfter.setBounds(23, 429, 314, 14);
		frmOnceUponA.getContentPane().add(lblAfter);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(43, 224, 309, 20);
		frmOnceUponA.getContentPane().add(comboBox);
		
		JLabel lblSentence = new JLabel("Sentence");
		lblSentence.setBounds(43, 201, 46, 14);
		frmOnceUponA.getContentPane().add(lblSentence);
		
		JLabel lblTime = new JLabel("Time(Reactive-Feedback, Narrative-Feedforward)");
		lblTime.setBounds(43, 265, 272, 14);
		frmOnceUponA.getContentPane().add(lblTime);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setToolTipText("BEFORE\r\nMEETS\r\nOVERLAPS\r\nSTARTS\r\nDURING\r\nFINISHES\r\nEQUALS");
		comboBox_1.setBounds(43, 292, 180, 20);
		frmOnceUponA.getContentPane().add(comboBox_1);
		
		JLabel label = new JLabel("Sentence");
		label.setBounds(43, 333, 46, 14);
		frmOnceUponA.getContentPane().add(label);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(43, 356, 309, 20);
		frmOnceUponA.getContentPane().add(comboBox_2);
		
		JButton btnNewButton = new JButton("Add time");
		btnNewButton.setBounds(43, 387, 89, 23);
		frmOnceUponA.getContentPane().add(btnNewButton);
	}
}
