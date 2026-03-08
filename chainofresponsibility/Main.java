package wastemanagement;

/**
 * Entry point for the Automated Waste Management System demo.
 *
 * Demonstrates:
 *  1. Creating and registering containers of different types.
 *  2. Filling containers to various levels.
 *  3. Triggering the chain of responsibility for disposal.
 *  4. Validating that each container is routed to the correct collector.
 */
public class Main {

    public static void main(String[] args) {

        // ── 1. Initialise the system ─────────────────────────────────────────
        WasteManagementSystem system = new WasteManagementSystem();

        // ── 2. Create containers ─────────────────────────────────────────────
        WasteContainer organicBin    = new WasteContainer("C-001", WasteType.ORGANIC,    200.0);
        WasteContainer recyclableBin = new WasteContainer("C-002", WasteType.RECYCLABLE, 150.0);
        WasteContainer hazardousBin  = new WasteContainer("C-003", WasteType.HAZARDOUS,  100.0);
        WasteContainer partialBin    = new WasteContainer("C-004", WasteType.ORGANIC,    200.0);

        // ── 3. Register containers ───────────────────────────────────────────
        System.out.println("\n--- Registering Containers ---");
        system.registerContainer(organicBin);
        system.registerContainer(recyclableBin);
        system.registerContainer(hazardousBin);
        system.registerContainer(partialBin);

        // ── 4. Fill containers to different levels ───────────────────────────
        System.out.println("\n--- Filling Containers ---");
        organicBin.addWaste(200.0);          // completely full  → needs disposal
        recyclableBin.addWaste(150.0);       // completely full  → needs disposal
        hazardousBin.addWaste(90.0);         // 90% full; mark manually
        hazardousBin.markNeedsDisposal();
        partialBin.addWaste(60.0);           // 30% full         → should be skipped

        System.out.printf("%-8s fill: %.1f%% status: %s%n",
                organicBin.getId(),    organicBin.getFillPercentage(),    organicBin.getStatus());
        System.out.printf("%-8s fill: %.1f%% status: %s%n",
                recyclableBin.getId(), recyclableBin.getFillPercentage(), recyclableBin.getStatus());
        System.out.printf("%-8s fill: %.1f%% status: %s%n",
                hazardousBin.getId(),  hazardousBin.getFillPercentage(),  hazardousBin.getStatus());
        System.out.printf("%-8s fill: %.1f%% status: %s%n",
                partialBin.getId(),    partialBin.getFillPercentage(),    partialBin.getStatus());

        // ── 5. Run full collection sweep ─────────────────────────────────────
        system.runCollection();

        // ── 6. Validate: containers should be reset after disposal ───────────
        System.out.println("--- Post-Collection Validation ---");
        validateReset(organicBin,    true);
        validateReset(recyclableBin, true);
        validateReset(hazardousBin,  true);
        validateReset(partialBin,    false);  // was skipped, so still partial

        // ── 7. Generate summary report ───────────────────────────────────────
        system.generateReport();
    }

    /**
     * Validates that a container has been reset (or intentionally untouched).
     *
     * @param container       the container to check
     * @param expectEmpty  true if the container should have been emptied
     */
    private static void validateReset(WasteContainer container, boolean expectEmpty) {
        boolean isEmpty = container.getStatus() == ContainerStatus.EMPTY;
        boolean pass    = isEmpty == expectEmpty;
        System.out.printf("  [%s] Container %-6s – expected %s, found %s  %s%n",
                pass ? "PASS" : "FAIL",
                container.getId(),
                expectEmpty ? "EMPTY" : "NOT EMPTY",
                container.getStatus(),
                pass ? "✓" : "✗");
    }
}
