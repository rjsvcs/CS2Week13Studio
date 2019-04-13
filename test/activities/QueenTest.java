package activities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {
    @Test
    public void canAttackNorthWest() {
        int row = 3;
        int col = 4;

        Queen q = new Queen(row, col);

        for(int delta=0; delta<=col; delta++) {
            Queen enemy = new Queen(row-delta, col-delta);
            assertTrue(q.canAttack(enemy), q +
                    " should be able to attack " + enemy);
        }
    }

    @Test
    public void canAttackNorth() {
        int row = 5;
        int col = 7;

        Queen queen = new Queen(row, col);

        for(int delta=1; delta<=row; delta++) {
            Queen enemy = new Queen(row-delta, col);
            assertTrue(queen.canAttack(enemy), queen +
                    " should be able to attack " + enemy);
        }
    }


}