package com.mycompany.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BacktrackingSudokuSolverTest {

    @org.junit.jupiter.api.Test
    public void solve() {
        System.out.println("solve");
        BacktrackingSudokuSolver testSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard instance = new SudokuBoard(testSudokuSolver);
        instance.randomStart();

        boolean expResult = true;
        boolean result = testSudokuSolver.solve(instance);
        assertEquals(expResult, result);

    }
}