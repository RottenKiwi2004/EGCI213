package Lab_Ch5;

import java.util.*;
import java.io.*;

/////////////////////////////////////////////////////////////////////////////////////////////
class MyInputReader
{
    private String   path, fileName;
    private Scanner  keyboardScan;
    private int      sum;
    
    public MyInputReader(String p, String fn)                
    {
        path      = p;
        fileName  = fn; 
        keyboardScan = new Scanner(System.in);
    }
    
    public void processLine(String line)
    {
        int numerator, divisor, result = 0;
        try
        {
            String []buf = line.split("\\s+");         // split by one or more spaces
            numerator = Integer.parseInt(buf[0]);
            divisor   = Integer.parseInt(buf[1]);
            result    = numerator / divisor;
            System.out.printf("%2d / %2d = %d \n", numerator, divisor, result);
        }
        catch(RuntimeException e) 
        //catch(ArithmeticException | ArrayIndexOutOfBoundsException e)
        //catch(ArithmeticException | RuntimeException e)    
        {   
            System.out.println(e + " --> skip");
            result = 0; 
        }
        finally
        {
            sum = sum + result;
        }
    }
    
    public void oldTry_openFileOnce() 
    {
        // ----- (1) declare fileScan outside try-block, so it can be used in finally-block
        Scanner fileScan = null;
        try 
        {
            fileScan = new Scanner(new File(path + fileName));
            while(fileScan.hasNextLine())  
            { 
                processLine(fileScan.nextLine());
            }
        }
        catch (FileNotFoundException e) 
        {
            System.out.println(e);
        }
        finally 
        {
            // ----- (1) close the file, either with or without an exception
            if (fileScan != null) fileScan.close();
            System.out.printf("Finally >> Sum = %d \n", sum);
        }
    }
    
    public void newTry_openFileOnce() 
    {
        try (
            // ----- (2) declare fileScan in resource declaration of try-block
            //           it can be used only in try-block & close automatically
            Scanner fileScan = new Scanner(new File(path + fileName));
        ){
            while(fileScan.hasNextLine())  
            { 
                processLine(fileScan.nextLine());
            }
        }
        catch (FileNotFoundException e) 
        {
            System.out.println(e);
        }
        finally 
        {
            System.out.printf("Finally >> Sum = %d \n", sum);
        }
    }
    
    public void newTry_openFileLoop() 
    {
        boolean opensuccess = false;
        while (!opensuccess)
        {
            try (
                Scanner fileScan = new Scanner(new File(path + fileName));
            ){
                opensuccess = true;                
                while(fileScan.hasNextLine())  
                { 
                    processLine(fileScan.nextLine());
                }
            }
            catch (FileNotFoundException e) 
            {
                System.out.print(e + " --> ");
                System.out.println("New file name = ");
                fileName = keyboardScan.next();
            }
            finally 
            {
                System.out.printf("Finally >> Sum = %d \n", sum);
            }
        }
    }
}
/////////////////////////////////////////////////////////////////////////////////////////////
public class w5_6_TryResource 
{
    public static void main(String[] args) 
    {
        String path     = "src/main/Java/Lab_Ch5/";
        String [] files = {"correctone.txt", "correctzero.txt", "wrong.txt"};
        
        MyInputReader calc = new MyInputReader( path, files[2] );
        calc.oldTry_openFileOnce();
        //calc.newTry_openFileOnce();
        //calc.newTry_openFileLoop();
    }    
}
