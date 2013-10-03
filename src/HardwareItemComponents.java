import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

@SuppressWarnings("serial")
public class HardwareItemComponents extends JFrame{
	private JFrame frame;
	private JTextField txtCompName;
	private JTextField txtStateName;
	private JTextField txtTransName;
	JComboBox<comboItem> cbComponent = new JComboBox<comboItem>();
	JComboBox<comboItem> cbStateFrom = new JComboBox<comboItem>();
	JComboBox<comboItem> cbStateTo = new JComboBox<comboItem>();
	JComboBox<comboItem> cbEvents = new JComboBox<comboItem>();
	JComboBox<comboItem> cbHardwareItem = new JComboBox<comboItem>();
	JComboBox<comboItem> cbHardwareItemComponent = new JComboBox<comboItem>();	
	JComboBox<comboItem> cbTransComponents = new JComboBox<comboItem>();
	JComboBox<comboItem> cbModalityType = new JComboBox<comboItem>();
	private DynamicTree treePanelHardwareItems;
	
	private void populateTreeHardwareItems(DynamicTree treePanel) {
        DefaultMutableTreeNode p1, p2;
        try	{
        	if (cbHardwareItem.getSelectedItem() != null){
        		treePanel.clear();
        		ConnectDatabase cdb = new ConnectDatabase();
        		ConnectDatabase cdb2 = new ConnectDatabase();
        		ConnectDatabase cdb3 = new ConnectDatabase();
        		String Query = "select mt.idmodality_types, mt.ModalityTypeName from hardware_items hi "
					+ " join hardware_items_modalities him on hi.idhardware_items = him.HardwareItemID "
					+ " join modality_types mt on mt.idmodality_types = him.ModalityTypeID "
					+ " where hi.idhardware_items = " + Integer.toString(((comboItem)cbHardwareItem.getSelectedItem()).value);
        		System.out.println(Query);
        		ResultSet result = cdb.st.executeQuery(Query);
        		while(result.next()) {
        			p1 = treePanel.addObject(null, result.getString("ModalityTypeName"));
        			String QuerySeq = "select c.Name, c.idcomponents from hardware_items hi join hardware_items_components hic on hi.idhardware_items = hic.HardwareItemID "
								+ " join components c on hic.ComponentID = c.idComponents where hi.idhardware_items = "+ Integer.toString(((comboItem)cbHardwareItem.getSelectedItem()).value) + " and c.ModalityTypeID = "+ result.getString("idmodality_types");
        			ResultSet resultSeq = cdb2.st.executeQuery(QuerySeq);
        			while(resultSeq.next()) {
        				p2 = treePanel.addObject(p1, resultSeq.getString("c.Name"));
        				String QuerySt = "select Name from comp_states where ComponentID = " + resultSeq.getString("c.idcomponents");
        				ResultSet resultSt = cdb3.st.executeQuery(QuerySt);
        				while(resultSt.next()) {
        					treePanel.addObject(p2, resultSt.getString("Name"));
        				}
        			}
        		}	
        		treePanel.expand();
        		cdb.st.close();
        		cdb2.st.close();
        		cdb3.st.close();
        	}
		}
		catch(SQLException ex){System.out.print(ex);}
    }
	
	private void getDataStatesForComponents(){
		try{
			cbStateFrom.removeAllItems();			  
			cbStateTo.removeAllItems();
			
			if (cbTransComponents.getSelectedItem() != null){
				ConnectDatabase cdb = new ConnectDatabase();
				String Query = "SELECT idcomp_states, Name FROM comp_states WHERE ComponentID = " +Integer.toString(((comboItem)cbTransComponents.getSelectedItem()).value);
				System.out.println(Query);
				ResultSet result = cdb.st.executeQuery(Query);
				cbStateFrom.addItem(new comboItem("", -1));
				cbStateTo.addItem(new comboItem("", -1));
				while(result.next()) { 
					cbStateFrom.addItem(new comboItem(result.getString("Name"), result.getInt("idcomp_states")));
					cbStateTo.addItem(new comboItem(result.getString("Name"), result.getInt("idcomp_states")));
				}
				cdb.st.close();
			}
			
		}
		catch( Exception e ) {
			e.printStackTrace();
		}
		
	}
	
	private void getDataComponentsForHardwareItem(){
		try{
			cbHardwareItemComponent.removeAllItems();
			cbHardwareItemComponent.addItem(new comboItem("", -1));
			if (cbHardwareItem.getSelectedItem() != null){		
				ConnectDatabase cdb = new ConnectDatabase(); 
				ConnectDatabase cdb2 = new ConnectDatabase();
				ResultSet resultModalities = cdb.st.executeQuery("select ModalityTypeID from hardware_items hi join hardware_items_modalities him on hi.idhardware_items = him.HardwareItemID where hi.idhardware_items = " + Integer.toString(((comboItem)cbHardwareItem.getSelectedItem()).value));	
				while(resultModalities.next()) { 
					String Query = "select idComponents, Name from components  where ModalityTypeID = " + resultModalities.getString("ModalityTypeID");
					System.out.println(Query);
					ResultSet result = cdb2.st.executeQuery(Query);									
					while(result.next()) { 
						cbHardwareItemComponent.addItem(new comboItem(result.getString("Name"), result.getInt("idComponents")));				
					}
				}
				cdb.st.close();
				cdb2.st.close();
			}			
		}
		catch( Exception e ) {
			e.printStackTrace();
		}
		
	}
    
	private void getData(){
		try { 
			  cbTransComponents.removeAllItems();
			  cbComponent.removeAllItems();
			  cbHardwareItem.removeAllItems();
			  cbHardwareItemComponent.removeAllItems();
			  cbEvents.removeAllItems();
			  cbModalityType.removeAllItems();
			  
			  ConnectDatabase cdb = new ConnectDatabase();
		      ResultSet result = cdb.st.executeQuery("SELECT idcomponents, Name FROM components");
		      cbComponent.addItem(new comboItem("", -1));		      
		      cbTransComponents.addItem(new comboItem("", -1));		      
			  while(result.next()) { 
				  cbComponent.addItem(new comboItem(result.getString("Name"), result.getInt("idcomponents")));
				  cbTransComponents.addItem(new comboItem(result.getString("Name"), result.getInt("idcomponents")));				  
		      }
		 	 		      		      
		      result = cdb.st.executeQuery("SELECT idmodality_types, ModalityTypeName FROM modality_types");
		      cbModalityType.addItem(new comboItem("", -1));
			  while(result.next()) { 
				  cbModalityType.addItem(new comboItem(result.getString("ModalityTypeName"), result.getInt("idmodality_types")));
		      }
		      
			  result = cdb.st.executeQuery("SELECT idevents, EventName FROM events");
		      cbEvents.addItem(new comboItem("", -1));
			  while(result.next()) { 
				  cbEvents.addItem(new comboItem(result.getString("EventName"), result.getInt("idevents")));
		      }
			  
			  
			  result = cdb.st.executeQuery("SELECT idhardware_items, Name FROM hardware_items");			  
			  cbHardwareItem.addItem(new comboItem("", -1));
			  while(result.next()) { 
				  cbHardwareItem.addItem(new comboItem(result.getString("Name"), result.getInt("idhardware_items")));				  
		      }
		      cdb.st.close();	
		    }
		    catch( Exception e ) {
		      e.printStackTrace();
		}
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HardwareItemComponents window = new HardwareItemComponents();
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
	public HardwareItemComponents() {		
		initialize();
		getData();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 405, 779);
		frame.getContentPane().setLayout(null);
		
		JLabel lblHardwareItem = new JLabel("Hardware Item");
		lblHardwareItem.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblHardwareItem.setBounds(8, 474, 89, 14);
		frame.getContentPane().add(lblHardwareItem);
				
		cbHardwareItem.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbHardwareItem.setBounds(100, 471, 252, 20);
		frame.getContentPane().add(cbHardwareItem);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblName.setBounds(8, 55, 46, 14);
		frame.getContentPane().add(lblName);
		
		txtCompName = new JTextField();
		txtCompName.setFont(new Font("Calibri", Font.PLAIN, 11));
		txtCompName.setBounds(100, 53, 115, 20);
		frame.getContentPane().add(txtCompName);
		txtCompName.setColumns(10);
		
		JButton btnAddComponent = new JButton("Add component");
		btnAddComponent.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnAddComponent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConnectDatabase cdb = new ConnectDatabase();
		        System.out.println("After Connect Database!");		        		       
		        try {
		            cdb.st.executeUpdate("INSERT INTO components (Name, ModalityTypeID) VALUES ('"+txtCompName.getText()+"',"+Integer.toString(((comboItem)cbModalityType.getSelectedItem()).value)+ ")");
		            System.out.println("1 row affected");		           
		        } catch (SQLException ex) {
		        	System.out.println("SQL statement is not executed!"+ex);
		        }
		        getData();		
			}
		});
		btnAddComponent.setBounds(231, 51, 115, 23);
		frame.getContentPane().add(btnAddComponent);
		
		JLabel lblComponents_1 = new JLabel("Components:");
		lblComponents_1.setFont(new Font("Calibri", Font.BOLD, 12));
		lblComponents_1.setBounds(8, 11, 104, 14);
		frame.getContentPane().add(lblComponents_1);
		
		JLabel lblStates = new JLabel("States:");
		lblStates.setFont(new Font("Calibri", Font.BOLD, 12));
		lblStates.setBounds(8, 84, 46, 14);
		frame.getContentPane().add(lblStates);
		
		JLabel lblName_1 = new JLabel("Name");
		lblName_1.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblName_1.setBounds(8, 134, 46, 14);
		frame.getContentPane().add(lblName_1);
		
		txtStateName = new JTextField();
		txtStateName.setFont(new Font("Calibri", Font.PLAIN, 11));
		txtStateName.setBounds(100, 131, 115, 20);
		frame.getContentPane().add(txtStateName);
		txtStateName.setColumns(10);			
				
		JLabel lblComponent = new JLabel("Component");
		lblComponent.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblComponent.setBounds(8, 109, 89, 14);
		frame.getContentPane().add(lblComponent);
		
		JLabel lblTransitions = new JLabel("Transitions:");
		lblTransitions.setFont(new Font("Calibri", Font.BOLD, 12));
		lblTransitions.setBounds(8, 175, 89, 14);
		frame.getContentPane().add(lblTransitions);
		
		txtTransName = new JTextField();
		txtTransName.setFont(new Font("Calibri", Font.PLAIN, 11));
		txtTransName.setColumns(10);
		txtTransName.setBounds(100, 215, 252, 20);
		frame.getContentPane().add(txtTransName);
					
		cbEvents.setFont(new Font("Calibri", Font.PLAIN, 11));		
		cbEvents.setBounds(100, 385, 252, 20);
		frame.getContentPane().add(cbEvents);
		
		JLabel label = new JLabel("Event");
		label.setFont(new Font("Calibri", Font.PLAIN, 11));
		label.setForeground(Color.BLACK);
		label.setBounds(8, 388, 28, 14);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("Action");
		label_1.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_1.setForeground(Color.BLACK);
		label_1.setBounds(8, 344, 30, 12);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("Condition");
		label_2.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_2.setForeground(Color.BLACK);
		label_2.setBounds(8, 302, 82, 12);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("State to");
		label_3.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_3.setForeground(Color.BLACK);
		label_3.setBounds(8, 271, 39, 14);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("State from");
		label_4.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_4.setForeground(Color.BLACK);
		label_4.setBounds(8, 246, 51, 14);
		frame.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("Name");
		label_5.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_5.setForeground(Color.BLACK);
		label_5.setBounds(8, 221, 82, 14);
		frame.getContentPane().add(label_5);
		
		final JTextArea txtCondition = new JTextArea();
		txtCondition.setFont(new Font("Calibri", Font.PLAIN, 11));
		txtCondition.setBounds(100, 296, 252, 36);
		frame.getContentPane().add(txtCondition);
		
		final JTextArea txtAction = new JTextArea();
		txtAction.setFont(new Font("Calibri", Font.PLAIN, 13));
		txtAction.setBounds(100, 338, 252, 36);
		frame.getContentPane().add(txtAction);
		
		JButton btnAddTransition = new JButton("Add Transition");
		btnAddTransition.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnAddTransition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnectDatabase cdb = new ConnectDatabase();
		        System.out.println("After Connect Database!");
		        int StateFromID = ((comboItem)cbStateFrom.getSelectedItem()).value;
		        int StateToID = ((comboItem)cbStateTo.getSelectedItem()).value;
		        int EventID = ((comboItem)cbEvents.getSelectedItem()).value;
		     
		        try {
		        	String Query = "INSERT INTO `test`.`transitions` (`Name`,`StateFrom`,`StateTo`,`Condition`,`Action`,`EventID`) VALUES ('"+txtTransName.getText()+"',"+Integer.toString(StateFromID)+","+Integer.toString(StateToID)+",'"+txtCondition.getText()+"','"+txtAction.getText()+"',"+Integer.toString(EventID)+")";
		        	System.out.println(Query);
		            cdb.st.executeUpdate(Query);
		            System.out.println("1 row affected");		           
		        } catch (SQLException ex) {
		        	System.out.println("SQL statement is not executed!"+ex);
		        }
		        getData();
			}
		});
		btnAddTransition.setBounds(231, 408, 115, 23);
		frame.getContentPane().add(btnAddTransition);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(18, 79, 328, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(18, 162, 328, 2);
		frame.getContentPane().add(separator_1);
		
		JLabel lblCompo = new JLabel("Component");
		lblCompo.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblCompo.setBounds(8, 502, 76, 14);
		frame.getContentPane().add(lblCompo);
				
		//JTree tree = new JTree();
		treePanelHardwareItems = new DynamicTree();
		treePanelHardwareItems.setBounds(8, 564, 340, 166);
		frame.getContentPane().add(treePanelHardwareItems);
		
		cbHardwareItemComponent.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbHardwareItemComponent.setBounds(100, 499, 252, 20);
		cbHardwareItem.addItemListener(new ItemListener() {
		  	public void itemStateChanged(ItemEvent arg0) {
		  		getDataComponentsForHardwareItem();
		  		populateTreeHardwareItems(treePanelHardwareItems);
		  	}
		  });
		frame.getContentPane().add(cbHardwareItemComponent);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Calibri", Font.PLAIN, 11));
		//btnAdd.setBounds(126, 523, 89, 23);
		frame.getContentPane().add(btnAdd);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(24, 438, 328, 2);
		frame.getContentPane().add(separator_2);
		
		JLabel lblCoupling = new JLabel("Coupling of Hardware Item and Components:");
		lblCoupling.setFont(new Font("Calibri", Font.BOLD, 12));
		lblCoupling.setBounds(8, 448, 259, 14);
		frame.getContentPane().add(lblCoupling);
		JButton btnAddState = new JButton("Add state");
		btnAddState.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnAddState.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnectDatabase cdb = new ConnectDatabase();
		        System.out.println("After Connect Database!");
		       
		        try {
		            cdb.st.executeUpdate("INSERT INTO comp_states (ComponentID, Name) VALUES ("+Integer.toString(((comboItem)cbComponent.getSelectedItem()).value)+",'"+txtStateName.getText()+"')");
		            System.out.println("1 row affected state " + txtStateName.getText());		           
		        } catch (SQLException ex) {
		        	System.out.println("SQL statement is not executed!"+ex);
		        }
		        getData();		        
			}
		});
		btnAddState.setBounds(231, 130, 115, 23);
		frame.getContentPane().add(btnAddState);		
		
		cbComponent.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbComponent.setBounds(100, 105, 246, 20);
		frame.getContentPane().add(cbComponent);

		cbStateFrom.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbStateFrom.setBounds(100, 244, 252, 20);
		frame.getContentPane().add(cbStateFrom);
		
		cbStateTo.setFont(new Font("Calibri", Font.PLAIN, 11));		
		cbStateTo.setBounds(100, 269, 252, 20);
		frame.getContentPane().add(cbStateTo);
		
		JLabel lblComponent_1 = new JLabel("Component");
		lblComponent_1.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblComponent_1.setBounds(8, 195, 82, 14);
		frame.getContentPane().add(lblComponent_1);
		
		cbTransComponents.addItemListener(new ItemListener() {
	      	public void itemStateChanged(ItemEvent arg0) {
	      		getDataStatesForComponents();
	      	}
	      });
	      cbTransComponents.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbTransComponents.setBounds(100, 191, 248, 20);
		frame.getContentPane().add(cbTransComponents);
		
		JButton btnNewButton = new JButton("Add coupling");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConnectDatabase cdb = new ConnectDatabase();
		        System.out.println("After Connect Database!");
		       
		        try {
		            cdb.st.executeUpdate("INSERT INTO hardware_items_components (ComponentID, HardwareItemID) VALUES ("+Integer.toString(((comboItem)cbHardwareItemComponent.getSelectedItem()).value)+","+Integer.toString(((comboItem)cbHardwareItem.getSelectedItem()).value)+")");
		            System.out.println("1 row affected state ");		           
		        } catch (SQLException ex) {
		        	System.out.println("SQL statement is not executed!"+ex);
		        }
		        getData();
		        populateTreeHardwareItems(treePanelHardwareItems);
			}
		});
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton.setBounds(231, 530, 115, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblModalityType = new JLabel("Modality type");
		lblModalityType.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblModalityType.setBounds(8, 30, 76, 14);
		frame.getContentPane().add(lblModalityType);
		
		
		cbModalityType.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbModalityType.setBounds(100, 26, 246, 20);
		frame.getContentPane().add(cbModalityType);
	}
}
