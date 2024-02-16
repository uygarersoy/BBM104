//import necessary packages
import java.io.File;  
import java.io.FileNotFoundException; 
import java.util.Scanner; 
import java.util.ArrayList;
import java.io.FileWriter;  
import java.io.IOException; 


public class WriteFile{

    //write to a file the outputs of the program
    public static void writer(String lines, String outName){
        try {
            FileWriter myWriter = new FileWriter(outName);
            myWriter.write(lines);
            myWriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}