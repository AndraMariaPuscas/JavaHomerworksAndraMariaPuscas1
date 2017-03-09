/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validationexpression;

/**
 *
 * @author andra
 */
public class Validation {

    class ValidationException extends Exception {

        public ValidationException(String message) {
            super(message);
        }
    }

     int parseAge(String age) throws ValidationException {
        int value;
        try {
            value = Integer.parseInt(age);
        } catch (NumberFormatException e) {
            throw new ValidationException("should be an int");
        }
        if (value < 0 || value > 150) {
            throw new ValidationException("should be between 0 and 150 inclusive");
        }
        return value;
    }

    public static void main(String[] args) {
        Validation varsta = new Validation();
        try {
            varsta.parseAge("a");
        } catch (ValidationException e) {
            System.out.println("Invalid age argument: " + e.getMessage());
        }
    }
}
