package scene;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TV extends Thread {

//new object from class Mesaje
    Mesaje mesaj1;    

//constructor with 1 parameter for class TV 
    public TV(Mesaje mesaj1) {
        this.mesaj1 = mesaj1;
    }

    @Override
    public void run() {
        System.out.println ("TV START");
        try {
            sleep(2000);
            
            synchronized (mesaj1) {
                mesaj1.setMesaj("TV: Eu cand pornesc, notific barbatul");
                mesaj1.notify();
                System.out.println(mesaj1.getMesaj());
            }
        } catch (InterruptedException e) {
            Logger.getLogger(TV.class.getName()).log(Level.SEVERE, null, e);
        }
        System.out.println("TV STOP");

    }

}
