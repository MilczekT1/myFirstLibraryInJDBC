package Program;

import LibraryStuff.Book;
import LibraryStuff.BookReader;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.SQLException;

/*
Projekt powinien symulować działanie biblioteki.
        Zaimplementuj następujące funkcjonalności:
        * dodawanie książek do biblioteki       +
        * dodawanie osób do biblioteki          +
        * dodawanie wypożyczeń do biblioteki
        * edycja danych osób w bibliotece
        * wyświetlanie wszystkich książek
        * wyświetlanie wszystkich wypożyczeń
        * wyświetlanie wszystkich osób
        * usuwanie konkretnej osoby  
*/
public class Main {
    public static void main(String[] args) {
        DatabaseManager database = new DatabaseManager();
        Library library = new Library();
        
        library.addBookReader(new BookReader("James", "Bond"));
        library.addBook(new Book("LOTR", 4676));
        library.showAll("readers",3);
        library.showAll("books",3);
        System.out.println("Amen");
        
        try {
            CachedRowSet crs = database.dbGetAllFromTable("readers");
            
            while(crs.next()){
                System.out.print(crs.getString(1) + " ");
                System.out.print(crs.getString(2) + " ");
                System.out.println(crs.getString(3));
            }
            crs.close();
            crs = database.dbGetAllFromTable("books");
            while(crs.next()){
                System.out.print(crs.getString(1) + " ");
                System.out.print(crs.getString(2) + " ");
                System.out.println(crs.getString(3));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
