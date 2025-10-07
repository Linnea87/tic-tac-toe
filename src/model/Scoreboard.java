package model;

import util.ConsoleColors;
import util.ConsoleUI;
import util.Messages;

import java.util.HashMap;
import java.util.Map;

public class Scoreboard {
    private final Map<String, Integer> scores = new HashMap<>();

    // Add one win for the given player (name must be non-empty)
    public void addWin(String playerName) {
        validateName(playerName);
        scores.put(playerName, scores.getOrDefault(playerName, 0) + 1);
    }

    public void ensurePlayer(String playerName) {
        validateName(playerName);
        scores.putIfAbsent(playerName, 0);
    }

    // Read current wins for a player (0 if unknown)
    public int getWins(String playerName) {
        validateName(playerName);
        return scores.getOrDefault(playerName, 0);
    }

    // Clear all scores
    public void reset() {
        scores.clear();
    }

    // Print all scores
    public void printScores() {
        ConsoleUI.heading(ConsoleColors.CYAN + "ScoreBoard" + ConsoleColors.RESET);
        if (scores.isEmpty()) {
            System.out.println("No results yet");
            return;
        }
        int nameWidth = 6;
        for (String n : scores.keySet()) {
            if (n != null) nameWidth = Math.max(nameWidth, n.length());
        }

        final String header = String.format(
                "%-" + nameWidth + "s  %s",
                ConsoleColors.CYAN + "Player" + ConsoleColors.RESET,
                ConsoleColors.CYAN + "Wins" + ConsoleColors.RESET
        );

        final String rowFormat = "%-" + nameWidth + "s  %d%n" ;
        System.out.println(header);
        System.out.println(ConsoleColors.GRAY + "-".repeat(header.length()) + ConsoleColors.RESET);

        scores.entrySet().stream()
                .sorted((a, b) -> a.getKey().compareToIgnoreCase(b.getKey()))
                .forEach(e -> {
                    String coloredName = colorizeName(e.getKey());
                    System.out.printf(rowFormat, coloredName, e.getKey());
                });
    }

    // Internal guard for input
    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(Messages.ERR_NAME_EMPTY);
        }

    }

    private String colorizeName(String name) {
        if (name.toUpperCase().contains("X")) {
            return ConsoleColors.PURPLE + name + ConsoleColors.RESET;
        }
        else if (name.toUpperCase().contains("O")) {
            return ConsoleColors.YELLOW + name + ConsoleColors.RESET;
        }
        else {
            return name;
        }
    }
}
