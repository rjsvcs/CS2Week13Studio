package queens;

import backtracker.Configuration;

import java.util.Collection;

public class NQueens implements Configuration {

    private final Queen[] queens;
    private final int N;

    NQueens(Queen[] queens, int N) {
        this.queens = queens;
        this.N = N;
    }

    @Override
    public Collection<Configuration> getSuccessors() {

        return null;
    }

    @Override
    public boolean isValid() {
        int last = queens.length -1;
        for(int i=0; i<last; i++) {
            if(queens[i].canAttack(queens[last])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isGoal() {
        return queens.length == N && isValid();
    }
}
