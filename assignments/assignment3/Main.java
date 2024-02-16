import java.util.ArrayList;

public class Main{
    // read commands from the file given the path from command line, and call test method from Process class to evaluate the commands
    public static void main(String[] args){
        ArrayList<String> commands = ReadFile.readFile(args[0]);
        Process.test(commands, args[1]);
    }    
}