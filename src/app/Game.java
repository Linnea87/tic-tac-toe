package app;

import ai.Difficulty;
import ai.HeuristicStrategy;
import ai.MinimaxStrategy;
import ai.RandomStrategy;
import model.Board;
import model.Mark;
import model.Scoreboard;
import player.ComputerPlayer;
import player.HumanPlayer;
import player.Player;
import util.NameValidator;

import java.util.Scanner;

public class Game {
    private final Scanner scanner;
    private Board board;
    private Player p1;
    private Player p2;
    private final Scoreboard scoreboard;

    // 1) Constructor: initialize scanner, players, and board
    public Game() {
        this.scanner = new Scanner(System.in);
        this.board = new Board();
        this.scoreboard = new Scoreboard();
    }

    // 2) Outer game loop: allows multiple rounds until the user quits
    public void play() {
        printWelcome();
        setupPlayers();

        boolean again = true;
        while (again) {
            playOneRound();

            // print scores after the round
            System.out.println("\nCurrent Scoreboard:");
            scoreboard.printScores();

            System.out.println("Play again? (y/n): ");
            String ans = scanner.nextLine().trim().toLowerCase();
            again = ans.startsWith("y");
            if (again) {
                board = new Board(); // reset board for a new round
            }
        }
        System.out.println("Thanks for playing!");
    }

    private void printWelcome() {
        System.out.println("===================================");
        System.out.println("   Welcome to Tic-Tac-Toe!");
        System.out.println(" Two players take turns to play.");
        System.out.println(" Get three in a row to win!");
        System.out.println("===================================\n");
    }

    private void setupPlayers() {
        String nameX = askPlayerName("Enter name for Player X: ");
        this.p1 = new HumanPlayer(nameX, Mark.X, scanner);

        System.out.println("Play against computer? (y/n): ");
        String ans = scanner.nextLine().trim().toLowerCase();

        if (ans.startsWith("y")) {
            Difficulty diff = askDifficulty();

            switch (diff) {
                case EASY -> this.p2 = new ComputerPlayer("CPU-EASY", Mark.O, new RandomStrategy());
                case MEDIUM -> this.p2 = new ComputerPlayer("CPU-MEDIUM", Mark.O, new HeuristicStrategy());
                case HARD ->  this.p2 = new ComputerPlayer("CPU-HARD", Mark.O, new MinimaxStrategy());
                                                        // <-- placeholder for MinimaxStrategy
                default -> this.p2 = new ComputerPlayer("CPU-DEFAULT", Mark.O, new RandomStrategy());
            }
        }
        else {
            String nameO = askPlayerName("Enter name for player O: ");
            this.p2 = new HumanPlayer(nameO, Mark.O, scanner);
        }
    }

    private Difficulty askDifficulty() {
        while (true) {
            System.out.println("Select difficulty (EASY / MEDIUM / HARD): ");
            String raw = scanner.nextLine().trim().toUpperCase();
            try {
               return Difficulty.valueOf(raw);
            }
            catch (IllegalArgumentException ex) {
                System.out.println("Invalid difficulty. Please type EASY, MEDIUM, HARD.");
            }
        }
    }

    private String askPlayerName(String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            try {
                NameValidator.validateLettersOnly(input);
                return input;
            }  catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage() + "Try again.");
            }
        }
    }

    // 3) Play a single round: handle turns, input validation, win/draw conditions
    private void playOneRound() {
        Player current = p1;
        while (true) {
            System.out.println();
            board.printBoard(); // assumes Board has print() method
            System.out.println();

            int cell = current.chooseCell(board);
            if (!board.placeMarkByCell(cell, current.getMark())){
                System.out.println("That cell is not available. Try again.");
                continue;
            }

            // Check if current player has won
            if (board.checkWin(current.getMark())) {
                board.printBoard();
                System.out.println("\n" + current.getName() + " (" + current.getMark() + ") Wins!");
                scoreboard.addWin(current.getName());
                break;
            }

            // Check if the board is full (draw)
            if (board.isFull()) {
                board.printBoard();
                System.out.println("\nIt's a draw!");
                break;
            }

            // Switch player turn
            current =(current == p1) ? p2 : p1;
        }
    }

}
