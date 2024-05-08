package Lab_Ch4;

import java.util.*;

// Entity class
class CircleCalculator2 
{
    private double radius;
    public CircleCalculator2(double r)          { radius = r; }    
    public void findArea()	
    { 
        double area = Math.PI * radius * radius;
	System.out.printf("Radius = %.2f, area = %.4f \n", radius, area); 
    }
};

//////////////////////////////////////////////////////////////////////////////////////////
// Application logic --> structural (C) programming style
public class w4_3_PreviousApp_2
{
    private static double lowerBound = 5;
    private static double upperBound = 10;

    public static void main(String[] args) 
    {
        task();    
    }
    public static void task()
    {
        double r = getInput();        
        CircleCalculator2 cir = new CircleCalculator2(r);
        cir.findArea();         
    }
    
    ////////////////////////////////////////////////////////////////////////////
    // Helper methods
    public static double getInput()
    {
        // Check valid input 
        Scanner scan = new Scanner(System.in);
        double r;
        do
        {
            System.out.printf("Enter radius between %.0f and %.0f = \n", lowerBound, upperBound); 
            r = scan.nextDouble();
        } while (r < lowerBound || r > upperBound);
        return r;
    }    
    public static void setBounds(int lower, int upper)
    {
        lowerBound = lower;
        upperBound = upper;
    }
}
