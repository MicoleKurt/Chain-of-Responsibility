package wastemanagement;

/**
 * Handles organic waste containers — routes collected waste to a composting facility.
 */
public class OrganicWasteCollector extends WasteCollector {

    private static final String COMPOSTING_FACILITY = "City Composting Center – Zone A";
    private int processedCount = 0;

    public OrganicWasteCollector() {
        super("Organic Waste Collector", WasteType.ORGANIC);
    }

    @Override
    protected void collect(WasteContainer container) {
        System.out.printf("  [%s] Collecting %.2fL of organic waste from container %s.%n",
                collectorName, container.getCurrentLevel(), container.getId());
    }

    @Override
    protected void dispose(WasteContainer container) {
        System.out.printf("  [%s] Transporting to: %s%n", collectorName, COMPOSTING_FACILITY);
        System.out.printf("  [%s] Initiating composting process for %.2fL of organic waste.%n",
                collectorName, container.getCurrentLevel());
        processedCount++;
    }

    public int getProcessedCount() { return processedCount; }
}
