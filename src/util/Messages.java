package util;

/**
 * Messages – central place for all user-facing text.
 */
public class Messages {

    // === Constructors =========================================================

    private Messages() {
        // no instances
    }

    // === Prompts =============================================================

    public static final String PROMPT_MODE_TITLE     = "Select mode:";
    public static final String PROMPT_RANGE          = "Enter 1-%d:";
    public static final String PROMPT_DIFFICULTY     = "Select difficulty (EASY / MEDIUM / HARD):";
    public static final String PROMPT_PLAYER_NAME    = "Enter name for %s:";
    public static final String PROMPT_POSTGAME_TITLE = "What would you like to do?:";
    public static final String PROMPT_BOARD_SIZE     = "Choose board size (3–10):";
    public static final String PROMPT_CHOOSE_CELL    = "Choose a cell (%s):";

    // === Errors ==============================================================

    public static final String ERR_INVALID_INPUT   = "Invalid input.";
    public static final String ERR_TRY_AGAIN       = "Try again!";
    public static final String ERR_RANGE           = "Please enter a number 1-%d.";
    public static final String ERR_DIFFICULTY      = "Please type EASY, MEDIUM, HARD.";
    public static final String ERR_CELL_TAKEN      = "That cell is not available.";
    public static final String ERR_NAME_EMPTY      = "Player name must be non-empty.";
    public static final String ERR_NAME_LETTERS    = "Player name must contain letters only (A–Ö).";
    public static final String ERR_BOARD_SIZE      = "Please enter a number between 3 and 10.";

    // === Cell input / parsing ================================================

    public static final String ERR_CELL_FORMAT      = "Format must be A1–C3.";
    public static final String ERR_CELL_COLUMN      = "Column must be A–%s.";
    public static final String ERR_CELL_ROW         = "Row must be 1–%d.";
    public static final String ERR_MARK_REQUIRED    = "Mark is required.";
    public static final String ERR_STRATEGY_REQUIRED= "Strategy is required.";
    public static final String ERR_SCANNER_REQUIRED = "Scanner is required.";
}