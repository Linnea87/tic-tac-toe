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
                "A1", "A2", "B1", "B2", "C1",
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
                "A1", "B1", "C1", "B2","C2", "A2", "A3", "C3", "B3",
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
                "A1","B1","C1","A2","B2","C2","A3","B3","C3",
                "A1","B1","C1","A2","B2","C2","A3","B3","C3",
                "n"
        ) + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        new Game(false).play();

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
                "A1","B1","C1","A2","B2","C2","A3","B3","C3",
                "A1","B1","C1","A2","B2","C2","A3","B3","C3",
                "n"
        ) + "\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        new Game(false).play();

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
                "A1", "C1", "B2","A3", "C3",
                "n"
        ) + "\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));
        new Game(false).play();

        String s = out.toString();
        assertTrue(s.contains("Player O is the Computer (HARD)."));
        assertTrue(s.contains("Thanks for playing!"));
    }
}
