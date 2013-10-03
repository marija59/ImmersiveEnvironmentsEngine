import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.mysql.jdbc.Blob;

import javax.swing.JLabel;
import javax.swing.JTextPane;


public class Storyboard {

	private JFrame frame;
	JLabel lblImage = new JLabel("New label");
	JTextPane textPane = new JTextPane();
	CardLayout cl = new CardLayout();
	
	private void populateTree() throws IOException {
	    ConnectDatabase cdb = new ConnectDatabase();
	    ConnectDatabase cdb2 = new ConnectDatabase();
	    ConnectDatabase cdb3 = new ConnectDatabase(); 
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
		try	{
			ResultSet result = cdb.st.executeQuery(Query);			  			  
			String NameEvSeq, SenSeqConID, Type;
			
			while(result.next()) {
				NameEvSeq = result.getString("NameEvSeq");	
				SenSeqConID = result.getString("SenConcID");
				Type = result.getString("Type");
				System.out.println("result 1 query 1");
				if (Type.equals("3")){
					
					String QuerySeq = "select * from "
								+ " ( SELECT seq_events.idseq_events as id, subjects.SubjectTypesID, concat(subjects.SubjectName, ' ', events.EventName, IFNULL(o.SubjectName, '')) as NameEvSeq, seq_events.Time  as SeqTime, sentences.StoryBoardImage as StoryBoardImage"  
								+ " from sequences 	join seq_events on sequences.idsequence = seq_events.SequenceID "  
								+ " left join sentences on sentences.idSentences = seq_events.EventConID " 	
								+ " left join subjects on sentences.SubjectID = subjects.idsubjects "  
								+ " left join subjects o on sentences.ObjectID = o.idsubjects "  
								+ " left join events  on sentences.VerbID = events.idevents "  
								+ " left join places on sentences.LocationID = places.idplaces "  
								+ " left join places po on sentences.LocationObjectID = po.idplaces "   
								+ " where sequences.idsequence = " + SenSeqConID +  " and seq_events.TypeID = 1 " 
								+ " union " 
								+ " select seq_events.idseq_events as id, 3 as SybjectTypesID, concurrences.NameConc as NameEvSeq,  seq_events.Time  as SeqTime, null as StoryBoardImage "
								+ " from sequences 	join seq_events on sequences.idsequence = seq_events.SequenceID "  
								+ " join concurrences on concurrences.idconcurrences = seq_events.EventConID "
								+ " where sequences.idsequence =  " + SenSeqConID +  " and seq_events.TypeID = 2 ) as result "
								+ " order by id ";
					ResultSet resultSeq = cdb2.st.executeQuery(QuerySeq);			  			  
					String NameSeq;						
					while(resultSeq.next()) {
						NameSeq = resultSeq.getString("NameEvSeq");	
						Blob b = (Blob) resultSeq.getBlob(5); 
						ImageIcon i = new ImageIcon(b.getBytes( 1L, (int) b.length() ) );				        
				        lblImage.setIcon(i);
				        JLabel jLabel = new JLabel();
				        jLabel.setIcon(i);
				        cl.addLayoutComponent(jLabel, "jLabel"+i);
				        frame.getContentPane().add(jLabel);
				        System.out.println("result 1 query 2");
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
					}
				}	
			}
			cdb.st.close();
			cdb2.st.close();
			cdb3.st.close();	
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
					Storyboard window = new Storyboard();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public Storyboard() throws IOException {
		initialize();
		populateTree();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 861, 655);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		
		
		
		
//		lblImage.setBounds(10, 22, 274, 189);
//		frame.getContentPane().add(lblImage);
//		
//		
//		textPane.setBounds(10, 222, 464, 341);
//		frame.getContentPane().add(textPane);
	}
}
