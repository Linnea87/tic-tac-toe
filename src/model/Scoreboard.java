package model;

import java.util.HashMap;
import java.util.Map;

public class Scoreboard {
    private final Map<String, Integer> scores = new HashMap<>();

    // Add one win for the given player (name must be non-empty)
    public void addWin(String playerName) {
        validateName(playerName);
        scores.put(playerName, scores.getOrDefault(playerName, 0) + 1);
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
        if (scores.isEmpty()) {
            System.out.println("No wins yet!");
            return;
        }
        System.out.println("=== Scoreboard ===");
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " wins");
        }
    }

    // Internal guard for input
    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Player name must be non-empty");
        }

    }
}
