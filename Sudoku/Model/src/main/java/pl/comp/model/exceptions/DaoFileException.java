package pl.comp.model.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class DaoFileException extends DaoException {

    private static Logger logger = (Logger)
            LogManager.getLogger(DaoFileException.class.getName());

    public DaoFileException(String msg, Throwable cause) {
        super(msg, cause);
        logger.error(msg);
    }
}
