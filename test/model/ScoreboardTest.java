package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ScoreboardTest {
    @Test
    public void newScoreboardHasZeroWins() {
        Scoreboard sb = new Scoreboard();
        assertEquals(0, sb.getWins(("Alice")));
        assertEquals(0, sb.getWins(("Bob")));
    }

    @Test
     void addWinIncrementsForThatPlayerOnly() {
        Scoreboard sb = new Scoreboard();
        sb.addWin("Alice");
        sb.addWin("Alice");
        sb.addWin("Bob");

        assertEquals(2, sb.getWins("Alice"));
        assertEquals(1, sb.getWins("Bob"));
        assertEquals(0, sb.getWins("Computer"));
    }
    @Test
    void resetClearsAllWins() {
        Scoreboard sb = new Scoreboard();
        sb.addWin("Alice");
        sb.addWin("Bob");

        sb.reset();
        assertEquals(0, sb.getWins("Alice"));
        assertEquals(0, sb.getWins("Bob"));
    }

    @Test
    void invalidNamesThrow() {
        Scoreboard sb = new Scoreboard();
        assertThrows(IllegalArgumentException.class, () -> sb.addWin(null));
        assertThrows(IllegalArgumentException.class, () -> sb.addWin("   "));
        assertThrows(IllegalArgumentException.class, () -> sb.getWins(""));
    }


}
