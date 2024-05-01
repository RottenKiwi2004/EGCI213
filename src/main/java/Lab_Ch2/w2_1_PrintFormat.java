package Lab_Ch2;

public class w2_1_PrintFormat 
{
    public static void main(String[] args) 
    {
        // (1) change from println to print -- notice differences
        System.out.println("Hello world");

        String name   = "Jonh";
        int    age    = 30;
        
        /*
        // (2) string concatenation for print and println
        System.out.println("\n=== From println ===");
        System.out.println("My name is " + name);
        System.out.println("I am " + age + " years old");
        
        // (3) c-style formatting for printf
        System.out.printf("\n=== From printf === \n");
        System.out.printf("My name is %s \n", name);
        System.out.printf("I am %d years old \n", age);
        */

        
        /*
        // (4) repeating strings
        System.out.println("\n=== Repeating strings ===");
        String separator = "-+";
        System.out.println( "#".repeat(80) );                 
        System.out.printf( "%s \n", separator.repeat(40) );      
        System.out.printf( "hello %15s world \n", " ");         // long spaces
        */

        String col = "O";
        
        /*
        // (5) %[align][width] { c | C | s | S }
        System.out.println("\n=== Formatting by %c and %s ===");
        char   a  = 'a';
        String th = "Thailand";
        String jp = "Japan";
        String sw = "Switzerland";
        System.out.println( col.repeat(15) + "+++++" + col.repeat(4) + "+++++" );
        System.out.printf("%15s +++ %-4c +++ \n", th, a);       // try string widht = 4
        System.out.printf("%15s +++ %-4c +++ \n", jp, a);    
        System.out.printf("%15S +++ %-4C +++ \n", sw, a);
        */

        
        /*
        // (6) %[align][sign][0][,][width] d
        System.out.println("\n=== Formatting by %d ===");
        int x = 11111;
        int y = -22222;
        System.out.println( "    " + col.repeat(8) );
        System.out.printf("x = %8d \n", x);                     // try string widht = 4
        System.out.printf("y = %-8d \n", y);
        */
        
        
        /*
        // (7) %[align][sign][0][,][width][.precision] f
        //     - add suffix f for float
        //     - float with too many digits may give imprecise decimals
        System.out.println("\n=== Formatting by %f ===");
        double u = 10000.456;
        float  v = 20000.456f;                                 // try 200 instead of 20000
        System.out.println( "    " + col.repeat(12) );
        System.out.printf("u = %12.2f \n", u);                 // try string widht = 4
        System.out.printf("v = %-12.2f \n", v);        
        */
    }
    
}
