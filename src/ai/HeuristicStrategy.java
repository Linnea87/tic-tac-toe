package ai;

import model.Board;
import model.Mark;

/**
 * HeuristicStrategy – try to win, otherwise block the opponent; fallback to random.
 */
public class HeuristicStrategy implements AiStrategy {

    // === Fields ==============================================================

    private final RandomStrategy fallback = new RandomStrategy();

    // === Core logic ==========================================================

    @Override
    public int chooseCell(Board board, Mark aiMark) {
        Mark opponent = (aiMark == Mark.X) ? Mark.O : Mark.X;

        int winMove = findWinningMove(board, aiMark);
        if (winMove != -1) {
            return winMove;
        }

        int blockMove = findWinningMove(board, opponent);
        if (blockMove != -1) {
            return blockMove;
        }

        return fallback.chooseCell(board, aiMark);
    }

    // === Helpers =============================================================

    private int findWinningMove(Board board, Mark mark) {
        int cells = board.getSize() * board.getSize();
        for (int c = 1; c <= cells; c++) {
            if (board.isCellEmpty(c)) {
                Board copy = copyBoard(board);
                copy.placeMarkByCell(c, mark);
                if (copy.checkWin(mark)) {
                    return c;
                }
            }
        }
        return -1;
    }

    private Board copyBoard(Board original) {
        Board copy = new Board(original.getSize());
        int cells = original.getSize() * original.getSize();
        for (int c = 1; c <= cells; c++) {
            if (!original.isCellEmpty(c)) {
                copy.placeMarkByCell(c, original.getMarkAtCell(c));
            }
        }
        return copy;
    }
}