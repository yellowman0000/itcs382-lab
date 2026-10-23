package bikeshare;

public enum MemberType {
    STUDENT(2),
    STAFF(3);

    private final int ratePerMinute;

    MemberType(int ratePerMinute) {
        this.ratePerMinute = ratePerMinute;
    }

    public int calculateFee(int minutes) {
        return minutes * ratePerMinute;
    }

    public static MemberType fromToken(String token) {
        for (MemberType type : values()) {
            if (type.name().equalsIgnoreCase(token)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown member type: " + token);
    }
}
