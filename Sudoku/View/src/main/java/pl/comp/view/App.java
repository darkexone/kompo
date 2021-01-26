package pl.comp.view;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import pl.comp.view.exceptions.FormFileException;

public class App extends Application {

    private static Scene scene;

    private static Logger logger = (Logger) LogManager.getLogger(App.class.getName());

    ResourceBundle exceptionResources = ResourceBundle.getBundle("pl.comp.view.bundles.bundle");

    @Override
    public void start(Stage stage) throws FormFileException {
        logger.info("START stage " + stage + " with scene primary");
        try {
            scene = new Scene(loadFxml("primary"), 500, 600);
        } catch (IOException e) {
            throw new FormFileException(exceptionResources
                    .getObject("FormFileException").toString(), e);
        }
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    static void setRoot(String fxml) throws FormFileException {
        logger.info(fxml + " element set as root");
        try {
        scene.setRoot(loadFxml(fxml));
        } catch (IOException e) {
            throw new FormFileException(ResourceBundle.getBundle("pl.comp.view.bundles.bundle")
                    .getObject("FormFileException").toString(), e);
        }
    }

    private static Parent loadFxml(String fxml) throws FormFileException {
        ResourceBundle resources = ResourceBundle.getBundle("pl.comp.view.bundles.bundle");
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"), resources);
        try {
            return fxmlLoader.load();
        } catch (IOException e) {
            throw new FormFileException(resources
                    .getObject("FormFileException").toString(), e);
        }
    }

    public static void changeLanguage(String locale, String actualFxml) throws FormFileException {
        logger.info("change locale to " + locale);
        Locale.setDefault(new Locale(locale));

        try {
            loadFxml(actualFxml);
            setRoot(actualFxml);
        } catch (IOException e) {
            throw new FormFileException(ResourceBundle.getBundle("pl.comp.view.bundles.bundle")
                    .getObject("FormFileException").toString(), e);
        }
    }

    public static void main(String[] args) {
        Locale.setDefault(new Locale("pl_pl"));
        launch();
    }

}