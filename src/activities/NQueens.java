package activities;

import backtracker.Backtracker;
import backtracker.Configuration;

import java.util.*;

public class NQueens implements Configuration {
    private static final String NL = System.getProperty("line.separator");
    private static final char COL_SEPARATOR = '|';

    private final int maxQueens;
    private final Queen[] queens;
    private final int row;
    private final int col;

    NQueens(int maxQueens) {
        this(maxQueens, new Queen[0], 1, -1);
    }

    NQueens(int maxQueens, Queen[] queens, int row, int col) {
        this.maxQueens = maxQueens;
        this.queens = queens;
        this.row = row;
        this.col = col;
    }

    @Override
    public Collection<Configuration> getSuccessors() {
        List<Configuration> successors = new ArrayList<>();

        int start = row * col + 1;
        int end = maxQueens * maxQueens;

        for(int i=start; i<end; i++) {
            int r = i / maxQueens;
            int c = i % maxQueens;

            Queen[] next = Arrays.copyOf(queens, queens.length + 1);
            next[queens.length] = new Queen(r, c);

            successors.add(new NQueens(maxQueens, next, r, c));

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
        boolean[][] board = new boolean[maxQueens][maxQueens];
        for(Queen queen : queens) {
            board[queen.getRow()][queen.getCol()] = true;
        }

        StringBuilder builder = new StringBuilder();
        for(int r=0; r<maxQueens; r++) {
            addRowSeparator(builder, maxQueens);
            builder.append(NL);
            builder.append(COL_SEPARATOR);
            for(int c=0; c<maxQueens; c++) {
                builder.append(board[r][c] ? 'Q' : ' ');
                builder.append(COL_SEPARATOR);
            }
            builder.append(NL);
        }
        addRowSeparator(builder, maxQueens);

        return builder.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Number of Queens (enter for 4): ");
        String number = scanner.nextLine().trim();
        System.out.print("Debug Y/N (enter for N): ");
        String yesOrNo = scanner.nextLine().trim();

        int maxQueens = number.length() > 0 ? Integer.parseInt(number) : 4;
        boolean debug = yesOrNo.length() > 0 && yesOrNo.equalsIgnoreCase("y");

        NQueens configuration = new NQueens(maxQueens);

        Backtracker backtracker = new Backtracker(debug);
        long time = System.currentTimeMillis();
        Optional<Configuration> solution =  backtracker.solve(configuration);
        time = System.currentTimeMillis() - time;
        System.out.println("Completed in " +
                (time < 1000 ? time + "ms" : time/1000.0 + "s"));

        if(solution.isPresent()) {
            System.out.println(solution.get());
        } else {
            System.out.println("No Solution");
        }
    }

    private static void addRowSeparator(StringBuilder builder, int cols) {
        builder.append('-');
        for(int c=0; c<cols; c++) {
            builder.append("--");
        }
    }
}
