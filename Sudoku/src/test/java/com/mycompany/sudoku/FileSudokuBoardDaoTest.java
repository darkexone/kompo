package com.mycompany.sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FileSudokuBoardDaoTest {

    String pathPrefix = "target" + File.separator + "FileSudokuBoardDaoTest";

    @BeforeAll
    static void doBefore() throws IOException {
        String path = System.getProperty("user.dir") + File.separator + "target" + File.separator + "FileSudokuBoardDaoTest";
        if (Files.exists(Path.of(path))) {
            Files.walk(Path.of(path))
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        }
        File file = new File(path);
        boolean bool = file.mkdir();
        if(bool) {
            System.out.println("Directory created successfully");
        } else {
            System.out.println("Sorry couldn’t create specified directory");
        }
    }

    @Test
    public void testFinalize() throws Throwable {
        BacktrackingSudokuSolver solver1 = new BacktrackingSudokuSolver();
        SudokuBoard instance1 = new SudokuBoard(solver1,false);
        String fileName = pathPrefix + File.separator + "testFinalizeFile";
        FileSudokuBoardDao file1 = new FileSudokuBoardDao(fileName);

        file1.write(instance1);
        file1.finalize();
    }

    @Test
    public void testWriteAndRead() throws Throwable {
        BacktrackingSudokuSolver solver1 = new BacktrackingSudokuSolver();
        SudokuBoard instance1 = new SudokuBoard(solver1,false);
        String fileName = pathPrefix + File.separator + "testWriteAndReadFile";
        FileSudokuBoardDao file1 = new FileSudokuBoardDao(fileName);

        file1.write(instance1);
        SudokuBoard instance2 = file1.read();
        assertTrue(instance1.equals(instance2));
    }

    @Test
    public void testExceptionsNone() throws IOException {
        String fileName = pathPrefix + File.separator + "testPass";
        BacktrackingSudokuSolver solver1 = new BacktrackingSudokuSolver();
        SudokuBoard instance1 = new SudokuBoard(solver1,false);
        Files.deleteIfExists(Paths.get(System.getProperty("user.dir") + File.separator + pathPrefix + File.separator + fileName));

        assertDoesNotThrow(() -> {
            SudokuBoardDaoFactory.getFileDao(fileName).write(instance1);
        });
        assertDoesNotThrow(() -> {
                SudokuBoardDaoFactory.getFileDao(fileName).read();
        });
    }

    @Test
    public void testExceptionsIOException() throws IOException {
        String fileName = pathPrefix + File.separator + "testFailIOException";
        BacktrackingSudokuSolver solver1 = new BacktrackingSudokuSolver();
        SudokuBoard instance1 = new SudokuBoard(solver1,false);

        Files.deleteIfExists(Paths.get(System.getProperty("user.dir") + File.separator + pathPrefix + File.separator + fileName));
        new RandomAccessFile(System.getProperty("user.dir") + File.separator + fileName, "rw").getChannel().lock();

        assertThrows(IOException.class,() -> {
            SudokuBoardDaoFactory.getFileDao(fileName).write(instance1);
        });

        assertThrows(IOException.class,() -> {
            SudokuBoardDaoFactory.getFileDao(fileName).write(instance1);
        });

        assertThrows(IOException.class,() -> {
            SudokuBoardDaoFactory.getFileDao(fileName).write(instance1);
        });
    }

    @Test
    public void testExceptionsFileNotFoundException() throws IOException {
        String fileName = "target" + File.separator + "nonExistingPath" + File.separator + "testFailFileNotFoundException";
        Files.deleteIfExists(Paths.get(System.getProperty("user.dir") + File.separator + pathPrefix + File.separator + fileName));

        assertThrows(FileNotFoundException.class,() -> {
            SudokuBoardDaoFactory.getFileDao(fileName).read();
        });

        BacktrackingSudokuSolver solver1 = new BacktrackingSudokuSolver();
        SudokuBoard instance1 = new SudokuBoard(solver1,false);
        assertThrows(FileNotFoundException.class,() -> {
            SudokuBoardDaoFactory.getFileDao(fileName).write(instance1);
        });
    }

    //@Test
    //public void testExceptionsClassNotFoundException() throws IOException {
    //    String fileName = "testXD";
    //    String filePath = System.getProperty("user.dir") + File.separator + fileName;
    //
    //    assertThrows(ClassNotFoundException.class,() -> {
    //        SudokuBoardDaoFactory.getFileDao(fileName).read();
    //    });
    //}

    //@Test
    //public void testClose() {
    //    SudokuBoardDaoFactory.getFileDao("testCloseFile").close();
    //}

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
