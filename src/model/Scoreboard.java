package model;

import util.ConsoleColors;
import util.ConsoleUI;
import util.Messages;

import java.util.HashMap;
import java.util.Map;

/**
 * Scoreboard – tracks wins per player and prints a small table.
 */
public class Scoreboard {

    // === Fields ===============================================================

    private final Map<String, Integer> scores = new HashMap<>();
    private final Map<String, Mark> marksByName = new HashMap<>();

    // === Mutators =============================================================

    public void addWin(String playerName) {
        validateName(playerName);
        scores.put(playerName, scores.getOrDefault(playerName, 0) + 1);
    }

    public void ensurePlayer(String playerName, Mark mark) {
        validateName(playerName);
        scores.putIfAbsent(playerName, 0);
        if (mark != null) {
            marksByName.put(playerName, mark);
        }
    }

    public void reset() {
        scores.clear();
        marksByName.clear();
    }

    // === Accessors ============================================================

    public int getWins(String playerName) {
        validateName(playerName);
        return scores.getOrDefault(playerName, 0);
    }

    // === Rendering ============================================================

    public void printScores() {
        ConsoleUI.heading(ConsoleColors.CYAN + "Scoreboard" + ConsoleColors.RESET);
        if (scores.isEmpty()) {
            System.out.println("No results yet");
            return;
        }

        int tmpWidth = 6;
        for (String n : scores.keySet()) {
            if (n != null) tmpWidth = Math.max(tmpWidth, n.length());
        }

        final int nameWidth = tmpWidth;

        final String header =
                ConsoleColors.CYAN + String.format("%-" + nameWidth + "s", "Player") + ConsoleColors.RESET
                        + "  "
                        + ConsoleColors.CYAN + "Wins" + ConsoleColors.RESET;

        System.out.println(header);
        System.out.println(ConsoleColors.GRAY + "-".repeat(header.length()) + ConsoleColors.RESET);

        scores.entrySet().stream()
                .sorted((a, b) -> a.getKey().compareToIgnoreCase(b.getKey()))
                .forEach(e -> {
                    String rawName = e.getKey();
                    Mark mark = marksByName.get(rawName);
                    String coloredName = ConsoleUI.coloredByMark(rawName, mark);
                    String paddedColoredName = coloredName + " ".repeat(Math.max(0, nameWidth - rawName.length()));
                    System.out.println(paddedColoredName + "   " + e.getValue());
                });
    }

    // === Validation ===========================================================

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(Messages.ERR_NAME_EMPTY);
        }
    }
}