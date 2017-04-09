
package concurrency;

/**
 *
 * @author andra
 */
public class Adunare extends Thread {
    Contor contor;
    public Adunare (Contor contorFromTest){
         contor=contorFromTest;
    }
    @Override
    public void run(){
        
             int temp = contor.getContor();
             temp++;
             contor.setContor(temp);
         
    }
}
