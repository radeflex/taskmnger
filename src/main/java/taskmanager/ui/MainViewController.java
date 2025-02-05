package taskmanager.ui;

import javafx.fxml.FXML;

import javafx.scene.layout.VBox;
import taskmanager.logic.Status;
import taskmanager.logic.Task;
import taskmanager.logic.TaskBoard;

import java.util.*;

public class MainViewController {
    @FXML
    private VBox todoBox;
    @FXML
    private VBox inProgressBox;
    @FXML
    private VBox doneBox;

    private Map<Status, VBox> statusBoxes;
    private Map<Task, TaskUI> taskUIs;
    private AddTaskWindow addTaskWindow;
    private TaskUIFactory taskUIFactory;

    @FXML
    private void addTaskAction() {
        AddTaskController.setMainViewController(this);
        addTaskWindow.show();
    }

    @FXML
    public void initialize() {
        List<Task> taskList = TaskBoard.getTaskList();
        addTaskWindow = AddTaskWindow.getInstance();
        taskUIs = new HashMap<>();
        taskUIFactory = new TaskUIFactory(this);
        statusBoxes = Map.of(Status.TODO, todoBox, Status.IN_PROGRESS, inProgressBox, Status.DONE, doneBox);

        taskList.forEach(task -> {
            TaskUI taskUI = taskUIFactory.createTaskUI(task);
            taskUIs.put(task, taskUI);
            statusBoxes.get(task.getStatus()).getChildren().add(taskUI);
        });
    }

    public void addNewTask(Task task) {
        TaskUI taskUI = taskUIFactory.createTaskUI(task);
        statusBoxes.get(task.getStatus()).getChildren().add(taskUI);
        taskUIs.put(task, taskUI);
    }

    public void updateTask(Task task, Status status) {
        TaskUI taskUI = taskUIs.get(task);
        statusBoxes.get(task.getStatus()).getChildren().remove(taskUI);
        task.setStatus(status);
        statusBoxes.get(task.getStatus()).getChildren().add(taskUI);
    }

    public void removeTask(Task task) {
        statusBoxes.get(task.getStatus()).getChildren().remove(taskUIs.remove(task));
    }
}