package Lab_Ch6;

class MySleepingThread extends Thread
{
    private int count;

    MySleepingThread(String name)       { super(name); start(); }

    // ----- (1) method to handle interrupt
    public void interrupt_Handle(boolean awake)
    {
	count++;
	if (awake)
            System.out.printf("\n\n Interrupt %d, waking \n", count);
	else
            System.out.printf("\n\n Interrupt %d, sleeping \n", count);
    }

    @Override
    public void run()
    {
	int i = 1;
	while (i <= 1000)
	{
            System.out.printf("%4d ", i);

            // ----- (2) go to sleep, handle interrupt while sleeping
            if (i%10 == 0) 
                try { sleep(20); } catch (InterruptedException e) { interrupt_Handle(false); }


            // ----- (4) handle interrupt while running
            //           notice the difference between the following methods
            if ( interrupted() )	interrupt_Handle(true);
            //if ( isInterrupted() )	interrupt_Handle(true);

            i++;
	}
        System.out.println();
    }
};

//////////////////////////////////////////////////////////////////////////////////////////////////
class w6_2_Interrupt
{
    public static void main(String[] args) 
    {
	MySleepingThread T1 = new MySleepingThread("T1");

	// ----- (3) interrupt the running thread 3 times
	
	try 
	{ 
            Thread.sleep(20);	T1.interrupt();
            Thread.sleep(20);	T1.interrupt();
            Thread.sleep(20);	T1.interrupt();     
	}	
	catch (InterruptedException e) { }		
        
    }
}
