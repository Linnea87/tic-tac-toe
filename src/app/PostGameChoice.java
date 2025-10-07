package app;

/**
 * What to do after a finished round
 */
public enum PostGameChoice {
    REMATCH ("Rematch (same players & settings)"),
    CHANGE_OPPONENT ("Change opponent (keep Player X)"),
    CHANGE_BOTH ("Change both players"),
    QUIT ("Quit");

    private final String label;
    PostGameChoice(String label) {
        this.label = label;
    }
    public String getLabel() {
        return label;
    }

}
