package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for ConsoleColors – basic sanity of ANSI constants.
 */
public class ConsoleColorsTest {

    // === Constants ============================================================

    @Test
    void ansiConstants_areNonNullAndNonEmpty() {
        assertNotNull(ConsoleColors.PURPLE);
        assertNotNull(ConsoleColors.YELLOW);
        assertNotNull(ConsoleColors.RESET);
        assertNotNull(ConsoleColors.CYAN);
        assertNotNull(ConsoleColors.GRAY);
        assertNotNull(ConsoleColors.RED);

        assertFalse(ConsoleColors.PURPLE.isEmpty());
        assertFalse(ConsoleColors.RESET.isEmpty());
    }

    @Test
    void ansiConstants_startWithEscapeSequence() {
        assertTrue(ConsoleColors.PURPLE.startsWith("\u001B["));
        assertTrue(ConsoleColors.YELLOW.startsWith("\u001B["));
        assertTrue(ConsoleColors.CYAN.startsWith("\u001B["));
        assertTrue(ConsoleColors.GRAY.startsWith("\u001B["));
        assertTrue(ConsoleColors.RED.startsWith("\u001B["));
        assertEquals("\u001B[0m", ConsoleColors.RESET);
    }
}
