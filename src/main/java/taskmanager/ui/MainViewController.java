package taskmanager.ui;

import javafx.fxml.FXML;

import javafx.scene.layout.VBox;
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

    private Map<Task, VBox> taskUIs;
    private AddTaskWindow addTaskWindow;

    @FXML
    private void addTaskAction() {
        AddTaskController.setMainViewController(this);
        addTaskWindow.show();
    }

    @FXML
    public void initialize() {
        List<Task> taskLists = TaskBoard.getTaskList();
        addTaskWindow = AddTaskWindow.getInstance();
        taskUIs = new HashMap<>();

        taskLists.forEach(t -> {
            VBox taskUI = TaskUIFactory.createTaskUI(t, this);
            taskUIs.put(t, taskUI);
            getTaskBox(t).getChildren().add(taskUI);
        });
    }

    private VBox getTaskBox(Task task) {
        VBox choice = null;
        switch (task.getStatus()) {
            case TODO:
                choice = todoBox;
                break;
            case IN_PROGRESS:
                choice = inProgressBox;
                break;
            case DONE:
                choice = doneBox;
        }
        return choice;
    }

    public void addNewTask(Task task) {
        VBox taskUI = TaskUIFactory.createTaskUI(task, this);
        getTaskBox(task).getChildren().add(taskUI);
        taskUIs.put(task, taskUI);
    }

    public void removeTask(Task task) {
        getTaskBox(task).getChildren().remove(taskUIs.remove(task));
    }
}