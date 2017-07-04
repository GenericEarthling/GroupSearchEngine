/** @author Flavio Aquino, Sharon Tender, Frank Castillo
 * Spring 2017
 * 
 *  Class responsible for handling file I/O
 */

package searchEngineProj;

import java.util.Scanner;
import javax.swing.JFileChooser;
import java.io.*;
import static java.lang.Long.parseLong;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.PatternSyntaxException;
import javax.swing.UIManager;
import searchEngineProj.FilePath;
import static java.lang.Long.parseLong;

public class FileMgmt {    
    // default constructor
    public FileMgmt() throws IOException {}   
    
    // Persistant data: Array List to hold the persistant file data 
    static List<FilePath> persistentArray = new ArrayList<>();
    
    // Persistant data variables 
    static final String PERSISTENT = "./Persistent.txt";
    static final Path PERSISTENT_PATH = Paths.get(PERSISTENT);
    static final File PERSISTENT_FILE = PERSISTENT_PATH.toFile();
    
    // open file if file exists, create one if it does not
    static void load () throws  NumberFormatException, IllegalStateException, PatternSyntaxException {
        try {
            if (Files.notExists(PERSISTENT_PATH))  {
                Files.createFile(PERSISTENT_PATH);
            }
        }
        catch (IOException e)  {
            System.err.println("Static Initialization Block, IOException: " + e);
        }

        // 3) this saves FROM persistant.txt TO array list RAM         
        try ( Scanner  in = new Scanner(
                           new File(PERSISTENT)))  {
            // if the file is not null, save to ArrayList
            if ( in != null) {
                while ( in.hasNext() )    {
                    String pathIn = in.next();
                    String dateString = in.next() ;         
                    long dateIn = Long.valueOf(dateString);
                    FilePath intoRamArray = new FilePath(pathIn, dateIn);
                    persistentArray.add(intoRamArray);  
                }
//                in.close();
            }
            in.close();
            System.out.println(persistentArray.size());

//            updateIndex();
        } catch (Exception e)   {
            System.err.println("Error Reading Persistant File: " + e);
        }
    }
	
    // MaintenanceGui Button event "Add File"
    // This will add a selected file to the ArrayList to be searched
    public void addFileToIndex()  throws IOException {  
        
        // set look and feel to match native system UI
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("I guess you're stuck with Java's default look and feel");
        }
        
        // open a window to choose a file (REQUIRES: import javax.swing.JFileChooser;)
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            
            // get selected file, path, and date last modified
            File file = fileChooser.getSelectedFile();
            try (Scanner selectedFile = new Scanner(file)) {
                String selectedFilePath = file.getCanonicalPath();
                long lastModifiedDate = file.lastModified();
                FilePath pathAndDate = new FilePath(selectedFilePath, lastModifiedDate);
                String pathAndDateString = pathAndDate.toString();
                // Persistant data: if the ArrayList is not empty, add selected file
                //    note: "true" appends data to files, without "true" it will overwrite
                if ( persistentArray != null ) {
                    try ( PrintWriter out = new PrintWriter(
                            new BufferedWriter(
                                    new FileWriter(PERSISTENT_FILE, true)))) {
                        int endOfList = persistentArray.size();
                        persistentArray.add(endOfList, pathAndDate);
                        
                        // --------- WARNING: problem area -----------
                        // this creates another MaintenanceGui window on top of original one... 
                        // but it automatically updates list as file is added... hmmmm
                        MaintenanceGui.getModel().fileList.append(pathAndDateString + "\n");  
                        out.println(pathAndDateString);
                    } catch (IOException e) {
                        System.err.println("IOEXCEPTION: " + e);
                    }
                } else {
                    persistentArray.add(pathAndDate);
                    
                }   updateIndex();
            }
        }
    }  // end of Button event "Add File"      
        
    // Remove a file from targetIndexFiles
    public static void removeFileFromIndex() {       
        // stub for MaintenanceGui class, addFile.addActionListener
    }                                           
    
    // Read targetIndexFiles
    public void readIndexFile() {
    }
    
    // save file in array for searching with InvertedIndex.java
    public void saveFilePathForSearching(String s) {
    }
   
	public static void updateIndex() {

        // need an event listener here for object added to persistent array
        System.err.println("From: FileMgmt.updateIndex() method");
            
	}
       
    // clearing files for new search data
    // note: this will also need to clear the search results in the Search Window
    public void removeFileList(String s) {        
    }

} // end of FileMgmt class
