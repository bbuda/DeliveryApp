package ru.bbuda;

import com.sun.tools.javac.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import ru.bbuda.configuration.SceneSources;
import ru.bbuda.model.PersonCredentials;

import java.io.IOException;
import java.util.Objects;

@Getter
public class DeliveryApp extends Application {
    private static Stage stage;
    private static PersonCredentials currentPersonCredentials;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DeliveryApp.class.getResource(SceneSources.HELLO_VIEW.getSrc()));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setTitle("App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void changeScene(SceneSources source)  {
        try {
            Parent panel = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(source.getSrc())));
            stage.setScene(new Scene(panel));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setCurrentPersonCredentials(PersonCredentials currentPersonCredentials) {
        DeliveryApp.currentPersonCredentials = currentPersonCredentials;
    }
}
