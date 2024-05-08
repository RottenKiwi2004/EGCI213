package Ex1_6481322;

//import java.util.*;

public class Main {

    public static void main(String[] args) {

        int n;
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        do {
            System.out.println("Enter integer x (2-1000) = ");
            n = scanner.nextInt();
        } while(n < 2 || n > 1000);

        boolean prime = isPrime(n);

        System.out.println(n + (prime ? " is prime" : " is not prime"));
        if (prime) return;

        for (int i = n; i >= 2; i--)
            if (isPrime(i)) {
                System.out.println("The immediate smaller value that is prime = " + i);
                return;
            }

    }

    public static boolean isPrime(int n) {
        for (int i = 2; i < n / 2; i++)
            if (n % i == 0) return false;
        return true;
    }
}
