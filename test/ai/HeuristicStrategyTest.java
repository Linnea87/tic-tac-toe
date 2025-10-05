package ai;

import model.Board;
import model.Mark;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HeuristicStrategyTest {
    @Test
    void chooseWinningMove() {
        Board board = new Board();
        HeuristicStrategy strat = new HeuristicStrategy();

        // Setup: X is about to win on cell 3
        board.placeMarkByCell(1, Mark.X);
        board.placeMarkByCell(2, Mark.X);

        // Expect AI to pick the winning cell
        int move = strat.chooseCell(board, Mark.O);
        assertEquals(3, move, "AI should choose winning move (cell 3)");
    }

    @Test
    void blocksOpponentWin() {
        Board board = new Board();
        HeuristicStrategy strat = new HeuristicStrategy();

        // Setup: X is about to win on cell 9
        board.placeMarkByCell(7, Mark.X);
        board.placeMarkByCell(8, Mark.X);

        // Expect AI to block the opponent
        int move = strat.chooseCell(board, Mark.O);
        assertEquals(9, move, "AI should block opponent's win (cell 9)");
    }

    @Test
    void fallsBackToRandom() {
        Board board = new Board();
        HeuristicStrategy strat = new HeuristicStrategy();

        // Setup: No immediate win or block available
        board.placeMarkByCell(1, Mark.X);
        board.placeMarkByCell(2, Mark.O);

        // Expect AI to pick a valid empty cell as fallback
        int move = strat.chooseCell(board, Mark.O);

        assertTrue(move >= 1 && move <= 9, "Move should be within board range");
        assertTrue(board.isCellEmpty(move), "Move should be an empty cell");
    }
}
