package pl.comp.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.comp.model.exceptions.DaoJdbcException;

import java.io.File;
import java.sql.Connection;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JdbcSudokuBoardDaoTest {

    @Test
    public void testWrite() throws SQLException, DaoJdbcException {
        BacktrackingSudokuSolver solver1 = new BacktrackingSudokuSolver();
        SudokuBoard instance1 = new SudokuBoard(solver1,false);
        SudokuBoard instance2 = new SudokuBoard(solver1,false);

        try (JdbcSudokuBoardDao file1 = new JdbcSudokuBoardDao("testWrite1")) {
            file1.write(instance1);
            instance1.set(1,1,1);
            file1.write(instance1);
        }

        try (JdbcSudokuBoardDao file2 = new JdbcSudokuBoardDao("testWrite2")) {
            instance2.set(1,1,2);
            file2.write(instance2);
        }


        String URL = "jdbc:derby:" + System.getProperty("user.dir") + File.separator + "database" + ";create=true";
        Connection connection = DriverManager.getConnection(URL);
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM SudokuNames");
        ResultSet rs = stmt.executeQuery();
        PreparedStatement stmt2 = connection.prepareStatement("SELECT * FROM SudokuValues");
        ResultSet rs2 = stmt2.executeQuery();

        while (rs.next()) {
            rs2.next();
            System.out.print("ID=" + rs.getInt(1) + " name=\"" + rs.getString(2) + "\"");
            System.out.println("values=" + rs2.getString(2));
        }

        stmt.close();
        rs.close();

        JdbcSudokuBoardDao file1 = new JdbcSudokuBoardDao("testWrite2");
        SudokuBoard XD = file1.read();
        System.out.println(XD.get(1, 1));

    }

    //@Test
    //public void displayTables() throws SQLException {
    //    String URL = "jdbc:derby:" + System.getProperty("user.dir") + File.separator + "database" + ";create=false";
    //    Connection connection = DriverManager.getConnection(URL);
    //    PreparedStatement stmt = connection.prepareStatement("SELECT * FROM SudokuNames");
    //    ResultSet rs = stmt.executeQuery();
    //    PreparedStatement stmt2 = connection.prepareStatement("SELECT * FROM SudokuValues");
    //    ResultSet rs2 = stmt2.executeQuery();
//
    //    while (rs.next()) {
    //        rs2.next();
    //        System.out.print("ID=" + rs.getInt(1) + " name=\"" + rs.getString(2) + "\"");
    //        System.out.println("values=" + rs2.getString(2));
    //    }
//
    //    stmt.close();
    //    rs.close();
    //}

    @Test
    public void odczytZapis() throws DaoJdbcException, SQLException {
        BacktrackingSudokuSolver solver1 = new BacktrackingSudokuSolver();
        SudokuBoard instance1 = new SudokuBoard(solver1,false);
        SudokuBoard instance2;

        try (JdbcSudokuBoardDao file1 = new JdbcSudokuBoardDao("testWrite1")) {
            file1.write(instance1);
            file1.write(instance1);
            instance2=file1.read();
            //file1.close();
            //assertTrue(file1.connectionClose());
        }

        Assertions.assertTrue(instance1.equals(instance2));
        assertFalse(instance1 == instance2);

    }

    @Test
    public void JdbcFail() throws DaoJdbcException {
        SudokuBoard instance2;
        String URL = "jdbc:derby:" + System.getProperty("user.dir") + "database" + ";create=false";
        assertThrows(SQLException.class,() -> {
            Connection connection = DriverManager.getConnection(URL);
        });

        BacktrackingSudokuSolver solver1 = new BacktrackingSudokuSolver();
        SudokuBoard instance1 = new SudokuBoard(solver1,false);
        try (JdbcSudokuBoardDao file1 = new JdbcSudokuBoardDao("testWrite1")) {
            file1.write(instance1);

            file1.write(instance1);

        }

        try (JdbcSudokuBoardDao file2 = new JdbcSudokuBoardDao("testFail")) {
            assertThrows(DaoJdbcException.class,() -> {
                SudokuBoard instance3 = file2.read();
            });
        }
    }


}
