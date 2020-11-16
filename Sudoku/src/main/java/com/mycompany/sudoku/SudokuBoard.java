package com.mycompany.sudoku;

//import java.lang.reflect.Array;
//import java.util.Arrays;
//import java.util.Arrays;
//import java.util.ArrayList;
//import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

//import static java.util.ArrayList.*;
//import static java.util.Arrays.asList;


public class SudokuBoard implements Observer {
    
    Random random = new Random();

    //private SudokuArrayList<SudokuArrayList<SudokuField>> board = new SudokuArrayList<>();
    private final List<SudokuField> board = Arrays.asList(new SudokuField[81]);

    //List<String> fixedSizeList = asList(new String[100]);
    //private ArrayList<ArrayList<SudokuField>> board = new ArrayList<>();

    //private SudokuField[][] board = new SudokuField[9][9];

    public boolean isUpdate = false;
    
    public boolean mode;

    public boolean isCheckBoardTrue() {
        return checkBoard();
    }

    private SudokuSolver solver;

    public void update(Observable obj, Object arg) {
        if (this.mode) {
        if (this.checkBoard() == false) {
            System.out.println("Blad przy zmianie");
            isUpdate = true;
        }
       }
    }

    // private ArrayList<ArrayList<SudokuField>> board = new ArrayList<>(9);

    public SudokuBoard(SudokuSolver sudokuSolver, boolean mode) {
        solver = sudokuSolver;
        this.mode = mode;

            for (int i = 0; i < 81; i++) {
                this.board.set(i, new SudokuField());
                this.board.get(i).addObserver(this);
            }
        }


    public boolean solveGame() {
        return solver.solve(this);
    }
    
    private boolean checkBoard() {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!getRow(i).verify()) {
                    //System.out.print(i);
                    return false;
                }
                
                if (!getColumn(j).verify()) {
                    //System.out.print(j);
                    return false;
                }
                
                if (!getBox(i,j).verify()) {
                    //System.out.print("Box" + i + " " +j );
                    return false;
                }
            }
        }
        return true;
    }

    
    
    public int get(int column, int row) {
        return board.get((row * 9) + column).getFieldValue();
    }

    public void set(int column, int row, int value) {
        board.get((row * 9) + column).setFieldValue(value);
    }

    public SudokuRow getRow(int y) {

        SudokuField [] rzad = new SudokuField[9];

        for (int i = 0; i < 9; i++) {
            rzad[i] = new SudokuField();
            rzad[i].setFieldValue(board.get(i + (y * 9)).getFieldValue());
        }

        SudokuRow sudokuRow = new SudokuRow(rzad);

        return sudokuRow;
    }

    public SudokuColumn getColumn(int x) {

        SudokuField [] kolumna = new SudokuField[9];

        for (int i = 0; i < 9; i++) {
            kolumna[i] = new SudokuField();
            kolumna[i].setFieldValue(board.get((i * 9) + x).getFieldValue());
        }

        SudokuColumn sudokuColumn = new SudokuColumn(kolumna);

        return sudokuColumn;
    }

    public SudokuBox getBox(int x, int y) {

        SudokuField [] box = new SudokuField[9];

        for (int i = 0; i < 9; i++) {
            box[i] = new SudokuField();
        }

        int rzad = (x / 3) * 3;
        int kol = (y / 3) * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                box[ (3 * i) + j].setFieldValue(board.get((9 * i) + j).getFieldValue());
            }
        }
        //for(int i=0;i<9;i++) {
        //    System.out.print(box[i]);
        //}

        SudokuBox sudokuBox = new SudokuBox(box);

        return sudokuBox;
    }

}