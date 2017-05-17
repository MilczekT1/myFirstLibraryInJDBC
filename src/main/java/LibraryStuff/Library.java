package LibraryStuff;

import lombok.Cleanup;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;

public class Library {
    
    public void addBookReader(String name, String surname){
        try {
            DbLibraryManager.dbAddBookReader(name, surname);
        } catch (SQLException e) {
            System.out.println("Adding book reader to db failed");
        }
    }
    public void addBook(String title, Integer pages){
        try {
            DbLibraryManager.dbAddBook(title, pages);
        } catch (SQLException e) {
            System.out.println("Adding book to db failed");
        }
    }
    public void addRent(Integer bookID, Integer readerID){
        try {
            DbLibraryManager.dbAddRent(bookID, readerID);
        } catch (SQLException e) {
            System.out.println("Adding rent to db failed");
        }
    }
    
    public void deleteBookReader(Integer bookReaderID) {
        try {
            DbLibraryManager.dbDeleteBookReader(bookReaderID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteBook(Integer bookID) {
        try {
            DbLibraryManager.dbDeleteBook(bookID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteRent(Integer rentID) {
        try {
            DbLibraryManager.dbDeleteRent(rentID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void showAll(String tableName){
        try {
            @Cleanup
            CachedRowSet crs = DbLibraryManager.dbGetAllFromTable(tableName);
            while(crs.next()) {
                for (int i = 1; i <= 50; i++) {
                    try {
                        System.out.print(crs.getString(i) + " ");
                    }
                    catch (SQLException notEnoughColumns){
                        break;
                    }
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public LibraryItem getObjectFromTable(String tableName, int wantedId){
        LibraryItem resultObject= null;
        try {
            resultObject = DbLibraryManager.dbGetObjectFromTableWithId(tableName, wantedId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultObject;
    }
}
