package taskmanager.ui.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import taskmanager.logic.TaskService;
import taskmanager.logic.domain.Status;
import taskmanager.logic.domain.Task;
import taskmanager.ui.window.AddTaskWindow;

import java.util.Arrays;
import java.util.stream.Collectors;

public class AddTaskController {
    @FXML
    private TextField descField;
    @FXML
    private ChoiceBox<String> statusField;
    @FXML
    private VBox newTaskWindow;

    private final TaskService taskService = TaskService.getInstance();

    private AddTaskWindow addTaskWindow;

    private static MainViewController mainViewController;

    @FXML
    private void confirmAction() {
        String desc = descField.getText();
        Status status = Status.valueOf(statusField.getValue());
        addTaskWindow.close();
        try {
            Task task = new Task(desc, status, mainViewController.getCurrentUser().getUserName());
            taskService.add(task);
            mainViewController.addNewTask(task);
        } catch (RuntimeException e) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initModality(Modality.APPLICATION_MODAL);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            });
        }
    }

    @FXML
    public void initialize() {
        addTaskWindow = AddTaskWindow.getInstance();
        ObservableList<String> statusValues = FXCollections.observableList(Arrays.stream(Status.values()).map(Status::toString).collect(Collectors.toList()));
        statusField.setItems(statusValues);
        statusField.setValue(statusValues.get(0));

        newTaskWindow.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                confirmAction();
            }
        });
    }

    public static void setMainViewController(MainViewController controller) {
        mainViewController = controller;
    }
}
