package Lab_Ch5;

// classes Dog, Bird, Plane from w5_2_Interface (compile w5_2_Interface first)

/////////////////////////////////////////////////////////////////////////////////////////////

// ----- (1) class template
//           which class headers work for (2) and (3) ?

//class Cell<T>
//class Cell<T extends AnimalAbstract>
class Cell<T extends Flyer>
{
    protected T member;
    public Cell(T m)					{ member = m; } 
    public void testCell()
    {
	System.out.printf("Cell<%s> : ", member.getClass().getName());
	member.fly("Manchester", "London");
    }
};

// ----- (3) inherit from class template
//           which class headers in (1) work ?
/*
class Airport extends Cell <Plane>
{
    public Airport(Plane p)				{ super(p); }
    public void testAirport()
    {
	System.out.printf("Airport<%s> : ", member.getClass().getName());
	member.fly("Manchester", "London");
    }
};
*/

/////////////////////////////////////////////////////////////////////////////////////////////
class w5_3_Template
{
    public static void main(String[] args) 
    {
	Dog   lassie = new Dog("Lassie");
	Bird  parrot = new Bird("Parrot", true);
	Plane bm     = new Plane("British Midland");


	// ----- (2) create & use objects from Cell
	//           which class headers in (1) work ?
	System.out.println("\n\n--- test class templates ---");
	//Cell<Dog>  dogCell    = new Cell<Dog>(lassie);    dogCell.testCell();
	//Cell<Bird> birdCell   = new Cell<Bird>(parrot);   birdCell.testCell();
	//Cell<Plane> planeCell = new Cell<Plane>(bm);      planeCell.testCell();

		
	// ----- (3) create & use objects from Airport
	System.out.println("\n\n--- test class inheriting from class template ---");
	//Airport ap = new Airport(bm);     ap.testAirport();


	// ----- (4) wildcard : uncomment the code in (2)
	//           anyCell can point to dogCell, birdCell, and planeCell
	System.out.println("\n\n--- test wildcard ---");
        //Cell<? extends AnimalAbstract> anyCell; 
	//Cell<? extends Flyer> anyCell; 
        
        //anyCell = dogCell;      anyCell.testCell();
        //anyCell = birdCell;     anyCell.testCell();
        //anyCell = planeCell;    anyCell.testCell();
        
	System.out.println();
	}
}
