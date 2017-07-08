package LibraryStuff;

import lombok.Cleanup;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

class DbLibraryManager {
    
    private static final String DB = "jdbc:mysql://5.135.218.27:3306/Konrad_Boniecki";
    private static final String USER = "Konrad_Boniecki";
    private static final String USERPW = "Kondzio";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static Connection connection;
    private static final DbLibraryManager instance = new DbLibraryManager();
    
    private DbLibraryManager(){
        try {
            Class.forName(DRIVER).newInstance();
            connection = DriverManager.getConnection(DB, USER, USERPW);
            createTablesIfNotExists();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }
    
    public static DbLibraryManager getInstance()throws IllegalStateException{
        if (instance != null)
            return instance;
        else
            throw new IllegalStateException();
    }
    
    private static void createTablesIfNotExists() throws  SQLException{
        @Cleanup
        Statement statement = connection.createStatement();
        
        try {
            Path path = Paths.get("src/main/resources/","Create.sql");
            List<String> lines = Files.readAllLines(path, Charset.forName("UTF-8"));
            
            StringBuilder query = new StringBuilder();
            for (String line : lines){
                query.append(line);
                if (line.endsWith(";")){
                    statement.execute(query.toString());
                    query = new StringBuilder();
                }
            }
        } catch ( IOException e) {
            e.printStackTrace();
        }
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
    protected static LibraryItem dbGetObjectFromTableWithId(String tableName, Integer wantedId) throws SQLException, IllegalArgumentException {
        String selectBook = "SELECT * FROM books WHERE bookID = " + wantedId;
        String selectReader = "SELECT * FROM readers WHERE readerID = " + wantedId;
        String selectRent = "SELECT * FROM rents WHERE rentID = " + wantedId;
        
        @Cleanup
        Statement statement = connection.createStatement();
        
        switch (tableName){
            case "books":
                @Cleanup
                ResultSet book = statement.executeQuery(selectBook);
                if(book.next()) {
                    return new Book(book.getString("title"), book.getInt("pages"), book.getInt("bookID"));
                } else { break; }
            case "readers":
                @Cleanup
                ResultSet reader = statement.executeQuery(selectReader);
                if(reader.next()) {
                    return new BookReader(reader.getString("name"),reader.getString("surname"), reader.getInt("readerID"));
                } else { break; }
            case "rents":
                @Cleanup
                ResultSet rent = statement.executeQuery(selectRent);
                if(rent.next()) {
                    return new Rent(rent.getInt("bookID"),rent.getInt("readerID"),rent.getInt("rentID"));
                } else { break; }
            default:
                System.out.println("error: no such object in database");
                break;
        }
        throw new SQLException("error selecting object from db");
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
        String query = "INSERT INTO rents(readerID, bookID, endDate) VALUES(?, ?, ?)";
    
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 30); // DEFAULT RENT TIME 30 DAYS
        
        @Cleanup
        PreparedStatement preStatement = connection.prepareStatement(query);
        
        preStatement.setInt(1, readerID);
        preStatement.setInt(2, bookID);
        preStatement.setTimestamp(3, new Timestamp(cal.getTime().getTime()));
        preStatement.execute();
        
        //TODO: log it
        System.out.println("added rent");
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
