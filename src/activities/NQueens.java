package activities;

import backtracker.Configuration;

import java.util.*;

public class NQueens implements Configuration {
    private static final String NL = System.getProperty("line.separator");
    private static final char COL_SEPARATOR = '|';

    private final int maxQueens;
    private final Queen[] queens;
    private final int index;

    NQueens(int maxQueens) {
        this(maxQueens, new Queen[0], -1);
    }

    NQueens(int maxQueens, Queen[] queens, int index) {
        this.maxQueens = maxQueens;
        this.queens = queens;
        this.index = index;
    }

    @Override
    public Collection<Configuration> getSuccessors() {
        List<Configuration> successors = new ArrayList<>();

        int start = index + 1;
        int end = maxQueens * maxQueens;

        for(int index=start; index<end; index++) {
            Queen[] next = Arrays.copyOf(queens, queens.length + 1);
            next[queens.length] = new Queen(index, maxQueens);

            successors.add(new NQueens(maxQueens, next, index));

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
        boolean[] board = new boolean[maxQueens*maxQueens];
        for(Queen queen : queens) {
            board[queen.getIndex()] = true;
        }

        StringBuilder builder = new StringBuilder();

        for(int index=0; index<maxQueens*maxQueens; index++) {
            int row = index / maxQueens;
            int col = index % maxQueens;

            if(col == 0) {
                builder.append(NL);
                builder.append(COL_SEPARATOR);
            }
            builder.append(board[index] ? 'Q' : ' ');
            builder.append(COL_SEPARATOR);
        }

        return builder.toString();
    }
}
