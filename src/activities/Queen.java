package activities;

/**
 * A queen on a chess board.
 */
public class Queen {
    /**
     * The index of the queen's position in an array.
     */
    private final int index;

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
     *
     * @param index The index of the queen on the board as if it were an array.
     * @param boardDimension The size of each dimension of a two-dimensional
     *                       board.
     */
    Queen(int index, int boardDimension) {
        this.index = index;
        this.row = index / boardDimension;
        this.col = index % boardDimension;
    }

    /**
     * Returns the index of the queen on the board.
     *
     * @return The queen's position on the board.
     */
    public int getIndex() {
        return index;
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
