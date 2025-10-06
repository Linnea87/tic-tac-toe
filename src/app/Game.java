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
    private final boolean thinkingDelay;
    private final Menu menu;

    public Game() {
        this(true);
    }
    // 1) Constructor: initialize scanner, players, and board
    public Game(boolean thinkingDelay) {
        this.scanner = new Scanner(System.in);
        this.board = new Board();
        this.scoreboard = new Scoreboard();
        this.thinkingDelay = thinkingDelay;
        this.menu = new Menu(this.scanner);

    }

    // 2) Outer game loop: allows multiple rounds until the user quits
    public void play() {
        printWelcome();

        boolean again = true;
        while (again) {
            setupPlayers();
            board = new Board();
            playOneRound();

            // print scores after the round
            System.out.println("\nCurrent Scoreboard:");
            scoreboard.printScores();

            System.out.println("Play again? (y/n): ");
            String ans = scanner.nextLine().trim().toLowerCase();
            again = ans.startsWith("y");
        }
        System.out.println("Thanks for playing!");
    }

    private void printWelcome() {
        System.out.println("===================================");
        System.out.println("   Welcome to Tic-Tac-Toe!");
        System.out.println(" Two players take turns to play.");
        System.out.println(" Get three in a row to win!");
        System.out.println();
        System.out.println(" Enter moves as column+row, e.g. A1, B2, C3.");
        System.out.println("===================================\n");
    }

    private void setupPlayers() {
        Mode mode = menu.askMode();
        String nameX = menu.askPlayerName("Enter name for Player X: ");
        this.p1 = new HumanPlayer(nameX, Mark.X, scanner);

        if (mode == Mode.HUMAN_VS_CPU) {
            Difficulty diff = menu.askDifficulty();
            switch (diff) {
                case EASY -> this.p2 = new ComputerPlayer("CPU-EASY", Mark.O, new RandomStrategy(), thinkingDelay);
                case MEDIUM -> this.p2 = new ComputerPlayer("CPU-MEDIUM", Mark.O, new HeuristicStrategy(), thinkingDelay);
                case HARD ->  this.p2 = new ComputerPlayer("CPU-HARD", Mark.O, new MinimaxStrategy(), thinkingDelay);
                default -> this.p2 = new ComputerPlayer("CPU-DEFAULT", Mark.O, new RandomStrategy(), thinkingDelay);
            }
           System.out.println("Player O is the Computer (" + diff + ").");
        }
        else {
           String nameO = menu.askPlayerName("Enter name for player O: ");
           this.p2 = new HumanPlayer(nameO, Mark.O, scanner);
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
