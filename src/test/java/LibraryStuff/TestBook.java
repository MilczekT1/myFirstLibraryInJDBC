package LibraryStuff;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestBook {
    private static Book book;
    
    @Before
    public void beforeTest(){
        try {
            book = new Book("LOTR",500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @AfterClass
    public static void shutDown(){
        book = null;
    }
    
    @Test
    public void emptyConstructorTest(){
        book = new Book();
        Assert.assertEquals("Id should be 0",0, book.getId());
        Assert.assertEquals("Pages amount should be 0",0, book.getPages());
        Assert.assertNull("Title should be null", book.getTitle());
    }
    
    @Test
    public void constructorsWith2ArgumentsTest(){
        // Incorrect pages amount
        try {
            book = new Book("LOTR", -5);
            Assert.fail("IllegalArgumentException has not been thrown");
        } catch(IllegalArgumentException e){ ; }
        
        // Incorrect title
        try {
            book = new Book(null, 500);
            Assert.fail("IllegalArgumentException has not been thrown");
        } catch(IllegalArgumentException e){ ; }
        
        // Incorrect both arguments
        try {
            book = new Book(null, -500);
            Assert.fail("IllegalArgumentException has not been thrown");
        } catch(IllegalArgumentException e){ ; }
        
        // Correct both arguments
        try {
            book = new Book("LOTR", 500);
        } catch(IllegalArgumentException e) {
            Assert.fail("IllegalArgumentException has been thrown");
        }
        Assert.assertEquals("id should be 0",0,book.getId());
        Assert.assertEquals("Pages amount should be 500",500,book.getPages());
        Assert.assertEquals("Title should be LOTR","LOTR",book.getTitle());
    }
    
    @Test
    public void constructorsWith3ArgumentsTest(){
    
        // all arguments correct
        try {
            book = new Book("LOTR", 500,5);
        } catch(IllegalArgumentException e){
            Assert.fail("IllegalArgumentException has been thrown");
        }
        
        // incorrect pages amount
        try {
            book = new Book("LOTR", 0,5);
            Assert.fail("IllegalArgumentException has not been thrown");
        } catch(IllegalArgumentException e){ ; }
        
        // incorrect id
        try {
            book = new Book("LOTR", 5,-5);
            Assert.fail("IllegalArgumentException has not been thrown");
        } catch(IllegalArgumentException e){ ; }
    
        // incorrect title
        try {
            book = new Book(null, 5,5);
            Assert.fail("IllegalArgumentException has not been thrown");
        } catch(IllegalArgumentException e){ ; }
        
        // all incorrect arguments
        try {
            book = new Book(null, -5,-5);
            Assert.fail("IllegalArgumentException has not been thrown");
        } catch(IllegalArgumentException e){ ; }
    }
}
