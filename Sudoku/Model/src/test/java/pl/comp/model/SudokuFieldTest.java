/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.comp.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author student
 */
public class SudokuFieldTest {
    
    public SudokuFieldTest() {
    }

    /**
     * Test of getFieldValue method, of class SudokuField.
     */
    @Test
    public void testGetFieldValue() {
        System.out.println("getFieldValue");
        SudokuField instance = new SudokuField();
        
        assertEquals(instance.getFieldValue(),0);
    }

    /**
     * Test of setFieldValue method, of class SudokuField.
     */
    @Test
    public void testSetFieldValue() {
        System.out.println("setFieldValue");
        
        SudokuField instance = new SudokuField();
        
        if(instance.getFieldValue()==0) {
            instance.setFieldValue(3);
            assertEquals(instance.getFieldValue(),3);
        }
        
        else {
            fail("Poczatkowa wartosc nie jest zero");
        }
        
    }


    @org.junit.jupiter.api.Test
    public void testHashCode() {
        SudokuField field1 = new SudokuField();
        SudokuField field2 = new SudokuField();
        field1.setFieldValue(0);
        field2.setFieldValue(9);

        assertNotEquals(field1.hashCode(), field2.hashCode());
    }

    @org.junit.jupiter.api.Test
    public void testToString() {
        SudokuField field1 = new SudokuField();
        SudokuField field2 = new SudokuField();
        field1.setFieldValue(0);
        field2.setFieldValue(9);

        assertNotEquals(field1.toString(), field2.toString());
    }

    @org.junit.jupiter.api.Test
    public void testEquals() {
        SudokuField field1 = new SudokuField();
        SudokuField field2 = new SudokuField();
        SudokuField field3 = new SudokuField();
        field1.setFieldValue(0);
        field2.setFieldValue(9);
        field3.setFieldValue(9);

        SudokuField [] fieldArray1 = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            fieldArray1[i] = new SudokuField();
            fieldArray1[i].setFieldValue(i);
        }
        SudokuColumn column1 = new SudokuColumn(fieldArray1);

        assertFalse(field1.equals(null));
        assertTrue(field1.equals(field1));
        assertFalse(field1.equals(field2));
        assertFalse(field1.equals(column1));
        assertTrue(field2.equals(field3));
    }
}
