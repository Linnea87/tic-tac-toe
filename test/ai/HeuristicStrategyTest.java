package ai;

import model.Board;
import model.Mark;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for HeuristicStrategy – verifies win, block, and fallback logic.
 */
public class HeuristicStrategyTest {

    // === Winning Move Logic ===================================================

    @Test
    void chooseWinningMove_detected() {
        final Board board = new Board();
        final HeuristicStrategy strat = new HeuristicStrategy();

        // AI can win with the cell 3
        board.placeMarkByCell(1, Mark.O);
        board.placeMarkByCell(2, Mark.O);

        final int move = strat.chooseCell(board, Mark.O);
        assertEquals(3, move, "AI should choose winning move (cell 3)");
    }

    // === Blocking Logic =======================================================

    @Test
    void blocksOpponentWin_detected() {
       final Board board = new Board();
        final HeuristicStrategy strat = new HeuristicStrategy();

        // X threatens to win with cell 9 → AI should block
        board.placeMarkByCell(7, Mark.X);
        board.placeMarkByCell(8, Mark.X);

        final int move = strat.chooseCell(board, Mark.O);
        assertEquals(9, move, "AI should block opponent's win (cell 9)");
    }

    // === Fallback Logic =======================================================

    @Test
    void fallsBackToRandom_moveIsValid() {
       final Board board = new Board();
       final HeuristicStrategy strat = new HeuristicStrategy();

        // No immediate win or block available
        board.placeMarkByCell(1, Mark.X);
        board.placeMarkByCell(2, Mark.O);

        final int move = strat.chooseCell(board, Mark.O);
        assertTrue(move >= 1 && move <= board.getSize() * board.getSize(), "Move should be within board range");
        assertTrue(board.isCellEmpty(move), "Move should be an empty cell");
    }
}
