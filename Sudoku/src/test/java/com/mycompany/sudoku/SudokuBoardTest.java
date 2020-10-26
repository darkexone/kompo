package com.mycompany.sudoku;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.sudoku.SudokuBoard;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author student
 */
public class SudokuBoardTest {
    
    public SudokuBoardTest() {
    }


    /**
     * Test of fillBoard method, of class SudokuBoard.
     */
    @org.junit.jupiter.api.Test
    public void testFillBoard() {
        System.out.println("fillBoard");
        SudokuBoard instance = new SudokuBoard();
        instance.fixedStart();
        //instance.printSudoku();
        
        boolean expResult = true;
        boolean result = instance.fillBoard();
        //instance.printSudoku();
        assertEquals(expResult, result);
        
    }
    
    /**
     * All rows,columns and boxes give equal sums
     */
    @org.junit.jupiter.api.Test
    public void testEqualSum() {
        System.out.println("equalSum");
        SudokuBoard instance = new SudokuBoard();
        instance.fixedStart();
        //instance.printSudoku();
        
        int expResult = 405;
        instance.fillBoard();
       
        
        int sum=0;
        
        for (int i=0;i<9;i++){
            for (int j=0; j<9;j++){
                sum+=instance.getCell(j,i); 
            }
        }
        
        //instance.printSudoku();
        assertEquals(expResult, sum);
        
    }
    
    /**
     * Test of check method when false, of class SudokuBoard.
     */
    @org.junit.jupiter.api.Test
    public void testCheck_false() {
        System.out.println("check_false");
        int row = 0;
        int column = 0;
        int i = 0;
        SudokuBoard instance = new SudokuBoard();
        instance.fixedStart();
        
        boolean expResult = false;
        boolean result = instance.check(row, column, i);
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of check method when true, of class SudokuBoard.
     */
    @org.junit.jupiter.api.Test
    public void testCheck_true() {
        System.out.println("check_true");
        int row = 0;
        int column = 0;
        int i = 1;
        SudokuBoard instance = new SudokuBoard();
        instance.fixedStart();
        
        boolean expResult = true;
        boolean result = instance.check(row, column, i);
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of randomStart method, of class SudokuBoard.
     */
    @org.junit.jupiter.api.Test
    public void testRandomStart() {
        System.out.println("randomStart");
        SudokuBoard instance = new SudokuBoard();
        instance.randomStart();
        
        boolean check=false;
        boolean expResult=true;
        for(int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                if (instance.getCell(i,j)!=0){
                    check=true;
                    break;
                }
            }
        }
        
        assertEquals(expResult, check);
    }

    /**
     * Test of getCell method, of class SudokuBoard.
     */
    @org.junit.jupiter.api.Test
    public void testGetCell() {
        System.out.println("getCell");
        SudokuBoard instance = new SudokuBoard();
        instance.fixedStart();
        
        int check=instance.getCell(2, 1);
        int expResult=3;
        
        
        assertEquals(expResult, check);
    }
    
    /**
     * Different boards test
     */
    @org.junit.jupiter.api.Test
    public void testDifference() {
        System.out.println("difference");
        
        SudokuBoard instance1 = new SudokuBoard();
        instance1.randomStart();
        
        SudokuBoard instance2 = new SudokuBoard();
        instance2.randomStart();
        
        boolean check = false;
        boolean expResult = true;
        
        for(int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                if (instance1.getCell(i,j)==instance2.getCell(i,j)){
                    check=true;
                    break;
                }
            }
        }
        assertEquals(expResult, check);
        
    }
}
