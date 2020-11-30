package com.mycompany.sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FileSudokuBoardDaoTest {

    @Test
    public void testFinalize() throws Throwable {
        BacktrackingSudokuSolver solver1 = new BacktrackingSudokuSolver();
        SudokuBoard instance1 = new SudokuBoard(solver1,false);
        String fileName = "testFinalizeFile";
        FileSudokuBoardDao file1 = new FileSudokuBoardDao(fileName);

        file1.write(instance1);
        file1.finalize();
        Files.deleteIfExists(Paths.get(System.getProperty("user.dir") + File.separator + fileName));
    }

    @Test
    public void testWriteAndRead() throws Throwable {
        BacktrackingSudokuSolver solver1 = new BacktrackingSudokuSolver();
        SudokuBoard instance1 = new SudokuBoard(solver1,false);
        String fileName = "testWriteAndReadFile";
        FileSudokuBoardDao file1 = new FileSudokuBoardDao(fileName);

        file1.write(instance1);
        SudokuBoard instance2 = file1.read();
        assertTrue(instance1.equals(instance2));

        Files.deleteIfExists(Paths.get(System.getProperty("user.dir") + File.separator + fileName));
    }

    @org.junit.jupiter.api.Test
    public void testHashCode() {
        FileSudokuBoardDao file1 = new FileSudokuBoardDao("testFile1");
        FileSudokuBoardDao file2 = new FileSudokuBoardDao("testFile2");
        FileSudokuBoardDao file3 = new FileSudokuBoardDao("testFile2");

        assertFalse(file1.equals(file2));
        assertNotEquals(file1.hashCode(), file2.hashCode());
        assertEquals(file2.hashCode(), file3.hashCode());
        assertTrue(file2.equals(file3));
    }

    @org.junit.jupiter.api.Test
    public void testToString(){
        FileSudokuBoardDao file1 = new FileSudokuBoardDao("testFile1");
        FileSudokuBoardDao file2 = new FileSudokuBoardDao("testFile2");
        FileSudokuBoardDao file3 = new FileSudokuBoardDao("testFile2");

        assertNotEquals(file1.toString(), file2.toString());
        assertEquals(file2.toString(), file3.toString());
    }

    @org.junit.jupiter.api.Test
    public void testEquals(){
        FileSudokuBoardDao file1 = new FileSudokuBoardDao("testFile1");
        FileSudokuBoardDao file2 = new FileSudokuBoardDao("testFile2");
        FileSudokuBoardDao file3 = new FileSudokuBoardDao("testFile2");

        assertFalse(file1.equals(null));
        assertFalse(file1.equals(new BacktrackingSudokuSolverTest()));
        assertTrue(file1.equals(file1));
        assertFalse(file1.equals(file2));
        assertTrue(file2.equals(file3));
    }
}
