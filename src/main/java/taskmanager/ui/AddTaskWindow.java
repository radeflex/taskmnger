package taskmanager.ui;

import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.IOException;

public class AddTaskWindow {
    private static AddTaskWindow addTaskWindow;
    private final Stage stage;

    private AddTaskWindow() {
        stage = new Stage();
        stage.setTitle("New task");
        stage.initModality(Modality.APPLICATION_MODAL);
    }

    public static AddTaskWindow getInstance() {
        if (addTaskWindow == null) {
            addTaskWindow = new AddTaskWindow();
        }
        return addTaskWindow;
    }

    public void show() {
        FXMLLoader loader = new FXMLLoader(AddTaskWindow.class.getResource("add-task.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        stage.close();
    }
}
