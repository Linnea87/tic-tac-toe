package util;


import model.Board;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for CellParser – converts chess-style input to cell numbers.
 */
public class CellParserTest {
    // === Happy path ===========================================================

    @Test
    void parse_validInputs_mapToExpectedCells() {
        final Board board = new Board();
        assertEquals(1, CellParser.parse("A1", board));
        assertEquals(2, CellParser.parse("B1", board));
        assertEquals(3, CellParser.parse("C1", board));
        assertEquals(5, CellParser.parse("B2", board));
        assertEquals(9, CellParser.parse("C3", board));
    }

    @Test
    void parse_isCaseInsensitiveForColumn() {
        final Board board = new Board();
        assertEquals(1, CellParser.parse("a1", board));
        assertEquals(5, CellParser.parse("b2", board));
    }

    // === Validation ===========================================================

    @Test
    void parse_rejectsWrongLengthOrNull() {
        final Board board = new Board();
        assertThrows(IllegalArgumentException.class, () -> CellParser.parse(null, board));
        assertThrows(IllegalArgumentException.class, () -> CellParser.parse("", board));
        assertThrows(IllegalArgumentException.class, () -> CellParser.parse("A10", board));
        assertThrows(IllegalArgumentException.class, () -> CellParser.parse("A", board));
    }

    @Test
    void parse_rejectsInvalidColumn() {
        final Board board = new Board();
        assertThrows(IllegalArgumentException.class, () -> CellParser.parse("D1", board));
        assertThrows(IllegalArgumentException.class, () -> CellParser.parse("Z3", board));
        assertThrows(IllegalArgumentException.class, () -> CellParser.parse("11", board));
    }

    @Test
    void parse_rejectsInvalidRow() {
        final Board board = new Board();
        assertThrows(IllegalArgumentException.class, () -> CellParser.parse("A0", board));
        assertThrows(IllegalArgumentException.class, () -> CellParser.parse("B9", board));
        assertThrows(IllegalArgumentException.class, () -> CellParser.parse("C-", board));
    }
}
