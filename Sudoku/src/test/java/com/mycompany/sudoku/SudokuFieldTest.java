/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sudoku;

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
    
   
    
}
