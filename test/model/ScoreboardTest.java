package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Tests for Scoreboard – verifies win tracking, reset behavior, and validation.
 */
public class ScoreboardTest {

    // === Initialization =======================================================

    @Test
    void newScoreboard_hasZeroWins() {
        final Scoreboard sb = new Scoreboard();
        assertEquals(0, sb.getWins("Alice"));
        assertEquals(0, sb.getWins("Bob"));
    }
    // === Win Tracking =========================================================

    @Test
     void addWin_incrementsForThatPlayerOnly() {
        final Scoreboard sb = new Scoreboard();
        sb.addWin("Alice");
        sb.addWin("Alice");
        sb.addWin("Bob");

        assertEquals(2, sb.getWins("Alice"));
        assertEquals(1, sb.getWins("Bob"));
        assertEquals(0, sb.getWins("Computer"));
    }

    // === Reset ================================================================

    @Test
    void reset_clearsAllWins() {
        final Scoreboard sb = new Scoreboard();
        sb.addWin("Alice");
        sb.addWin("Bob");

        sb.reset();
        assertEquals(0, sb.getWins("Alice"));
        assertEquals(0, sb.getWins("Bob"));
    }

    // === Validation ===========================================================

    @Test
    void invalidNames_throwException() {
        final Scoreboard sb = new Scoreboard();
        assertThrows(IllegalArgumentException.class, () -> sb.addWin(null));
        assertThrows(IllegalArgumentException.class, () -> sb.addWin("   "));
        assertThrows(IllegalArgumentException.class, () -> sb.getWins(""));
    }


}
