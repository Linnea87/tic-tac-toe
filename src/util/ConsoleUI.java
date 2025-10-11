package util;

import model.Mark;
import player.Player;
import java.util.Scanner;

/**
 * ConsoleUI – small helpers for console output:
 * headings, colored messages, common error/info lines,
 * and a generic choice-prompt used by Menu.
 */
public class ConsoleUI {

    // === Constructors =========================================================

    private ConsoleUI() {
        // no instances
    }

    // === Basic output =========================================================

    public static void clearScreen() {
        for (int i = 0; i < 1; i++) {
            System.out.println();
        }
    }

    public static void heading(String title) {
        printSeparator();
        System.out.println(" " + ConsoleColors.CYAN + title + ConsoleColors.RESET);
        printSeparator();
    }

    // === Error & info messages ===============================================

    public static void printError(String... parts) {
        StringBuilder msg = new StringBuilder();
        for (String p : parts) {
            if (p != null && !p.isBlank()) {
                if (msg.length() > 0) msg.append(' ');
                msg.append(p);
            }
        }
        System.out.println(ConsoleColors.RED + msg + ConsoleColors.RESET);
    }

    public static void printInfo(String... parts) {
        StringBuilder msg = new StringBuilder();
        for (String p : parts) {
            if (p != null && !p.isBlank()) {
                if (msg.length() > 0) msg.append(' ');
                msg.append(p);
            }
        }
        System.out.println(ConsoleColors.CYAN + msg + ConsoleColors.RESET);
    }

    public static void printInvalidChoice(int max) {
        printError(
                Messages.ERR_INVALID_INPUT,
                Messages.ERR_RANGE.formatted(max),
                Messages.ERR_TRY_AGAIN
        );
    }

    public static void printDifficultyError() {
        printError(Messages.ERR_INVALID_INPUT, Messages.ERR_DIFFICULTY, Messages.ERR_TRY_AGAIN);
    }

    public static void printCellTaken() {
        printError(Messages.ERR_CELL_TAKEN, Messages.ERR_TRY_AGAIN);
    }

    public static void printNameError(String validatorMessage) {
        printError(validatorMessage, Messages.ERR_TRY_AGAIN);
    }

    // === Coloring helpers ====================================================

    public static String coloredMark(Mark mark) {
        if (mark == null) return "";
        return switch (mark) {
            case X -> ConsoleColors.PURPLE + "X" + ConsoleColors.RESET;
            case O -> ConsoleColors.YELLOW + "O" + ConsoleColors.RESET;
            default -> " ";
        };
    }

    public static String coloredByMark(String text, Mark mark) {
        if (mark == null || text == null) return text;
        String color = switch (mark) {
            case X -> ConsoleColors.PURPLE;
            case O -> ConsoleColors.YELLOW;
            default -> ConsoleColors.RESET;
        };

        return color + text + ConsoleColors.RESET;
    }

    // === Generic choice prompt ==============================================

    /**
     * Prints a numbered list of options and reads a 1-based selection.
     */
    public static <T> T askChoice(Scanner scanner, String title, T[] options, String[] labels) {
        while (true) {
            System.out.println(title);
            for (int i = 0; i < options.length; i++) {
                String label = (labels != null && labels.length == options.length)
                        ? labels[i]
                        : String.valueOf(options[i]);
                System.out.println(" " + (i + 1) + ") " + label);
            }
            System.out.printf(Messages.PROMPT_RANGE + "%n", options.length);

            String raw = scanner.nextLine().trim();
            try {
                int choice = Integer.parseInt(raw);
                if (choice >= 1 && choice <= options.length) {
                    return options[choice - 1];
                }
            } catch (NumberFormatException ignored) {
                // fall through to error printing
            }
            printInvalidChoice(options.length);
        }
    }

    // === Banners & separators ===============================================

    public static void printSeparator() {
        System.out.println(ConsoleColors.GRAY + Messages.SEPARATOR_LINE + ConsoleColors.RESET);
    }

    public static void printStartBanner(Player p1, Player p2) {
        System.out.println();
        printSeparator();

        System.out.println(" " + coloredByMark(Messages.LABEL_PLAYER_X.formatted(p1.getName()), Mark.X));
        System.out.println(" " + coloredByMark(Messages.LABEL_PLAYER_O.formatted(p2.getName()), Mark.O));

        printSeparator();
        System.out.println("                 " + Messages.BANNER_LETS_PLAY);
        printSeparator();
    }

    // === Condition hint ==================================================

    public static void printMoveHint(int boardSize) {
        String range = Grid.getCoordinateRange(boardSize);
        printInfo(" " + Messages.MOVE_HINT.formatted(range));
    }

    public static void printWinConditionHint(int size) {
        printInfo(" " + Messages.MSG_WIN_CONDITION.formatted(size, size, size));
    }

    public static void printPreRoundHints(int boardSize) {
        printWinConditionHint(boardSize);
        printMoveHint(boardSize);
    }
}