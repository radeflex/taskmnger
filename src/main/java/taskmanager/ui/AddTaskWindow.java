package taskmanager.ui;

import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;

public class AddTaskWindow {

    private static final Stage stage = new Stage();

    public static void show() {
        stage.setTitle("New task");
        stage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(AddTaskWindow.class.getResource("add-task.fxml"));

        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close() {
        stage.close();
    }
}
