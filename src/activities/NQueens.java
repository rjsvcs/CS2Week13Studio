package activities;

import backtracker.Configuration;

import java.util.*;

public class NQueens implements Configuration {
    private static final String NL = System.getProperty("line.separator");
    private static final char COL_SEPARATOR = '|';

    private final int maxQueens;
    private final Queen[] queens;

    NQueens(int maxQueens) {
        this(maxQueens, new Queen[0]);
    }

    NQueens(int maxQueens, Queen[] queens) {
        this.maxQueens = maxQueens;
        this.queens = queens;
    }

    @Override
    public Collection<Configuration> getSuccessors() {
        List<Configuration> successors = new ArrayList<>();

        int row = queens.length != 0 ?
                queens[queens.length-1].getRow() + 1 : 0;
        if(row < maxQueens) {
            for(int col=0; col<maxQueens; col++) {
                Queen[] next = Arrays.copyOf(queens, queens.length + 1);
                next[queens.length] = new Queen(row, col);
                successors.add(new NQueens(maxQueens, next));
            }
        }

        return successors;
    }

    @Override
    public boolean isValid() {
        int newest = queens.length-1;
        for(int i=0; i<newest; i++) {
            if(queens[i].canAttack(queens[newest])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isGoal() {
        return isValid() && (queens.length == maxQueens);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        boolean[][] board = new boolean[maxQueens][maxQueens];
        for(Queen queen : queens) {
            board[queen.getRow()][queen.getCol()] = true;
        }

        for(int index=0; index<maxQueens*maxQueens; index++) {
            int row = index / maxQueens;
            int col = index % maxQueens;

            if(col == 0) {
                builder.append(NL);
                builder.append(COL_SEPARATOR);
            }
            builder.append(board[row][col] ? 'Q' : ' ');
            builder.append(COL_SEPARATOR);
        }

        return builder.toString();
    }
}
