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
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

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
	JTextPane textPane = new JTextPane();
	private JTextField txtSubjectName;
	private JTextField txtPlaceName;
	private JTextField txtEventName;
	private JTextField txtSeqName;
	private JTextField txtConName;
	private int TypeSub=3;
	private int TypeEvent=3;
	
	
	private void getData(){
		try {			  
			  cbSubject.setFont(new Font("Calibri", Font.PLAIN, 11));
			  cbSubject.removeAllItems();
			  cbObject.setFont(new Font("Calibri", Font.PLAIN, 11));
			  cbObject.removeAllItems();			 
			  cbVerb.setFont(new Font("Calibri", Font.PLAIN, 11));
			  cbVerb.removeAllItems();
			  cbLocation.setFont(new Font("Calibri", Font.PLAIN, 11));
			  cbLocation.removeAllItems();
			  cbLocationObject.removeAllItems();
			  			  
			  ConnectDatabase cdb = new ConnectDatabase();
		        
		      ResultSet result = cdb.st.executeQuery("SELECT idsubjects, SubjectName FROM subjects");
		      List<comboItem> subjects = new ArrayList<comboItem>();
		      String s = "";
		      int id = -1;	
		      comboItem cItem = new comboItem(s, id);
		      cbSubject.addItem(cItem);
		      cbObject.addItem(cItem);
		 	  while(result.next()) { 		        		       
		        s = result.getString("SubjectName");
		        id = result.getInt("idsubjects");		        
		        cItem = new comboItem(s, id);
		        cbSubject.addItem(cItem);
		        cbObject.addItem(cItem);
		      }
		 	 		      		      
		      result = cdb.st.executeQuery("SELECT idevents, EventName FROM events");		      
		      List<comboItem> events = new ArrayList<comboItem>();
		      s = "";
		      id = -1;	
		      cItem = new comboItem(s, id);
		      cbVerb.addItem(cItem);
			  while(result.next()) { // process results one row at a time		        		       
		        s = result.getString("EventName");
		        id = result.getInt("idevents");		        
		        cItem = new comboItem(s, id);
		        cbVerb.addItem(cItem);
		      }			  
			  //comboItem [] eventArray = events.toArray( new comboItem[events.size()]);
			  //cbVerb = new JComboBox(eventArray);			  

			  result = cdb.st.executeQuery("SELECT idplaces, PlaceName FROM places");
			  List<comboItem> places = new ArrayList<comboItem>();
			  s = "";
		      id = -1;	
		      cItem = new comboItem(s, id);
		      cbLocation.addItem(cItem);
		      cbLocationObject.addItem(cItem);
			  while(result.next()) { // process results one row at a time		        		       
		        s = result.getString("PlaceName");
		        id = result.getInt("idplaces");
		        cItem = new comboItem(s, id);
		        cbLocation.addItem(cItem);
		        cbLocationObject.addItem(cItem);
		      }
			  //comboItem [] placeArray = places.toArray( new comboItem[places.size()]);
			  //cbLocation = new JComboBox(placeArray);			  
			  //cbLocationObject = new JComboBox(placeArray);			  
		      cdb.st.close();	
		    }
		    catch( Exception e ) {
		      e.printStackTrace();
		}
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
		initialize();		
//		GetDataSentences gds = new GetDataSentences();
//		gds.getData();
//		System.out.println(gds.subjectArray[2].name);
//		DefaultComboBoxModel<comboItem> modelComp = new DefaultComboBoxModel<comboItem>(gds.subjectArray);
//		cbSubject = new JComboBox<comboItem>(modelComp);
//		cbSubject.setBounds(6, 97, 145, 20);
//		frmTellMeA.getContentPane().add(cbSubject);
//		
		getData();
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
		frmTellMeA.setBounds(100, 100, 1155, 598);
		frmTellMeA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTellMeA.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Agent/Prop/Participant");
		label.setFont(new Font("Calibri", Font.PLAIN, 11));
		label.setForeground(Color.BLACK);
		label.setBounds(6, 615, 155, 14);
		frmTellMeA.getContentPane().add(label);
		
		JButton button = new JButton("Add new");
		button.setFont(new Font("Calibri", Font.PLAIN, 11));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefineSubject defSubject = new DefineSubject();		
				String[] args = new String[0];
				defSubject.main(args);
				getData();
			}
		});		
		button.setBounds(158, 611, 89, 23);
		frmTellMeA.getContentPane().add(button);
		
		JLabel label_1 = new JLabel("Place");
		label_1.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_1.setForeground(Color.BLACK);
		label_1.setBounds(6, 649, 46, 14);
		frmTellMeA.getContentPane().add(label_1);
		
		JButton button_1 = new JButton("Add new");
		button_1.setFont(new Font("Calibri", Font.PLAIN, 11));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefinePlace defPlace = new DefinePlace();		
				String[] args = new String[0];
				defPlace.main(args);
				getData();
			}
		});
		button_1.setBounds(158, 645, 89, 23);
		frmTellMeA.getContentPane().add(button_1);
		
		JLabel label_2 = new JLabel("Event");
		label_2.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_2.setForeground(Color.BLACK);
		label_2.setBounds(6, 683, 46, 14);
		frmTellMeA.getContentPane().add(label_2);
		
		JButton button_2 = new JButton("Add new");
		button_2.setFont(new Font("Calibri", Font.PLAIN, 11));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefineEvent defEvent = new DefineEvent();		
				String[] args = new String[0];
				defEvent.main(args);
				getData();
			}
		});
		
		button_2.setBounds(158, 679, 89, 23);
		frmTellMeA.getContentPane().add(button_2);
		
		JLabel label_3 = new JLabel("Sentences:");
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("Calibri", Font.BOLD, 13));
		label_3.setBounds(6, 65, 89, 14);
		frmTellMeA.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Subject");
		label_4.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_4.setForeground(Color.BLACK);
		label_4.setBounds(6, 90, 46, 14);
		frmTellMeA.getContentPane().add(label_4);
				
		//cbSubject.setBounds(6, 97, 145, 20);
		//frmTellMeA.getContentPane().add(cbSubject);
		
		JLabel label_5 = new JLabel("Verb");
		label_5.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_5.setForeground(Color.BLACK);
		label_5.setBounds(171, 90, 46, 14);
		frmTellMeA.getContentPane().add(label_5);
		
		cbSubject.setBounds(6, 116, 145, 20);
		frmTellMeA.getContentPane().add(cbSubject);
						
		cbVerb.setBounds(171, 116, 145, 20);
		frmTellMeA.getContentPane().add(cbVerb);
						
		cbObject.setBounds(336, 116, 141, 20);
		frmTellMeA.getContentPane().add(cbObject);
		
		JLabel label_6 = new JLabel("Object");
		label_6.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_6.setForeground(Color.BLACK);
		label_6.setBounds(336, 90, 46, 14);
		frmTellMeA.getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("Location");
		label_7.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_7.setForeground(Color.BLACK);
		label_7.setBounds(6, 138, 46, 14);
		frmTellMeA.getContentPane().add(label_7);
				
		cbLocation.setBounds(6, 156, 145, 20);
		frmTellMeA.getContentPane().add(cbLocation);
		
		
		textPane.setFont(new Font("Calibri", Font.PLAIN, 11));
		textPane.setEditable(false);
		//textPane.setBounds(26, 339, 471, 195);
		JScrollPane sp = new JScrollPane(textPane);
		sp.setBounds(6, 227, 471, 295);
		frmTellMeA.getContentPane().add(sp);
		
		JButton btnAddSentence = new JButton("Add sentence");
		btnAddSentence.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnAddSentence.addActionListener(new ActionListener() {
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
		        getData();
			}
		});
				
		btnAddSentence.setBounds(368, 184, 109, 23);
		frmTellMeA.getContentPane().add(btnAddSentence);		
				
		cbLocationObject.setFont(new Font("Calibri", Font.PLAIN, 11));		
		cbLocationObject.setBounds(336, 156, 141, 20);
		frmTellMeA.getContentPane().add(cbLocationObject);
		
		JLabel label_8 = new JLabel("Location");
		label_8.setForeground(Color.BLACK);
		label_8.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_8.setBounds(336, 138, 46, 14);
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
		btnNewButton.setBounds(257, 611, 121, 23);
		frmTellMeA.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Define Events");
		btnNewButton_1.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Agents events = new Agents();		
				String[] args = new String[0];
				events.main(args);
			}
		});
		btnNewButton_1.setBounds(657, 329, 104, 23);
		frmTellMeA.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Define agent");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AgentItems agentItems = new AgentItems();		
				String[] args = new String[0];
				agentItems.main(args);
			}
		});
		btnNewButton_2.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton_2.setBounds(657, 98, 104, 23);
		frmTellMeA.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Define Place");
		btnNewButton_3.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton_3.setBounds(656, 184, 104, 23);
		frmTellMeA.getContentPane().add(btnNewButton_3);
		
		JRadioButton rdbtnMust = new JRadioButton("MUST");
		rdbtnMust.setFont(new Font("Calibri", Font.PLAIN, 11));
		rdbtnMust.setBounds(400, 611, 63, 23);
		frmTellMeA.getContentPane().add(rdbtnMust);
		
		JRadioButton rdbtnMay = new JRadioButton("MAY");
		rdbtnMay.setFont(new Font("Calibri", Font.PLAIN, 11));
		rdbtnMay.setBounds(400, 645, 109, 23);
		frmTellMeA.getContentPane().add(rdbtnMay);
		
		JRadioButton rdbtnForbidden = new JRadioButton("FORBIDDEN");
		rdbtnForbidden.setFont(new Font("Calibri", Font.PLAIN, 11));
		rdbtnForbidden.setBounds(400, 671, 109, 23);
		frmTellMeA.getContentPane().add(rdbtnForbidden);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(505, 127, 369, 2);
		frmTellMeA.getContentPane().add(separator);
		
		JButton btnCreateSequence = new JButton("New sequence");
		btnCreateSequence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sequence sequence = new Sequence();
				String[] args = new String[0];
				sequence.main(args);
			}
		});
		btnCreateSequence.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnCreateSequence.setBounds(257, 640, 121, 23);
		frmTellMeA.getContentPane().add(btnCreateSequence);
		
		JButton btnNewButton_4 = new JButton("Define seq");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateSequence upd_sequence = new UpdateSequence();
				String[] args = new String[0];
				upd_sequence.main(args);
			}
		});
		btnNewButton_4.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton_4.setBounds(657, 434, 104, 23);
		frmTellMeA.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("New concurrence");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Concurrent concurrent = new Concurrent();
				String[] args = new String[0];
				concurrent.main(args);
			}
		});
		btnNewButton_5.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton_5.setBounds(257, 674, 121, 23);
		frmTellMeA.getContentPane().add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Define conc");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdateConcurrence upd_concurrent = new UpdateConcurrence();
				String[] args = new String[0];
				upd_concurrent.main(args);
			}
		});
		btnNewButton_6.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton_6.setBounds(657, 516, 104, 23);
		frmTellMeA.getContentPane().add(btnNewButton_6);
		
		JLabel label_9 = new JLabel("Name");		
		label_9.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_9.setBounds(505, 33, 46, 14);
		frmTellMeA.getContentPane().add(label_9);
		
		txtSubjectName = new JTextField();
		txtSubjectName.setColumns(10);
		txtSubjectName.setBounds(542, 29, 311, 20);
		frmTellMeA.getContentPane().add(txtSubjectName);
		
		JLabel label_10 = new JLabel("Type");		
		label_10.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_10.setBounds(506, 55, 24, 14);
		frmTellMeA.getContentPane().add(label_10);
		
		final JRadioButton rbParticipant = new JRadioButton("Participant");		
		rbParticipant.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				TypeSub = 0;
			}
		});
		rbParticipant.setFont(new Font("Calibri", Font.PLAIN, 11));
		rbParticipant.setBounds(542, 54, 106, 23);
		frmTellMeA.getContentPane().add(rbParticipant);
		
		final JRadioButton rbAgent = new JRadioButton("Agent");
		rbAgent.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				TypeSub = 1;
			}
		});
		rbAgent.setFont(new Font("Calibri", Font.PLAIN, 11));
		
		rbAgent.setBounds(542, 72, 55, 23);
		frmTellMeA.getContentPane().add(rbAgent);
		
		final JRadioButton rbProp = new JRadioButton("Prop");
		rbProp.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				TypeSub = 2;
			}
		});
		rbProp.setFont(new Font("Calibri", Font.PLAIN, 11));
			rbProp.setBounds(542, 90, 89, 23);
		frmTellMeA.getContentPane().add(rbProp);
		
		 //Group the radio buttons.
	    final ButtonGroup TypeSubject = new ButtonGroup();
	    TypeSubject.add(rbParticipant);
	    TypeSubject.add(rbAgent);
	    TypeSubject.add(rbProp);
		
		JButton btnAddSubject = new JButton("Add agent");
		btnAddSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (TypeSubject.getSelection() != null )
				{
					if (txtSubjectName.getText().equals("")){JOptionPane.showMessageDialog(null, "Please enter a name!");}
					else {						
						ConnectDatabase cdb = new ConnectDatabase();
						System.out.println("After Connect Database!");		        
						try {
							int val = cdb.st.executeUpdate("INSERT INTO subjects (SubjectName, SubjectTypesID) VALUES ('"+txtSubjectName.getText()+"',"+TypeSub+")");
							System.out.println("1 row affected");		           
						} catch (SQLException ex) {
							System.out.println("SQL statement is not executed!"+ex);
						} 
						getData();						
						TypeSubject.clearSelection();
						txtSubjectName.setText("");
					}
				}
				else {JOptionPane.showMessageDialog(null, "Please choose a type of subject!");}
			}
		});
		btnAddSubject.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnAddSubject.setBounds(764, 98, 89, 23);
		frmTellMeA.getContentPane().add(btnAddSubject);
		
		JLabel label_11 = new JLabel("Name");		
		label_11.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_11.setBounds(505, 156, 35, 14);
		frmTellMeA.getContentPane().add(label_11);
		
		txtPlaceName = new JTextField();
		txtPlaceName.setColumns(10);
		txtPlaceName.setBounds(542, 152, 311, 20);
		frmTellMeA.getContentPane().add(txtPlaceName);
		
		JButton btnAddPlace = new JButton("Add place");		
		btnAddPlace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtPlaceName.getText().equals("")){JOptionPane.showMessageDialog(null, "Please enter a name!");}
				else {	
					ConnectDatabase cdb = new ConnectDatabase();
					System.out.println("After Connect Database!");
					try {
						int val = cdb.st.executeUpdate("INSERT INTO places (PlaceName) VALUES ('"+txtPlaceName.getText()+"')");
						System.out.println("1 row affected");		           
					} catch (SQLException ex) {
						System.out.println("SQL statement is not executed!"+ex);
					} 
					getData();
					txtPlaceName.setText("");
				}
			}
		});
		btnAddPlace.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnAddPlace.setBounds(764, 184, 89, 23);
		frmTellMeA.getContentPane().add(btnAddPlace);
		
		JLabel lblNewLabel = new JLabel("Place");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 11));
		lblNewLabel.setBounds(505, 137, 46, 14);
		frmTellMeA.getContentPane().add(lblNewLabel);
		
		JLabel lblSubject = new JLabel("Characters");
		lblSubject.setFont(new Font("Calibri", Font.BOLD, 11));
		lblSubject.setBounds(505, 8, 126, 14);
		frmTellMeA.getContentPane().add(lblSubject);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(505, 218, 369, 2);
		frmTellMeA.getContentPane().add(separator_1);
		
		JLabel label_12 = new JLabel("Event");		
		label_12.setFont(new Font("Calibri", Font.BOLD, 11));
		label_12.setBounds(505, 227, 35, 15);
		frmTellMeA.getContentPane().add(label_12);
		
		JLabel label_13 = new JLabel("Name");		
		label_13.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_13.setBounds(505, 253, 44, 14);
		frmTellMeA.getContentPane().add(label_13);
		
		txtEventName = new JTextField();
		txtEventName.setColumns(10);
		txtEventName.setBounds(542, 249, 311, 20);
		frmTellMeA.getContentPane().add(txtEventName);
		
		JLabel label_14 = new JLabel("Produced by:");		
		label_14.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_14.setBounds(505, 278, 115, 14);
		frmTellMeA.getContentPane().add(label_14);
		
		JRadioButton rbUser = new JRadioButton("User");		
		rbUser.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				TypeEvent = 0;
			}
		});
		rbUser.setFont(new Font("Calibri", Font.PLAIN, 11));
		rbUser.setBounds(542, 290, 93, 23);
		frmTellMeA.getContentPane().add(rbUser);
		
		JRadioButton rbInterEnv = new JRadioButton("Interactive environment");
		rbInterEnv.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				TypeEvent = 1;
			}
		});
		rbInterEnv.setFont(new Font("Calibri", Font.PLAIN, 11));
		rbInterEnv.setBounds(542, 308, 149, 23);
		frmTellMeA.getContentPane().add(rbInterEnv);
		
		final ButtonGroup TypeEvents = new ButtonGroup();
		TypeEvents.add(rbUser);
		TypeEvents.add(rbInterEnv);
		
		JButton btnAddEvent = new JButton("Add event");
		btnAddEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (TypeEvents.getSelection() != null )
				{
					if (txtEventName.getText().equals("")){JOptionPane.showMessageDialog(null, "Please enter a name!");}
					else {	
						ConnectDatabase cdb = new ConnectDatabase();
						System.out.println("After Connect Database!");
						try {
							int val = cdb.st.executeUpdate("INSERT INTO events (EventName, EventTypesID) VALUES ('"+txtEventName.getText()+"',"+TypeEvent+")");
							System.out.println("1 row affected");		           
						} catch (SQLException ex) {
							System.out.println("SQL statement is not executed!"+ex);
						} 
						getData();
						TypeEvents.clearSelection();
						txtEventName.setText("");
					}
				}
				else {JOptionPane.showMessageDialog(null, "Please choose a type of events!");}
			}
		});
		btnAddEvent.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnAddEvent.setBounds(764, 329, 89, 23);
		frmTellMeA.getContentPane().add(btnAddEvent);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(505, 360, 369, 2);
		frmTellMeA.getContentPane().add(separator_2);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setFont(new Font("Calibri", Font.BOLD, 11));
		lblTime.setBounds(505, 364, 46, 14);
		frmTellMeA.getContentPane().add(lblTime);
		
		JLabel label_15 = new JLabel("Name");
		label_15.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_15.setBounds(505, 403, 46, 14);
		frmTellMeA.getContentPane().add(label_15);
		
		txtSeqName = new JTextField();
		txtSeqName.setColumns(10);
		txtSeqName.setBounds(542, 403, 311, 20);
		frmTellMeA.getContentPane().add(txtSeqName);
		
		JButton btnAddSequence = new JButton("Add seq");
		btnAddSequence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtSeqName.getText().equals("")){JOptionPane.showMessageDialog(null, "Please enter a name!");}
				else {	
					ConnectDatabase cdb = new ConnectDatabase();
					System.out.println("After Connect Database!");
					int EventID = -1; //((comboItem)cbTriggerEvent.getSelectedItem()).value;		     
					try {
						int val = cdb.st.executeUpdate("INSERT INTO sequences (NameSeq, TriggeredByEvent) VALUES ('"+txtSeqName.getText()+"',"+Integer.toString(EventID)+")");
						System.out.println("1 row affected");		           
					} catch (SQLException ex) {
						System.out.println("SQL statement is not executed!"+ex);
					}
					getData();
					txtSeqName.setText("");
				}
			}
		});
		btnAddSequence.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnAddSequence.setBounds(764, 434, 92, 23);
		frmTellMeA.getContentPane().add(btnAddSequence);
		
		JLabel lblSequence = new JLabel("Sequence");
		lblSequence.setFont(new Font("Calibri", Font.BOLD, 11));
		lblSequence.setBounds(505, 384, 66, 14);
		frmTellMeA.getContentPane().add(lblSequence);
		
		JLabel label_16 = new JLabel("Name");
		label_16.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_16.setBounds(505, 489, 46, 14);
		frmTellMeA.getContentPane().add(label_16);
		
		txtConName = new JTextField();
		txtConName.setColumns(10);
		txtConName.setBounds(542, 485, 311, 20);
		frmTellMeA.getContentPane().add(txtConName);
		
		JButton btnAddCon = new JButton("Add con");
		btnAddCon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtConName.getText().equals("")){JOptionPane.showMessageDialog(null, "Please enter a name!");}
				else {	
					ConnectDatabase cdb = new ConnectDatabase();
					System.out.println("After Connect Database!");
					int EventID = -1; //((comboItem)cbTriggerEvent.getSelectedItem()).value;		     
					try {
						int val = cdb.st.executeUpdate("INSERT INTO concurrences (NameConc, TriggeredByEvent) VALUES ('"+txtConName.getText()+"',"+Integer.toString(EventID)+")");
						System.out.println("1 row affected");		           
					} catch (SQLException ex) {
						System.out.println("SQL statement is not executed!"+ex);
					}
					getData();
					txtConName.setText("");
				}
			}
		});
		btnAddCon.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnAddCon.setBounds(764, 516, 92, 23);
		frmTellMeA.getContentPane().add(btnAddCon);
		
		JLabel lblNewLabel_1 = new JLabel("Concurrence");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 11));
		lblNewLabel_1.setBounds(505, 465, 66, 14);
		frmTellMeA.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Title:");
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 12));
		lblNewLabel_2.setBounds(6, 7, 46, 14);
		frmTellMeA.getContentPane().add(lblNewLabel_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(6, 55, 457, 2);
		frmTellMeA.getContentPane().add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(10, 218, 457, 2);
		frmTellMeA.getContentPane().add(separator_4);
		
		JButton btnNewButton_7 = new JButton("Add Item");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HardwareItem hardItem = new HardwareItem();
				String[] args = new String[0];
				hardItem.main(args);
			}
		});
		btnNewButton_7.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton_7.setBounds(764, 62, 89, 23);
		frmTellMeA.getContentPane().add(btnNewButton_7);
	
	
						
		
		
	}
	
	
}
