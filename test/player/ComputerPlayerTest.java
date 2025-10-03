package player;

import ai.RandomStrategy;
import model.Board;
import model.Mark;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ComputerPlayerTest {
    @Test
    void computerChoosesValidCell() {
        Board board = new Board();
        ComputerPlayer cpu = new ComputerPlayer("CPU", Mark.O, new RandomStrategy());
        int cell = cpu.chooseCell(board);
        assertTrue(cell >= 1 && cell <= 9);
        assertTrue(board.isCellEmpty(cell));
    }
}
