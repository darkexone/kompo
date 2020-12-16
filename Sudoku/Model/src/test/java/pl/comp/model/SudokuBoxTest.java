package pl.comp.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

class SudokuBoxTest {

    Random random = new Random();

    @org.junit.jupiter.api.Test
    public void testVerify_false() {

        System.out.println("verify_false");

        //fixedStart(instance);

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

        SudokuBox sudokuBox = new SudokuBox(rzad);
        boolean expResult = false;
        boolean result = sudokuBox.verify();
        assertEquals(expResult, result);

        }

    @org.junit.jupiter.api.Test
    public void testVerify_true() {

        System.out.println("verify_true");

        SudokuField[] rzad = new SudokuField[9];

        for (int i = 0; i < 9; i++) {
            rzad[i] = new SudokuField();
            rzad[i].setFieldValue(i+1);
        }

        SudokuBox sudokuBox = new SudokuBox(rzad);
        boolean expResult = true;
        boolean result = sudokuBox.verify();
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
        SudokuBox box1 = new SudokuBox(field1);
        SudokuBox box2 = new SudokuBox(field2);
        SudokuBox box3 = new SudokuBox(field2);
        assertNotEquals(box1.hashCode(), box2.hashCode());
        assertEquals(box2.hashCode(), box3.hashCode());
        assertTrue(box2.equals(box3));
    }

    @org.junit.jupiter.api.Test
    public void testToString() {
        SudokuField [] field1 = new SudokuField[9];
        SudokuField [] field2 = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            field1[i] = new SudokuField();
            field2[i] = new SudokuField();
        }
        SudokuBox box1 = new SudokuBox(field1);
        SudokuBox box2 = new SudokuBox(field2);
        assertEquals(box1.toString(), box2.toString());

        field1[0].setFieldValue(2);
        SudokuBox box3 = new SudokuBox(field1);
        assertNotEquals(box1.toString(), box3.toString());
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
        SudokuBox box1 = new SudokuBox(field1);
        SudokuBox box2 = new SudokuBox(field2);
        SudokuBox box3 = new SudokuBox(field2);
        SudokuColumn column1 = new SudokuColumn(field1);

        assertFalse(box1.equals(null));
        assertTrue(box1.equals(box1));
        assertFalse(box1.equals(box2));
        assertFalse(box1.equals(column1));
        assertEquals(box2.hashCode(), box3.hashCode());
        assertTrue(box2.equals(box3));
    }

}