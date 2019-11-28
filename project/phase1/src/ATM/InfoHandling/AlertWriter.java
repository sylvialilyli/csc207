package ATM.InfoHandling;

import java.io.*;

/***
 * AlertWriter class
 */
public class AlertWriter implements WriteTXT, Serializable {
    /***
     * path of file to be written
     */
    private String path = "./alerts.txt";

    /***
     * Write the file with content.
     * If the file does not exist, create a new one.
     * @param content
     */
    public void write(String content){
        File file = new File(path);
        try{
            if(file.exists()==false){
                file.createNewFile();
            }
            PrintWriter out = new PrintWriter(new FileWriter(file, true));

            out.append(content + "\n");
            out.close();
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
