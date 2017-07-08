package LibraryStuff;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestBookReader {
    private static BookReader bookReader;
    
    @Before
    public void beforeTest(){
        bookReader = new BookReader("Konrad", "Boniecki");
    }
    @AfterClass
    public static void shutDown(){
        bookReader = null;
    }
    
    
    @Test
    public void test2ArgConstructors(){
        // Correct all arguments
        try {
            bookReader = new BookReader("Konrad", "Boniecki");
        } catch(IllegalArgumentException e) {
            Assert.fail("IllegalArgumentException was thrown");
        }
        
        // Incorrect name
        try {
            bookReader = new BookReader(null, "Boniecki");
            Assert.fail("IllegalArgumentException was not thrown");
        } catch(IllegalArgumentException e) { ; }
        
        // Incorrect surname
        try {
            bookReader = new BookReader("Konrad", null);
            Assert.fail("IllegalArgumentException was not thrown");
        } catch(IllegalArgumentException e) { ; }
        
        // Incorrect all arguments
        try {
            bookReader = new BookReader(null, null);
            Assert.fail("IllegalArgumentException was not thrown");
        } catch(IllegalArgumentException e) { ; }
    }
    
    @Test
    public void test3ArgConstructors(){
        // Correct all arguments
        try {
            bookReader = new BookReader("Konrad", "Boniecki",5);
        } catch(IllegalArgumentException e) {
            Assert.fail("IllegalArgumentException was thrown");
        }
    
        // Incorrect all arguments;
        try {
            bookReader = new BookReader(null, null,null);
            Assert.fail("IllegalArgumentException was not thrown");
        } catch(IllegalArgumentException e) { ; }
        
        try {
            bookReader = new BookReader(null, null, 0);
            Assert.fail("IllegalArgumentException was not thrown");
        } catch(IllegalArgumentException e) { ; }
    
        // Incorrect name
        try {
            bookReader = new BookReader(null, "Boniecki", 5);
            Assert.fail("IllegalArgumentException was not thrown");
        } catch(IllegalArgumentException e) { ; }
    
        // Incorrect surname
        try {
            bookReader = new BookReader("Konrad", null,5);
            Assert.fail("IllegalArgumentException was not thrown");
        } catch(IllegalArgumentException e) { ; }
        
        // Incorrect id
        try {
            bookReader = new BookReader("Konrad", "Boniecki",0);
            Assert.fail("IllegalArgumentException was not thrown");
        } catch(IllegalArgumentException e) { ; }
        
        try {
            bookReader = new BookReader("Konrad", "Boniecki",null);
            Assert.fail("IllegalArgumentException was not thrown");
        } catch(IllegalArgumentException e) { ; }
    }
}
