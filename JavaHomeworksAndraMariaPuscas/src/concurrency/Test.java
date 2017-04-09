package concurrency;

import java.util.concurrent.Executor;

import java.util.concurrent.Executors;

public class Test {

    public static void main(String[] args) {
        Contor c = new Contor();
        for (int i = 0; i < 500; i++) {
            Runnable a = new Adunare(c);
            Runnable s = new Scadere(c);

            Executor executor = Executors.newSingleThreadExecutor();

            executor.execute(a);
            executor.execute(s);
            System.out.println("Counter=" + c.getContor());
        }

    }

}
