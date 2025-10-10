package ai;

import model.Board;
import model.Mark;

/**
 * Interface for AI strategies.
 * Implementations decide which cell to choose for the computer player.
 */
public interface AiStrategy {
    /**
     * Choose a cell on the board for the AI player.
     *
     * @param board the current game board
     * @param aiMark the AI player's mark (X or O)
     * @return the chosen cell number (1..board size^2)
     */
    int chooseCell(Board board, Mark aiMark);
}
