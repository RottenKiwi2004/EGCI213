package Lab_Ch2;

// Read input by using Scanner

import java.util.*;

public class w2_3_InputScanner
{
    public static void main(String[] args) 
    {
	int    ia, ib;
	double da, db;
	String  buf, line;

	Scanner scan = new Scanner(System.in);
	System.out.println("Enter string     = "); buf  = scan.next();
	System.out.println("Enter integer    = "); ia   = scan.nextInt();
	System.out.println("Enter double     = "); da   = scan.nextDouble();
        scan.nextLine();
        System.out.println("Enter whole line = "); line = scan.nextLine();

	ib = ia / 100;
	db = da * 0.25;
    System.out.println();
	System.out.println("output string    = " + buf);
	System.out.printf("Integer (/ 100)  = %d \n", ib);
	System.out.printf("Double  (* 0.25) = %.2f \n", db);
        System.out.printf("Message = %s \n", line);
    }
}
