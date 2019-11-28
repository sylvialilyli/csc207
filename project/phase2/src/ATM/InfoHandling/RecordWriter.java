package ATM.InfoHandling;

import java.io.*;

/***
 * RecordWriter class
 */
public class RecordWriter implements WriteTXT, Serializable {
    /***
     * path of file to be written
     */
    private String path = "./record.txt";

    /***
     * Write the file with content.
     * If the file does not exist, create a new one.
     * @param content
     */
    public void write(String content){
        File file = new File(path);
        try{
            writeHelper(file, content);
        }catch(IOException e){
            System.out.println("Alert is not recorded.");
        }
    }

    /***
     * Set the path of file to be written
     * @param newPath (String) path of expected file
     */
    public void setPath(String newPath) {
        this.path = newPath;
    }
}
