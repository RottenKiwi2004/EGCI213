package Lab_Ch5;

import java.util.*;
        
//class MyPoint implements Comparable<MyPoint>
class MyPoint implements Comparable 
{
    private int x, y;
    public MyPoint(int a, int b)	{ x = a; y = b; }

    // Rules for comparing this and another MyPoint object
    //public int compareTo(MyPoint other)
    @Override
    public int compareTo(Object param) 
    {
        // No need for type casting if param type = MyPoint
	MyPoint other = (MyPoint) param;
        
        // Sorting depends on value of x only
	if (this.x < other.x)       return -1;	
	else if (this.x > other.x)  return 1;	
	else                        return 0;
    }

    public void print()    
    { 
        System.out.printf("(x = %d, y = %d) \n", x, y);
    }
}

/////////////////////////////////////////////////////////////////////////////////////////////
class w5_4_Sorting
{
    public static void main(String[] args) 
    {
        // (1) Arrays
        MyPoint [] P = new MyPoint[4];
        P[0] = new MyPoint(40, 100);
        P[1] = new MyPoint(10, 400);
        P[2] = new MyPoint(30, 200);
        P[3] = new MyPoint(20, 300);
        
        System.out.println("\n----- Using Array -----");
        Arrays.sort(P);
        for (MyPoint p : P) p.print();
        
        // (2) ArrayList - type argument on RHS can be omitted
        ArrayList<MyPoint> AL = new ArrayList<MyPoint>();
        //ArrayList<MyPoint> AL = new ArrayList<>();
        for (int i=0; i < P.length; i++) 
            AL.add( P[i] );
        
        System.out.println("\n----- Using ArrayList -----");
        Collections.sort(AL);
        for (MyPoint p : AL) p.print();

        
        // Conversion between array and ArrayList
        // (3) Array to List, using asList
        //     LHS type must be List (i.e. parent interface of ArrayList)
        System.out.println("\n");
        List<MyPoint> fromArray = Arrays.asList(P);
        MyPoint max = Collections.max(fromArray);
        System.out.print("Array to List      --> max object  = "); max.print();
        
        // (4) ArrayList to array, using toArray
        //     Return type is array of object (type casting is required)
        Object [] fromList = AL.toArray();
        MyPoint first = (MyPoint) fromList[0];
        System.out.print("ArrayList to array --> first index = "); first.print();
    }
}
