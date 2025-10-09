package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for NameValidator – verifies name validation rules.
 */
public class NameValidatorTest {

    // === Valid Names ==========================================================

    @Test
    void accepts_validNames() {
        assertDoesNotThrow(() -> NameValidator.validateLettersOnly("Alice"),
                "Should accept normal alphabetic names");
        assertDoesNotThrow(() -> NameValidator.validateLettersOnly("Åsa"),
                "Should accept names with Swedish letters");
    }

    // === Invalid Names ========================================================

    @Test
    void rejects_nullValue() {
        assertThrows(IllegalArgumentException.class,
                () -> NameValidator.validateLettersOnly(null),
                "Null names should throw exception");
    }

    @Test
    void rejects_blankValue() {
        assertThrows(IllegalArgumentException.class,
                () -> NameValidator.validateLettersOnly("  "),
                "Blank names should throw exception");
    }

    @Test
    void rejects_digitsInName() {
        assertThrows(IllegalArgumentException.class,
                () -> NameValidator.validateLettersOnly("Alice123"),
                "Names with digits should throw exception");
    }

    @Test
    void rejects_symbolsInName() {
        assertThrows(IllegalArgumentException.class,
                () -> NameValidator.validateLettersOnly("B@b"),
                "Names with symbols should throw exception");
    }
}
