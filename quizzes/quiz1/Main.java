import java.lang.Math;
import java.io.File;  
import java.io.FileNotFoundException; 
import java.util.Scanner; 
import java.util.ArrayList;
import java.io.FileWriter;  
import java.io.IOException; 
import java.util.*;

public class Main{
    
    public static void main(String[] args){
        String inputFile = args[0];
        ArrayList<String> lines = readFile(inputFile);
        ArrayList<String> finalWrite = new ArrayList<String>();

        for (int i=0; i < lines.size(); i++){
            if (lines.get(i).equals("Armstrong numbers up to:")){
                int range = Integer.parseInt(lines.get(i+1));
                ArrayList<Integer> temp = new ArrayList<Integer>();
                for(int j=1; j < range+1 ;j++){
                    if (isArmstrong(j)){
                        temp.add(j);
                    }
                    
                }
                ArrayList<String> tempToString = new ArrayList<String>();
                for(int j=0; j<temp.size();j++){
                    tempToString.add(Integer.toString(temp.get(j)));
                }
                String result = "Armstrong numbers up to " + lines.get(i+1) + ":\n" + String.join(" ", tempToString);
                finalWrite.add(result+"\n");
            }
            else if (lines.get(i).equals("Emirp numbers up to:")){
                int range = Integer.parseInt(lines.get(i+1));
                ArrayList<Integer> temp = new ArrayList<Integer>();
                for(int j=1; j < range+1 ;j++){
                    if (isPrime(j) && isPrime(reverseNum(j)) && (j != reverseNum(j))){
                        temp.add(j);
                    }
                    
                }
                ArrayList<String> tempToString = new ArrayList<String>();
                for(int j=0; j<temp.size();j++){
                    tempToString.add(Integer.toString(temp.get(j)));
                }
                String result = "Emirp numbers up to " + lines.get(i+1) + ":\n" + String.join(" ", tempToString);
                finalWrite.add(result+"\n");
            }
            else if (lines.get(i).equals("Abundant numbers up to:")){
                int range = Integer.parseInt(lines.get(i+1));
                ArrayList<Integer> temp = new ArrayList<Integer>();
                for(int j=1; j < range+1 ;j++){
                    if (abundant(j) > j){
                        temp.add(j);
                    }
                    
                }
                ArrayList<String> tempToString = new ArrayList<String>();
                for(int j=0; j<temp.size();j++){
                    tempToString.add(Integer.toString(temp.get(j)));
                }
                String result = "Abundant numbers up to " + lines.get(i+1) + ":\n" + String.join(" ", tempToString);
                finalWrite.add(result+"\n");
            }
            else if (lines.get(i).equals("Ascending order sorting:")){
                int index = i+1;
                ArrayList<Integer> numsToSort = new ArrayList<Integer>();
                String result = "Ascending order sorting:\n";
                while(true){
                    try{
                        if (Integer.parseInt(lines.get(index)) == -1){
                            break;
                        }
                        numsToSort.add(Integer.parseInt(lines.get(index)));
                        Collections.sort(numsToSort);
                        ArrayList<String> tempToString = new ArrayList<String>();
                        for(int j=0; j<numsToSort.size();j++){
                            tempToString.add(Integer.toString(numsToSort.get(j)));
                        }
                        result += String.join(" ", tempToString) + "\n";                  
                        index++;
                    }
                    catch(Exception e){
                        break;
                    }
                }
                finalWrite.add(result);
            }
            else if (lines.get(i).equals("Descending order sorting:")){
                int index = i+1;
                ArrayList<Integer> numsToSort = new ArrayList<Integer>();
                String result = "Descending order sorting:\n";
                while(true){
                    try{
                        if (Integer.parseInt(lines.get(index)) == -1){
                            break;
                        }
                        numsToSort.add(Integer.parseInt(lines.get(index)));
                        Collections.sort(numsToSort, Collections.reverseOrder());
                        ArrayList<String> tempToString = new ArrayList<String>();
                        for(int j=0; j<numsToSort.size();j++){
                            tempToString.add(Integer.toString(numsToSort.get(j)));
                        }
                        result += String.join(" ", tempToString) + "\n";                  
                        index++;
                    }
                    catch(Exception e){
                        break;
                    }
                }
                finalWrite.add(result);
            }
            else if (lines.get(i).equals("Exit")){
                break;
            }
        }
        String output = String.join("\n", finalWrite) + "\n" + "Finished...";
        writer(output);

    }
    public static boolean isArmstrong(int number){
        int digitNum = digitCount(number);
        int total = 0;
        int check = number;

        while(number!=0){
            total += Math.pow(number % 10, digitNum);
            number /= 10;
        }
        return check == total;
    }


    public static int digitCount(int number){
        int total = 0;
        if (number == 0){
            return 1;
        }

        while (number != 0){
            number /= 10;
            total++;
        }
        return total;
    }


    public static boolean isPrime(int num){
        if(num < 2){
            return false;
        }

        if(num % 2 == 0){
            return num == 2;
        }

        for(int i=3; i<Math.pow(num,0.5)+1;i++){
            if(num%i==0){
                return false;
            }
        }
        return true;
    }

    public static int reverseNum(int num){
        int reverse = 0;

        while (num != 0){
            reverse = reverse*10 + num % 10;
            num /= 10;
        }
        return reverse;
    }


    public static int abundant(int num){
        int total = 0;
        for (int i=1;i < num;i++){
            if(num%i==0){
                total += i;
            }
        }
        return total;
    }


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

  public static void writer(String lines) {
    
    String joined = String.join("\n", lines);
    try {
      FileWriter myWriter = new FileWriter("output.txt");
      myWriter.write(joined);
      myWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}