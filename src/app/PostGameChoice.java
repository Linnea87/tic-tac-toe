package app;

/**
 * PostGameChoice – defines the available options after a finished round.
 */
public enum PostGameChoice {
    REMATCH("Rematch (same players & settings)"),
    CHANGE_OPPONENT("Change opponent (keep Player X)"),
    CHANGE_BOTH("Change both players"),
    QUIT("Quit");

    // === Fields ===============================================================

    private final String label;

    // === Constructors =========================================================

    PostGameChoice(String label) {
        this.label = label;
    }

    // === Accessors ============================================================

    /**
     * Returns a human-friendly label for use in menus and logging.
     */
    public String getLabel() {
        return label;
    }
}