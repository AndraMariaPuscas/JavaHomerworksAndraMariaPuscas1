/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package throwssyntax;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author andra
 */
public class CircleAreaIT {

    public CircleAreaIT() {
    }

    /**
     * Test of main method, of class CircleArea.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        CircleArea.main(args);
    }

    /**
     * Test of circleArea method, of class CircleArea.
     */
    @Test
    public void testCircleArea() throws Exception {
        System.out.println("circleArea");
        double radius = 0.0;
        double expResult = 0.0;
        double result = CircleArea.circleArea(radius);
        assertEquals(expResult, result, 0.0);
    }

}
