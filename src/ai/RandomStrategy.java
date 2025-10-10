package ai;

import model.Board;
import model.Mark;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * RandomStrategy – a simple AI strategy that chooses a random empty cell.
 */
public class RandomStrategy implements AiStrategy {

    // === Fields ==============================================================

    private final Random rnd = new Random();

    // === Core logic ==========================================================

    @Override
    public int chooseCell(Board board, Mark aiMark) {
        int size = board.getSize();
        int cells = size * size;

        List<Integer> freeCells = new ArrayList<>();
        for (int c = 1; c <= cells; c++) {
            if (board.isCellEmpty(c)) {
                freeCells.add(c);
            }
        }

        return freeCells.get(rnd.nextInt(freeCells.size()));
    }
}