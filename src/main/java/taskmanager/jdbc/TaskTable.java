package taskmanager.jdbc;

import taskmanager.logic.Status;
import taskmanager.logic.Task;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TaskTable {
    private static final String ADD_TASK = "INSERT INTO tasks(name, status, date_added) VALUES(?, ?, ?);";
    private static final String UPD_TASK = "UPDATE tasks SET status = ? WHERE name = ?;";
    private static final String DEL_TASK = "DELETE FROM tasks WHERE name = ?;";
    private static final String GET_ALL_TASKS = "SELECT * FROM tasks";

    public static void add(Task task) {
        try (PreparedStatement stmt = DBUtils.getConnection().prepareStatement(ADD_TASK)) {
            stmt.setString(1, task.getDesc());
            stmt.setInt(2, task.getStatus().ordinal());
            stmt.setString(3, task.getDateAdded().toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(String taskName, Status status) {
        try (PreparedStatement stmt = DBUtils.getConnection().prepareStatement(UPD_TASK)) {
            stmt.setInt(1, status.ordinal());
            stmt.setString(2, taskName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void remove(String taskName) {
        try (PreparedStatement stmt = DBUtils.getConnection().prepareStatement(DEL_TASK)) {
            stmt.setString(1, taskName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Task> getAllTasks() {
        List<Task> taskList = null;
        try (Statement stmt = DBUtils.getConnection().createStatement()) {
            ResultSet resultSet = stmt.executeQuery(GET_ALL_TASKS);

            taskList = new ArrayList<>();
            while (resultSet.next()) {
                String desc = resultSet.getString(2);
                Status status = Status.values()[resultSet.getInt(3)];
                Timestamp date = Timestamp.valueOf(resultSet.getString(4));
                taskList.add(new Task(desc, status, date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskList;
    }
}
