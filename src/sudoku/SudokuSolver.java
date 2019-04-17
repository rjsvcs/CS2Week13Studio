package sudoku;

import backtracker.Configuration;

import java.util.*;

public class SudokuSolver implements Sudoku, Configuration {
    private final int[][] board;
    private final int moves;
    private final int lastRow;
    private final int lastCol;

    public SudokuSolver(int[][] board, int moves, int lastRow, int lastCol) {
        this.board = board;
        this.moves = moves;
        this.lastRow = lastRow;
        this.lastCol = lastCol;
    }

    @Override
    public Collection<Configuration> getSuccessors() {
        List<Configuration> successors = new ArrayList<>(9);

        int row = lastRow;
        int col = lastCol;

        while(row < SIZE && board[row][col] != 0 ) {
            col++;
            if(col == SIZE) {
                col = 0;
                row++;
            }
        }

        if(row < SIZE) {
            for(int i=1; i<10; i++) {
                int[][] copy = Sudoku.copy2DArray(board);
                copy[row][col] = i;
                successors.add(new SudokuSolver(copy, moves+1, row, col));
            }
        }

        return successors;
    }

    @Override
    public boolean isValid() {
        Set<Integer> taken = new HashSet<>();
        for(int col=0; col<SIZE; col++) {
            int value = board[lastRow][col];
            if(value != 0 && taken.contains(value)) {
                return false;
            } else {
                taken.add(value);
            }
        }

        taken.clear();
        for(int row=0; row<SIZE; row++) {
            int value = board[row][lastCol];
            if(value != 0 && taken.contains(value)) {
                return false;
            } else {
                taken.add(value);
            }
        }

        taken.clear();
        int regionRow = lastRow / REGIONS * REGIONS;
        int regionCol = lastCol / REGIONS * REGIONS;
        for(int row=0; row<3; row++) {
            for(int col=0; col<3; col++) {
                int value = board[regionRow+row][regionCol+col];
                if(value != 0 && taken.contains(value)) {
                    return false;
                } else {
                    taken.add(value);
                }
            }
        }

        return true;
    }

    @Override
    public boolean isGoal() {
        return moves == TOTAL_MOVES;
    }

    public int[][] getBoard() {
        return board;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for(int row=0; row<SIZE; row++) {
            for(int col=0; col<SIZE; col++) {
                builder.append("|");
                int value = board[row][col];
                builder.append( value == 0 ? " " : value);
            }
            builder.append("|\n");
        }

        return builder.toString();
    }
}
