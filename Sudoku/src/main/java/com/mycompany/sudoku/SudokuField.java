package com.mycompany.sudoku;

public class SudokuField {

    public int value;

    public SudokuField() {
        value = 0;
    }

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int newValue) {
        value = newValue;
    }
}
