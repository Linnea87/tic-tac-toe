package player;

import model.Board;
import model.Mark;

/**
 * Player – common contract for human and computer players.
 */
public interface Player {

    // === Accessors ============================================================

    String getName();
    Mark getMark();

    // === Core logic ===========================================================

    int chooseCell(Board board);
}
