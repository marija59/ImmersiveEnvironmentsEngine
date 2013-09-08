import java.awt.EventQueue;

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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class UpdateConcurrence {

	private JFrame frame;
	JComboBox cbSequences = new JComboBox(new Object[]{});
	JComboBox cbEvents = new JComboBox(new Object[]{});
	
	private void getData(){
		cbEvents.removeAll();
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
			List<comboItem> sentences = new ArrayList<comboItem>();		    
			comboItem cItem = new comboItem("", -1);
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
			    cItem = new comboItem(OneSentence, Integer.parseInt(SentenceID));
			    sentences.add(cItem);	
			}
			comboItem [] sentenceArray = sentences.toArray( new comboItem[sentences.size()]);	
			System.out.println(sentenceArray[5]);
			cbEvents = new JComboBox(sentenceArray);
			cbEvents.setFont(new Font("Calibri", Font.PLAIN, 11));
		}
		catch(SQLException ex){System.out.print(ex);}
		
		try	{
			ConnectDatabase cdb = new ConnectDatabase();        
			String Query = "SELECT NameConc, idconcurrences from concurrences";			
			ResultSet result = cdb.st.executeQuery(Query);
			List<comboItem> sequences = new ArrayList<comboItem>();		    
			comboItem cItem = new comboItem("", -1);
			sequences.add(cItem);
			while(result.next()) {
				String SeqID = result.getString("idconcurrences");							
				String SeqName = result.getString("NameConc");				
			    cItem = new comboItem(SeqName, Integer.parseInt(SeqID));
			    sequences.add(cItem);	
			}
			comboItem [] seqArray = sequences.toArray( new comboItem[sequences.size()]);
			cbSequences = new JComboBox(seqArray);
			cbSequences.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
				}
			});
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
					UpdateConcurrence window = new UpdateConcurrence();
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
	public UpdateConcurrence() {
		getData();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 433, 355);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(20, 92, 374, 100);
		frame.getContentPane().add(textPane);
		
		JButton button = new JButton("Add in seq");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConnectDatabase cdb = new ConnectDatabase();
		        System.out.println("After Connect Database!");	        
		        int SeqID = ((comboItem)cbSequences.getSelectedItem()).value;
		        int EventConID = ((comboItem)cbEvents.getSelectedItem()).value;
		        int TypeID = ((comboItem)cbEvents.getSelectedItem()).value;	
		        try {
		            int val = cdb.st.executeUpdate("INSERT INTO concurr_events (ConcurrenceID, EventConID, EventConTypeID) VALUES ("+Integer.toString(SeqID)+","+Integer.toString(EventConID)+","+Integer.toString(TypeID)+")");
		            System.out.println("1 row affected");		           
		        } catch (SQLException ex) {
		        	System.out.println("SQL statement is not executed!"+ex);
		        }
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
		frame.getContentPane().add(cbSequences);
		
		JLabel label_4 = new JLabel("Update sequence");
		label_4.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_4.setBounds(10, 11, 127, 14);
		frame.getContentPane().add(label_4);
	}
}
