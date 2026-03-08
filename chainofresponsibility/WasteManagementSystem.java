package wastemanagement;

import java.util.ArrayList;
import java.util.List;

/**
 * Central orchestrator of the Automated Waste Management System.
 *
 * Responsibilities:
 *  - Builds and maintains the chain of WasteCollector handlers.
 *  - Registers and tracks all WasteContainers in the municipality.
 *  - Triggers collection for individual containers or runs a full sweep.
 *  - Maintains an audit log of all collection events.
 */
public class WasteManagementSystem {

    private final WasteCollector collectionChain;
    private final List<WasteContainer> containers = new ArrayList<>();
    private final List<String> collectionLog = new ArrayList<>();

    public WasteManagementSystem() {
        collectionChain = initializeChain();
        System.out.println("=== Automated Waste Management System Initialized ===");
        System.out.println("Chain: Organic → Recyclable → Hazardous");
        System.out.println("=====================================================\n");
    }

    // ─── Chain Setup ───────────────────────────────────────────────────────────

    /**
     * Builds the chain of responsibility:
     *   OrganicWasteCollector → RecyclableWasteCollector → HazardousWasteCollector
     */
    private WasteCollector initializeChain() {
        WasteCollector organic    = new OrganicWasteCollector();
        WasteCollector recyclable = new RecyclableWasteCollector();
        WasteCollector hazardous  = new HazardousWasteCollector();

        organic.setNext(recyclable).setNext(hazardous);
        return organic;  // head of chain
    }

    // ─── Container Management ──────────────────────────────────────────────────

    /**
     * Registers a container with the system.
     *
     * @param container the WasteContainer to register
     */
    public void registerContainer(WasteContainer container) {
        containers.add(container);
        System.out.printf("Registered: %s%n", container);
    }

    // ─── Collection Triggers ───────────────────────────────────────────────────

    /**
     * Triggers collection for a single container if it needs disposal.
     *
     * @param container the container to process
     */
    public void triggerCollection(WasteContainer container) {
        if (!container.needsDisposal()) {
            System.out.printf("Container %s does not yet need disposal (%.1f%% full). Skipping.%n%n",
                    container.getId(), container.getFillPercentage());
            return;
        }

        String entry = String.format("Triggered: container=%s type=%s fill=%.1f%%",
                container.getId(), container.getWasteType(), container.getFillPercentage());
        collectionLog.add(entry);

        System.out.printf("--- Triggering collection for %s [%.1f%% full] ---%n", 
                container.getId(), container.getFillPercentage());
        collectionChain.handle(container);
    }

    /**
     * Runs a full collection sweep across all registered containers.
     */
    public void runCollection() {
        System.out.println("\n========== RUNNING FULL COLLECTION SWEEP ==========\n");
        for (WasteContainer container : containers) {
            triggerCollection(container);
        }
        System.out.println("========== SWEEP COMPLETE ==========\n");
    }

    // ─── Reporting ─────────────────────────────────────────────────────────────

    /**
     * Prints a summary report to stdout.
     */
    public void generateReport() {
        System.out.println("\n============= COLLECTION REPORT =============");
        System.out.printf("Total containers registered : %d%n", containers.size());
        System.out.printf("Total collection events    : %d%n", collectionLog.size());
        System.out.println("\nEvent Log:");
        collectionLog.forEach(e -> System.out.println("  • " + e));
        System.out.println("=============================================\n");
    }

    public List<String> getCollectionLog() {
        return new ArrayList<>(collectionLog);
    }
}
