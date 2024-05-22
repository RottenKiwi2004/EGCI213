package Playground1_1;

public class Test1_1 extends SuperClass {
    public static void main(String[] args) {
//        TestVisibility.privateHello();    Other class, same package cannot access private information
        TestVisibility.defaultHello();
        TestVisibility.protectedHello();
        TestVisibility.publicHello();
//        privateHello();       Super class, subclass cannot access private information
        defaultHello();
        protectedHello();
        publicHello();
    }
}
