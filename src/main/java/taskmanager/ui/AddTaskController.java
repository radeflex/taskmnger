package taskmanager.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import taskmanager.logic.Status;
import taskmanager.logic.TaskBoard;
import taskmanager.logic.Task;

import java.util.Arrays;
import java.util.stream.Collectors;

public class AddTaskController {
    @FXML
    private TextField descField;
    @FXML
    private ChoiceBox<String> statusField;

    private AddTaskWindow addTaskWindow;
    private static MainViewController mainViewController;

    @FXML
    private void confirmAction() {
        String desc = descField.getText();
        Status status = Status.valueOf(statusField.getValue());
        try {
            Task task = new Task(desc, status);
            TaskBoard.add(task);
            mainViewController.addNewTask(task);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        addTaskWindow.close();
    }

    @FXML
    public void initialize() {
        addTaskWindow = AddTaskWindow.getInstance();
        ObservableList<String> statusValues = FXCollections.observableList(Arrays.stream(Status.values()).map(Status::toString).collect(Collectors.toList()));
        statusField.setItems(statusValues);
        statusField.setValue(statusValues.get(0));
    }

    public static void setMainViewController(MainViewController controller) {
        mainViewController = controller;
    }
}
