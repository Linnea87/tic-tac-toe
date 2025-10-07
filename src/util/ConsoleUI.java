package util;

import model.Board;

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
        StringBuilder sb = new StringBuilder(ConsoleColors.RED);
        for (String p : parts) {
            if (p != null && !p.isBlank()) {
                sb.append(p).append(" ");
            }
        }
        sb.append(ConsoleColors.RESET);
        System.out.println(sb.toString().trim());
    }

    public static void printInfo(String... parts) {
        StringBuilder sb = new StringBuilder(ConsoleColors.CYAN);
        for (String p : parts) {
            if (p != null && !p.isBlank()) {
                sb.append(p).append(" ");
            }
        }
        sb.append(ConsoleColors.RESET);
        System.out.println(sb.toString().trim());
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

}
