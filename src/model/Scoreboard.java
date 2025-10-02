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

    // Internal guard for input
    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Player name must be non-empty");
        }

    }
}
