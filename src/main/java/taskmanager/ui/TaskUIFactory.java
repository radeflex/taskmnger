package taskmanager.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import taskmanager.logic.Task;
import taskmanager.logic.TaskBoard;

import java.util.Optional;

public class TaskUIFactory {
    public static VBox createTaskUI(Task task, MainViewController controller) {
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(20, 20, 20, 20));

        Label taskDesc = new Label(task.getDesc());
        Label dateAdded = new Label("added at: " + task.getDateAdded().toString());

        Button removeBtn = new Button("X");

        HBox controlPanel = new HBox();
        controlPanel.setSpacing(10);
        controlPanel.getChildren().add(removeBtn);

        removeBtn.setOnAction(e -> {
            Optional<ButtonType> choice = AlertFactory.createAlert(Alert.AlertType.CONFIRMATION, "Do you really want to remove it?").showAndWait();
            if (choice.isPresent() && choice.get().equals(ButtonType.OK)) {
                TaskBoard.remove(task);
                controller.removeTask(task);
            }
        });

        vBox.getChildren().addAll(taskDesc, dateAdded, controlPanel);
        return vBox;
    }
}
