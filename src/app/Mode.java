package app;

/**
 * Mode – defines available game modes.
 */
public enum Mode {
    HUMAN_VS_HUMAN("Human vs Human"),
    HUMAN_VS_CPU("Human vs Computer");

    private final String label;
    Mode(String label) {
        this.label = label;
    }

    /**
     * return human-friendly label for menus and logging
     */
    public String getLabel() {
        return label;
    }
}
