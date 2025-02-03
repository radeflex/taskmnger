package taskmanager.logic;

import taskmanager.jdbc.TaskTable;

import java.util.List;

public class TaskBoard {
    private static final List<Task> taskList = TaskTable.getAllTasks();

    public static List<Task> getTaskList() {
        return taskList;
    }

//    public static List<Task> getTaskListByStatus(Status status) {
//        return taskList.stream()
//                .filter(t -> t.getStatus().equals(status))
//                .collect(Collectors.toList());
//    }

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
        TaskTable.add(task);
        taskList.add(task);
    }

//    public static void update(String taskName, Status status) {
//        Task task = findTask(taskName);
//        TaskTable.update(taskName, status);
//        task.setStatus(status);
//    }

    public static void remove(Task task) {
        TaskTable.remove(task);
        taskList.remove(task);
    }
}
