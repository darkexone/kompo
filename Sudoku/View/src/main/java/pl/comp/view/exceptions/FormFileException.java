package pl.comp.view.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class FormFileException extends ViewExceptions {

    private static Logger logger = (Logger)
            LogManager.getLogger(FormFileException.class.getName());

    public FormFileException(String msg, Throwable cause) {
        super(msg, cause);
        logger.error(msg);
    }
}