package model;

import static util.ConsoleColors.*;

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

    // --- Helpers: convert cell number to row/col ---
    private int toRow(int cell) {
            return (cell -1) / SIZE;
    }

    private int toCol(int cell) {
        return (cell - 1) % SIZE;
    }

    // Print the board in a user-friendly way
    public void printBoard() {

        // Header
        System.out.println("    " + CYAN + "A" + RESET + "   " + CYAN + "B" + RESET + "   " + CYAN + "C" + RESET);

        for (int row = 0; row < SIZE; row++) {
            // Row content
            StringBuilder line = new StringBuilder();
            line.append(row + 1).append("  ");

            for (int col = 0; col < SIZE; col++) {
                Mark m = grid[row][col];
                String cell;
                if (m == Mark.X) {
                    cell = PURPLE + "X" + RESET;
                } else if (m == Mark.O) {
                    cell = YELLOW + "O" + RESET;
                } else {
                    cell = " ";
                }

                line.append(" ").append(cell).append(" ");
                if (col < SIZE - 1) {
                    line.append(GRAY).append("|").append(RESET);
                }
            }
            System.out.println(line);

            // Separator line
            if (row < SIZE - 1) {
                System.out.println("   " + GRAY + "---+---+---" + RESET);
            }
        }
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
        if (cell < 1 || cell > SIZE * SIZE) {
            return false;
        }
        return placeMark(toRow(cell), toCol(cell), mark);
    }

    /**
     * Check if a cell is empty (contains Mark.EMPTY)
     */
    public boolean isCellEmpty(int cell) {
        if (cell < 1 || cell > SIZE * SIZE) {
            return false;
        }
        return grid[toRow(cell)][toCol(cell)] == Mark.EMPTY;
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

    /**
     * Get the mark at a specific cell (1..SIZE*SIZE).
     */
    public Mark getMarkAtCell(int cell) {
        if (cell < 1 || cell > SIZE * SIZE) {
            throw new IllegalArgumentException("Invalid cell: " + cell);
        }
        return grid[toRow(cell)][toCol(cell)];
    }

    public String formatCell(int cell) {
        if (cell < 1 || cell > SIZE * SIZE) {
            throw new IllegalArgumentException("Invalid cell: " + cell);
        }
        int row = (cell - 1) / SIZE;
        int col = (cell - 1) % SIZE;
        char colChar = (char) ('A' + col);
        char rowChar = (char) ('1' + row);
        return "" + colChar + rowChar;
    }
}
