package util;

import model.Board;

/**
 * CellParser – converts chess-style input (e.g. "A1") into board cell numbers.
 */
public class CellParser {
    private CellParser() {}

    /**
     * Parses chess-style input like "A1", "B2", "C3" into a 1..9 cell number.
     * Column first (A..), then row (1..).
     *
     * @param input raw user input
     * @param board the current board (for size reference)
     * @return cell number between 1 and size^2
     * @throws IllegalArgumentException if input is invalid
     */
    public static int parse(String input, Board board) {
        if (input == null || input.length() != 2) {
            throw new IllegalArgumentException(Messages.ERR_CELL_FORMAT);
        }

        char colChar = Character.toUpperCase(input.charAt(0));
        char rowChar = input.charAt(1);

        int col = switch (colChar) {
            case 'A' -> 0;
            case 'B' -> 1;
            case 'C' -> 2;
            default -> throw new IllegalArgumentException(Messages.ERR_CELL_COLUMN);
        };

        if (rowChar < '1' || rowChar > '3') {
            throw new IllegalArgumentException(Messages.ERR_CELL_ROW);
        }
        int row = rowChar - '1';
        return row * board.getSize() + col + 1;
    }
}
