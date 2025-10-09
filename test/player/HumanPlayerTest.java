package player;

import model.Board;
import model.Mark;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests for HumanPlayer – verifies input handling, validation, and cell selection.
 */
public class HumanPlayerTest {

    // === Constructor =========================================================

    @Test
    void constructor_acceptsValidArgs() {
        final Scanner sc = new Scanner(new ByteArrayInputStream("".getBytes()));
        final HumanPlayer p = new HumanPlayer("Alice", Mark.X, sc);

        assertEquals("Alice", p.getName(), "Name should be stored correctly");
        assertEquals(Mark.X, p.getMark(), "Mark should be stored correctly");
    }
    @Test
    void constructor_rejectsInvalidName() {
        final Scanner sc = new Scanner(new ByteArrayInputStream("".getBytes()));
        assertThrows(IllegalArgumentException.class,
                () -> new HumanPlayer("123", Mark.X, sc),
                "Invalid player names should throw an exception");
    }

    // === chooseCell ==========================================================

    @Test
    void chooseCell_returnsValidCell() {
        final String input = "B2\n";
        final Scanner sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
        final HumanPlayer p = new HumanPlayer("Bob", Mark.X, sc);
        final Board board = new Board();

        final int cell = p.chooseCell(board);
        assertEquals(5, cell, "B2 should correspond to cell number 5");
    }

    @Test
    void chooseCell_repromptsUntilValid() {
        final String input = "hej\nC3\n";
        final Scanner sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
        final HumanPlayer p = new HumanPlayer("Eve", Mark.X, sc);
        final Board board = new Board();

        final int cell = p.chooseCell(board);
        assertEquals(9, cell, "After invalid input, player should choose C3 (cell 9)");
    }
}
