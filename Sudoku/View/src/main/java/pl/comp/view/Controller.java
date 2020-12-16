package pl.comp.view;

import java.io.IOException;
import java.util.Random;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import pl.comp.model.BacktrackingSudokuSolver;
import pl.comp.model.SudokuArrayList;
import pl.comp.model.SudokuBoard;
import pl.comp.model.SudokuField;

public class Controller {
    Random random = new Random();

    private Poziom poziom;

    @FXML
    GridPane board;

    @FXML
    Label level;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }


    private boolean check(int row, int column, int i, SudokuBoard board) {

        //row
        for (int x = 0; x < 9; x++) {
            if (board.get(x, row) == i) {
                return false;
            }
        }

        //column
        for (int y = 0; y < 9; y++) {
            if (board.get(column, y) == i) {
                return false;
            }
        }

        //3x3
        int rzad = (row / 3) * 3;
        int kol = (column / 3) * 3;
        for (int m = rzad; m < rzad + 3; m++) {
            for (int n = kol; n < kol + 3; n++) {
                if (board.get(n, m) == i) {
                    return false;
                }
            }
        }

        return true;
    }

    public void randomStart(SudokuBoard board, int count) {

        //ustawianie losowych liczb w losowych komórkach
        for (int r = 0; r < count; r++) {
            int rr = random.nextInt(9);
            int rc = random.nextInt(9);
            //board[rc][rr] = random.nextInt(9) + 1;
            int randomOdd = random.nextInt(9) + 1;
            if (board.get(rr, rc) == 0) {
                r--;
                break;
            }
            if (check(rr, rc, randomOdd, board)) {
                board.set(rc, rr, randomOdd);
            }
        }
    }

    @FXML
    private void start() {

        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver(), false);
        randomStart(sudokuBoard, 9);
        sudokuBoard.solveGame();

        ObservableList<Node> childrens = board.getChildren();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                //if (childrens.get((i * 9) + j) instanceof TextField) {
                ((TextField) childrens.get((i * 9) + j))
                        .setText(Integer.toString(sudokuBoard.get(i, j)));
                ((TextField) childrens.get((i * 9) + j)).setEditable(false);//}
                //}
            }
        }

        this.setPoziom(this.level).start(board);
    }

    public Poziom setPoziom(Label level) {
        if (level.getText().compareTo("H") == 0) {
            return Poziom.TRUDNY;
        } else if (level.getText().compareTo("M") == 0) {
            return Poziom.SREDNI;
        } else {
            return Poziom.LATWY;
        }
    }
}