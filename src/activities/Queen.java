package activities;

public class Queen {
    private final int index;
    private final int row;
    private final int col;

    Queen(int index, int maxQueens) {
        this.index = index;
        this.row = index / maxQueens;
        this.col = index % maxQueens;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getIndex() {
        return index;
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
