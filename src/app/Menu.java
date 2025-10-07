package app;

import ai.Difficulty;
import util.ConsoleColors;
import util.ConsoleUI;
import util.Messages;
import util.NameValidator;

import java.io.Console;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner;

    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void clearScreen() {
        ConsoleUI.clearScreen();
    }

    public Mode askMode() {
        while (true) {
            System.out.println(Messages.PROMPT_MODE_TITLE);
            Mode[] modes = Mode.values();
            for (int i = 0; i < modes.length; i++) {
                System.out.println("  " + (i + 1) + ") " + modes[i].getLabel());
            }
            System.out.printf(Messages.PROMPT_RANGE + "%n", modes.length);

            String raw = scanner.nextLine().trim();
            try {
                int choice = Integer.parseInt(raw);
                if (choice >= 1 && choice <= modes.length) {
                    return modes[choice - 1];
                }
            } catch (NumberFormatException ignored) {}
            ConsoleUI.printInvalidChoice(modes.length);
        }
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

    public PostGameChoice askPostGameChoice() {
        while (true) {
            System.out.println();
            System.out.println(Messages.PROMPT_POSTGAME_TITLE);
            PostGameChoice[] options = PostGameChoice.values();
            for (int i = 0; i < options.length; i++) {
                System.out.println("  " + (i + 1) + ") " + options[i].getLabel());
            }
            System.out.printf(Messages.PROMPT_RANGE, options.length);

            String raw = scanner.nextLine().trim();
            try {
               int choice = Integer.parseInt(raw);
               if (choice >= 1 && choice <= options.length) {
                   return options[choice - 1];
               }
            }
            catch (NumberFormatException ignored) {}
           ConsoleUI.printInvalidChoice(options.length);
        }
    }
}
