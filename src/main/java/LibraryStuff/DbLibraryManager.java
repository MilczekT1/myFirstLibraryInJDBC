package LibraryStuff;

import lombok.Cleanup;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

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
    //TODO: creates from file
    
    protected static void createTablesIfNotExists() throws  SQLException{
        DatabaseMetaData md = connection.getMetaData();
        @Cleanup
        ResultSet rs = md.getTables(null, null, "%", null);
        @Cleanup
        Statement statement = connection.createStatement();
        if (!rs.next()) {
            statement.execute("CREATE TABLE IF NOT EXISTS readers (" +
                                      "readerID INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                                      "name text NOT NULL," +
                                      "surname text NOT NULL)");
            statement.execute("CREATE TABLE IF NOT EXISTS books (" +
                                      "bookID INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                                      "title text NOT NULL," +
                                      "pages INTEGER)");
            statement.execute("CREATE TABLE IF NOT EXISTS rents (" +
                                      "rentID INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                                      "readerID INTEGER NOT NULL," +
                                      "bookID INTEGER NOT NULL)");
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