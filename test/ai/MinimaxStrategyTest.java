package ai;

import model.Board;
import model.Mark;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for MinimaxStrategy – verifies win, block, and valid move selection.
 */
public class MinimaxStrategyTest {

    // === Winning Move Logic ===================================================

    @Test
    void chooseWinningMove_asO_detected() {
       final Board board = new Board();
       final MinimaxStrategy strat = new MinimaxStrategy();

        // AI can win immediately by placing at cell 3
        board.placeMarkByCell(1, Mark.O);
        board.placeMarkByCell(2, Mark.O);

        final int move = strat.chooseCell(board, Mark.O);

        assertEquals(3, move, "Minimax (O) should take immediate win at cell 3, but chose " + move);
    }

    // === Blocking Logic =======================================================

    @Test
    void blocksOpponentWin_asO_detected() {
        final Board board = new Board();
        final MinimaxStrategy strat = new MinimaxStrategy();

        // X threatens to win by placing at cell 9 (X has 7 and 8)
        board.placeMarkByCell(7, Mark.X);
        board.placeMarkByCell(8, Mark.X);

        final int move = strat.chooseCell(board, Mark.O);

        // Apply the chosen move and verify X no longer wins immediately
        final boolean placed = board.placeMarkByCell(move, Mark.O);

        assertTrue(placed,"Chosen move must be placed on the board");
        assertFalse(board.checkWin(Mark.X),
                "Minimax (O) should block X's immediate win, but failed. Chosen move was " + move);
    }

    // === Fallback Logic =======================================================

    @Test
    void playReasonableMove_onEmptyBoard_validMove() {
        final Board board = new Board();
        final MinimaxStrategy strat = new MinimaxStrategy();

        // Empty board: AI should choose a valid move
        final int move = strat.chooseCell(board, Mark.O);
        final int maxCell = board.getSize() * board.getSize();

        assertTrue(move >= 1 && move <= maxCell, "Move must be inside valid range");
        assertTrue(board.isCellEmpty(move), "Move must be an empty cell");
    }
}
