/** @author Flavio Aquino, Sharon Tender, Frank Castillo
 * Spring 2017
 * 
 *  Class responsible for creating the  Administrator
 *  GUI.
 */

package searchEngineProj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.List;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;


public class MaintenanceGui {
	JLabel title, numberOfFiles;     
	JButton addFile, updateFile, removeFile;
	public JTable table;                       // public for other classes to acces it?
//	public List fileList;              // let Flavio know i am commenting this out
	public JFrame myFrame;
	static MaintenanceGui maintGui;
    JTextArea fileList = new JTextArea();

    
    static MaintenanceGui getModel() {
        if ( maintGui == null ) 
            maintGui = new MaintenanceGui();
        return maintGui;
    }
    
	public MaintenanceGui() {
		Font titleFont = new Font("Serif", Font.BOLD, 46);
		Font regularFont = new Font("Serif", Font.PLAIN, 26);
		
		// to create the window frame
		JFrame myFrame = new JFrame();
		myFrame.setTitle("My Search Engine Maintenance");
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.getContentPane().setLayout(new BorderLayout());
		myFrame.setMinimumSize(new Dimension(950, 650));
		myFrame.setVisible(true);
		
		// to create a container to place the north panel into for Heading
		Container contentPane = myFrame.getContentPane();
		JPanel northPanel = new JPanel();
		northPanel.setBackground(Color.BLUE);
		northPanel.setLayout(new BorderLayout());
		northPanel.setPreferredSize(new Dimension(850, 100));
		northPanel.setBorder(new EmptyBorder(0, 0, 0, 20));
		contentPane.add(northPanel, BorderLayout.NORTH);
		
		// to create and add title label to north panel
		title = new JLabel("My Search Engine - Maintenance");
		title.setFont(titleFont);
        title.setForeground(Color.white);
		title.setHorizontalAlignment(JLabel.CENTER);
		northPanel.add(title, BorderLayout.CENTER);
		        
		JScrollPane sp = new JScrollPane(fileList);
		contentPane.add( sp );        
		
        // Populate TextPane with list of files in persistent ArrayList on load
        FileMgmt.persistentArray.stream().forEach((fileLine) -> {
            fileList.append(fileLine + "\n");
        });
        System.err.println("FROM: MaintenanceGui, ln. 80");      // testing
        

        
//        // update TextPane
//        static void updateFileList() {
//            for ( FilePath fileLine : FileMgmt.persistentArray ) {
//                fileList.append(fileLine + "\n");
//            }
//        }        

		// to create the south panel
		JPanel southPanel = new JPanel();
		southPanel.setBackground(Color.BLUE);
		southPanel.setLayout(new FlowLayout());
		((FlowLayout)southPanel.getLayout()).setHgap(70);
		((FlowLayout)southPanel.getLayout()).setVgap(30);
		southPanel.setPreferredSize(new Dimension(850, 150));
		contentPane.add(southPanel, BorderLayout.SOUTH);
		
		// to create buttons and add them to south panel
		addFile = new JButton("Add File");
		addFile.setFont(regularFont);
		southPanel.add(addFile);
		
        // button event calls FileMgmt.addFileToIndex() method which adds a file to ArrayList
		addFile.addActionListener(actionEvent -> {  
            try {
                FileMgmt addSearchFile = new FileMgmt();
                addSearchFile.addFileToIndex();                 
            } catch (IndexOutOfBoundsException e) {
                System.err.println("IndexOutOfBoundsException: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("Caught IOException: " + e.getMessage());
            }
            
//            // delete current TextPane content
//            fileList.removeAll();
//            fileList.repaint();
//            fileList.revalidate();
            
//            // Populate TextPane with list of files in persistent ArrayList on load
//            for ( FilePath fileLine : FileMgmt.persistentArray ) {
//                fileList.append(fileLine + "\n");
//            }
            System.err.println("FROM: MaintenanceGui, ln. 125ish; addFile action event");      // testing
                        
		});
		
		updateFile = new JButton("Update Index File");
		updateFile.setFont(regularFont);
		southPanel.add(updateFile);
		
		updateFile.addActionListener(actionEvent -> {
            fileList.revalidate();
        // call the displayFile method from FileMgmt Class
//		FileMgmt.updateIndex();
//        contentPane.repaint();  //.repaint(fileList);   
//        contentPane.revalidate();
//        FileMgmt.load();
        System.err.println("FROM: MaintenanceGui, ln. 140; update File actionEvent");

        });

		removeFile = new JButton("Remove Selected File");
		removeFile.setFont(regularFont);
		southPanel.add(removeFile);
		
		removeFile.addActionListener(actionEvent -> {
        // call the remove file method from FileMgmt class
//        FileMgmt.removeFileFromIndex();
        // update the GUI display window
//        FileMgmt.updateIndex();
            
//        // need to delete this when we have the method working
//		JOptionPane.showMessageDialog(null, "Operation not yet available",
//					"Temporary Message", JOptionPane.OK_OPTION);
		});
		
		// nested panel to put the number of file
		// label in a different line
		JPanel southPanel2 = new JPanel();
		southPanel2.setLayout(new FlowLayout());
		((FlowLayout)southPanel2.getLayout()).setVgap(5);
		southPanel2.setBackground(Color.BLUE);
		southPanel2.setPreferredSize(new Dimension(850, 100));
		numberOfFiles = new JLabel("");
		numberOfFiles.setFont(regularFont);
		southPanel2.add(numberOfFiles);
		southPanel.add(southPanel2);        
	}
		}
