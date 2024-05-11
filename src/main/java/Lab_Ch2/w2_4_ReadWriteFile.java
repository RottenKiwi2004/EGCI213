package Lab_Ch2;

import java.io.*;
import java.util.*;

class w2_4_ReadWriteFile  
{
  public static void main(String[] args)  
  {
        // Use separate scanner objects for keyboard and file input
        Scanner keyboardScan = new Scanner(System.in);
      
        // This is where we are
        String localDir = System.getProperty("user.dir");
        System.out.println("Current directory = " + localDir + "\n");

        // Don't use absolute path. Use only path relative to src
        // Use forward slash (/) instead of backward slash (\)
        String path        = "src/main/Java/Lab_Ch2/";
        String inFilename  = path + "input.txt";
        String outFilename = path + "output.txt";
        //String outFilename = "output2.txt";             // file is place outside src
        
        try
            {
                File inFile      = new File(inFilename);
                Scanner fileScan = new Scanner(inFile);
                System.out.println("Read input from (relative path) " + inFile.getPath());
                System.out.println("Read input from (absolute path) " + inFile.getAbsolutePath() + "\n");


                File outFile      = new File(outFilename);
                PrintWriter write = new PrintWriter(outFile);                              // overwrite (default)
                //PrintWriter write = new PrintWriter( new FileWriter(outFile, false) );   // overwrite
                //PrintWriter write = new PrintWriter( new FileWriter(outFile, true)  );   // append
                write.println("test");
                while (fileScan.hasNext())
                {
                    String name	  = fileScan.next();
                    double height = fileScan.nextDouble();
                    int age = fileScan.nextInt();
                    // Use \r\n when writing to file
                    System.out.printf("%s  height = %.0f  age = %d \n", name, height*100, age);
                    write.printf("%s  height = %.0f  age = %d \r\n", name, height*100, age);
                }

                fileScan.close();
                write.close();

                System.out.println("\nEnter 0 to delete output file"); int ans = keyboardScan.nextInt();
                if (ans == 0)
                {
                    if (outFile.exists()) outFile.delete();
                }
        }
        catch(Exception e) {
                System.err.println("An error occurs. End program.");
                System.err.println(e);
                //System.exit(-1);
        }
    }
}