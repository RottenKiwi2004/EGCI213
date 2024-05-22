package Playground1_2;

import Playground1_1.*;

public class Test1_2 extends SuperClass {

    public static void main(String[] args) {
//        privateHello(); Super class, different package, subclass cannot access private information
//        defaultHello(); Super class, different package, subclass cannot access default information
        protectedHello();
        publicHello();
//        TestVisibility.privateHello();    Other class, different package, cannot access private information
//        TestVisibility.defaultHello();    Other class, different package, cannot access default information
//        TestVisibility.protectedHello();  Other class, different package, cannot access protected information
        TestVisibility.publicHello();
    }



}