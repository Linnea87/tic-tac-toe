package app;

/**
 * Game mode selection
 */
public enum Mode {
    HUMAN_VS_HUMAN("Human vs Human"),
    HUMAN_VS_CPU("Human vs Computer");

    private final String label;
    Mode(String label) {
        this.label = label;
    }

    /**
     * @return human-friendly label for menus/logging
     */
    public String getLabel() {
        return label;
    }
}
