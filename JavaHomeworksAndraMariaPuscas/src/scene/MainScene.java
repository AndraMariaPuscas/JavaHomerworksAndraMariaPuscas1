package scene;

public class MainScene {

    public synchronized static void main(String[] args) {
//creating new objects for each class
        Mesaje msg = new Mesaje("");
        Actor1 barbat = new Actor1(msg);
        Actor2 femeia = new Actor2(msg);
        TV tv = new TV(msg);

//starting the threads        
        barbat.start();
        tv.start();
        femeia.start();
    }
}
