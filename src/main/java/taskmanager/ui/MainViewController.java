package taskmanager.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;

import javafx.scene.layout.VBox;
import taskmanager.logic.Status;
import taskmanager.logic.Task;
import taskmanager.logic.TaskBoard;

public class MainViewController {
    @FXML
    private FlowPane todoBox;
    @FXML
    private FlowPane inProgressBox;
    @FXML
    private FlowPane doneBox;

    @FXML
    private void addTaskAction() {
        AddTaskWindow.show();
    }

    @FXML
    public void initialize() {
        TaskBoard.getTaskListByStatus(Status.TODO)
                .forEach(t -> todoBox.getChildren().add(TaskUI.createTask(t)));
        TaskBoard.getTaskListByStatus(Status.IN_PROGRESS)
                .forEach(t -> inProgressBox.getChildren().add(TaskUI.createTask(t)));
        TaskBoard.getTaskListByStatus(Status.DONE)
                .forEach(t -> doneBox.getChildren().add(TaskUI.createTask(t)));
    }

    public void addNewTask(Task task) {
        VBox taskUI = TaskUI.createTask(task);
        switch (task.getStatus()) {
            case TODO:
                todoBox.getChildren().add(taskUI);
                break;
            case IN_PROGRESS:
                inProgressBox.getChildren().add(taskUI);
                break;
            case DONE:
                doneBox.getChildren().add(taskUI);
                break;
        }
    }
}