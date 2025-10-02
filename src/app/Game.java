package app;

import model.Board;
import model.Mark;
import player.HumanPlayer;
import player.Player;

import java.util.Scanner;

public class Game {
    private final Scanner scanner;
    private Board board;
    private Player p1;
    private Player p2;

    // 1) Constructor: initialize scanner, players, and board
    public Game() {
        this.scanner = new Scanner(System.in);
        this.board = new Board();

    }

    // 2) Outer game loop: allows multiple rounds until the user quits
    public void play() {
        printWelcome();
        setupPlayers();


        boolean again = true;
        while (again) {
            playOneRound();
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
        String nameX = askPlayerName("Enter name for player X: ");
        String nameO = askPlayerName("Enter name for player O: ");

        // Reuse the same scanner for both players
        this.p1 = new HumanPlayer(nameX, Mark.X, scanner);
        this.p2 = new HumanPlayer(nameO, Mark.O, scanner);
    }

    private String askPlayerName(String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            try {
                new HumanPlayer(input, Mark.X, new Scanner(System.in));
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
            int[] rc = toRowCol(cell, board.getSIZE());
            int r = rc[0], c = rc[1];

            // If the chosen cell is not available, ask the same player again
            if (!board.placeMark(r, c, current.getMark())) {
                System.out.println("That cell is not available. Try again.");
                continue;
            }

            // Check if current player has won
            if (board.checkWin(current.getMark())) {
                board.printBoard();
                System.out.println("\n" + current.getName() + " (" + current.getMark() + ") Wins!");
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

    // 4) Helper method: map cell number (1..N^2) to row/column coordinates
    private int[] toRowCol(int cell, int size) {
        int idx = cell -1;
        int row = idx / size;
        int col = idx % size;
        return new int[]{row, col};
    }
}
