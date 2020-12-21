package pl.comp.view;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFxml("primary"), 500, 600);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFxml(fxml));
    }

    private static Parent loadFxml(String fxml) throws IOException {
        ResourceBundle resources  = ResourceBundle.getBundle("pl.comp.view.bundles.bundle");
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"), resources);
        return fxmlLoader.load();
    }

    public static void changeLanguage(String locale, String actualFxml) throws IOException {

        Locale.setDefault(new Locale(locale));
        loadFxml(actualFxml);
        setRoot(actualFxml);
    }

    public static void main(String[] args) {
        Locale.setDefault(new Locale("pl"));
        launch();
    }

}