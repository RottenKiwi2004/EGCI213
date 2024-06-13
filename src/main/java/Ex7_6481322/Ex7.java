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
        int temp = Integer.MIN_VALUE;
        try { temp = barrier.await(); } catch (Exception e) { e.printStackTrace(); }

        if (temp == 0) { System.out.printf("%s >> start deposit\n", Thread.currentThread().getName()); }

        System.out.printf("(%d)\n", temp);

        // (2) Each thread does 3 transactions of deposit by calling Account's deposit
        int transactionNumber = 1;
        for(int i=0; i<3; i++) sharedAccount.deposit(transactionNumber++);

        // (3) Each bank representative whose Exchanger != null exchanges shardAccount
        //     - Other threads who don't exchange objects must wait until this is done

        if (exchanger != null)
            try { sharedAccount = exchanger.exchange(sharedAccount); barrier.await();}
            catch (InterruptedException | BrokenBarrierException e) { throw new RuntimeException(e); }
        else try { barrier.await(); } catch (Exception ignored) { }

//        try { System.out.printf("%d\n", barrier.await()); } catch (Exception e) { }

        System.out.printf("%s >> %s\n", Thread.currentThread().getName(), sharedAccount.getName());

        // (4) Only 1 thread (from any bank) print start withdraw to signal withdrawal tasks
        //     - All threads must wait to do the next step together

        
        // (5) Each thread does 3 transactions of withdrawal by calling Account's withdraw
    }
}

////////////////////////////////////////////////////////////////////////////////
class Account {
    private String  name;
    private int     balance;
    
    public Account(String id, int b)   { name = id; balance = b; }
    public String getName()            { return name; }
    public int    getBalance()         { return balance; }

    public void deposit(int transactionRound)
    {
        // Random money 1-100 to deposit; update balance
        Random random = new Random();
        int rand = random.nextInt(1, 100);
        this.balance += rand;
        // Report thread activity (see example output)
        System.out.printf("%s >> transaction %d %s %4s  balance = %4d\n",
            Thread.currentThread().getName(), transactionRound,
            this.name, this.toSignedNumber(rand), this.balance
        );
    }
    
    public void withdraw()
    {
        // If balance > 0, random money 1-100 but not exceeding balance to withdraw; update balance
        // If balance = 0, report that this account is closed
        // Report thread activity (see example output)
    }

    private String toSignedNumber(int number) { return "+" + number; }
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
            bankA.setExchanger(exchanger); bankB.setExchanger(exchanger);
            allThreads.add(bankA); allThreads.add(bankB);
        }

        for (BankThread bt: allThreads) {
            bt.start();
        }
        
        // (4) After all BankThreads complete their work, print final balance all accounts
    }
}
