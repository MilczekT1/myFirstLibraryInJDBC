package Program;

import LibraryStuff.Book;
import LibraryStuff.BookReader;
import lombok.Cleanup;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;

public class Library {
    private DatabaseManager database;
    
    public Library() {
        database = new DatabaseManager();
    }
    
    public void addBookReader(BookReader reader){
        try {
            database.dbAddBookReader(reader.getName(), reader.getSurname());
        } catch (SQLException e) {
            System.out.println("Adding book reader to db failed");
        }
    }
    public void addBook(Book book){
        try {
            database.dbAddBook(book.getTitle(),book.getPages());
        } catch (SQLException e) {
            System.out.println("Adding book to db failed");
        }
    }
    public void showAll(String tablename, int columns){
        try {
            @Cleanup
            CachedRowSet crs = database.dbGetAllFromTable(tablename);
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
