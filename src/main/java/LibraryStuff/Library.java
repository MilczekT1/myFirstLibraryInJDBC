package LibraryStuff;

import lombok.Cleanup;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;

public class Library {
    
    public void addBookReader(String name, String surname) throws SQLException {
        DbLibraryManager.dbAddBookReader(name, surname);
    }
    public void addBook(String title, Integer pages) throws SQLException {
        DbLibraryManager.dbAddBook(title, pages);
    }
    public void addRent(Integer bookID, Integer readerID) throws SQLException {
        DbLibraryManager.dbAddRent(bookID, readerID);
    }
    
    public void deleteBookReader(Integer bookReaderID) throws SQLException {
        DbLibraryManager.dbDeleteBookReader(bookReaderID);
    }
    public void deleteBook(Integer bookID) throws SQLException {
        DbLibraryManager.dbDeleteBook(bookID);
    }
    public void deleteRent(Integer rentID) throws SQLException {
        DbLibraryManager.dbDeleteRent(rentID);
    }
    
    public boolean showAll(String tableName) throws SQLException{
        @Cleanup
        CachedRowSet crs = DbLibraryManager.dbGetAllFromTable(tableName);
        if(crs.size() > 0) {
            int counter = crs.size();
            while (crs.next()) {
                for (int i = 1; i <= counter; i++) {
                    try {
                        System.out.print(crs.getString(i) + " ");
                    } catch (SQLException notEnoughColumns) {
                        counter = i;
                        break;
                    }
                }
                System.out.println();
            }
            return true;
        }
        else
            return false;
    }
    public LibraryItem getObjectFromTable(String tableName, int wantedId){
        LibraryItem resultObject= null;
        try {
            resultObject = DbLibraryManager.dbGetObjectFromTableWithId(tableName, wantedId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultObject;
    }
}
