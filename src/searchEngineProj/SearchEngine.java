/** @author Flavio Aquino, Sharon Tender, Frank Castillo
 * Spring 2017
 */

package searchEngineProj;

import java.awt.Window;
import javax.swing.JFrame;
import javax.swing.*;

public class SearchEngine extends JFrame {
	public static void main ( String[] args ) {
        FileMgmt.load();
		UserForm testGui = new UserForm();
	}
}