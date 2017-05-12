package LibraryStuff;

import lombok.Cleanup;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;

public class DbLibraryManager {
    private static final String DB = "jdbc:mysql://5.135.218.27:3306/Konrad_Boniecki";
    private static final String USER = "Konrad_Boniecki";
    private static final String USERPW = "Kondzio";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static Connection connection;
    private static DbLibraryManager instance = new DbLibraryManager();
    
    private DbLibraryManager(){
        try {
            Class.forName(DRIVER).newInstance();
            connection = DriverManager.getConnection(DB, USER, USERPW);
            createTablesIfNotExists();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }
    
    private static DbLibraryManager getInstance(){
        return instance;
    }
    
    protected static void createTablesIfNotExists() throws  SQLException{
        DatabaseMetaData md = connection.getMetaData();
        @Cleanup
        Statement statement = connection.createStatement();
    
        try {
            Path path = Paths.get("src/main/resources/","Create.sql");
            List<String> lines = Files.readAllLines(path, Charset.forName("UTF-8"));
        
            String query = "";
            for (String line : lines){
                query += line;
                if (line.endsWith(";")){
                    statement.execute(query);
                    query = "";
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    protected static void dbAddBookReader(String name, String surname) throws SQLException {
        String query = "INSERT INTO readers(name, surname) VALUES(?, ?)";
        
        @Cleanup
        PreparedStatement preStatement = connection.prepareStatement(query);
        
        preStatement.setString(1, name);
        preStatement.setString(2, surname);
        preStatement.execute();
        
        //TODO: log it
        System.out.println("added bookReader");
    }
    protected static void dbAddBook(String title, Integer pages)throws SQLException{
        String query = "INSERT INTO books(title, pages) VALUES(?, ?)";
        
        @Cleanup
        PreparedStatement preStatement = connection.prepareStatement(query);
        preStatement.setString(1, title);
        preStatement.setString(2, pages.toString());
        preStatement.execute();
        
        //TODO: log it
        System.out.println("added book");
    }
    protected static void dbAddRent(Integer readerID, Integer bookID) throws SQLException{
        String query = "INSERT INTO rents(readerID, bookID) VALUES(?, ?)";
        
        @Cleanup
        PreparedStatement preStatement = connection.prepareStatement(query);
        
        preStatement.setInt(1, readerID);
        preStatement.setInt(2, bookID);
        preStatement.execute();
    
        //TODO: log it
        System.out.println("added rent");
    }
    
    protected static CachedRowSet dbGetAllFromTable(String tableName) throws SQLException{
        @Cleanup
        Statement statement = connection.createStatement();
        RowSetFactory factory = RowSetProvider.newFactory();
        CachedRowSet crs = factory.createCachedRowSet();
        
        @Cleanup
        ResultSet rs = statement.executeQuery("SELECT * FROM " + tableName);
        crs.populate(rs);
        crs.setTableName(tableName);
        return crs;
    }
    
    protected static Object dbGetObjectFromTableWithId(String tableName, Integer wantedId) throws SQLException {
        String query = "SELECT * FROM ? WHERE ?=?"; // TODO: LIMIT 1
        @Cleanup
        PreparedStatement preStatement = connection.prepareStatement(query);
    
        preStatement.setString(1, tableName);
        preStatement.setInt(3,wantedId);
    
        Object result = null;
        switch (tableName){
            case "books":
                result = (Book) result;
                preStatement.setString(2, "bookID");
                @Cleanup
                ResultSet book = preStatement.executeQuery();
                if(book.next()) {
                    ((Book) result).setId(book.getInt("bookID"));
                    ((Book) result).setPages(book.getInt("pages"));
                    ((Book) result).setTitle(book.getString("title"));
                }
                break;
            case "readers":
                result = (BookReader) result;
                preStatement.setString(2, "readerID");
                @Cleanup
                ResultSet reader = preStatement.executeQuery();
                if(reader.next()) {
                    ((Book) result).setId(reader.getInt("bookID"));
                    ((Book) result).setPages(reader.getInt("pages"));
                    ((Book) result).setTitle(reader.getString("title"));
                }
                break;
            case "rents":
                result = (Rent) result;
                preStatement.setString(2, "rentID");
                @Cleanup
                ResultSet rent = preStatement.executeQuery();
                if(rent.next()) {
                    ((Book) result).setId(rent.getInt("bookID"));
                    ((Book) result).setPages(rent.getInt("pages"));
                    ((Book) result).setTitle(rent.getString("title"));
                }
                break;
            default:
                System.out.println("error: no such object in database");
                break;
        }
        return result;
    }
    
    protected static void dbDeleteBookReader(Integer bookReaderID) throws SQLException {
        String query = "DELETE FROM readers WHERE readerID = ?";
        @Cleanup
        PreparedStatement preStatement = connection.prepareStatement(query);
        preStatement.setInt(1, bookReaderID);
        preStatement.execute();
    
        System.out.println("deleted book reader with ID: " + bookReaderID);
    }
    protected static void dbDeleteBook(Integer bookID) throws SQLException {
        String query = "DELETE FROM books WHERE bookID = ?";
        @Cleanup
        PreparedStatement preStatement = connection.prepareStatement(query);
        preStatement.setInt(1, bookID);
        preStatement.execute();
        
        System.out.println("deleted book with ID: " + bookID);
    }
    protected static void dbDeleteRent(Integer rentID) throws SQLException {
        String query = "DELETE FROM rents WHERE rentID = ?";
        @Cleanup
        PreparedStatement preStatement = connection.prepareStatement(query);
        preStatement.setInt(1, rentID);
        preStatement.execute();
        
        System.out.println("deleted rent with ID: " + rentID);
    }
    
}
