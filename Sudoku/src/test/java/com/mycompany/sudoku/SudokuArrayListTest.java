package com.mycompany.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SudokuArrayListTest {

    @Test
    public void add_true () {
        System.out.println("add_true");

        SudokuArrayList<SudokuArrayList<SudokuField>> instance = new SudokuArrayList<>();


        boolean Result = instance.add(new SudokuArrayList<SudokuField>());
        boolean expResult = true;

        assertEquals(expResult, Result);
    }

    @Test
    public void add_false () {
        System.out.println("add_false");

        SudokuArrayList<SudokuArrayList<SudokuField>> instance = new SudokuArrayList<>();

        for (int i=0;i<9;i++) {
            instance.add(new SudokuArrayList<SudokuField>());
        }

        boolean Result = instance.add(new SudokuArrayList<SudokuField>());
        boolean expResult = false;

        assertEquals(expResult, Result);
    }
}
