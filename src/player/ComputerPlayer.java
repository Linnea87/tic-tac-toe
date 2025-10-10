package player;

import ai.AiStrategy;
import model.Board;
import model.Mark;
import util.ConsoleColors;
import util.ConsoleUI;
import util.Messages;

/**
 * ComputerPlayer – a player controlled by an AI strategy.
 */
public class ComputerPlayer implements Player {

    // === Fields ===============================================================

    private final String name;
    private final Mark mark;
    private final AiStrategy strategy;
    private final boolean thinkingDelay;

    // === Constructors =========================================================

    public ComputerPlayer(String name, Mark mark, AiStrategy strategy) {
        this(name, mark, strategy, true);
    }

    /**
     * @param name          display name (e.g., "Computer (Easy)")
     * @param mark          player's mark (X or O)
     * @param strategy      AI strategy used to pick moves
     * @param thinkingDelay simulate a short "thinking" pause
     */
    public ComputerPlayer(String name, Mark mark, AiStrategy strategy, boolean thinkingDelay) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(Messages.ERR_NAME_EMPTY);
        }
        if (mark == null) {
            throw new IllegalArgumentException(Messages.ERR_MARK_REQUIRED);
        }
        if (strategy == null) {
            throw new IllegalArgumentException(Messages.ERR_STRATEGY_REQUIRED);
        }

        this.name = name.trim();
        this.mark = mark;
        this.strategy = strategy;
        this.thinkingDelay = thinkingDelay;
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
     * Asks the AI strategy for a move, with optional thinking delay and UI output.
     * @return a valid cell number (1..size^2)
     */
    @Override
    public int chooseCell(Board board) {
        String nameC = ConsoleUI.coloredByMark(name, mark);
        String markC = ConsoleUI.coloredMark(mark);

        System.out.println(nameC + " " + markC + ConsoleColors.GRAY + " "+ Messages.MSG_IS_THINKING + ConsoleColors.RESET);
        if (thinkingDelay) { try { Thread.sleep(350); } catch (InterruptedException ignored) {} }

        int cell = strategy.chooseCell(board, mark);

        String move = board.formatCell(cell);
        System.out.println(nameC + " " + markC + ConsoleColors.CYAN + " " + Messages.MSG_PLAYED_MOVE.formatted(move) + ConsoleColors.RESET);
        System.out.println();

        if (thinkingDelay) { try { Thread.sleep(200); } catch (InterruptedException ignored) {} }
        return cell;
    }
}