package player;

import model.Board;
import model.Mark;
import util.CellParser;
import util.NameValidator;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private final String name;
    private final Mark mark;
    private final Scanner scanner;

    /**
     * Creates a HumanPlayer with name, mark (X or O), and a scanner for input.
     * Validate that name is letters-only, and that mark/scanner are non-null.
     */
    public HumanPlayer(String name, Mark mark, Scanner scanner) {
        NameValidator.validateLettersOnly(name);
        if (mark == null) {
            throw new IllegalArgumentException("Mark cannot be null");
        }
        if (scanner == null) {
            throw new IllegalArgumentException("Scanner cannot be null");
        }
        this.name = NameValidator.formatName(name);
        this.mark = mark;
        this.scanner = scanner;
    }

    /** @return the player's name */
    @Override
    public String getName() {
        return name;
    }
    /** @return the player's mark (X or O) */
    @Override
    public Mark getMark() {
        return mark;
    }

    /**
     * Ask the human player to choose a cell.
     * Input is validated as chess-style coordinates:
     *  - must be exactly 2 chars
     *  - column letter A..C, then row digit 1..3 (e.g., A1, B2, C3)
     * Keeps looping until valid input is given.
     *
     * @param board the current game board (for size/range)
     * @return a valid cell number in 1..SIZE*SIZE
     */
    @Override
    public int chooseCell(Board board) {
        while (true) {
            System.out.println(name + " (" + mark + "), choose a cell (e.g., A1, B2, C3): ");
            String raw = scanner.nextLine().trim();
            try {
                return CellParser.parse(raw, board);
            }
            catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage() + " Try again.");
            }
        }
    }
}
