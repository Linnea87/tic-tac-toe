package player;

import model.Board;
import model.Mark;

/**
 * Player – common contract for human and computer players.
 */
public interface Player {
    String getName();
    Mark getMark();

    int chooseCell(Board board);
}
