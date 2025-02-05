package taskmanager.logic;

import taskmanager.jdbc.TaskTable;

import java.util.List;

public class TaskBoard {
    private static final List<Task> taskList = TaskTable.getAllTasks();

    public static List<Task> getTaskList() {
        return taskList;
    }

    private static void validateTask(Task task) throws RuntimeException {
        if (taskList.size() == 6) {
            throw new RuntimeException("You cannot have more than 6 tasks simultaneously");
        } else if (task.getDesc().isBlank()) {
            throw new RuntimeException("Task's name cannot be blank");
        } else if (taskList.contains(task)) {
            throw new RuntimeException("Task with such description has already been created");
        }
    }

    public static void add(Task task) throws RuntimeException {
        validateTask(task);
        TaskTable.add(task.getDesc(), task.getStatus().ordinal(), task.getDateAdded().toString());
        taskList.add(task);
    }

    public static void update(Task task, Status status) {
        TaskTable.update(task.getDesc(), status.ordinal());
    }

    public static void remove(Task task) {
        TaskTable.remove(task.getDesc());
        taskList.remove(task);
    }
}
