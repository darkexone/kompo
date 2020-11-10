/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sudoku;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;

/**
 *
 * @author student
 */
public class SudokuRowTest {
    
    public SudokuRowTest() {
    }

     Random random = new Random();

    /**
     * Test of verify method, of class SudokuRow.
     */
    @Test
    public void testVerify_false() {
        System.out.println("verify_false");

        SudokuField[] rzad = new SudokuField[9];

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

        SudokuRow sudokuRow = new SudokuRow(rzad);
        boolean expResult = false;
        boolean result = sudokuRow.verify();
        assertEquals(expResult, result);
    }
    
    @org.junit.jupiter.api.Test
    public void testVerify_true() {

        System.out.println("verify_false");

        SudokuField[] rzad = new SudokuField[9];

        for (int i = 0; i < 9; i++) {
            rzad[i] = new SudokuField();
            rzad[i].setFieldValue(i+1);
        }

        SudokuRow sudokuRow = new SudokuRow(rzad);
        boolean expResult = true;
        boolean result = sudokuRow.verify();
        assertEquals(expResult, result);

    }
}
