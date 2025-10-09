package model;

import util.Messages;

import static util.ConsoleColors.*;

/**
 * Board – square grid that stores marks and evaluates wins/draws.
 * Supports dynamic sizes from 3×3 up to 10×10.
 */
public class Board {
    private final int SIZE;
    private final Mark[][] grid;

    // === Constructors =========================================================

    public Board() {
        this(3); // default 3x3 (keeps AI/back-compat behavior)
    }
    public Board(int size) {
        if (size < 3 || size > 10) {
            throw new IllegalArgumentException(Messages.ERR_BOARD_SIZE);
        }
        this.SIZE = size;
        this.grid = new Mark[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                grid[row][col] = Mark.EMPTY;
            }
        }
    }

    // === Coordinate helpers ===================================================

    private int toRow(int cell) {
        return (cell - 1) / SIZE;
    }

    private int toCol(int cell) {
        return (cell - 1) % SIZE;
    }

    // === Rendering ============================================================

    /**
     * Prints the board with lettered columns (A...) and numbered rows (1...), using colored X/O.
     */
    public void printBoard() {
        StringBuilder header = new StringBuilder();
        header.append("   ");
        for (int c = 0; c < SIZE; c++) {
            char colChar = (char) ('A' + c);
            header.append(CYAN).append(colChar).append(RESET);
            if (c < SIZE - 1) header.append("   ");
        }
        System.out.println(header);

        for (int row = 0; row < SIZE; row++) {
            StringBuilder line = new StringBuilder();

            String rowLabel = String.valueOf(row + 1);
            if (SIZE >= 10 && rowLabel.length() == 1) {
                line.append(" ").append(rowLabel).append("  ");
            }
            else {
                line.append(rowLabel).append("  ");
            }

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

            if (row < SIZE - 1) {
                StringBuilder sep = new StringBuilder();
                sep.append("   ").append(GRAY);
                for (int c = 0; c < SIZE; c++) {
                    sep.append("---");
                    if (c < SIZE - 1) sep.append("+");
                }
                sep.append(RESET);
                System.out.println(sep);
            }
        }
    }

    // === Mutators =============================================================

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

    public boolean placeMarkByCell(int cell, Mark mark) {
        if (cell < 1 || cell > SIZE * SIZE) {
            return false;
        }
        return placeMark(toRow(cell), toCol(cell), mark);
    }

    // === Queries ==============================================================

    public boolean isCellEmpty(int cell) {
        if (cell < 1 || cell > SIZE * SIZE) {
            return false;
        }
        return grid[toRow(cell)][toCol(cell)] == Mark.EMPTY;
    }

    public boolean isFull() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (grid[row][col] == Mark.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkWin(Mark mark) {
        boolean diag1 = true;
        boolean diag2 = true;

        for (int i = 0; i < SIZE; i++) {
            boolean rowAll = true;
            boolean colAll = true;

            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] != mark) {
                    rowAll = false;
                }
                if (grid[j][i] != mark) {
                    colAll = false;
                }
            }
            if (rowAll || colAll) return true;

            if (grid[i][i] != mark) diag1 = false;
            if (grid[i][SIZE - 1 - i] != mark)  diag2 = false;
        }
        return diag1 || diag2;
    }

    // === Accessors ============================================================

    public int getSize() {
        return SIZE;
    }

    public Mark getMarkAtCell(int cell) {
        if (cell < 1 || cell > SIZE * SIZE) {
            throw new IllegalArgumentException(Messages.ERR_CELL_FORMAT + " " + cell);
        }
        return grid[toRow(cell)][toCol(cell)];
    }

    public String formatCell(int cell) {
        if (cell < 1 || cell > SIZE * SIZE) {
            throw new IllegalArgumentException(Messages.ERR_CELL_FORMAT + " " + cell);
        }
        int row = (cell - 1) / SIZE;
        int col = (cell - 1) % SIZE;
        char colChar = (char) ('A' + col);
        String rowStr = String.valueOf(row + 1);
        return colChar + rowStr;
    }
}
