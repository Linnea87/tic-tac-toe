package util;

import model.Board;

public class CellParser {
    private CellParser() {}
    /**
     * Parse chess-style input like "A1", "B2", "C3" to 1..9 based on board size.
     * Column first (A..), then row (1..).
     */
    public static int parse(String input, Board board) {
        if (input == null || input.length() != 2) {
            throw new IllegalArgumentException("Invalid format. Use A1..C3");
        }

        char colChar = Character.toUpperCase(input.charAt(0));
        char rowChar = input.charAt(1);

        int col = switch (colChar) {
            case 'A' -> 0;
            case 'B' -> 1;
            case 'C' -> 2;
            default -> throw new IllegalArgumentException("Invalid column. Use A, B, C.");
        };
        if (rowChar < '1' || rowChar > '3') {
            throw new IllegalArgumentException("Invalid row. Use 1, 2 or 3.");
        }
        int row = rowChar - '1';
        return row * board.getSize() + col + 1;
    }

}
