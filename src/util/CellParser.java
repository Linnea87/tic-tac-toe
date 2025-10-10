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

    public static int parse(String input, Board board) {
        final int size = board.getSize();
        final char lastCol = Grid.getLastColumn(size);

        if (input == null || input.trim().length() < 2) {
            throw new IllegalArgumentException(
                    Messages.ERR_CELL_FORMAT.formatted(Grid.getCoordinateRange(size))
            );
        }

        String normalized = input.trim().toUpperCase();
        char colChar = normalized.charAt(0);
        String rowPart = normalized.substring(1);

        if (colChar < 'A' || colChar > lastCol) {
            throw new IllegalArgumentException(
                    Messages.ERR_CELL_COLUMN.formatted(String.valueOf(lastCol))
            );
        }
        int col = colChar - 'A';

        int row;
        try {
            row = Integer.parseInt(rowPart) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    Messages.ERR_CELL_ROW.formatted(size)
            );
        }

        if (row < 0 || row >= size) {
            throw new IllegalArgumentException(
                    Messages.ERR_CELL_ROW.formatted(size)
            );
        }

        return row * size + col + 1;
    }
}