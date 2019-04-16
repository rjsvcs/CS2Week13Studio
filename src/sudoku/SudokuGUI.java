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

    /**
     * A black background fill.
     */
    private static final Background BACKGROUND_BLACK = new Background(
            new BackgroundFill(Color.BLACK, CornerRadii.EMPTY,
            Insets.EMPTY));

    private final TextField[][] input = new TextField[COLS][ROWS];
    private final Backtracker backtracker = new Backtracker(false);
    private Label feedback;

    @Override
    public void start(Stage stage) throws Exception {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(3));
        grid.setBackground(BACKGROUND_BLACK);

        for(int row=0; row<3; row++) {
            for(int col=0; col<3; col++) {
                GridPane region = makeRegion(row, col);
                grid.add(region, col, row);
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

    /**
     * Makes one of the 3x3 regions that make up the board.
     *
     * @param regionRow The number of the region's row.
     * @param regionCol The number of the region's column.
     * @return The 3x3 region as a {@link GridPane}.
     */
    private GridPane makeRegion(int regionRow, int regionCol) {
        int startRow = regionRow * REGIONS;
        int startCol = regionCol * REGIONS;

        GridPane region = new GridPane();
        region.setBackground(BACKGROUND_BLACK);
        region.setPadding(new Insets(3));
        for(int row=0; row<3; row++) {
            for(int col=0; col<3; col++) {
                TextField field = new TextField("");
                input[row+startRow][col+startCol] = field;
                field.setPrefColumnCount(1);
                field.setFont(new Font("Courier New", 18));
                region.add(field, col, row);
            }
        }
        return region;
    }

    /**
     * Makes a sudoku board from the numbers currently entered into the UI and
     * then attempts to find a solution to the puzzle using backtracking. If
     * it finds one, the GUI is updated with the correct solution.
     */
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


    /**
     * Clears anything currently typed into the UI.
     */
    private void clearBoard() {
        for(int row=0; row<ROWS; row++) {
            for(int col=0; col<COLS; col++) {
                TextField field = input[row][col];
                field.setText("");
                field.setStyle(REGULAR);
            }
        }
    }

    /**
     * Updates the Ui to display the contents of the specified board (0 values
     * are not displayed).
     *
     * @param board The board to display.
     */
    private void setBoard(int[][] board) {
        for(int row=0; row<ROWS; row++) {
            for(int col=0; col<COLS; col++) {
                input[row][col].setText(Integer.toString(board[row][col]));
            }
        }
    }
}
