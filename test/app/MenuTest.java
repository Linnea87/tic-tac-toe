package app;

import ai.Difficulty;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Messages;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for Menu – verifies option selection, difficulty parsing, and name input.
 */
public class MenuTest {
    private PrintStream origOut;
    private ByteArrayOutputStream out;

    // === Setup / Teardown =====================================================

    @BeforeEach
    void setUp() {
        origOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void tearDown() {
        System.setOut(origOut);
    }

    // === Helpers ==============================================================

    private static String stripAnsi(final String s) {
        return s.replaceAll("\u001B\\[[;\\d]*m", "");
    }

    private Scanner scannerFromLines(final String... lines) {
        final String joined = String.join("\n", lines) + "\n";
        return new Scanner(joined);
    }

    // === askMode ==============================================================

    @Test
    void askMode_validChoice_returnsHumanVsHuman() {
        final Menu menu = new Menu(scannerFromLines("1"));
        final Mode mode = menu.askMode();
        assertEquals(Mode.HUMAN_VS_HUMAN, mode);
    }

    @Test
    void askMode_repromptsUntilValid_returnsHumanVsCpu() {
        final Menu menu = new Menu(scannerFromLines("abc", "2"));
        final Mode mode = menu.askMode();

        assertEquals(Mode.HUMAN_VS_CPU, mode);

        final String output = stripAnsi(out.toString());
        assertTrue(output.contains(Messages.ERR_INVALID_INPUT), "Should print invalid message");
    }

    // === askDifficulty ========================================================

    @Test
    void askDifficulty_acceptsLowercaseEasy() {
        final Menu menu = new Menu(scannerFromLines("easy"));
        final Difficulty d = menu.askDifficulty();
        assertEquals(Difficulty.EASY, d);
    }

    @Test
    void askDifficulty_acceptsUppercaseHard() {
        final Menu menu = new Menu(scannerFromLines("HARD"));
        final Difficulty d = menu.askDifficulty();
        assertEquals(Difficulty.HARD, d);
    }

    @Test
    void askDifficulty_repromptsOnInvalid_thenReturnsHard() {
        final Menu menu = new Menu(scannerFromLines("nope", "HARD"));
        final Difficulty d = menu.askDifficulty();

        assertEquals(Difficulty.HARD, d);

        final String output = stripAnsi(out.toString());
        assertTrue(output.contains(Messages.ERR_DIFFICULTY), "Should hint valid difficulty values");
    }

    // === askPlayerName ========================================================

    @Test
    void askPlayerName_repromptsUntilValid_thenReturnsTrimmed() {
        final Menu menu = new Menu(scannerFromLines("B0b", "Bob"));
        final String name = menu.askPlayerName("Enter name:");

        assertEquals("Bob", name);
        final String output = stripAnsi(out.toString());
        assertTrue(output.contains(Messages.ERR_NAME_LETTERS), "Should show letters-only validation");
    }

    // === askPostGameChoice ====================================================

    @Test
    void askPostGameChoice_validQuit() {
        final Menu menu = new Menu(scannerFromLines("4"));
        final PostGameChoice choice = menu.askPostGameChoice();
        assertEquals(PostGameChoice.QUIT, choice);
    }

    @Test
    void askPostGameChoice_repromptsUntilValid_thenReturnsChangeBoth() {
        final Menu menu = new Menu(scannerFromLines("0", "3"));
        final PostGameChoice choice = menu.askPostGameChoice();

        assertEquals(PostGameChoice.CHANGE_BOTH, choice);

        final String output = stripAnsi(out.toString());
        assertTrue(output.contains(Messages.ERR_INVALID_INPUT), "Should print invalid input message");
    }
}
