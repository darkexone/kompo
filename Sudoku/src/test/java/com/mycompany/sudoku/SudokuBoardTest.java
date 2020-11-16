package com.mycompany.sudoku;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;


public class SudokuBoardTest {

    Random random = new Random();
    
    public void randomStart(SudokuBoard board) {

        //ustawianie losowych liczb w losowych komórkach
        for (int r = 0; r < 9; r++) {
            int rr = random.nextInt(9);
            int rc = random.nextInt(9);
            int randomOdd = random.nextInt(9) + 1;
            if (check(rr, rc, randomOdd, board)) {
                board.set(rc, rr, randomOdd);
            }
        }
    }

    private boolean check(int row, int column, int i, SudokuBoard board) {

        //row
        for (int x = 0;x < 9;x++) {
            if (board.get(x,row) == i) {
                return false;
            }
        }

        //column
        for (int y = 0;y < 9;y++) {
            if (board.get(column,y) == i) {
                return false;
            }
        }

        //3x3
        int rzad = (row / 3) * 3;
        int kol = (column / 3) * 3;
        for (int m = rzad; m < rzad + 3; m++) {
            for (int n  = kol;n < kol + 3; n++) {
                if (board.get(n,m) == i) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void fixedStart(SudokuBoard board) {

        board.set(0,0,8);
        board.set(2,1,3);
        board.set(4,2,6);
    }

    public void printSudoku(SudokuBoard board) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board.get(j, i));
            }
            System.out.println();
        }
    }

    /** tests starts here */

    @org.junit.jupiter.api.Test
    public void testSolveGame() {
        System.out.println("solveGame");
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard instance = new SudokuBoard(solver,false);
        randomStart(instance);

        boolean expResult = true;
        boolean Result = instance.solveGame();

        assertEquals(expResult, Result);
    }

    @org.junit.jupiter.api.Test
    public void testEqualSum() {
        System.out.println("equalSum");
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard instance = new SudokuBoard(solver,false);
        randomStart(instance);
        
        int expResult = 405;
        instance.solveGame();

        int sum = 0;
        
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                sum += instance.get(j, i);
            }
        }

        assertEquals(expResult, sum);
    }

    @org.junit.jupiter.api.Test
    public void testCheckBoard_false_row() {
        System.out.println("check_false_row");
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard instance = new SudokuBoard(solver,false);
        
        instance.set(1,0,2);
        instance.set(0,0,2);

        boolean expResult = false;   
        boolean result = instance.isCheckBoardTrue();
     
        assertEquals(expResult, result);
    }
    
    @org.junit.jupiter.api.Test
    public void testCheckBoard_false_Column() {
        System.out.println("check_false_column");
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard instance = new SudokuBoard(solver,false);

        solver.solve(instance);
        instance.set(0,8, instance.get(0, 0));

        boolean expResult = false;
        boolean result = instance.isCheckBoardTrue();
     
        assertEquals(expResult, result);
    }
    
    @org.junit.jupiter.api.Test
    public void testCheckBoard_false_Box() {
        System.out.println("check_false_box");
        int i = 0;
        int m = 0;
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard instance = new SudokuBoard(solver,false);

        for(int j = 1; j < 10; j++) {
            instance.set(i,0, j);
            i++;
        }
        
        for(int n = 1; n < 10; n++) {
            instance.set(0, m, n);
            m++;
        }
        
        instance.set(0,0,0);

        boolean expResult = false;   
        boolean result = instance.isCheckBoardTrue();
     
        assertEquals(expResult, result);
        
    }

    @org.junit.jupiter.api.Test
    public void testCheckBoard_true() {
        System.out.println("check_true");

        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard instance = new SudokuBoard(solver,false);
        instance.solveGame();
        
        boolean expResult = true;
        boolean result = instance.isCheckBoardTrue();

        assertEquals(expResult, result);
    }

    /*@org.junit.jupiter.api.Test
    public void testRandomStart() {
        System.out.println("randomStart");
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard instance = new SudokuBoard(solver,false);
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
    */

    @org.junit.jupiter.api.Test
    public void testGet() {
        System.out.println("getCell");
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard instance = new SudokuBoard(solver,false);
        randomStart(instance);
        instance.set(2,3,3);
        
        int check=instance.get(2, 3);
        int expResult = 3;

        assertEquals(expResult, check);
    }

    @org.junit.jupiter.api.Test
    public void testDifference() {
        System.out.println("difference");

        BacktrackingSudokuSolver solver1 = new BacktrackingSudokuSolver();
        BacktrackingSudokuSolver solver2 = new BacktrackingSudokuSolver();
        
        SudokuBoard instance1 = new SudokuBoard(solver1,false);
        randomStart(instance1);
        
        SudokuBoard instance2 = new SudokuBoard(solver2,false);
        randomStart(instance2);
        
        boolean check = false;
        boolean expResult = true;
        
        for(int i=0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if (instance1.get(i, j)==instance2.get(i,j)){
                    check=true;
                    break;
                }
            }
        }

        assertEquals(expResult, check);
    }

    @org.junit.jupiter.api.Test
    void testSet() {
        System.out.println("setCell");
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard instance = new SudokuBoard(solver,false);
        instance.set(6,6,6);

        assertEquals(instance.get(6,6), 6);
    }
    
    @org.junit.jupiter.api.Test
    void testGetBox_true() {
        System.out.println("getBox_true");
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard instance = new SudokuBoard(solver,false);
        instance.solveGame();
        
        boolean expResult = true;
        boolean result = instance.getBox(0, 0).verify();

        assertEquals(result, expResult);
    }
    
    @org.junit.jupiter.api.Test
    void testGetBox_false() {
        System.out.println("getBox_false");
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard instance = new SudokuBoard(solver,false);

        instance.set(1,0,2);
        instance.set(0,0,2);
        
        boolean expResult = false;
        boolean result = instance.getBox(0, 0).verify();

        assertEquals(result, expResult);
    }
    
    @org.junit.jupiter.api.Test
    void testGetRow_false() {
        System.out.println("getRow_false");
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard instance = new SudokuBoard(solver,false);

        instance.set(1,0,2);
        instance.set(0,0,2);

        boolean expResult = false;

        assertEquals(instance.getRow(0).verify(), expResult);
    }
    
    @org.junit.jupiter.api.Test
    void testGetRow_true() {
        System.out.println("getRow_true");
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard instance = new SudokuBoard(solver,false);
        instance.solveGame();

        boolean expResult = true;

        assertEquals(instance.getRow(0).verify(), expResult);
    }
    
    @org.junit.jupiter.api.Test
    void testGetColumn_false() {
        System.out.println("getColumn_false");
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard instance = new SudokuBoard(solver,false);

        instance.set(0,1,2);
        instance.set(0,0,2);

        boolean expResult = false;

        assertEquals(instance.getColumn(0).verify(), expResult);
    }
    
    @org.junit.jupiter.api.Test
    void testGetColumn_true() {
        System.out.println("getColumn_true");
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard instance = new SudokuBoard(solver,false);
        instance.solveGame();

        boolean expResult = true;

        assertEquals(instance.getColumn(0).verify(), expResult);
    }
    
     @org.junit.jupiter.api.Test
    public void testUpdate_false() {
        System.out.println("update_false");
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard instance = new SudokuBoard(solver,true);

        instance.set(0, 0, 2);
        instance.set(1, 0, 2);

        assertEquals(instance.isUpdate,true);
    }
    
    @org.junit.jupiter.api.Test
    public void testUpdate_true() {
        System.out.println("update_true");
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard instance = new SudokuBoard(solver,true);
        randomStart(instance);
        solver.solve(instance);

        instance.set(0, 0, instance.get(0, 0));
        //instance.set(0,0,1);
        
        assertEquals(instance.isUpdate,false);
    }
}
