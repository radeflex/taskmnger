package taskmanager.logic;

import taskmanager.jdbc.TaskDao;
import taskmanager.logic.domain.Status;
import taskmanager.logic.domain.Task;

import java.util.List;

public class TaskService {
    private static TaskService taskService;
    private final TaskDao taskDao = new TaskDao();

    private TaskService() {}

    public static TaskService getInstance() {
        if (taskService == null) {
            taskService = new TaskService();
        }
        return taskService;
    }

    public List<Task> getTaskList() {
        return taskDao.getAllTasks();
    }

    private void validateTask(Task task) throws RuntimeException {
        var taskList = taskDao.getAllTasks();
        if (taskList.size() == 6) {
            throw new RuntimeException("You cannot have more than 6 tasks simultaneously");
        } else if (task.getDesc().isBlank()) {
            throw new RuntimeException("Task's name cannot be blank");
        } else if (taskList.contains(task)) {
            throw new RuntimeException("Task with such description has already been created");
        }
    }

    public void add(Task task) throws RuntimeException {
        validateTask(task);
        taskDao.add(task);
    }

    public void update(Task task, Status status) {
        taskDao.update(task, status);
    }

    public void remove(Task task) {
        taskDao.remove(task);
    }
}
