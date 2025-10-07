package util;

/**
 * Central place for all user-facing text messages.
 * Keeps prompts and errors in one place for reuse.
 */
public class Messages {
    private Messages() {} // instantiation

    // ==== Prompts ====
    public static final String PROMPT_MODE_TITLE = "Select mode";
    public static final String PROMPT_RANGE = "Enter 1-%d ";
    public static final String PROMPT_DIFFICULTY = "Select difficulty (EASY / MEDIUM / HARD): ";
    public static final String PROMPT_PLAYER_NAME = "Enter name for %s ";
    public static final String PROMPT_POSTGAME_TITLE = "What would you like to do?:";

    // ==== Errors ====
    public static final String ERR_INVALID_INPUT = "Invalid input. Try again.";
    public static final String ERR_TRY_AGAIN = "Try again.";
    public static final String ERR_RANGE         = "Please enter a number 1-%d.";
    public static final String ERR_DIFFICULTY    = "Please type EASY, MEDIUM, HARD.";
    public static final String ERR_CELL_TAKEN    = "That cell is not available.";
    public static final String ERR_NAME_EMPTY    = "Player name must be non-empty.";
    public static final String ERR_NAME_LETTERS  = "Player name must contain letters only (A–Ö).";

}
