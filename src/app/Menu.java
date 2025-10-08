package app;

import ai.Difficulty;
import util.ConsoleUI;
import util.Messages;
import util.NameValidator;

import java.util.Scanner;

/**
 * Menu - Handles all pre-/post-game user interactions:
 * mode selection, difficulty, player names, and post-game options.
 */
public class Menu {
    private final Scanner scanner;

    // === Constructors =========================================================

    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    // === Utilities ============================================================

    public void clearScreen() {
        ConsoleUI.clearScreen();
    }

    // === Selections before game ==============================================

    public Mode askMode() {
       Mode[] modes = Mode.values();
       String[] labels = new String[modes.length];
       for (int i = 0; i < modes.length; i++) {
           labels[i] = modes[i].getLabel();
       }
       return ConsoleUI.askChoice(scanner, Messages.PROMPT_MODE_TITLE, modes, labels);
    }

    public Difficulty askDifficulty() {
        while (true) {
            System.out.println(Messages.PROMPT_DIFFICULTY);
            String raw = scanner.nextLine().trim().toUpperCase();
            try {
                return Difficulty.valueOf(raw);
            }
            catch (IllegalArgumentException ex) {
                ConsoleUI.printDifficultyError();
            }
        }
    }

    public String askPlayerName(String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            try {
                NameValidator.validateLettersOnly(input);
                return input;
            }  catch (IllegalArgumentException ex) {
                ConsoleUI.printNameError(ex.getMessage());
            }
        }
    }

    // === Post-game selection ==================================================

    public PostGameChoice askPostGameChoice() {
        PostGameChoice[] options = PostGameChoice.values();
        String[] labels = new String[options.length];
        for (int i = 0; i < options.length; i++) {
            labels[i] = options[i].getLabel();
        }
        return ConsoleUI.askChoice(scanner, Messages.PROMPT_POSTGAME_TITLE, options, labels);
    }
}
