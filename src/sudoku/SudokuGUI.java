package sudoku;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SudokuGUI extends Application {
    private static final int SIZE = 9;

    private final TextField[][] input = new TextField[SIZE][SIZE];
    private Label feedback;

    @Override
    public void start(Stage stage) throws Exception {
        GridPane grid = new GridPane();
        for(int row=0; row<SIZE; row++) {
            for(int col=0; col<SIZE; col++) {
                TextField field = new TextField(" ");
                input[row][col] = field;
                field.setPrefColumnCount(1);
                field.setFont(new Font("Courier New", 18));
                grid.add(field, row, col);
            }
        }

        BorderPane main = new BorderPane();
        main.setCenter(grid);

        FlowPane bottom = new FlowPane();

        Button solve = new Button("Solve");
        solve.setOnAction(e -> solve());

        feedback = new Label("Click to solve!");
        feedback.setPadding(new Insets(10));

        bottom.getChildren().addAll(solve, feedback);
        main.setBottom(bottom);

        stage.setTitle("Sudoku Solver");
        stage.setScene(new Scene(main));
        stage.show();
    }

    private void solve() {
        int[][] board = new int[SIZE][SIZE];
        for(int row=0; row<SIZE; row++) {
            for(int col=0; col<SIZE; col++) {
                String value = input[row][col].getText().trim();
                if(value.equals("")) {
                    board[row][col] = 0;
                } else {
                    board[row][col] = Integer.parseInt(value);
                }
            }
        }
    }
}
