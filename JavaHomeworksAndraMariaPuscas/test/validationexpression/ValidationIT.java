/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validationexpression;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author andra
 */
public class ValidationIT {
    
    public ValidationIT() {
    }

    /**
     * Test of parseAge method, of class Validation.
     */
    @Test
    public void testParseAge() throws Exception {
        System.out.println("parseAge");
        String age = "5";
        Validation instance = new Validation();
        int expResult = 5;
        int result = instance.parseAge(age);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class Validation.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Validation.main(args);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
