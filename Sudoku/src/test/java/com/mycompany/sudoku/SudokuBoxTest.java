package com.mycompany.sudoku;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

class SudokuBoxTest {

    Random random = new Random();

    @org.junit.jupiter.api.Test
    public void testVerify_false() {

        System.out.println("verify_false");

        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard instance = new SudokuBoard(solver,false);
        //fixedStart(instance);

        SudokuField [] rzad = new SudokuField[9];

        for (int i = 0; i < 9; i++) {
            rzad[i] = new SudokuField();
            rzad[i].setFieldValue(0);
        }

        int rr = random.nextInt(9);
        int rc = random.nextInt(9);
        int randomOdd = random.nextInt(9) + 1;
        while (rc==rr) {
            rc = random.nextInt(9);
        }
        rzad[rr].setFieldValue(randomOdd);
        rzad[rc].setFieldValue(randomOdd);

        SudokuBox sudokuBox = new SudokuBox(rzad);
        boolean expResult = false;
        boolean result = sudokuBox.verify();
        assertEquals(expResult, result);

        }

    @org.junit.jupiter.api.Test
    public void testVerify_true() {

        System.out.println("verify_false");

        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard instance = new SudokuBoard(solver,false);
        //fixedStart(instance);

        SudokuField [] rzad = new SudokuField[9];

        for (int i = 0; i < 9; i++) {
            rzad[i] = new SudokuField();
            rzad[i].setFieldValue(i+1);
        }

        SudokuBox sudokuBox = new SudokuBox(rzad);
        boolean expResult = true;
        boolean result = sudokuBox.verify();
        assertEquals(expResult, result);

    }

}