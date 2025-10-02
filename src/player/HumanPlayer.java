package player;

import model.Board;
import model.Mark;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private final String name;
    private final Mark mark;
    private final Scanner scanner;

    /**
     * Creates a HumanPlayer with name, mark (X or O), and a scanner for input
     * validate that none of the values are null/empty
     */
    public HumanPlayer(String name, Mark mark, Scanner scanner) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Player name must be non-empty");
        }
        if (mark == null) {
            throw new IllegalArgumentException("Mark cannot be non-null");
        }
        if (scanner == null) {
            throw new IllegalArgumentException("Scanner cannot be null");
        }
        this.name = name.trim();
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
     * Input is validated:
     *  - must be an integer
     *  - must be within the valid board of range (1..SIZE*SIZE)
     * Keeps looping until valid input is given.
     *
     * @param board the current game board(for knowing its size)
     * @return a valid cell number (1..SIZE*SIZE
     */
    @Override
    public int chooseCell(Board board) {
        int max = board.getSIZE() * board.getSIZE();
        int cell = -1;

        boolean valid = false;
        while (!valid) {
            System.out.println(name + " (" + mark + "), choose a cell (1-" + max + "): ");
            String raw = scanner.nextLine().trim();
            try {
                cell = Integer.parseInt(raw);
                if (cell >= 1 && cell <= max) {
                    valid = true; // input is valid, exit loop
                }
                else {
                    System.out.println("Out of range. Please enter a number.");
                }
            }
            catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please enter a number.");
            }

        }
        return cell;
    }
}
