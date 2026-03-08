package wastemanagement;

/**
 * Handles hazardous waste containers — follows strict safety protocols
 * and routes waste to a licensed treatment facility.
 */
public class HazardousWasteCollector extends WasteCollector {

    private static final String TREATMENT_FACILITY = "Regional Hazardous Waste Treatment Plant";
    private static final String SAFETY_PROTOCOL     = "HazMat Protocol Level-2";
    private int processedCount = 0;

    public HazardousWasteCollector() {
        super("Hazardous Waste Collector", WasteType.HAZARDOUS);
    }

    @Override
    protected void collect(WasteContainer container) {
        System.out.printf("  [%s] ⚠ Activating %s.%n", collectorName, SAFETY_PROTOCOL);
        System.out.printf("  [%s] Collecting %.2fL of hazardous waste from container %s with protective gear.%n",
                collectorName, container.getCurrentLevel(), container.getId());
    }

    @Override
    protected void dispose(WasteContainer container) {
        System.out.printf("  [%s] Transporting (sealed) to: %s%n", collectorName, TREATMENT_FACILITY);
        System.out.printf("  [%s] Initiating neutralization/treatment for %.2fL of hazardous material.%n",
                collectorName, container.getCurrentLevel());
        processedCount++;
    }

    public int getProcessedCount() { return processedCount; }
}
