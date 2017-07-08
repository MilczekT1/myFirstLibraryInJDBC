package LibraryStuff;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

public class TestDbLibraryManager {
    private static DbLibraryManager dbLibraryManager;
    
    @AfterClass
    public static void shutDown(){
        dbLibraryManager = null;
    }
    
    @Test
    public void testMethod_GetInstance(){
        Assert.assertNotNull("getInstance() shouldn't return null",DbLibraryManager.getInstance());
    }
    
}
