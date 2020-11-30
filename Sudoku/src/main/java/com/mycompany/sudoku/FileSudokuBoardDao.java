package com.mycompany.sudoku;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
    public SudokuBoard read() throws IOException, ClassNotFoundException {
        SudokuBoard sudokuBoardInstance = null;

        try (FileInputStream fileIn = new FileInputStream(absolutePath);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {

            sudokuBoardInstance = (SudokuBoard) in.readObject();
            System.out.println("read file: " + absolutePath);
        }

        return sudokuBoardInstance;
    }

    @Override
    public void write(SudokuBoard sudokuBoardInstance) throws IOException {

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
    public String toString() {
        return new ToStringBuilder(this)
                .append("filename ", filename)
                .append("asbolutePath ", absolutePath)
                .toString();
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }

        FileSudokuBoardDao rhs = (FileSudokuBoardDao) obj;
        return new EqualsBuilder()
                .append(filename, rhs.filename)
                .append(absolutePath, rhs.absolutePath)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(filename)
                .append(absolutePath)
                .toHashCode();
    }
}
