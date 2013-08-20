import java.awt.EventQueue;

import javax.swing.ComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.event.ListDataListener;

import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

class comboItem  
{  
  String name;  
  int value;  
  public comboItem(String n, int v)  
  {  
    name = n; value = v;  
  } 
  public String toString(){return name;} 
    
}

public class Sentences {

	private JFrame frmTellMeA;	
	JComboBox cbSubject = new JComboBox();
	JComboBox cbVerb = new JComboBox();
	JComboBox cbObject = new JComboBox();
	JComboBox cbLocation = new JComboBox();
	 
	
	private void getData(){
		try {
			  cbSubject.removeAllItems();
			  cbObject.setFont(new Font("Calibri", Font.PLAIN, 11));
			  cbObject.removeAllItems();
			  cbVerb.setFont(new Font("Calibri", Font.PLAIN, 11));
			  cbVerb.removeAllItems();
			  cbLocation.setFont(new Font("Calibri", Font.PLAIN, 11));
			  cbLocation.removeAllItems();
			  
			  ConnectDatabase cdb = new ConnectDatabase();
		        
		      ResultSet result = cdb.st.executeQuery("SELECT idsubjects, SubjectName FROM subjects");        
		     
		      
		      List<comboItem> subjects = new ArrayList<comboItem>();
		      int i = 0;
		 	  while(result.next()) { // process results one row at a time		        		       
		        String s = result.getString("SubjectName");
		        int id = result.getInt("idsubjects");		        
		        comboItem cItem = new comboItem(s, id);
		        subjects.add(cItem);
		        //System.out.println(subjects[i].name);
		        cbObject.addItem(s.trim());
		        i++;
		      }
		 	 		 	  
		 	 comboItem [] subjectArray = subjects.toArray( new comboItem[subjects.size()]); 
		 	 System.out.print("test subjects3");
		 	 cbSubject = new JComboBox(subjectArray);
		 	 cbSubject.setFont(new Font("Calibri", Font.PLAIN, 11));
		      		      
		      result = cdb.st.executeQuery("SELECT EventName FROM events");         
			  while(result.next()) { // process results one row at a time		        		       
		        String s = result.getString("EventName");
		        cbVerb.addItem(s.trim());
		      }

			  result = cdb.st.executeQuery("SELECT PlaceName FROM places");         
			  while(result.next()) { // process results one row at a time		        		       
		        String s = result.getString("PlaceName");
		        cbLocation.addItem(s.trim());
		      }
		    
			  
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
					Sentences window = new Sentences();
					window.frmTellMeA.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Sentences() {
		getData();
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTellMeA = new JFrame();		
		frmTellMeA.setTitle("Tell me a story!");
		frmTellMeA.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				//getData();
			}
		});
		
		frmTellMeA.getContentPane().setBackground(Color.GRAY);
		frmTellMeA.setBounds(100, 100, 523, 605);
		frmTellMeA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTellMeA.getContentPane().setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(26, 336, 471, 220);
		frmTellMeA.getContentPane().add(textPane);
		
		JLabel label = new JLabel("Agent/Prop/Participant");
		label.setFont(new Font("Calibri", Font.PLAIN, 11));
		label.setForeground(Color.WHITE);
		label.setBounds(26, 28, 155, 14);
		frmTellMeA.getContentPane().add(label);
		
		JButton button = new JButton("Add new");
		button.setFont(new Font("Calibri", Font.PLAIN, 11));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefineSubject defSubject = new DefineSubject();		
				String[] args = new String[0];
				defSubject.main(args);
			}
		});
		button.setForeground(Color.WHITE);
		button.setBackground(Color.GRAY);
		button.setBounds(178, 24, 89, 23);
		frmTellMeA.getContentPane().add(button);
		
		JLabel label_1 = new JLabel("Place");
		label_1.setFont(new Font("Calibri", Font.PLAIN, 12));
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(26, 62, 46, 14);
		frmTellMeA.getContentPane().add(label_1);
		
		JButton button_1 = new JButton("Add new");
		button_1.setFont(new Font("Calibri", Font.PLAIN, 11));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefinePlace defPlace = new DefinePlace();		
				String[] args = new String[0];
				defPlace.main(args);
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setBackground(Color.GRAY);
		button_1.setBounds(178, 58, 89, 23);
		frmTellMeA.getContentPane().add(button_1);
		
		JLabel label_2 = new JLabel("Event");
		label_2.setFont(new Font("Calibri", Font.PLAIN, 12));
		label_2.setForeground(Color.WHITE);
		label_2.setBounds(26, 96, 46, 14);
		frmTellMeA.getContentPane().add(label_2);
		
		JButton button_2 = new JButton("Add new");
		button_2.setFont(new Font("Calibri", Font.PLAIN, 11));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefineEvent defEvent = new DefineEvent();		
				String[] args = new String[0];
				defEvent.main(args);;
			}
		});
		button_2.setForeground(Color.WHITE);
		button_2.setBackground(Color.GRAY);
		button_2.setBounds(178, 92, 89, 23);
		frmTellMeA.getContentPane().add(button_2);
		
		JLabel label_3 = new JLabel("Sentences:");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Calibri", Font.BOLD, 13));
		label_3.setBounds(26, 136, 89, 14);
		frmTellMeA.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Subject");
		label_4.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_4.setForeground(Color.WHITE);
		label_4.setBounds(26, 161, 46, 14);
		frmTellMeA.getContentPane().add(label_4);
		
		cbSubject.setForeground(Color.WHITE);
		cbSubject.setBackground(Color.GRAY);
		cbSubject.setBounds(26, 192, 145, 20);
		frmTellMeA.getContentPane().add(cbSubject);
		
		JLabel label_5 = new JLabel("Verb");
		label_5.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_5.setForeground(Color.WHITE);
		label_5.setBounds(191, 161, 46, 14);
		frmTellMeA.getContentPane().add(label_5);
				
		cbVerb.setBackground(Color.GRAY);
		cbVerb.setBounds(191, 192, 145, 20);
		frmTellMeA.getContentPane().add(cbVerb);
				
		cbObject.setBackground(Color.GRAY);
		cbObject.setBounds(356, 192, 141, 20);
		frmTellMeA.getContentPane().add(cbObject);
		
		JLabel label_6 = new JLabel("Object");
		label_6.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_6.setForeground(Color.WHITE);
		label_6.setBounds(356, 161, 46, 14);
		frmTellMeA.getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("Location");
		label_7.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_7.setForeground(Color.WHITE);
		label_7.setBounds(26, 223, 46, 14);
		frmTellMeA.getContentPane().add(label_7);
				
		cbLocation.setForeground(Color.WHITE);
		cbLocation.setBackground(Color.GRAY);
		cbLocation.setBounds(26, 248, 145, 20);
		frmTellMeA.getContentPane().add(cbLocation);
		
		JButton button_3 = new JButton("Add new");
		button_3.setFont(new Font("Calibri", Font.PLAIN, 11));
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnectDatabase cdb = new ConnectDatabase();
		        System.out.println("After Connect Database!");
		        int SubjectID = ((comboItem)cbSubject.getSelectedItem()).value;
		        
		        try {
		            int val = cdb.st.executeUpdate("INSERT INTO sentences (SubjectID, VerbID) VALUES ("+Integer.toString(SubjectID)+","+Integer.toString(SubjectID)+")");
		            System.out.println("1 row affected");		           
		        } catch (SQLException ex) {
		        	System.out.println("SQL statement is not executed!"+ex);
		        }
			}
		});
		
		button_3.setForeground(Color.WHITE);
		button_3.setBackground(Color.GRAY);
		button_3.setBounds(393, 305, 89, 23);
		frmTellMeA.getContentPane().add(button_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(Color.WHITE);
		comboBox.setFont(new Font("Calibri", Font.PLAIN, 11));
		comboBox.setBackground(Color.GRAY);
		comboBox.setBounds(356, 248, 141, 20);
		frmTellMeA.getContentPane().add(comboBox);
		
		JLabel label_8 = new JLabel("Location");
		label_8.setForeground(Color.WHITE);
		label_8.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_8.setBounds(356, 223, 46, 14);
		frmTellMeA.getContentPane().add(label_8);
	}
	
	
}
