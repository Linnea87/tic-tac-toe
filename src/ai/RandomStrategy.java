package ai;

import model.Board;
import model.Mark;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * RandomStrategy:
 * An easy AI strategy that picks a random free cell on the board.
 */
public class RandomStrategy implements AiStrategy {
    private final Random rnd = new Random(); // random number generator

    @Override
    public int chooseCell(Board board, Mark aiMark) {
        int size = board.getSize();
        int cells = size * size;

        // Collect all free cells
        List<Integer> freeCells = new ArrayList<>();
        for (int c = 1; c <= cells; c++) {
            if (board.isCellEmpty(c)) {
                freeCells.add(c);
            }
        }

        // Pick one random cell from the list
        return freeCells.get(rnd.nextInt(freeCells.size()));

    }
}
