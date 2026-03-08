package wastemanagement;

/**
 * Abstract base class for waste collectors in the Chain of Responsibility.
 *
 * Each concrete collector handles one specific WasteType. If a container's
 * type does not match, the request is passed to the next collector in the chain.
 */
public abstract class WasteCollector {

    protected WasteCollector nextCollector;
    protected final String collectorName;
    protected final WasteType handledWasteType;

    /**
     * @param collectorName   human-readable name for this collector
     * @param handledWasteType the waste type this handler is responsible for
     */
    public WasteCollector(String collectorName, WasteType handledWasteType) {
        this.collectorName = collectorName;
        this.handledWasteType = handledWasteType;
    }

    /**
     * Links this handler to the next handler in the chain.
     *
     * @param nextCollector the next WasteCollector to try
     * @return the next collector (enables fluent chaining)
     */
    public WasteCollector setNext(WasteCollector nextCollector) {
        this.nextCollector = nextCollector;
        return nextCollector;
    }

    /**
     * Handles the container if this collector owns its waste type;
     * otherwise delegates to the next collector in the chain.
     *
     * @param container the container requiring disposal
     * @return true if handled, false if no collector could handle it
     */
    public boolean handle(WasteContainer container) {
        if (container.getWasteType() == handledWasteType) {
            System.out.printf("[%s] Accepting container %s%n", collectorName, container.getId());
            collect(container);
            dispose(container);
            container.reset();
            System.out.printf("[%s] Container %s successfully processed and reset.%n%n",
                    collectorName, container.getId());
            return true;
        }

        if (nextCollector != null) {
            System.out.printf("[%s] Cannot handle %s waste – forwarding to %s.%n",
                    collectorName, container.getWasteType(), nextCollector.getCollectorName());
            return nextCollector.handle(container);
        }

        System.out.printf("[CHAIN] No collector found for waste type: %s%n",
                container.getWasteType());
        return false;
    }

    /**
     * Performs the physical collection of the container.
     *
     * @param container the container to collect
     */
    protected abstract void collect(WasteContainer container);

    /**
     * Disposes of the collected waste appropriately.
     *
     * @param container the container whose waste is being disposed
     */
    protected abstract void dispose(WasteContainer container);

    // ─── Getters ───────────────────────────────────────────────────────────────

    public String getCollectorName()        { return collectorName; }
    public WasteType getHandledWasteType()  { return handledWasteType; }
}
