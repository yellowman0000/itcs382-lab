package bikeshare;

public class Bike {
    public String id;
    public String station;
    public String rentedBy;
    public int memberType; // 1 = student, 2 = staff

    public Bike(String id, String station) {
        this.id = id;
        this.station = station;
        this.rentedBy = null;
        this.memberType = 0;
    }

    public void update(String station, String memberId, int memberType, boolean isRent) {
        if (isRent) {
            this.station = null;
            this.rentedBy = memberId;
            this.memberType = memberType;
        } else {
            this.station = station;
            this.rentedBy = null;
            this.memberType = 0;
        }
        if (!BikeShare.bikes.contains(this)) {
            BikeShare.bikes.add(this);
        }
    }
}
