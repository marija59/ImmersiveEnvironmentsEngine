import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JComboBox;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JCheckBox;


public class Agents {
	
	private JFrame frmDefineRelationAgentprops;
	JComboBox cbEvents = new JComboBox();
	JComboBox cbAgent = new JComboBox();
	int IDEvent = -1;
	private final JComboBox comboBox = new JComboBox();
	Vector comboBoxItems = new Vector();
	Vector comboBoxItemsPlace = new Vector();
	
	private void getData(){
		try	{
			ConnectDatabase cdb = new ConnectDatabase();        
			String Query = "SELECT distinct events.EventName, events.idevents from sentences "
						+  " left join events  on sentences.VerbID = events.idevents";			
			ResultSet result = cdb.st.executeQuery(Query);			  			  
			String EventID, EventName;	
			List<comboItemSentence> sentences = new ArrayList<comboItemSentence>();		    
			comboItemSentence cItem = new comboItemSentence("", -1);
		    sentences.add(cItem);
			while(result.next()) {
				EventID = result.getString("events.idevents");							
				EventName = result.getString("events.EventName");
				
			    cItem = new comboItemSentence(EventName, Integer.parseInt(EventID));
			    sentences.add(cItem);	
			}
			comboItemSentence [] sentenceArray = sentences.toArray( new comboItemSentence[sentences.size()]);	
			System.out.println(sentenceArray[1]);
			cbEvents = new JComboBox(sentenceArray);	
			cbEvents.setFont(new Font("Calibri", Font.PLAIN, 11));
			
				
			
		}
		catch(SQLException ex){System.out.print(ex);}
	
	}
	
	private void getDataForSelectedEvent(){		
		try	{			
			comboBoxItems.removeAllElements();
			ConnectDatabase cdb = new ConnectDatabase();        
			String	Query = "SELECT distinct subjects.idsubjects, subjects.SubjectName, VerbID from sentences " 
					+ " left join subjects  on sentences.SubjectID = subjects.idsubjects "
					+ "where subjects.SubjectTypesID<>0  and VerbID = " + Integer.toString(((comboItemSentence)cbEvents.getSelectedItem()).value) + " ";	
			System.out.println(Query);
			ResultSet result = cdb.st.executeQuery(Query);			  			  
			String AgentID, AgentName;
						
			comboBoxItems.add(new comboItem("", -1));
			while(result.next()) {
				AgentID = result.getString("subjects.idsubjects");							
				AgentName = result.getString("subjects.SubjectName");				
				System.out.println(AgentName);
				//comboBoxItems.add(AgentName);
				comboBoxItems.add(new comboItem(AgentName, Integer.parseInt(AgentID)));
				//customCombo.addItem(new CustomComboItem(AgentName, Integer.parseInt(AgentID)));
			}	
			
			
			Query = "select distinct sentences.LocationID as idPlace, places.PlaceName as PlaceName from sentences "
					+ " left join places on places.idplaces = sentences.LocationID "
					+ " union select distinct sentences.LocationObjectID as idPlace, places.PlaceName as PlaceName from sentences "
					+ " left join places on places.idplaces = sentences.LocationObjectID  and VerbID = " + Integer.toString(((comboItemSentence)cbEvents.getSelectedItem()).value) + " ";	
			System.out.println(Query);
			result = cdb.st.executeQuery(Query);			  			  
			String PlaceID, PlaceName;
						
			comboBoxItems.add(new comboItem("", -1));
			while(result.next()) {
				PlaceID = result.getString("idPlace");							
				PlaceName = result.getString("PlaceName");				
				System.out.println(PlaceName);
				//comboBoxItems.add(AgentName);
				comboBoxItemsPlace.add(new comboItem(PlaceName, Integer.parseInt(PlaceID)));
				//customCombo.addItem(new CustomComboItem(AgentName, Integer.parseInt(AgentID)));
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
					Agents window = new Agents();
					window.frmDefineRelationAgentprops.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Agents() {
		getData();
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDefineRelationAgentprops = new JFrame();
		frmDefineRelationAgentprops.getContentPane().setFont(new Font("Calibri", Font.PLAIN, 11));
		frmDefineRelationAgentprops.setTitle("Which Agent/Prop respond to which Event?");
		frmDefineRelationAgentprops.setBounds(100, 100, 500, 761);
		frmDefineRelationAgentprops.getContentPane().setLayout(null);
		
		JLabel lblEvents = new JLabel("Events:");
		lblEvents.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblEvents.setBounds(26, 11, 46, 14);
		frmDefineRelationAgentprops.getContentPane().add(lblEvents);
		
		final JLabel lblWhowhatDoesThis = new JLabel("Who/what does/sense this action?");
		lblWhowhatDoesThis.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblWhowhatDoesThis.setBounds(26, 74, 305, 14);
		frmDefineRelationAgentprops.getContentPane().add(lblWhowhatDoesThis);
		
		JLabel lblAgentprop = new JLabel("Agent/Prop:");
		lblAgentprop.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblAgentprop.setBounds(26, 99, 76, 14);
		frmDefineRelationAgentprops.getContentPane().add(lblAgentprop);
		
		JLabel lblNewLabel = new JLabel("Place:");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblNewLabel.setBounds(26, 148, 46, 14);
		frmDefineRelationAgentprops.getContentPane().add(lblNewLabel);
		
		JLabel lblHow = new JLabel("How?");
		lblHow.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblHow.setBounds(26, 205, 46, 14);
		frmDefineRelationAgentprops.getContentPane().add(lblHow);
		
		JLabel label = new JLabel("Modalities ");
		label.setFont(new Font("Calibri", Font.PLAIN, 11));
		label.setBounds(26, 235, 212, 14);
		frmDefineRelationAgentprops.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Observer(See, Hear, Sense...)");
		label_1.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_1.setBounds(293, 235, 160, 14);
		frmDefineRelationAgentprops.getContentPane().add(label_1);
		
		final JCheckBox cbActuation = new JCheckBox("Embodied actuation");
		cbActuation.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbActuation.setBounds(26, 256, 160, 23);
		frmDefineRelationAgentprops.getContentPane().add(cbActuation);
		
		final JCheckBox cbSound = new JCheckBox("Sound");
		cbSound.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbSound.setBounds(26, 282, 97, 23);
		frmDefineRelationAgentprops.getContentPane().add(cbSound);
		
		final JCheckBox cbAnimation = new JCheckBox("Animations");
		cbAnimation.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbAnimation.setBounds(26, 308, 97, 23);
		frmDefineRelationAgentprops.getContentPane().add(cbAnimation);
		
		final JCheckBox cbLight = new JCheckBox("Light");
		cbLight.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbLight.setBounds(26, 334, 97, 23);
		frmDefineRelationAgentprops.getContentPane().add(cbLight);
		
		final JCheckBox cbSee = new JCheckBox("See");
		cbSee.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbSee.setBounds(293, 256, 97, 23);
		frmDefineRelationAgentprops.getContentPane().add(cbSee);
		
		final JCheckBox cbListen = new JCheckBox("Listen");
		cbListen.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbListen.setBounds(293, 282, 97, 23);
		frmDefineRelationAgentprops.getContentPane().add(cbListen);
		
		final JCheckBox cbProximity = new JCheckBox("Proximity");
		cbProximity.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbProximity.setBounds(293, 308, 97, 23);
		frmDefineRelationAgentprops.getContentPane().add(cbProximity);
		
		final JCheckBox cbSense = new JCheckBox("Sense");
		cbSense.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbSense.setBounds(293, 334, 97, 23);
		frmDefineRelationAgentprops.getContentPane().add(cbSense);
		
		final JCheckBox cbSpecialEffects = new JCheckBox("Special effects");
		cbSpecialEffects.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbSpecialEffects.setBounds(26, 360, 97, 23);
		frmDefineRelationAgentprops.getContentPane().add(cbSpecialEffects);
		
		cbEvents.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {				
				 getDataForSelectedEvent();
				 final DefaultComboBoxModel model = new DefaultComboBoxModel(comboBoxItems);
				 JComboBox comboBox = new JComboBox(model);
				 frmDefineRelationAgentprops.getContentPane().add(comboBox);
				 comboBox.setBounds(26, 124, 158, 20);
				 
				 final DefaultComboBoxModel modelPlaces = new DefaultComboBoxModel(comboBoxItemsPlace);
				 JComboBox comboBoxPlaces = new JComboBox(modelPlaces);
				 frmDefineRelationAgentprops.getContentPane().add(comboBoxPlaces);
				 comboBoxPlaces.setBounds(26, 173, 158, 20);
				 
				 try{
					 ConnectDatabase cdb = new ConnectDatabase();        
					 String Query = "select EventTypesID from events where idevents = "  + Integer.toString(((comboItemSentence)cbEvents.getSelectedItem()).value) + " "; 			
					 ResultSet result = cdb.st.executeQuery(Query);
					 String EventType;
					 while(result.next()) {
						 EventType = result.getString("EventTypesID");					 
						 if (EventType.equals("1")){
							 lblWhowhatDoesThis.setText("Who/What does this action?");
							 cbActuation.setEnabled(true); 
							 cbSound.setEnabled(true);
							 cbAnimation.setEnabled(true);
							 cbLight.setEnabled(true);
							 cbSpecialEffects.setEnabled(true);
							 cbSee.setEnabled(false);
							 cbListen.setEnabled(false);
							 cbProximity.setEnabled(false);
							 cbSense.setEnabled(false);
						 } 
						 else {
							 lblWhowhatDoesThis.setText("Who/What sense this action?");
							 cbActuation.setEnabled(false); 
							 cbSound.setEnabled(false);
							 cbAnimation.setEnabled(false);
							 cbLight.setEnabled(false);
							 cbSpecialEffects.setEnabled(false);
							 cbSee.setEnabled(true);
							 cbListen.setEnabled(true);
							 cbProximity.setEnabled(true);
							 cbSense.setEnabled(true);
						 }
						 
					 }
				 }
					catch(SQLException ex){System.out.print(ex);}	
			}
		});
		cbEvents.setBounds(26, 36, 158, 20);
		frmDefineRelationAgentprops.getContentPane().add(cbEvents);
		
		JCheckBox cbInformation = new JCheckBox("Information");
		cbInformation.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbInformation.setBounds(293, 360, 97, 23);
		frmDefineRelationAgentprops.getContentPane().add(cbInformation);
		
		JCheckBox chckbxInformation = new JCheckBox("Information");
		chckbxInformation.setFont(new Font("Calibri", Font.PLAIN, 11));
		chckbxInformation.setBounds(26, 389, 97, 23);
		frmDefineRelationAgentprops.getContentPane().add(chckbxInformation);
		
		
	}
}
