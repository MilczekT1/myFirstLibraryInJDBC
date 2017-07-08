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
        boolean exceptionCatched = false;
        try {
            bookReader = new BookReader("Konrad", "Boniecki");
        } catch(IllegalArgumentException e) {
            exceptionCatched = true;
        }
        if (exceptionCatched) Assert.fail("IllegalArgumentException was thrown");
        
        // Incorrect name
        exceptionCatched = false;
        try {
            bookReader = new BookReader(null, "Boniecki");
        } catch(IllegalArgumentException e) {
            exceptionCatched = true;
        }
        if (!exceptionCatched) Assert.fail("IllegalArgumentException was not thrown");
        
        // Incorrect surname
        exceptionCatched = false;
        try {
            bookReader = new BookReader("Konrad", null);
        } catch(IllegalArgumentException e) {
            exceptionCatched = true;
        }
        if (!exceptionCatched) Assert.fail("IllegalArgumentException was not thrown");
        
        // Incorrect all arguments
        exceptionCatched = false;
        try {
            bookReader = new BookReader(null, null);
        } catch(IllegalArgumentException e) {
            exceptionCatched = true;
        }
        if (!exceptionCatched) Assert.fail("IllegalArgumentException was not thrown");
    }
    
    @Test
    public void test3ArgConstructors(){
        // Correct all arguments
        boolean exceptionCatched = false;
        try {
            bookReader = new BookReader("Konrad", "Boniecki",5);
        } catch(IllegalArgumentException e) {
            exceptionCatched = true;
        }
        if (exceptionCatched) Assert.fail("IllegalArgumentException was thrown");
    
        // Incorrect all arguments
        exceptionCatched = false;
        try {
            bookReader = new BookReader(null, null,null);
        } catch(IllegalArgumentException e) {
            exceptionCatched = true;
        }
        if (!exceptionCatched) Assert.fail("IllegalArgumentException was not thrown");
        
        exceptionCatched = false;
        try {
            bookReader = new BookReader(null, null, 0);
        } catch(IllegalArgumentException e) {
            exceptionCatched = true;
        }
        if (!exceptionCatched) Assert.fail("IllegalArgumentException was not thrown");
    
        // Incorrect name
        exceptionCatched = false;
        try {
            bookReader = new BookReader(null, "Boniecki", 5);
        } catch(IllegalArgumentException e) {
            exceptionCatched = true;
        }
        if (!exceptionCatched) Assert.fail("IllegalArgumentException was not thrown");
    
        // Incorrect surname
        exceptionCatched = false;
        try {
            bookReader = new BookReader("Konrad", null,5);
        } catch(IllegalArgumentException e) {
            exceptionCatched = true;
        }
        if (!exceptionCatched) Assert.fail("IllegalArgumentException was not thrown");
        // Incorrect id
        exceptionCatched = false;
        try {
            bookReader = new BookReader("Konrad", "Boniecki",0);
        } catch(IllegalArgumentException e) {
            exceptionCatched = true;
        }
        if (!exceptionCatched) Assert.fail("IllegalArgumentException was not thrown");
        
        exceptionCatched = false;
        try {
            bookReader = new BookReader("Konrad", "Boniecki",null);
        } catch(IllegalArgumentException e) {
            exceptionCatched = true;
        }
        if (!exceptionCatched) Assert.fail("IllegalArgumentException was not thrown");
    
    }
}
