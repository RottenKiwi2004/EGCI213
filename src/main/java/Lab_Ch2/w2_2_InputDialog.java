package Lab_Ch2;

// Read input from dialog box

import javax.swing.*;

public class w2_2_InputDialog
{
    public static void main(String[] args) 
    {
        int    ia, ib;
	double da, db;
	String  buf;
	buf = JOptionPane.showInputDialog("Enter input");

	System.out.println("output string = " + buf);
	JOptionPane.showMessageDialog(null, "output string = " + buf, "My Dialog", JOptionPane.INFORMATION_MESSAGE);


	// ----- (1) get integer into ia
	//           try entering a double as input
	/*
	ia = Integer.parseInt( buf );
	ib = ia * 100;
	System.out.println( "Integer (* 100) = " + ib );
	*/


	// -----(2) get double into da
	//          try entering an integer as input
	/*
	da = Double.parseDouble( buf );
	db = da * 0.25;
	System.out.println( "Double  (* 0.25) = " + db );
	*/
    }
}
