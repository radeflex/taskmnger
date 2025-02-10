package taskmanager.ui.factory;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import taskmanager.logic.domain.Status;
import taskmanager.logic.domain.Task;
import taskmanager.logic.TaskService;
import taskmanager.ui.domain.TaskUI;
import taskmanager.ui.controller.MainViewController;

import java.util.Optional;

public class TaskUIFactory {
    private final MainViewController controller;

    private final TaskService taskService = TaskService.getInstance();
    private final AlertFactory alertFactory = new AlertFactory();

    public TaskUIFactory(MainViewController controller) {
        this.controller = controller;
    }

    private void updateTask(TaskUI taskUI, Task task, Status status) {
        taskUI.getDemoteBtn().setDisable(status.equals(Status.TODO));
        taskUI.getPromoteBtn().setDisable(status.equals(Status.DONE));
        taskService.update(task, status);
        controller.updateTask(task, status);
    }
    
    public TaskUI createTaskUI(Task task) {
        TaskUI taskUI = new TaskUI(task);

        taskUI.getRemoveBtn().setOnAction(e -> {
            Optional<ButtonType> choice = alertFactory.createAlert(Alert.AlertType.CONFIRMATION, "Do you really want to remove it?").showAndWait();
            if (choice.isPresent() && choice.get().equals(ButtonType.OK)) {
                taskService.remove(task);
                controller.removeTask(task);
            }
        });

        taskUI.getPromoteBtn().setOnAction(e -> {
            Status newStatus = Status.values()[task.getStatus().ordinal() + 1];
            updateTask(taskUI, task, newStatus);
        });

        taskUI.getDemoteBtn().setOnAction(e -> {
            Status newStatus = Status.values()[task.getStatus().ordinal() - 1];
            updateTask(taskUI, task, newStatus);
        });

        return taskUI;
    }
}
