import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTree;

import java.awt.BorderLayout;

import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.imageio.ImageIO;
import javax.swing.JTextPane;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

@SuppressWarnings("serial")
public class TellMeAStory extends JPanel{

	private JFrame frmTellMeA;
	JComboBox<comboItem> cbSubject = new JComboBox<comboItem>();
	JComboBox<comboItem> cbVerb = new JComboBox<comboItem>();
	JComboBox<comboItem> cbObject = new JComboBox<comboItem>();
	JComboBox<comboItem> cbLocation = new JComboBox<comboItem>();
	JComboBox<comboItem> cbLocationObject = new JComboBox<comboItem>();	
	JComboBox<comboItemSeq> cbEvents = new JComboBox<comboItemSeq>();
	JTextPane textPane = new JTextPane();
	private JTextField txtSubjectName;
	private JTextField txtPlaceName;
	private JTextField txtEventName;
	//private JTextField txtSeqName;
	//private JTextField txtConName;
	private int TypeSub=-1;
	private int TypeEvent=-1;
	private int isMustMay=-1;
	private DynamicTree treePanelHardwareItems;
	private DynamicTree treePanelItems;
	private DynamicTree treePanelChronology;
	BufferedImage image;  
	//private static JPanel imagePanel;  
	//private static JFrame frame;  
	private String imagePath; 
	JLabel lblImage = new JLabel("Storyboard image");
	final JScrollPane sp = new JScrollPane();
	private JTextField txtSeqName;
	private JTextField txtConName;
	
	public void LoadAnshowImageFromFile() {  
	       try {
	    	   JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(TellMeAStory.this);

	           if (returnVal == JFileChooser.APPROVE_OPTION) {
	               File file = fc.getSelectedFile();
	               image = ImageIO.read(new File(file.getPath()));  
	               imagePath = file.getPath();
	           }
	       } catch (IOException e) {  
  
	           System.out.println("Error reading dir: " + e.getMessage());  
	       }  
	  
	    }  
	protected ImageIcon createImageIcon(String path,
            String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
	
	private void populateTreeItems(DynamicTree treePanel) {
     DefaultMutableTreeNode p1;
     treePanel.clear();
     ConnectDatabase cdb = new ConnectDatabase();
     ConnectDatabase cdb2 = new ConnectDatabase();
		try	{
			String Query = "select idhardware_items, Name from hardware_items";
			ResultSet result = cdb.st.executeQuery(Query);
			while(result.next()) {
					p1 = treePanel.addObject(null, result.getString("Name"));
					CallableStatement cs = cdb.con.prepareCall("{call SelectModalityTypesByItemID("+result.getString("idhardware_items")+")}");
					ResultSet resultComp = cs.executeQuery(); 
					while(resultComp.next()) {
						treePanel.addObject(p1, resultComp.getString("mt.ModalityTypeName") + ":  " + resultComp.getString("c.Name"));
					}
			}	
			cdb.st.close();
 		cdb2.st.close();    			
			treePanel.expand();
		}
		catch(SQLException ex){System.out.print(ex);}
 }
	
	
	private void populateTree(DynamicTree treePanel) {
     DefaultMutableTreeNode p1;
     treePanel.clear();
     ConnectDatabase cdb = new ConnectDatabase();
     ConnectDatabase cdb2 = new ConnectDatabase();
     ConnectDatabase cdb3 = new ConnectDatabase();    
		try	{
			CallableStatement cs = cdb.con.prepareCall("{call SelectChronology()}");
			ResultSet result = cs.executeQuery(); 			  			  
			String NameEvSeq, SenSeqConID, Type;
			treePanel.addObject(null, image);
			
			while(result.next()) {
				NameEvSeq = result.getString("NameEvSeq");	
				SenSeqConID = result.getString("SenConcID");
				Type = result.getString("Type");
				System.out.println("result 1 query 1");
				p1 = treePanel.addObject(null, NameEvSeq);
				if (Type.equals("3")){
					cs = cdb.con.prepareCall("{call SelectSentenceConSeqByID(" + SenSeqConID + ")}");
					ResultSet resultSeq = cs.executeQuery();
					String NameSeq;						
					while(resultSeq.next()) {
						NameSeq = resultSeq.getString("NameEvSeq");	
						treePanel.addObject(p1, NameSeq);
					}
				}	
				if (Type.equals("2")){
					cs = cdb.con.prepareCall("{call SelectSentenceConSeqByIDType(" + SenSeqConID + ")}");
					ResultSet resultCon = cs.executeQuery();
					String NameCon;						
					while(resultCon.next()) {
						NameCon = resultCon.getString("NameEvSeq");	
						treePanel.addObject(p1, NameCon);
					}
				}	
			}
			cdb.st.close();
 		cdb2.st.close();
 		cdb3.st.close();	
			treePanel.expand();
		}
		catch(SQLException ex){System.out.print(ex);}
 }
	
	
	private void getData(){
		try {			  
			  cbSubject.removeAllItems();
			  cbObject.removeAllItems();
			  cbVerb.removeAllItems();
			  cbLocation.removeAllItems();
			  cbLocationObject.removeAllItems();
			  			  
			  ConnectDatabase cdb = new ConnectDatabase();
		        
		      ResultSet result = cdb.st.executeQuery("SELECT idsubjects, SubjectName FROM subjects");
		      cbSubject.addItem(new comboItem("", -1));
		      cbObject.addItem(new comboItem("", -1));
		 	  while(result.next()) {		        
		        cbSubject.addItem(new comboItem(result.getString("SubjectName"), result.getInt("idsubjects")));
		        cbObject.addItem(new comboItem(result.getString("SubjectName"), result.getInt("idsubjects")));
		      }
		 	 		      		      
		      result = cdb.st.executeQuery("SELECT idevents, EventName FROM events");
		      cbVerb.addItem(new comboItem("", -1));
			  while(result.next()) { 
		        cbVerb.addItem(new comboItem(result.getString("EventName"), result.getInt("idevents")));
		      }	  

			  result = cdb.st.executeQuery("SELECT idplaces, PlaceName FROM places");
		      cbLocation.addItem(new comboItem("", -1));
		      cbLocationObject.addItem(new comboItem("", -1));
			  while(result.next()) {
		        cbLocation.addItem(new comboItem(result.getString("PlaceName"), result.getInt("idplaces")));
		        cbLocationObject.addItem(new comboItem(result.getString("PlaceName"), result.getInt("idplaces")));
		      }		  
		      cdb.st.close();	
		    }
		    catch( Exception e ) {
		      e.printStackTrace();
		}
		
		try	{
			ConnectDatabase cdb = new ConnectDatabase();      
			CallableStatement cs = cdb.con.prepareCall("{call SelectSentences()}");
			ResultSet result = cs.executeQuery();
			String SubType = "-1", SubName, SubPlaceName, Verb, ObjName, ObjPlaceName, ObjType= "-1";	
			textPane.setText("");
			while(result.next()) {
				SubType = result.getString("SubjectTypesID");
				ObjType = result.getString("objTypesID");				
				SubName = result.getString("SubjectName");
				SubPlaceName = result.getString("PlaceName");
				Verb = result.getString("EventName");
				ObjName = result.getString("ObjectName");
				ObjPlaceName = result.getString("ObjPlaceName");				
				StyledDocument doc = textPane.getStyledDocument();
				System.out.println(SubName);
				System.out.println(SubType);
				try {
					Style style = textPane.addStyle("Style", null);
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
				catch (BadLocationException ex){}			
			}
			cdb.st.close();
		}
		catch(SQLException ex){System.out.print(ex);}
		
	}
	
	private void getDataTimeEvents(){
		cbEvents.removeAllItems();
		try	{
			ConnectDatabase cdb = new ConnectDatabase();
			CallableStatement cs = cdb.con.prepareCall("{call SelectSentenceConSeq()}");
			ResultSet result = cs.executeQuery();
			cbEvents.addItem(new comboItemSeq("", -1, -1));
			while(result.next()) {
			    cbEvents.addItem(new comboItemSeq(result.getString("NameEvConc"), result.getInt("SenConcID"), result.getInt("Type")));
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
					TellMeAStory window = new TellMeAStory();
					window.frmTellMeA.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TellMeAStory() {
		initialize();
		getData();
		getDataTimeEvents();
		sp.setViewportView(textPane);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTellMeA = new JFrame();
		frmTellMeA.setTitle("Tell me a story!");
		frmTellMeA.setBounds(100, 100, 534, 853);
		frmTellMeA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel card1 = new JPanel();	    
		JPanel card2 = new JPanel();	    
	    JPanel card3 = new JPanel();
	    JPanel card4 = new JPanel();
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setFont(new Font("Calibri", Font.PLAIN, 11));
		tabbedPane.addTab("-		Who What Where		-", card1);
		card1.setLayout(null);
		
		JButton btnDefineEvents = new JButton("Define Events");
		btnDefineEvents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] args = new String[0];
				Agents.main(args);
			}
		});
		btnDefineEvents.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnDefineEvents.setBounds(214, 32, 104, 23);
		card3.add(btnDefineEvents);
		
		JButton btnDefineCharacter = new JButton("Define agent");
		btnDefineCharacter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] args = new String[0];
				AgentItems.main(args);
			}
		});
		btnDefineCharacter.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnDefineCharacter.setBounds(214, 94, 104, 23);
		card3.add(btnDefineCharacter);
		
		JButton btnDefinePlace = new JButton("Define Place");
		btnDefinePlace.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnDefinePlace.setBounds(214, 150, 104, 23);
		card3.add(btnDefinePlace);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 260, 458, 2);
		card1.add(separator);
		
		JLabel lblChName = new JLabel("Name");
		lblChName.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblChName.setBounds(10, 99, 46, 14);
		card1.add(lblChName);
		
		txtSubjectName = new JTextField();
		txtSubjectName.setFont(new Font("Calibri", Font.PLAIN, 10));
		txtSubjectName.setColumns(10);
		txtSubjectName.setBounds(47, 95, 219, 20);
		card1.add(txtSubjectName);
		
		JLabel lblChType = new JLabel("Type");
		lblChType.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblChType.setBounds(11, 121, 24, 14);
		card1.add(lblChType);
		
		JRadioButton rbParticipant = new JRadioButton("Participant");
		rbParticipant.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				TypeSub = 0;
			}
		});
		rbParticipant.setFont(new Font("Calibri", Font.PLAIN, 11));
		rbParticipant.setBounds(47, 120, 106, 23);
		card1.add(rbParticipant);
		
		JRadioButton rbAgent = new JRadioButton("Agent");
		rbAgent.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				TypeSub = 1;
			}
		});
		rbAgent.setFont(new Font("Calibri", Font.PLAIN, 11));
		rbAgent.setBounds(47, 138, 55, 23);
		card1.add(rbAgent);
		
		JRadioButton rbProp = new JRadioButton("Prop");
		rbProp.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				TypeSub = 2;
			}
		});
		rbProp.setFont(new Font("Calibri", Font.PLAIN, 11));
		rbProp.setBounds(47, 156, 89, 23);
		card1.add(rbProp);
		
		final ButtonGroup TypeSubject = new ButtonGroup();
	    TypeSubject.add(rbParticipant);
	    TypeSubject.add(rbAgent);
	    TypeSubject.add(rbProp);
		
		JButton btnAddCharacter = new JButton("Add agent");
		btnAddCharacter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (TypeSubject.getSelection() != null )
				{
					if (txtSubjectName.getText().equals("")){JOptionPane.showMessageDialog(null, "Please enter a name!");}
					else {						
						ConnectDatabase cdb = new ConnectDatabase();
						System.out.println("After Connect Database!");		        
						try {
							cdb.st.executeUpdate("INSERT INTO subjects (SubjectName, SubjectTypesID) VALUES ('"+txtSubjectName.getText()+"',"+TypeSub+")");
							System.out.println("1 row affected");		           
						} catch (SQLException ex) {
							System.out.println("SQL statement is not executed!"+ex);
						} 
						getData();						
						TypeSubject.clearSelection();
						txtSubjectName.setText("");
					}
				}
				else {JOptionPane.showMessageDialog(null, "Please choose a type of subject!");}
			}
		});
		btnAddCharacter.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnAddCharacter.setBounds(269, 95, 89, 23);
		card1.add(btnAddCharacter);
		
		JLabel lblPlName = new JLabel("Name");
		lblPlName.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblPlName.setBounds(10, 289, 35, 14);
		card1.add(lblPlName);
		
		txtPlaceName = new JTextField();
		txtPlaceName.setFont(new Font("Calibri", Font.PLAIN, 10));
		txtPlaceName.setColumns(10);
		txtPlaceName.setBounds(47, 285, 219, 20);
		card1.add(txtPlaceName);
		
		JButton btnAddPlace = new JButton("Add place");
		btnAddPlace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtPlaceName.getText().equals("")){JOptionPane.showMessageDialog(null, "Please enter a name!");}
				else {	
					ConnectDatabase cdb = new ConnectDatabase();
					System.out.println("After Connect Database!");
					try {
						cdb.st.executeUpdate("INSERT INTO places (PlaceName) VALUES ('"+txtPlaceName.getText()+"')");
						System.out.println("1 row affected");		           
					} catch (SQLException ex) {
						System.out.println("SQL statement is not executed!"+ex);
					} 
					getData();
					txtPlaceName.setText("");
				}
			}
		});
		btnAddPlace.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnAddPlace.setBounds(269, 285, 89, 23);
		card1.add(btnAddPlace);
		
		JLabel lblPlace = new JLabel("Place");
		lblPlace.setFont(new Font("Calibri", Font.BOLD, 12));
		lblPlace.setBounds(10, 270, 46, 14);
		card1.add(lblPlace);
		
		JLabel lblCharacters = new JLabel("Characters");
		lblCharacters.setFont(new Font("Calibri", Font.BOLD, 12));
		lblCharacters.setBounds(10, 74, 126, 14);
		card1.add(lblCharacters);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 548, 458, 2);
		card1.add(separator_1);
		
		JLabel lblEvent = new JLabel("Event");
		lblEvent.setFont(new Font("Calibri", Font.BOLD, 12));
		lblEvent.setBounds(10, 557, 35, 15);
		card1.add(lblEvent);
		
		JLabel lblEvName = new JLabel("Name");
		lblEvName.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblEvName.setBounds(10, 627, 44, 14);
		card1.add(lblEvName);
		
		txtEventName = new JTextField();
		txtEventName.setFont(new Font("Calibri", Font.PLAIN, 10));
		txtEventName.setColumns(10);
		txtEventName.setBounds(47, 623, 219, 20);
		card1.add(txtEventName);
		
		JLabel lblBy = new JLabel("By");
		lblBy.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblBy.setBounds(10, 652, 115, 14);
		card1.add(lblBy);
		
		JRadioButton rbUser = new JRadioButton("User");
		rbUser.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				TypeEvent = 0;
			}
		});
		rbUser.setFont(new Font("Calibri", Font.PLAIN, 11));
		rbUser.setBounds(47, 664, 93, 23);
		card1.add(rbUser);
		
		JRadioButton rbInterEnv = new JRadioButton("Interactive environment");
		rbInterEnv.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				TypeEvent = 1;
			}
		});
		rbInterEnv.setFont(new Font("Calibri", Font.PLAIN, 11));
		rbInterEnv.setBounds(47, 682, 149, 23);
		card1.add(rbInterEnv);
		
		final ButtonGroup TypeEvents = new ButtonGroup();
		TypeEvents.add(rbUser);
		TypeEvents.add(rbInterEnv);
		
		JButton btnAddEvent = new JButton("Add event");
		btnAddEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if (TypeEvents.getSelection() != null )
					{
						if (txtEventName.getText().equals("")){JOptionPane.showMessageDialog(null, "Please enter a name!");}
						else {	
							ConnectDatabase cdb = new ConnectDatabase();
							System.out.println("After Connect Database!");
							try {
								cdb.st.executeUpdate("INSERT INTO events (EventName, EventTypesID) VALUES ('"+txtEventName.getText()+"',"+TypeEvent+")");
								System.out.println("1 row affected");		           
							} catch (SQLException ex) {
								System.out.println("SQL statement is not executed!"+ex);
							} 
							getData();
							getDataTimeEvents();
							TypeEvents.clearSelection();
							txtEventName.setText("");
						}
					}
					else {JOptionPane.showMessageDialog(null, "Please choose a type of events!");}
				}
		});
		btnAddEvent.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnAddEvent.setBounds(269, 623, 89, 23);
		card1.add(btnAddEvent);
		
		JLabel lblTitle = new JLabel("Title:");
		lblTitle.setFont(new Font("Calibri", Font.BOLD, 12));
		lblTitle.setBounds(10, 11, 46, 14);
		card1.add(lblTitle);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(10, 65, 458, 2);
		card1.add(separator_4);
		
		JLabel label1 = new JLabel("New label");
		label1.setBounds(90, 10, 46, 14);
		card1.add(label1);
		
		JRadioButton rbEvent = new JRadioButton("Event");
		rbEvent.setBounds(10, 579, 65, 23);
		card1.add(rbEvent);
		
		JRadioButton rbAction = new JRadioButton("Action");
		rbAction.setBounds(77, 579, 109, 23);
		card1.add(rbAction);
        tabbedPane.addTab("-		With		-", card2);
        card2.setLayout(null);
        ImageIcon icon = createImageIcon("images/middle.gif", "a pretty but meaningless splat");
        label1 = new JLabel("Image and Text", icon, JLabel.CENTER);
        
        JLabel label_8 = new JLabel("Sentences:");
        label_8.setForeground(Color.BLACK);
        label_8.setFont(new Font("Calibri", Font.BOLD, 13));
        label_8.setBounds(10, 11, 89, 14);
        card2.add(label_8);
        
        JLabel label_9 = new JLabel("Subject");
        label_9.setForeground(Color.BLACK);
        label_9.setFont(new Font("Calibri", Font.PLAIN, 11));
        label_9.setBounds(10, 36, 46, 14);
        card2.add(label_9);
        
        JLabel label_10 = new JLabel("Verb");
        label_10.setForeground(Color.BLACK);
        label_10.setFont(new Font("Calibri", Font.PLAIN, 11));
        label_10.setBounds(175, 36, 46, 14);
        card2.add(label_10);
        
        cbSubject.setFont(new Font("Calibri", Font.PLAIN, 11));
        cbSubject.setBounds(10, 62, 145, 20);
        card2.add(cbSubject);
        
        cbVerb.setFont(new Font("Calibri", Font.PLAIN, 11));
        cbVerb.setBounds(175, 62, 145, 20);
        card2.add(cbVerb);
        
        cbObject.setFont(new Font("Calibri", Font.PLAIN, 11));
        cbObject.setBounds(340, 62, 141, 20);
        card2.add(cbObject);
        
        JLabel label_11 = new JLabel("Object");
        label_11.setForeground(Color.BLACK);
        label_11.setFont(new Font("Calibri", Font.PLAIN, 11));
        label_11.setBounds(340, 36, 46, 14);
        card2.add(label_11);
        
        JLabel label_12 = new JLabel("Location");
        label_12.setForeground(Color.BLACK);
        label_12.setFont(new Font("Calibri", Font.PLAIN, 11));
        label_12.setBounds(10, 84, 46, 14);
        card2.add(label_12);
        
        cbLocation.setFont(new Font("Calibri", Font.PLAIN, 11));
        cbLocation.setBounds(10, 102, 145, 20);
        card2.add(cbLocation);
        
        JButton btnAddSentence = new JButton("Add sentence");
        btnAddSentence.addActionListener(new ActionListener() {
        	@SuppressWarnings("resource")
			public void actionPerformed(ActionEvent arg0) {
        		try {
					ConnectDatabase cdb = new ConnectDatabase();
					System.out.println("After Connect Database!");
					PreparedStatement psmnt = null;
					// declare FileInputStream object to store binary stream of given image.
					FileInputStream fis;
					File Saveimage = new File(imagePath);
					fis = new FileInputStream(Saveimage);
					int SubjectID = ((comboItem)cbSubject.getSelectedItem()).value;
					int VerbID = ((comboItem)cbVerb.getSelectedItem()).value;
					int ObjectID = ((comboItem)cbObject.getSelectedItem()).value;
					int LocationID = ((comboItem)cbLocation.getSelectedItem()).value;
					int LocationObjectID = ((comboItem)cbLocationObject.getSelectedItem()).value;
					psmnt = cdb.con.prepareStatement
							("INSERT INTO sentences (SubjectID, VerbID, ObjectID, LocationID, LocationObjectID, StoryBoardImage) "+ "values(?,?,?,?,?,?)");
							psmnt.setString(1,Integer.toString(SubjectID));
							psmnt.setString(2,Integer.toString(VerbID));
							psmnt.setString(3,Integer.toString(ObjectID));
							psmnt.setString(4,Integer.toString(LocationID));
							psmnt.setString(5,Integer.toString(LocationObjectID));
							fis = new FileInputStream(Saveimage);
							psmnt.setBinaryStream(6, (InputStream)fis, (int)(Saveimage.length()));
							/* executeUpdate() method execute specified sql query. Here this query 
							insert data and image from specified address. */ 
					int s = psmnt.executeUpdate();
					if(s>0) {
						System.out.println("Uploaded successfully !");
					}
					else {
						System.out.println("unsucessfull to upload image.");					
					}
		        
		            //cdb.st.executeUpdate("INSERT INTO sentences (SubjectID, VerbID, ObjectID, LocationID, LocationObjectID) VALUES ("+Integer.toString(SubjectID)+","+Integer.toString(VerbID)+","+Integer.toString(ObjectID)+","+Integer.toString(LocationID)+","+Integer.toString(LocationObjectID)+")");
		            System.out.println("1 row affected");		           
		        } catch (SQLException | FileNotFoundException ex) {
		        	System.out.println("SQL statement is not executed!"+ex);
		        }
		        getData();
		        getDataTimeEvents();
        	}
        });
        btnAddSentence.setFont(new Font("Calibri", Font.PLAIN, 11));
        btnAddSentence.setBounds(372, 369, 109, 23);
        card2.add(btnAddSentence);
        
        cbLocationObject.setFont(new Font("Calibri", Font.PLAIN, 11));
        cbLocationObject.setBounds(340, 102, 141, 20);
        card2.add(cbLocationObject);
        
        JLabel label_13 = new JLabel("Location");
        label_13.setForeground(Color.BLACK);
        label_13.setFont(new Font("Calibri", Font.PLAIN, 11));
        label_13.setBounds(340, 84, 46, 14);
        card2.add(label_13);
        
        JSeparator separator_2 = new JSeparator();
        separator_2.setBounds(20, 403, 457, 2);
        card2.add(separator_2);
        
//        DynamicTree dynamicTree = new DynamicTree();
//        dynamicTree.setBounds(10, 446, 471, 295);
//        card2.add(dynamicTree);
//        
//        JScrollPane scrollPane = new JScrollPane();
//        dynamicTree.add(scrollPane);
        
        /*treePanelItems = new DynamicTree();		
		treePanelItems.setBounds(890, 31, 369, 321);
		treePanelItems.setFont(new Font("Calibri", Font.PLAIN, 11));
		card2.add(treePanelItems);
		populateTreeItems(treePanelItems);*/
		
		textPane.setFont(new Font("Calibri", Font.PLAIN, 11));
		textPane.setEditable(false);
		//textPane.setBounds(26, 339, 471, 195);
		sp.setBounds(10, 446, 471, 295);
		card2.add(sp);
		sp.setViewportView(textPane);
		
		treePanelHardwareItems = new DynamicTree();		
		treePanelHardwareItems.setBounds(10, 446, 471, 295);
		card2.add(treePanelHardwareItems);
        
        JButton btnChronology = new JButton("Chronology");
        btnChronology.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		populateTree(treePanelHardwareItems);
				sp.setViewportView(treePanelHardwareItems);
        	}
        });
        btnChronology.setFont(new Font("Calibri", Font.PLAIN, 10));
        btnChronology.setBounds(10, 752, 89, 23);
        card2.add(btnChronology);
        
        JButton btnSentences = new JButton("Sentences");
        btnSentences.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		sp.setViewportView(textPane);
        	}
        });
        btnSentences.setFont(new Font("Calibri", Font.PLAIN, 10));
        btnSentences.setBounds(109, 752, 89, 23);
        card2.add(btnSentences);
        
       
        lblImage.setToolTipText("Click Storyboard image button to select an image for the sentence");
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);
        lblImage.setForeground(Color.BLACK);
        lblImage.setFont(new Font("Calibri", Font.PLAIN, 10));
        lblImage.setBackground(Color.LIGHT_GRAY);
        lblImage.setBounds(10, 133, 338, 258);
        card2.add(lblImage);
        
        JButton btnChooseStoryboard = new JButton("Storyboard image");
        btnChooseStoryboard.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		LoadAnshowImageFromFile();
				ImageIcon icon = new ImageIcon(image);
				lblImage.setIcon(icon);
        	}
        });
        btnChooseStoryboard.setFont(new Font("Calibri", Font.PLAIN, 10));
        btnChooseStoryboard.setBounds(372, 164, 109, 23);
        card2.add(btnChooseStoryboard);
        
        JButton button_10 = new JButton("Storyboard");
        button_10.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String[] args = new String[0];
				ImageList.main(args);
        	}
        });
        button_10.setFont(new Font("Calibri", Font.PLAIN, 10));
        button_10.setBounds(208, 751, 89, 23);
        card2.add(button_10);
        
        tabbedPane.addTab("-		When		-", card4);
        card4.setLayout(null);
        
        JLabel lblTime = new JLabel("Time");
        lblTime.setFont(new Font("Calibri", Font.BOLD, 11));
        lblTime.setBounds(10, 11, 46, 14);
        card4.add(lblTime);
        
        JLabel lblSequence = new JLabel("Coupling of events in a sequence");
        lblSequence.setFont(new Font("Calibri", Font.BOLD, 11));
        lblSequence.setBounds(10, 31, 155, 14);
        card4.add(lblSequence);
        
        JLabel lblSeqName = new JLabel("Name");
        lblSeqName.setFont(new Font("Calibri", Font.PLAIN, 11));
        lblSeqName.setBounds(10, 50, 46, 14);
        card4.add(lblSeqName);
        
        txtSeqName = new JTextField();
        txtSeqName.setFont(new Font("Calibri", Font.PLAIN, 10));
        txtSeqName.setColumns(10);
        txtSeqName.setBounds(47, 50, 155, 20);
        card4.add(txtSeqName);
        
        JButton btnDefSeq = new JButton("Define seq");
        btnDefSeq.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String[] args = new String[0];
				UpdateSequence.main(args);
        	}
        });
        btnDefSeq.setFont(new Font("Calibri", Font.PLAIN, 11));
        btnDefSeq.setBounds(316, 48, 104, 23);
        card4.add(btnDefSeq);
        
        JButton btnAddSequence = new JButton("Add seq");
        btnAddSequence.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (txtSeqName.getText().equals("")){JOptionPane.showMessageDialog(null, "Please enter a name!");}
				else {	
					ConnectDatabase cdb = new ConnectDatabase();
					System.out.println("After Connect Database!");
					int EventID = -1; //((comboItem)cbTriggerEvent.getSelectedItem()).value;		     
					try {
						cdb.st.executeUpdate("INSERT INTO sequences (NameSeq, TriggeredByEvent, isMust) VALUES ('"+txtSeqName.getText()+"',"+Integer.toString(EventID)+","+Integer.toString(isMustMay)+ ")");
						System.out.println("1 row affected");		           
					} catch (SQLException ex) {
						System.out.println("SQL statement is not executed!"+ex);
					}
					getData();
					getDataTimeEvents();
					txtSeqName.setText("");
				}
        	}
        });
        btnAddSequence.setFont(new Font("Calibri", Font.PLAIN, 11));
        btnAddSequence.setBounds(212, 48, 92, 23);
        card4.add(btnAddSequence);
        
        JLabel lblConc = new JLabel("Agents acting together");
        lblConc.setFont(new Font("Calibri", Font.BOLD, 11));
        lblConc.setBounds(10, 112, 143, 14);
        card4.add(lblConc);
        
        JLabel lblConcName = new JLabel("Name");
        lblConcName.setFont(new Font("Calibri", Font.PLAIN, 11));
        lblConcName.setBounds(10, 136, 46, 14);
        card4.add(lblConcName);
        
        txtConName = new JTextField();
        txtConName.setFont(new Font("Calibri", Font.PLAIN, 10));
        txtConName.setColumns(10);
        txtConName.setBounds(47, 132, 155, 20);
        card4.add(txtConName);
        
        JButton btnDefConc = new JButton("Define conc");
        btnDefConc.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String[] args = new String[0];
				UpdateConc.main(args);
        	}
        });
        btnDefConc.setFont(new Font("Calibri", Font.PLAIN, 11));
        btnDefConc.setBounds(316, 132, 104, 23);
        card4.add(btnDefConc);
        
        JButton btnAddCon = new JButton("Add con");
        btnAddCon.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (txtConName.getText().equals("")){JOptionPane.showMessageDialog(null, "Please enter a name!");}
				else {	
					ConnectDatabase cdb = new ConnectDatabase();
					System.out.println("After Connect Database!");
					int EventID = -1; //((comboItem)cbTriggerEvent.getSelectedItem()).value;		     
					try {
						cdb.st.executeUpdate("INSERT INTO concurrences (NameConc, TriggeredByEvent) VALUES ('"+txtConName.getText()+"',"+Integer.toString(EventID)+")");
						System.out.println("1 row affected");		           
					} catch (SQLException ex) {
						System.out.println("SQL statement is not executed!"+ex);
					}
					getData();
					getDataTimeEvents();
					txtConName.setText("");
				}
        	}
        });
        btnAddCon.setFont(new Font("Calibri", Font.PLAIN, 11));
        btnAddCon.setBounds(212, 131, 92, 23);
        card4.add(btnAddCon);
        
        JLabel lblTimeBefore = new JLabel("BEFORE (Inicialize environment)");
        lblTimeBefore.setFont(new Font("Calibri", Font.PLAIN, 11));
        lblTimeBefore.setBounds(10, 189, 198, 14);
        card4.add(lblTimeBefore);
        
        JLabel lblTimeNarrative = new JLabel("INTERACTIVE NARRATIVE");
        lblTimeNarrative.setFont(new Font("Calibri", Font.PLAIN, 11));
        lblTimeNarrative.setBounds(6, 344, 159, 14);
        card4.add(lblTimeNarrative);
        
       
        cbEvents.setFont(new Font("Calibri", Font.PLAIN, 11));
        cbEvents.setBounds(129, 366, 250, 20);
        card4.add(cbEvents);
        
        JRadioButton rbTimeSeqMay = new JRadioButton("MAY");
        rbTimeSeqMay.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusGained(FocusEvent arg0) {
        		isMustMay = 1;
        	}
        });
        rbTimeSeqMay.setFont(new Font("Calibri", Font.PLAIN, 10));
        rbTimeSeqMay.setBounds(6, 391, 59, 23);
        card4.add(rbTimeSeqMay);
        
        JRadioButton rbTimeSeqMust = new JRadioButton("MUST");
        rbTimeSeqMust.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusGained(FocusEvent e) {
        		isMustMay = 2;
        	}
        });
        rbTimeSeqMust.setFont(new Font("Calibri", Font.PLAIN, 10));
        rbTimeSeqMust.setBounds(67, 391, 66, 23);
        card4.add(rbTimeSeqMust);
        
        final ButtonGroup TypeSeq = new ButtonGroup();
        TypeSeq.add(rbTimeSeqMay);
        TypeSeq.add(rbTimeSeqMust);
        
        JButton btnAddSeqcon = new JButton("Add");
        btnAddSeqcon.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			if (TypeSeq.getSelection() != null )
    				{
        				ConnectDatabase cdb = new ConnectDatabase();
        				ConnectDatabase cdb2 = new ConnectDatabase();
        				String Guard="", Action="", Variable="", InitialValue = "0";
        				String SelQuery = "select ifnull(guard, '') as guard, idstory_chronology, concat('G',idstory_chronology) as variable, "
        					+ " concat(ifnull(guard, '') , ' && (G' , idstory_chronology , '==1)') as newguard, 0 as InitialValue "
        					+ " from story_chronology  where isMust = 2 order by idstory_chronology desc limit 1";
        				ResultSet result = cdb.st.executeQuery(SelQuery);
        				if(result.next()) {
        					Guard = result.getString("newguard");
        				}
        			
        				int StoryID = 1; //((comboItem)cbEvents.getSelectedItem()).value;
        				String Query = "INSERT INTO story_chronology (StoryID, EventSeqConID, EventSeqConTypeID, Guard, Action, Variable, InitialValue, isMust) "
		        			+ "VALUES ("+Integer.toString(StoryID)+","+Integer.toString(((comboItemSeq)cbEvents.getSelectedItem()).value)+","+Integer.toString(((comboItemSeq)cbEvents.getSelectedItem()).type)
		        			+",'" + Guard + "','" + Action + "','" + Variable + "','" + InitialValue + "'," + Integer.toString(isMustMay)+")";
        				System.out.println(Query);
        				int val = cdb.st.executeUpdate(Query);
        				System.out.println("1 row affected");		           
		        	
        				String LastIDQuery = "select idstory_chronology from story_chronology order by idstory_chronology desc limit 1";
        				ResultSet resultLastID = cdb.st.executeQuery(LastIDQuery);
        				if(resultLastID.next()) {
        					Action = "G" + Integer.toString(resultLastID.getInt("idstory_chronology"))+"==1";
        					Variable = "G" + Integer.toString(resultLastID.getInt("idstory_chronology"));
        				}
        				String UpdateQuery = "UPDATE story_chronology SET Action = '" +Action+ "', Variable = '" + Variable + "' where idstory_chronology = " + Integer.toString(resultLastID.getInt("idstory_chronology"));
        				System.out.println(UpdateQuery);
        				cdb2.st.executeUpdate(UpdateQuery);
        				System.out.println("1 row affected");		 
    				}
        			else {JOptionPane.showMessageDialog(null, "Please choose does the events MAY or MUST happen!");}
		        } catch (SQLException ex) {
		        	System.out.println("SQL statement is not executed!"+ex);
		        }
		        populateTree(treePanelChronology);
        	}
        });
        btnAddSeqcon.setFont(new Font("Calibri", Font.PLAIN, 11));
        btnAddSeqcon.setBounds(271, 391, 108, 23);
        card4.add(btnAddSeqcon);
        
        treePanelChronology = new DynamicTree();		
        treePanelChronology.setBounds(10, 420, 369, 321);
        treePanelChronology.setFont(new Font("Calibri", Font.PLAIN, 11));
		card4.add(treePanelChronology);
		populateTree(treePanelChronology);
        
        JSeparator separator_5 = new JSeparator();
        separator_5.setBounds(10, 176, 458, 2);
        card4.add(separator_5);
        
        JLabel lblTimeEvSeqCon = new JLabel("Event/Sequence/Concurrence");
        lblTimeEvSeqCon.setFont(new Font("Calibri", Font.PLAIN, 10));
        lblTimeEvSeqCon.setBounds(6, 369, 179, 14);
        card4.add(lblTimeEvSeqCon);
        
        JLabel lblTimeAfter = new JLabel("AFTER (And they lived happily ever after... or ...)");
        lblTimeAfter.setFont(new Font("Calibri", Font.PLAIN, 11));
        lblTimeAfter.setBounds(10, 752, 335, 14);
        card4.add(lblTimeAfter);
        
        
        
        tabbedPane.addTab("-		How		-", card3);
        card3.setLayout(null);
        
        JButton button_11 = new JButton("Add Item");
        button_11.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String[] args = new String[0];
				HardwareItem.main(args);
				//populateTreeItems(treePanelItems);
        	}
        });
        button_11.setFont(new Font("Calibri", Font.PLAIN, 11));
        button_11.setBounds(30, 710, 89, 23);
        card3.add(button_11);
        
        JLabel lblDefHardwareSoftware = new JLabel("Define hardware and software items");
        lblDefHardwareSoftware.setFont(new Font("Calibri", Font.BOLD, 12));
        lblDefHardwareSoftware.setBounds(20, 359, 233, 14);
        card3.add(lblDefHardwareSoftware);
        
        JButton button_12 = new JButton("Define Item");
        button_12.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String[] args = new String[0];
				HardwareItemComponents.main(args);
				//populateTreeItems(treePanelItems);
        	}
        });
        button_12.setFont(new Font("Calibri", Font.PLAIN, 11));
        button_12.setBounds(143, 710, 89, 23);
        card3.add(button_12);
        
        JSeparator separator_3 = new JSeparator();
        separator_3.setBounds(20, 351, 369, 2);
        card3.add(separator_3);
        
        treePanelItems = new DynamicTree();		
        treePanelItems.setBounds(20, 384, 369, 321);
        treePanelItems.setFont(new Font("Calibri", Font.PLAIN, 11));
		card3.add(treePanelItems);
		populateTreeItems(treePanelItems);
		
		JLabel lblNewLabel = new JLabel("Which agent/prop responds to which Event? Who does or sense the action?");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblNewLabel.setBounds(20, 14, 369, 14);
		card3.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Which are the modalities? Hardware and software items?");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(20, 65, 369, 14);
		card3.add(lblNewLabel_1);

		frmTellMeA.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		 
	        
	}
}
