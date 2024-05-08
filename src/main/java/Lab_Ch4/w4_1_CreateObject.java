package Lab_Ch4;

class Man1
{
    private String  name;
    private String  surname;
    private int	age;

    // Constructors
    public Man1()				{ }
    public Man1(String n, String s, int a)	{ name = n; surname = s; age = a; }

    // Setters + Getters
    public void    setAge(int a)		{ age = a; }
    public String  getName()                    { return name; }
    public String  getSurname()                 { return surname; }
    public int     getAge()			{ return age; }

    // Activities
    public void speak()			
    { 
        System.out.println("Hello. My name is " + getName() + " " + getSurname()); 
    }
};

/////////////////////////////////////////////////////////////////////////////////////////////
class Alien1
{
    private String  name;
    private String  planet;

    public Alien1(String n, String p)	{ name = n; planet = p; }

    // Setters + Getters
    public String  getName()		{ return name; }
    public String  getPlanet()		{ return planet; }

    // Activities
    public void scream()			
    { 
        System.out.println(getName() + " is an alien from " + getPlanet()); 
    }
};

/////////////////////////////////////////////////////////////////////////////////////////////
class w4_1_CreateObject
{
    public static void main(String[] args) 
    {
        // ----- (1) Create object
        Man1   Will  = new Man1("Will", "Smith", 20);
        Man1   Bruce = new Man1("Bruce", "Willis", 50);
        Alien1 Susan = new Alien1("Susan", "Mars");

        Will.speak();
        Bruce.speak();
        Susan.scream();


        // ----- (2) Check object's type
        //           Object is a root of all classes in Java
        Object any;
        System.out.println("\n\n--- Check object's type ---");
        any = Susan;
        if (any instanceof Man1) System.out.println("Man1 instance");
        else                     System.out.println("Not Man1 instance");

        if (any.getClass().getName().equals("Man1")) System.out.println("Class Man1 ");
        else                                         System.out.println("Not class Man1");		
    }
}
