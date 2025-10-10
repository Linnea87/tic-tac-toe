package app;

/**
 * Mode – defines the available game modes.
 */
public enum Mode {
    HUMAN_VS_HUMAN("Human vs Human"),
    HUMAN_VS_CPU("Human vs Computer");

    // === Fields ===============================================================

    private final String label;

    // === Constructors =========================================================

    Mode(String label) {
        this.label = label;
    }

    // === Accessors ============================================================

    /**
     * Returns a human-friendly label for menus and logging.
     */
    public String getLabel() {
        return label;
    }
}