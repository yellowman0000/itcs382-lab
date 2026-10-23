package bikeshare;

import java.io.UncheckedIOException;
import java.util.Scanner;

public class Main {
    private final BikeShareService service;

    public Main(BikeShareService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        RideRepository repository = new CsvRideRepository("rides.csv");
        BikeShareService service = new BikeShareService(repository);
        new Main(service).run();
    }

    public void run() {
        System.out.println(
                "Commands: rent <memberId> <student|staff> <station>, return <bikeId> <station> <minutes>, quit");
        try (Scanner in = new Scanner(System.in)) {
            while (in.hasNextLine()) {
                String line = in.nextLine().trim();
                if (line.equalsIgnoreCase("quit")) {
                    break;
                }
                if (!line.isEmpty()) {
                    handleCommand(line.split("\\s+"));
                }
            }
        }
    }

    private void handleCommand(String[] parts) {
        try {
            if (parts[0].equalsIgnoreCase("rent") && parts.length == 4) {
                handleRent(parts[1], MemberType.fromToken(parts[2]), Station.fromToken(parts[3]));
            } else if (parts[0].equalsIgnoreCase("return") && parts.length == 4) {
                handleReturn(parts[1], Station.fromToken(parts[2]), Integer.parseInt(parts[3]));
            } else {
                System.out.println("Invalid command");
            }
        } catch (IllegalArgumentException | UncheckedIOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void handleRent(String memberId, MemberType memberType, Station station) {
        String bikeId = service.rentBike(memberId, memberType, station);
        if (bikeId == null) {
            System.out.println("No bike available at " + station.getDisplayName());
        } else {
            System.out.println("Rented bike " + bikeId + " to " + memberId + " at " + station.getDisplayName());
        }
    }

    private void handleReturn(String bikeId, Station station, int minutes) {
        RideReceipt receipt = service.returnBike(bikeId, station, minutes);
        if (receipt == null) {
            System.out.println("Bike " + bikeId + " is not currently rented");
        } else {
            System.out.println("Receipt: Bike " + receipt.getBikeId() + ", "
                    + receipt.getMinutes() + " minutes, Fee " + receipt.getFee() + " baht");
        }
    }
}
