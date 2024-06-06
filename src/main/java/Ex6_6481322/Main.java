package Ex6_6481322;

import java.io.*;
import java.util.*;

class PrimeThread extends Thread {
    private PrintWriter out; // each thread writes to separate file
    private ArrayList<Integer> numbers;
    private int totalPrime, target;

    public PrimeThread(String n, int t) { super(n); target = t;
        try {
            out = new PrintWriter("src/main/java/Ex6_6481322/"+n+".txt");
            out.printf("%s, target = %d\n", n, t);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isPrime(int n) {
        for(int i=2; i*i<n+1; i++)
            if (n % i == 0) return false;

        return true;
    }

    private String toPrimeString(int n) {
        if (isPrime(n)) return "+" + n;
        return "" + n;
    }

    public void run() {
        // Execute steps 1-4 in loop
        // Stop the loop once totalPrime >= target
        int round = 1;
        while (totalPrime < target) {
            numbers = new ArrayList<Integer>();
            // 1. Random 5 integers (2-100) & put them in ArrayList
            for (int i=0; i<5; i++) {
                Random random = new Random();
                int r = random.nextInt(2, 101);
                numbers.add(r);
            }
            // 2. Sort the ArrayList in increasing order
            Collections.sort(numbers);
            // 3. Check each member. If it is a prime, add it to totalPrime.
            for(int num: numbers)
                if (isPrime(num))
                    totalPrime += num;
            // 4. Print round number, all sorted values (primes must be printed with + sign, non-

            System.out.printf("Round %3d >> %4s %4s %4s %4s %4s        total prime = %4d\n",
                    round,
                    toPrimeString(numbers.get(0)),
                    toPrimeString(numbers.get(1)),
                    toPrimeString(numbers.get(2)),
                    toPrimeString(numbers.get(3)),
                    toPrimeString(numbers.get(4)),
                    totalPrime
            );
            out.printf("Round %3d >> %4s %4s %4s %4s %4s        total prime = %4d\n",
                    round++,
                    toPrimeString(numbers.get(0)),
                    toPrimeString(numbers.get(1)),
                    toPrimeString(numbers.get(2)),
                    toPrimeString(numbers.get(3)),
                    toPrimeString(numbers.get(4)),
                    totalPrime
            );

            // primes must be printed without + sign), and current totalPrime to file
        }

        out.close();
        // Report number of rounds to the screen
    }
}


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter #threads = ");
        int t = scanner.nextInt();
        System.out.println("Enter target = ");
        int n = scanner.nextInt();

        for(int i=0; i<t; i++) {
            PrimeThread p = new PrimeThread("Thread_"+i, n);
            p.start();
        }
    }
}
