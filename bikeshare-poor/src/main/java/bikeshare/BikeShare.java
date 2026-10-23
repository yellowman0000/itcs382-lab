package bikeshare;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BikeShare {
    public static List<Bike> bikes = new ArrayList<>();
    public static int studentRate = 2;
    public static int staffRate = 3;
    public static String fileName = "rides.csv";

    public static void main(String[] args) {
        String[] stations = { "Library", "Canteen", "Dorm" };
        int nextId = 1;
        for (String station : stations) {
            bikes.add(new Bike("B" + nextId++, station));
            bikes.add(new Bike("B" + nextId++, station));
        }

        System.out.println(
                "Commands: rent <memberId> <student|staff> <station>, return <bikeId> <station> <minutes>, quit");
        try (Scanner in = new Scanner(System.in)) {
            while (in.hasNextLine()) {
                String line = in.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\\s+");
                if (parts[0].equals("quit")) {
                    break;
                } else if (parts[0].equals("rent") && parts.length == 4) {
                    int type = (parts[2].equalsIgnoreCase("student") || parts[2].equals("1")) ? 1 : 2;
                    processRide(parts[1], type, parts[3], "", 0, true);
                } else if (parts[0].equals("return") && parts.length == 4) {
                    try {
                        int minutes = Integer.parseInt(parts[3]);
                        processRide("", 0, parts[2], parts[1], minutes, false);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid minutes: " + parts[3]);
                    }
                } else {
                    System.out.println("Invalid command");
                }
            }
        }
    }

    public static void processRide(String memberId, int memberType, String station,
            String bikeId, int minutes, boolean isRent) {
        if (isRent) {
            for (Bike bike : bikes) {
                if (bike.rentedBy == null && station.equalsIgnoreCase(bike.station)) {
                    bike.update(station, memberId, memberType, true);
                    System.out.println("Rented bike " + bike.id + " to " + memberId + " at " + station);
                    return;
                }
            }
            System.out.println("No bike available at " + station);
        } else {
            for (Bike bike : bikes) {
                if (bike.id.equalsIgnoreCase(bikeId) && bike.rentedBy != null) {
                    String renter = bike.rentedBy;
                    int fee = calculateFee(bike, minutes);
                    bike.update(station, null, 0, false);
                    System.out.println("Receipt: Bike " + bike.id + ", " + minutes + " minutes, Fee " + fee + " baht");
                    saveRide(bike.id, renter, station, minutes, fee);
                    return;
                }
            }
            System.out.println("Bike " + bikeId + " is not currently rented");
        }
    }

    public static int calculateFee(Bike bike, int minutes) {
        if (bike.memberType == 1) {
            return minutes * studentRate;
        }
        return minutes * staffRate;
    }

    public static void saveRide(String bikeId, String memberId, String station, int minutes, int fee) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(bikeId + "," + memberId + "," + station + "," + minutes + "," + fee + "\n");
        } catch (IOException e) {
            System.out.println("Error saving ride: " + e.getMessage());
        }
    }
}
