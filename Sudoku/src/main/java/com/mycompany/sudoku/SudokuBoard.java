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
}


