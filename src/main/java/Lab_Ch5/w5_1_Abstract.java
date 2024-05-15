package Lab_Ch5;

// ----- (1) abstract class
abstract class AnimalAbstract1
{
    private int legs;
    private boolean flyable;

    public AnimalAbstract1(int g, boolean f)		{ legs = g; flyable = f; }
    public int	    getLegs()				{ return legs; }
    public boolean  canFly()				{ return flyable; }

    // ----- (2) enforce the implementation of these methods
    abstract public void eat(AnimalAbstract1 food);
    abstract public void walk(String from, String to);
    abstract public void fly(String from, String to);
    abstract public void swim(String from, String to);
};

/////////////////////////////////////////////////////////////////////////////////////////////
class Dog1 extends AnimalAbstract1
{
    private String name;
    public Dog1(String n)				{ super(4, false); name = n; }
    public String getName()				{ return name; }

    @Override
    public void eat(AnimalAbstract1 food)	
    { 
	System.out.printf("%s eats %s \n", getName(), food.getClass().getName()); 
    }

    @Override
    public void walk(String from, String to)		
    { 
	System.out.printf("%s walks with %d legs from %s \n", getName(), getLegs(), from); 
    }
    
    //@Override
    public void fly(String from, String to)             { }
    //@Override
    public void swim(String from, String to)            { }
};

/////////////////////////////////////////////////////////////////////////////////////////////
class Bird1 extends AnimalAbstract1
{
    private String name;
    public Bird1(String n, boolean f)			{ super(2, f); name = n; }
    public String getName()				{ return name; }

    @Override
    public void fly(String from, String to)
    { 
	if (canFly()) System.out.printf("%s flies from %s to %s \n", getName(), from, to);
	else          System.out.printf("%s cannot fly \n", getName());
    }

    @Override
    public void walk(String from, String to)		
    { 
	System.out.printf("%s walks with %d legs to %s \n", getName(), getLegs(), to); 
    }

    //@Override
    public void eat(AnimalAbstract1 food)               { }
    //@Override
    public void swim(String from, String to)            { }
};

/////////////////////////////////////////////////////////////////////////////////////////////
class w5_1_Abstract
{
    public static void main(String[] args) 
    {
	// ----- (1) using abstract class as reference var & object
	AnimalAbstract1[] A = new AnimalAbstract1[3];
	//A[0] = new AnimalAbstract1(4, false);
	A[0] = new Dog1("Lassie");
	A[1] = new Bird1("Parrot", true);
	A[2] = new Bird1("Penguin", false);

	A[0].eat(A[1]);
	System.out.println();

	// ----- (2) polymorphism
        System.out.println("\n\n--- polymorphism via class ---");
	for (int i=0; i < A.length; i++)
	{
            A[i].walk("Manchester", "London");
            A[i].fly("Manchester", "London");
            System.out.println();
	}
    }
}
