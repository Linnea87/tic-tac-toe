package ai;

import model.Board;
import model.Mark;

public class MinimaxStrategy implements AiStrategy{
    @Override
    public int chooseCell(Board board, Mark aiMark) {
        int bestScore = Integer.MIN_VALUE;
        int bestMove = 1;

        int cells = board.getSize() * board.getSize();
        for (int c = 1; c <= cells; c++) {
            if (board.isCellEmpty(c)) {
                Board copy = copyBoard(board);
                copy.placeMarkByCell(c, aiMark);

                int score = minimax(copy, false, aiMark);
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = c;
                }
            }
        }
        return bestMove;
    }

    private int minimax(Board board, boolean isMaximizing, Mark aiMark) {
        Mark opponent = (aiMark == Mark.X) ? Mark.O : Mark.X;

        if (board.checkWin(aiMark)) return 10;
        if (board.checkWin(opponent)) return -10;
        if (board.isFull()) return  0;

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        int cells = board.getSize() * board.getSize();
        for (int c = 1; c <= cells; c++) {
            if (board.isCellEmpty(c)) {
                Board copy = copyBoard(board);
                copy.placeMarkByCell(c, isMaximizing ? aiMark : opponent);
                int score = minimax(copy, !isMaximizing, aiMark);
                if (isMaximizing) {
                    bestScore = Math.max(bestScore, score);
                }
                else {
                    bestScore = Math.min(bestScore, score);
                }
            }
        }
        return bestScore;
    }
    private Board copyBoard(Board original) {
        Board copy = new Board();
        int cells = original.getSize() * original.getSize();
        for (int c = 1; c <= cells; c++) {
            Mark m = original.getMarkAtCell(c);
            if (m != Mark.EMPTY) {
                copy.placeMarkByCell(c, m);
            }
        }
        return copy;
    }
}
