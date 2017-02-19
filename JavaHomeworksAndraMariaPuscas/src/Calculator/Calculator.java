
package Calculator;

import javax.swing.JOptionPane;
public class Calculator {

    public static void main(String[] args) {
        String input=JOptionPane.showInputDialog("First no:");
        String input2=JOptionPane.showInputDialog("Second no:");
        Double a, b, sum, prod, div, minus;
        Character x, y;
        for (int i=0; i<input.length(); i++) {
            x= input.charAt(i);
            for (int j=0; j<input2.length(); j++){
                y=input2.charAt(j);
                if (Character.isDigit(x)&&Character.isDigit(y)){
                    a=Double.parseDouble(input);
                    b=Double.parseDouble(input2);
                    String operator=JOptionPane.showInputDialog("Press '+' or '-' or *' or '/'");
                    switch (operator) {
                        case "+": sum = a+b;
                        JOptionPane.showMessageDialog(null, sum);
                        break;
                        case "*": prod = a*b;
                        JOptionPane.showMessageDialog(null, prod);
                        break;
                        case "/": div = a/b;
                        JOptionPane.showMessageDialog(null, div);
                        break;
                        case "-": minus = a-b;
                        JOptionPane.showMessageDialog(null, minus);
                        break;}}
                else {
                    JOptionPane.showMessageDialog(null, "Sunt calculator, trebuie sa introduci numere");
                }
        
    }}
        
       
        
      
    }
    
}
