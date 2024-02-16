//import necessary packages to access to data structures
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Comparator;

public class DataStructures{
    //given an ArrayList, write its content to a file
    public static void arrayListWrite(ArrayList<Verse> fileContent, String outputFile){
        //ArrayList is returned as default data structure from ReadFile.readFile method
        String arrayListString = "";
        for (int i = 0; i < fileContent.size(); i++){
            arrayListString += fileContent.get(i).getId() + "\t" + fileContent.get(i).getText() + "\n";
        }
        //get rid of the last newline character at the end
        arrayListString = arrayListString.trim();
        WriteFile.writer(arrayListString, outputFile);
    }
    //using idComparator class to sort the ArrayList and write its content to a file
    public static void arrayListSort(ArrayList<Verse> lines, String outputFile){
        Comparator<Verse> idComparator = new idComparator();
        Collections.sort(lines, idComparator);
        String arrayListSorted = "";
        for (int i = 0; i < lines.size(); i++){
            arrayListSorted += lines.get(i).getId() + "\t" + lines.get(i).getText() + "\n";
        }
        arrayListSorted = arrayListSorted.trim();
        WriteFile.writer(arrayListSorted, outputFile);
    }
    
    //given an ArrayList, create an HashSet with the content of the ArrayList and write the content of HashSet to a file
    public static void hashSetWrite(ArrayList<Verse> lines, String outputFile){
        HashSet<Verse> hashSetLines = new HashSet<Verse>();
        for (Verse verse : lines){
            hashSetLines.add(verse);
        }

        String hashSetToString = "";

        for (Verse verse : hashSetLines){
            hashSetToString += verse.getId() + "\t" + verse.getText() + "\n";
        }
        hashSetToString = hashSetToString.trim();
        WriteFile.writer(hashSetToString, outputFile);
    }

    //given an ArrayList, create an TreeSet with the content of the ArrayList and write the content of TreeSet to a file
    public static void treeSetWrite(ArrayList<Verse> lines, String outputFile){
        TreeSet<Verse> TreeSetLines = new TreeSet<>();
        for (Verse verse : lines){
            TreeSetLines.add(verse);
        }

        String TreeSetToString = "";

        for (Verse verse : TreeSetLines){
            TreeSetToString += verse.getId() + "\t" + verse.getText() + "\n";
        }
        TreeSetToString = TreeSetToString.trim();
        WriteFile.writer(TreeSetToString, outputFile);
    }
    //create a TreeSet and add the content of ArrayList to it
    //using the custom sorting method in idComparator class, sort the TreeSet rather than using its main sorting system
    //write the content of it to a file
    public static void treeSetSort(ArrayList<Verse> lines, String outputFile){
        TreeSet<Verse> TreeSetLines = new TreeSet<>(new idComparator());
        for (Verse verse : lines){
            TreeSetLines.add(verse);
        }

        String TreeSetToString = "";

        for (Verse verse : TreeSetLines){
            TreeSetToString += verse.getId() + "\t" + verse.getText() + "\n";
        }
        TreeSetToString = TreeSetToString.trim();
        WriteFile.writer(TreeSetToString, outputFile);
        
    }
    //create a HashMap using the content of the ArrayList. keys are Integer and values are String
    //arrange the content and add it to HashMap
    //write the content of the HashMap to a file
    public static void hashMapWrite(ArrayList<Verse> lines, String outputFile){
        HashMap<Integer, String> hashMapLines = new HashMap<Integer, String>();
        for (Verse verse : lines){
            hashMapLines.put(verse.getId(), verse.getText());
        }
        Map<Integer, String> treeMap = new TreeMap<>(hashMapLines);


        String outLines = "";
        //using Map.Entry<> to iterate over HashMap
        for (Map.Entry<Integer, String> entry : treeMap.entrySet()){
            outLines += entry.getKey() + "\t" + entry.getValue() + "\n";
        }
        outLines = outLines.trim();
        WriteFile.writer(outLines, outputFile);
    }
}