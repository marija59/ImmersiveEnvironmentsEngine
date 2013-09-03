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
		frmOnceUponA.setBounds(100, 100, 612, 709);
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
				
		cbFirstSentence.setBounds(43, 224, 309, 20);
		frmOnceUponA.getContentPane().add(cbFirstSentence);
		
		JLabel lblSentence = new JLabel("Sentence");
		lblSentence.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblSentence.setBounds(43, 201, 139, 14);
		frmOnceUponA.getContentPane().add(lblSentence);
		
		JLabel lblTime = new JLabel("Time(Reactive-Feedback, Narrative-Feedforward)");
		lblTime.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblTime.setBounds(43, 265, 272, 14);
		frmOnceUponA.getContentPane().add(lblTime);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setFont(new Font("Calibri", Font.PLAIN, 11));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"BEFORE", "MEETS", "OVERLAPS", "STARTS", "DURING", "FINISHES", "EQUALS"}));
		comboBox_1.setToolTipText("BEFORE\r\nMEETS\r\nOVERLAPS\r\nSTARTS\r\nDURING\r\nFINISHES\r\nEQUALS");
		comboBox_1.setBounds(43, 292, 180, 20);
		frmOnceUponA.getContentPane().add(comboBox_1);
		
		JLabel label = new JLabel("Sentence");
		label.setFont(new Font("Calibri", Font.PLAIN, 11));
		label.setBounds(43, 333, 128, 14);
		frmOnceUponA.getContentPane().add(label);
				
		cbSecondSentence.setBounds(43, 356, 309, 20);
		frmOnceUponA.getContentPane().add(cbSecondSentence);
		
		JButton btnNewButton = new JButton("Add time");
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton.setBounds(43, 387, 89, 23);
		frmOnceUponA.getContentPane().add(btnNewButton);
	}
}
