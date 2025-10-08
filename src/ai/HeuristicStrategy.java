package ai;

import model.Board;
import model.Mark;

public class HeuristicStrategy implements AiStrategy{
    private final RandomStrategy fallback = new RandomStrategy();

    @Override
    public int chooseCell(Board board, Mark aiMark) {
        Mark opponent = (aiMark == Mark.X) ? Mark.O : Mark.X;

      int winMove = findWinningMove(board, aiMark);
        if (winMove!= -1) {
            return winMove;
        }

        int blockMove = findWinningMove(board, opponent);
        if (blockMove != -1) {
            return blockMove;
        }
        return fallback.chooseCell(board, aiMark);
    }
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
        Board copy = new Board();
        int cells = original.getSize() * original.getSize();
        for (int c = 1; c <= cells; c++) {
            if (!original.isCellEmpty(c)) {
                Mark m = original.getMarkAtCell(c);
                copy.placeMarkByCell(c, m);
            }
        }
        return copy;
    }
}
