package com.mycompany.sudoku;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardDaoFactoryTest {

    @Test
    public void testGet() throws Throwable {
        BacktrackingSudokuSolver solver1 = new BacktrackingSudokuSolver();
        SudokuBoard instance1 = new SudokuBoard(solver1,false);
        FileSudokuBoardDao file1 = new FileSudokuBoardDao("testFile");

        file1.write(instance1);


        Dao file2 = SudokuBoardDaoFactory.getFileDao("testFile");
        Files.deleteIfExists(Paths.get(System.getProperty("user.dir") + File.separator + "testFile"));

        assertTrue(file1.equals(file2));
    }
}
