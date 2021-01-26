package pl.comp.model;

import pl.comp.model.exceptions.DaoJdbcException;

public class SudokuBoardDaoFactory {

    private SudokuBoardDaoFactory() {
    }

    public static Dao<SudokuBoard> getFileDao(String fileName) {
        return new FileSudokuBoardDao(fileName);
    }

    public static JdbcSudokuBoardDao getJdbcDao(String filename) throws DaoJdbcException {
        JdbcSudokuBoardDao jdbcSudokuBoardDao = new JdbcSudokuBoardDao(filename);
        return jdbcSudokuBoardDao;
    }
}
