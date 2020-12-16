/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.comp.model;

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

    @org.junit.jupiter.api.Test
    public void testHashCode() {
        SudokuField [] field1 = new SudokuField[9];
        SudokuField [] field2 = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            field1[i] = new SudokuField();
            field2[i] = new SudokuField();

            field1[i].setFieldValue(i);
            field2[i].setFieldValue(9-i);
        }
        SudokuRow row1 = new SudokuRow(field1);
        SudokuRow row2 = new SudokuRow(field2);
        assertNotEquals(row1.hashCode(), row2.hashCode());
    }

    @org.junit.jupiter.api.Test
    public void testToString() {
        SudokuField [] field1 = new SudokuField[9];
        SudokuField [] field2 = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            field1[i] = new SudokuField();
            field2[i] = new SudokuField();

            field1[i].setFieldValue(i);
            field2[i].setFieldValue(9-i);
        }
        SudokuRow row1 = new SudokuRow(field1);
        SudokuRow row2 = new SudokuRow(field1);

        assertEquals(row1.toString(), row2.toString());
        field1[0].setFieldValue(2);
        SudokuRow row3 = new SudokuRow(field2);
        assertNotEquals(row1.toString(), row3.toString());
    }

    @org.junit.jupiter.api.Test
    public void testEquals() {
        SudokuField [] field1 = new SudokuField[9];
        SudokuField [] field2 = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            field1[i] = new SudokuField();
            field2[i] = new SudokuField();

            field1[i].setFieldValue(i);
            field2[i].setFieldValue(9-i);
        }
        SudokuRow row1 = new SudokuRow(field1);
        SudokuRow row2 = new SudokuRow(field2);
        SudokuRow row3 = new SudokuRow(field2);
        SudokuColumn column1 = new SudokuColumn(field1);

        assertFalse(row1.equals(null));
        assertTrue(row1.equals(row1));
        assertFalse(row1.equals(row2));
        assertFalse(row1.equals(column1));
        assertTrue(row2.equals(row3));
    }

    @org.junit.jupiter.api.Test
    public void testClone() throws CloneNotSupportedException {
        SudokuField [] field1 = new SudokuField[9];

        for (int i = 0; i < 9; i++) {
            field1[i] = new SudokuField();
            field1[i].setFieldValue(i);
        }

        SudokuRow row1 = new SudokuRow(field1);
        SudokuRow row2 = row1.clone();
        assertTrue(row1.equals(row2));

        for (int i = 0; i < 9; i++) {
            field1[i].setFieldValue(9-i);
        }
        row1 = new SudokuRow(field1);
        assertFalse(row1.equals(row2));
    }
}
