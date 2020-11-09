package com.mycompany.sudoku;

public class SudokuBox {

    private final SudokuField[] box = new SudokuField[9];

    public SudokuBox(SudokuField[] field) {

        for (int i = 0; i < 9; i++) {
            box[i] = new SudokuField();
            box[i].setFieldValue(field[i].getFieldValue());
            }
        }
    
    

    public boolean verify() {

        //3x3
        int sum = 0;

        //int [] checkBoard = new int[10];

        for (int i = 0; i < 9; i++) {
                sum += box[i].getFieldValue();
                //checkBoard[Box[i][j].getFieldValue()]++;
            }

        //for (int i = 1; i < 10; i++) {
        // if (checkBoard[i] != 1) {
        //    return false;
        // }
        //}

        if (sum != 45) {
            System.out.print(sum);
            return false;
        }

        return true;
    }
}