package activities;

import backtracker.Backtracker;
import backtracker.Configuration;

import java.util.Optional;

public class NQueensLoop {
    public static void main(String[] args) {
        boolean sentenel = true;
        int maxQueens = 4;
        while(sentenel) {
            NQueens configuration = new NQueens(maxQueens);
            Backtracker backtracker = new Backtracker(false);
            long time = System.currentTimeMillis();
            Optional<Configuration> solution = backtracker.solve(configuration);
            time = System.currentTimeMillis() - time;
            System.out.println(maxQueens + " completed in " +
                    (time < 1000 ? time + "ms" : time / 1000.0 + "s"));

            if(!solution.isPresent()) {
                System.out.println("No Solution");
                System.exit(0);
            }

            if(time > (1000 * 60 * 10)) {
                System.out.println("Took more than 10 minutes, quitting...");
                System.exit(0);
            }

            maxQueens++;
        }
    }
}
