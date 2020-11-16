package com.mycompany.sudoku;

import java.util.ArrayList;

public class SudokuArrayList<OBJECT> extends ArrayList<OBJECT> {
    // "Object" nie przechodzi JaCoCo


    @Override
    public boolean add(OBJECT e) {
        if (this.size() < 9) {
            return super.add(e);
        }
        return false;
    }
}