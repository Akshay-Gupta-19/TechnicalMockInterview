/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Library;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 *
 * @author Akshay Gupta
 */
public class IITBLibrary {
    
}
class IITBookCollection implements BookCollection {

    Map<String, BookType> books;

    public IITBookCollection() {
        books=new HashMap<>();
    }
    
    @Override
    public Location search(String title) throws BookNotAvailable, NoBookCopyAvaialble {
        if (!books.containsKey(title)) {
            throw new BookNotAvailable();
        }
        return books.get(title).firstLocation();
    }

    @Override
    public boolean CheckAvailablility(String bookName) {
        return books.containsKey(bookName) && books.get(bookName).isAvailalbe();
    }

    void isBookPresent(String book) throws BookNotAvailable {
        if (!books.containsKey(book)) {
            throw new BookNotAvailable();
        }
    }

    @Override
    public Ticket issueOneCopy(Member member, String book) throws BookNotAvailable, NoBookCopyAvaialble {
        isBookPresent(book);
        BookCopy issuingCopy = books.get(book).getOne();
        Ticket ticket = new Ticket(member, issuingCopy, new Date());
        return ticket;
    }

    @Override
    public void addNewBook(BookType book) {
        System.out.println("Adding book type "+book);
        books.put(book.getName(), book);
    }

    @Override
    public void addBookCopy(BookCopy bookCopy) throws BookNotAvailable {
        isBookPresent(bookCopy.type.getName());
        books.get(bookCopy.type.getName()).addCopy(bookCopy);
    }

    @Override
    public void removeBookCopy(BookCopy bookCopy) throws NoBookCopyAvaialble, BookNotAvailable {
        isBookPresent(bookCopy.type.getName());
        books.get(bookCopy.type.getName()).removeCopy(bookCopy);
    }

    @Override
    public Fine returnOneBook(Ticket ticket) throws BookNotAvailable{
        Date currDate = new Date();
        addBookCopy(ticket.book);
        return new Fine(currDate.getDate() - ticket.exptectedReturn.getDate() * ticket.fineMutliplier, ticket.member);
    }

}

class IITBLocationCollection  implements LocationCollection{

    Queue<Location> locations;

    public IITBLocationCollection() {
        locations=new LinkedList<>();
    }
    
    void checkAvailable() throws LibraryFull{
        if(locations.isEmpty()){
            throw new LibraryFull();
        }
    }
    @Override
    public Location fillFirstFree()  throws LibraryFull{
        checkAvailable();
        return locations.poll();
    }

    @Override
    public void vacateThisOne(Location location) throws NoSuchLocation{
        locations.add(location);
    }

    @Override
    public void addLocation(Location location) {
        locations.add(location);
    }

    @Override
    public void removeLocation(Location location) throws NoSuchLocation {
        if(!locations.contains(location)){
            throw new NoSuchLocation();
        }
        locations.remove(location);
    }
    
}
class IITBMemeber extends Person implements Member{
    Librarian librarion;

    public IITBMemeber(Librarian librarion, String name, int uniqueId) {
        super(name, uniqueId);
        this.librarion = librarion;
    }
    

    @Override
    public Ticket requestBook(String book) throws BookNotAvailable, NoBookCopyAvaialble {
        return librarion.bookNow(this, book);
    }

    @Override
    public Fine returnBook(Ticket ticket) {
        return librarion.returnBook(ticket);
    }

    @Override
    public Location search(String title) throws BookNotAvailable, NoBookCopyAvaialble {
        return librarion.askLocation(title);
    }
    
}

class IITBLibrarion extends Person implements Librarian{

    Library library;

    public IITBLibrarion(Library library, String name, int uniqueId) {
        super(name, uniqueId);
        this.library = library;
    }

 
    @Override
    public Fine returnBook(Ticket ticket) {
        try{
            System.out.println("Returning book "+ticket);
           return library.bookCollection.returnOneBook(ticket);
        }catch(BookNotAvailable ex){
            System.err.println("Book type removed");
            return null;
        }
   }

    @Override
    public Ticket bookNow(Member member,String book) throws BookNotAvailable, NoBookCopyAvaialble {
       return library.bookCollection.issueOneCopy(member, book);
    }

    @Override
    public Location askLocation(String book) throws BookNotAvailable, NoBookCopyAvaialble {
        return library.bookCollection.search(book);
    }
    
}
class IITBAdmin extends Person implements Admin{

    Library library;

    public IITBAdmin(Library library, String name, int uniqueId) {
        super(name, uniqueId);
        this.library = library;
    }

    @Override
    public void addLocation(Location location) {
        library.locations.addLocation(location);
    }

    @Override
    public void addBookCopy(BookCopy book) throws LibraryFull,BookNotAvailable {
        Location firstFree = library.locations.fillFirstFree();
        book.setLocation(firstFree);
        library.bookCollection.addBookCopy(book);
        System.out.println("Adding book "+book+" On "+firstFree);
    }

    @Override
    public void addBooType(BookType bookType) {
        library.bookCollection.addNewBook(bookType);
    }

}