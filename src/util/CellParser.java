package util;

import model.Board;

/**
 * CellParser – converts chess-style input (e.g. "A1") into board cell numbers.
 */
public class CellParser {

    // === Constructors =========================================================

    private CellParser() {
        // no instances
    }

    // === Core logic ===========================================================

    /**
     * Parses chess-style input like "A1" or "J10" into a cell number.
     */
    public static int parse(String input, Board board) {
        if (input == null || input.length() < 2) {
            throw new IllegalArgumentException(Messages.ERR_CELL_FORMAT);
        }
        input = input.trim().toUpperCase();
        char colChar = input.charAt(0);
        String rowPart = input.substring(1);

        int size = board.getSize();

        if (colChar < 'A' || colChar > (char) ('A' + size - 1)) {
            char lastCol = (char) ('A' + size - 1);
            throw new IllegalArgumentException(Messages.ERR_CELL_COLUMN.formatted(lastCol));
        }
        int col = colChar - 'A';

        int row;
        try {
            row = Integer.parseInt(rowPart) - 1;
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException(Messages.ERR_CELL_ROW.formatted(size));
        }
        if (row < 0 || row >= size) {
            throw new IllegalArgumentException(Messages.ERR_CELL_ROW.formatted(size));
        }
       return row * size + col + 1;
    }
}
