package com.mycompany.sudoku;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileSudokuBoardDao implements Dao<SudokuBoard> { //TODO ,AutoCloseable { <-- co z tym?

    private String filename;
    private String absolutePath;

    public FileSudokuBoardDao(String filename) {
        this.filename = filename;
        absolutePath = System.getProperty("user.home") + File.separator + this.filename;
    }

    @Override
    public SudokuBoard read() {
        SudokuBoard sudokuBoardInstance = null;

        try (FileReader fileReader = new FileReader(absolutePath)) {
            int ch = fileReader.read();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sudokuBoardInstance.set(i,j,ch);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sudokuBoardInstance;
    }

    @Override
    public void write(SudokuBoard sudokuBoardInstance) {

        try (FileWriter fileWriter = new FileWriter(absolutePath)) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    fileWriter.write(sudokuBoardInstance.get(i,j));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize(); //TODO ZAPYTAC NA KONSULTACJACH
    }
}
