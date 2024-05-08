package Lab_Ch4;

public class w4_4_NewApp 
{
    public static void main(String[] args) 
    {
        // Reuse applicaion logic from previous program
        
        // ----- (1) Previous program has only main method
        /*
        System.out.println("----- Call PreviousApp (1) -----");
        w4_3_PreviousApp_1.main(args);
        */
        
        
        // ----- (2) Previous program has static methods
        /*
        System.out.println("----- Call PreviousApp (2) -----");
        System.out.println("\n\n+++++ Reuse 1 : default bounds ");
        w4_3_PreviousApp_2.task();
        
        System.out.println("\n\n+++++ Reuse 2 : new bounds ");
        w4_3_PreviousApp_2.setBounds(6, 8);
        w4_3_PreviousApp_2.task();

        System.out.println("\n\n+++++ Reuse 3 : back to default bounds ");
        w4_3_PreviousApp_2.setBounds(5, 10);
        w4_3_PreviousApp_2.task();        
        */
        
        
        // ----- (3) Previous program has non-static methods
        /*
        System.out.println("----- Call PreviousApp (3) -----");  
        System.out.println("\n\n+++++ Reuse 1 : default bounds ");
        w4_3_PreviousApp_3 reuse1 = new w4_3_PreviousApp_3();
        reuse1.task();
        
        System.out.println("\n\n+++++ Reuse 2 : new bounds ");
        w4_3_PreviousApp_3 reuse2 = new w4_3_PreviousApp_3();
        reuse2.setBounds(6, 8);
        reuse2.task();  
        
        System.out.println("\n\n+++++ Reuse 3 : back to default bounds ");
        reuse1.task();
        */
    }
}
