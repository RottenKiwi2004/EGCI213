package Lab_Ch4;

import java.util.*;

// Entity class
class CircleCalculator1 
{
    private double radius;
    public CircleCalculator1(double r)          { radius = r; }    
    public void findArea()	
    { 
        double area = Math.PI * radius * radius;
	System.out.printf("Radius = %.2f, area = %.4f \n", radius, area); 
    }
};

//////////////////////////////////////////////////////////////////////////////////////////
// Application logic 
public class w4_3_PreviousApp_1
{
    public static void main(String[] args) 
    {
        double lowerBound = 5;
        double upperBound = 10; 
        
        // Check valid input
        Scanner scan = new Scanner(System.in);
        double r;
        do
        {
            System.out.printf("Enter radius between %.0f and %.0f = \n", lowerBound, upperBound); 
            r = scan.nextDouble();
        } while (r < lowerBound || r > upperBound);
        
        CircleCalculator1 cir = new CircleCalculator1(r);
        cir.findArea();
    }
}