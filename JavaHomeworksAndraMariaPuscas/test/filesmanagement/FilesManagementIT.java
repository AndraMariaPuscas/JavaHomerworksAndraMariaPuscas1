package filesmanagement;

import org.junit.Test;
import static org.junit.Assert.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author andra
 */
public class FilesManagementIT {

    public FilesManagementIT() {
    }

    /**
     * Test of writeInFile method, of class FilesManagement.
     */
    @Test
    public void testWriteInFile() {
        System.out.println("writeInFile");
        String file = "";

        FilesManagement instance = new FilesManagement(file);

        String expResult = "ccc";
        try {
            FileReader fr = new FileReader("/home/andra/Desktop/file2.txt");
            BufferedReader br = new BufferedReader(fr);

            String result = br.readLine();
            assertEquals(expResult, result);
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
    }

    /**
     * Test of deleteFromFile method, of class FilesManagement.
     */
    @Test
    public void testDeleteFromFile() {
        System.out.println("deleteFromFile");
        String file = "";
        FilesManagement instance = new FilesManagement(file);
        instance.deleteFromFile("/home/andra/Desktop/file3.txt");
    }

    /**
     * Test of main method, of class FilesManagement.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        FilesManagement.main(args);
        
    }

}
