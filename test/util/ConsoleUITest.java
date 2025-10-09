package util;

import model.Mark;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for ConsoleUI – coloring helpers, headings, and choice prompt.
 */
public class ConsoleUITest {
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

    private static Scanner scannerFromLines(final String... lines) {
        final String joined = String.join("\n", lines) + "\n";
        return new Scanner(joined);
    }

    // === Coloring helpers =====================================================

    @Test
    void coloredMark_wrapsXandO_withReset() {
        final String x = ConsoleUI.coloredMark(Mark.X);
        final String o = ConsoleUI.coloredMark(Mark.O);

        assertTrue(x.contains("X"));
        assertTrue(o.contains("O"));
        assertTrue(x.endsWith(ConsoleColors.RESET));
        assertTrue(o.endsWith(ConsoleColors.RESET));
    }

    @Test
    void clearScreen_printsBlankLines() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream old = System.out;
        System.setOut(new PrintStream(out));
        try {
            ConsoleUI.clearScreen();
        } finally {
            System.setOut(old);
        }

        String s = out.toString();

        long blankLines = s.lines().filter(String::isBlank).count();
        assertTrue(blankLines >= 1, "clearScreen() should print at least 1 blank line");    }

    // === askChoice ============================================================

    @Test
    void askChoice_returnsSelectedOption_andPrintsMenu() {
        final String[] options = { "A", "B", "C" };
        final Scanner sc = scannerFromLines("2");

        final String choice = ConsoleUI.askChoice(sc, "Pick:", options, null);
        assertEquals("B", choice);

        final String outStr = stripAnsi(out.toString());
        assertTrue(outStr.contains("Pick:"));
        assertTrue(outStr.contains("1) A"));
        assertTrue(outStr.contains("2) B"));
        assertTrue(outStr.contains("3) C"));
    }

    @Test
    void askChoice_repromptsOnInvalid_thenReturns() {
        final Integer[] options = { 10, 20, 30 };
        final String[] labels = { "Ten", "Twenty", "Thirty" };
        final Scanner sc = scannerFromLines("0", "hej", "3");

        final Integer choice = ConsoleUI.askChoice(sc, "Pick a number:", options, labels);
        assertEquals(30, choice);

        final String outStr = stripAnsi(out.toString());
        assertTrue(outStr.contains(Messages.ERR_INVALID_INPUT));
        assertTrue(outStr.contains("Pick a number:"));
        assertTrue(outStr.contains("1) Ten"));
        assertTrue(outStr.contains("2) Twenty"));
        assertTrue(outStr.contains("3) Thirty"));
    }
}
