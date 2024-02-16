import java.util.ArrayList;

public class Member{
    // define attributes of the member object as follows. Private is used for safety
    private static int idIncrement = 1;
    private int id;
    private String person;
    //store the borrowed books of the member inside of an array list
    private ArrayList<Book> borrowedBooks = new ArrayList<Book>();
    private int borrowLimit;
    private int maxDeadline;

    //using constructor, set member type, borrow limit, and maximum deadline time for member alongside of the id of member
    public Member(String person){
        this.id = idIncrement++;
        this.person = person;
        this.borrowLimit = this.person.equals("A") ? 4 : 2;
        this.maxDeadline = this.person.equals("A") ? 14 : 7;
    }
    //using getter and setter methods, access and modify the wanted properties of the member
    public int getId(){
        return this.id;
    }

    public String getPerson(){
        return this.person;
    }

    public ArrayList<Book> getBorrowedBooks(){
        return this.borrowedBooks;
    }

    public int getBorrowLimit(){
        return this.borrowLimit;
    }

    public void addBorrowedBook(Book book){
        this.borrowedBooks.add(book);
    }

    public void setBorrowLimit(int i){
        this.borrowLimit += i;
    }

    public int getDeadline(){
        return this.maxDeadline;
    }

}