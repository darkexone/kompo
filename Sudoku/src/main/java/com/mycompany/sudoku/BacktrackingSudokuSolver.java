package com.mycompany.sudoku;

public class BacktrackingSudokuSolver implements SudokuSolver {

    private boolean check(int row, int column, int i, SudokuBoard board) {

        //row
        for (int x = 0;x < 9;x++) {
            if (board.get(x,row) == i) {
                return false;
            }
        }

        //column
        for (int y = 0;y < 9;y++) {
            if (board.get(column,y) == i) {
                return false;
            }
        }

        //3x3
        int rzad = (row / 3) * 3;
        int kol = (column / 3) * 3;
        for (int m = rzad; m < rzad + 3; m++) {
            for (int n  = kol;n < kol + 3; n++) {
                if (board.get(n,m) == i) {
                    return false;
                }
            }
        }

        return true;
    }


    public boolean solve(SudokuBoard board) {
        for (int row = 0; row < 9; row++) {
        for (int col = 0; col < 9; col++) {
            if (board.get(col,row) == 0) {
                for (int number = 1;number <= 9;number++) {
                    if (check(row, col, number, board)) {
                        board.set(col,row,number);
                        if (solve(board)) {
                            return true;
                        } else {
                            board.set(col,row,0);
                        }
                    }
                }
                return false;
            }
        }
    }
    return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                //.append("SudokuSolver", this.getClass())
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

        return true;
    }
}
