import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.text.BadLocationException;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Font;
import javax.swing.JTree;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class comboItemSentence  
{  
  String name;  
  int value;  
  public comboItemSentence(String n, int v)  
  {  
    name = n; value = v;  
  } 
  public String toString(){return name;} 
}


public class Time {
	JComboBox cbSecondSentence = new JComboBox();
	JComboBox cbFirstSentence = new JComboBox();
	JComboBox cbEvents = new JComboBox();

	private JFrame frmOnceUponA;
	
	private void getData(){
		cbFirstSentence.removeAll();
		cbSecondSentence.removeAll();
		try	{
			ConnectDatabase cdb = new ConnectDatabase();        
			String Query = "SELECT idSentences, subjects.SubjectTypesID, subjects.SubjectName, places.PlaceName, events.EventName, IFNULL(o.SubjectName, '') as ObjectName, IFNULL(po.PlaceName, '') as ObjPlaceName from sentences "
						+ " left join subjects on sentences.SubjectID = subjects.idsubjects"
						+ " left join subjects o on sentences.ObjectID = o.idsubjects"
						+ " left join events  on sentences.VerbID = events.idevents"
						+ " left join places on sentences.LocationID = places.idplaces" 
						+ " left join places po on sentences.LocationObjectID = po.idplaces ";		
			ResultSet result = cdb.st.executeQuery(Query);			  			  
			String SentenceID, SubType, SubName, SubPlaceName, Verb, ObjName, ObjPlaceName, OneSentence = "";	
			List<comboItemSentence> sentences = new ArrayList<comboItemSentence>();		    
			comboItemSentence cItem = new comboItemSentence("", -1);
		    sentences.add(cItem);
			while(result.next()) {
				SentenceID = result.getString("idSentences");
				SubType = result.getString("subjects.SubjectTypesID");				
				SubName = result.getString("subjects.SubjectName");
				SubPlaceName = result.getString("places.PlaceName");
				Verb = result.getString("events.EventName");
				ObjName = result.getString("ObjectName");
				ObjPlaceName = result.getString("ObjPlaceName");
				OneSentence = SubName + " (" + SubPlaceName + ") " + Verb + " " + ObjName + " (" + ObjPlaceName + ")";
				System.out.println(OneSentence);
			    cItem = new comboItemSentence(OneSentence, Integer.parseInt(SentenceID));
			    sentences.add(cItem);	
			}
			comboItemSentence [] sentenceArray = sentences.toArray( new comboItemSentence[sentences.size()]);	
			System.out.println(sentenceArray[5]);
			cbFirstSentence = new JComboBox(sentenceArray);
			cbFirstSentence.setFont(new Font("Calibri", Font.PLAIN, 11));
			cbSecondSentence = new JComboBox(sentenceArray);
			cbSecondSentence.setFont(new Font("Calibri", Font.PLAIN, 11));
		}
		catch(SQLException ex){System.out.print(ex);}
		
		cbEvents.removeAll();
		try	{
			ConnectDatabase cdb = new ConnectDatabase();        
			String Query = "SELECT idSentences as SenConcID, concat(subjects.SubjectName, places.PlaceName, events.EventName, IFNULL(o.SubjectName, ''),  IFNULL(po.PlaceName, '')) as NameEvConc, 1 as Type " 
						+ " from sentences left join subjects on sentences.SubjectID = subjects.idsubjects "
						+ " left join subjects o on sentences.ObjectID = o.idsubjects "
						+ " left join events  on sentences.VerbID = events.idevents "
						+ " left join places on sentences.LocationID = places.idplaces "
						+ " left join places po on sentences.LocationObjectID = po.idplaces "
						+ " union " 
						+ " select idconcurrences as SenConcID, NameConc as NameEvConc, 2 as Type " 
						+ " from concurrences"	
						+ " union " 
						+ " select idsequence as SenConcID, NameSeq as NameEvConc, 3 as Type " 
						+ " from sequences";
			ResultSet result = cdb.st.executeQuery(Query);			  			  
			String SentenceID, Type, OneSentence = "";	
			List<comboItemSeq> sentences = new ArrayList<comboItemSeq>();		    
			comboItemSeq cItem = new comboItemSeq("", -1, -1);
		    sentences.add(cItem);
			while(result.next()) {
				SentenceID = result.getString("SenConcID");
				OneSentence = result.getString("NameEvConc");				
				Type = result.getString("Type");
			    cItem = new comboItemSeq(OneSentence, Integer.parseInt(SentenceID), Integer.parseInt(Type));
			    sentences.add(cItem);	
			}
			comboItemSeq [] sentenceArray = sentences.toArray( new comboItemSeq[sentences.size()]);	
			System.out.println(sentenceArray[5]);
			cbEvents = new JComboBox(sentenceArray);
			cbEvents.setFont(new Font("Calibri", Font.PLAIN, 11));
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
		getData();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmOnceUponA = new JFrame();
		frmOnceUponA.setTitle("Once upon a TIME...");
		frmOnceUponA.setBounds(100, 100, 709, 709);
		frmOnceUponA.getContentPane().setLayout(null);
		
		JLabel lblBeforeUser = new JLabel("BEFORE (Inicialize environment)");
		lblBeforeUser.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblBeforeUser.setBounds(23, 11, 198, 14);
		frmOnceUponA.getContentPane().add(lblBeforeUser);
		
		JLabel lblInteraction = new JLabel("INTERACTIVE NARRATIVE");
		lblInteraction.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblInteraction.setBounds(23, 176, 159, 14);
		frmOnceUponA.getContentPane().add(lblInteraction);
		
		JLabel lblAfter = new JLabel("AFTER (And they lived happily ever after... or ...)");
		lblAfter.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblAfter.setBounds(23, 429, 314, 14);
		frmOnceUponA.getContentPane().add(lblAfter);
				
		cbFirstSentence.setBounds(33, 477, 309, 20);
		frmOnceUponA.getContentPane().add(cbFirstSentence);
		
		JLabel lblSentence = new JLabel("Sentence");
		lblSentence.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblSentence.setBounds(33, 454, 139, 14);
		frmOnceUponA.getContentPane().add(lblSentence);
		
		JLabel lblTime = new JLabel("Time(Reactive-Feedback, Narrative-Feedforward)");
		lblTime.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblTime.setBounds(33, 518, 272, 14);
		frmOnceUponA.getContentPane().add(lblTime);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Calibri", Font.PLAIN, 11));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"BEFORE", "MEETS", "OVERLAPS", "STARTS", "DURING", "FINISHES", "EQUALS"}));
		comboBox_1.setToolTipText("BEFORE\r\nMEETS\r\nOVERLAPS\r\nSTARTS\r\nDURING\r\nFINISHES\r\nEQUALS");
		comboBox_1.setBounds(33, 545, 180, 20);
		frmOnceUponA.getContentPane().add(comboBox_1);
		
		JLabel label = new JLabel("Sentence");
		label.setFont(new Font("Calibri", Font.PLAIN, 11));
		label.setBounds(33, 586, 128, 14);
		frmOnceUponA.getContentPane().add(label);
				
		cbSecondSentence.setBounds(33, 609, 309, 20);
		frmOnceUponA.getContentPane().add(cbSecondSentence);
		
		JButton btnNewButton = new JButton("Add time");
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton.setBounds(33, 640, 89, 23);
		frmOnceUponA.getContentPane().add(btnNewButton);
		
		JLabel lblSequenceOrConcurrence = new JLabel("Add chronologically sequence/concurrence/event");
		lblSequenceOrConcurrence.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblSequenceOrConcurrence.setBounds(33, 201, 323, 14);
		frmOnceUponA.getContentPane().add(lblSequenceOrConcurrence);
		
		
		cbEvents.setBounds(33, 227, 198, 20);
		frmOnceUponA.getContentPane().add(cbEvents);
		
		JButton btnAddSeqcon = new JButton("Add");
		btnAddSeqcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConnectDatabase cdb = new ConnectDatabase();
		        System.out.println("After Connect Database!");	        
		        int StoryID = 1; //((comboItem)cbEvents.getSelectedItem()).value;
		        int EventConID = ((comboItemSeq)cbEvents.getSelectedItem()).value;
		        int TypeID = ((comboItemSeq)cbEvents.getSelectedItem()).type;	
		        try {
		        	String Query = "INSERT INTO story_chronology (StoryID, EventSeqConID, EventSeqConTypeID) VALUES ("+Integer.toString(StoryID)+","+Integer.toString(EventConID)+","+Integer.toString(TypeID)+")";
		        	System.out.println(Query);
		            int val = cdb.st.executeUpdate(Query);
		            System.out.println("1 row affected");		           
		        } catch (SQLException ex) {
		        	System.out.println("SQL statement is not executed!"+ex);
		        }
			}
		});
		btnAddSeqcon.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnAddSeqcon.setBounds(33, 261, 108, 23);
		frmOnceUponA.getContentPane().add(btnAddSeqcon);
		
		JTree tree = new JTree();
		tree.setBounds(265, 228, 314, 170);
		frmOnceUponA.getContentPane().add(tree);
	}
}
