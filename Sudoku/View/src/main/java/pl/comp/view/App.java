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

public class App extends Application {

    private static Scene scene;

    private static Logger logger = (Logger) LogManager.getLogger(App.class.getName());

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFxml("primary"), 500, 600);
        logger.info("START stage " + stage + " with scene primary");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        logger.info(fxml + " element set as root");
        scene.setRoot(loadFxml(fxml));
    }

    private static Parent loadFxml(String fxml) throws IOException {
        ResourceBundle resources  = ResourceBundle.getBundle("pl.comp.view.bundles.bundle");
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"), resources);
        return fxmlLoader.load();
    }

    public static void changeLanguage(String locale, String actualFxml) throws IOException {
        logger.info("change locale to " + locale);
        Locale.setDefault(new Locale(locale));
        loadFxml(actualFxml);
        setRoot(actualFxml);
    }

    public static void main(String[] args) {
        Locale.setDefault(new Locale("pl"));
        launch();
    }

}