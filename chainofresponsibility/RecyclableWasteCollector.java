package wastemanagement;

/**
 * Handles recyclable waste containers — routes collected waste to a recycling centre.
 */
public class RecyclableWasteCollector extends WasteCollector {

    private static final String RECYCLING_CENTER = "Metro Recycling Centre – Building 3";
    private int processedCount = 0;

    public RecyclableWasteCollector() {
        super("Recyclable Waste Collector", WasteType.RECYCLABLE);
    }

    @Override
    protected void collect(WasteContainer container) {
        System.out.printf("  [%s] Collecting %.2fL of recyclable waste from container %s.%n",
                collectorName, container.getCurrentLevel(), container.getId());
    }

    @Override
    protected void dispose(WasteContainer container) {
        System.out.printf("  [%s] Transporting to: %s%n", collectorName, RECYCLING_CENTER);
        System.out.printf("  [%s] Sorting and processing %.2fL of recyclable material.%n",
                collectorName, container.getCurrentLevel());
        processedCount++;
    }

    public int getProcessedCount() { return processedCount; }
}
