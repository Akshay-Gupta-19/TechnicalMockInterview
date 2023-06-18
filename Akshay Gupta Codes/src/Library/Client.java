/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Library;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Akshay Gupta
 */
public class Client {
    public static void main(String[] args) {
        Library library=new Library("Shaitan gali", "WBH");
        Admin admin=new IITBAdmin(library, "admin1", 1010);
        Librarian librarian=new IITBLibrarion(library,"Librarion1",1020);
        Member member = new IITBMemeber(librarian,"Member1",1030);
        Member member2 = new IITBMemeber(librarian,"Member2",1031);
        Member member3 = new IITBMemeber(librarian,"Member3",1032);
        
        admin.addLocation(new Location(0,1,1,1));
        admin.addLocation(new Location(0,1,1,2));
        admin.addLocation(new Location(0,1,1,3));
        try {
            BookType harryBook=new BookType("Harry potter", "101", "JKR", new Date(10,11,12));
            admin.addBooType(harryBook);
            admin.addBookCopy(new BookCopy(101,harryBook));
            admin.addBookCopy(new BookCopy(102,harryBook));
            admin.addBookCopy(new BookCopy(103,harryBook));
            Ticket ticket1 = member.requestBook("Harry potter");
            System.out.println("Ticket1 "+ticket1);
            Ticket ticket2 = member2.requestBook("Harry potter");
            System.out.println("Ticket2 "+ticket2);
            Ticket ticket3 = member3.requestBook("Harry potter");
            System.out.println("Ticket3 "+ticket3);
        } catch (LibraryFull ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BookNotAvailable ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoBookCopyAvaialble ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
