package scene;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Actor2 extends Thread {

//new object from class Mesaje
    Mesaje mesaj3;

//constructor with 1 parameter for class Actor2 
    public Actor2(Mesaje mesaj3) {
        this.mesaj3 = mesaj3;
    }

    @Override
    public synchronized void run() {
        System.out.println("START FEMEIE");
        try {
            synchronized (mesaj3) {
                System.out.println("Femeie urmeaza wait");
                mesaj3.wait();//waits for the notification sent by thread barbat
                System.out.println("Femeia: Eu sunt trezita de barbat");

            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Actor1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}