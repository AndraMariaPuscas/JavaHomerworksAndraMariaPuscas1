
package SomethingisWrong;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author andra
 */
public class RectangleIT {
    
    public RectangleIT() {
    }

    /**
     * Test of getHeight method, of class Rectangle.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        Rectangle instance = new Rectangle();
        int expResult = 0;
        int result = instance.getHeight();
        assertEquals(expResult, result);
        }

    /**
     * Test of setHeight method, of class Rectangle.
     */
    @Test
    public void testSetHeight() {
        System.out.println("setHeight");
        int height = 0;
        Rectangle instance = new Rectangle();
        instance.setHeight(height);
        }

    /**
     * Test of getWidth method, of class Rectangle.
     */
    @Test
    public void testGetWidth() {
        System.out.println("getWidth");
        Rectangle instance = new Rectangle();
        int expResult = 0;
        int result = instance.getWidth();
        assertEquals(expResult, result);
        }

    /**
     * Test of setWidth method, of class Rectangle.
     */
    @Test
    public void testSetWidth() {
        System.out.println("setWidth");
        int width = 0;
        Rectangle instance = new Rectangle();
        instance.setWidth(width);
        }

    /**
     * Test of area method, of class Rectangle.
     */
    @Test
    public void testArea() {
        System.out.println("area");
        Rectangle instance = new Rectangle();
        int expResult = 0;
        int result = instance.area();
        assertEquals(expResult, result);
    }
    
}
