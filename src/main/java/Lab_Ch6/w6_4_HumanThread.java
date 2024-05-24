package Lab_Ch6;

class Human
{
    private String name;
    public Human(String n)                  { name = n; }
    public String getName()                 { return name; }
};

class MySoul extends Human implements Runnable
{
    public MySoul(String n)                 { super(n); }
    public String activity()                { return ("runnable activity"); }

    @Override
    public void run()
    {
	// ----- (1) call thread's methods in runnable object
	Thread me = Thread.currentThread();
	try  { me.sleep(200); }  catch (InterruptedException e)  { }
	System.out.printf("Thread = %-8s   runnable = %-4s   -->   run in %s \n\n", 
                          me.getName(), getName(), getClass().getName());
    }
};

//////////////////////////////////////////////////////////////////////////////////////////////////
class MyBody extends Thread  
{
    private MySoul soul;	
    public MyBody(MySoul s, String n)       { super(s, n); soul = s; }
    
    @Override
    public void run()  
    {
	// ----- (2) call runnable's methods in thead object
	System.out.printf("Thread = %-8s   runnable = %-4s   -->   run in %s ", 
                          getName(), soul.getName(), getClass().getName());
	System.out.printf("   -->   call %s \n\n", soul.activity());
    }
}

class HerBody extends Thread
{
    private MySoul soul;
    public HerBody(MySoul s, String n)      { super(s, n); soul = s; }
}

//////////////////////////////////////////////////////////////////////////////////////////////////
class w6_4_HumanThread
{
    public static void main(String[] args) 
    {
	Thread tbody  = new Thread( new MySoul("TSoul"), "Thread" ); 
	MyBody mbody  = new MyBody( new MySoul("MSoul"), "MyBody" );
	HerBody hbody = new HerBody( new MySoul("HSoul"), "HerBody" );
        
        tbody.start();  mbody.start();  hbody.start();
    }
}
