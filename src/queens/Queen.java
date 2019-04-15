package queens;

public class Queen {

    private final int index;
    private final int row;
    private final int col;

    public Queen(int index, int N) {
        this.index = index;
        this.row = index / N;
        this.col = index % N;
    }

    public boolean canAttack(Queen enemy) {
        int deltaRow = row - enemy.row;
        int deltaCol = col - enemy.col;


        return (deltaRow == 0 ||
                deltaCol == 0 ||
                deltaRow == deltaCol ||
                -deltaRow == deltaCol);
    }

}
