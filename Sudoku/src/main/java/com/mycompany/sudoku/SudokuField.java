package com.mycompany.sudoku;

import java.util.Observable;

public class SudokuField extends Observable {

    private int value;

    public SudokuField() {
        value = 0;
    }

    public int getFieldValue() {
        return value;
    }

    public void setFieldValue(int newValue) {
        //if (this.value != 0) {
        
        //}
        value = newValue;
        setChanged();
        notifyObservers();
    }
}
