// Mark Kittiphat Kuprasertwong 6481322
package Ex2_6481322;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        String currentDirectory = "src/main/java/Ex2_6481322/";
        String fileInputPath =  currentDirectory + "provinces.txt";
        String fileOutputPath =  currentDirectory + "output.txt";

        System.out.println("Enter the size of your city in square km =");
        Scanner scanner = new Scanner(System.in);
        int userSqKm = scanner.nextInt();

        // Write to output stream
        System.out.println("Read province size from " + fileInputPath);
        System.out.println("Write output " + fileOutputPath);

        try {

            // Write to file
            PrintWriter printWriter = new PrintWriter(new File(fileOutputPath));

            // Head of table
            printWriter.printf("%-15s%20s%20s%30s\r\n",
                        "Province", "Square km", "Square mile", "Ratio to your city"
                    );
            printWriter.println("=".repeat(85));

            // Start table content
            printOutput(printWriter,"Your city", userSqKm, toSqMile(userSqKm), 1.00);

            Scanner fileReader = new Scanner(new File(fileInputPath));
            while(fileReader.hasNext()) {
                String city = fileReader.next();
                int area = fileReader.nextInt();
                double sqMile = toSqMile(area);
                double ratio = (double) area / userSqKm;
                printOutput(printWriter, city, area, sqMile, ratio);
            }
            printWriter.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    public static double toSqMile(int area) {
        return area * 0.386102;
    }

    public static void printOutput(PrintWriter printWriter, String city, int area, double sqMile, double ratio) {
        printWriter.printf("%-15s%,20d%,20.2f%30.2f\r\n", city, area, sqMile, ratio);
    }
}
