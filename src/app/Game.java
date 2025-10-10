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
import util.ConsoleColors;
import util.ConsoleUI;
import util.Messages;

import java.util.Scanner;

/**
 * Game - Orchestrates the game flow:
 * setup -> play rounds -> show results -> post-game options.
 */
public class Game {

    // === Fields ==============================================================

    private final Scanner scanner;
    private Board board;
    private Player p1;
    private Player p2;
    private final Scoreboard scoreboard;
    private final boolean thinkingDelay;
    private final Menu menu;
    private int boardSize = 3; // default 3x3 (AI stays on 3x3)

    // === Constructors =========================================================

    public Game() {
        this(true);
    }

    public Game(boolean thinkingDelay) {
        this.scanner = new Scanner(System.in);
        this.board = new Board();
        this.scoreboard = new Scoreboard();
        this.thinkingDelay = thinkingDelay;
        this.menu = new Menu(this.scanner);
    }

    // === Main game loop =======================================================

    public void play() {
        printWelcome();
        setupPlayers();
        ConsoleUI.clearScreen();
        ConsoleUI.printStartBanner(p1, p2);

        boolean running = true;
        while (running) {
            ConsoleUI.clearScreen();
            board = new Board(boardSize);
            playOneRound();

            System.out.println();
            scoreboard.printScores();
            System.out.println();

            PostGameChoice choice = menu.askPostGameChoice();
            switch (choice) {
                case REMATCH -> {
                    /* start a new round same players */
                    ConsoleUI.clearScreen();
                    ConsoleUI.printStartBanner(p1, p2);
                }

                case CHANGE_OPPONENT -> {
                    scoreboard.reset();
                    setupOpponentOnly();
                    ConsoleUI.clearScreen();
                    ConsoleUI.printStartBanner(p1, p2);
                }

                case CHANGE_BOTH -> {
                    scoreboard.reset();
                    setupPlayers();
                    ConsoleUI.clearScreen();
                    ConsoleUI.printStartBanner(p1, p2);
                }

                case QUIT -> running = false;
            }
        }
        System.out.println("Thanks for playing!");
    }

    // === Intro screen =========================================================

    private void printWelcome() {
        ConsoleUI.heading("Welcome to Tic-Tac-Toe!");
        ConsoleUI.printInfo(" Two players take turns to play.");
        ConsoleUI.printInfo(" Get three in a row to win!");
        System.out.println();
        ConsoleUI.printInfo(" Enter moves as column+row, e.g. A1, B2, C3.");
        System.out.println();
    }

    // === Player setup =========================================================

    private void setupPlayers() {
        String nameX = menu.askPlayerName(Messages.PROMPT_PLAYER_NAME.formatted(ConsoleColors.PURPLE + "Player X" + ConsoleColors.RESET));
        this.p1 = new HumanPlayer(nameX, Mark.X, scanner);
        this.p2 = createPlayerO();

        scoreboard.ensurePlayer(p1.getName(), p1.getMark());
        scoreboard.ensurePlayer(p2.getName(), p2.getMark());
    }

    private void setupOpponentOnly() {
        this.p2 = createPlayerO();
        scoreboard.ensurePlayer(p2.getName(), p2.getMark());
    }

    private Player createPlayerO() {
        Mode mode = menu.askMode();
        if (mode == Mode.HUMAN_VS_CPU) {
            boardSize = 3;
            Difficulty diff = menu.askDifficulty();

            return switch (diff) {
                case EASY -> new ComputerPlayer("Computer (Easy)", Mark.O, new RandomStrategy(), thinkingDelay);
                case MEDIUM -> new ComputerPlayer("Computer (Medium)", Mark.O, new HeuristicStrategy(), thinkingDelay);
                case HARD -> new ComputerPlayer("Computer (Hard)", Mark.O, new MinimaxStrategy(), thinkingDelay);
            };
        }
        else {
            boardSize = menu.askBoardSize();
          String nameO = menu.askPlayerName(Messages.PROMPT_PLAYER_NAME.formatted(ConsoleColors.YELLOW + "Player O" + ConsoleColors.RESET));
          return new HumanPlayer(nameO, Mark.O, scanner);
        }
    }

    // === One round ============================================================

    /**
     * Plays a single round:
     * draw board → ask current player for a move → place/check validity →
     * check win/draw → switch player.
     */
    private void playOneRound() {
        Player current = p1;
        while (true) {
            board.printBoard();
            System.out.println();

            int cell = current.chooseCell(board);
            if (!board.placeMarkByCell(cell, current.getMark())) {
                ConsoleUI.printCellTaken();
                continue; // invalid move, same player tries again
            }

            if (board.checkWin(current.getMark())) {
                board.printBoard();

                System.out.println();
                System.out.println("Round finished!");
                System.out.println();

                ConsoleUI.printSeparator();
                System.out.println(
                        ConsoleUI.coloredByMark(current.getName() + " " + current.getMark() + " Wins!", current.getMark())
                );
                ConsoleUI.printSeparator();

                scoreboard.addWin(current.getName());
                break;
            }

            if (board.isFull()) {
                board.printBoard();

                System.out.println();
                System.out.println("Round finished!");
                System.out.println();

                ConsoleUI.printSeparator();
                ConsoleUI.printInfo("It's a draw!");
                ConsoleUI.printSeparator();
                break;
            }

            current = (current == p1) ? p2 : p1;
        }
    }
}
