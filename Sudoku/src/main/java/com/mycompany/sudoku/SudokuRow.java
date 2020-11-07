package com.mycompany.sudoku;

public class SudokuRow {

    private SudokuField[] row = new SudokuField[9];

    public SudokuRow(SudokuField[] field) {

        for (int i = 0; i < 9; i++) {
            row[i] = new SudokuField();
        }

        for (int i = 0; i < 9; i++) {
            row[i].setFieldValue(field[i].getFieldValue());
        }
    }

    public boolean verify() {

        //Row
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += row[i].getFieldValue();
        }

        if (sum == 45) {
            return true;
        } else {
            return false;
        }
    }
}