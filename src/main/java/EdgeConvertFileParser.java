import java.io.*;
import java.util.*;
import javax.swing.*;

public abstract class EdgeConvertFileParser {
   public static final String DELIM = "|"; 
   protected File file;
   protected BufferedReader br;
  

   public EdgeConvertFileParser(File file) {
        this.file = file;
    }
   
   public abstract void parseFile() throws IOException;

   protected BufferedReader openFile(File file) throws IOException {
      System.out.println("Opening file: " + file.getAbsolutePath());
        return new BufferedReader(new FileReader(file));
    }

    public abstract EdgeTable[] getEdgeTables();
    public abstract EdgeField[] getEdgeFields();
}