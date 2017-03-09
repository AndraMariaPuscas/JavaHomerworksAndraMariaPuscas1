/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package throwssyntax;

/**
 *
 * @author andra
 */
public class CircleArea {

    public static void main(String[] args)  {

        try {
            System.out.println(circleArea(-10.5));
        } catch (Exception e) {
            System.out.println("Cannot calculate area: " + e.getMessage());
        }
    }

    static double circleArea(double radius) throws Exception {
        if (radius < 0) {
            throw new Exception("radius should be positive");
        }
        return Math.PI * radius * radius;
    }
}
