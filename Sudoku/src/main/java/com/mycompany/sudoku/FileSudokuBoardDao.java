package com.mycompany.sudoku;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.junit.platform.commons.util.ToStringBuilder;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {

    private String filename;
    private String absolutePath;

    public FileSudokuBoardDao(String filename) {
        this.filename = filename;
        this.absolutePath = System.getProperty("user.dir") + File.separator + this.filename;
        // .. + "Serializable" + File.separator + this.filename;
    }

    @Override
    public SudokuBoard read() throws Throwable {
        SudokuBoard sudokuBoardInstance = null;

        try (FileInputStream fileIn = new FileInputStream(absolutePath);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {

            sudokuBoardInstance = (SudokuBoard) in.readObject();
            System.out.println("read file: " + absolutePath);
        }

        return sudokuBoardInstance;
    }

    @Override
    public void write(SudokuBoard sudokuBoardInstance) throws Throwable {

        try (FileOutputStream fileOut = new FileOutputStream(absolutePath);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

            out.writeObject(sudokuBoardInstance);
            System.out.println("write file: " + absolutePath);
        }
    }

    @Override
    public void finalize() throws Throwable {
        new FileInputStream(absolutePath).close();
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
