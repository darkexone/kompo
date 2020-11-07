package com.mycompany.sudoku;

public class SudokuColumn {

    private SudokuField[] column = new SudokuField[9];

    public SudokuColumn(SudokuField[] field) {

        for (int i = 0; i < 9; i++) {
            column[i] = new SudokuField();
        }

        for (int i = 0; i < 9; i++) {
            column[i].setFieldValue(field[i].getFieldValue());
        }
    }

    public boolean verify() {

        //column
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += column[i].getFieldValue();
        }

        if (sum != 45) {
            return false;
        }

        return true;
    }
}
