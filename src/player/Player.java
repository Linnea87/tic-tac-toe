package player;

import model.Board;
import model.Mark;

public interface Player {
    /**
     * @return the player's name
     */
    String getName();

    /**
     * @return the player's mark (X or O)
     */
    Mark getMark();

    /**
     * Ask the player for the next move and return a cell number.
     * The value must be between 1 and SIZE*SIZE (board dimension squared).
     * The Game class will map this number to a (row, col) position and
     * place the mark on the board.
     *
     * @param board the current game board (used to know the valid range of moves)
     * @return a valid cell number chosen by the player
     */
    int chooseCell(Board board);
}
