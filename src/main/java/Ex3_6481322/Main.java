package Ex3_6481322;

import java.io.File;
import java.util.Scanner;

class Racing
{
    public static final int CURRENT_YEAR = 2024;

    private String  event, venue;
    protected int   venueOpened, venueAge;
    public Racing(String nm, String vn) { event = nm; venue = vn; }
    public String getEvent()            { return event; }
    public String getVenue()            { return venue; }
    public void printVenue()            { /* override this in child class */ }
    public void printDetails()          { /* override this in child class */ }
}

class HorseRacing extends Racing {
    private double distanceFurlong, distanceKm;

    public HorseRacing(String name, String venue, int age, double dist) {
        super(name, venue);
        this.venueOpened = CURRENT_YEAR - age;
        this.venueAge = age;
        this.distanceFurlong = dist;
        this.distanceKm = dist / 5;
    }

    @Override
    public void printVenue() {
        System.out.printf("%-30s venue = %-30s (opened %-4d, %4d years ago)\n",
                this.getEvent(), this.getVenue(),
                this.venueOpened, this.venueAge);
    }

    @Override
    public  void printDetails() {
        System.out.printf("%-30s distance = %7.2f furlongs = %7.2f km\n",
                this.getEvent(), this.distanceFurlong, this.distanceKm);
    }
}

class MotorRacing extends Racing {
    private double lapLength, speed, lapTimeMs;
    private String lapTime;
    public MotorRacing(String name, String venue, int year, double lapLength, String lapTime) {
        super(name, venue);
        this.venueOpened = year;
        this.venueAge = CURRENT_YEAR - year;
        this.lapLength = lapLength;
        this.lapTime = lapTime;
        String [] lapInfo = lapTime.split(":");
        this.lapTimeMs = Integer.parseInt(lapInfo[0]) * 60000 + Double.parseDouble(lapInfo[1]) * 1000;
        // Speed in km/hr = lapLength (km) / lapTime (ms) * 3600000 (ms) / 1 (hr)
        this.speed = this.lapLength * 3600000 / this.lapTimeMs;
    }

    @Override
    public void printVenue() {
        System.out.printf("%-30s venue = %-30s (opened %-4d, %4d years ago)\n",
                this.getEvent(), this.getVenue(),
                this.venueOpened, this.venueAge);
    }

    @Override
    public  void printDetails() {
        System.out.printf("%-30s lap = %6.3f km    lap time = %9s mins    avg speed = %7.1f km/hr\n",
                this.getEvent(), this.lapLength, this.lapTime, this.speed);
    }
}

public class Main {
    public static void main(String[] args) {
        Racing [] allRaces = new Racing[13];
        try {
            // Get input from file
            Scanner scanner = new Scanner(new File("src/main/java/Ex3_6481322/racing.txt"));
            for(int i=0; i<13; i++) {
                String oneLine = scanner.nextLine();
                String[] columns = oneLine.split(",");
                String type = columns[0].trim();
                String event = columns[1].trim();
                String venue = columns[2].trim();


                allRaces[i] = type.equalsIgnoreCase("h") ?
                        new HorseRacing(event, venue,
                                        Integer.parseInt(columns[3].trim()),
                                        Double.parseDouble(columns[4].trim())
                        ) :
                        new MotorRacing(event, venue,
                                        Integer.parseInt(columns[3].trim()),
                                        Double.parseDouble(columns[4].trim()),
                                        columns[5].trim()
                        );
            }

            // Print venue (reverse)
            System.out.println("=== All races (reverse order) ===");
            for(int i=12; i>=0; i--)
                allRaces[i].printVenue();

            System.out.println();

            // Print horse details
            System.out.println("=== Only Horse races (input order) ===");
            for(int i=0; i<13; i++)
                if (allRaces[i] instanceof HorseRacing)
                    allRaces[i].printDetails();

            System.out.println();

            // Print motor details
            System.out.println("=== Only Motor races (input order) ===");
            for(int i=0; i<13; i++)
                if (allRaces[i] instanceof MotorRacing)
                    allRaces[i].printDetails();
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }
}
