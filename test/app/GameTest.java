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
                "Alice",
                "n",
                "Bob",
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
                "A",
                "n",
                "B",
                "1", "2", "3", "5", "6", "4", "7", "9", "8",
                "n"
        ) + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        new Game().play();
        String s = out.toString();
        assertTrue(s.contains("It's a draw!"));
        assertTrue(s.contains("Thanks for playing!"));
    }

    @Test
    void play_vs_computer__easy_completes_round() {
        String input = String.join("\n",
                "Alice",
                "y",
                "EASY",
                "1","2","3","4","5","6","7","8","9",
                "1","2","3","4","5","6","7","8","9",
                "n"
        ) + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        new Game().play();
        String s = out.toString();
        assertTrue(s.contains("Player O is the Computer (EASY)."));
        assertTrue(s.contains("Thanks for playing!"));

    }

    @Test
    void play_vs_computer_medium_quit_immediately() {
        String input = String.join("\n",
                "Alice",
                "y",
                "MEDIUM",
                "1","2","3","4","5","6","7","8","9",
                "1","2","3","4","5","6","7","8","9",
                "n"
        ) + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        new Game().play();
        String s = out.toString();
        assertTrue(s.contains("Player O is the Computer (MEDIUM)."));
        assertTrue(s.contains("Thanks for playing!"));
    }

    @Test
    void play_vs_computer_Hard_quit_immediately() {
        String input = String.join("\n",
                "Alice",
                "y",
                "Hard",
                "1", "3", "5","7", "9",
                "n"
        ) + "\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));
        new Game().play();
        String s = out.toString();
        assertTrue(s.contains("Player O is the Computer (HARD)."));
        assertTrue(s.contains("Thanks for playing!"));
    }
}
