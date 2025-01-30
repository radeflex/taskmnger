package taskmanager.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import taskmanager.logic.Task;

public class TaskUI {
    public static VBox createTask(Task task) {
        VBox vBox = new VBox();
        Label taskDesc = new Label(task.getDesc());
        Label dateAdded = new Label("added at: " + task.getDateAdded().toString());
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(20, 20, 20, 20));
        vBox.getChildren().add(taskDesc);
        vBox.getChildren().add(dateAdded);

        return vBox;
    }
}
