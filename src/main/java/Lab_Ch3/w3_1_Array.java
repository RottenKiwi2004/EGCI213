package Lab_Ch3;

import java.util.*;

class w3_1_Array 
{
    public static void main(String[] args) 
    {
		// ----- (1) array initialisation
		//           both "int[] x " and "int x[]" are allowed
		//           notice that scope of "i" is only within loop
		int[] x = new int[10];
		for (int i=0; i < x.length; i++)  x[i] = (int)(Math.random() * 10);
		//System.out.println("current i = " + i);

		System.out.print("Original = ");
		for (int i=0; i < x.length; i++)  System.out.print(x[i] + "  ");

		System.out.print("\nSorted   = ");
		Arrays.sort(x);
		for (int i=0; i < x.length; i++)  System.out.print(x[i] + "  ");
		System.out.println("\n");


		// ----- (2) array of characters and string
		char[] c = new char[5];
		c[0] = 'H'; c[1] = 'e'; c[2] = 'L'; c[3] = 'L'; c[4] = 'o';
		//char[] c = {'H', 'e', 'L', 'L', 'o'};

		String s = "HeLLo";
		//String s = new String("HeLLo");
		//String s = new String(c);

		for (int i=0; i < s.length(); i++)  System.out.println(i + " : " + s.charAt(i));
		System.out.println();


		// ----- (3) convert whole string vs. just 1 char
		String upper = s.toUpperCase();                        // non-static method
		System.out.printf("%s --> %s \n", s, upper);

		char lower = Character.toLowerCase(c[0]);              // static method
		System.out.printf("%c --> %c \n\n", c[0], lower);
        
        
        // ----- (4) new features : switch(string), for-each
        String [] days = {"Monday", "Tuesday", "Wednesday", "Thursday", 
                          "Friday", "Saturday", "Sunday"};
        String test = "Wednesday";
        
        for(String d : days) System.out.println(d);
        
        
        // colon-case requires break to prevent fall-through to the next case
        // one colon-case for each case
        /*
		switch(test)
        {
            case "Saturday": case "Sunday":
                System.out.printf("\n%s is weekend \n", test);
                break;
            default:
                System.out.printf("\n%s is weekday \n", test);
        }
        */
        

        // arrow-case doesn't require break at the end
        // cases on RHS are separated by comma, LHS = an instruction or a block of code
        switch(test)
        {
            case "Saturday", "Sunday" -> System.out.printf("\n%s is weekend \n", test); 
            default                   -> {
				System.out.printf("\n%s is weekday \n", test);
				System.out.println("Test");
			}
        }

    }
}
