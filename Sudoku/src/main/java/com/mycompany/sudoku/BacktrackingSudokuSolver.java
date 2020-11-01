/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sudoku;

/**
 *
 * @author student
 */
public class BacktrackingSudokuSolver implements SudokuSolver {
    public boolean solve(SudokuBoard board){
        for (int row = 0;row < 9;row++) {
        for (int col = 0;col < 9;col++) {
            if (board.board[col][row] == 0) {
                for (int number = 1;number <= 9;number++) {
                    if (board.check(row, col, number)) {
                        board.board[col][row] = number;
                        if (board.fillBoard()) {
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
