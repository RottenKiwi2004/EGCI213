package Lab_Ch4;

class Alien2
{
//    static private final int maxcount = 4;
//    static public int count = 0;

    // ----- (4) use class constructor to initialize static members

    static private final int maxcount;
    static public int count;

    static public void startup()                { System.out.println("\n=== Startup ===\n"); }
    static {
        maxcount = 4;
        count = 0;
        startup();
    }


    private String  name;

    // ----- (1) Object constructors - constructor chain
    private Alien2()				{ System.out.print("Alien "); }
    public Alien2(String n)			{ this(); name = n; System.out.println(name);}

    // ----- (2) static & non-static members
    // static public String getName()           { return name; }

    static public boolean arrive()
    {
	if (count < maxcount) return true;
	else return false;
    }

    // ----- (5) update final variable
    //static public void moreAliens(int m)		{ maxcount = m; }
};

/////////////////////////////////////////////////////////////////////////////////////////////
class w4_2_Members
{
    public static void main(String[] args) 
    {
        System.out.println("========== Program start ==========");
        String[] names = {"A", "B", "C", "D", "E", "F"};
        Alien2[] a = new Alien2[6];

        for (int i=0; i < a.length; i++)
        {
            // ----- (1) constructor
            a[i] = new Alien2(names[i]);

            // ----- (2) call static member "arrive()" via class name
            //           notice that getName() must be called via object only
            if (Alien2.arrive())
            {
                // Uncomment this line
                //System.out.printf("***** %s is welcome \n\n", a[i].getName());
                Alien2.count++;
            }

            // ----- (3) call static member "arrive()" via object
            /*
            if (a[i].arrive())
            {
                //System.out.printf("***** %s is welcome \n\n", a[i].getName());
                a[i].count++;
            }
            */
        }

        // ----- (5) update final variable
        //Alien2.moreAliens(10);

        System.out.println();
    }
}
