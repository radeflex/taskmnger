package taskmanager.logic;

import taskmanager.jdbc.TaskTable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaskBoard {
    private static final List<Task> taskList = TaskTable.getAllTasks();

    public static boolean isEmpty() {
        return taskList.isEmpty();
    }

    public static List<Task> getTaskListByStatus(Status status) {
        return taskList.stream()
                .filter(t -> t.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    public static void add(Task task) throws RuntimeException {
        if (taskList.size() > 6) {
            throw new RuntimeException("You cannot have more than 6 tasks simultaneously");
        }
        TaskTable.add(task);
        taskList.add(task);

    }

    public static Task findTask(String taskName) throws IllegalArgumentException {
        Optional<Task> task = taskList.stream().filter(t -> t.getDesc().equals(taskName)).findFirst();
        if (task.isPresent()) {
            return task.get();
        }
        throw new IllegalArgumentException();
    }

    public static void update(String taskName, Status status) {
        Task task = findTask(taskName);
        TaskTable.update(taskName, status);
        task.setStatus(status);
    }

    public static boolean remove(String taskName) {
        Task task = findTask(taskName);
        TaskTable.remove(taskName);
        return taskList.removeIf(t -> t.getDesc().equals(task.getDesc()));
    }
}
