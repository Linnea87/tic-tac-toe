package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Board – verifies placement, win conditions, and formatting.
 */
public class BoardTest {

    // === Basic State ==========================================================

    @Test
    void newBoard_isNotFull() {
        final Board board = new Board();
        assertFalse(board.isFull(), "A new board should not be full");
    }

    // === Mark Placement =======================================================

    @Test
    void placeMark_onEmptyCell_succeeds() {
        final Board board = new Board();
        final boolean result = board.placeMark(0, 0, Mark.X);
        assertTrue(result, "Should be able to place mark on an empty cell");
    }

    @Test
    void placeMark_onOccupiedCell_fails() {
        final Board board = new Board();
        board.placeMark(0, 0, Mark.X);
        final boolean result = board.placeMark(0, 0, Mark.O);
        assertFalse(result, "Should not be able to place mark on occupied cell");
    }

    @Test
    void placeMark_outOfBounds_fails() {
        final Board board = new Board();
        assertFalse(board.placeMark(-1, 0, Mark.X), "Row -1 is invalid");
        assertFalse(board.placeMark(0, -1, Mark.X), "Col -1 is invalid");
        assertFalse(board.placeMark(board.getSize(), 0, Mark.X), "Row SIZE is invalid");
        assertFalse(board.placeMark(0, board.getSize(), Mark.X), "Col SIZE is invalid");
    }

    // === Win Conditions =======================================================

    @Test
    void win_inRow_detected() {
        final Board board = new Board();
        board.placeMark(0, 0, Mark.X);
        board.placeMark(0, 1, Mark.X);
        board.placeMark(0, 2, Mark.X);
        assertTrue(board.checkWin(Mark.X), "X should win with a row");
    }

    @Test
    void win_inColumn_detected() {
        final Board board = new Board();
        board.placeMark(0, 1, Mark.O);
        board.placeMark(1, 1, Mark.O);
        board.placeMark(2, 1, Mark.O);
        assertTrue(board.checkWin(Mark.O), "O should win with a column");
    }

    @Test
    void win_inMainDiagonal_detected() {
        final Board board = new Board();
        board.placeMark(0, 0, Mark.X);
        board.placeMark(1, 1, Mark.X);
        board.placeMark(2, 2, Mark.X);
        assertTrue(board.checkWin(Mark.X), "X should win on the main diagonal");
    }

    @Test
    void win_inAntiDiagonal_detected() {
        final Board board = new Board();
        board.placeMark(0, 2, Mark.O);
        board.placeMark(1, 1, Mark.O);
        board.placeMark(2, 0, Mark.O);
        assertTrue(board.checkWin(Mark.O), "O should win on the anti-diagonal");
    }

    @Test
    void noWin_detected_whenIncomplete() {
        final Board board = new Board();
        board.placeMark(0, 0, Mark.X);
        board.placeMark(0, 1, Mark.O);
        board.placeMark(1, 1, Mark.X);
        assertFalse(board.checkWin(Mark.X), "X should not have a win yet");
        assertFalse(board.checkWin(Mark.O), "O should not have a win yet");
    }

    // === Board State ==========================================================

    @Test
    void board_becomesFull_afterAllCellsFilled() {
        final Board board = new Board();
        for (int r = 0; r < board.getSize(); r++) {
            for (int c = 0; c < board.getSize(); c++) {
                board.placeMark(r, c, Mark.X);
            }
        }
        assertTrue(board.isFull(), "Board should be full after " + (board.getSize() * board.getSize()) + " moves");
    }

    // === Cell Formatting ======================================================

    @Test
    void formatCell_returnCorrectNotation() {
        final Board board = new Board();
        assertEquals("A1", board.formatCell(1));
        assertEquals("B1", board.formatCell(2));
        assertEquals("C1", board.formatCell(3));
        assertEquals("A2", board.formatCell(4));
        assertEquals("B2", board.formatCell(5));
        assertEquals("C2", board.formatCell(6));
        assertEquals("A3", board.formatCell(7));
        assertEquals("B3", board.formatCell(8));
        assertEquals("C3", board.formatCell(9));
    }

    @Test
    void formatCell_invalidCell_throwsException() {
        final Board board = new Board();
        assertThrows(IllegalArgumentException.class, () -> board.formatCell(0));
        assertThrows(IllegalArgumentException.class, () -> board.formatCell(10));
    }
}


