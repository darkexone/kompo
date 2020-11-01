package com.mycompany.sudoku;

public class BacktrackingSudokuSolver implements SudokuSolver {


    public boolean solve(SudokuBoard board) {
        for (int row = 0; row < 9; row++) {
        for (int col = 0; col < 9; col++) {
            if (board.board[col][row] == 0) {
                for (int number = 1;number <= 9;number++) {
                    if (board.check(row, col, number)) {
                        board.board[col][row] = number;
                        if (solve(board)) {
                            return true;
                        } else {
                            board.board[col][row] = 0;
                        }
                    }
                }
                return false;
            }
        }
    }
    return true;
    }
}
