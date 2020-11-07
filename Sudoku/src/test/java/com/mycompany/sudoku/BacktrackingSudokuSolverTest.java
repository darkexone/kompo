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


    @org.junit.jupiter.api.Test
    public void solve() {


        System.out.println("solve");
        BacktrackingSudokuSolver testSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard instance = new SudokuBoard(testSudokuSolver);
        System.out.println("przed rozwiazaniem:");
        printSudoku(instance);
        //SudokuField field1 = new SudokuField();
        //System.out.println(field1.getFieldValue());
        //System.out.println(instance.board[0][0].getFieldValue());
        instance.randomStart();
        //fixedStart(instance);
        System.out.println("po random starcie:");
        printSudoku(instance);
        boolean expResult = true;
        boolean result = testSudokuSolver.solve(instance);
        System.out.println("po rozwiazaniu:");
        printSudoku(instance);
        assertEquals(expResult, result);

    }
}