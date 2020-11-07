package com.mycompany.sudoku;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author student
 */
public class SudokuBoardTest {
    
    public SudokuBoardTest() {
    }

    public void fixedStart(SudokuBoard board) {
        //zainicjowanie zerami
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board.set(i,j,0);
            }
        }

        board.set(2,1,3);
        board.set(4,2,6);
        board.set(0,0,8);
    }

    public void printSudoku(SudokuBoard board) {
        for (int i = 0;i < 9;i++) {
            for (int j = 0;j < 9;j++) {
                System.out.print(board.get(i,j));
            }
            System.out.println("");
        }
    }


    /**
     * Test of fillBoard method, of class SudokuBoard.
     */
    @org.junit.jupiter.api.Test
    public void testSolveGame() {
        System.out.println("solveGame");
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard instance = new SudokuBoard(solver);
        instance.randomStart();
        boolean expResult = true;
        boolean Result = instance.solveGame();


        assertEquals(expResult, Result);
        
    }
    
    /**
     * All rows,columns and boxes give equal sums
     */
    @org.junit.jupiter.api.Test
    public void testEqualSum() {
        System.out.println("equalSum");
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard instance = new SudokuBoard(solver);
        instance.randomStart();
        //instance.printSudoku();
        
        int expResult = 405;
        instance.solveGame();
       
        
        int sum=0;
        
        for (int i=0;i<9;i++){
            for (int j=0; j<9;j++){
                sum+=instance.get(j,i);
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
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard instance = new SudokuBoard(solver);
        fixedStart(instance);
        
        boolean expResult = false;
        boolean result = instance.isCheckBoardTrue();
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

        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard instance = new SudokuBoard(solver);
        fixedStart(instance);
        instance.solveGame();
        
        boolean expResult = true;
        boolean result = instance.isCheckBoardTrue();
        assertEquals(expResult, result);
        
    }
    
    /**
     * Test of randomStart method, of class SudokuBoard.
     */
    @org.junit.jupiter.api.Test
    public void testRandomStart() {
        System.out.println("randomStart");
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard instance = new SudokuBoard(solver);
        instance.randomStart();
        
        boolean check=false;
        boolean expResult=true;
        for(int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                if (instance.get(i,j)!=0){
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
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard instance = new SudokuBoard(solver);
        instance.randomStart();
        instance.set(2,3,3);
        
        int check=instance.get(2, 3);
        int expResult=3;
        
        
        assertEquals(expResult, check);
    }
    
    /**
     * Different boards test
     */
    @org.junit.jupiter.api.Test
    public void testDifference() {
        System.out.println("difference");

        BacktrackingSudokuSolver solver1 = new BacktrackingSudokuSolver();
        BacktrackingSudokuSolver solver2 = new BacktrackingSudokuSolver();
        
        SudokuBoard instance1 = new SudokuBoard(solver1);
        instance1.randomStart();
        
        SudokuBoard instance2 = new SudokuBoard(solver2);
        instance2.randomStart();
        
        boolean check = false;
        boolean expResult = true;
        
        for(int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                if (instance1.get(i,j)==instance2.get(i,j)){
                    check=true;
                    break;
                }
            }
        }
        assertEquals(expResult, check);
        
    }


    @org.junit.jupiter.api.Test
    void testSetCell() {
        System.out.println("setCell");
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard instance = new SudokuBoard(solver);
        instance.set(6,6,6);

        assertEquals(instance.get(6,6), 6);
    }
}
