import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.ComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

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

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;

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
	JComboBox cbLocationObject = new JComboBox();	
	
	
	private void getData(){
		try {			  
			  cbSubject.removeAllItems();
			  cbObject.removeAllItems();			 
			  cbVerb.removeAllItems();
			  cbLocation.removeAllItems();
			  cbLocationObject.removeAllItems();
			  			  
			  ConnectDatabase cdb = new ConnectDatabase();
		        
		      ResultSet result = cdb.st.executeQuery("SELECT idsubjects, SubjectName FROM subjects");        
		     		      
		      List<comboItem> subjects = new ArrayList<comboItem>();
		      String s = "";
		      int id = -1;	
		      comboItem cItem = new comboItem(s, id);
		      subjects.add(cItem);
		 	  while(result.next()) { // process results one row at a time		        		       
		        s = result.getString("SubjectName");
		        id = result.getInt("idsubjects");		        
		        cItem = new comboItem(s, id);
		        subjects.add(cItem);		        
		      }		 	 		 	  
		 	  comboItem [] subjectArray = subjects.toArray( new comboItem[subjects.size()]);		 	 
		 	  cbSubject = new JComboBox(subjectArray);
		 	  cbSubject.setFont(new Font("Calibri", Font.PLAIN, 11));
		 	  cbObject = new JComboBox(subjectArray);
		 	  cbObject.setFont(new Font("Calibri", Font.PLAIN, 11));
		 	 		      		      
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
			  cbVerb = new JComboBox(eventArray);
			  cbVerb.setFont(new Font("Calibri", Font.PLAIN, 11));

			  result = cdb.st.executeQuery("SELECT idplaces, PlaceName FROM places");
			  List<comboItem> places = new ArrayList<comboItem>();
			  s = "";
		      id = -1;	
		      cItem = new comboItem(s, id);
		      places.add(cItem);
			  while(result.next()) { // process results one row at a time		        		       
		        s = result.getString("PlaceName");
		        id = result.getInt("idplaces");
		        cItem = new comboItem(s, id);
		        places.add(cItem);
		      }
			  comboItem [] placeArray = places.toArray( new comboItem[places.size()]);
			  cbLocation = new JComboBox(placeArray);
			  cbLocation.setFont(new Font("Calibri", Font.PLAIN, 11));
			  cbLocationObject = new JComboBox(placeArray);
			  
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
		System.out.println("INIT 1");
		frmTellMeA = new JFrame();		
		frmTellMeA.setTitle("Tell me a story!");
		frmTellMeA.addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent arg0) {
				//getData();
			}
		});
		
		//frmTellMeA.getContentPane().setBackground(Color.GRAY);
		frmTellMeA.setBounds(100, 100, 526, 751);
		frmTellMeA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTellMeA.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Agent/Prop/Participant");
		label.setFont(new Font("Calibri", Font.PLAIN, 11));
		label.setForeground(Color.BLACK);
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
		button.setBounds(178, 24, 89, 23);
		frmTellMeA.getContentPane().add(button);
		
		JLabel label_1 = new JLabel("Place");
		label_1.setFont(new Font("Calibri", Font.PLAIN, 12));
		label_1.setForeground(Color.BLACK);
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
		button_1.setBounds(178, 58, 89, 23);
		frmTellMeA.getContentPane().add(button_1);
		
		JLabel label_2 = new JLabel("Event");
		label_2.setFont(new Font("Calibri", Font.PLAIN, 12));
		label_2.setForeground(Color.BLACK);
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
		
		button_2.setBounds(178, 92, 89, 23);
		frmTellMeA.getContentPane().add(button_2);
		
		JLabel label_3 = new JLabel("Sentences:");
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("Calibri", Font.BOLD, 13));
		label_3.setBounds(26, 136, 89, 14);
		frmTellMeA.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Subject");
		label_4.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_4.setForeground(Color.BLACK);
		label_4.setBounds(26, 161, 46, 14);
		frmTellMeA.getContentPane().add(label_4);
				
		cbSubject.setBounds(26, 192, 145, 20);
		frmTellMeA.getContentPane().add(cbSubject);
		
		JLabel label_5 = new JLabel("Verb");
		label_5.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_5.setForeground(Color.BLACK);
		label_5.setBounds(191, 161, 46, 14);
		frmTellMeA.getContentPane().add(label_5);
						
		cbVerb.setBounds(191, 192, 145, 20);
		frmTellMeA.getContentPane().add(cbVerb);
						
		cbObject.setBounds(356, 192, 141, 20);
		frmTellMeA.getContentPane().add(cbObject);
		
		JLabel label_6 = new JLabel("Object");
		label_6.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_6.setForeground(Color.BLACK);
		label_6.setBounds(356, 161, 46, 14);
		frmTellMeA.getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("Location");
		label_7.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_7.setForeground(Color.BLACK);
		label_7.setBounds(26, 223, 46, 14);
		frmTellMeA.getContentPane().add(label_7);
				
		cbLocation.setBounds(26, 248, 145, 20);
		frmTellMeA.getContentPane().add(cbLocation);
		
		JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Calibri", Font.PLAIN, 11));
		textPane.setEditable(false);
		textPane.setBounds(26, 339, 471, 195);
		frmTellMeA.getContentPane().add(textPane);
		
		JButton button_3 = new JButton("Add new");
		button_3.setFont(new Font("Calibri", Font.PLAIN, 11));
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnectDatabase cdb = new ConnectDatabase();
		        System.out.println("After Connect Database!");
		        int SubjectID = ((comboItem)cbSubject.getSelectedItem()).value;
		        int VerbID = ((comboItem)cbVerb.getSelectedItem()).value;
		        int ObjectID = ((comboItem)cbObject.getSelectedItem()).value;
		        int LocationID = ((comboItem)cbLocation.getSelectedItem()).value;
		        int LocationObjectID = ((comboItem)cbLocationObject.getSelectedItem()).value;
		        try {
		            int val = cdb.st.executeUpdate("INSERT INTO sentences (SubjectID, VerbID, ObjectID, LocationID, LocationObjectID) VALUES ("+Integer.toString(SubjectID)+","+Integer.toString(VerbID)+","+Integer.toString(ObjectID)+","+Integer.toString(LocationID)+","+Integer.toString(LocationObjectID)+")");
		            System.out.println("1 row affected");		           
		        } catch (SQLException ex) {
		        	System.out.println("SQL statement is not executed!"+ex);
		        }
		        initialize();
			}
		});
				
		button_3.setBounds(393, 305, 89, 23);
		frmTellMeA.getContentPane().add(button_3);
		
				
		cbLocationObject.setFont(new Font("Calibri", Font.PLAIN, 11));		
		cbLocationObject.setBounds(356, 248, 141, 20);
		frmTellMeA.getContentPane().add(cbLocationObject);
		
		JLabel label_8 = new JLabel("Location");
		label_8.setForeground(Color.BLACK);
		label_8.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_8.setBounds(356, 223, 46, 14);
		frmTellMeA.getContentPane().add(label_8);
		
		JButton btnNewButton = new JButton("Add Time");
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Time time = new Time();		
				String[] args = new String[0];
				time.main(args);
			}
		});
		btnNewButton.setBounds(26, 555, 121, 23);
		frmTellMeA.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Define Events");
		btnNewButton_1.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Events events = new Events();		
				String[] args = new String[0];
				events.main(args);
			}
		});
		btnNewButton_1.setBounds(247, 584, 121, 23);
		frmTellMeA.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Define Agent/Prop");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Agents agent = new Agents();		
				String[] args = new String[0];
				agent.main(args);
			}
		});
		btnNewButton_2.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton_2.setBounds(247, 555, 121, 23);
		frmTellMeA.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Define Place");
		btnNewButton_3.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton_3.setBounds(378, 555, 104, 23);
		frmTellMeA.getContentPane().add(btnNewButton_3);
		
		JRadioButton rdbtnMust = new JRadioButton("MUST");
		rdbtnMust.setFont(new Font("Calibri", Font.PLAIN, 11));
		rdbtnMust.setBounds(26, 277, 63, 23);
		frmTellMeA.getContentPane().add(rdbtnMust);
		
		JRadioButton rdbtnMay = new JRadioButton("MAY");
		rdbtnMay.setFont(new Font("Calibri", Font.PLAIN, 11));
		rdbtnMay.setBounds(26, 305, 109, 23);
		frmTellMeA.getContentPane().add(rdbtnMay);
		
		JRadioButton rdbtnForbidden = new JRadioButton("FORBIDDEN");
		rdbtnForbidden.setFont(new Font("Calibri", Font.PLAIN, 11));
		rdbtnForbidden.setBounds(91, 276, 109, 23);
		frmTellMeA.getContentPane().add(rdbtnForbidden);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(26, 121, 471, 2);
		frmTellMeA.getContentPane().add(separator);
		
		JButton btnCreateSequence = new JButton("New sequence");
		btnCreateSequence.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnCreateSequence.setBounds(26, 584, 121, 23);
		frmTellMeA.getContentPane().add(btnCreateSequence);
		
		JButton btnNewButton_4 = new JButton("Update sequence");
		btnNewButton_4.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton_4.setBounds(26, 611, 121, 23);
		frmTellMeA.getContentPane().add(btnNewButton_4);
						
		ConnectDatabase cdb = new ConnectDatabase();        
		String Query = "SELECT subjects.SubjectTypesID, subjects.SubjectName, places.PlaceName, events.EventName, IFNULL(o.SubjectTypesID,'') as objTypesID, IFNULL(o.SubjectName, '') as ObjectName, IFNULL(po.PlaceName, '') as ObjPlaceName from sentences "
			+ " left join subjects on sentences.SubjectID = subjects.idsubjects"
		+ " left join subjects o on sentences.ObjectID = o.idsubjects"
		+ " left join events  on sentences.VerbID = events.idevents"
		+ " left join places on sentences.LocationID = places.idplaces" 
		+ " left join places po on sentences.LocationObjectID = po.idplaces ";
		try	{
			ResultSet result = cdb.st.executeQuery(Query);			  			  
			String SubType, SubName, SubPlaceName, Verb, ObjName, ObjPlaceName, ObjType;			 
			while(result.next()) {
				SubType = result.getString("subjects.SubjectTypesID");
				ObjType = result.getString("objTypesID");
				System.out.println(SubType);
				SubName = result.getString("subjects.SubjectName");
				SubPlaceName = result.getString("places.PlaceName");
				Verb = result.getString("events.EventName");
				ObjName = result.getString("ObjectName");
				ObjPlaceName = result.getString("ObjPlaceName");
				StyledDocument doc = textPane.getStyledDocument();

				try {
					Style style = textPane.addStyle("Style", null);
					if (SubType.equals("0")){
						StyleConstants.setForeground(style, Color.blue);}
					else{StyleConstants.setForeground(style, Color.red);}
					doc.insertString(doc.getLength(), SubName + "   ", style);
					StyleConstants.setForeground(style, Color.orange);
					doc.insertString(doc.getLength(), " (" + SubPlaceName + ") ", style);

					StyleConstants.setForeground(style, Color.gray);
					doc.insertString(doc.getLength(), Verb +"  ", style); 
				
					if (ObjType.equals("0")){
						StyleConstants.setForeground(style, Color.blue);}
					else{StyleConstants.setForeground(style, Color.red);}
					doc.insertString(doc.getLength(), ObjName, style);
				
					StyleConstants.setForeground(style, Color.orange);
					doc.insertString(doc.getLength(), " (" + ObjPlaceName + ") \n", style);				
				}	
				catch (BadLocationException ex){}			
			}
		}
		catch(SQLException ex){System.out.print(ex);}
		
	}
	
	
}
