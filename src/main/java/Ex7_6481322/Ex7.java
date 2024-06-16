// Don't forget to rename the package
package Ex7_6481322;

import java.util.*;
import java.util.concurrent.*;

////////////////////////////////////////////////////////////////////////////////
class BankThread extends Thread
{
    private Account             sharedAccount;    // threads from the same bank work on the same account
    private Exchanger<Account>  exchanger;         
    private CyclicBarrier       barrier;
    private int                 transaction = 1;

    public BankThread(String n, Account sa)             { super(n); sharedAccount = sa; }
    public void setBarrier(CyclicBarrier ba)            { barrier   = ba; }
    public void setExchanger(Exchanger<Account> ex)     { exchanger = ex; }
    
    public void run() {

        // (1) Only 1 thread (from any bank) print start deposit to signal deposit tasks
        //     X All threads must wait to do next step together
        int temp = this.waitAndLog();
        if (temp == 0) { System.out.printf("%s >> start deposit\n", Thread.currentThread().getName()); }

        // (2) Each thread does 3 transactions of deposit by calling Account's deposit
        int transactionNumber = 1;
        for(int i=0; i<3; i++) sharedAccount.deposit(transactionNumber++);

        // (3) Each bank representative whose Exchanger != null exchanges shardAccount
        //     X Other threads who don't exchange objects must wait until this is done

        // Wait until all transactions are complete before exchanging the account
        this.waitAndLog();

        if (exchanger != null) {
            try {
                sharedAccount = exchanger.exchange(sharedAccount);
                this.waitAndLog();
                System.out.printf("%s >> exchange account : %s\n", Thread.currentThread().getName(), sharedAccount.getName());
            }
            catch (InterruptedException e) { throw new RuntimeException(e); }
        }
        else this.waitAndLog();

        // (4) Only 1 thread (from any bank) print start withdraw to signal withdrawal tasks
        //     - All threads must wait to do the next step together
        int temp2 = this.waitAndLog();
        if (temp2 == 0) { System.out.printf("%s >> start withdraw\n", Thread.currentThread().getName()); }
        
        // (5) Each thread does 3 transactions of withdrawal by calling Account's withdraw
        for(int i=0; i<3; i++) sharedAccount.withdraw(transactionNumber++);

    }

    private int waitAndLog() {
        int barrierRemaning = Integer.MIN_VALUE;
        try {
            barrierRemaning = barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            System.err.println(e.getMessage());
        }
        return barrierRemaning;
    }
}

////////////////////////////////////////////////////////////////////////////////
class Account {
    private String  name;
    private int     balance;
    
    public Account(String id, int b)   { name = id; balance = b; }
    public String getName()            { return name; }
    public int    getBalance()         { return balance; }

    synchronized public void deposit(int transactionRound)
    {
        // Random money 1-100 to deposit; update balance
        Random random = new Random();
        int rand = random.nextInt(1, 101);
        this.balance += rand;
        // Report thread activity (see example output)
        System.out.printf("%s >> transaction %-3d %s %4s  balance = %4d\n",
            Thread.currentThread().getName(), transactionRound,
            this.name, this.toSignedNumber(rand), this.balance
        );
    }
    
    synchronized public void withdraw(int transactionRound)
    {
        // If balance = 0, report that this account is closed
        if (this.balance <= 0) {
            System.out.printf("%s >> transaction %-3d %s closed\n",
                    Thread.currentThread().getName(), transactionRound, this.name
            );
            return;
        }

        // If balance > 0, random money 1-100 but not exceeding balance to withdraw; update balance
        Random random = new Random();
        int rand = -1 * Integer.min(this.balance, random.nextInt(1, 101));
//        System.out.printf("%s >> transaction %-3d === %d\n", Thread.currentThread().getName(), transactionRound, rand);
        this.balance += rand;

        // Report thread activity (see example output)
        System.out.printf("%s >> transaction %-3d %s %4s  balance = %4d\n",
                Thread.currentThread().getName(), transactionRound,
                this.name, this.toSignedNumber(rand), this.balance
        );
    }

    private String toSignedNumber(int number) { return (number > 0 ? "+" : "") + number; }
}

////////////////////////////////////////////////////////////////////////////////
public class Ex7 {
    public static void main(String[] args) {
        Ex7 mainApp = new Ex7();
        mainApp.runSimulation();
    }

    public void runSimulation()
    {
        // (1) Suppose there are 2 banks (A and B). Each bank has 1 account
        Account [] accounts = { new Account("account A", 0), 
                                new Account(".".repeat(35) + "account B", 0)
        };

        Scanner keyboardScan = new Scanner(System.in);
        System.out.printf("%s  >>  Enter #threads per bank = \n", Thread.currentThread().getName());  
        int num = keyboardScan.nextInt();

        
        // (2) Synchronization objects that will be shared by all threads from both banks
        Exchanger<Account> exchanger = new Exchanger<>();
        CyclicBarrier barrier        = new CyclicBarrier(num*2);


        // (3) Add code to
        //     X Create threads for bank A (using account A) and bank B (using account B)
        //     X Pass synchronization objects; Exchanger may be passed to 1 thread per bank
        //     X Start all Bankthreads
        ArrayList<BankThread> allThreads = new ArrayList<>();
        for (int i=0; i<num; i++) {
            BankThread bankA = new BankThread("BankThreadA_" + i, accounts[0]);
            BankThread bankB = new BankThread("BankThreadB_" + i, accounts[1]);
            bankA.setBarrier(barrier); bankB.setBarrier(barrier);
            bankA.setExchanger( i == 0 ? exchanger : null ); bankB.setExchanger( i == 0 ? exchanger : null);
            allThreads.add(bankA); allThreads.add(bankB);
        }

        for (BankThread bt: allThreads) {
            bt.start();
        }


        for (BankThread bt: allThreads) {
            try {
                bt.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // (4) After all BankThreads complete their work, print final balance all accounts
        System.out.printf("%s  >>\n", Thread.currentThread().getName());
        for (Account account : accounts) {
            System.out.printf("%s  >> final balance  %s = %d\n", Thread.currentThread().getName(), account.getName(), account.getBalance());
        }


    }
}
