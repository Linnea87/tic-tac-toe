package util;

import model.Board;
import model.Mark;

public class ConsoleUI {
    private ConsoleUI() {}

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
}
