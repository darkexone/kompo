package pl.comp.model;

import org.junit.jupiter.api.Test;
import pl.comp.model.exceptions.DaoJdbcException;

import java.sql.Connection;
import java.sql.*;

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


        String URL = "jdbc:derby:c:\\Users\\huber\\Desktop\\demo;create=false";
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

    @Test
    public void displayTables() throws SQLException {
        String URL = "jdbc:derby:c:\\Users\\huber\\Desktop\\demo;create=false";
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
    }



}
