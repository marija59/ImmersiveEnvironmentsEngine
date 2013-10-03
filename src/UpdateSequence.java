import java.awt.Color;
import java.awt.EventQueue;

import javax.jws.soap.SOAPBinding.Style;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

class comboItemSeq  
{  
  String name;  
  int value;  
  int type;
  public comboItemSeq(String n, int v, int t)  
  {  
    name = n; value = v; type = t;
  } 
  public String toString(){return name;} 
}

public class UpdateSequence {

	private JFrame frame;
	private JTextField txtTime;
	JComboBox cbSequences = new JComboBox(new Object[]{});
	JComboBox cbEvents = new JComboBox(new Object[]{});
	final JTextPane textPane = new JTextPane();
	
	private void getSequence(){
		ConnectDatabase cdb = new ConnectDatabase();  
		int SeqID = ((comboItem)cbSequences.getSelectedItem()).value;
		String Query = "select * from "
					+ " ( SELECT seq_events.idseq_events as id, subjects.SubjectTypesID, subjects.SubjectName, places.PlaceName, events.EventName, IFNULL(o.SubjectTypesID,'') as objTypesID, IFNULL(o.SubjectName, '') as ObjectName, IFNULL(po.PlaceName, '') as ObjPlaceName, seq_events.Time  as SeqTime"  
					+ " from sequences 	join seq_events on sequences.idsequence = seq_events.SequenceID "  
					+ " left join sentences on sentences.idSentences = seq_events.EventConID " 	
					+ " left join subjects on sentences.SubjectID = subjects.idsubjects "  
					+ " left join subjects o on sentences.ObjectID = o.idsubjects "  
					+ " left join events  on sentences.VerbID = events.idevents "  
					+ " left join places on sentences.LocationID = places.idplaces "  
					+ " left join places po on sentences.LocationObjectID = po.idplaces "   
					+ " where sequences.idsequence = " + Integer.toString(SeqID) +  " and seq_events.TypeID = 1 " 
					+ " union " 
					+ " select seq_events.idseq_events as id, 3 as SybjectTypesID, concurrences.NameConc as SubjectName, '' as PlaceName, '' as EventName, '' as objeTypesID, '' as ObjectName, '' as ObjPlaceName, seq_events.Time  as SeqTime "
					+ " from sequences 	join seq_events on sequences.idsequence = seq_events.SequenceID "  
					+ " join concurrences on concurrences.idconcurrences = seq_events.EventConID "
					+ " where sequences.idsequence =  " + Integer.toString(SeqID) +  " and seq_events.TypeID = 2 ) as result "
					+ " order by id ";
		System.out.println(Query);
		try	{
			ResultSet result = cdb.st.executeQuery(Query);			  			  
			String SubType, SubName, SubPlaceName, Verb, ObjName, ObjPlaceName, ObjType, SeqTime;
			textPane.setText("");
			while(result.next()) {
				SubType = result.getString("SubjectTypesID");
				ObjType = result.getString("objTypesID");
				System.out.println(SubType);
				SubName = result.getString("SubjectName");
				SubPlaceName = result.getString("PlaceName");
				Verb = result.getString("EventName");
				ObjName = result.getString("ObjectName");
				ObjPlaceName = result.getString("ObjPlaceName");
				SeqTime = result.getString("SeqTime");
				StyledDocument doc = textPane.getStyledDocument();		
			

				try {
					javax.swing.text.Style style = textPane.addStyle("Style", null);
					doc.insertString(doc.getLength(), SeqTime + " msec   ", style);
					if (SubType.equals("3")){
						doc.insertString(doc.getLength(), SubName + "  \n ", style);
					}
					else 
					{
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
				}	
				catch (BadLocationException ex){}			
			}
		}
		catch(SQLException ex){System.out.print(ex);}
		
	}
	
	
	private void getData(){
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
						+ " from concurrences";		
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
			
			cbEvents = new JComboBox(sentenceArray);
			cbEvents.setFont(new Font("Calibri", Font.PLAIN, 11));
		}
		catch(SQLException ex){System.out.print(ex);}
		
		try	{
			ConnectDatabase cdb = new ConnectDatabase();        
			String Query = "SELECT NameSeq, idsequence from sequences";			
			ResultSet result = cdb.st.executeQuery(Query);
			List<comboItem> sequences = new ArrayList<comboItem>();		    
			comboItem cItem = new comboItem("", -1);
			sequences.add(cItem);
			while(result.next()) {
				String SeqID = result.getString("idsequence");							
				String SeqName = result.getString("NameSeq");				
			    cItem = new comboItem(SeqName, Integer.parseInt(SeqID));
			    sequences.add(cItem);	
			}
			comboItem [] seqArray = sequences.toArray( new comboItem[sequences.size()]);
			cbSequences = new JComboBox(seqArray);
			
			cbSequences.setFont(new Font("Calibri", Font.PLAIN, 11));
			
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
					UpdateSequence window = new UpdateSequence();
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
	public UpdateSequence() {
		getData();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 433, 355);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Sequence of controllable events:");
		label.setFont(new Font("Calibri", Font.PLAIN, 11));
		label.setBounds(10, 67, 240, 14);
		frame.getContentPane().add(label);
		
		
		cbEvents.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbEvents.setBounds(123, 259, 271, 20);
		frame.getContentPane().add(cbEvents);
		
		JLabel label_1 = new JLabel("Event/concurent events");
		label_1.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_1.setBounds(10, 263, 138, 14);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Time before");
		label_2.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_2.setBounds(10, 236, 70, 14);
		frame.getContentPane().add(label_2);
		
		txtTime = new JTextField();
		txtTime.setText("0");
		txtTime.setColumns(10);
		txtTime.setBounds(123, 232, 103, 20);
		frame.getContentPane().add(txtTime);
		
		JLabel label_3 = new JLabel("msec");
		label_3.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_3.setBounds(233, 236, 46, 14);
		frame.getContentPane().add(label_3);
		
		
		textPane.setBounds(20, 92, 374, 100);
		frame.getContentPane().add(textPane);
		
		JButton button = new JButton("Add in seq");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConnectDatabase cdb = new ConnectDatabase();
		        System.out.println("After Connect Database!");	        
		        int SeqID = ((comboItem)cbSequences.getSelectedItem()).value;
		        int EventConID = ((comboItemSeq)cbEvents.getSelectedItem()).value;
		        int TypeID = ((comboItemSeq)cbEvents.getSelectedItem()).type;	
		        try {
		        	String Query = "INSERT INTO seq_events (SequenceID, EventConID, TypeID, Time) VALUES ("+Integer.toString(SeqID)+","+Integer.toString(EventConID)+","+Integer.toString(TypeID)+","+txtTime.getText()+")";
		        	System.out.println(Query);
		            int val = cdb.st.executeUpdate(Query);
		            System.out.println("1 row affected");		           
		        } catch (SQLException ex) {
		        	System.out.println("SQL statement is not executed!"+ex);
		        }
		        getSequence();
			}
		});
		button.setFont(new Font("Calibri", Font.PLAIN, 11));
		button.setBounds(305, 290, 89, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Concurrent");
		button_1.setFont(new Font("Calibri", Font.PLAIN, 11));
		button_1.setBounds(123, 203, 103, 23);
		frame.getContentPane().add(button_1);
		
		
		cbSequences.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbSequences.setBounds(10, 36, 127, 20);
		cbSequences.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				getSequence();
			}
		});
		frame.getContentPane().add(cbSequences);
		
		
		JLabel label_4 = new JLabel("Update sequence");
		label_4.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_4.setBounds(10, 11, 127, 14);
		frame.getContentPane().add(label_4);
	}
}
