/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

//package components;

/*
 * This code is based on an example provided by Richard Stanford, 
 * a tutorial reader.
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.tree.DefaultMutableTreeNode;

public class DynamicTreeDemo extends JPanel 
                             implements ActionListener {
    private int newNodeSuffix = 1;
    private static String ADD_COMMAND = "add";
    private static String REMOVE_COMMAND = "remove";
    private static String CLEAR_COMMAND = "clear";
    
    private DynamicTree treePanel;

    public DynamicTreeDemo() {
        super(new BorderLayout());
        
        //Create the components.
        treePanel = new DynamicTree();
        populateTree(treePanel);

        JButton addButton = new JButton("Add");
        addButton.setActionCommand(ADD_COMMAND);
        addButton.addActionListener(this);
        
        JButton removeButton = new JButton("Remove");
        removeButton.setActionCommand(REMOVE_COMMAND);
        removeButton.addActionListener(this);
        
        JButton clearButton = new JButton("Clear");
        clearButton.setActionCommand(CLEAR_COMMAND);
        clearButton.addActionListener(this);

        //Lay everything out.
        treePanel.setPreferredSize(new Dimension(300, 150));
        add(treePanel, BorderLayout.CENTER);

        JPanel panel = new JPanel(new GridLayout(0,3));
        panel.add(addButton);
        panel.add(removeButton); 
        panel.add(clearButton);
	add(panel, BorderLayout.SOUTH);
    }

    public void populateTree(DynamicTree treePanel) {
        String p1Name = new String("Parent 1");
        String p2Name = new String("Parent 2");
        String c1Name = new String("Child 1");
        String c2Name = new String("Child 2");
        
        DefaultMutableTreeNode p1, p2;
        
        ConnectDatabase cdb = new ConnectDatabase();
        ConnectDatabase cdb2 = new ConnectDatabase();
        ConnectDatabase cdb3 = new ConnectDatabase(); 
		int StoryID = 1; //((comboItem)cbSequences.getSelectedItem()).value;
		String Query = "select * from " 
					+ " ( SELECT story_chronology.idstory_chronology as id, idSentences as SenConcID, concat(subjects.SubjectName, places.PlaceName, events.EventName, IFNULL(o.SubjectName, ''),  IFNULL(po.PlaceName, '')) as NameEvSeq, 1 as Type" 
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
				p1 = treePanel.addObject(null, NameEvSeq);
				if (Type.equals("3")){
					
					String QuerySeq = "select * from "
								+ " ( SELECT seq_events.idseq_events as id, subjects.SubjectTypesID, concat(subjects.SubjectName, places.PlaceName, events.EventName, IFNULL(o.SubjectName, ''),  IFNULL(po.PlaceName, '')) as NameEvSeq, seq_events.Time  as SeqTime"  
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
							+ " ( SELECT concurr_events.idconcurr_events as id, subjects.SubjectTypesID, concat(subjects.SubjectName, places.PlaceName, events.EventName, IFNULL(o.SubjectName, ''),  IFNULL(po.PlaceName, '')) as NameEvSeq "
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
		}
		catch(SQLException ex){System.out.print(ex);}

        

        //p1 = treePanel.addObject(null, p1Name);
        //p2 = treePanel.addObject(null, p2Name);

        //treePanel.addObject(p1, c1Name);
        //treePanel.addObject(p1, c2Name);

        //treePanel.addObject(p2, c1Name);
        //treePanel.addObject(p2, c2Name);
    }
    
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        if (ADD_COMMAND.equals(command)) {
            //Add button clicked
            treePanel.addObject("New Node " + newNodeSuffix++);
        } else if (REMOVE_COMMAND.equals(command)) {
            //Remove button clicked
            treePanel.removeCurrentNode();
        } else if (CLEAR_COMMAND.equals(command)) {
            //Clear button clicked.
            treePanel.clear();
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("DynamicTreeDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        DynamicTreeDemo newContentPane = new DynamicTreeDemo();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
