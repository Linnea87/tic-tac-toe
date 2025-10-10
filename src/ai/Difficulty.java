package ai;

/**
 * Difficulty levels for the computer opponent
 */
public enum Difficulty {
    EASY,   // Random strategy
    MEDIUM, // Heuristic strategy (block/win/center/corners)
    HARD    // Minimax (optimal)
}
