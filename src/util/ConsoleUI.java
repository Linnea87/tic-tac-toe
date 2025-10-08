package util;
import model.Mark;

import java.util.Scanner;

/**
 * ConsoleUI – small helpers for console output:
 * headings, colored messages, common error/info lines,
 * and a generic choice-prompt used by Menu.
 */
public class ConsoleUI {
    private ConsoleUI() {}

    // === Basics ===============================================================

    public static void clearScreen() {
        for (int i = 0; i < 3; i++) {
            System.out.println();
        }
    }

    public static void heading(String title) {
        System.out.println("===================================");
        System.out.println(" " + title);
        System.out.println("===================================");
    }

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

    // === Coloring helpers =====================================================

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

    // === Generic choice helper ===============================================

    /**
     * Prints a numbered list of options and reads a 1-based selection.
     *
     * @param scanner input source
     * @param title   heading shown above options
     * @param options values to choose from (returned object)
     * @param labels  optional labels matching options; if null uses toString()
     * @return the selected option
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
            }
            catch (NumberFormatException ignored) {
                // fall through to error printing
            }
            printInvalidChoice(options.length);
        }
    }
}
