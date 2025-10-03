package model;

public class Board {
    private final int SIZE = 3; // Board size (3x3)
    private final Mark[][] grid; // 2D array representing the board

    // Constructor: initialize all cells as EMPTY
    public Board() {
        grid = new Mark[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                grid[row][col] = Mark.EMPTY;
            }
        }
    }

    // Print the board in a user-friendly way
    public void printBoard() {
        System.out.println();
        for (int row = 0; row < SIZE; row++) {
            StringBuilder line = new StringBuilder();

            for (int col = 0; col < SIZE; col++) {
                String cell =(grid[row][col] == Mark.EMPTY) ? " " : grid[row][col].toString();
                line.append(" ").append(cell).append(" ");
                if (col < SIZE - 1) {
                  line.append("|");
                }
            }
            System.out.println(line);
            if (row < SIZE - 1) {
                System.out.println("---+---+---");
            }
        }
        System.out.println();
    }

    // Try to place a mark at a given position
    public boolean placeMark(int row, int col, Mark mark) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            return false; // out of bounds
        }
        if (grid[row][col] != Mark.EMPTY) {
            return false; // cell already taken
        }
        grid[row][col] = mark;
        return true;
    }

    /**
     * Place a mark using a single cell number (1..SIZE*SIZE).
     * Converts to (row,col) and delegates to placeMark(row, col, mark).
     */
    public boolean placeMarkByCell(int cell, Mark mark) {
        int idx = cell - 1;
        if (idx < 0 || idx >= SIZE * SIZE) {
            return false;
        }
        int row = idx / SIZE;
        int col = idx % SIZE;
        return placeMark(row, col, mark);
    }

    // Check if the board is full (for draw condition)
    public boolean isFull() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++ ) {
                if (grid[row][col] == Mark.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    // Check if player has won
    public boolean checkWin(Mark mark) {
        // Check rows
        for (int row = 0; row < SIZE; row++) {
            if (grid[row][0] == mark && grid[row][1] == mark && grid[row][2] == mark) {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < SIZE; col++) {
            if (grid[0][col] == mark && grid[1][col] == mark && grid[2][col] == mark) {
                return true;
            }
        }

        // Check diagonals
        if (grid[0][0] == mark && grid[1][1] == mark && grid[2][2] == mark) {
            return true;
        }
        if (grid[0][2] == mark && grid[1][1] == mark && grid[2][0] == mark) {
            return true;
        }
        return false;
    }

    // Getter for the board size
    public int getSize() {
        return SIZE;
    }


}
