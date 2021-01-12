package pl.comp.view;

import java.io.IOException;
import java.util.Locale;
import java.util.Random;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import pl.comp.model.BacktrackingSudokuSolver;
import pl.comp.model.SudokuBoard;
import pl.comp.model.SudokuBoardDaoFactory;
import pl.comp.model.SudokuField;
import pl.comp.model.exceptions.DaoFileException;
import pl.comp.view.exceptions.FormFileException;

public class Controller {

    private static Logger logger = (Logger) LogManager.getLogger(App.class.getName());

    private static Poziom level;

    Random random = new Random();

    private boolean firstStart = true;

    private SudokuBoard actualSudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver(), false);

    @FXML
    GridPane board;

    @FXML
    MenuButton menuButton;

    private Authors authors = new Authors();

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

        fillBoard(sudokuBoard);
        level.start(board);
    }

    private void fillBoard(SudokuBoard sudokuBoard) {
        ObservableList<Node> childrens = board.getChildren();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = (TextField) childrens.get((i * 9) + j);

                if (sudokuBoard.get(i, j) % 10 == 0) {
                    textField.setText("");
                } else {
                    if (sudokuBoard.get(i, j) > 9) {
                        textField.setText(Integer.toString(sudokuBoard.get(i, j) - 10));
                    } else {
                        textField.setText(Integer.toString(sudokuBoard.get(i, j)));
                    }
                }
                if (sudokuBoard.get(i, j) < 10) {
                    textField.setEditable(false);
                } else {
                    textField.setEditable(true);
                }

                if (firstStart == true) {
                    textField.textProperty().addListener((observable, oldValue, newValue) -> {
                        if (newValue.length() > 0) {
                            textField.setText(newValue.substring(newValue.length() - 1));
                        }
                        updateSudokuBoard();
                    });
                }
            }
        }
        firstStart = false;
    }

    @FXML
    public void startFromFile() throws DaoFileException {
        SudokuBoard sudokuBoard = null;
        sudokuBoard = SudokuBoardDaoFactory.getFileDao("save").read();
        fillBoard(sudokuBoard);
    }

    @FXML
    public void saveToFile() throws DaoFileException {

        SudokuBoard boardToSave = new SudokuBoard(new BacktrackingSudokuSolver(), false);
        ObservableList<Node> childrens = board.getChildren();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = (TextField) childrens.get((i * 9) + j);

                if (textField.isEditable() == true) {
                    if (textField.getText().compareTo("") == 0) {
                        boardToSave.set(i, j, 10);
                    } else {
                        if (NumberUtils.isParsable(textField.getText()) == true) {
                            boardToSave.set(i, j, 10 + Integer.parseInt(textField.getText()));
                        } else {
                            boardToSave.set(i, j, 10);
                        }
                    }
                } else {
                    boardToSave.set(i, j, Integer.parseInt(textField.getText()));
                }
            }
        }
            SudokuBoardDaoFactory.getFileDao("save").write(boardToSave);
    }

    private void updateSudokuBoard() {
        ObservableList<Node> childrens = board.getChildren();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = (TextField) childrens.get((i * 9) + j);
                if (NumberUtils.isParsable(textField.getText()) == false) {
                    actualSudokuBoard.set(i, j, 0);
                } else {
                    actualSudokuBoard.set(i, j, Integer.parseInt(textField.getText()));
                }
            }
        }

        childrens = board.getChildren();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = (TextField) childrens.get((i * 9) + j);
                if (textField.isEditable() == false) {
                    textField.setStyle("-fx-text-fill: black;");
                } else {
                    checkCorrect(textField, i, j);
                }
            }
        }
    }

    private void checkCorrect(TextField textField, int i, int j) {
        if (NumberUtils.isParsable(textField.getText()) == false) {
            textField.setStyle("-fx-text-fill: red;");
        } else if (actualSudokuBoard.isCheckBoardTrue() == true && textField.isEditable() == true) {
            textField.setStyle("-fx-text-fill: green;");
        } else if (actualSudokuBoard.getColumn(i).verify() == false) {
            checkElement(textField, actualSudokuBoard.getColumn(i)
                    .getList().toArray(new SudokuField[0]));
        } else if (actualSudokuBoard.getRow(i).verify() == false) {
            checkElement(textField, actualSudokuBoard.getRow(i)
                    .getList().toArray(new SudokuField[0]));
        } else if (actualSudokuBoard.getBox(i, j).verify() == false) {
            checkElement(textField, actualSudokuBoard.getBox(i, j)
                    .getList().toArray(new SudokuField[0]));
        }
    }

    private void checkElement(TextField textField, SudokuField [] sudokuFields) {
        for (int i = 0; i < 9; i++) {
            if (NumberUtils.isParsable(textField.getText()) == true) {
                if (sudokuFields[i].getFieldValue() == Integer.parseInt(textField.getText())
                        && textField.isEditable() == true) {
                    textField.setStyle("-fx-text-fill: blue;");
                }
            }
        }
    }

    @FXML
    private void switchToSecondaryE() throws FormFileException {
        logger.info("set easy level");
        this.level = Poziom.LATWY;
        menuButton.hide();
        App.setRoot("secondary");
    }

    @FXML
    private void switchToSecondaryM() throws FormFileException {
        logger.info("set medium level");
        this.level = Poziom.SREDNI;
        menuButton.hide();
        App.setRoot("secondary");
    }

    @FXML
    private void switchToSecondaryH() throws FormFileException {
        logger.info("set hard level");
        this.level = Poziom.TRUDNY;
        menuButton.hide();
        App.setRoot("secondary");
    }

    @FXML
    private void onActionButtonAuthors() {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(
                new Text("Student 1: " + authors.getObject("author1")
                        + "\nStudent 2: " + authors.getObject("author2")));
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }

    @FXML
    private void onActionChangeLanguage() throws FormFileException {
        if (Locale.getDefault().equals(new Locale("pl_PL")) == true) {
            App.changeLanguage("en_EN", "primary");
        } else {
            App.changeLanguage("pl_PL", "primary");
        }
    }

}