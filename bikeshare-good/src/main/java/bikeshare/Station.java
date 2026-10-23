package bikeshare;

public enum Station {
    LIBRARY("Library"),
    CANTEEN("Canteen"),
    DORM("Dorm");

    private final String displayName;

    Station(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Station fromToken(String token) {
        for (Station station : values()) {
            if (station.displayName.equalsIgnoreCase(token)) {
                return station;
            }
        }
        throw new IllegalArgumentException("Unknown station: " + token);
    }
}
