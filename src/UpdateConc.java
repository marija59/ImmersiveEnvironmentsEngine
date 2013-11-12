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

import javax.swing.ButtonGroup;
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

import javax.swing.JRadioButton;

import com.mysql.jdbc.CallableStatement;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


public class UpdateConc {

	private JFrame frame;
	private JTextField txtTime;
	JComboBox<comboItem> cbSequences = new JComboBox<comboItem>();
	JComboBox<?> cbEvents = new JComboBox<Object>(new Object[]{});
	final JTextPane textPane = new JTextPane();
	private int isMustMay=-1;
	
	private void getSequence(){
		ConnectDatabase cdb = new ConnectDatabase();  
		int SeqID = ((comboItem)cbSequences.getSelectedItem()).value;
		String Query = "select * from "
					+ " ( SELECT concurr_events.idconcurr_events as id, subjects.SubjectTypesID, subjects.SubjectName, places.PlaceName, events.EventName, IFNULL(o.SubjectTypesID,'') as objTypesID, IFNULL(o.SubjectName, '') as ObjectName, IFNULL(po.PlaceName, '') as ObjPlaceName "
					+ " from concurrences 	join concurr_events on concurrences.idconcurrences = concurr_events.ConcurrenceID " 
					+ " left join sentences on sentences.idSentences = concurr_events.EventConID " 
					+ " left join subjects on sentences.SubjectID = subjects.idsubjects " 
					+ " left join subjects o on sentences.ObjectID = o.idsubjects " 
					+ " left join events  on sentences.VerbID = events.idevents " 
					+ " left join places on sentences.LocationID = places.idplaces " 
					+ " left join places po on sentences.LocationObjectID = po.idplaces " 
					+ " where concurrences.idconcurrences = 1 and concurr_events.EventConTypeID = 1 " 
					+ " union " 
					+ " select concurr_events.idconcurr_events as id, 3 as SybjectTypesID, sequences.NameSeq as SubjectName, '' as PlaceName, '' as EventName, '' as objeTypesID, '' as ObjectName, '' as ObjPlaceName " 
					+ " from concurrences 	join concurr_events on concurrences.idconcurrences = concurr_events.ConcurrenceID " 
					+ " join sequences on sequences.idsequence = concurr_events.EventConID " 
					+ " where concurrences.idconcurrences =  1 and concurr_events.EventConTypeID = 3 ) as result " 
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
				
				StyledDocument doc = textPane.getStyledDocument();		
			

				try {
					javax.swing.text.Style style = textPane.addStyle("Style", null);					
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
		cbEvents.removeAllItems();
		try	{
			ConnectDatabase cdb = new ConnectDatabase();        
			String Query = "SELECT idSentences as SenConcID, concat(subjects.SubjectName, places.PlaceName, events.EventName, IFNULL(o.SubjectName, ''),  IFNULL(po.PlaceName, '')) as NameEvSeq, 1 as Type " 
						+ " from sentences left join subjects on sentences.SubjectID = subjects.idsubjects " 
						+ " left join subjects o on sentences.ObjectID = o.idsubjects " 
						+ " left join events  on sentences.VerbID = events.idevents " 
						+ " left join places on sentences.LocationID = places.idplaces " 
						+ " left join places po on sentences.LocationObjectID = po.idplaces " 
						+ " union " 
						+ " select idsequence as SenConcID, NameSeq as NameEvSeq, 3 as Type " 
						+ " from sequences";	
			System.out.println(Query);
			ResultSet result = cdb.st.executeQuery(Query);			  			  
			String SentenceID, Type, OneSentence = "";	
			List<comboItemSeq> sentences = new ArrayList<comboItemSeq>();		    
			comboItemSeq cItem = new comboItemSeq("", -1, -1);
		    sentences.add(cItem);
			while(result.next()) {
				SentenceID = result.getString("SenConcID");
				OneSentence = result.getString("NameEvSeq");				
				Type = result.getString("Type");
			    cItem = new comboItemSeq(OneSentence, Integer.parseInt(SentenceID), Integer.parseInt(Type));
			    sentences.add(cItem);	
			}
			comboItemSeq [] sentenceArray = sentences.toArray( new comboItemSeq[sentences.size()]);	
			System.out.println(sentenceArray[5]);
			cbEvents = new JComboBox<Object>(sentenceArray);
			cbEvents.setFont(new Font("Calibri", Font.PLAIN, 11));
		}
		catch(SQLException ex){System.out.print(ex);}
		
		try	{
			ConnectDatabase cdb = new ConnectDatabase();        
			String Query = "select NameConc, idconcurrences from concurrences";			
			ResultSet result = cdb.st.executeQuery(Query);
			cbSequences.addItem(new comboItem("", -1));
			while(result.next()) {
				
			    cbSequences.addItem(new comboItem(result.getString("NameConc"), result.getInt("idconcurrences")));	
			}
			
			
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
					UpdateConc window = new UpdateConc();
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
	public UpdateConc() {
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
		
		JRadioButton rbTimeSeqMay = new JRadioButton("MAY");
		rbTimeSeqMay.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				isMustMay = 1;
			}
		});
		rbTimeSeqMay.setFont(new Font("Calibri", Font.PLAIN, 10));
		rbTimeSeqMay.setBounds(233, 199, 59, 23);
		frame.getContentPane().add(rbTimeSeqMay);
		
		JRadioButton rbTimeSeqMust = new JRadioButton("MUST");
		rbTimeSeqMust.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				isMustMay = 2;
			}
		});
		rbTimeSeqMust.setFont(new Font("Calibri", Font.PLAIN, 10));
		rbTimeSeqMust.setBounds(294, 199, 66, 23);
		frame.getContentPane().add(rbTimeSeqMust);
		
		final ButtonGroup TypeSeq = new ButtonGroup();
	    TypeSeq.add(rbTimeSeqMay);
	    TypeSeq.add(rbTimeSeqMust);
		
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
				ConnectDatabase cdb2 = new ConnectDatabase();
				
				int SeqID = ((comboItem)cbSequences.getSelectedItem()).value;
		        int EventConID = ((comboItemSeq)cbEvents.getSelectedItem()).value;
		        int TypeID = ((comboItemSeq)cbEvents.getSelectedItem()).type;	
				
				String Guard="", Action="", Variable="", InitialValue = "0";
				try {
					CallableStatement cs = (CallableStatement) cdb.con.prepareCall("{call SelectConGuard(" + SeqID + ")}");
					ResultSet result = cs.executeQuery(); 
					if(result.next()) {
						Guard = result.getString("newguard");
					}
							
			        String Query = "INSERT INTO concurr_events (ConcurrenceID, EventConID, EventConTypeID, Guard) "
			        			+ " VALUES (" + Integer.toString(SeqID) + "," + Integer.toString(EventConID) + "," + Integer.toString(TypeID) + ",'" + Guard + "')";
			        System.out.println(Query);
			        int val = cdb.st.executeUpdate(Query);
			        System.out.println("1 row affected");
			        
			        String LastIDQuery = "select idconcurr_events from concurr_events where ConcurrenceID = " + SeqID + " order by idconcurr_events desc limit 1";
					ResultSet resultLastID = cdb.st.executeQuery(LastIDQuery);
					if(resultLastID.next()) {
						Action = "GC" + Integer.toString(resultLastID.getInt("idconcurr_events"))+"==1";
						Variable = "GC" + Integer.toString(resultLastID.getInt("idconcurr_events"));
					}
					String UpdateQuery = "UPDATE concurr_events SET Action = '" +Action+ "', Variable = '" + Variable + "' where idconcurr_events = " + Integer.toString(resultLastID.getInt("idconcurr_events"));
					System.out.println(UpdateQuery);
					cdb2.st.executeUpdate(UpdateQuery);
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
