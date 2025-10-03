package app;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {
    private InputStream origIn;
    private PrintStream origOut;
    private ByteArrayOutputStream out;

    @BeforeEach
    void setup() {
        origIn = System.in;
        origOut = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }
    @AfterEach
    void teardown() {
        System.setIn(origIn);
        System.setOut(origOut);
    }

    @Test
    void play_xWins_once_then_quit() {
        String input = String.join("\n",
                "Alice", "Bob",
                "1", "4", "2", "5", "3",
                "n"
        ) + "\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        new Game().play();

        String s = out.toString();
        assertTrue(s.contains("Alice (X) Wins!"));
        assertTrue(s.contains("Current Scoreboard"));
        assertTrue(s.contains("Thanks for playing!"));
    }

    @Test
    void play_draw_then_quit() {
        String input = String.join("\n",
                "A", "B",
                "1", "2", "3", "5", "6", "4", "7", "9", "8",
                "n"
        ) + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        new Game().play();
        String s = out.toString();
        assertTrue(s.contains("It's a draw!"));
        assertTrue(s.contains("Thanks for playing!"));


    }
}
