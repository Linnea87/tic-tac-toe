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
    private boolean moveHintShown = false;

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

        if (!moveHintShown) {
           ConsoleUI.printPreRoundHints(boardSize);
            moveHintShown = true;
        }

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
                    ConsoleUI.clearScreen();
                    ConsoleUI.printStartBanner(p1, p2);

                }

                case CHANGE_OPPONENT -> {
                    scoreboard.reset();
                    setupOpponentOnly();
                    scoreboard.ensurePlayer(p1.getName(), p1.getMark());
                    moveHintShown = false;

                    ConsoleUI.clearScreen();
                    ConsoleUI.printStartBanner(p1, p2);
                   ConsoleUI.printPreRoundHints(boardSize);
                    moveHintShown = true;
                }

                case CHANGE_BOTH -> {
                    scoreboard.reset();
                    setupPlayers();
                    moveHintShown = false;

                    ConsoleUI.clearScreen();
                    ConsoleUI.printStartBanner(p1, p2);
                   ConsoleUI.printPreRoundHints(boardSize);
                    moveHintShown = true;
                }

                case QUIT -> running = false;
            }
        }
        ConsoleUI.printInfo(" " + Messages.MSG_THANKS_FOR_PLAYING);
    }

    // === Intro screen =========================================================

    private void printWelcome() {
        ConsoleUI.heading(Messages.WELCOME_TITLE);
        ConsoleUI.printInfo(" " + Messages.WELCOME_LINE1);
        ConsoleUI.printInfo(" " + Messages.WELCOME_LINE2);
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
                ConsoleUI.printInfo(" " + Messages.MSG_ROUND_FINISHED);
                System.out.println();

                ConsoleUI.printSeparator();
                System.out.println(
                        ConsoleUI.coloredByMark(
                                current.getName() + " " + current.getMark() + " " + Messages.MSG_WINS,
                                current.getMark()
                        )
                );
                ConsoleUI.printSeparator();

                scoreboard.addWin(current.getName());
                break;
            }

            if (board.isFull()) {
                board.printBoard();

                System.out.println();
                ConsoleUI.printInfo(" " + Messages.MSG_ROUND_FINISHED);
                System.out.println();

                ConsoleUI.printSeparator();
                ConsoleUI.printInfo(" " + Messages.MSG_DRAW);
                ConsoleUI.printSeparator();
                break;
            }

            current = (current == p1) ? p2 : p1;
        }
    }
}
