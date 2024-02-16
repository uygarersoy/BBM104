import java.io.*;
import java.util.Scanner;
import java.lang.*;

class Main {
    static String allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ";
    
    public static void main(String[] args){
        String line = "";
        try{
            String filePath = args[0];
        }catch(Exception e){
            appendToFile("output.txt", "There should be only 1 parameter\n");
            System.exit(0);
        }
        if(args.length != 1){
            appendToFile("output.txt", "There should be only 1 parameter\n");
            System.exit(0);
        }
        File file = new File(args[0]);
        if(!file.exists()){
            appendToFile("output.txt", "There should be an input file in the specified path\n");
            System.exit(0);
        } 
        try{
            line += readFile(args[0]);
            if(line.isEmpty()){
                appendToFile("output.txt", "The input file should not be empty\n");
                System.exit(0);
            }
            else if(!checkChars(line, allowedChars)){
                appendToFile("output.txt", "The input file should not contains unexpected characters\n");
                System.exit(0);
            }
        }catch(FileNotFoundException e){
            appendToFile("output.txt", "There should be an input file in the specified path\n");
            e.printStackTrace();
            System.exit(0);
        }
        appendToFile("output.txt", line + "\n");
    }

    public static boolean checkChars(String line, String control){
        for(int i = 0; i < line.length(); i++){
            boolean result = false;
            for(int j = 0; j < control.length(); j++){
                if(line.charAt(i) == control.charAt(j)){
                    result = true;
                    break;
                }
            }
            if(!result){
                return result;
            }
        }
        return true;
    }


    public static void appendToFile(String fileName, String str)
    {
        try {
            BufferedWriter out = new BufferedWriter(
                new FileWriter(fileName, true));
            out.write(str);
            out.close();
        }
        catch (IOException e) {
            System.out.println("exception occurred" + e);
        }
    }



    public static String readFile(String filePath) throws FileNotFoundException{
        
            File myObj = new File(filePath);
            Scanner myReader = new Scanner(myObj);
            String lines = "";
            while (myReader.hasNextLine()){
                lines += myReader.nextLine();
            }
            myReader.close();
            return (lines);
        }       
    
}