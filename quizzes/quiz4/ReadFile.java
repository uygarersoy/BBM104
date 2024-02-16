//import necessary packages
import java.io.File;  
import java.io.FileNotFoundException; 
import java.util.Scanner; 
import java.util.ArrayList;
import java.io.FileWriter;  
import java.io.IOException; 

public class ReadFile{

    //read the content of the file to an ArrayList contains objects of verses
    public static ArrayList<Verse> readFile(String fileName) {
        try {
        File myObj = new File(fileName);
        Scanner myReader = new Scanner(myObj);
        ArrayList<Verse> lines = new ArrayList<Verse>();
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            int id = Integer.valueOf(data.split("\t")[0]);
            String text = data.split("\t")[1];
            Verse verse = new Verse(id, text);
            lines.add(verse);
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