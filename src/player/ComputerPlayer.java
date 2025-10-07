package player;

import ai.AiStrategy;
import model.Board;
import model.Mark;
import util.ConsoleUI;
import util.Messages;

/**
 * ComputerPlayer:
 * A player controlled by an AI strategy. The strategy decides which cell to choose
 */
public class ComputerPlayer implements Player {
    private final String name;
    private final Mark mark;
    private final AiStrategy strategy;
    private final boolean thinkingDelay;

    public ComputerPlayer(String name, Mark mark, AiStrategy strategy) {
        this(name, mark, strategy, true);
    }
    /**
     * Create a computer-controlled player
     *
     * @param name     display name, e.g., "CPU-EASY"
     * @param mark     the player's mark (X or O)
     * @param strategy the AI strategy used to pick moves
     * @throws IllegalArgumentException if any argument is invalid
     */
    public ComputerPlayer(String name, Mark mark, AiStrategy strategy, boolean thinkingDelay) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(Messages.ERR_NAME_EMPTY);
        }
        if (mark == null){
            throw new IllegalArgumentException("Mark required");
        }
        if (strategy == null){
            throw new IllegalArgumentException("Strategy required");
        }

        this.name = name.trim();
        this.mark = mark;
        this.strategy = strategy;
        this.thinkingDelay = thinkingDelay;
    }

    @Override
    public String getName() {

        return name;
    }
    @Override
    public Mark getMark() {

        return mark;
    }

    /**
     * Delegate the move decision to the AI strategy
     * @param board current game board
     * @return a valid cell number (1..size^2)
     */
    @Override
    public int chooseCell(Board board) {
        ConsoleUI.printInfo(name + " (" + mark + ") is thinking....");
        if (thinkingDelay) {
            try {
                Thread.sleep(350);
            }
            catch (InterruptedException ignored) {}
        }

        int cell = strategy.chooseCell(board, mark);

        String move = board.formatCell(cell);
        ConsoleUI.printInfo(name + " (" + mark + ") played " + move);
        System.out.println();

        if (thinkingDelay) {
            try {
                Thread.sleep(200);
            }
            catch (InterruptedException ignored) {}
        }

        // Delegates to the chosen AI strategy
        return cell;
    }

}
