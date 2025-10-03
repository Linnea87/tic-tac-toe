package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateNameTest {
    @Test
    void acceptsValidName() {
        assertDoesNotThrow(() -> NameValidator.validateLettersOnly("Alice"));
        assertDoesNotThrow(() -> NameValidator.validateLettersOnly("Åsa"));
    }

    @Test
    void rejectsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> NameValidator.validateLettersOnly(null));
    }

    @Test
    void rejectsBlank() {
        assertThrows(IllegalArgumentException.class,
                () -> NameValidator.validateLettersOnly("  "));
    }

    @Test
    void rejectsDigits() {
        assertThrows(IllegalArgumentException.class,
                () -> NameValidator.validateLettersOnly("Alice123"));
    }

    @Test
    void rejectsSymbols() {
        assertThrows(IllegalArgumentException.class,
                () -> NameValidator.validateLettersOnly("B@b"));
    }
}
