package pl.comp.view.exceptions;

import java.io.IOException;

public class ViewExceptions extends IOException {

    public ViewExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
