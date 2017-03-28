package multiproces;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andra
 */
public class LaunchingTextEditor {

    public static void main(String[] args) {

        try {
            ProcessBuilder pb;
            pb = new ProcessBuilder("kate");
            int numberOfOpenings = 3;
            for (int i = 0; i < numberOfOpenings; i++) {
                Process p = pb.start();
            }
        } catch (IOException e) {
            Logger.getLogger(LaunchingTextEditor.class.getName()).log(Level.SEVERE, "An IO exception is here!", e);
        }
    }

}
