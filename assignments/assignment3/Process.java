import java.util.ArrayList;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;

public class Process{
    //define the followings to store the output of commands,
    //created books and members, also borrowed and read in library books
    static ArrayList<String> output = new ArrayList<String>();
    static ArrayList<Book> books = new ArrayList<Book>();
    static ArrayList<Member> members = new ArrayList<Member>();
    static ArrayList<Book> borrowed = new ArrayList<Book>();
    static ArrayList<Book> read = new ArrayList<Book>();

    //check the command given in the input file, and call necessary functions for operating them
    public static void test(ArrayList<String> inp, String outName){
        for(String line: inp){
            String[] splittedLine = line.split("\t");

            if (splittedLine[0].equals("addBook")){
                addBook(splittedLine);
            }
            else if(splittedLine[0].equals("addMember")){
                addMember(splittedLine);
            }
            else if(splittedLine[0].equals("borrowBook")){
                borrowBook(splittedLine);
            }
            else if(splittedLine[0].equals("readInLibrary")){
                readInLibrary(splittedLine);
            }
            else if(splittedLine[0].equals("returnBook")){
                returnBook(splittedLine);
            }
            else if(splittedLine[0].equals("extendBook")){
                extendBook(splittedLine);
            }
            else if(splittedLine[0].equals("getTheHistory")){
                getTheHistory();
            }
            else{
                output.add("Invalid command!");
            }
                        
        }
        //write the output of commands from output arraylist to output file
        WriteFile.writer(output, outName);
    
    }

    //create a new book, store it in book. store the output in output arraylist
    public static void addBook(String[] info){
        Book book = new Book(info[1]);
        books.add(book);
        String type = book.getType().equals("P") ? "Printed" : "Handwritten";
        output.add(String.format("Created new book: %s [id: %d]", type, book.getId()));
    }
    //create a new member, store it in book. store the output in output arraylist
    public static void addMember(String[] info){
        Member member = new Member(info[1]);
        members.add(member);
        String type = member.getPerson().equals("S") ? "Student" : "Academic";
        output.add(String.format("Created new member: %s [id: %d]", type, member.getId()));
    }

    //iterate over book and members arraylit to find given book and member
    public static void borrowBook(String[] info){
        boolean isBorrowed = false;
        for(Member member: members){
            if (member.getId() == Integer.parseInt(info[2])){
                //if person maximized its borrow limit, output a warning
                if (member.getBorrowLimit() > 0){
                    for (Book book: books){
                        if (book.getId() == Integer.parseInt(info[1])){
                            //if book is not borrowable, or borrowed, output a warning
                            if (book.getBorrowable() && !book.getIsBorrowed()){
                                //borrow book and update the states of given member and book
                                output.add(String.format("The book [%s] was borrowed by member [%s] at %s", info[1], info[2], info[3]));
                                book.setIsBorrowed(true);
                                book.setBorrowedDate(info[3]);
                                book.setBorrower(Integer.parseInt(info[2]));
                                member.addBorrowedBook(book);
                                borrowed.add(book);
                                member.setBorrowLimit(-1);
                                isBorrowed = true;
                            }
                            else{
                                output.add("You cannot borrow this book!");
                                //update isBorrowed anyway to prevent unnecessary output messages
                                isBorrowed = true;
                            }
                        }
                    }
                }
                else{
                    output.add("You have exceeded the borrowing limit!");
                    isBorrowed = true;
                }
            }
        }
        //if borrowing didn't happen, output a warning message
        if (!isBorrowed){
            output.add("An error occured while trying to borrow the given book. Check the book and your access to the book!");
        }
    }
    //iterate over book and members arraylit to find given book and member
    public static void readInLibrary(String[] info){
        boolean success = false;
        for (Member member: members){
            if (member.getId() == Integer.parseInt(info[2])){
                for (Book book: books){
                    if (book.getId() == Integer.parseInt(info[1])){
                        //if a student tries to read handwritten book, output a warning
                        if (member.getPerson().equals("S") && book.getType().equals("H")){
                            output.add("Students can not read handwritten books!");
                            success = true;
                        }
                        //if book is not available at the moment, output a warning
                        else if (!book.getIsBorrowed()){
                            if ((member.getPerson().equals("S")  && book.getType().equals("P")) || member.getPerson().equals("A")){
                                //if everything is okay, read the book and update the states of given member and book
                                output.add(String.format("The book [%s] was read in library by member [%s] at %s", info[1], info[2], info[3]));
                                book.setIsBorrowed(true);
                                book.setBorrowedDate(info[3]);
                                member.addBorrowedBook(book);
                                read.add(book);
                                book.setBorrower(Integer.parseInt(info[2]));
                                success = true;
                            }
                            else{
                                output.add("You can not read this book!");
                                //update success anyway to prevent unnecessary output messages
                                success = true;
                            }
                        }
                        else{
                            output.add("You can not read this book!");
                            success = true;
                        }
                    }
                }
            }
        }
        //if process went unsuccessful, output an error message
        if (!success){
            output.add("An error occured while trying to read the book. Check if you possess the given book!");
        }
        
    }

    public static void returnBook(String[] info){
        //iterate over book and members arraylit to find given book and member
        boolean returned = false;
        for (Member member: members){
            if (member.getId() == Integer.parseInt(info[2])){
                for (int i = 0; i < member.getBorrowedBooks().size(); i++){
                    if (member.getBorrowedBooks().get(i).getId() == Integer.parseInt(info[1])){
                        //check the fee of the return state, and output appropriate message
                        Book check = member.getBorrowedBooks().get(i);
                        //check if the given book is being returned from read in library. if not increment the borrowing limit of the member
                        //update the states of book and member
                        for (Book book: borrowed){
                            if (book.getId() == Integer.parseInt(info[1]) && book.getIsBorrowed()){
                                member.setBorrowLimit(1);
                                int fee = checkFee(check.getBorrowedDate(), info[3], member, check.getIsExtended());
                                output.add(String.format("The book [%s] was returned by member [%s] at %s Fee: %d", info[1], info[2], info[3], fee));
                                member.getBorrowedBooks().remove(i);
                                check.setIsBorrowed(false);
                                check.setBorrowedDate("");
                                check.setIsExtended(false);
                                check.setBorrower(0);
                                returned = true;
                            }
                        }
                        // if the book is not returned above, than it means it was read in library and cannot have fee while returning
                        // output a message and update the states of the member and book
                        if (!returned){
                            output.add(String.format("The book [%s] was returned by member [%s] at %s Fee: %d", info[1], info[2], info[3], 0));
                            member.getBorrowedBooks().remove(i);
                            check.setIsBorrowed(false);
                            check.setBorrowedDate("");
                            check.setIsExtended(false);
                            check.setBorrower(0);
                            returned = true;
                        }
                        
                    }
                }
            }
            }
        //if returning process did not happen, output a warning
        if (!returned){
            output.add("An error occured while trying to return the book. Check if you possess the given book!");
        }
        }
    

    //iterate over book and members arraylit to find given book and member
    public static void extendBook(String[] info){
        boolean success = false;
        for (Member member: members){
            if (member.getId() == Integer.parseInt(info[2])){
                for (Book book: books){
                    if (book.getId() == Integer.parseInt(info[1])){
                        //check if the book already extended or not. if it has been, then output a warning
                        if (!book.getIsExtended()){
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            LocalDate date1Parsed = LocalDate.parse(info[3], formatter);
                            LocalDate date2Parsed = LocalDate.parse(book.getBorrowedDate(), formatter);
                            int difference = (int) ChronoUnit.DAYS.between(date1Parsed, date2Parsed);
                            //check if the extension request is before deadline. if it is after, output a warning
                            if (difference + member.getDeadline() >= 0){
                                //if extension request is suitable, output a message and update the state of book
                                output.add(String.format("The deadline of book [%s] was extended by member [%s] at %s", info[1], info[2], info[3]));
                                int timeToAdd = member.getPerson().equals("S") ? 7 : 14;
                                output.add(String.format("New deadline of book [%s] is %s", info[1], addDays(book.getBorrowedDate(), (timeToAdd * 2))));
                                book.setBorrowedDate(addDays(book.getBorrowedDate(), (timeToAdd * 2)));
                                book.setIsExtended(true);
                                success = true;
                            }
                            else{
                                output.add("Deadline already passed. You cannot extend the book. You have to pay a fee!");
                                //update success anyway to prevent unnecessary output messages
                                success = true;
                            }
                            
                        }
                        else{
                            output.add("You cannot extend the deadline!");
                            success = true;
                        }
                    }
                }
            }

        }
        //if process went unsuccessful, output an error message
        if (!success){
            output.add("An error occured while trying to extend the book. Check if you possess the given book!");
        }

    }
    //call necessary functions to output the history of the library
    public static void getTheHistory(){
        output.add("History of library:\n");
        noOfStudents();
        noOfAcademics();
        noOfPrinted();
        noOfHandWritten();
        borrowedCheck();
        readCheck();
    }

    //get the number of students that are recorded as a member to the system of library
    public static void noOfStudents(){
        output.add("Number of students: " + memberTypeCount("S"));
        for (Member member: members){
            if (member.getPerson().equals("S")){
                output.add(String.format("Student [id: %d]", member.getId()));
            }
        }
        output.add("");
    }
    //get the number of academics that are recorded as a member to the system of library
    public static void noOfAcademics(){
        output.add("Number of academics: " + memberTypeCount("A"));
        for (Member member: members){
            if (member.getPerson().equals("A")){
                output.add(String.format("Academic [id: %d]", member.getId()));
            }
        }
        output.add("");
    }
    //get the number of printed books that are recorded as a member to the system of library
    public static void noOfPrinted(){
        output.add("Number of printed books: " + bookTypeCount("P"));
        for (Book book: books){
            if (book.getType().equals("P")){
                output.add(String.format("Printed [id: %d]", book.getId()));
            }
        }
        output.add("");
    }
    //get the number of handwritten books that are recorded as a member to the system of library
    public static void noOfHandWritten(){
        output.add("Number of handwritten books: " + bookTypeCount("H"));
        for (Book book: books){
            if (book.getType().equals("H")){
                output.add(String.format("Handwritten [id: %d]", book.getId()));
            }
        }
        output.add("");
    }
    // output the borrowed books, that have not been returned to the library yet
    public static void borrowedCheck(){
        output.add("Number of borrowed books: " + borrowedCount());
        for (Book book: borrowed){
            if (book.getIsBorrowed()){
                output.add(String.format("The book [%d] was borrowed by member [%d] at %s", book.getId(), book.getBorrower(), book.getBorrowedDate()));
            }
        }
        output.add("");
    }
    //output the number of books that have been read in library alongside with their id and id of the reader of them
    public static void readCheck(){
        output.add("Number of books read in library: " + readCount());
        for (Book book: read){
            if (book.getIsBorrowed() && checkReadOnly(book.getId())){
                output.add(String.format("The book [%d] was read in library by member [%d] at %s", book.getId(), book.getBorrower(), book.getBorrowedDate()));
            }
        }
    }
    //check the fee that a member has to pay to the library
    public static int checkFee(String date1, String date2, Member person, boolean extend){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date1Parsed = LocalDate.parse(date1, formatter);
        LocalDate date2Parsed = LocalDate.parse(date2, formatter);
        int difference = (int) ChronoUnit.DAYS.between(date1Parsed, date2Parsed);
        int result = 0;
        //depending on the person and the extension state of the book, calculate the fee accordingly and return the result
        if (person.getPerson().equals("S")){
            if (!extend){
                result = person.getDeadline() >= difference ? 0 : difference - person.getDeadline();
            }
            else{
                result = person.getDeadline() * 2 >= difference ? 0 : difference - person.getDeadline() * 2;
            }
        }
        else if (person.getPerson().equals("A")){
            if (!extend){
                result = person.getDeadline() >= difference ? 0 : difference - person.getDeadline();
            }
            else{
                result = person.getDeadline() * 2 >= difference ? 0 : difference - person.getDeadline() * 2;
            }
        }
        return result;
    }

    // for the extension request, depending on the person, add a number of days to the current deadline and return new deadline
    public static String addDays(String dateStr, int daysToAdd) {
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        date = date.plusDays(daysToAdd);
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }   

    // given the type of a person either student or academician, return the number of that type recorded in the system
    public static int memberTypeCount(String type){
        int result = 0;
        for (Member member: members){
            if (member.getPerson().equals(type)){
                result += 1;
            }
        }
        return result;
    }
    // given the type of a book either printed or handwritten, return the number of that type recorded in the system
    public static int bookTypeCount(String type){
        int result = 0;
        for (Book book: books){
            if (book.getType().equals(type)){
                result += 1;
            }
        }
        return result;
    }
    // return the count of books that have not been returned
    public static int borrowedCount(){
        int result = 0;
        for (Book book: borrowed){
            if (book.getIsBorrowed()){
                result += 1;
            }
        }
        return result;
    }
    // return the count of books that have been kept to read yet not returned
    public static int readCount(){
        int result = 0;
        for (Book book: read){
            if (book.getIsBorrowed() && checkReadOnly(book.getId())){
                result += 1;
            }
        }
        return result;
    }
    // check the books that have been kept to read and not returned yet
    // this method exist in order to prevent the confusion between the types of book that have been borrowed
    // borrowed ones can be printed or handwritten
    public static boolean checkReadOnly(int id){
        for (Book book: borrowed){
            if (book.getId() == id){
                return false;
            }
        }
        return true;
    }
}