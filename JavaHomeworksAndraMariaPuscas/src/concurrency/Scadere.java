package concurrency;

/**
 *
 * @author andra
 */
public class Scadere extends Thread {

    Contor contor;

    public Scadere(Contor contorFromTest) {
        contor = contorFromTest;
    }

    @Override
    public void run() {

        int temp = contor.getContor();
        temp--;
        contor.setContor(temp);
    }

}
