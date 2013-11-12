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

/**
 * A 1.4 application that requires the following additional files:
 *   TreeDemoHelp.html
 *    arnold.html
 *    bloch.html
 *    chan.html
 *    jls.html
 *    swingtutorial.html
 *    tutorial.html
 *    tutorialcont.html
 *    vm.html
 */

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.ToolTipManager;
import javax.swing.ImageIcon;
import javax.swing.Icon;

import com.mysql.jdbc.CallableStatement;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Component;


public class TreeIconDemo2 extends JPanel 
                           implements TreeSelectionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JEditorPane htmlPane;
    private JTree tree;
    private URL helpURL;
    private static boolean DEBUG = false;

    public TreeIconDemo2() {
        super(new GridLayout(1,0));

        //Create the nodes.
        DefaultMutableTreeNode top =
            new DefaultMutableTreeNode("The Java Series");
        createNodes(top);

        //Create a tree that allows one selection at a time.
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);

        //Enable tool tips.
        ToolTipManager.sharedInstance().registerComponent(tree);

        //Set the icon for leaf nodes.
        ImageIcon tutorialIcon = new ImageIcon("D:/eclipse-standard-kepler-R-win32-x86_64/eclipse/workspace/ImmersiveEnvironmentsEngine/src/images/check.gif");//createImageIcon("middle.gif");
        ImageIcon tutorialIcon_qm = new ImageIcon("D:/eclipse-standard-kepler-R-win32-x86_64/eclipse/workspace/ImmersiveEnvironmentsEngine/src/images/qm.fig"); //createImageIcon("images/qm.gif");
        if (tutorialIcon != null) {
            tree.setCellRenderer(new MyRenderer(tutorialIcon, tutorialIcon_qm));
        } else {
            System.err.println("Tutorial icon missing; using default.");
        }

        //Listen for when the selection changes.
        tree.addTreeSelectionListener(this);

        //Create the scroll pane and add the tree to it. 
        JScrollPane treeView = new JScrollPane(tree);

        //Create the HTML viewing pane.
        htmlPane = new JEditorPane();
        htmlPane.setEditable(false);
        initHelp();
        JScrollPane htmlView = new JScrollPane(htmlPane);

        //Add the scroll panes to a split pane.
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(treeView);
        splitPane.setBottomComponent(htmlView);

        Dimension minimumSize = new Dimension(100, 50);
        htmlView.setMinimumSize(minimumSize);
        treeView.setMinimumSize(minimumSize);
        splitPane.setDividerLocation(100); //XXX: ignored in some releases
                                           //of Swing. bug 4101306
        //workaround for bug 4101306:
        //treeView.setPreferredSize(new Dimension(100, 100)); 

        splitPane.setPreferredSize(new Dimension(500, 300));

        //Add the split pane to this panel.
        add(splitPane);
        for (int i = 0; i < tree.getRowCount(); i++) {
            tree.expandRow(i);
    	}
    }

    /** Required by TreeSelectionListener interface. */
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                           tree.getLastSelectedPathComponent();

        if (node == null) return;

        Object nodeInfo = node.getUserObject();
        if (node.isLeaf()) {
        	CronologyInfo book = (CronologyInfo)nodeInfo;
            //displayURL(book.bookURL);
            if (DEBUG) {
                System.out.print(book.senName + ":  \n    ");
            }
        } else {
            displayURL(helpURL); 
        }
        if (DEBUG) {
            System.out.println(nodeInfo.toString());
        }
    }

    private class CronologyInfo {
        public String senName;
        public int isMust;

        public CronologyInfo(String NameSen, int isMustSen) {
        	senName = NameSen;
        	isMust = isMustSen;
        }

        public String toString() {
            return senName;
        }
    }

    private void initHelp() {
        String s = "TreeDemoHelp.html";
        helpURL = TreeIconDemo.class.getResource(s);
        if (helpURL == null) {
            System.err.println("Couldn't open help file: " + s);
        } else if (DEBUG) {
            System.out.println("Help URL is " + helpURL);
        }

        displayURL(helpURL);
    }

    private void displayURL(URL url) {
        try {
            if (url != null) {
                htmlPane.setPage(url);
            } else { //null url
		htmlPane.setText("File Not Found");
                if (DEBUG) {
                    System.out.println("Attempted to display a null URL.");
                }
            }
        } catch (IOException e) {
            System.err.println("Attempted to read a bad URL: " + url);
        }
    }

    private void createNodes(DefaultMutableTreeNode top) {
        DefaultMutableTreeNode root = null;
        DefaultMutableTreeNode p1 = null;
        DefaultMutableTreeNode p2 = null;

        root = new DefaultMutableTreeNode("Books for Java Programmers");
        top.add(root);
        
        
    	try	{
    		ConnectDatabase cdb = new ConnectDatabase();
    	    ConnectDatabase cdb2 = new ConnectDatabase();
    	    ConnectDatabase cdb3 = new ConnectDatabase();  
			CallableStatement cs = (CallableStatement) cdb.con.prepareCall("{call SelectChronology()}");
			ResultSet result = cs.executeQuery(); 			  			  
			String SenSeqConID, Type;
			
			while(result.next()) {
				SenSeqConID = result.getString("SenConcID");
				Type = result.getString("Type");
				p1 = new DefaultMutableTreeNode(new CronologyInfo(result.getString("NameEvSeq"), result.getInt("isMust")));
				root.add(p1);
				if (Type.equals("3")){
					cs = (CallableStatement) cdb2.con.prepareCall("{call SelectSentenceConSeqByID(" + SenSeqConID + ")}");
					ResultSet resultSeq = cs.executeQuery();
					while(resultSeq.next()) {
						p2 = new DefaultMutableTreeNode(new CronologyInfo(resultSeq.getString("NameEvSeq"), resultSeq.getInt("isMust")));
						p1.add(p2);
					}
				}	
				if (Type.equals("2")){
					cs = (CallableStatement) cdb3.con.prepareCall("{call SelectSentenceConSeqByIDType(" + SenSeqConID + ")}");
					ResultSet resultCon = cs.executeQuery();
					while(resultCon.next()) {
						p2 = new DefaultMutableTreeNode(new CronologyInfo(resultCon.getString("NameEvSeq"), resultCon.getInt("isMust")));
						p1.add(p2);
					}
				}	
			}
			cdb.st.close();
			cdb2.st.close();
			cdb3.st.close();	
			//treePanel.expand();
		}
		catch(SQLException ex){System.out.print(ex);}
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = TreeIconDemo2.class.getResource(path);
        System.out.println(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    	
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TreeIconDemo2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        TreeIconDemo2 newContentPane = new TreeIconDemo2();
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

    private class MyRenderer extends DefaultTreeCellRenderer {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		Icon tutorialIcon;
		Icon tutorialIcon_qm;

        public MyRenderer(Icon icon, Icon icon_qm) {
            tutorialIcon = icon;
            tutorialIcon_qm =  icon_qm;
        }

        public Component getTreeCellRendererComponent(
                            JTree tree,
                            Object value,
                            boolean sel,
                            boolean expanded,
                            boolean leaf,
                            int row,
                            boolean hasFocus) {
        	System.out.println("row: " +Integer.toString(row));

            super.getTreeCellRendererComponent(
                            tree, value, sel,
                            expanded, leaf, row,
                            hasFocus);
            if (isMustSenSeqCon(value)) {
                setIcon(tutorialIcon);
                setForeground(Color.RED);
                setTextSelectionColor(Color.BLUE);
                //setToolTipText("This book is in the Tutorial series.");
            } else {
            	setIcon(tutorialIcon_qm);
                setForeground(Color.BLUE);
                setTextSelectionColor(Color.BLUE);
                setToolTipText(null); //no tool tip
            }

            return this;
        }

        protected boolean isMustSenSeqCon(Object value) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
            Object userObject = ((DefaultMutableTreeNode) value).getUserObject();
            if (userObject instanceof CronologyInfo) {
            	System.out.println("Object CronologyInfo");
            	CronologyInfo nodeInfo = (CronologyInfo)(node.getUserObject());
            	if (nodeInfo.isMust == 2) {
            		return true;
            	} 
            }

            return false;
        }
    }
}
