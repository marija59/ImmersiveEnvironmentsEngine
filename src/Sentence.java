import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Color;


public class Sentence extends JPanel {

	/**
	 * Create the panel.
	 */
	public Sentence() {
		setBackground(Color.GRAY);
		setLayout(null);
		
		JLabel lblSubject = new JLabel("Agent/Prop/Participant");
		lblSubject.setForeground(Color.WHITE);
		lblSubject.setBounds(29, 25, 155, 14);
		add(lblSubject);
		
		JButton btnDefineSubject = new JButton("Add new");
		btnDefineSubject.setBackground(Color.GRAY);
		btnDefineSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDefineSubject.setBounds(157, 21, 89, 23);
		add(btnDefineSubject);
		
		JLabel lblPlace = new JLabel("Place");
		lblPlace.setForeground(Color.WHITE);
		lblPlace.setBounds(29, 59, 46, 14);
		add(lblPlace);
		
		JButton btnDefinePlace = new JButton("Add new");
		btnDefinePlace.setBackground(Color.GRAY);
		btnDefinePlace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnDefinePlace.setBounds(157, 55, 89, 23);
		add(btnDefinePlace);
		
		JLabel lblSentences = new JLabel("Sentences:");
		lblSentences.setForeground(Color.WHITE);
		lblSentences.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSentences.setBounds(29, 144, 89, 14);
		add(lblSentences);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(Color.GRAY);
		comboBox.setBounds(29, 200, 74, 20);
		add(comboBox);
		
		JLabel lblSubject_1 = new JLabel("Subject");
		lblSubject_1.setForeground(Color.WHITE);
		lblSubject_1.setBounds(29, 169, 46, 14);
		add(lblSubject_1);
		
		JLabel lblVerb = new JLabel("Verb");
		lblVerb.setForeground(Color.WHITE);
		lblVerb.setBounds(138, 169, 46, 14);
		add(lblVerb);
		
		JLabel lblNewLabel = new JLabel("Object");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(245, 169, 46, 14);
		add(lblNewLabel);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setForeground(Color.WHITE);
		lblLocation.setBounds(348, 169, 46, 14);
		add(lblLocation);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBackground(Color.GRAY);
		comboBox_1.setBounds(245, 200, 74, 20);
		add(comboBox_1);
		
		JLabel lblEvent = new JLabel("Event");
		lblEvent.setForeground(Color.WHITE);
		lblEvent.setBounds(29, 93, 46, 14);
		add(lblEvent);
		
		JButton btnAddNew = new JButton("Add new");
		btnAddNew.setBackground(Color.GRAY);
		btnAddNew.setBounds(157, 89, 89, 23);
		add(btnAddNew);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBackground(Color.GRAY);
		comboBox_2.setBounds(138, 200, 74, 20);
		add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(348, 200, 74, 20);
		add(comboBox_3);
		
		JButton btnAddNew_1 = new JButton("Add new");
		btnAddNew_1.setBackground(Color.GRAY);
		btnAddNew_1.setBounds(348, 243, 89, 23);
		add(btnAddNew_1);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(31, 293, 391, 220);
		add(textPane);

	}
}
