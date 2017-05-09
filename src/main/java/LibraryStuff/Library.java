package LibraryStuff;

import LibraryStuff.Book;
import LibraryStuff.BookReader;
import LibraryStuff.Rent;
import Program.DatabaseManager;
import lombok.Cleanup;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;

public class Library {

    public void addBookReader(BookReader reader){
        try {
            DatabaseManager.dbAddBookReader(reader.getName(), reader.getSurname());
        } catch (SQLException e) {
            System.out.println("Adding book reader to db failed");
        }
    }
    public void addBook(Book book){
        try {
            DatabaseManager.dbAddBook(book.getTitle(),book.getPages());
        } catch (SQLException e) {
            System.out.println("Adding book to db failed");
        }
    }
    public void addRent(Rent rent){
        try {
            DatabaseManager.dbAddRent(rent.getBookID(), rent.getReaderID());
        } catch (SQLException e) {
            System.out.println("Adding rent to db failed");
        }
    }
    public void showAll(String tablename, int columns){
        try {
            @Cleanup
            CachedRowSet crs = DatabaseManager.dbGetAllFromTable(tablename);
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
    public void deleteBookReader(Integer bookReaderID) {
        try {
            DatabaseManager.dbDeleteBookReader(bookReaderID);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("deleting book reader failed");
        }
    }
}
