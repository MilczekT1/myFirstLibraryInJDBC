package Program;

import lombok.Cleanup;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

public class DatabaseManager {
    private static final String DB = "jdbc:mysql://5.135.218.27:3306/Konrad_Boniecki";
    private static final String USER = "Konrad_Boniecki";     //"oskar";
    private static final String USERPW = "Kondzio";             //"akademiakodu";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private Connection connection;
    
    //TODO: creates from file
    
    public DatabaseManager(){
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
            //TODO:  log no database connection
            return;
        }
    }
    
    private void createTablesIfNotExists() throws  SQLException{
        DatabaseMetaData md = connection.getMetaData();
        ResultSet rs = md.getTables(null, null, "%", null);
        @Cleanup
        Statement statement = connection.createStatement();
        if (!rs.next()) {
            statement.execute("CREATE TABLE readers (" +
                                      "readerID INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                                      "name text NOT NULL," +
                                      "surname text NOT NULL)");
            statement.execute("CREATE TABLE books (" +
                                      "bookID INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                                      "title text NOT NULL," +
                                      "pages INTEGER)");
            statement.execute("CREATE TABLE rents (" +
                                      "rentID INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                                      "readerID INTEGER NOT NULL," +
                                      "bookID INTEGER NOT NULL)");
        }
        rs.close();
    }
    public void dbAddBookReader(String name, String surname) throws SQLException {
        String query = "INSERT INTO readers(name, surname) VALUES(?, ?)";
        
        @Cleanup
        PreparedStatement preStatement = connection.prepareStatement(query);
        
        preStatement.setString(1, name);
        preStatement.setString(2, surname);
        preStatement.execute();
        
        //TODO: log it
        System.out.println("added bookReader");
    }
    public void dbAddBook(String title, Integer pages)throws SQLException{
        String query = "INSERT INTO books(title, pages) VALUES(?, ?)";
        
        @Cleanup
        PreparedStatement preStatement = connection.prepareStatement(query);
        preStatement.setString(1, title);
        preStatement.setString(2, pages.toString());
        preStatement.execute();
        
        //TODO: log it
        System.out.println("added book");
    }
    public void dbAddRent(int readerID, int bookID) throws SQLException{
        String query = "INSERT INTO rents(readerID, bookID) VALUES(?, ?)";
        
        @Cleanup
        PreparedStatement preStatement = connection.prepareStatement(query);
        
        preStatement.setInt(1, readerID);
        preStatement.setInt(2, bookID);
        preStatement.execute();
    
        //TODO: log it
        System.out.println("added rent");
    }
    
    public CachedRowSet dbGetAllFromTable(String tableName) throws SQLException{
        @Cleanup
        Statement statement = connection.createStatement();
        RowSetFactory factory = RowSetProvider.newFactory();
        CachedRowSet crs = factory.createCachedRowSet();
    
        ResultSet rs = statement.executeQuery("SELECT * FROM " + tableName);
        crs.populate(rs);
        crs.setTableName(tableName);
        
        return crs;
    }
    
}
