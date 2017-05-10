package sample.Program;

import LibraryStuff.Library;
import LibraryStuff.Book;
import LibraryStuff.BookReader;
import LibraryStuff.Rent;


/*
Project should simulate simple library with:
    + Add book to lib
    + Add user to lib
    + Add rent to lib
    + Show all books
    + Show all users
    + Show all rents
    + Remove user

*/
//todo: delete user using object
public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        
        library.addBookReader(new BookReader("James", "Bond"));
        library.addBook(new Book("LOTR", 4676));
        library.addRent(new Rent(3,4));
        library.deleteBookReader(5);
        library.showAll("readers",3);
        library.showAll("books",3);
        library.showAll("rents", 3);
        
        System.out.println("Amen");
    }
}
