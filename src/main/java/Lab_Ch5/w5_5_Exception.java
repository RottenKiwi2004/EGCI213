package Lab_Ch5;

// ----- (1) user-defined exceptions
class WingException extends Exception
{
    public WingException(String message)	{ super(message); }
};

class FoodException extends Exception
{			
    public FoodException(String message)	{ super(message); }
};

/////////////////////////////////////////////////////////////////////////////////////////////
abstract class AnimalAbstract4 
{
    private int legs;
    private boolean flyable;

    public AnimalAbstract4(int g, boolean f)        { legs = g; flyable = f; }
    public int	    getLegs()                       { return legs; }
    public boolean  canFly()                        { return flyable; }

    // ----- (2) method throws exception
    public void eat(AnimalAbstract4 food) throws FoodException	
    { 
	if (getClass() == food.getClass())
            throw new FoodException("Eating the same species is not allowed");
	else
            System.out.printf("%s eats %s \n", getClass().getName(), food.getClass().getName());
    }
    public void walk(String from, String to)        { }
    public void swim(String from, String to)        { }

    // ----- (3.1) method does not throw exception, but child's might
    //             try throwing WingException and just Exception
    public void fly(String from, String to) throws WingException   { }
};

/////////////////////////////////////////////////////////////////////////////////////////////
// ----- (1) create new classes from abstract class

class Dog4 extends AnimalAbstract4
{
    private String name;
    public Dog4(String n)                           { super(4, false); name = n; }
    public String getName()                         { return name; }

    // ----- (3.2) method throws exception
    //             try throwing WingException and just Exception
    @Override
    public void fly(String from, String to) throws WingException
    { 
	throw new WingException("Flying is not allowed for dog");
    }
};

class Bird4 extends AnimalAbstract4
{
    private String name;
    public Bird4(String n, boolean f)               { super(2, f); name = n; }
    public String getName()                         { return name; }

    @Override
    public void fly(String from, String to) 
    { 
	if (canFly()) System.out.printf("%s flies from %s to %s \n", getName(), from, to);
	else          System.out.printf("%s cannot fly \n", getName());
    }
};

/////////////////////////////////////////////////////////////////////////////////////////////
class w5_5_Exception
{
    public static void main(String[] args)    //throws WingException, FoodException
    {
	Dog4 lassie  = new Dog4("Lassie");
	Dog4 scooby  = new Dog4("Scooby");
	Bird4 parrot = new Bird4("Parrot", true);
		
	// ----- (4) test exception
	//           - try throwing exception to JVM
	//           - how to make both fly() and eat(...) be called ?
	System.out.println("\n\n----- Test exception -----\n");
	//lassie.fly("Manchester", "London");
	//lassie.eat(scooby);
		
        
	try
	{
            lassie.fly("Manchester", "London");
            //lassie.eat(scooby);
        }
	catch (WingException e) { e.printStackTrace(); System.out.println(); }
	//catch (FoodException e) { System.out.println(e); }
        

        
	try
	{
            lassie.eat(scooby);
        }
	catch (FoodException e) { System.out.println(e); }    
        //catch (FoodException e) { System.out.println(e.getClass().getName()); } 
        

	System.out.println("\n\n----- End -----");
    }
}
