//import necessary packages
import java.io.File;  
import java.io.FileNotFoundException; 
import java.util.Scanner; 
import java.util.ArrayList;
import java.io.FileWriter;  
import java.io.IOException; 

public class ReadFile{

    //read the content of the file to an ArrayList
    public static ArrayList<String> readFile(String fileName) {
        try {
        File myObj = new File(fileName);
        Scanner myReader = new Scanner(myObj);
        ArrayList<String> lines = new ArrayList<String>();
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            lines.add(data);
        }
        myReader.close();
        return (lines);
        } catch (FileNotFoundException e) {
        System.out.println("An error occurred while reading file.");
        e.printStackTrace();
        return null;
        }
  }
}