package bikeshare;

import java.util.ArrayList;
import java.util.List;

public class BikeShareService {
    private final List<Bike> bikes;
    private final RideRepository rideRepository;

    public BikeShareService(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
        this.bikes = new ArrayList<>();
        int nextId = 1;
        for (Station station : Station.values()) {
            this.bikes.add(new Bike("B" + nextId++, station));
            this.bikes.add(new Bike("B" + nextId++, station));
        }
    }

    public String rentBike(String memberId, MemberType memberType, Station station) {
        for (Bike bike : bikes) {
            if (bike.isAvailableAt(station)) {
                bike.rent(memberId, memberType);
                return bike.getId();
            }
        }
        return null;
    }

    public RideReceipt returnBike(String bikeId, Station station, int minutes) {
        for (Bike bike : bikes) {
            if (bike.isRentedWithId(bikeId)) {
                RideReceipt receipt = bike.returnTo(station, minutes);
                rideRepository.save(receipt);
                return receipt;
            }
        }
        return null;
    }
}
