package pl.comp.model;

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

        SudokuBoardDaoFactory.getFileDao("testFile").write(instance1);
        SudokuBoard dao1 = SudokuBoardDaoFactory.getFileDao("testFile").read();

        Files.deleteIfExists(Paths.get(System.getProperty("user.dir") + File.separator + "testFile"));

        assertTrue(instance1.equals(dao1));
    }
}
