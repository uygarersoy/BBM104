import java.util.ArrayList;

public class Main{

    public static void main(String[] args){
        //read content of input files into an ArrayList
        ArrayList<Verse> input = ReadFile.readFile(args[0]);

        //call necessary methods from DataStructures class
        DataStructures.arrayListWrite(input, "poemArrayList.txt");
        DataStructures.arrayListSort(input, "poemArrayListOrderByID.txt");
        DataStructures.hashSetWrite(input, "poemHashSet.txt");
        DataStructures.treeSetWrite(input, "poemTreeSet.txt");
        DataStructures.treeSetSort(input, "poemTreeSetOrderByID.txt");
        DataStructures.hashMapWrite(input, "poemHashMap.txt");
    }

    
}