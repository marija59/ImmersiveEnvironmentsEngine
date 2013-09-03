import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class comboItemEvents  
{  
  String name;  
  int value;  
  public comboItemEvents(String n, int v)  
  {  
    name = n; value = v;  
  } 
  public String toString(){return name;} 
}


public class Events {

	private JFrame frmAndThenHappen;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	JComboBox cbEvents = new JComboBox();
	

	private void getData(){		
		//try	{
			
			
			
			
		//}
		//catch(SQLException ex){System.out.print(ex);}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Events window = new Events();
					window.frmAndThenHappen.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Events() {		
		initialize();		
		getData();
		cbEvents.removeAll();
		try
		{
			ConnectDatabase cdb = new ConnectDatabase();        
			String Query = "SELECT distinct events.EventName, events.idevents from sentences "
					+  " left join events  on sentences.VerbID = events.idevents";								
			ResultSet result = cdb.st.executeQuery(Query);			  			  
			String EventID, EventName;	
			List<comboItemEvents> events = new ArrayList<comboItemEvents>();		    
			comboItemEvents cItem = new comboItemEvents("", -1);
			events.add(cItem);
			while(result.next()) {
				EventID = result.getString("events.idevents");								
				EventName = result.getString("events.EventName");				
				cItem = new comboItemEvents(EventName, Integer.parseInt(EventID));
				events.add(cItem);	
			}
			comboItemEvents [] eventArray = events.toArray( new comboItemEvents[events.size()]);	
			System.out.print(eventArray[1]);
			System.out.print(eventArray[2]);
			System.out.print(eventArray[3]);
			cbEvents = new JComboBox(eventArray);
		}catch(SQLException ex){System.out.print(ex);}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAndThenHappen = new JFrame();
		frmAndThenHappen.setTitle("And then... ");
		frmAndThenHappen.setBounds(100, 100, 633, 810);
		frmAndThenHappen.getContentPane().setLayout(null);
		
		JLabel lblEvent = new JLabel("Event");
		lblEvent.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblEvent.setBounds(22, 11, 46, 14);
		frmAndThenHappen.getContentPane().add(lblEvent);
		
		
		cbEvents.setBounds(22, 36, 187, 20);
		frmAndThenHappen.getContentPane().add(cbEvents);
		
		
		
		JLabel lblAgentprop = new JLabel("Agent/Prop/Place");
		lblAgentprop.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblAgentprop.setBounds(22, 78, 100, 14);
		frmAndThenHappen.getContentPane().add(lblAgentprop);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(22, 103, 187, 20);
		frmAndThenHappen.getContentPane().add(comboBox_1);
		
		JLabel lblModalities = new JLabel("Modalities ");
		lblModalities.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblModalities.setBounds(22, 145, 212, 14);
		frmAndThenHappen.getContentPane().add(lblModalities);
		
		JLabel lblObserver = new JLabel("Observer(See, Hear, Sense...)");
		lblObserver.setBounds(289, 145, 160, 14);
		frmAndThenHappen.getContentPane().add(lblObserver);
		
		JLabel lblDynamics = new JLabel("Dynamics (Position, Speed, Acceleration...)");
		lblDynamics.setBounds(22, 310, 224, 14);
		frmAndThenHappen.getContentPane().add(lblDynamics);
		
		JCheckBox chckbxActuation = new JCheckBox("Embodied actuation");
		chckbxActuation.setFont(new Font("Calibri", Font.PLAIN, 11));
		chckbxActuation.setBounds(22, 166, 160, 23);
		frmAndThenHappen.getContentPane().add(chckbxActuation);
		
		JCheckBox chckbxSound = new JCheckBox("Sound");
		chckbxSound.setFont(new Font("Calibri", Font.PLAIN, 11));
		chckbxSound.setBounds(22, 192, 97, 23);
		frmAndThenHappen.getContentPane().add(chckbxSound);
		
		JCheckBox chckbxVirtualReality = new JCheckBox("Animations");
		chckbxVirtualReality.setBounds(22, 218, 97, 23);
		frmAndThenHappen.getContentPane().add(chckbxVirtualReality);
		
		JCheckBox chckbxLight = new JCheckBox("Light");
		chckbxLight.setBounds(22, 244, 97, 23);
		frmAndThenHappen.getContentPane().add(chckbxLight);
		
		JCheckBox chckbxSee = new JCheckBox("See");
		chckbxSee.setBounds(289, 166, 97, 23);
		frmAndThenHappen.getContentPane().add(chckbxSee);
		
		JCheckBox chckbxListen = new JCheckBox("Listen");
		chckbxListen.setBounds(289, 192, 97, 23);
		frmAndThenHappen.getContentPane().add(chckbxListen);
		
		JCheckBox chckbxProximity = new JCheckBox("Proximity");
		chckbxProximity.setBounds(289, 218, 97, 23);
		frmAndThenHappen.getContentPane().add(chckbxProximity);
		
		JCheckBox chckbxSense = new JCheckBox("Sense");
		chckbxSense.setBounds(289, 244, 97, 23);
		frmAndThenHappen.getContentPane().add(chckbxSense);
		
		JLabel lblEmbodiedActuation = new JLabel("Embodied actuation 1,2,3...");
		lblEmbodiedActuation.setBounds(32, 339, 150, 14);
		frmAndThenHappen.getContentPane().add(lblEmbodiedActuation);
		
		JLabel lblTime = new JLabel("Lenght");
		lblTime.setBounds(65, 364, 81, 14);
		frmAndThenHappen.getContentPane().add(lblTime);
		
		JLabel lblPosition = new JLabel("Position");
		lblPosition.setBounds(65, 389, 46, 14);
		frmAndThenHappen.getContentPane().add(lblPosition);
		
		JLabel lblSpeed = new JLabel("Speed");
		lblSpeed.setBounds(65, 414, 46, 14);
		frmAndThenHappen.getContentPane().add(lblSpeed);
		
		JLabel lblAcceleration = new JLabel("Acceleration");
		lblAcceleration.setBounds(65, 439, 95, 14);
		frmAndThenHappen.getContentPane().add(lblAcceleration);
		
		JLabel lblSound = new JLabel("Sound");
		lblSound.setBounds(32, 464, 46, 14);
		frmAndThenHappen.getContentPane().add(lblSound);
		
		textField = new JTextField();
		textField.setBounds(148, 361, 86, 20);
		frmAndThenHappen.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(148, 386, 86, 20);
		frmAndThenHappen.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(148, 411, 86, 20);
		frmAndThenHappen.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(148, 436, 86, 20);
		frmAndThenHappen.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblMsec = new JLabel("msec");
		lblMsec.setBounds(240, 364, 46, 14);
		frmAndThenHappen.getContentPane().add(lblMsec);
		
		JLabel lblLenght = new JLabel("Lenght");
		lblLenght.setBounds(65, 489, 46, 14);
		frmAndThenHappen.getContentPane().add(lblLenght);
		
		JLabel lblVolume = new JLabel("Volume");
		lblVolume.setBounds(65, 514, 46, 14);
		frmAndThenHappen.getContentPane().add(lblVolume);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Special effects");
		chckbxNewCheckBox.setBounds(22, 270, 97, 23);
		frmAndThenHappen.getContentPane().add(chckbxNewCheckBox);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(65, 535, 46, 14);
		frmAndThenHappen.getContentPane().add(lblType);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setToolTipText("Ambient\r\nDialog\r\nMusic\r\nSpot\r\nOther");
		comboBox_2.setBounds(148, 532, 86, 20);
		frmAndThenHappen.getContentPane().add(comboBox_2);
		
		textField_4 = new JTextField();
		textField_4.setBounds(148, 511, 86, 20);
		frmAndThenHappen.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(148, 486, 86, 20);
		frmAndThenHappen.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Animation");
		lblNewLabel.setBounds(32, 566, 87, 14);
		frmAndThenHappen.getContentPane().add(lblNewLabel);
		
		JLabel lblType_1 = new JLabel("Type");
		lblType_1.setBounds(65, 591, 46, 14);
		frmAndThenHappen.getContentPane().add(lblType_1);
		
		JLabel lblLength = new JLabel("Length");
		lblLength.setBounds(65, 616, 46, 14);
		frmAndThenHappen.getContentPane().add(lblLength);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setToolTipText("Film\r\n3D\r\nSimulator\r\nOther");
		comboBox_3.setBounds(148, 588, 86, 20);
		frmAndThenHappen.getContentPane().add(comboBox_3);
		
		textField_6 = new JTextField();
		textField_6.setBounds(148, 613, 86, 20);
		frmAndThenHappen.getContentPane().add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblLight = new JLabel("Light");
		lblLight.setBounds(32, 641, 46, 14);
		frmAndThenHappen.getContentPane().add(lblLight);
		
		JLabel lblType_2 = new JLabel("Type");
		lblType_2.setBounds(65, 658, 46, 14);
		frmAndThenHappen.getContentPane().add(lblType_2);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(148, 655, 86, 20);
		frmAndThenHappen.getContentPane().add(comboBox_4);
		
		JLabel lblSpecialEffects = new JLabel("Special Effect");
		lblSpecialEffects.setBounds(32, 703, 100, 14);
		frmAndThenHappen.getContentPane().add(lblSpecialEffects);
		
		JLabel lblType_3 = new JLabel("Type");
		lblType_3.setBounds(65, 727, 46, 14);
		frmAndThenHappen.getContentPane().add(lblType_3);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setToolTipText("Wind\r\nFog\r\nSmell\r\nTactile\r\nOther");
		comboBox_5.setBounds(148, 724, 86, 20);
		frmAndThenHappen.getContentPane().add(comboBox_5);
		
		JLabel lblMsec_1 = new JLabel("msec");
		lblMsec_1.setBounds(240, 489, 46, 14);
		frmAndThenHappen.getContentPane().add(lblMsec_1);
		
		JLabel label = new JLabel("msec");
		label.setBounds(240, 616, 46, 14);
		frmAndThenHappen.getContentPane().add(label);
	}
}
