package Lab_Ch7;

import java.util.concurrent.*;
import java.util.*;

class ExchangeThread extends Thread
{
    private Value            myValue, yourValue;
    private Exchanger<Value> exchanger;
    private CyclicBarrier    barrier;
    
    public ExchangeThread(String n, Value v)    { super(n); myValue = v; }
    
    public void setSynch(Exchanger<Value> ex, CyclicBarrier cb)
    {
        exchanger = ex; 
        barrier   = cb;        
    }
    
    public void run()
    {
        System.out.printf("Thread %s >> my value = %d \n", 
                           Thread.currentThread().getName(), myValue.getValue());
        
        int x = -1;
        // ----- (1) try with & without barrier
        //try { x = barrier.await(); }  catch(Exception e) { }
        //if (x == 0)
        {
            System.out.printf("Thread %s >> ----------------------- \n", Thread.currentThread().getName());
            System.out.printf("Thread %s >> prepare to exchange     \n", Thread.currentThread().getName());
            System.out.printf("Thread %s >> ----------------------- \n", Thread.currentThread().getName());
        }
        
        // ----- (2) wait at the exchanger (implicit barrier), then exchange
        try { yourValue = exchanger.exchange(myValue); }           
        catch(InterruptedException e) { }
        
        System.out.printf("Thread %s >> your value = %d, result = %d \n", 
                           Thread.currentThread().getName(), yourValue.getValue(), 
                           myValue.getValue()/2 + yourValue.getValue());
    }
}

///////////////////////////////////////////////////////////////////////////////////////
class Value
{
    private int value;
    public Value(int v)         { value = v; }
    public int getValue()       { return value; }
}

///////////////////////////////////////////////////////////////////////////////////////
public class w7_5_Exchanger 
{
    public static void main(String[] args) 
    {
        w7_5_Exchanger mainApp = new w7_5_Exchanger();
        mainApp.testExchange();
    }
    
    public void testExchange() 
    {
        Exchanger<Value> exchanger = new Exchanger<Value>(); 
                
        int parties = 0;
        ArrayList<ExchangeThread> allThreads = new ArrayList<>();
        //----- (3) what if >2 threads holding Exchanger object (try odd and even #threads)
        allThreads.add( new ExchangeThread("A", new Value(10))  ); parties++;
        allThreads.add( new ExchangeThread("B", new Value(200)) ); parties++;
        //allThreads.add( new ExchangeThread("C", new Value(2))   ); parties++;
        //allThreads.add( new ExchangeThread("D", new Value(4))   ); parties++;
        
        CyclicBarrier barrier = new CyclicBarrier(parties); 
        
        for(ExchangeThread ext : allThreads) ext.setSynch(exchanger, barrier);
        for(ExchangeThread ext : allThreads) ext.start();
    }
}
