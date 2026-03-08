package wastemanagement;

/**
 * Enum representing types of waste that containers can hold.
 */
public enum WasteType {
    ORGANIC("Organic Waste"),
    RECYCLABLE("Recyclable Waste"),
    HAZARDOUS("Hazardous Waste");

    private final String displayName;

    WasteType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
