package com.mycompany.sudoku;

//import java.lang.reflect.Array;
//import java.util.Arrays;
//import java.util.Arrays;
//import java.util.ArrayList;
//import java.util.List;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.platform.commons.util.ToStringBuilder;

//import static java.util.ArrayList.*;
//import static java.util.Arrays.asList;


public class SudokuBoard implements Observer, Serializable {
    
    Random random = new Random();

    //private SudokuArrayList<SudokuArrayList<SudokuField>> board = new SudokuArrayList<>();
    private SudokuArrayList<SudokuArrayList<SudokuField>> board = new SudokuArrayList<>();
    //private SudokuArrayList<SudokuArrayList<SudokuField>> board2 = new SudokuArrayList<>();

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


        for (int i = 0; i < 9; i++) {
            board.add(new SudokuArrayList<>());
            for (int j = 0; j < 9; j++) {
                board.get(i).add(new SudokuField());
                board.get(i).get(j).addObserver(this);
            }
        }
        System.out.println("rozmiar board: " + board.size());
        System.out.println("rozmiar board.get(0): " + board.get(0).size());
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
        return board.get(column).get(row).getFieldValue();
    }

    public void set(int column, int row, int value) {
        board.get(column).get(row).setFieldValue(value);
    }

    public SudokuRow getRow(int y) {

        SudokuField [] rzad = new SudokuField[9];

        for (int i = 0; i < 9; i++) {
            rzad[i] = new SudokuField();
            rzad[i].setFieldValue(board.get(i).get(y).getFieldValue());
        }

        SudokuRow sudokuRow = new SudokuRow(rzad);

        return sudokuRow;
    }

    public SudokuColumn getColumn(int x) {

        SudokuField [] kolumna = new SudokuField[9];

        for (int i = 0; i < 9; i++) {
            kolumna[i] = new SudokuField();
        }

        for (int i = 0; i < 9; i++) {
            kolumna[i].setFieldValue(board.get(x).get(i).getFieldValue());
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
                box[ (3 * i) + j].setFieldValue(board.get(i + rzad).get(j + kol).getFieldValue());
            }
        }
        //for(int i=0;i<9;i++) {
        //    System.out.print(box[i]);
        //}

        SudokuBox sudokuBox = new SudokuBox(box);

        return sudokuBox;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("SudokuSolver", solver)
                .append("ArrayListBoard", board)
                .toString();
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }

        SudokuBoard rhs = (SudokuBoard) obj;
        return new EqualsBuilder()
                .append(solver, rhs.solver)
                .append(board, rhs.board)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(solver)
                .append(board)
                .toHashCode();
    }

}