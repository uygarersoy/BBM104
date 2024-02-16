//import necessary packages
import java.io.File;  
import java.io.FileNotFoundException; 
import java.util.Scanner; 
import java.util.ArrayList;
import java.io.FileWriter;  
import java.io.IOException; 

public class Main{
    //define static method to access throughout the class without creating objects
    static ArrayList<String> board = new ArrayList<String>();
    static ArrayList<String> moves = new ArrayList<String>();
    static int row;
    static int column;
    static int score = 0;
    static ArrayList<String> joined = new ArrayList<String>();
    //create board and apply printing operations with game playing
    public static void main(String[] args){
        board = readFile(args[0]);
        moves = readFile(args[1]);
        char[][] balls = setMatrix(board.size(), board.get(0).length());
        row = findWhite()[0];
        column = findWhite()[1];
        joined.add("Game board:\n");
        inputOutputInfo(balls);
        joined.add("\nYour movement is:\n");
        int moveCount = play(balls);
        inputOutputInfo(moveCount);
        joined.add("\n" + "\nYour output is:\n");
        inputOutputInfo(balls);
        joined.add("\nGame Over!\nScore: " + Integer.toString(score) + "\n");
        writer(joined);
    }
    //initialize the ball matrix
    public static char[][] setMatrix(int row, int column){
        char[][] balls = new char[row][column];

        for(int i=0; i<row; i++){
            for(int j=0; j<column; j++){
                balls[i][j] = board.get(i).charAt(j);
            }
        }
        return balls;
    }


    //find the starting coordinate of the white ball
    public static int[] findWhite(){
        int[] index = new int[2];
        for(int i=0; i<board.size(); i++){
            for(int j=0;j<board.get(0).length();j++){
                if(board.get(i).charAt(j) == '*'){
                    index[0] = i;
                    index[1] = j;
                }
            }
        }
        return index;
    }

    //play the game by checking moves. if game is over stop playing
    public static int play(char[][] balls){
        int moveCount = 0;
        for(int i=0; i<moves.get(0).length();i++){
            char move = moves.get(0).charAt(i);
            switch(move){
                case 'L':
                    balls = left(balls);
                    break;
                case 'R':
                    balls = right(balls);
                    break;
                case 'U':
                    balls = up(balls);
                    break;
                case 'D':
                    balls = down(balls);
                    break;
            }
            moveCount += 1;
            boolean gameOver = checkInHole(balls);
            if (gameOver){
                return moveCount;
            }
        }
        return moveCount;
    }
    //check if the white ball fell into the hole
    public static boolean checkInHole(char[][] balls){
        for(char[] array: balls){
            for(char ball: array){
                if(ball == ' '){
                    return true;
                }
            }
        }
        return false;
    }

    //change the board according to the item which is on the left of white ball
    public static char[][] left(char[][] balls){
        int check = (column-1)%balls[0].length;
        if (check < 0){
            check = balls[0].length + check;
        }
        if(balls[row][check] != 'H'){
            if(balls[row][check] != 'W'){
                char ball = balls[row][check];
                switch(ball){
                    case 'R':
                        score += 10;
                        balls[row][column] = 'X';
                        balls[row][check] = '*';
                        break;
                    case 'Y':
                        score += 5;
                        balls[row][column] = 'X';
                        balls[row][check] = '*';
                        break;
                    case 'B':
                        score -= 5;
                        balls[row][column] = 'X';
                        balls[row][check] = '*';
                        break;
                    default:
                        balls[row][column] = balls[row][check];
                        balls[row][check] = '*';
                        break;
                }
                column = check;
                return balls;
            }
            else{
                char ball = balls[row][(column+1)%balls[0].length];
                switch(ball){
                    case 'R':
                        balls[row][column] = 'X';
                        balls[row][(column+1)%balls[0].length] = '*';
                        score += 10;
                        break;
                    case 'Y':
                        balls[row][column] = 'X';
                        balls[row][(column+1)%balls[0].length] = '*';
                        score += 5;
                        break;
                    case 'B':
                        balls[row][column] = 'X';
                        balls[row][(column+1)%balls[0].length] = '*';
                        score -= 5;
                        break;
                    default:
                        balls[row][column] = balls[row][(column+1)%balls[0].length];
                        balls[row][(column+1)%balls[0].length] = '*';
                        break;
                }
                column = (column+1)%balls[0].length;
                return balls;
            }
        }
        else{
            balls[row][column] = ' ';
            return balls;
        }
    }
    //change the board according to the item which is on the right of white ball
    public static char[][] right(char[][] balls){
        if(balls[row][(column+1)%balls[0].length] != 'H'){
            if(balls[row][(column+1)%balls[0].length] != 'W'){
                char ball = balls[row][(column+1)%balls[0].length];
                switch(ball){
                    case 'R':
                        score += 10;
                        balls[row][column] = 'X';
                        balls[row][(column+1)%balls[0].length] = '*';
                        break;
                    case 'Y':
                        score += 5;
                        balls[row][column] = 'X';
                        balls[row][(column+1)%balls[0].length] = '*';
                        break;
                    case 'B':
                        score -= 5;
                        balls[row][column] = 'X';
                        balls[row][(column+1)%balls[0].length] = '*';
                        break;
                    default:
                        balls[row][column] = balls[row][(column+1)%balls[0].length];
                        balls[row][(column+1)%balls[0].length] = '*';
                        break;
                }
                column = (column+1)%balls[0].length;
                return balls;
            }
            else{
                int check = (column-1)%balls[0].length;
                if (check < 0){
                    check = balls[0].length + check;
                }
                char ball = balls[row][check];
                switch(ball){
                    case 'R':
                        balls[row][column] = 'X';
                        balls[row][check] = '*';
                        score += 10;
                        break;
                    case 'Y':
                        balls[row][column] = 'X';
                        balls[row][check] = '*';
                        score += 5;
                        break;
                    case 'B':
                        balls[row][column] = 'X';
                        balls[row][check] = '*';
                        score -= 5;
                        break;
                    default:
                        balls[row][column] = balls[row][check];
                        balls[row][check] = '*';
                        break;
                }
                column = check;
                return balls;
            }
        }
        else{
            balls[row][column] = ' ';
            return balls;
        }
    }
    //change the board according to the item which is above of white ball
    public static char[][] up(char[][] balls){
        int check = (row-1)%balls.length;
        if (check < 0){
            check = balls.length + check;
        }

        if(balls[check][column] != 'H'){
            if(balls[check][column] != 'W'){
                char ball = balls[check][column];
                switch(ball){
                    case 'R':
                        score += 10;
                        balls[row][column] = 'X';
                        balls[check][column] = '*';
                        break;
                    case 'Y':
                        score += 5;
                        balls[row][column] = 'X';
                        balls[check][column] = '*';
                        break;
                    case 'B':
                        score -= 5;
                        balls[row][column] = 'X';
                        balls[check][column] = '*';
                        break;
                    default:
                        balls[row][column] = balls[check][column];
                        balls[check][column] = '*';
                        break;
                }
                row = check;
                return balls;
            }
            else{
                char ball = balls[(row+1)%balls.length][column];
                switch(ball){
                    case 'R':
                        balls[row][column] = 'X';
                        balls[(row+1)%balls.length][column] = '*';
                        score += 10;
                        break;
                    case 'Y':
                        balls[row][column] = 'X';
                        balls[(row+1)%balls.length][column] = '*';
                        score += 5;
                        break;
                    case 'B':
                        balls[row][column] = 'X';
                        balls[(row+1)%balls.length][column] = '*';
                        score -= 5;
                        break;
                    default:
                        balls[row][column] = balls[(row+1)%balls.length][column];
                        balls[(row+1)%balls.length][column] = '*';
                        break;
                }
                row = (row+1)%balls.length;
                return balls;
            }
        }
        else{
            balls[row][column] = ' ';
            return balls;
        }
    }
    //change the board according to the item which is on the left of white ball
    public static char[][] down(char[][] balls){
        if(balls[(row+1)%balls.length][column] != 'H'){
            if(balls[(row+1)%balls.length][column] != 'W'){
                char ball = balls[(row+1)%balls.length][column];
                switch(ball){
                    case 'R':
                        score += 10;
                        balls[row][column] = 'X';
                        balls[(row+1)%balls.length][column] = '*';
                        break;
                    case 'Y':
                        score += 5;
                        balls[row][column] = 'X';
                        balls[(row+1)%balls.length][column] = '*';
                        break;
                    case 'B':
                        score -= 5;
                        balls[row][column] = 'X';
                        balls[(row+1)%balls.length][column] = '*';
                        break;
                    default:
                        balls[row][column] = balls[(row+1)%balls.length][column];
                        balls[(row+1)%balls.length][column] = '*';
                        break;
                }
                row = (row+1)%balls.length;
                return balls;
            }
            else{
                int check = (row-1)%balls.length;
                if (check < 0){
                    check = balls.length + check;
                }
                char ball = balls[check][column];
                switch(ball){
                    case 'R':
                        balls[row][column] = 'X';
                        balls[check][column] = '*';
                        score += 10;
                        break;
                    case 'Y':
                        balls[row][column] = 'X';
                        balls[check][column] = '*';
                        score += 5;
                        break;
                    case 'B':
                        balls[row][column] = 'X';
                        balls[check][column] = '*';
                        score -= 5;
                        break;
                    default:
                        balls[row][column] = balls[check][column];
                        balls[check][column] = '*';
                        break;
                }
                row = check;
                return balls;
            }
        }
        else{
            balls[row][column] = ' ';
            return balls;
        }
    }   
    //write to a file the outputs of the program
    public static void writer(ArrayList<String> joined){
        String output = String.join("", joined);
        try {
            FileWriter myWriter = new FileWriter("output.txt");
            myWriter.write(output);
            myWriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    //add the states of the board to a ArrayList
    public static void inputOutputInfo(char[][] balls){

        for(char[] row: balls){
            for(char ball: row){
                joined.add(""+ball);
                joined.add(" ");
            }
            joined.set(joined.size()-1, "");
            joined.add("\n");
        }
    }
    //add the moves that have been played to the ArrayList
    public static void inputOutputInfo(int moveCount){
        for(int i=0;i<moves.size();i++){
            for(int j=0; j < moveCount;j++){
                joined.add(""+moves.get(i).charAt(j));
                joined.add(" ");
            }
        }
        joined.set(joined.size()-1,"");
    }
    //read the content of the file to an ArrayList
    public static ArrayList<String> readFile(String fileName) {
        try {
        File myObj = new File(fileName);
        Scanner myReader = new Scanner(myObj);
        ArrayList<String> lines = new ArrayList<String>();
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine().replace(" ", "");
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