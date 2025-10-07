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
import util.ConsoleUI;
import util.Messages;

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
    // Constructor: initialize scanner, players, and board
    public Game(boolean thinkingDelay) {
        this.scanner = new Scanner(System.in);
        this.board = new Board();
        this.scoreboard = new Scoreboard();
        this.thinkingDelay = thinkingDelay;
        this.menu = new Menu(this.scanner);

    }

    // Outer game loop: allows multiple rounds until the user quits
    public void play() {
        printWelcome();
        setupPlayers();

        boolean running = true;
        while (running) {
            ConsoleUI.clearScreen();
            board = new Board();
            playOneRound();

            // print scores after the round
            System.out.println("\nRound finished!\n");
            scoreboard.printScores();

            PostGameChoice choice = menu.askPostGameChoice();
            switch (choice) {
                case REMATCH ->  {

                }
                case CHANGE_OPPONENT ->  {
                    scoreboard.reset();
                    setupOpponentOnly();
                }
                case CHANGE_BOTH -> {
                    scoreboard.reset();
                    setupPlayers();
                }
                case QUIT -> {
                    running = false;
                }
            }
        }
        System.out.println("Thanks for playing!");
    }

    private void printWelcome() {
        ConsoleUI.heading("Welcome to Tic-Tac-Toe!");
        System.out.println(" Two players take turns to play.");
        System.out.println(" Get three in a row to win!");
        System.out.println();
        System.out.println(" Enter moves as column+row, e.g. A1, B2, C3.");
        System.out.println();
    }

    // ----- Player setup -----

    private void setupPlayers() {
        String nameX = menu.askPlayerName(Messages.PROMPT_PLAYER_NAME.formatted("Player X"));
        this.p1 = new HumanPlayer(nameX, Mark.X, scanner);
        this.p2 = createPlayerO();

        scoreboard.ensurePlayer(p1.getName());
        scoreboard.ensurePlayer(p2.getName());
    }
    private void setupOpponentOnly() {
        this.p2 = createPlayerO();
        scoreboard.ensurePlayer(p2.getName());
    }
    private Player createPlayerO() {
        Mode mode = menu.askMode();
        if (mode == Mode.HUMAN_VS_CPU) {
            Difficulty diff = menu.askDifficulty();

            Player cpu = switch (diff) {
                case EASY -> new ComputerPlayer("Computer (Easy)", Mark.O, new RandomStrategy(), thinkingDelay);
                case MEDIUM -> new ComputerPlayer("Computer (Medium)", Mark.O, new HeuristicStrategy(), thinkingDelay);
                case HARD -> new ComputerPlayer("Computer (Hard)", Mark.O, new MinimaxStrategy(), thinkingDelay);
            };
           System.out.println("Player O is the Computer (" + diff + ").");
           return cpu;
        }
        else {
          String nameO = menu.askPlayerName(Messages.PROMPT_PLAYER_NAME.formatted("Player O"));
          return new HumanPlayer(nameO, Mark.O, scanner);
        }
    }

    // ----- One round -----

    // Play a single round: handle turns, input validation, win/draw conditions
    private void playOneRound() {
        Player current = p1;
        while (true) {
            board.printBoard(); // assumes Board has print() method
            System.out.println();

            int cell = current.chooseCell(board);
            if (!board.placeMarkByCell(cell, current.getMark())) {
                ConsoleUI.printCellTaken();
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
