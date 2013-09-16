import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import java.awt.Font;

import javax.swing.JCheckBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JSeparator;


public class HardwareItem {

	private JFrame frmHardware;
	private JTextField txtName;
	JComboBox<comboItem> cbType = new JComboBox<comboItem>();
	JComboBox<comboItem> cbItems = new JComboBox<comboItem>();
	
	private void getDataTypes(){
		try	{
			cbType.removeAllItems();
			ConnectDatabase cdb = new ConnectDatabase();        
			String Query = "select iditems_types, ItemsTypesName from items_types";			
			ResultSet result = cdb.st.executeQuery(Query);
			cbType.addItem(new comboItem("", -1));
			while(result.next()) {
			    cbType.addItem(new comboItem(result.getString("ItemsTypesName"), Integer.parseInt(result.getString("iditems_types"))));	
			}	
		}
		catch(SQLException ex){System.out.print(ex);}
	
	}
	
	private void getDataItems(){
		try	{
			cbItems.removeAllItems();
			ConnectDatabase cdb = new ConnectDatabase();        
			String Query = "select idhardware_items, Name from hardware_items";			
			ResultSet result = cdb.st.executeQuery(Query);
			cbItems.addItem(new comboItem("", -1));
			while(result.next()) {
			    cbItems.addItem(new comboItem(result.getString("Name"), Integer.parseInt(result.getString("idhardware_items"))));	
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
					HardwareItem window = new HardwareItem();
					window.frmHardware.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HardwareItem() {
		initialize();
		getDataTypes();
		getDataItems();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHardware = new JFrame();
		frmHardware.setTitle("Item");
		frmHardware.setBounds(100, 100, 363, 489);
		frmHardware.getContentPane().setLayout(null);
		
		JLabel lblHardwaresoftwareType = new JLabel("Type");
		lblHardwaresoftwareType.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblHardwaresoftwareType.setBounds(6, 12, 151, 14);
		frmHardware.getContentPane().add(lblHardwaresoftwareType);
		
		
		cbType.setBounds(105, 11, 206, 20);
		frmHardware.getContentPane().add(cbType);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblName.setBounds(6, 40, 46, 14);
		frmHardware.getContentPane().add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(105, 37, 206, 20);
		frmHardware.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblDescription.setBounds(6, 77, 70, 14);
		frmHardware.getContentPane().add(lblDescription);
		
		final JTextArea txtDescription = new JTextArea();
		txtDescription.setBounds(105, 72, 206, 74);
		frmHardware.getContentPane().add(txtDescription);
		
		JLabel label = new JLabel("Modalities ");
		label.setFont(new Font("Calibri", Font.PLAIN, 11));
		label.setBounds(6, 252, 212, 14);
		frmHardware.getContentPane().add(label);
		
		final JCheckBox cbActuation = new JCheckBox("Embodied actuation");
		cbActuation.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbActuation.setBounds(6, 273, 138, 23);
		frmHardware.getContentPane().add(cbActuation);
		
		JCheckBox cbSound = new JCheckBox("Sound");
		cbSound.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbSound.setBounds(6, 299, 97, 23);
		frmHardware.getContentPane().add(cbSound);
		
		JCheckBox cbAnimation = new JCheckBox("Animations");
		cbAnimation.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbAnimation.setBounds(6, 325, 97, 23);
		frmHardware.getContentPane().add(cbAnimation);
		
		JCheckBox cbLight = new JCheckBox("Light");
		cbLight.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbLight.setBounds(6, 351, 97, 23);
		frmHardware.getContentPane().add(cbLight);
		
		JCheckBox cbSpecialEffects = new JCheckBox("Special effects");
		cbSpecialEffects.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbSpecialEffects.setBounds(6, 377, 97, 23);
		frmHardware.getContentPane().add(cbSpecialEffects);
		
		JCheckBox cbSee = new JCheckBox("See");
		cbSee.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbSee.setBounds(152, 273, 97, 23);
		frmHardware.getContentPane().add(cbSee);
		
		JCheckBox cbListen = new JCheckBox("Listen");
		cbListen.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbListen.setBounds(152, 299, 97, 23);
		frmHardware.getContentPane().add(cbListen);
		
		JCheckBox checkBox_8 = new JCheckBox("Proximity");
		checkBox_8.setFont(new Font("Calibri", Font.PLAIN, 11));
		checkBox_8.setBounds(152, 325, 97, 23);
		frmHardware.getContentPane().add(checkBox_8);
		
		JCheckBox checkBox_9 = new JCheckBox("Sense");
		checkBox_9.setFont(new Font("Calibri", Font.PLAIN, 11));
		checkBox_9.setBounds(152, 351, 97, 23);
		frmHardware.getContentPane().add(checkBox_9);
		
		JCheckBox checkBox_10 = new JCheckBox("Information");
		checkBox_10.setFont(new Font("Calibri", Font.PLAIN, 11));
		checkBox_10.setBounds(152, 377, 97, 23);
		frmHardware.getContentPane().add(checkBox_10);
		
		JButton btnNewButton = new JButton("Add Item");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ConnectDatabase cdb = new ConnectDatabase();
					System.out.println("After Connect Database!");
					cdb.st.executeUpdate("INSERT INTO hardware_items (Name, HardwareTypeID, Description) VALUES ('" + txtName.getText() + "'," + Integer.toString(((comboItem)cbType.getSelectedItem()).value) + ",'" + txtDescription.getText() + "')");
		            System.out.println("1 row affected");
		            getDataItems();
		            if (cbActuation.isSelected())
		            {   
		            	//cdb.st.executeUpdate("INSERT INTO agent_modalities (AgentID, ModalityTypeID) VALUES (" + Integer.toString(AgentID) + ", 1)");
		            	//System.out.println("1 row affected");
			        }
				} catch (SQLException ex) {System.out.println("SQL statement is not executed!"+ex);}    
			}
		});
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton.setBounds(222, 157, 89, 23);
		frmHardware.getContentPane().add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 200, 312, 2);
		frmHardware.getContentPane().add(separator);
		
		JLabel lblItem = new JLabel("Item");
		lblItem.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblItem.setBounds(6, 213, 46, 14);
		frmHardware.getContentPane().add(lblItem);
		
		
		cbItems.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbItems.setBounds(105, 213, 206, 20);
		frmHardware.getContentPane().add(cbItems);
		
		JButton btnNewButton_1 = new JButton("Add modality");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ConnectDatabase cdb = new ConnectDatabase();
					System.out.println("After Connect Database!");					
		            if (cbActuation.isSelected())
		            {   
		            	cdb.st.executeUpdate("INSERT INTO hardware_items_modalities (HardwareItemID, ModalityTypeID) VALUES (" + Integer.toString(((comboItem)cbItems.getSelectedItem()).value) + ", 1)");
		            	System.out.println("1 row affected");
			        }
				} catch (SQLException ex) {System.out.println("SQL statement is not executed!"+ex);}    
			}
		});
		btnNewButton_1.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton_1.setBounds(222, 407, 89, 23);
		frmHardware.getContentPane().add(btnNewButton_1);
	}
}
