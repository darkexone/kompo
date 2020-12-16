package pl.comp.view;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondaryE() throws IOException {
        App.setRoot("secondaryE");
    }

    @FXML
    private void switchToSecondaryM() throws IOException {
        App.setRoot("secondaryM");
    }

    @FXML
    private void switchToSecondaryH() throws IOException {
        App.setRoot("secondaryH");
    }
}
