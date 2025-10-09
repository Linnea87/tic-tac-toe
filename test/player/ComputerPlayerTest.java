package player;

import ai.RandomStrategy;
import model.Board;
import model.Mark;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for ComputerPlayer – ensures AI chooses valid, empty cells.
 */
public class ComputerPlayerTest {

    // === Test ================================================================

    @Test
    void computerChooses_validCell() {
        final Board board = new Board();
        final ComputerPlayer cpu = new ComputerPlayer("CPU", Mark.O, new RandomStrategy());

        final int cell = cpu.chooseCell(board);
        final int maxCell = board.getSize() * board.getSize();

        assertTrue(cell >= 1 && cell <= maxCell, "Chosen cell must be within valid range (1–" + maxCell + ")");
        assertTrue(board.isCellEmpty(cell), "Chosen cell must be empty");
    }
}
