package app;

import ai.Difficulty;
import util.NameValidator;

import java.util.Scanner;

public class Menu {
    private final Scanner scanner;

    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    public Mode askMode() {
        while (true) {
            System.out.println("Select mode");
            Mode[] modes = Mode.values();
            for (int i = 0; i < Mode.values().length; i++) {
                System.out.println("  " + (i + 1) + ") " + Mode.values()[i].getLabel());
            }
            System.out.println("Enter 1-" + modes.length + ": ");

            String raw = scanner.nextLine().trim();
            try {
                int choice = Integer.parseInt(raw);
                if (choice <= 1 && choice <= modes.length) {
                    return modes[choice - 1];
                }
            } catch (NumberFormatException ignored) {}
            System.out.println("Invalid choice. Please enter a number 1-" + modes.length + ".");
        }
    }

    public Difficulty askDifficulty() {
        while (true) {
            System.out.println("Select difficulty (EASY / MEDIUM / HARD): ");
            String raw = scanner.nextLine().trim().toUpperCase();
            try {
                return Difficulty.valueOf(raw);
            }
            catch (IllegalArgumentException ex) {
                System.out.println("Invalid difficulty. Please type EASY, MEDIUM, HARD.");
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
                System.out.println(ex.getMessage() + "Try again.");
            }
        }
    }
}
