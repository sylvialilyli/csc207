package ATM.InfoHandling;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public interface WriteTXT {
    void write(String content);

    default void writeHelper(File file, String content) throws IOException {
        if(file.exists()==false){
            file.createNewFile();
        }
        PrintWriter out = new PrintWriter(new FileWriter(file, true));

        out.append(content + "\n");
        out.close();
    }
}
