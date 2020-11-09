package com.mycompany.sudoku;

import static org.junit.jupiter.api.Assertions.*;


class BacktrackingSudokuSolverTest {

    public void printSudoku(SudokuBoard board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board.get(i, j));
            }
            System.out.println("");
        }
    }

    @org.junit.jupiter.api.Test
    public void solve() {
        System.out.println("solve");
        BacktrackingSudokuSolver testSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard instance = new SudokuBoard(testSudokuSolver);

        System.out.println("przed rozwiazaniem:");
        printSudoku(instance);
        //SudokuField field1 = new SudokuField();
        //System.out.println(field1.getFieldValue());

        //instance.getCell(0, 0);
        instance.randomStart();
        System.out.println("po random starcie:");
        printSudoku(instance);

        boolean expResult = true;
        boolean result = testSudokuSolver.solve(instance);
        System.out.println("po rozwiazaniu:");
        printSudoku(instance);
        assertEquals(expResult, result);

    }
}