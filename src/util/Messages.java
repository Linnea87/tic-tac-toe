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
    public static final String PROMPT_POSTGAME_TITLE = "What would you like to do?";
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

    public static final String ERR_CELL_FORMAT       = "Format must be %s.";
    public static final String ERR_CELL_COLUMN       = "Column must be A–%s.";
    public static final String ERR_CELL_ROW          = "Row must be 1–%d.";
    public static final String ERR_MARK_REQUIRED     = "Mark is required.";
    public static final String ERR_STRATEGY_REQUIRED = "Strategy is required.";
    public static final String ERR_SCANNER_REQUIRED  = "Scanner is required.";

    // === Game messages ==========================================================

    public static final String SCOREBOARD_TITLE       = "Scoreboard";
    public static final String SCOREBOARD_EMPTY       = "No results yet.";
    public static final String COL_PLAYER             = "Player";
    public static final String COL_WINS               = "Wins";
    public static final String MSG_WINS               = "Wins!";

    public static final String MSG_ROUND_FINISHED     = "Round finished!";
    public static final String MSG_DRAW               = "It's a draw!";
    public static final String MSG_THANKS_FOR_PLAYING = "Thanks for playing!";
    public static final String MSG_IS_THINKING        = "is thinking...";
    public static final String MSG_PLAYED_MOVE        = "played %s";

    public static final String BANNER_LETS_PLAY       = "LET'S PLAY! 🎮";
    public static final String LABEL_PLAYER_X         = "Player X: %s";
    public static final String LABEL_PLAYER_O         = "Player O: %s";
    public static final String SEPARATOR_LINE         = "================================================";

    public static final String WELCOME_TITLE           = "Welcome to Tic-Tac-Toe!";
    public static final String WELCOME_LINE1           = "Two players take turns to play.";
    public static final String WELCOME_LINE2           = "Get three in a row to win!";
    public static final String MOVE_HINT               = "Enter moves as column+row, e.g. %s.";
    public static final String MSG_WIN_CONDITION      = "Win condition: %d in a row on a %dx%d board.";
}