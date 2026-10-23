package bikeshare;

public class Bike {
    private final String id;
    private Station station;
    private String rentedBy;
    private MemberType memberType;

    public Bike(String id, Station station) {
        this.id = id;
        this.station = station;
    }

    public String getId() {
        return id;
    }

    public boolean isAvailableAt(Station targetStation) {
        return rentedBy == null && station == targetStation;
    }

    public boolean isRentedWithId(String targetId) {
        return rentedBy != null && id.equalsIgnoreCase(targetId);
    }

    public void rent(String memberId, MemberType type) {
        this.station = null;
        this.rentedBy = memberId;
        this.memberType = type;
    }

    public RideReceipt returnTo(Station returnStation, int minutes) {
        RideReceipt receipt = new RideReceipt(id, memberType, minutes);
        this.station = returnStation;
        this.rentedBy = null;
        this.memberType = null;
        return receipt;
    }
}
