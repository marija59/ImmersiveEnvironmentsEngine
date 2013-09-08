import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JSeparator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Concurrent {

	private JFrame frmTimer;
	private JTextField txtName;
	JComboBox cbTriggerEvent = new JComboBox();
	JComboBox cbEvents = new JComboBox();
	JComboBox cbSequence = new JComboBox();
	
	private void getData(){
		try	{
			ConnectDatabase cdb = new ConnectDatabase();        
			String Query = "SELECT EventName, idevents from events";			
			ResultSet result = cdb.st.executeQuery(Query);			  			  
			String EventID, EventName;	
			List<comboItem> sentences = new ArrayList<comboItem>();		    
			comboItem cItem = new comboItem("", -1);
		    sentences.add(cItem);
			while(result.next()) {
				EventID = result.getString("idevents");							
				EventName = result.getString("EventName");				
			    cItem = new comboItem(EventName, Integer.parseInt(EventID));
			    sentences.add(cItem);	
			}
			comboItem [] sentenceArray = sentences.toArray( new comboItem[sentences.size()]);
			cbTriggerEvent = new JComboBox(sentenceArray);
			cbTriggerEvent.setFont(new Font("Calibri", Font.PLAIN, 11));
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
					Concurrent window = new Concurrent();
					window.frmTimer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Concurrent() {
		getData();
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTimer = new JFrame();
		frmTimer.setTitle("Concurrence");
		frmTimer.setBounds(100, 100, 433, 178);
		frmTimer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTimer.getContentPane().setLayout(null);
		
		JLabel lblStarts = new JLabel("Starts");
		lblStarts.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblStarts.setBounds(10, 36, 46, 14);
		frmTimer.getContentPane().add(lblStarts);
		
		JLabel lblTriggeredByEvent = new JLabel("Triggered by event/info");
		lblTriggeredByEvent.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblTriggeredByEvent.setBounds(20, 61, 128, 14);
		frmTimer.getContentPane().add(lblTriggeredByEvent);
		
		
		cbTriggerEvent.setBounds(51, 86, 86, 20);
		frmTimer.getContentPane().add(cbTriggerEvent);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblName.setBounds(10, 11, 46, 14);
		frmTimer.getContentPane().add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(51, 7, 86, 20);
		frmTimer.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Create concurrence");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConnectDatabase cdb = new ConnectDatabase();
		        System.out.println("After Connect Database!");
		        int EventID = ((comboItem)cbTriggerEvent.getSelectedItem()).value;		     
		        try {
		            int val = cdb.st.executeUpdate("INSERT INTO concurrences (NameConc, TriggeredByEvent) VALUES ('"+txtName.getText()+"',"+Integer.toString(EventID)+")");
		            System.out.println("1 row affected");		           
		        } catch (SQLException ex) {
		        	System.out.println("SQL statement is not executed!"+ex);
		        }
		        getData();
			}
		});
		btnNewButton_1.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton_1.setBounds(267, 104, 127, 23);
		frmTimer.getContentPane().add(btnNewButton_1);
	}
}
