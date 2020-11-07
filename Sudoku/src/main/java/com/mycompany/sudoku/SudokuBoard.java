package com.mycompany.sudoku;

import java.util.Random;

public class SudokuBoard {
    
    Random random = new Random();
    
    //Object[][] board = new Object[9][9];

    SudokuField[][] board = new SudokuField[9][9];

    SudokuSolver solver;

    public SudokuBoard(SudokuSolver sudokuSolver) {
        solver = sudokuSolver;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = new SudokuField();
            }
        }
    }

    public boolean solveGame() {
        return solver.solve(this);
    }
    
    public boolean check(int row, int column, int i) {
        
        //row
        for (int x = 0;x < 9;x++) {
            if (get(x,row) == i) {
                return false;
            }
        }
        
        //column
        for (int y = 0;y < 9;y++) {
            if (get(column,y) == i) {
                return false;
            }
        }
        
        //3x3
        int rzad = (row / 3) * 3;
        int kol = (column / 3) * 3;
        for (int m = rzad; m < rzad + 3; m++) {
            for (int n  = kol;n < kol + 3; n++) {
                if (get(n,m) == i) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private boolean checkBoard() {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!(getRow(i).verify() || getColumn(j).verify() || getBox(i,j).verify())) {

                    return false;
                }
            }
        }
        return true;
    }

    public void randomStart() {

        //ustawianie losowych liczb w losowych komórkach
        for (int r = 0; r < 9; r++) {
            int rr = random.nextInt(9);
            int rc = random.nextInt(9);
            //board[rc][rr] = random.nextInt(9) + 1;
            int randomOdd = random.nextInt(9) + 1;
            if (check(rr, rc, randomOdd)) {
                set(rc, rr, randomOdd);
            }
        }
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
                box[i+j].setFieldValue(board[i + rzad][j + kol].getFieldValue());
            }
        }

        SudokuBox sudokuBox = new SudokuBox(box);

        return sudokuBox;
    }

}


