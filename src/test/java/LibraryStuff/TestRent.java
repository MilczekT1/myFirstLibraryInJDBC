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
        boolean exceptionCatched = false;
        try {
            rent = new Rent(1,1);
        } catch(IllegalArgumentException e) {
            exceptionCatched = true;
        }
        if (exceptionCatched) Assert.fail("IllegalArgumentException was thrown");
    
        // Incorrect bookID
        exceptionCatched = false;
        try {
            rent = new Rent(-5,1);
        } catch(IllegalArgumentException e) {
            exceptionCatched = true;
        }
        if (!exceptionCatched) Assert.fail("IllegalArgumentException was not thrown");
        
        exceptionCatched = false;
        try {
            rent = new Rent(null,1);
        } catch(IllegalArgumentException e) {
            exceptionCatched = true;
        }
        if (!exceptionCatched) Assert.fail("IllegalArgumentException was not thrown");
    
        // Incorrect readerID
        exceptionCatched = false;
        try {
            rent = new Rent(1,-5);
        } catch(IllegalArgumentException e) {
            exceptionCatched = true;
        }
        if (!exceptionCatched) Assert.fail("IllegalArgumentException was not thrown");
    
        exceptionCatched = false;
        try {
            rent = new Rent(1,null);
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
            rent = new Rent(1,1,5);
        } catch(IllegalArgumentException e) {
            exceptionCatched = true;
        }
        if (exceptionCatched) Assert.fail("IllegalArgumentException was thrown");
    
        // Incorrect bookID
        exceptionCatched = false;
        try {
            rent = new Rent(-5,1,5);
        } catch(IllegalArgumentException e) {
            exceptionCatched = true;
        }
        if (!exceptionCatched) Assert.fail("IllegalArgumentException was not thrown");
    
        exceptionCatched = false;
        try {
            rent = new Rent(null,1,5);
        } catch(IllegalArgumentException e) {
            exceptionCatched = true;
        }
        if (!exceptionCatched) Assert.fail("IllegalArgumentException was not thrown");
    
        // Incorrect readerID
        exceptionCatched = false;
        try {
            rent = new Rent(1,-1,5);
        } catch(IllegalArgumentException e) {
            exceptionCatched = true;
        }
        if (!exceptionCatched) Assert.fail("IllegalArgumentException was not thrown");
    
        exceptionCatched = false;
        try {
            rent = new Rent(1,null,5);
        } catch(IllegalArgumentException e) {
            exceptionCatched = true;
        }
        if (!exceptionCatched) Assert.fail("IllegalArgumentException was not thrown");
        // Incorrect rentID
        exceptionCatched = false;
        try {
            rent = new Rent(1,1,-5);
        } catch(IllegalArgumentException e) {
            exceptionCatched = true;
        }
        if (!exceptionCatched) Assert.fail("IllegalArgumentException was not thrown");
    
        exceptionCatched = false;
        try {
            rent = new Rent(1,1,null);
        } catch(IllegalArgumentException e) {
            exceptionCatched = true;
        }
        if (!exceptionCatched) Assert.fail("IllegalArgumentException was not thrown");
    }
}
