package pl.comp.model.exceptions;

import java.sql.SQLException;

public class DaoException extends Throwable {

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
