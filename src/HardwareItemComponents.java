import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JTextArea;

import java.awt.Color;

import javax.swing.JTree;
import javax.swing.JSeparator;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Vector;



public class HardwareItemComponents extends JFrame{
	private JFrame frame;
	private JTextField txtCompName;
	private JTextField txtStateName;
	private JTextField txtTransName;
	JComboBox cbComponent = new JComboBox();
	JComboBox cbStateFrom = new JComboBox();
	JComboBox cbStateTo = new JComboBox();
	JComboBox cbEvents = new JComboBox();
	private JComboBox comboBox;
	comboItem [] placeArray, subjectArray;
		  
	
    
	private void getData(){
		try {			  
			  
			  //cbComponent.removeAllItems();
			  //cbEvents.removeAllItems();			 
			  //cbStateFrom.removeAllItems();
			  //cbStateTo.removeAllItems();
			  String s = "";
		      int id = -1;	
		      comboItem cItem; // = new comboItem(s, id);
			  //comboItem []  items = {cItem};
		      
			  			  			  
			  ConnectDatabase cdb = new ConnectDatabase();
		        
		      ResultSet result = cdb.st.executeQuery("SELECT idcomponents, Name FROM components");  
		      List<comboItem> subjects = new ArrayList<comboItem>();
		      s = "";
		      id = -1;	
		      cItem = new comboItem(s, id);
		      subjects.add(cItem);
		 	  while(result.next()) { // process results one row at a time		        		       
		        s = result.getString("Name");
		        id = result.getInt("idcomponents");		        
		        cItem = new comboItem(s, id);
		        subjects.add(cItem);		        
		      }		 	 		 	  
		 	  subjectArray = subjects.toArray( new comboItem[subjects.size()]);	 
		 	  //cbComponent = new JComboBox(subjectArray);
		 	 
		 	 		      		      
		      result = cdb.st.executeQuery("SELECT idevents, EventName FROM events");		      
		      List<comboItem> events = new ArrayList<comboItem>();
		      s = "";
		      id = -1;	
		      cItem = new comboItem(s, id);
		      events.add(cItem);
			  while(result.next()) { // process results one row at a time		        		       
		        s = result.getString("EventName");
		        id = result.getInt("idevents");		        
		        cItem = new comboItem(s, id);
		        events.add(cItem);
		      }			  
			  comboItem [] eventArray = events.toArray( new comboItem[events.size()]);
			  cbEvents = new JComboBox(eventArray);			  
			  cbEvents.setFont(new Font("Calibri", Font.PLAIN, 11));

			  result = cdb.st.executeQuery("SELECT idcomp_states, Name FROM comp_states");
			  List<comboItem> places = new ArrayList<comboItem>();
			  s = "";
		      id = -1;	
		      cItem = new comboItem(s, id);
		      places.add(cItem);
			  while(result.next()) { // process results one row at a time		        		       
		        s = result.getString("Name");
		        id = result.getInt("idcomp_states");
		        cItem = new comboItem(s, id);
		        System.out.println(cItem.name);
		        
		        places.add(cItem);
		      }
			  placeArray = places.toArray( new comboItem[places.size()]);
			  //cbStateFrom = new JComboBox(placeArray);			  
			  //cbStateFrom.setFont(new Font("Calibri", Font.PLAIN, 11));
			  //cbStateTo = new JComboBox(placeArray);
			  //cbStateTo.setFont(new Font("Calibri", Font.PLAIN, 11));
			  
			  
			  
		      cdb.st.close();	
		    }
		    catch( Exception e ) {
		      e.printStackTrace();
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HardwareItemComponents window = new HardwareItemComponents();
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
	public HardwareItemComponents() {		
		initialize();
		getData();
		comboItem  cItem1 = new comboItem("", -1);
		comboItem[] items = {cItem1};
		DefaultComboBoxModel<comboItem> model = new DefaultComboBoxModel(placeArray);
		DefaultComboBoxModel<comboItem> modelStateTo = new DefaultComboBoxModel(placeArray);
	    comboBox = new JComboBox(model);		      
	    comboBox.setBounds(126, 523, 89, 23);	    
	    frame.getContentPane().add(comboBox, BorderLayout.SOUTH);	
	    
	    cbStateFrom = new JComboBox(model);
	    cbStateTo = new JComboBox(modelStateTo);
	    cbStateFrom.setBounds(100, 223, 252, 20);
		frame.getContentPane().add(cbStateFrom);
				
		cbStateTo.setBounds(100, 248, 252, 20);
		frame.getContentPane().add(cbStateTo);
	    
	    
	    DefaultComboBoxModel<comboItem> modelComp = new DefaultComboBoxModel(subjectArray);
	    cbComponent = new JComboBox(modelComp);
	    cbComponent.setBounds(100, 106, 115, 20);
		frame.getContentPane().add(cbComponent);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 405, 779);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblHardwareItem = new JLabel("Hardware Item");
		lblHardwareItem.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblHardwareItem.setBounds(8, 461, 89, 14);
		frame.getContentPane().add(lblHardwareItem);
		
		JComboBox cbHardwareItem = new JComboBox();
		cbHardwareItem.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbHardwareItem.setBounds(100, 458, 252, 20);
		frame.getContentPane().add(cbHardwareItem);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblName.setBounds(8, 36, 46, 14);
		frame.getContentPane().add(lblName);
		
		txtCompName = new JTextField();
		txtCompName.setBounds(100, 33, 115, 20);
		frame.getContentPane().add(txtCompName);
		txtCompName.setColumns(10);
		
		JButton btnAddComponent = new JButton("Add component");
		btnAddComponent.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnAddComponent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConnectDatabase cdb = new ConnectDatabase();
		        System.out.println("After Connect Database!");		        		       
		        try {
		            int val = cdb.st.executeUpdate("INSERT INTO components (Name) VALUES ('"+txtCompName.getText()+"')");
		            System.out.println("1 row affected");		           
		        } catch (SQLException ex) {
		        	System.out.println("SQL statement is not executed!"+ex);
		        }
		        getData();
		        comboItem  cItem1 = new comboItem(txtCompName.getText(), -1);
		        cbComponent.addItem(cItem1);
			}
		});
		btnAddComponent.setBounds(231, 32, 115, 23);
		frame.getContentPane().add(btnAddComponent);
		
		JLabel lblComponents_1 = new JLabel("Components:");
		lblComponents_1.setFont(new Font("Calibri", Font.BOLD, 12));
		lblComponents_1.setBounds(8, 11, 104, 14);
		frame.getContentPane().add(lblComponents_1);
		
		JLabel lblStates = new JLabel("States:");
		lblStates.setFont(new Font("Calibri", Font.BOLD, 12));
		lblStates.setBounds(8, 84, 46, 14);
		frame.getContentPane().add(lblStates);
		
		JLabel lblName_1 = new JLabel("Name");
		lblName_1.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblName_1.setBounds(8, 134, 46, 14);
		frame.getContentPane().add(lblName_1);
		
		txtStateName = new JTextField();
		txtStateName.setBounds(100, 131, 115, 20);
		frame.getContentPane().add(txtStateName);
		txtStateName.setColumns(10);			
				
		JLabel lblComponent = new JLabel("Component");
		lblComponent.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblComponent.setBounds(8, 109, 89, 14);
		frame.getContentPane().add(lblComponent);
				
		
		
		JLabel lblTransitions = new JLabel("Transitions:");
		lblTransitions.setFont(new Font("Calibri", Font.BOLD, 12));
		lblTransitions.setBounds(8, 178, 89, 14);
		frame.getContentPane().add(lblTransitions);
		
		txtTransName = new JTextField();
		txtTransName.setColumns(10);
		txtTransName.setBounds(100, 198, 252, 20);
		frame.getContentPane().add(txtTransName);
				
		
				
		cbEvents.setBounds(100, 368, 252, 20);
		frame.getContentPane().add(cbEvents);
		
		JLabel label = new JLabel("Event");
		label.setFont(new Font("Calibri", Font.PLAIN, 11));
		label.setForeground(Color.BLACK);
		label.setBounds(8, 371, 28, 14);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Action");
		label_1.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_1.setForeground(Color.BLACK);
		label_1.setBounds(8, 327, 30, 12);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Condition");
		label_2.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_2.setForeground(Color.BLACK);
		label_2.setBounds(8, 285, 45, 12);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("State to");
		label_3.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_3.setForeground(Color.BLACK);
		label_3.setBounds(8, 254, 39, 14);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("State from");
		label_4.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_4.setForeground(Color.BLACK);
		label_4.setBounds(8, 229, 51, 14);
		frame.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("Name");
		label_5.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_5.setForeground(Color.BLACK);
		label_5.setBounds(8, 204, 27, 14);
		frame.getContentPane().add(label_5);
		
		JTree tree = new JTree();
		tree.setBounds(8, 552, 340, 178);
		frame.getContentPane().add(tree);
		
		final JTextArea txtCondition = new JTextArea();
		txtCondition.setBounds(100, 279, 252, 36);
		frame.getContentPane().add(txtCondition);
		
		final JTextArea txtAction = new JTextArea();
		txtAction.setBounds(100, 321, 252, 36);
		frame.getContentPane().add(txtAction);
		
		JButton btnAddTransition = new JButton("Add Transition");
		btnAddTransition.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnAddTransition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnectDatabase cdb = new ConnectDatabase();
		        System.out.println("After Connect Database!");
		        int StateFromID = ((comboItem)cbStateFrom.getSelectedItem()).value;
		        int StateToID = ((comboItem)cbStateTo.getSelectedItem()).value;
		        int EventID = ((comboItem)cbEvents.getSelectedItem()).value;
		     
		        try {
		            int val = cdb.st.executeUpdate("INSERT INTO transitions (Name, StateFrom, StateTo, Condition, Action, EventID) VALUES ('"+txtTransName.getText()+"',"+Integer.toString(StateFromID)+","+Integer.toString(StateToID)+",'"+txtCondition.getText()+"','"+txtAction.getText()+"',"+Integer.toString(EventID)+")");
		            System.out.println("1 row affected");		           
		        } catch (SQLException ex) {
		        	System.out.println("SQL statement is not executed!"+ex);
		        }
		        getData();
			}
		});
		btnAddTransition.setBounds(231, 391, 115, 23);
		frame.getContentPane().add(btnAddTransition);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(18, 71, 328, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(18, 162, 328, 2);
		frame.getContentPane().add(separator_1);
		
		JLabel lblCompo = new JLabel("Component");
		lblCompo.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblCompo.setBounds(8, 495, 76, 14);
		frame.getContentPane().add(lblCompo);
		
		JComboBox cbHardwareItemComponent = new JComboBox();
		cbHardwareItemComponent.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbHardwareItemComponent.setBounds(100, 492, 252, 20);
		frame.getContentPane().add(cbHardwareItemComponent);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Calibri", Font.PLAIN, 11));
		//btnAdd.setBounds(126, 523, 89, 23);
		frame.getContentPane().add(btnAdd);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(24, 429, 328, 2);
		frame.getContentPane().add(separator_2);
		
		JLabel lblCoupling = new JLabel("Coupling Hardware Item and Components:");
		lblCoupling.setFont(new Font("Calibri", Font.BOLD, 12));
		lblCoupling.setBounds(8, 436, 259, 14);
		frame.getContentPane().add(lblCoupling);
		JButton btnAddState = new JButton("Add state");
		btnAddState.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnAddState.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnectDatabase cdb = new ConnectDatabase();
		        System.out.println("After Connect Database!");
		        int CompID = ((comboItem)cbComponent.getSelectedItem()).value;
		     
		        try {
		            int val = cdb.st.executeUpdate("INSERT INTO comp_states (ComponentID, Name) VALUES ("+Integer.toString(CompID)+",'"+txtStateName.getText()+"')");
		            System.out.println("1 row affected state " + txtStateName.getText());		           
		        } catch (SQLException ex) {
		        	System.out.println("SQL statement is not executed!"+ex);
		        }
		        getData();
		        comboItem  cItem1 = new comboItem(txtStateName.getText(), -1);
		        comboBox.addItem(cItem1);
		        
			}
		});
		btnAddState.setBounds(231, 130, 115, 23);
		frame.getContentPane().add(btnAddState);
	}
	
	
}
