package ai;

import model.Board;
import model.Mark;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for RandomStrategy – ensures valid and empty cell selection.
 */
public class RandomStrategyTest {

    // === Random Move Logic ====================================================

    @Test
    void randomChooses_emptyCell_validRange() {
        final Board board = new Board();
        final RandomStrategy strat = new RandomStrategy();

        final int cell = strat.chooseCell(board, Mark.X);
        final int maxCell = board.getSize() * board.getSize();

        assertTrue(cell >= 1 && cell <= maxCell,
                "Chosen cell must be within board range (1–" + maxCell + ")");

        assertTrue(board.isCellEmpty(cell), "Chosen cell must be empty");
    }
}
