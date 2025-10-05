package ai;

import model.Board;
import model.Mark;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MinimaxStrategyTest {
    @Test
    void chooseImmediateWin_asO() {
        Board board = new Board();
        MinimaxStrategy strat = new MinimaxStrategy();

        // Setup:O can win immediately by placing at cell 3
        board.placeMarkByCell(1, Mark.O);
        board.placeMarkByCell(2, Mark.O);

        // Minimax should choose the winning cell 3
        int move = strat.chooseCell(board, Mark.O);
        assertEquals(3, move, "Minimax (O) should take immediate win att cell 3, but chose " + move);
    }

    @Test
    void blocksOpponentWin_asO_behaviorBased() {
        Board board = new Board();
        MinimaxStrategy strat = new MinimaxStrategy();

        // Setup: X threatens to win by placing at cell 9 (X has 7 and 8)
        board.placeMarkByCell(7, Mark.X);
        board.placeMarkByCell(8, Mark.X);

        int move = strat.chooseCell(board, Mark.O);

        // Apply the chosen move and verify X no longer wins immediately
        boolean placed = board.placeMarkByCell(move, Mark.O);
        assertTrue(placed,"Chosen move must be placed on the board");
        assertFalse(board.checkWin(Mark.X), "Minimax (O) should block X's immediate win, but failed. Chosen move was " + move);
    }

    @Test
    void playReasonavleMoveOnEmptyBoard() {
        Board board = new Board();
        MinimaxStrategy strat = new MinimaxStrategy();

        // Empty board: Minimax should choose a valid move
        int move = strat.chooseCell(board, Mark.O);

        assertTrue(move >= 1 && move <= 9, "Move must be inside valid range");
        assertTrue(board.isCellEmpty(move), "Move must be an empty cell");
    }
}
