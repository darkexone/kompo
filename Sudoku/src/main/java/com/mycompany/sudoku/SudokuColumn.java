package com.mycompany.sudoku;

public class SudokuColumn {

    private SudokuField[] Column = new SudokuField[9];

    public SudokuColumn(int y) {
        for (int i=0;i<9;i++) {
            Column[i]=field[y][i];
        }
    }

    public boolean verify() {


        //column
        for (int y = 0;y < 9;y++) {
            if (get(column,y) == i) {
                return false;
            }
        }

        return true;
    }
}
