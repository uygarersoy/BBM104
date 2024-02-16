import java.io.File;  
import java.io.FileNotFoundException; 
import java.util.Scanner; 
import java.io.IOException; 
import java.io.FileWriter;  

/**
 * This class contains methods to perform various operations using stacks, queues, and file handling.
 */
public class Main {
    /**
     * The main method reads the input file and calls the appropriate methods based on the input lines.
     *
     * @param args command-line arguments (input file path and output file path)
     */
    public static void main (String[] args){
        readFile(args[0], args[1]);
    }
    /**
     * Converts a decimal number to binary and writes the result to the output file.
     *
     * @param decimalNumber the decimal number to convert
     * @param outputFile    the path of the output file
     * @param mainNumber    the original decimal number
     */
    public static void convertDecimalToBinary(int decimalNumber, String outputFile, int mainNumber) {
        Stack<Integer> stack = new Stack<>();
        boolean negative = decimalNumber < 0 ? true:false;
        decimalNumber = decimalNumber < 0 ? decimalNumber * -1 : decimalNumber;
        // Special case for 0
        if (decimalNumber == 0) {
            stack.push(0);
        }

        // Convert decimal number to binary
        while (decimalNumber > 0) {
            stack.push(decimalNumber % 2);
            decimalNumber = decimalNumber / 2;
        }

        // Build the binary representation
        StringBuilder binaryNumber = new StringBuilder();
        while (!stack.isEmpty()) {
            binaryNumber.append(stack.pop());
        }
        if (!negative) {
          writer("Equivalent of " + mainNumber + " (base 10) in base 2 is: " + binaryNumber.toString() + "\n", outputFile);
        }
        else {
          writer("Equivalent of " + mainNumber + " (base 10) in base 2 is: -" + binaryNumber.toString() + "\n", outputFile);
        }
    }
    /**
     * Validates the parentheses in an expression and writes the result to the output file.
     *
     * @param expression  the expression to validate
     * @param outputFile  the path of the output file
     */
    public static void validateParentheses(String expression, String outputFile) {
        Stack<Character> stack = new Stack<>();
        boolean wrong = true;
        // Iterate through each character in the expression
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty() && wrong) {
                  writer(String.format("\"%s\"", expression) + " is not a valid expression.\n", outputFile); // Closing parenthesis without a corresponding opening parenthesis
                  wrong = false;
                  break;
                }
                char opening = stack.pop();
                if (!isMatchingPair(opening, c) && wrong) {
                  writer(String.format("\"%s\"", expression) + " is not a valid expression.\n", outputFile); // Mismatched opening and closing parentheses
                  wrong = false;
                  break;
                }
            }
        }

        // Check if all opening parentheses have been closed
        if (stack.isEmpty() && wrong) {
          writer(String.format("\"%s\"", expression) + " is a valid expression.\n", outputFile);
          wrong = false;
        }
        else if (wrong){
          writer(String.format("\"%s\"", expression) + " is not a valid expression.\n", outputFile);
        }
    }
    /**
     * Checks the given chars creates a matching pair
     * 
     * @param opening  starting char
     * @param closing  closing char
     * @return boolean value depending on the match between opening and closing
     * 
     */

    public static boolean isMatchingPair(char opening, char closing) {
        return (opening == '(' && closing == ')') ||
               (opening == '{' && closing == '}') ||
               (opening == '[' && closing == ']');
    }

    /**
     * Checks if a given input is a palindrome and writes the result to the output file.
     *
     * @param input       the input to check for palindrome
     * @param outputFile  the path of the output file
     */
    public static void checkPalindrome(String input, String outputFile) {
        Stack<Character> stack = new Stack<>();
        boolean wrong = true;
        // Remove punctuation marks and white spaces, and convert to lowercase
        String processedInput = input.replaceAll("[^a-zA-Z]", "").toLowerCase();

        // Push characters onto the stack
        for (int i = 0; i < processedInput.length(); i++) {
            stack.push(processedInput.charAt(i));
        }

        // Compare characters from the stack with the processed input
        for (int i = 0; i < processedInput.length(); i++) {
            if (stack.pop() != processedInput.charAt(i)) {
              writer(String.format("\"%s\"", input) + " is not a palindrome.\n", outputFile); // Characters don't match, not a palindrome
              wrong = false;
              break;
            }
        }
        if (wrong) {
          writer(String.format("\"%s\"", input) + " is a palindrome.\n", outputFile);; // All characters match, it is a palindrome
        }
    }
    /**
     * Generates binary numbers from 1 to n and writes them to the output file.
     *
     * @param n           the number of binary numbers to generate
     * @param outputFile  the path of the output file
     */

    public static void countInBinary(int n, String outputFile) {
        Queue<String> queue = new Queue<>();

        // Enqueue the first binary number "1"
        queue.enqueue("1");

        String combinedString = ""; // Initialize combinedString as an empty string

        // Generate binary numbers from 1 to n
        for (int i = 0; i < n; i++) {
            String binaryNumber = queue.dequeue();
            combinedString += binaryNumber + "\t";
            queue.enqueue(binaryNumber + "0");
            queue.enqueue(binaryNumber + "1");
        }
        if (n > 0) {
          writer("Counting from 1 up to " + n + " in binary:\t" + combinedString.trim() + "\n", outputFile);
        }
        else {
          writer("Counting from 1 up to " + n + " in binary:" + combinedString.trim() + "\n", outputFile);
        }
    }
    /**
     * Reads the input file line by line and calls the appropriate methods based on the input lines.
     *
     * @param inputFile   the path of the input file
     * @param outputFile  the path of the output file
     */
    public static void readFile(String inputFile, String outputFile) {
      try {
        File myObj = new File(inputFile);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
          String line = myReader.nextLine();
          if (line.startsWith("Convert")) {
            convertDecimalToBinary(Integer.parseInt(line.split(":\t")[1]), outputFile, Integer.parseInt(line.split(":\t")[1]));
          }
          if (line.split(" ")[3].equals("expression")) {
            validateParentheses(line.split(":\t")[1], outputFile);
          }
          if (line.split(" ")[4].equals("palindrome")) {
            checkPalindrome(line.split(":\t")[1], outputFile);
          }
          if (line.startsWith("Count")) {
            countInBinary(Integer.parseInt(line.split(":\t")[1]), outputFile);
          }
        }
        myReader.close();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
  }

  /**
     * Writes the given lines to the output file.
     *
     * @param lines       the lines to write
     * @param outputFile  the path of the output file
     */
  public static void writer(String lines, String outputFile) {
    
    try {
      FileWriter myWriter = new FileWriter(outputFile, true);
      myWriter.write(lines);
      myWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}