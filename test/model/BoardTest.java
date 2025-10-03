package model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class BoardTest {
    @Test
    void testNewBoardIsNotFull() {
        Board board = new Board();
        assertFalse(board.isFull(), "A new board should not be full");
    }

    @Test
    void testPlaceMarkOnEmptyCell() {
        Board board = new Board();
        boolean result = board.placeMark(0, 0, Mark.X);
        assertTrue(result, "Should be able to place mark on an empty cell");
    }
    @Test
    void testPlaceMarkOnOccupiedCell() {
        Board board = new Board();
        board.placeMark(0, 0, Mark.X);
        boolean result = board.placeMark(0, 0, Mark.O);
        assertFalse(result, "Should not be able to place mark on occupied cell");
    }

    @Test
    void testWinInRow() {
        Board board = new Board();
        board.placeMark(0, 0, Mark.X);
        board.placeMark(0, 1, Mark.X);
        board.placeMark(0, 2, Mark.X);
        assertTrue(board.checkWin(Mark.X), "X should win with a row");
    }
    @Test
    void testWinInColumn() {
        Board board = new Board();
        board.placeMark(0, 1, Mark.O);
        board.placeMark(1, 1, Mark.O);
        board.placeMark(2, 1, Mark.O);
        assertTrue(board.checkWin(Mark.O), "O should win with a column");
    }
    @Test
    void testWinInMainDiagonal() {
        Board board = new Board();
        board.placeMark(0, 0, Mark.X);
        board.placeMark(1, 1, Mark.X);
        board.placeMark(2, 2, Mark.X);
        assertTrue(board.checkWin(Mark.X), "X should win on the main diagonal");
    }
    @Test
    void testWinAntiDiagonal() {
        Board board = new Board();
        board.placeMark(0, 2, Mark.O);
        board.placeMark(1, 1, Mark.O);
        board.placeMark(2, 0, Mark.O);
        assertTrue(board.checkWin(Mark.O), "O should win on the anti diagonal");
    }
    @Test
    void testBoardIsFull() {
        Board board = new Board();
        for (int r = 0; r < board.getSize(); r++) {
            for (int c = 0; c < board.getSize(); c++) {
                board.placeMark(r, c, Mark.X);
            }
        }
        assertTrue(board.isFull(), "Board should be full after " + (board.getSize() * board.getSize()) + " moves");
    }

    @Test
    void testPlaceMarkOutOfBounds() {
        Board board = new Board();
        assertFalse(board.placeMark(-1, 0, Mark.X), "Row -1 is invalid");
        assertFalse(board.placeMark(0, -1, Mark.X), "Col -1 is invalid");
        assertFalse(board.placeMark( board.getSize(), 0, Mark.X), "Row SIZE is invalid");
        assertFalse(board.placeMark(0, board.getSize(), Mark.X), "Col SIZE is invalid");
    }

    @Test
    void testNoWinYet() {
        Board board = new Board();
        board.placeMark(0, 0, Mark.X);
        board.placeMark(0, 1, Mark.O);
        board.placeMark(1, 1, Mark.X);
        assertFalse(board.checkWin(Mark.X), "X should not have a win yet");
        assertFalse(board.checkWin(Mark.O), "O should not have a win yet");
    }



}


