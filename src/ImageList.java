import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.ImageIO;
import com.mysql.jdbc.Blob;

class imageItem  
{  
  String image;  
  int value;  
  public imageItem(String i, int v)  
  {  
    image = i; value = v;  
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
                        	textView.setText(Integer.toString(idObject.value));
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
        String QuerySeq = "select * from "
				+ " ( SELECT seq_events.idseq_events as id, subjects.SubjectTypesID, concat(subjects.SubjectName, ' ', events.EventName, IFNULL(o.SubjectName, '')) as NameEvSeq, seq_events.Time  as SeqTime, sentences.StoryBoardImage as StoryBoardImage"  
				+ " from sequences 	join seq_events on sequences.idsequence = seq_events.SequenceID "  
				+ " left join sentences on sentences.idSentences = seq_events.EventConID " 	
				+ " left join subjects on sentences.SubjectID = subjects.idsubjects "  
				+ " left join subjects o on sentences.ObjectID = o.idsubjects "  
				+ " left join events  on sentences.VerbID = events.idevents "  
				+ " left join places on sentences.LocationID = places.idplaces "  
				+ " left join places po on sentences.LocationObjectID = po.idplaces "   
				+ " where sequences.idsequence = 17 and seq_events.TypeID = 1 " 
				+ " union " 
				+ " select seq_events.idseq_events as id, 3 as SybjectTypesID, concurrences.NameConc as NameEvSeq,  seq_events.Time  as SeqTime, null as StoryBoardImage "
				+ " from sequences 	join seq_events on sequences.idsequence = seq_events.SequenceID "  
				+ " join concurrences on concurrences.idconcurrences = seq_events.EventConID "
				+ " where sequences.idsequence =  17 and seq_events.TypeID = 2 ) as result "
				+ " order by id ";
        ConnectDatabase cdb2 = new ConnectDatabase();
        ResultSet resultSeq = cdb2.st.executeQuery(QuerySeq);
        while(resultSeq.next()) {
        	Blob b = (Blob) resultSeq.getBlob(5);    
        	BufferedImage photo = null;
			photo = ImageIO.read(b.getBinaryStream());
        	model.addElement(photo);
        	idListModel.addElement(new imageItem(photo.toString(), resultSeq.getInt("id")));
        }

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