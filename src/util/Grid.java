package util;

/**
 * Grid – helper methods for board coordinate calculations.
 * Centralizes repeated logic so it's reusable across Board, CellParser, etc.
 */
public class Grid {

    // === Constructors =========================================================

    private Grid() {
        // no instances
    }

    // === Helpers ==============================================================

    public static char getLastColumn(int size) {
        return (char) ('A' + size - 1);
    }
    public static String getCoordinateRange(int size) {
        return "A1–" + getLastColumn(size) + size;
    }
}
