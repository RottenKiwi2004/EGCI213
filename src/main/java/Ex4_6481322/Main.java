package Ex4_6481322;

import java.util.*;
import java.io.*;

class Seafood implements Comparable<Seafood> {

    private String name, type;
    private int omega3, cholesterol;
    private double mercury;
    public Seafood(String name, String type, int omega3, int cholesterol, double mercury) {
        this.name = name;
        this.type = type;
        this.omega3 = omega3;
        this.cholesterol = cholesterol;
        this.mercury = mercury;
    }
    public int compareTo(Seafood other) {
        if (this.omega3 > other.omega3) return -1;
        else if (this.omega3 < other.omega3) return 1;
        else if (this.cholesterol < other.cholesterol) return -1;
        else if (this.cholesterol > other.cholesterol) return 1;
        else if (this.mercury < other.mercury) return -1;
        else if (this.mercury > other.mercury) return 1;
        else return this.name.compareToIgnoreCase(other.name);
    }

    public String   getName()       { return this.name;         }
    public String   getType()       { return this.type;         }
    public int      getOmega3()     { return this.omega3;       }
    public int      getCholesterol(){ return this.cholesterol;  }
    public double   getMercury()    { return this.mercury;      }
    public String   getTypeString() {
        switch(this.type.charAt(0)) {
            case 'm' -> { return "mollusk";     }
            case 'c' -> { return "crustacean";  }
            case 'f' -> { return "fish";        }
            default  -> { return "";            }
        }
    }
}

class Table {
    private String formatHeader, formatContent, tableHead;
    public Table(int [] format, int decimalCount) {
        this.formatHeader  = "%" + format[0] + "s"
                           + "%" + format[1] + "s"
                           + "%" + format[2] + "s"
                           + "%" + format[3] + "s"
                           + "%" + format[4] + "s" + "\n";
        this.formatContent = "%" + format[0] + "s"
                           + "%" + format[1] + "s"
                           + "%" + format[2] + "d"
                           + "%" + format[3] + "d"
                           + "%" + format[4] + "." + decimalCount + "f" + "\n";
        int sum = 0;
        for(int num : format) sum += num > 0 ? num : -num;
        this.tableHead = "=".repeat(sum);
    }

    // For printing each seafood
    public void printTable(Seafood seafood, String type) {

        if (type.compareToIgnoreCase(seafood.getType()) != 0 && type.compareToIgnoreCase("a") != 0) return;

        System.out.printf(formatContent , seafood.getName()
                                        , seafood.getTypeString()
                                        , seafood.getOmega3()
                                        , seafood.getCholesterol()
                                        , seafood.getMercury()
        );
    }

    // For printing header
    public void printTable() {
        System.out.printf(formatHeader  , "Seafood (3 oz)"
                                        , "Type"
                                        , "Omega-3 (mg)"
                                        , "Cholesterol (mg)"
                                        , "Mercury (ppm)"
        );
        System.out.println(tableHead);
    }
}

public class Main {

    public static void main(String[] args) {
        try {

            Scanner fileScanner = new Scanner(new File("src/main/java/Ex4_6481322/seafoods.txt"));

            // Ignore first line
            fileScanner.nextLine();

            ArrayList<Seafood> seafoods = new ArrayList<Seafood>();
            while (fileScanner.hasNextLine()) {
                String lineInput = fileScanner.nextLine();
                String [] col = lineInput.split(",");
                for (int i=0; i < col.length; i++)
                    col[i] = col[i].trim();

                String type = col[0];
                String name = col[1];
                int omega3 = Integer.parseInt(col[2]);
                int cholesterol = Integer.parseInt(col[3]);
                double mercury = Double.parseDouble(col[4]);
                seafoods.add(new Seafood(
                        name, type, omega3, cholesterol, mercury
                ));
            }

            Collections.sort(seafoods);

            Scanner scanner = new Scanner(System.in);
            int [] formatHeader = {-30, -12, 15, 20, 20};
            Table table = new Table(formatHeader, 3);
            while (true) {
                System.out.println("Choose filter --> a = all, f = fish, c = crustacean, m = mollusk, others = quit");
                String selected = scanner.nextLine();
                selected = selected.toLowerCase();
                switch(selected.charAt(0)) {
                    case 'm': case 'c': case 'f': case 'a': break;
                    default: return;
                }
                table.printTable();
                for (Seafood seafood : seafoods) {
                    table.printTable(seafood, selected);
                }
                System.out.println("\n\n");
            }
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}
