/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Library;

import java.util.Date;
import java.util.*;

/**
 *
 * @author Akshay Gupta
 */
/**
 * Requirments
 * 1. Librarian can add a shelf, book, call persist and load database
 * 2. book should have id, title, author, and detials
 * 3. Member have a library card 
 * 4. Search a book by author, title, 
 * 5. Issue a book if available 10 days issue
 * 6.While a book is issued or not system should should see current status.
 * 7. Request reservation if not available
 * 8. Return book if overdue pay fine
 * 9. Log all the data who borrowed book and time and fine
 */

/*
Assumptions
1. book will go where it comes from
2. One demo book
*/

public class Library {

    String address;
    String name;
    BookCollection bookCollection;
    LocationCollection locations;

    public Library(String address, String name) {
        this.address = address;
        this.name = name;
        this.bookCollection = new IITBookCollection();
        this.locations = new IITBLocationCollection();
    }
    
}
abstract class Person{
    String name;
    int uniqueId;

    public Person(String name, int uniqueId) {
        this.name = name;
        this.uniqueId = uniqueId;
    }

    @Override
    public String toString() {
        return "Person{" + "name=" + name + ", uniqueId=" + uniqueId + '}';
    }
    
}
interface Admin {

    void addLocation(Location location);

    void addBookCopy(BookCopy book) throws  LibraryFull,BookNotAvailable;
    
    void addBooType(BookType bookType);
}

interface Librarian {

    Ticket bookNow(Member member,String book) throws BookNotAvailable, NoBookCopyAvaialble;

    Fine returnBook(Ticket ticket);
    
    Location askLocation(String book)throws BookNotAvailable,NoBookCopyAvaialble;
}

interface Member {
    
    Ticket requestBook(String book) throws BookNotAvailable,NoBookCopyAvaialble;

    Fine returnBook(Ticket ticket);

    Location search(String title) throws BookNotAvailable,NoBookCopyAvaialble;
}

class Fine {

    double amount;
    Member member;

    public Fine(double amount, Member member) {
        this.amount = amount;
        this.member = member;
    }

}

class BookType {

    private String name;
    private String bookId;
    private String author;
    private Date pubDate;
    private Queue<BookCopy> copyIds;

    public BookType(String name, String bookId, String author, Date pubDate) {
        this.name = name;
        this.bookId = bookId;
        this.author = author;
        this.pubDate = pubDate;
        this.copyIds = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    boolean isAvailalbe() {
        return !copyIds.isEmpty();
    }

    Location firstLocation() throws NoBookCopyAvaialble {
        if (!isAvailalbe()) {
            throw new NoBookCopyAvaialble();
        }
        return copyIds.peek().location;
    }

    BookCopy getOne() throws NoBookCopyAvaialble {
        if (!isAvailalbe()) {
            throw new NoBookCopyAvaialble();
        }
        return copyIds.poll();
    }

    void putBackOne(BookCopy returning) {
        copyIds.add(returning);
    }

    void addCopy(BookCopy bookCopy) {
        copyIds.add(bookCopy);
    }

    void removeCopy(BookCopy bookCopy) throws NoBookCopyAvaialble {
        copyIds.remove(bookCopy);
    }

    @Override
    public String toString() {
        return "Book{" + "name=" + name + ", bookId=" + bookId + ", author=" + author + ", pubDate=" + pubDate + '}' + " Total " + copyIds.size() + " Copies available";
    }

}

class BookCopy{

    int uniqueCopyId;
    Location location;
    BookType type;

    public BookCopy(int uniqueCopyId, BookType type) {
        this.uniqueCopyId = uniqueCopyId;
        this.type = type;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "BookCopy{" + "uniqueCopyId=" + uniqueCopyId + ", location=" + location + ", type=" + type + '}';
    }
}

interface BookCollection {

    Location search(String title) throws BookNotAvailable, NoBookCopyAvaialble;

    boolean CheckAvailablility(String book);

    void addNewBook(BookType book);

    void addBookCopy(BookCopy bookCopy) throws BookNotAvailable;

    void removeBookCopy(BookCopy bookCopy) throws NoBookCopyAvaialble, BookNotAvailable;

    Ticket issueOneCopy(Member member, String bookName) throws BookNotAvailable, NoBookCopyAvaialble;

    Fine returnOneBook(Ticket ticket) throws BookNotAvailable;
}


class LocationFilled extends Exception {

}
class BookNotAvailable extends Exception {

}

class NoBookCopyAvaialble extends Exception {

}

class LibraryFull extends Exception{
    
}

class NoSuchLocation extends Exception {

}

class Ticket {

    Member member;
    BookCopy book;
    Date issueDate;
    Date exptectedReturn;
    int fineMutliplier;

    public Ticket(Member member, BookCopy book, Date issueDate) {
        this.member = member;
        this.book = book;
        this.issueDate = issueDate;
    }

    @Override
    public String toString() {
        return "Ticket{" + "member=" + member + ", book=" + book + ", issueDate=" + issueDate + ", exptectedReturn=" + exptectedReturn + ", fineMutliplier=" + fineMutliplier + '}';
    }

    
}


class Location {
    int floor, shelf, row, col;
    
    public Location(int floor, int shelf, int row, int col) {
        this.floor = floor;
        this.shelf = shelf;
        this.row = row;
        this.col = col;
    }
    
}

interface LocationCollection{
    Location fillFirstFree() throws LibraryFull;
    void vacateThisOne(Location location) throws NoSuchLocation;
    void addLocation(Location location);
    void removeLocation(Location location) throws NoSuchLocation;
}
