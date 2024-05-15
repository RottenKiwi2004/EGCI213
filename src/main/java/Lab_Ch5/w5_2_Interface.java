package Lab_Ch5;

interface Flyer
{
    public void fly(String from, String to);
};

interface Swimmer
{
    public void swim(String from, String to);
};

class AnimalAbstract implements Flyer, Swimmer
{
    private int legs;
    private boolean flyable;

    public AnimalAbstract(int g, boolean f)		{ legs = g; flyable = f; }
    public int	    getLegs()				{ return legs; }
    public boolean  canFly()                            { return flyable; }

    public void eat(AnimalAbstract food)		{ }
    public void walk(String from, String to)            { }
    @Override
    public void fly(String from, String to)		{ }
    @Override
    public void swim(String from, String to)            { }
};

/////////////////////////////////////////////////////////////////////////////////////////////
// ----- (1) create new classes from abstract class

class Dog extends AnimalAbstract
{
    private String name;
    public Dog(String n)				{ super(4, false); name = n; }
    public String getName()				{ return name; }

    @Override
    public void fly(String from, String to)		
    { 
	System.out.printf("%s cannot fly \n", getName()); 
    }
};

class Bird extends AnimalAbstract
{
    private String name;
    public Bird(String n, boolean f)			{ super(2, f); name = n; }
    public String getName()				{ return name; }

    @Override
    public void fly(String from, String to)
    { 
	if (canFly()) System.out.printf("%s flies from %s to %s \n", getName(), from, to);
	else          System.out.printf("%s cannot fly \n", getName());
    }
};

/////////////////////////////////////////////////////////////////////////////////////////////
// ----- (2) create new class from interface

class Plane implements Flyer
{
    private String name;
    public Plane(String n)				{ name = n; }
    public String getName()				{ return name; }

    @Override
    public void fly(String from, String to)		
    { 
	System.out.printf("%s takes off from %s, bound for %s \n", getName(), from, to); 
    }
};

/////////////////////////////////////////////////////////////////////////////////////////////
class w5_2_Interface
{
    public static void main(String[] args) 
    {
        w5_2_Interface mainApp = new w5_2_Interface();
//        mainApp.testPolymorphismViaClass();
//        mainApp.testPolymorphismViaInterface();
    }
    
    public void testPolymorphismViaClass()
    {
	// ----- (3) polymorphism via class
	System.out.println("\n\n--- polymorphism via class ---");
	AnimalAbstract[] A = new AnimalAbstract[3];
	A[0] = new Dog("Lassie");
	A[1] = new Bird("Parrot", true);
	A[2] = new Bird("Penquin", false);
	for (int i=0; i < A.length; i++) A[i].fly("Manchester", "London");
        //for (AnimalAbstract a : A) a.fly("Manchester", "London");     
        
        System.out.println();
    }
    
    public void testPolymorphismViaInterface()
    {
	// ----- (4) polymorphism via interface
	System.out.println("\n\n--- polymorphism via interface ---");
	Flyer[] F = new Flyer[2];
	F[0] = new Bird("Pigeon", true);
	F[1] = new Plane("British Midland");
	for (int i=0; i < F.length; i++) F[i].fly("Manchester", "London");
        //for(Flyer f : F) f.fly("Manchester", "London");    
        
        System.out.println();
    }
}
