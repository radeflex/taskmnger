package taskmanager.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Task Manager");
        new FXMLLoader(Application.class.getResource("view.fxml")).load();
        Parent root = new FXMLLoader(Application.class.getResource("login.fxml")).load();
        new FXMLLoader(Application.class.getResource("register.fxml")).load();
        scene = new Scene(root, 810, 450);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(Parent parent) {
        scene.setRoot(parent);
    }

    public static void main(String[] args) {
        launch();
    }
}