/** @author Flavio Aquino, Sharon Tender, Frank Castillo,
 *  Spring 2017
 * 
 *  Class responsible for handling file path and modified date
 */
package searchEngineProj;

public class FilePath {
    private String path;
    private long fileModifiedDate;
    
    public FilePath(String path, long fileModifiedDate) {
        this.path = path;
        this.fileModifiedDate = fileModifiedDate;
    }    
    public String getPath() {
        return this.path;
    }
    public String setPath(String newPath) {
        return (this.path = newPath);
    }    
    public long getModifiedDate() {
        return this.fileModifiedDate;
    }
    public long setModifiedDate(long newDate) {
        return (this.fileModifiedDate = newDate);
    }    
    @Override
    public String toString() {
        return String.format("%s\t%d", this.path, this.fileModifiedDate);
    }
    
    
}