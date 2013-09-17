import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTree;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AgentItems {

	private JFrame frmAgentsItems;
	JComboBox<comboItem> cbAgent = new JComboBox<comboItem>();
	JComboBox<comboItem> cbItems = new JComboBox<comboItem>();
	JComboBox<comboItem> cbAgentModality = new JComboBox<comboItem>();
	int flagForItems = 0;
	
	private void getDataAgents(){		
		try	{			
			cbAgent.setFont(new Font("Calibri", Font.PLAIN, 11));			
			cbAgent.removeAllItems();			
			
			
			ConnectDatabase cdb = new ConnectDatabase();        
			String	Query = "SELECT subjects.idsubjects, subjects.SubjectName from subjects where subjects.SubjectTypesID<>0 ";	
			System.out.println(Query);
			ResultSet result = cdb.st.executeQuery(Query);			  			  
			String AgentID, AgentName;
						
			cbAgent.addItem(new comboItem("", -1));
			while(result.next()) {
				AgentID = result.getString("subjects.idsubjects");							
				AgentName = result.getString("subjects.SubjectName");				
				System.out.println(AgentName);				
				cbAgent.addItem(new comboItem(AgentName, Integer.parseInt(AgentID)));
			}	
		}
		catch(SQLException ex){System.out.print(ex);}	
	}
	
	private void getDataForSelectedAgent(){		
		try	{			
			cbAgentModality.removeAllItems();	
			ConnectDatabase cdb = new ConnectDatabase();        
			String	Query = "select modality_types.idmodality_types, modality_types.ModalityTypeName from agent_modalities join modality_types on agent_modalities.ModalityTypeID = modality_types.idmodality_types where AgentID = " + Integer.toString(((comboItem)cbAgent.getSelectedItem()).value) + " ";	
			System.out.println(Query);
			ResultSet result = cdb.st.executeQuery(Query);			  			  
			String ModalityTypeID, ModalityTypeName;
						
			cbAgentModality.addItem(new comboItem("", -1));
			while(result.next()) {
				ModalityTypeID = result.getString("modality_types.idmodality_types");							
				ModalityTypeName = result.getString("modality_types.ModalityTypeName");
				cbAgentModality.addItem(new comboItem(ModalityTypeName, Integer.parseInt(ModalityTypeID)));
			}	
			
		}
		catch(SQLException ex){System.out.print(ex);}	
	}
	
	private void getDataForSelectedModality(){		
		try	{
			if (cbAgentModality.getSelectedItem() != null){
				cbItems.removeAllItems();			
			
				ConnectDatabase cdb = new ConnectDatabase();        
				String	Query = "select hi.Name, hi.idhardware_items from hardware_items hi join hardware_items_modalities him on hi.idhardware_items = him.HardwareItemID where him.ModalityTypeID =  " + Integer.toString(((comboItem)cbAgentModality.getSelectedItem()).value) + " ";	
				System.out.println(Query);
				ResultSet result = cdb.st.executeQuery(Query);			  			  
				String HardwareItemID, HardwareItemName;
						
				cbItems.addItem(new comboItem("", -1));
				while(result.next()) {
					HardwareItemID = result.getString("hi.idhardware_items");							
					HardwareItemName = result.getString("hi.Name");
					cbItems.addItem(new comboItem(HardwareItemName, Integer.parseInt(HardwareItemID)));
				}	
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
					AgentItems window = new AgentItems();
					window.frmAgentsItems.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AgentItems() {
		initialize();
		getDataAgents();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAgentsItems = new JFrame();
		frmAgentsItems.setTitle("Agent's items");
		frmAgentsItems.setBounds(100, 100, 756, 502);
		frmAgentsItems.getContentPane().setLayout(null);
		
		JLabel lblAgent = new JLabel("Agent");
		lblAgent.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblAgent.setBounds(10, 11, 46, 14);
		frmAgentsItems.getContentPane().add(lblAgent);
				
		cbAgent.setBounds(93, 8, 289, 20);
		cbAgent.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				getDataForSelectedAgent();
			}
		});
		frmAgentsItems.getContentPane().add(cbAgent);
		
		JLabel lblItem = new JLabel("Item");
		lblItem.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblItem.setBounds(10, 79, 46, 14);
		frmAgentsItems.getContentPane().add(lblItem);
		cbItems.setFont(new Font("Calibri", Font.PLAIN, 11));
				
		cbItems.setBounds(93, 76, 289, 20);
		frmAgentsItems.getContentPane().add(cbItems);
		
		JTree tree = new JTree();
		tree.setBounds(10, 151, 372, 302);
		frmAgentsItems.getContentPane().add(tree);
		
		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					ConnectDatabase cdb = new ConnectDatabase();					
					int AgentID = ((comboItem)cbAgent.getSelectedItem()).value;
					int ItemsID = ((comboItem)cbItems.getSelectedItem()).value;		        
					cdb.st.executeUpdate("INSERT INTO agent_items (AgentID, ItemID) VALUES (" + Integer.toString(AgentID) + "," + Integer.toString(ItemsID) + ")");
					System.out.println("1 row affected");
				} catch (SQLException ex) {System.out.println("SQL statement is not executed!"+ex);}
			}
		});
		btnAddItem.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnAddItem.setBounds(293, 107, 89, 23);
		frmAgentsItems.getContentPane().add(btnAddItem);
		
		JLabel lblAgentModalities = new JLabel("Agent modalities");
		lblAgentModalities.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblAgentModalities.setBounds(10, 44, 89, 14);
		frmAgentsItems.getContentPane().add(lblAgentModalities);
		cbAgentModality.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbAgentModality.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				System.out.println("modal");
				getDataForSelectedModality();
			}
		});
				
		cbAgentModality.setBounds(94, 39, 289, 20);
		frmAgentsItems.getContentPane().add(cbAgentModality);
	}
}
