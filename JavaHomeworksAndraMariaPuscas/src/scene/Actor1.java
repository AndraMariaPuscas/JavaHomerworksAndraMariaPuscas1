
package scene;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andra
 */
public class Actor1 extends Thread {

//new object from class Mesaje
    Mesaje mesaj2; 

//constructor with 1 parameter for class Actor1 
    public Actor1(Mesaje mesaj2) {
        this.mesaj2 = mesaj2;
    }

    @Override
    public synchronized void run() {
            System.out.println("Barbat start");
        try {
            synchronized (mesaj2) {

                mesaj2.wait(); //This thread waits for the notification from thread TV
                System.out.println("Barbat: Eu sunt notificat de TV si trezesc femeia");
                mesaj2.notify();//this thread notifies the next thread
                
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Actor1.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Barbat STOP");
    }
}
