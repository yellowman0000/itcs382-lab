package bikeshare;

public class RideReceipt {
    private final String bikeId;
    private final MemberType memberType;
    private final int minutes;

    public RideReceipt(String bikeId, MemberType memberType, int minutes) {
        this.bikeId = bikeId;
        this.memberType = memberType;
        this.minutes = minutes;
    }

    public String getBikeId() {
        return bikeId;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getFee() {
        return memberType.calculateFee(minutes);
    }
}
