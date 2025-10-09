package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for Messages – basic sanity and formatting checks.
 */
public class MessagesTest {

    // === Prompts ==============================================================

    @Test
    void prompts_areNonNull() {
        assertNotNull(Messages.PROMPT_MODE_TITLE);
        assertNotNull(Messages.PROMPT_RANGE);
        assertNotNull(Messages.PROMPT_DIFFICULTY);
        assertNotNull(Messages.PROMPT_PLAYER_NAME);
        assertNotNull(Messages.PROMPT_POSTGAME_TITLE);
    }

    @Test
    void promptRange_formatsCorrectly() {
        final String s = Messages.PROMPT_RANGE.formatted(3);
        assertTrue(s.contains("1-3"));
    }

    // === Errors ===============================================================

    @Test
    void errors_areNonNull() {
        assertNotNull(Messages.ERR_INVALID_INPUT);
        assertNotNull(Messages.ERR_TRY_AGAIN);
        assertNotNull(Messages.ERR_RANGE);
        assertNotNull(Messages.ERR_DIFFICULTY);
        assertNotNull(Messages.ERR_CELL_TAKEN);
        assertNotNull(Messages.ERR_NAME_EMPTY);
        assertNotNull(Messages.ERR_NAME_LETTERS);
        assertNotNull(Messages.ERR_CELL_FORMAT);
        assertNotNull(Messages.ERR_CELL_COLUMN);
        assertNotNull(Messages.ERR_CELL_ROW);
        assertNotNull(Messages.ERR_MARK_REQUIRED);
        assertNotNull(Messages.ERR_STRATEGY_REQUIRED);
        assertNotNull(Messages.ERR_SCANNER_REQUIRED);
    }

    @Test
    void errRange_formatsCorrectly() {
        final String s = Messages.ERR_RANGE.formatted(9);
        assertTrue(s.contains("1-9"));
    }
}
