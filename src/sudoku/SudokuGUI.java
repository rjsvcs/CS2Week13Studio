package sudoku;

import backtracker.Backtracker;
import backtracker.Configuration;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Optional;

public class SudokuGUI extends Application implements Sudoku {
    /**
     * Red, bold text style.
     */
    private static final String BOLD =
            "-fx-font-weight: bold; " +
            "-fx-text-inner-color: red;";

    /**
     * Regular text style.
     */
    private static final String REGULAR =
            "-fx-font-weight: normal;";

    private final TextField[][] input = new TextField[COLS][ROWS];
    private final Backtracker backtracker = new Backtracker(false);
    private Label feedback;

    @Override
    public void start(Stage stage) throws Exception {
        GridPane grid = new GridPane();
        for(int row=0; row<ROWS; row++) {
            for(int col=0; col<COLS; col++) {
                TextField field = new TextField("");
                input[row][col] = field;
                field.setPrefColumnCount(1);
                field.setFont(new Font("Courier New", 18));
                grid.add(field, col, row);
            }
        }

        BorderPane main = new BorderPane();
        main.setCenter(grid);

        HBox bottom = new HBox();

        Button clear = new Button("Clear");
        clear.setPadding(new Insets(10));
        clear.setOnAction(e -> clearBoard());

        Button solve = new Button("Solve");
        solve.setPadding(new Insets(10));
        solve.setOnAction(e -> {
            Thread thread = new Thread(() -> solve());
            thread.setDaemon(true);
            thread.start();
        });

        feedback = new Label("Click to solve!");
        feedback.setPadding(new Insets(10));

        bottom.getChildren().addAll(clear, solve, feedback);
        main.setBottom(bottom);

        stage.setTitle("Sudoku Solver");
        stage.setScene(new Scene(main));
        stage.show();
    }

    private void solve() {
        int[][] board = new int[ROWS][COLS];
        int moves = 0;

        for(int row=0; row<ROWS; row++) {
            for(int col=0; col<COLS; col++) {
                TextField field = input[row][col];
                String value = field.getText().trim();
                if(value.equals("")) {
                    field.setStyle(REGULAR);
                    board[row][col] = 0;
                } else {
                    field.setStyle(BOLD);
                    board[row][col] = Integer.parseInt(value);
                    moves++;
                }
            }
        }

        // create configuration and run backtracker here

    }


    private void clearBoard() {
        for(int row=0; row<ROWS; row++) {
            for(int col=0; col<COLS; col++) {
                TextField field = input[row][col];
                field.setText("");
                field.setStyle(REGULAR);
            }
        }
    }

    private void setBoard(int[][] board) {
        for(int row=0; row<ROWS; row++) {
            for(int col=0; col<COLS; col++) {
                input[row][col].setText(Integer.toString(board[row][col]));
            }
        }
    }
}
