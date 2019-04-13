package activities;

public class Queen {
    private final int row;
    private final int col;

    Queen(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    boolean canAttack(Queen q) {
        int deltaRow = row - q.row;
        int deltaCol = col - q.col;

        return deltaRow == 0 ||
                deltaCol == 0 ||
                deltaRow == deltaCol ||
                -deltaRow == deltaCol;
    }

    @Override
    public String toString() {
        return "Queen{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}
