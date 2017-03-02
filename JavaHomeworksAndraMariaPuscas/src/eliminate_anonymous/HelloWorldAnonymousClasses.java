
package eliminate_anonymous;

/**
 *
 * @author andra
 */
public class HelloWorldAnonymousClasses {

    /// Inner interface HelloWorld 
interface HelloWorld {

public void greet();

public void greetSomeone(String someone); }

// Method sayHello prints 
public void sayHello() {

// Local class EnglishGreetings implemented in the method sayHello() 
class EnglishGreeting implements HelloWorld {

String name = "world"; @Override
public void greet() {

greetSomeone("world"); }

@Override
public void greetSomeone(String someone) {

name = someone;

System.out.println("Hello " + name); }

} // end of EnglishGreeting local class
// create an EnglishGreeting object/instance
HelloWorld englishGreeting = new EnglishGreeting();

englishGreeting.greet(); 

OtherLanguage french = new OtherLanguage();
OtherLanguage spanish = new OtherLanguage();
french.sayItInFrench();
spanish.sayItInSpanish();
}
// main method
public static void main(String... args) {

HelloWorldAnonymousClasses myApp = new HelloWorldAnonymousClasses();

myApp.sayHello(); }

}

