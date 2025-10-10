package util;

/**
 * NameValidator – utility for validating and formatting player names.
 * Rule: non-null, non-blank, letters only (A–Ö).
 */
public class NameValidator {

    // === Constructors =========================================================

    private NameValidator() {
        // no instances
    }

    // === Validation ===========================================================

    public static void validateLettersOnly(String name) {
        if (name == null) {
            throw new IllegalArgumentException(Messages.ERR_NAME_EMPTY);
        }
        String trimmed = name.trim();
        if (trimmed.isBlank()) {
            throw new IllegalArgumentException(Messages.ERR_NAME_EMPTY);
        }
        boolean lettersOnly = trimmed.codePoints().allMatch(Character::isLetter);
        if (!lettersOnly) {
            throw new IllegalArgumentException(Messages.ERR_NAME_LETTERS);
        }
    }

    // === Formatting ===========================================================

    public static String formatName(String rawName) {
        if (rawName == null || rawName.isBlank()) {
            return rawName;
        }
        rawName = rawName.trim();
        return rawName.substring(0, 1).toUpperCase() + rawName.substring(1).toLowerCase();
    }
}
