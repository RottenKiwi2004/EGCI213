package Lab_Ch4;

import java.util.*;

// Entity class
class CircleCalculator3 
{
    private double radius;
    public CircleCalculator3(double r)          { radius = r; }    
    public void findArea()	
    { 
        double area = Math.PI * radius * radius;
	System.out.printf("Radius = %.2f, area = %.4f \n", radius, area); 
    }
};

//////////////////////////////////////////////////////////////////////////////////////////
// Application logic --> object-oriented (Java) programming style
public class w4_3_PreviousApp_3
{
    private double lowerBound = 5;
    private double upperBound = 10;

    public static void main(String[] args) 
    {
        w4_3_PreviousApp_3 mainApp = new w4_3_PreviousApp_3();
        mainApp.task();
    }    
    public void task()
    {
        double r = getInput();        
        CircleCalculator3 cir = new CircleCalculator3(r);
        cir.findArea();          
    }
    
    ////////////////////////////////////////////////////////////////////////////
    // Helper methods
    public double getInput()
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
    public void setBounds(int lower, int upper)
    {
        lowerBound = lower;
        upperBound = upper;
    }
}

