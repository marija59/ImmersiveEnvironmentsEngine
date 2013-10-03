import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JTextPane;

import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.tree.DefaultMutableTreeNode;

import java.awt.Button;

import javax.swing.SwingConstants;

class comboItem  
{  
  String name;  
  int value;  
  public comboItem(String n, int v)  
  {  
    name = n; value = v;  
  } 
  public String toString(){return name;} 
}

public class Sentences extends JPanel{

	private JFrame frmTellMeA;	
	JComboBox<comboItem> cbSubject = new JComboBox<comboItem>();
	JComboBox<comboItem> cbVerb = new JComboBox<comboItem>();
	JComboBox<comboItem> cbObject = new JComboBox<comboItem>();
	JComboBox<comboItem> cbLocation = new JComboBox<comboItem>();
	JComboBox<comboItem> cbLocationObject = new JComboBox<comboItem>();	
	JTextPane textPane = new JTextPane();
	private JTextField txtSubjectName;
	private JTextField txtPlaceName;
	private JTextField txtEventName;
	private JTextField txtSeqName;
	private JTextField txtConName;
	private int TypeSub=3;
	private int TypeEvent=3;
	private DynamicTree treePanelHardwareItems;
	private DynamicTree treePanelItems;
	BufferedImage image;  
	private static JPanel imagePanel;  
	private static JFrame frame;  
	private String imagePath; 
	JLabel lblImage = new JLabel("Storyboard image");
	
	public BufferedImage getImageById(int id) {
		String query = "select StoryBoardImage from sentences where idSentences = ?";
		BufferedImage buffimg = null;
		
		try {
			ConnectDatabase c = new ConnectDatabase();
			PreparedStatement stmt = c.con.prepareStatement(query);
			stmt.setInt(1,id);
			ResultSet result = stmt.executeQuery();
			result.next();
			System.out.println("before Blob");
			Blob b = result.getBlob(1);    
	        ImageIcon i = new ImageIcon(b.getBytes( 1L, (int) b.length() ) );
	        lblImage.setIcon(i);
		}
		catch(Exception ex) {
			//System.out.println(ex.getMessage());
			System.out.print(ex);
		}
		return buffimg;
	}
	
	public void LoadAnshowImageFromFile() {  
	       try {  
	          /** 
	           * ImageIO.read() returns a BufferedImage object, decoding the supplied   
	           * file with an ImageReader, chosen automatically from registered files  
	           * The File is wrapped in an ImageInputStream object, so we don't need 
	           * one. Null is returned, If no registered ImageReader claims to be able 
	           * to read the resulting stream. 
	           */  
	    	   JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(Sentences.this);

	           if (returnVal == JFileChooser.APPROVE_OPTION) {
	               File file = fc.getSelectedFile();
	               image = ImageIO.read(new File(file.getPath()));  
	               imagePath = file.getPath();
	           }
	       } catch (IOException e) {  
	           //Let us know what happened  
	           System.out.println("Error reading dir: " + e.getMessage());  
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
					String QueryComp = "select c.Name, mt.ModalityTypeName from hardware_items_components hic "
							+ " join hardware_items hi on hi.idhardware_items = hic.HardwareItemID "
							+ " join hardware_items_modalities him on him.HardwareItemID = hi.idhardware_items "
							+ " join modality_types mt on mt.idmodality_types = him.ModalityTypeID "
							+ " join components c on c.idcomponents = hic.ComponentID and c.ModalityTypeID = mt.idmodality_types where hi.idhardware_items = " + result.getString("idhardware_items");
					ResultSet resultComp = cdb2.st.executeQuery(QueryComp);
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
		//int StoryID = 1; //((comboItem)cbSequences.getSelectedItem()).value;
		String Query = "select * from " 
					+ " ( SELECT story_chronology.idstory_chronology as id, idSentences as SenConcID, concat(subjects.SubjectName, ' ', events.EventName, IFNULL(o.SubjectName, '')) as NameEvSeq, 1 as Type" 
					+ " from sentences left join subjects on sentences.SubjectID = subjects.idsubjects " 
					+ " left join subjects o on sentences.ObjectID = o.idsubjects " 
					+ " left join events  on sentences.VerbID = events.idevents " 
					+ " left join places on sentences.LocationID = places.idplaces " 
					+ " left join places po on sentences.LocationObjectID = po.idplaces " 
					+ " join story_chronology on story_chronology.EventSeqConID = sentences.idSentences and story_chronology.EventSeqConTypeID = 1 "
					+ " union " 
					+ " select story_chronology.idstory_chronology as id, idsequence as SenConcID, NameSeq as NameEvSeq, 3 as Type " 
					+ " from sequences join story_chronology on story_chronology.EventSeqConID = sequences.idsequence and story_chronology.EventSeqConTypeID = 3 "
					+ " union "
					+ " select story_chronology.idstory_chronology as id, idconcurrences as SenConcID, NameConc as NameEvSeq, 2 as Type " 
					+ " from concurrences join story_chronology on story_chronology.EventSeqConID = concurrences.idconcurrences and story_chronology.EventSeqConTypeID = 2"
					+ " ) as results "
					+ " order by id";
		System.out.println(Query);
		try	{
			ResultSet result = cdb.st.executeQuery(Query);			  			  
			String NameEvSeq, SenSeqConID, Type;
			treePanel.addObject(null, image);
			
			while(result.next()) {
				NameEvSeq = result.getString("NameEvSeq");	
				SenSeqConID = result.getString("SenConcID");
				Type = result.getString("Type");
				System.out.println("result 1 query 1");
				p1 = treePanel.addObject(null, NameEvSeq);
				if (Type.equals("3")){
					
					String QuerySeq = "select * from "
								+ " ( SELECT seq_events.idseq_events as id, subjects.SubjectTypesID, concat(subjects.SubjectName, ' ', events.EventName, IFNULL(o.SubjectName, '')) as NameEvSeq, seq_events.Time  as SeqTime"  
								+ " from sequences 	join seq_events on sequences.idsequence = seq_events.SequenceID "  
								+ " left join sentences on sentences.idSentences = seq_events.EventConID " 	
								+ " left join subjects on sentences.SubjectID = subjects.idsubjects "  
								+ " left join subjects o on sentences.ObjectID = o.idsubjects "  
								+ " left join events  on sentences.VerbID = events.idevents "  
								+ " left join places on sentences.LocationID = places.idplaces "  
								+ " left join places po on sentences.LocationObjectID = po.idplaces "   
								+ " where sequences.idsequence = " + SenSeqConID +  " and seq_events.TypeID = 1 " 
								+ " union " 
								+ " select seq_events.idseq_events as id, 3 as SybjectTypesID, concurrences.NameConc as NameEvSeq,  seq_events.Time  as SeqTime "
								+ " from sequences 	join seq_events on sequences.idsequence = seq_events.SequenceID "  
								+ " join concurrences on concurrences.idconcurrences = seq_events.EventConID "
								+ " where sequences.idsequence =  " + SenSeqConID +  " and seq_events.TypeID = 2 ) as result "
								+ " order by id ";
					ResultSet resultSeq = cdb2.st.executeQuery(QuerySeq);			  			  
					String NameSeq;						
					while(resultSeq.next()) {
						NameSeq = resultSeq.getString("NameEvSeq");	
						//SenSeqConID = result.getString("SenConcID");
						//Type = result.getString("Type");
						treePanel.addObject(p1, NameSeq);
					}
				}	
				if (Type.equals("2")){
					
					String QueryConc = "select * from "
							+ " ( SELECT concurr_events.idconcurr_events as id, subjects.SubjectTypesID, concat(subjects.SubjectName, ' ', events.EventName, IFNULL(o.SubjectName, '')) as NameEvSeq "
							+ " from concurrences 	join concurr_events on concurrences.idconcurrences = concurr_events.ConcurrenceID " 
							+ " left join sentences on sentences.idSentences = concurr_events.EventConID " 
							+ " left join subjects on sentences.SubjectID = subjects.idsubjects " 
							+ " left join subjects o on sentences.ObjectID = o.idsubjects " 
							+ " left join events  on sentences.VerbID = events.idevents " 
							+ " left join places on sentences.LocationID = places.idplaces " 
							+ " left join places po on sentences.LocationObjectID = po.idplaces " 
							+ " where concurrences.idconcurrences = " + SenSeqConID +  " and concurr_events.EventConTypeID = 1 " 
							+ " union " 
							+ " select concurr_events.idconcurr_events as id, 3 as SybjectTypesID, sequences.NameSeq as NameEvSeq" 
							+ " from concurrences 	join concurr_events on concurrences.idconcurrences = concurr_events.ConcurrenceID " 
							+ " join sequences on sequences.idsequence = concurr_events.EventConID " 
							+ " where concurrences.idconcurrences =  " + SenSeqConID +  " and concurr_events.EventConTypeID = 3) as result " 
							+ " order by id ";
					System.out.println(QueryConc);
					ResultSet resultCon = cdb3.st.executeQuery(QueryConc);			  			  
					String NameCon;						
					while(resultCon.next()) {
						NameCon = resultCon.getString("NameEvSeq");	
						//SenSeqConID = result.getString("SenConcID");
						//Type = result.getString("Type");
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
		
		ConnectDatabase cdb = new ConnectDatabase();        
		String Query = "SELECT IFNULL(subjects.SubjectTypesID,'-1') as SubjectTypesID, subjects.SubjectName, places.PlaceName, events.EventName, IFNULL(o.SubjectTypesID,'') as objTypesID, IFNULL(o.SubjectName, '') as ObjectName, IFNULL(po.PlaceName, '') as ObjPlaceName from sentences "
			+ " left join subjects on sentences.SubjectID = subjects.idsubjects"
		+ " left join subjects o on sentences.ObjectID = o.idsubjects"
		+ " left join events  on sentences.VerbID = events.idevents"
		+ " left join places on sentences.LocationID = places.idplaces" 
		+ " left join places po on sentences.LocationObjectID = po.idplaces ";
		try	{
			ResultSet result = cdb.st.executeQuery(Query);			  			  
			String SubType = "-1", SubName, SubPlaceName, Verb, ObjName, ObjPlaceName, ObjType= "-1";	
			textPane.setText("");
			while(result.next()) {
				SubType = result.getString("SubjectTypesID");
				ObjType = result.getString("objTypesID");				
				SubName = result.getString("subjects.SubjectName");
				SubPlaceName = result.getString("places.PlaceName");
				Verb = result.getString("events.EventName");
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sentences window = new Sentences();
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
	public Sentences() {		
		initialize();
		getData();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		System.out.println("INIT 1");
		frmTellMeA = new JFrame();		
		frmTellMeA.setTitle("Tell me a story!");
		frmTellMeA.setBounds(100, 100, 1295, 885);
		frmTellMeA.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTellMeA.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Agent/Prop/Participant");
		label.setFont(new Font("Calibri", Font.PLAIN, 11));
		label.setForeground(Color.BLACK);
		label.setBounds(20, 888, 155, 14);
		frmTellMeA.getContentPane().add(label);
		
		JButton button = new JButton("Add new");
		button.setFont(new Font("Calibri", Font.PLAIN, 11));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] args = new String[0];
				DefineSubject.main(args);
				getData();
			}
		});		
		button.setBounds(172, 884, 89, 23);
		frmTellMeA.getContentPane().add(button);
		
		JLabel label_1 = new JLabel("Place");
		label_1.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_1.setForeground(Color.BLACK);
		label_1.setBounds(20, 922, 46, 14);
		frmTellMeA.getContentPane().add(label_1);
		
		JButton button_1 = new JButton("Add new");
		button_1.setFont(new Font("Calibri", Font.PLAIN, 11));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] args = new String[0];
				DefinePlace.main(args);
				getData();
			}
		});
		button_1.setBounds(172, 918, 89, 23);
		frmTellMeA.getContentPane().add(button_1);
		
		JLabel label_2 = new JLabel("Event");
		label_2.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_2.setForeground(Color.BLACK);
		label_2.setBounds(20, 956, 46, 14);
		frmTellMeA.getContentPane().add(label_2);
		
		JButton button_2 = new JButton("Add new");
		button_2.setFont(new Font("Calibri", Font.PLAIN, 11));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] args = new String[0];
				DefineEvent.main(args);
				getData();
			}
		});
		
		button_2.setBounds(172, 952, 89, 23);
		frmTellMeA.getContentPane().add(button_2);
		
		JLabel label_3 = new JLabel("Sentences:");
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("Calibri", Font.BOLD, 13));
		label_3.setBounds(6, 65, 89, 14);
		frmTellMeA.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("Subject");
		label_4.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_4.setForeground(Color.BLACK);
		label_4.setBounds(6, 90, 46, 14);
		frmTellMeA.getContentPane().add(label_4);
				
		//cbSubject.setBounds(6, 97, 145, 20);
		//frmTellMeA.getContentPane().add(cbSubject);
		
		JLabel label_5 = new JLabel("Verb");
		label_5.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_5.setForeground(Color.BLACK);
		label_5.setBounds(171, 90, 46, 14);
		frmTellMeA.getContentPane().add(label_5);
		
		cbSubject.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbSubject.setBounds(6, 116, 145, 20);
		frmTellMeA.getContentPane().add(cbSubject);
						
		cbVerb.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbVerb.setBounds(171, 116, 145, 20);
		frmTellMeA.getContentPane().add(cbVerb);
		
		cbObject.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbObject.setBounds(336, 116, 141, 20);
		frmTellMeA.getContentPane().add(cbObject);
		
		JLabel label_6 = new JLabel("Object");
		label_6.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_6.setForeground(Color.BLACK);
		label_6.setBounds(336, 90, 46, 14);
		frmTellMeA.getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("Location");
		label_7.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_7.setForeground(Color.BLACK);
		label_7.setBounds(6, 138, 46, 14);
		frmTellMeA.getContentPane().add(label_7);
		
		cbLocation.setFont(new Font("Calibri", Font.PLAIN, 11));
		cbLocation.setBounds(6, 156, 145, 20);
		frmTellMeA.getContentPane().add(cbLocation);		
		
		JButton btnAddSentence = new JButton("Add sentence");
		btnAddSentence.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnAddSentence.addActionListener(new ActionListener() {
			@SuppressWarnings("resource")
			public void actionPerformed(ActionEvent e) {
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
			}
		});
				
		btnAddSentence.setBounds(368, 184, 109, 23);
		frmTellMeA.getContentPane().add(btnAddSentence);		
				
		cbLocationObject.setFont(new Font("Calibri", Font.PLAIN, 11));		
		cbLocationObject.setBounds(336, 156, 141, 20);
		frmTellMeA.getContentPane().add(cbLocationObject);
		
		JLabel label_8 = new JLabel("Location");
		label_8.setForeground(Color.BLACK);
		label_8.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_8.setBounds(336, 138, 46, 14);
		frmTellMeA.getContentPane().add(label_8);
		
		JButton btnNewButton = new JButton("Add Time");
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] args = new String[0];
				Time.main(args);
			}
		});
		btnNewButton.setBounds(271, 884, 121, 23);
		frmTellMeA.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Define Events");
		btnNewButton_1.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] args = new String[0];
				Agents.main(args);
			}
		});
		btnNewButton_1.setBounds(657, 329, 104, 23);
		frmTellMeA.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Define agent");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] args = new String[0];
				AgentItems.main(args);
			}
		});
		btnNewButton_2.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton_2.setBounds(657, 98, 104, 23);
		frmTellMeA.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Define Place");
		btnNewButton_3.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton_3.setBounds(656, 184, 104, 23);
		frmTellMeA.getContentPane().add(btnNewButton_3);
		
		JRadioButton rdbtnMust = new JRadioButton("MUST");
		rdbtnMust.setFont(new Font("Calibri", Font.PLAIN, 11));
		rdbtnMust.setBounds(414, 884, 63, 23);
		frmTellMeA.getContentPane().add(rdbtnMust);
		
		JRadioButton rdbtnMay = new JRadioButton("MAY");
		rdbtnMay.setFont(new Font("Calibri", Font.PLAIN, 11));
		rdbtnMay.setBounds(414, 918, 109, 23);
		frmTellMeA.getContentPane().add(rdbtnMay);
		
		JRadioButton rdbtnForbidden = new JRadioButton("FORBIDDEN");
		rdbtnForbidden.setFont(new Font("Calibri", Font.PLAIN, 11));
		rdbtnForbidden.setBounds(522, 879, 109, 23);
		frmTellMeA.getContentPane().add(rdbtnForbidden);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(505, 127, 369, 2);
		frmTellMeA.getContentPane().add(separator);
		
		JButton btnCreateSequence = new JButton("New sequence");
		btnCreateSequence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] args = new String[0];
				Sequence.main(args);
			}
		});
		btnCreateSequence.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnCreateSequence.setBounds(271, 913, 121, 23);
		frmTellMeA.getContentPane().add(btnCreateSequence);
		
		JButton btnNewButton_4 = new JButton("Define seq");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] args = new String[0];
				UpdateSequence.main(args);
			}
		});
		btnNewButton_4.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton_4.setBounds(657, 561, 104, 23);
		frmTellMeA.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("New concurrence");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] args = new String[0];
				Concurrent.main(args);
			}
		});
		btnNewButton_5.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton_5.setBounds(271, 947, 121, 23);
		frmTellMeA.getContentPane().add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Define conc");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] args = new String[0];
				UpdateConcurrence.main(args);
			}
		});
		btnNewButton_6.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton_6.setBounds(657, 643, 104, 23);
		frmTellMeA.getContentPane().add(btnNewButton_6);
		
		JLabel label_9 = new JLabel("Name");		
		label_9.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_9.setBounds(505, 33, 46, 14);
		frmTellMeA.getContentPane().add(label_9);
		
		txtSubjectName = new JTextField();
		txtSubjectName.setFont(new Font("Calibri", Font.PLAIN, 10));
		txtSubjectName.setColumns(10);
		txtSubjectName.setBounds(542, 29, 311, 20);
		frmTellMeA.getContentPane().add(txtSubjectName);
		
		JLabel label_10 = new JLabel("Type");		
		label_10.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_10.setBounds(506, 55, 24, 14);
		frmTellMeA.getContentPane().add(label_10);
		
		final JRadioButton rbParticipant = new JRadioButton("Participant");		
		rbParticipant.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				TypeSub = 0;
			}
		});
		rbParticipant.setFont(new Font("Calibri", Font.PLAIN, 11));
		rbParticipant.setBounds(542, 54, 106, 23);
		frmTellMeA.getContentPane().add(rbParticipant);
		
		final JRadioButton rbAgent = new JRadioButton("Agent");
		rbAgent.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				TypeSub = 1;
			}
		});
		rbAgent.setFont(new Font("Calibri", Font.PLAIN, 11));
		
		rbAgent.setBounds(542, 72, 55, 23);
		frmTellMeA.getContentPane().add(rbAgent);
		
		final JRadioButton rbProp = new JRadioButton("Prop");
		rbProp.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				TypeSub = 2;
			}
		});
		rbProp.setFont(new Font("Calibri", Font.PLAIN, 11));
			rbProp.setBounds(542, 90, 89, 23);
		frmTellMeA.getContentPane().add(rbProp);
		
		 //Group the radio buttons.
	    final ButtonGroup TypeSubject = new ButtonGroup();
	    TypeSubject.add(rbParticipant);
	    TypeSubject.add(rbAgent);
	    TypeSubject.add(rbProp);
		
		JButton btnAddSubject = new JButton("Add agent");
		btnAddSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		btnAddSubject.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnAddSubject.setBounds(764, 98, 89, 23);
		frmTellMeA.getContentPane().add(btnAddSubject);
		
		JLabel label_11 = new JLabel("Name");		
		label_11.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_11.setBounds(505, 156, 35, 14);
		frmTellMeA.getContentPane().add(label_11);
		
		txtPlaceName = new JTextField();
		txtPlaceName.setFont(new Font("Calibri", Font.PLAIN, 10));
		txtPlaceName.setColumns(10);
		txtPlaceName.setBounds(542, 152, 311, 20);
		frmTellMeA.getContentPane().add(txtPlaceName);
		
		JButton btnAddPlace = new JButton("Add place");		
		btnAddPlace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		btnAddPlace.setBounds(764, 184, 89, 23);
		frmTellMeA.getContentPane().add(btnAddPlace);
		
		JLabel lblNewLabel = new JLabel("Place");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 11));
		lblNewLabel.setBounds(505, 137, 46, 14);
		frmTellMeA.getContentPane().add(lblNewLabel);
		
		JLabel lblSubject = new JLabel("Characters");
		lblSubject.setFont(new Font("Calibri", Font.BOLD, 11));
		lblSubject.setBounds(505, 8, 126, 14);
		frmTellMeA.getContentPane().add(lblSubject);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(505, 218, 369, 2);
		frmTellMeA.getContentPane().add(separator_1);
		
		JLabel label_12 = new JLabel("Event");		
		label_12.setFont(new Font("Calibri", Font.BOLD, 11));
		label_12.setBounds(505, 227, 35, 15);
		frmTellMeA.getContentPane().add(label_12);
		
		JLabel label_13 = new JLabel("Name");		
		label_13.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_13.setBounds(505, 253, 44, 14);
		frmTellMeA.getContentPane().add(label_13);
		
		txtEventName = new JTextField();
		txtEventName.setFont(new Font("Calibri", Font.PLAIN, 10));
		txtEventName.setColumns(10);
		txtEventName.setBounds(542, 249, 311, 20);
		frmTellMeA.getContentPane().add(txtEventName);
		
		JLabel label_14 = new JLabel("Produced by:");		
		label_14.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_14.setBounds(505, 278, 115, 14);
		frmTellMeA.getContentPane().add(label_14);
		
		JRadioButton rbUser = new JRadioButton("User");		
		rbUser.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				TypeEvent = 0;
			}
		});
		rbUser.setFont(new Font("Calibri", Font.PLAIN, 11));
		rbUser.setBounds(542, 290, 93, 23);
		frmTellMeA.getContentPane().add(rbUser);
		
		JRadioButton rbInterEnv = new JRadioButton("Interactive environment");
		rbInterEnv.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				TypeEvent = 1;
			}
		});
		rbInterEnv.setFont(new Font("Calibri", Font.PLAIN, 11));
		rbInterEnv.setBounds(542, 308, 149, 23);
		frmTellMeA.getContentPane().add(rbInterEnv);
		
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
						TypeEvents.clearSelection();
						txtEventName.setText("");
					}
				}
				else {JOptionPane.showMessageDialog(null, "Please choose a type of events!");}
			}
		});
		btnAddEvent.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnAddEvent.setBounds(764, 329, 89, 23);
		frmTellMeA.getContentPane().add(btnAddEvent);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(505, 487, 369, 2);
		frmTellMeA.getContentPane().add(separator_2);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setFont(new Font("Calibri", Font.BOLD, 11));
		lblTime.setBounds(505, 491, 46, 14);
		frmTellMeA.getContentPane().add(lblTime);
		
		JLabel label_15 = new JLabel("Name");
		label_15.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_15.setBounds(505, 530, 46, 14);
		frmTellMeA.getContentPane().add(label_15);
		
		txtSeqName = new JTextField();
		txtSeqName.setFont(new Font("Calibri", Font.PLAIN, 10));
		txtSeqName.setColumns(10);
		txtSeqName.setBounds(542, 530, 311, 20);
		frmTellMeA.getContentPane().add(txtSeqName);
		
		JButton btnAddSequence = new JButton("Add seq");
		btnAddSequence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtSeqName.getText().equals("")){JOptionPane.showMessageDialog(null, "Please enter a name!");}
				else {	
					ConnectDatabase cdb = new ConnectDatabase();
					System.out.println("After Connect Database!");
					int EventID = -1; //((comboItem)cbTriggerEvent.getSelectedItem()).value;		     
					try {
						cdb.st.executeUpdate("INSERT INTO sequences (NameSeq, TriggeredByEvent) VALUES ('"+txtSeqName.getText()+"',"+Integer.toString(EventID)+")");
						System.out.println("1 row affected");		           
					} catch (SQLException ex) {
						System.out.println("SQL statement is not executed!"+ex);
					}
					getData();
					txtSeqName.setText("");
				}
			}
		});
		btnAddSequence.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnAddSequence.setBounds(764, 561, 92, 23);
		frmTellMeA.getContentPane().add(btnAddSequence);
		
		JLabel lblSequence = new JLabel("Coupling of events in a sequence");
		lblSequence.setFont(new Font("Calibri", Font.BOLD, 11));
		lblSequence.setBounds(505, 511, 155, 14);
		frmTellMeA.getContentPane().add(lblSequence);
		
		JLabel label_16 = new JLabel("Name");
		label_16.setFont(new Font("Calibri", Font.PLAIN, 11));
		label_16.setBounds(505, 616, 46, 14);
		frmTellMeA.getContentPane().add(label_16);
		
		txtConName = new JTextField();
		txtConName.setFont(new Font("Calibri", Font.PLAIN, 10));
		txtConName.setColumns(10);
		txtConName.setBounds(542, 612, 311, 20);
		frmTellMeA.getContentPane().add(txtConName);
		
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
					txtConName.setText("");
				}
			}
		});
		btnAddCon.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnAddCon.setBounds(764, 643, 92, 23);
		frmTellMeA.getContentPane().add(btnAddCon);
		
		JLabel lblNewLabel_1 = new JLabel("Coupling of stimuli");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 11));
		lblNewLabel_1.setBounds(505, 592, 143, 14);
		frmTellMeA.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Title:");
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 12));
		lblNewLabel_2.setBounds(6, 7, 46, 14);
		frmTellMeA.getContentPane().add(lblNewLabel_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(6, 55, 457, 2);
		frmTellMeA.getContentPane().add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(20, 487, 457, 2);
		frmTellMeA.getContentPane().add(separator_4);
		
		JButton btnNewButton_7 = new JButton("Add Item");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] args = new String[0];
				HardwareItem.main(args);
				populateTreeItems(treePanelItems);
			}
		});
		btnNewButton_7.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton_7.setBounds(890, 380, 89, 23);
		frmTellMeA.getContentPane().add(btnNewButton_7);
		
		JLabel lblNewLabel_3 = new JLabel("Define hardware and software items");
		lblNewLabel_3.setFont(new Font("Calibri", Font.BOLD, 11));
		lblNewLabel_3.setBounds(880, 8, 233, 14);
		frmTellMeA.getContentPane().add(lblNewLabel_3);
		
		JButton btnNewButton_8 = new JButton("Define Item");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] args = new String[0];
				HardwareItemComponents.main(args);
				populateTreeItems(treePanelItems);
				
			}
		});
		btnNewButton_8.setFont(new Font("Calibri", Font.PLAIN, 11));
		btnNewButton_8.setBounds(989, 380, 89, 23);
		frmTellMeA.getContentPane().add(btnNewButton_8);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(890, 360, 369, 2);
		frmTellMeA.getContentPane().add(separator_5);
		
		treePanelItems = new DynamicTree();		
		treePanelItems.setBounds(890, 31, 369, 321);
		treePanelItems.setFont(new Font("Calibri", Font.PLAIN, 11));
		frmTellMeA.getContentPane().add(treePanelItems);
		populateTreeItems(treePanelItems);
		
		textPane.setFont(new Font("Calibri", Font.PLAIN, 11));
		textPane.setEditable(false);
		//textPane.setBounds(26, 339, 471, 195);
		final JScrollPane sp = new JScrollPane();
		sp.setBounds(6, 500, 471, 295);
		frmTellMeA.getContentPane().add(sp);
		sp.setViewportView(textPane);
		
		treePanelHardwareItems = new DynamicTree();		
		treePanelHardwareItems.setBounds(6, 500, 471, 295);
		frmTellMeA.getContentPane().add(treePanelHardwareItems);
		
		JButton btnChronology = new JButton("Chronology");
		btnChronology.setFont(new Font("Calibri", Font.PLAIN, 10));
		btnChronology.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				populateTree(treePanelHardwareItems);
				sp.setViewportView(treePanelHardwareItems);
			}
		});
		btnChronology.setBounds(6, 806, 89, 23);
		frmTellMeA.getContentPane().add(btnChronology);
		
		JButton btnSentences = new JButton("Sentences");
		btnSentences.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sp.setViewportView(textPane);
			}
		});
		btnSentences.setFont(new Font("Calibri", Font.PLAIN, 10));
		btnSentences.setBounds(105, 806, 89, 23);
		frmTellMeA.getContentPane().add(btnSentences);
		
		//final JLabel lblImage = new JLabel("Storyboard image");
		lblImage.setToolTipText("Click Storyboard image button to select an image for the sentence");
		lblImage.setFont(new Font("Calibri", Font.PLAIN, 10));
		lblImage.setBackground(Color.LIGHT_GRAY);
		lblImage.setForeground(Color.BLACK);
		lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblImage.setBounds(6, 218, 471, 258);
		frmTellMeA.getContentPane().add(lblImage);
		
		JButton btnChooseStoryboard = new JButton("Storyboard image");
		btnChooseStoryboard.setFont(new Font("Calibri", Font.PLAIN, 10));
		btnChooseStoryboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoadAnshowImageFromFile();
				ImageIcon icon = new ImageIcon(image);
				lblImage.setIcon(icon);
				//lblImage.setBounds(10, 226, image.getWidth(null), image.getHeight(null));
//				frame = new JFrame("Loading Image From File Example");    
//		        imagePanel = new JPanel();  
//		        //Release the resource window handle as we close the frame  
//		        frame.addWindowListener(new WindowAdapter(){  
//		                public void windowClosing(WindowEvent e) {  
//		                    System.exit(0);  
//		                }  
//		            });  
//		        imagePanel.add(new LoadAnshowImageFromFile());  
//		        frame.add(imagePanel);  
//		        frame.pack();  
//		        frame.setVisible(true); 
			}
		});
		btnChooseStoryboard.setBounds(249, 183, 109, 23);
		frmTellMeA.getContentPane().add(btnChooseStoryboard);
		
		JButton btnNewButton_9 = new JButton("Storyboard");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 String[] args = new String[0];
				 ImageList.main(args);
			}
		});
		btnNewButton_9.setFont(new Font("Calibri", Font.PLAIN, 10));
		btnNewButton_9.setBounds(204, 805, 89, 23);
		frmTellMeA.getContentPane().add(btnNewButton_9);
		
		
	
	
						
		
		
	}
	
	
}
