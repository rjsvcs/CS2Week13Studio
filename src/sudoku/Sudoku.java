package sudoku;

import java.util.Arrays;

/**
 * An interface with useful constants and methods for implementing a Sudoku
 * solver.
 */
public interface Sudoku {
    /**
     * The dimension of one side of a Sudoku board.
     */
    int SIZE = 9;

    /**
     * The total moves that  must be made toc omplete the game.
     */
    int TOTAL_MOVES = SIZE * SIZE;

    /**
     * The number of regions in each dimension.
     */
    int REGIONS = 3;

    /**
     * Efficiently makes and returns a copy of the given array.
     *
     * @param array The array to copy.
     *
     * @return The copy of the original array.
     */
    static int[][] copy2DArray(int[][] array) {
        int[][] copy = new int[array.length][];
        for(int i=0; i<array.length; i++) {
            copy[i] = Arrays.copyOf(array[i], array[i].length);
        }
        return copy;
    }
}
