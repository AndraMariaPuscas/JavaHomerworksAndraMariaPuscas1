package cnp;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author andra
 */
public class CnpValidation {

//validate a CNP as being a number of 13 digits and first digit could be 1 or 2 and next 6 digits are for MMDDYYYY
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter CNP: ");

//read the introduced number
        String number = scan.next();

        if (number.length() != 13) {

            System.out.println("The CNP must have 13 digits");
        } else {
// create a pattern for CNP
            String expresie = "^(1|2)(1[0-2]|0[1-9])(3[01]|[12][0-9]|0[1-9])[0-9]{8}$";
            
            Pattern pattern = Pattern.compile(expresie);

// create the matcher as result of applying regex on source string
            Matcher matcher = pattern.matcher(number);
            
//verifying if the CNP introduced matches the pattern defined
            if (matcher.matches()) {

                System.out.println("the CNP is correct");

            } else {
                System.out.println("not correct");
            }
        }
    }

}

