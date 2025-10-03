package player;

import model.Board;
import model.Mark;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HumanPlayerTest {
    @Test
    void constructor_acceptsValidArgs() {
        Scanner sc = new Scanner(new ByteArrayInputStream("".getBytes()));
        HumanPlayer p = new HumanPlayer("Alice", Mark.X, sc);

        assertEquals("Alice", p.getName());
        assertEquals(Mark.X, p.getMark());
    }
    @Test
    void constructor_rejectsInvalidName() {
        Scanner sc = new Scanner(new ByteArrayInputStream("".getBytes()));
        assertThrows(IllegalArgumentException.class, () -> new HumanPlayer("123", Mark.X, sc));
    }
    @Test
    void chooseCell_returnsValidCell() {
        String input = "5\n";
        Scanner sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
        HumanPlayer p = new HumanPlayer("Bob", Mark.X, sc);
        Board board = new Board();

        int cell = p.chooseCell(board);
        assertEquals(5, cell);
    }

    @Test
    void chooseCell_repromptsUntilValid() {
        String input = "hej\n3\n";
        Scanner sc = new Scanner(new ByteArrayInputStream(input.getBytes()));
        HumanPlayer p = new HumanPlayer("Eve", Mark.X, sc);
        Board board = new Board();

        int cell = p.chooseCell(board);
        assertEquals(3, cell);

    }
}
