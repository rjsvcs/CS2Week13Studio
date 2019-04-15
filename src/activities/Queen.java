package activities;

/**
 * A queen on a chess board.
 */
public class Queen {
    /**
     * The row of the queen's position on a two-dimensional board.
     */
    private final int row;

    /**
     * The column of the queen's position on a two-dimensional board.
     */
    private final int col;

    /**
     * Creates a new Queen on a board of the specified size.
     */
    Queen(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Given some other queen, this method will return true if the queens can
     * attack each other, and false otherwise.
     *
     * @param enemy The other queen.
     *
     * @return True of the two queens can attack each other, false otherwise.
     */
    boolean canAttack(Queen enemy) {
        int deltaRow = row - enemy.row;
        int deltaCol = col - enemy.col;

        return deltaRow == 0 ||
                deltaCol == 0 ||
                deltaRow == deltaCol ||
                -deltaRow == deltaCol;
    }

    /**
     * Returns the queen's row on the board.
     *
     * @return The queen's row on the board.
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the queen's column on the board.
     *
     * @return The queen's column on the board.
     */
    public int getCol() {
        return col;
    }

    /**
     * Returns a string representation of the queen including its row and
     * column.
     *
     * @return A string representation of the queen.
     */
    @Override
    public String toString() {
        return "Queen{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}
