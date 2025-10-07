package util;

/**
 * Small utility fo validating player names.
 * Current rule: non-null, non-blank, letters only (A-Ö).
 */
public class NameValidator {
    private NameValidator() {}

    public static void validateLettersOnly(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Player name must be non-empty");
        }
        String trimmed = name.trim();
        if (trimmed.isBlank()) {
            throw new IllegalArgumentException("Player name must be non-empty");
        }
        boolean lettersOnly = trimmed.codePoints().allMatch(Character::isLetter);
        if (!lettersOnly) {
            throw new IllegalArgumentException("Player name must contain letters only (A-Ö) ");
        }
    }
    public static String formatName(String rawName) {
        if (rawName == null || rawName.isBlank()) {
            return rawName;
        }
        rawName = rawName.trim();
        return rawName.substring(0, 1).toUpperCase() + rawName.substring(1).toLowerCase();
    }


}
