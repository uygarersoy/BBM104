public class Book{
    // define attributes of the book object as follows. Private is used for safety
    private static int idIncrement = 1;
    private int id;
    private String type;
    private boolean isBorrowable;
    private boolean isBorrowed = false;
    private String borrowedDate;
    private boolean isExtended = false;
    private int borrower;

    //using constructor, set type and borrowable state alongside of the id of book
    public Book(String type){
        this.id = idIncrement++;
        this.type = type;
        this.isBorrowable = this.type.equals("P");
    }
    //using getter and setter methods, access and modify the wanted properties of the book
    public int getId(){
        return this.id;
    }

    public String getType(){
        return this.type;
    }
    
    public boolean getBorrowable(){
        return this.isBorrowable;
    }

    public void setIsBorrowable(boolean changedState){
        this.isBorrowable = changedState;
    }
    public boolean getIsBorrowed(){
        return this.isBorrowed;
    }
    public void setIsBorrowed(boolean state){
        this.isBorrowed = state;
    }

    public void setBorrowedDate(String date){
        this.borrowedDate = date;
    }
    public String getBorrowedDate(){
        return this.borrowedDate;
    }
    public boolean getIsExtended(){
        return this.isExtended;
    }
    public void setIsExtended(boolean newStatus){
        this.isExtended = newStatus;
    }
    public int getBorrower(){
        return this.borrower;
    }
    public void setBorrower(int person){
        this.borrower = person;
    }
}