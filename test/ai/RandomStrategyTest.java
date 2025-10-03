package ai;

import model.Board;
import model.Mark;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomStrategyTest {
    @Test
    void randomChoosesEmptyCell() {
        Board board = new Board();
        RandomStrategy strat = new RandomStrategy();
        int cell = strat.chooseCell(board, Mark.X);
        assertTrue(cell >= 1 && cell <= board.getSize() * board.getSize());
        assertTrue(board.isCellEmpty(cell));
    }
}
