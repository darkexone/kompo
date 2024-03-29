/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.comp.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Random;

/**
 *
 * @author student
 */
public class SudokuColumnTest {
    
    public SudokuColumnTest() {
    }
    
    Random random = new Random();

    /**
     * Test of verify method, of class SudokuColumn.
     */
    @Test
    public void testVerify_false() {
        //System.out.println("verify_false");

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

        SudokuColumn sudokuColumn = new SudokuColumn(rzad);
        boolean expResult = false;
        boolean result = sudokuColumn.verify();
        assertEquals(expResult, result);
    }
    
    @org.junit.jupiter.api.Test
    public void testVerify_true() {

        //System.out.println("verify_false");

        SudokuField[] rzad = new SudokuField[9];

        for (int i = 0; i < 9; i++) {
            rzad[i] = new SudokuField();
            rzad[i].setFieldValue(i+1);
        }

        SudokuColumn sudokuColumn = new SudokuColumn(rzad);
        boolean expResult = true;
        boolean result = sudokuColumn.verify();
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
        SudokuColumn column1 = new SudokuColumn(field1);
        SudokuColumn column2 = new SudokuColumn(field2);
        assertNotEquals(column1.hashCode(), column2.hashCode());
    }

    @org.junit.jupiter.api.Test
    public void testToString() {
        SudokuField [] field1 = new SudokuField[9];
        SudokuField [] field2 = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            field1[i] = new SudokuField();
            field2[i] = new SudokuField();
        }
        SudokuColumn column1 = new SudokuColumn(field1);
        SudokuColumn column2 = new SudokuColumn(field2);

        assertEquals(column1.toString(), column2.toString());

        field1[0].setFieldValue(2);
        SudokuColumn column3 = new SudokuColumn(field1);
        assertNotEquals(column1.toString(), column3.toString());
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
        SudokuColumn column1 = new SudokuColumn(field1);
        SudokuColumn column2 = new SudokuColumn(field2);
        SudokuColumn column3 = new SudokuColumn(field2);
        SudokuBox box1 = new SudokuBox(field1);

        assertFalse(column1.equals(null));
        assertTrue(column1.equals(column1));
        assertFalse(column1.equals(column2));
        assertFalse(column1.equals(box1));
        assertTrue(column2.equals(column3));
    }

    @org.junit.jupiter.api.Test
    public void testClone() throws CloneNotSupportedException {
        SudokuField [] field1 = new SudokuField[9];

        for (int i = 0; i < 9; i++) {
            field1[i] = new SudokuField();
            field1[i].setFieldValue(i);
        }

        SudokuColumn column1 = new SudokuColumn(field1);
        SudokuColumn column2 = column1.clone();
        assertTrue(column1.equals(column2));

        for (int i = 0; i < 9; i++) {
            field1[i].setFieldValue(9-i);
        }
        column1 = new SudokuColumn(field1);
        assertFalse(column1.equals(column2));
    }

    @org.junit.jupiter.api.Test
    public void testGetList() {
        SudokuField [] field1 = new SudokuField[9];

        for (int i = 0; i < 9; i++) {
            field1[i] = new SudokuField();
            field1[i].setFieldValue(i);
        }

        List<SudokuField> ColumnList = new SudokuColumn(field1).getList();

        for (int i = 0; i < 9; i++) {
            assertTrue(field1[i].getFieldValue() == ColumnList.get(i).getFieldValue());
        }
    }
}
