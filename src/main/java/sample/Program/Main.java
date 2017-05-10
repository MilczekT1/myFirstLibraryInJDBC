package sample.Program;

import sample.LibraryStuff.Library;
import sample.LibraryStuff.Book;
import sample.LibraryStuff.BookReader;
import sample.LibraryStuff.Rent;

/*
Projekt powinien symulować działanie biblioteki.
        Zaimplementuj następujące funkcjonalności:
        * dodawanie książek do biblioteki       +
        * dodawanie osób do biblioteki          +
        * dodawanie wypożyczeń do biblioteki    +
        * wyświetlanie wszystkich książek       +
        * wyświetlanie wszystkich wypożyczeń    +
        * wyświetlanie wszystkich osób          +
        * usuwanie konkretnej osoby              +
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
