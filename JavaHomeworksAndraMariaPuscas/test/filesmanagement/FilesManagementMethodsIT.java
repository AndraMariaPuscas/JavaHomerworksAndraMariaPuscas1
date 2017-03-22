/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesmanagement;

import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author andra
 */
public class FilesManagementMethodsIT {
    
    public FilesManagementMethodsIT() {
    }

    /**
     * Test of readFile method, of class FilesManagementMethods.
     */
    @Test
    public void testReadFile() throws Exception {
        System.out.println("readFile");
       
        FilesManagementMethods instance = new FilesManagementMethods ("/home/andra/Desktop/file1.txt");
        String[] expResult = {"Line one", "Line two", "Line 3"};
        String[] result = instance.readFile("/home/andra/Desktop/file1.txt");
        assertArrayEquals(expResult, result);
        
    }

    /**
     * Test of readNoOfLines method, of class FilesManagementMethods.
     */
    @Test
    public void testReadNoOfLines()  {
        System.out.println("readNoOfLines");
        FilesManagementMethods instance = new FilesManagementMethods("/home/andra/Desktop/file1.txt");
        int expResult = 3;
        try {
        int result = instance.readNoOfLines();
        assertEquals(expResult, result);
        }
        catch (IOException e) {
            e.getMessage();
        }
        
    }
    
}
