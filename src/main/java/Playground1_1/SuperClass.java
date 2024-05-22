package Playground1_1;

public class SuperClass {
    private static void privateHello() {
        System.out.println("Other Class, Same Package: Private Hello");
    }

    static void defaultHello() {
        System.out.println("Other Class, Same Package: Default Hello");
    }

    protected static void protectedHello() {
        System.out.println("Other Class, Same Package: Protected Hello");
    }

    public static void publicHello() {
        System.out.println("Other Class, Same Package: Public Hello");
    }
}
