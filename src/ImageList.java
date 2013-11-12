import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;

import com.mysql.jdbc.Blob;
import com.mysql.jdbc.CallableStatement;

class imageItem  
{  
  String image;  
  int value;  
  String text;
  int typeid;
  public imageItem(String i, int v, String t, int t_id)  
  {  
    image = i; value = v; text = t;  typeid = t_id;
  } 
  public int toInt(){return value;} 
}

public class ImageList {

    private JPanel gui;
    private JMenuBar menuBar;
    DefaultListModel<BufferedImage> model; 
    static JFrame f = new JFrame("Image Browser");
    DefaultListModel<imageItem> idListModel; 
    

    ImageList() throws IOException, SQLException {
        gui = new JPanel(new GridLayout());

        JPanel imageViewContainer = new JPanel(new GridBagLayout());
        final JLabel imageView = new JLabel();
        imageViewContainer.add(imageView);
        final JLabel textView = new JLabel();
        imageViewContainer.add(textView);

        model = new DefaultListModel<BufferedImage>(); 
        idListModel = new DefaultListModel<imageItem>();
        final JList<BufferedImage> imageList = new JList<BufferedImage>(model);
        //final JList<imageItem> idList = new JList<imageItem>(idListModel);
        imageList.setCellRenderer(new IconCellRenderer());
        ListSelectionListener listener = new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent lse) {
            	Object o = imageList.getSelectedValue();
                if (o instanceof BufferedImage) {
                    imageView.setIcon(new ImageIcon((BufferedImage)o));
                    for(int i=0; i < idListModel.getSize(); i++){
                    	imageItem idObject =  idListModel.getElementAt(i);  
                        if (idObject.image.equals(o.toString())){
                        	textView.setText(idObject.text);
                        }
                   } 
                }
            }

        };
        imageList.addListSelectionListener(listener);

        gui.add(new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT, 
                new JScrollPane(
                        imageList, 
                        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
                        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), 
                new JScrollPane(imageViewContainer)));
        loadImages();
    }
    
    public void loadImages() throws IOException, SQLException {
        model.removeAllElements();
        ConnectDatabase cdb = new ConnectDatabase();
        CallableStatement cs = (CallableStatement) cdb.con.prepareCall("{call SelectStory()}");
		ResultSet result = cs.executeQuery();
		while(result.next()) {
				Blob b = (Blob) result.getBlob(6);    
				if (b != null) {
					BufferedImage photo = null;
					photo = ImageIO.read(b.getBinaryStream());
					model.addElement(photo);
					idListModel.addElement(new imageItem(photo.toString(), result.getInt("StoryID"), result.getString("Sentence"), result.getInt("TypeID")));
				}
				else {
					Image in = Toolkit.getDefaultToolkit().createImage("D:/eclipse-standard-kepler-R-win32-x86_64/eclipse/workspace/ImmersiveEnvironmentsEngine/src/images/check.gif");
		            BufferedImage photo = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
		            Graphics2D g = photo.createGraphics();
		            g.drawImage(in, 0, 0, null);
		            g.dispose();
					model.addElement(photo);
					idListModel.addElement(new imageItem(photo.toString(), result.getInt("StoryID"), result.getString("Sentence"), result.getInt("TypeID")));
				}
		}
        
        /*ConnectDatabase cdb2 = new ConnectDatabase();
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
   					while(resultSeq.next()) {
   						Blob b = (Blob) resultSeq.getBlob(5);    
   						BufferedImage photo = null;
   						photo = ImageIO.read(b.getBinaryStream());
   						model.addElement(photo);
   						idListModel.addElement(new imageItem(photo.toString(), resultSeq.getInt("id"), resultSeq.getString("NameEvSeq")));
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
						Blob b = (Blob) resultCon.getBlob(5);    
   						BufferedImage photo = null;
   						photo = ImageIO.read(b.getBinaryStream());
   						model.addElement(photo);
   						idListModel.addElement(new imageItem(photo.toString(), resultCon.getInt("id"), resultCon.getString("NameEvSeq")));
					}
   				}
   			}
   		}catch(SQLException ex){System.out.print(ex);}
		*/
    }

    public Container getGui() {
        return gui;
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                ImageList imageList;
				try {
					imageList = new ImageList();
				

               
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.getContentPane().add(imageList.getGui());
                f.setJMenuBar(imageList.getMenuBar());
                f.setLocationByPlatform(true);
                f.pack();
                f.setSize(378,256);
                f.setVisible(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
    }
}

class IconCellRenderer extends DefaultListCellRenderer {

    private static final long serialVersionUID = 1L;

    private int size;
    private BufferedImage icon;

    IconCellRenderer() {
        this(100);
    }

    IconCellRenderer(int size) {
        this.size = size;
        icon = new BufferedImage(size,size,BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public java.awt.Component getListCellRendererComponent(
            JList<?> list, 
            Object value, 
            int index, 
            boolean isSelected, 
            boolean cellHasFocus) {
        java.awt.Component c =  super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (c instanceof JLabel && value instanceof BufferedImage) {
            JLabel l = (JLabel)c;
            l.setText("");
            BufferedImage i = (BufferedImage)value;
            l.setIcon(new ImageIcon(icon));

            Graphics2D g = icon.createGraphics();
            g.setColor(new Color(0,0,0,0));
            g.clearRect(0, 0, size, size);
            g.drawImage(i,0,0,size,size,this);

            g.dispose();
        }
        return c;
    }

    @Override 
    public Dimension getPreferredSize() {
        return new Dimension(size, size);
    }
}