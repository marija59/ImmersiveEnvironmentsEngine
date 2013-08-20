import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JRadioButton;

import java.awt.Insets;

import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


public class DefineSubject {

	private JFrame frame;
	private JTextField textField;
	private int TypeSub=3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DefineSubject window = new DefineSubject();
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
	public DefineSubject() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 1;
		frame.getContentPane().add(lblName, gbc_lblName);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		frame.getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblType = new JLabel("Type");
		lblType.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblType = new GridBagConstraints();
		gbc_lblType.insets = new Insets(0, 0, 5, 5);
		gbc_lblType.gridx = 1;
		gbc_lblType.gridy = 2;
		frame.getContentPane().add(lblType, gbc_lblType);
		
		JRadioButton rdbtnParticipant = new JRadioButton("Participant");
		rdbtnParticipant.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				TypeSub = 0;
			}
		});
		rdbtnParticipant.setForeground(Color.WHITE);
		rdbtnParticipant.setBackground(Color.GRAY);
		GridBagConstraints gbc_rdbtnParticipant = new GridBagConstraints();
		gbc_rdbtnParticipant.anchor = GridBagConstraints.WEST;
		gbc_rdbtnParticipant.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnParticipant.gridx = 2;
		gbc_rdbtnParticipant.gridy = 3;
		frame.getContentPane().add(rdbtnParticipant, gbc_rdbtnParticipant);
		
		JRadioButton rdbtnAgent = new JRadioButton("Agent");
		rdbtnAgent.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				TypeSub = 1;
			}
		});
		rdbtnAgent.setForeground(Color.WHITE);
		rdbtnAgent.setBackground(Color.GRAY);
		GridBagConstraints gbc_rdbtnAgent = new GridBagConstraints();
		gbc_rdbtnAgent.anchor = GridBagConstraints.WEST;
		gbc_rdbtnAgent.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnAgent.gridx = 2;
		gbc_rdbtnAgent.gridy = 4;
		frame.getContentPane().add(rdbtnAgent, gbc_rdbtnAgent);
		
		JRadioButton rdbtnProp = new JRadioButton("Prop");
		rdbtnProp.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				TypeSub = 2;
			}
		});
		rdbtnProp.setForeground(Color.WHITE);
		rdbtnProp.setBackground(Color.GRAY);
		GridBagConstraints gbc_rdbtnProp = new GridBagConstraints();
		gbc_rdbtnProp.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnProp.anchor = GridBagConstraints.WEST;
		gbc_rdbtnProp.gridx = 2;
		gbc_rdbtnProp.gridy = 5;
		frame.getContentPane().add(rdbtnProp, gbc_rdbtnProp);
				
		 //Group the radio buttons.
	    ButtonGroup TypeSubject = new ButtonGroup();
	    TypeSubject.add(rdbtnParticipant);
	    TypeSubject.add(rdbtnAgent);
	    TypeSubject.add(rdbtnProp);
	    		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        ConnectDatabase cdb = new ConnectDatabase();
		        System.out.println("After Connect Database!");

		        try {
		            int val = cdb.st.executeUpdate("INSERT INTO subjects (SubjectName, SubjectTypesID) VALUES ('"+textField.getText()+"',"+TypeSub+")");
		            System.out.println("1 row affected");		           
		        } catch (SQLException ex) {
		        	System.out.println("SQL statement is not executed!"+ex);
		        } 
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 2;
		gbc_btnNewButton.gridy = 6;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
	}

}
