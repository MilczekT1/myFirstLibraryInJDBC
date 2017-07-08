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
        // Wrong pages amount
        boolean exceptionCatched = false;
        try {
            book = new Book("LOTR", -5);
        } catch(IllegalArgumentException e){
            exceptionCatched = true;
        }
        if (!exceptionCatched) Assert.fail("IllegalArgumentException was not thrown");
        
        // Wrong title
        exceptionCatched = false;
        try {
            book = new Book(null, 500);
        } catch(IllegalArgumentException e){
            exceptionCatched = true;
        }
        if (!exceptionCatched) Assert.fail("IllegalArgumentException was not thrown");
        
        // Wrong both arguments
        exceptionCatched = false;
        try {
            book = new Book(null, -500);
        } catch(IllegalArgumentException e){
            exceptionCatched = true;
        }
        if (!exceptionCatched) Assert.fail("IllegalArgumentException was not thrown");
        
        // Good both arguments
        exceptionCatched = false;
        try {
            book = new Book("LOTR", 500);
        } catch(IllegalArgumentException e) {
            exceptionCatched = true;
        }
        if (exceptionCatched) Assert.fail("IllegalArgumentException was thrown");
        Assert.assertEquals("id should be 0",0,book.getId());
        Assert.assertEquals("Pages amount should be 500",500,book.getPages());
        Assert.assertEquals("Title should be LOTR","LOTR",book.getTitle());
    }
    
    @Test
    public void constructorsWith3ArgumentsTest(){
    
        // all arguments correct
        boolean exceptionCatched = false;
        try {
            book = new Book("LOTR", 500,5);
        } catch(Exception e){
            if (e instanceof IllegalArgumentException){
                exceptionCatched = true;
            }
        }
        if (exceptionCatched) Assert.fail("IllegalArgumentException was not thrown");
    
    
        // incorrect pages amount
        exceptionCatched = false;
        try {
            book = new Book("LOTR", 0,5);
        } catch(Exception e){
            if (e instanceof IllegalArgumentException){
                exceptionCatched = true;
            }
        }
        if (!exceptionCatched) Assert.fail("IllegalArgumentException was not thrown");
        // incorrect id
        exceptionCatched = false;
        try {
            book = new Book("LOTR", 5,-5);
        } catch(Exception e){
            if (e instanceof IllegalArgumentException){
                exceptionCatched = true;
            }
        }
        if (!exceptionCatched) Assert.fail("IllegalArgumentException was not thrown");
    
        // incorrect title
        exceptionCatched = false;
        try {
            book = new Book(null, 5,5);
        } catch(Exception e){
            if (e instanceof IllegalArgumentException){
                exceptionCatched = true;
            }
        }
        if (!exceptionCatched) Assert.fail("IllegalArgumentException was not thrown");
        // all incorrect arguments
        exceptionCatched = false;
        try {
            book = new Book(null, -5,-5);
        } catch(Exception e){
            if (e instanceof IllegalArgumentException){
                exceptionCatched = true;
            }
        }
        if (!exceptionCatched) Assert.fail("IllegalArgumentException was not thrown");
    }
}
