package wastemanagement;

/**
 * Represents a physical waste container with a type, capacity, and current fill level.
 * Containers track their own status and signal when disposal is required.
 */
public class WasteContainer {

    private final String id;
    private final WasteType wasteType;
    private final double capacity;   // in liters
    private double currentLevel;     // in liters
    private ContainerStatus status;

    /**
     * Constructs a WasteContainer with a given ID, type, and capacity.
     *
     * @param id        unique identifier for this container
     * @param wasteType type of waste this container holds
     * @param capacity  maximum capacity in liters
     */
    public WasteContainer(String id, WasteType wasteType, double capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity must be positive.");
        this.id = id;
        this.wasteType = wasteType;
        this.capacity = capacity;
        this.currentLevel = 0;
        this.status = ContainerStatus.EMPTY;
    }

    /**
     * Adds waste to the container and updates its status accordingly.
     *
     * @param amount amount of waste to add (in liters)
     */
    public void addWaste(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive.");
        currentLevel = Math.min(currentLevel + amount, capacity);
        updateStatus();
    }

    /**
     * Manually marks the container as needing disposal.
     */
    public void markNeedsDisposal() {
        this.status = ContainerStatus.NEEDS_DISPOSAL;
    }

    /**
     * Resets the container after disposal.
     */
    public void reset() {
        currentLevel = 0;
        status = ContainerStatus.EMPTY;
    }

    /**
     * Returns the fill percentage (0–100).
     */
    public double getFillPercentage() {
        return (currentLevel / capacity) * 100.0;
    }

    /**
     * Returns true if the container is at full capacity.
     */
    public boolean isFull() {
        return currentLevel >= capacity;
    }

    /**
     * Returns true if the container requires disposal (full or flagged).
     */
    public boolean needsDisposal() {
        return status == ContainerStatus.FULL || status == ContainerStatus.NEEDS_DISPOSAL;
    }

    // ─── Private helpers ───────────────────────────────────────────────────────

    private void updateStatus() {
        if (currentLevel == 0) {
            status = ContainerStatus.EMPTY;
        } else if (currentLevel >= capacity) {
            status = ContainerStatus.FULL;
        } else {
            status = ContainerStatus.PARTIAL;
        }
    }

    // ─── Getters ───────────────────────────────────────────────────────────────

    public String getId()                { return id; }
    public WasteType getWasteType()      { return wasteType; }
    public double getCapacity()          { return capacity; }
    public double getCurrentLevel()      { return currentLevel; }
    public ContainerStatus getStatus()   { return status; }

    @Override
    public String toString() {
        return String.format("WasteContainer[id=%s, type=%s, fill=%.1f%%, status=%s]",
                id, wasteType, getFillPercentage(), status);
    }
}
