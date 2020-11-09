package com.mycompany.sudoku;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;



public class SudokuBoard implements Observer {
    
    Random random = new Random();
    
    //Object[][] board = new Object[9][9];

    private SudokuField[][] board = new SudokuField[9][9];
    
    public boolean mode = false;

    public boolean isCheckBoardTrue() {
        return checkBoard();
    }

    private SudokuSolver solver;

    public void update(Observable obj, Object arg) {
       if (this.mode) {
        if (this.checkBoard() == false) {
            System.out.println("Blad przy zmianie");
        }
       }
    }

    public SudokuBoard(SudokuSolver sudokuSolver, boolean mode) {
        solver = sudokuSolver;
        this.mode = mode;
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = new SudokuField();
                board[i][j].addObserver(this);
            }
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
        return board[column][row].getFieldValue();
    }

    public void set(int column, int row, int value) {
        board[column][row].setFieldValue(value);
    }

    public SudokuRow getRow(int y) {

        SudokuField [] rzad = new SudokuField[9];

        for (int i = 0; i < 9; i++) {
            rzad[i] = new SudokuField();
            rzad[i].setFieldValue(board[i][y].getFieldValue());
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
            kolumna[i].setFieldValue(board[x][i].getFieldValue());
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
                box[ (3 * i) + j].setFieldValue(board[i + rzad][j + kol].getFieldValue());
            }
        }
        //for(int i=0;i<9;i++) {
        //    System.out.print(box[i]);
        //}

        SudokuBox sudokuBox = new SudokuBox(box);

        return sudokuBox;
    }

}


