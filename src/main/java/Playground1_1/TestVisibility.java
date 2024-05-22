package Playground1_1;

public class TestVisibility {
    private static void privateHello() {
        System.out.println("Private Hello");
    }

    static void defaultHello() {
        System.out.println("Default Hello");
    }

    protected static void protectedHello() {
        System.out.println("Protected Hello");
    }

    public static void publicHello() {
        System.out.println("Public Hello");
    }
}
