package activities;

import backtracker.Backtracker;
import backtracker.Configuration;

import java.util.Optional;
import java.util.Scanner;

public class NQueensPrompt {
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
}
