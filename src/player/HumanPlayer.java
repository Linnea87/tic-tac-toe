package player;

import model.Board;
import model.Mark;
import util.*;

import java.util.Scanner;

/**
 * HumanPlayer – represents a player controlled by user input.
 */
public class HumanPlayer implements Player {

    // === Fields ===============================================================

    private final String name;
    private final Mark mark;
    private final Scanner scanner;

    // === Constructors =========================================================

    /**
     * @param name    player's name (letters only, will be validated)
     * @param mark    player's mark (X or O)
     * @param scanner input source for moves
     */
    public HumanPlayer(String name, Mark mark, Scanner scanner) {
        NameValidator.validateLettersOnly(name);
        if (mark == null) {
            throw new IllegalArgumentException(Messages.ERR_MARK_REQUIRED);
        }
        if (scanner == null) {
            throw new IllegalArgumentException(Messages.ERR_SCANNER_REQUIRED);
        }
        this.name = NameValidator.formatName(name);
        this.mark = mark;
        this.scanner = scanner;
    }

    // === Accessors ============================================================

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Mark getMark() {
        return mark;
    }

    // === Turn logic ===========================================================

    /**
     * Prompts the player for a move, validates it, and returns the chosen cell.
     */
    @Override
    public int chooseCell(Board board) {
        while (true) {
            String nameColored = ConsoleUI.coloredByMark(name, mark);
            String markColored = ConsoleUI.coloredMark(mark);

            String hint = Grid.getCoordinateRange(board.getSize());
            System.out.println(
                    nameColored + " " + markColored + " " +
                            ConsoleColors.GRAY + Messages.PROMPT_CHOOSE_CELL.formatted(hint) + ConsoleColors.RESET
            );

            String raw = scanner.nextLine().trim();
            try {
                return CellParser.parse(raw, board);
            } catch (IllegalArgumentException ex) {
                ConsoleUI.printError(Messages.ERR_INVALID_INPUT, ex.getMessage(), Messages.ERR_TRY_AGAIN);
            }
        }
    }

}