package Program;

import LibraryStuff.Book;
import LibraryStuff.BookReader;
import LibraryStuff.Rent;
import lombok.Cleanup;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;

public class Library {
    /*private DatabaseManager database;
    
    public Library() {
        database = new DatabaseManager();
    }
    */
    public void addBookReader(BookReader reader){
        try {
            DatabaseManager.dbAddBookReader(reader.getName(), reader.getSurname());
            //database.dbAddBookReader(reader.getName(), reader.getSurname());
        } catch (SQLException e) {
            System.out.println("Adding book reader to db failed");
        }
    }
    public void addBook(Book book){
        try {
            DatabaseManager.dbAddBook(book.getTitle(),book.getPages());
            //database.dbAddBook(book.getTitle(),book.getPages());
        } catch (SQLException e) {
            System.out.println("Adding book to db failed");
        }
    }
    public void addRent(Rent rent){
        try {
            DatabaseManager.dbAddRent(rent.getBookID(), rent.getReaderID());
            //database.dbAddBook(rent.getBookID(), rent.getReaderID());
        } catch (SQLException e) {
            System.out.println("Adding rent to db failed");
        }
    }
    public void showAll(String tablename, int columns){
        try {
            @Cleanup
            CachedRowSet crs = DatabaseManager.dbGetAllFromTable(tablename);
            //CachedRowSet crs = database.dbGetAllFromTable(tablename);
            while(crs.next()) {
                for (int i = 1; i <= columns; i++) {
                    System.out.print(crs.getString(i) + " ");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
