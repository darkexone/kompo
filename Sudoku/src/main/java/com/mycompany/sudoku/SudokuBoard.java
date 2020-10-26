package com.mycompany.sudoku;

import java.util.Random;

public class SudokuBoard {
    
    Random random = new Random();
    
    private int[][] board = new int[9][9];
    
    public boolean fillBoard() {
    for (int row = 0;row < 9;row++) {
        for (int col = 0;col < 9;col++) {
            if (board[col][row] == 0) {
                for (int number = 1;number <= 9;number++) {
                    if (check(row, col, number)) {
                        board[col][row] = number;
                        if (fillBoard()) {
                            return true;
                        } else {
                            board[col][row] = 0;
                        }
                    }
                }
                return false;
            }
        }
    }
    return true;
}
    
    public boolean check(int row, int column, int i) {
        
        //row
        for (int x = 0;x < 9;x++) {
            if (board[x][row] == i) {
                return false;
            }
        }
        
        //column
        for (int y = 0;y < 9;y++) {
            if (board[column][y] == i) {
                return false;
            }
        }
        
        //3x3
        int rzad = (row / 3) * 3;
        int kol = (column / 3) * 3;
        for (int m = rzad; m < rzad + 3; m++) {
            for (int n  = kol;n < kol + 3; n++) {
                if (board[n][m] == i) {
                    return false;
                }
            }
        }
        
        return true;
    }

    public void printSudoku() {
        for (int i = 0;i < 9;i++) {
            for (int j = 0;j < 9;j++) {
                System.out.print(getCell(i,j));
            }
            System.out.println("");
        }
    }
    
    public void randomStart() {
        
        //zainicjowanie zerami
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[j][i] = 0;
            }
        }
        
        //ustawianie losowych liczb w losowych komórkach
        for (int r = 0; r < 9; r++) {
            int rr = random.nextInt(9);
            int rc = random.nextInt(9);
            board[rc][rr] = random.nextInt(9) + 1;
        }
}
    
    public void fixedStart() {
        //zainicjowanie zerami
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[j][i] = 0;
            }
        }
        
        board[2][1] = 3;
        board[4][2] = 6;
        board[0][0] = 8;
    }
    
    public int getCell(int column, int row) {
        return board[column][row];
    }

}


