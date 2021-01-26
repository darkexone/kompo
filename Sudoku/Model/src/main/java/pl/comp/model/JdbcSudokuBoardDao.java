package pl.comp.model;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import pl.comp.model.exceptions.DaoJdbcException;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {
    private static Logger logger = (Logger) LogManager.getLogger(SudokuBoard.class.getName());

    //private static final String URL
    // = "jdbc:derby:" + "c:\\Users\\huber\\Desktop\\demo" + ";create=true";
    private String url;

    private Statement jdbcStatement;
    private String fileName;
    private Connection connection;
    private PreparedStatement stmt;
    private PreparedStatement preparedStatement;
    private ResourceBundle resourceBundle
            = ResourceBundle.getBundle("pl.comp.model.bundles.bundle");

    public JdbcSudokuBoardDao(String name) throws DaoJdbcException {

        this.fileName = name;
        url = "jdbc:derby:" + System.getProperty("user.dir")
                + File.separator + "database" + ";create=true";
        try {
            this.connection = DriverManager.getConnection(url);
            this.connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoJdbcException(resourceBundle
                    .getObject("SQLConnectException").toString(), e);
        }
    }

    public void write(SudokuBoard sudokuBoard) throws DaoJdbcException {
        try {
            jdbcStatement = connection.createStatement();
            jdbcStatement.execute("CREATE TABLE SudokuNames"
                    + "(id int primary key GENERATED ALWAYS as identity, name varchar(50))");
            jdbcStatement.execute(
                    "CREATE TABLE SudokuValues(board_id int references SudokuNames (id),"
                            + " fields varchar(162))");
            logger.debug(resourceBundle
                    .getObject("SQLCreateSuccess"));
        } catch (SQLException e) {
            logger.warn(resourceBundle.getObject("SQLCreateException").toString());
        }

        try {
            jdbcStatement = connection.createStatement();
            preparedStatement = connection.prepareStatement(
                    "SELECT id FROM SudokuNames WHERE name=?");
            preparedStatement.setString(1, fileName);
            preparedStatement.executeQuery();
            stmt = connection.prepareStatement("select id from SudokuNames WHERE name=?");
            stmt.setString(1, fileName);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            //rs.getInt(1); //XDDDDDDDDDDDDDDDDDDDDD
            preparedStatement = connection.prepareStatement(
                    "UPDATE SudokuValues SET fields=? WHERE board_id=?");

            StringBuilder fieldsConcat = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (sudokuBoard.get(i, j) < 10) {
                        fieldsConcat.append("0");
                        fieldsConcat.append(sudokuBoard.get(i, j));
                    } else {
                        fieldsConcat.append(sudokuBoard.get(i, j));
                    }
                }
            }
            preparedStatement.setString(1, fieldsConcat.toString());
            preparedStatement.setString(2, String.valueOf(rs.getInt(1)));
            preparedStatement.executeUpdate();

            connection.commit();
            preparedStatement.close();
            rs.close();
            stmt.close();
            logger.info(resourceBundle
                    .getObject("SQLUpdateSuccess"));
            return;
        } catch (SQLException e) {
            logger.error(resourceBundle.getObject("SQLUpdateException").toString());
        }

        try {
            jdbcStatement = connection.createStatement();
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO SudokuNames (name) VALUES (?)");
            preparedStatement.setString(1, fileName);
            preparedStatement.executeUpdate();
            connection.commit();

            stmt = connection.prepareStatement("select id from SudokuNames WHERE name=?");
            stmt.setString(1, fileName);
            ResultSet rs = stmt.executeQuery(); //id
            rs.next();
            StringBuilder fieldsConcat = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (sudokuBoard.get(i, j) < 10) {
                        fieldsConcat.append("0");
                        fieldsConcat.append(sudokuBoard.get(i, j));
                    } else {
                        fieldsConcat.append(sudokuBoard.get(i, j));
                    }
                }
            }

            preparedStatement = connection.prepareStatement(
                    "INSERT INTO SudokuValues VALUES (?, ?)");

            preparedStatement.setString(1, String.valueOf(rs.getInt(1)));
            preparedStatement.setString(2, fieldsConcat.toString());
            preparedStatement.executeUpdate();
            connection.commit();
            preparedStatement.close();
            logger.debug(resourceBundle
                    .getObject("SQLInsertSuccess"));
        } catch (SQLException e) {
            throw new DaoJdbcException(resourceBundle
                    .getObject("SQLInsertException").toString(), e);
        }
    }


    public SudokuBoard read() throws DaoJdbcException {

        SudokuBoard sudokuBoard =
                new SudokuBoard(new BacktrackingSudokuSolver(), false);
        String fields = null;

        int[] tab = new int[81];

        try {
            jdbcStatement = connection.createStatement();
            stmt = connection.prepareStatement(
                    "SELECT id from SudokuNames WHERE name=?");
            stmt.setString(1, fileName);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            preparedStatement = connection.prepareStatement(
                    "SELECT fields FROM SudokuValues WHERE board_id=?");
            preparedStatement.setString(1, String.valueOf(rs.getInt(1)));
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                fields = resultSet.getString(1);
            }

            for (int i = 0; i < 162; i += 2) {
                tab[i / 2] = Integer.parseInt(fields.substring(i, i + 2));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DaoJdbcException(resourceBundle
                    .getObject("SQLReadFailException").toString(), e);
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard.set(i, j, tab[i * 9 + j]);
            }
        }
        return sudokuBoard;
    }

    public void close() {
        try {
            jdbcStatement.close();
            connection.close();
            logger.info(resourceBundle
                    .getObject("SQLCloseSuccess"));
        } catch (SQLException e) {
            logger.fatal(resourceBundle
                    .getObject("SQLCloseFail"));
        }
    }

    public String getFileName() {
        return fileName;
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize();
    }
}
