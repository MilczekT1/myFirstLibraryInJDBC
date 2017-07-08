package LibraryStuff;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestRent {
    private static Rent rent;
    
    @Before
    public void beforeTest(){
        rent = new Rent(1,1);
    }
    @AfterClass
    public static void shutDown(){
        rent = null;
    }
    
    
    @Test
    public void test2ArgConstructors(){
        // Correct all arguments
        try {
            rent = new Rent(1,1);
        } catch(IllegalArgumentException e) {
            Assert.fail("IllegalArgumentException has been thrown");
        }
    
        // Incorrect bookID
        try {
            rent = new Rent(-5,1);
            Assert.fail("IllegalArgumentException has not been thrown");
        } catch(IllegalArgumentException e) { ; }
        
        try {
            rent = new Rent(null,1);
            Assert.fail("IllegalArgumentException has not been thrown");
        } catch(IllegalArgumentException e) { ; }
    
        // Incorrect readerID
        try {
            rent = new Rent(1,-5);
            Assert.fail("IllegalArgumentException has not been thrown");
        } catch(IllegalArgumentException e) { ; }
        
        try {
            rent = new Rent(1,null);
            Assert.fail("IllegalArgumentException has not been thrown");
        } catch(IllegalArgumentException e) { ; }
    }
    
    @Test
    public void test3ArgConstructors(){
        // Correct all arguments
        try {
            rent = new Rent(1,1,5);
        } catch(IllegalArgumentException e) {
            Assert.fail("IllegalArgumentException has been thrown");
        }
    
        // Incorrect bookID
        try {
            rent = new Rent(-5,1,5);
            Assert.fail("IllegalArgumentException has not been thrown");
        } catch(IllegalArgumentException e) { ; }
        
        try {
            rent = new Rent(null,1,5);
            Assert.fail("IllegalArgumentException has not been thrown");
        } catch(IllegalArgumentException e) { ; }
    
        // Incorrect readerID
        try {
            rent = new Rent(1,-1,5);
            Assert.fail("IllegalArgumentException has not been thrown");
        } catch(IllegalArgumentException e) { ; }
        
        try {
            rent = new Rent(1,null,5);
            Assert.fail("IllegalArgumentException has not been thrown");
        } catch(IllegalArgumentException e) { ; }
        
        // Incorrect rentID
        try {
            rent = new Rent(1,1,-5);
            Assert.fail("IllegalArgumentException has not been thrown");
        } catch(IllegalArgumentException e) { ; }
        
        try {
            rent = new Rent(1,1,null);
            Assert.fail("IllegalArgumentException has not been thrown");
        } catch(IllegalArgumentException e) { ; }
    }
}
