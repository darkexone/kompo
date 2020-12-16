package pl.comp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Random;

class BacktrackingSudokuSolverTest {

    Random random = new Random();
    
    public void printSudoku(SudokuBoard board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board.get(i, j));
            }
            System.out.println("");
        }
    }

    public void randomStart(SudokuBoard board) {

        //ustawianie losowych liczb w losowych komórkach
        for (int r = 0; r < 9; r++) {
            int rr = random.nextInt(9);
            int rc = random.nextInt(9);
            //board[rc][rr] = random.nextInt(9) + 1;
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
        SudokuBoard instance = new SudokuBoard(testSudokuSolver,false);
        System.out.println("przed rozwiazaniem:");
        //printSudoku(instance);
        //SudokuField field1 = new SudokuField();
        //System.out.println(field1.getFieldValue());
        //System.out.println(instance.board[0][0].getFieldValue());
        randomStart(instance);
        //fixedStart(instance);
        System.out.println("po random starcie:");
        //printSudoku(instance);
        boolean expResult = true;
        boolean result = testSudokuSolver.solve(instance);
        System.out.println("po rozwiazaniu:");
        //printSudoku(instance);
        assertEquals(expResult, result);

    }

    @Test
    public void testToString() {
        BacktrackingSudokuSolver solver1 = new BacktrackingSudokuSolver();
        BacktrackingSudokuSolver solver2 = new BacktrackingSudokuSolver();
        assertEquals(solver1.toString(), solver2.toString());
    }

    @Test
    public void testEquals() {
        BacktrackingSudokuSolver solver1 = new BacktrackingSudokuSolver();
        BacktrackingSudokuSolver solver2 = new BacktrackingSudokuSolver();

        assertFalse(solver1.equals(null));
        assertTrue(solver1.equals(solver1));
        assertTrue(solver1.equals(solver2));
        assertFalse(solver1.equals(new SudokuBoard(solver1,false)));
    }
}