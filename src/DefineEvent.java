import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.ButtonGroup;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


public class DefineEvent {

	private JFrame frame;
	private JTextField textField;
	private int TypeEvent=3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DefineEvent window = new DefineEvent();
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
	public DefineEvent() {
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
		
		JLabel label = new JLabel("Event");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(30, 11, 35, 15);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Name");
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(52, 36, 27, 14);
		frame.getContentPane().add(label_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(84, 33, 283, 20);
		frame.getContentPane().add(textField);
		
		JLabel label_2 = new JLabel("Type");
		label_2.setForeground(Color.WHITE);
		label_2.setBounds(52, 62, 24, 14);
		frame.getContentPane().add(label_2);
		
		JRadioButton rbUncontrollable = new JRadioButton("Uncontrollable");
		rbUncontrollable.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				TypeEvent = 0;
			}
		});
		rbUncontrollable.setForeground(Color.WHITE);
		rbUncontrollable.setBackground(Color.GRAY);
		rbUncontrollable.setBounds(84, 58, 93, 23);
		frame.getContentPane().add(rbUncontrollable);
		
		JRadioButton rbControllable = new JRadioButton("Controllable");
		rbControllable.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				TypeEvent = 1;
			}
		});
		rbControllable.setForeground(Color.WHITE);
		rbControllable.setBackground(Color.GRAY);
		rbControllable.setBounds(84, 88, 83, 23);
		frame.getContentPane().add(rbControllable);
		
		ButtonGroup TypeSubject = new ButtonGroup();
		    TypeSubject.add(rbUncontrollable);
		    TypeSubject.add(rbControllable);
		    
		
		JLabel label_3 = new JLabel("Type");
		label_3.setForeground(Color.WHITE);
		label_3.setBounds(52, 149, 24, 14);
		frame.getContentPane().add(label_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("Listen\r\nSee\r\nSense\r\nTalk\r\nMove");
		comboBox.setBounds(84, 146, 283, 20);
		frame.getContentPane().add(comboBox);
		
		JButton btnAddEvent = new JButton("Add Event");
		btnAddEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnectDatabase cdb = new ConnectDatabase();
		        System.out.println("After Connect Database!");

		        try {
		            int val = cdb.st.executeUpdate("INSERT INTO events (EventName, EventTypesID) VALUES ('"+textField.getText()+"',"+TypeEvent+")");
		            System.out.println("1 row affected");		           
		        } catch (SQLException ex) {
		        	System.out.println("SQL statement is not executed!"+ex);
		        } 
			}
		});
		btnAddEvent.setBounds(278, 189, 89, 23);
		frame.getContentPane().add(btnAddEvent);
	}
}
