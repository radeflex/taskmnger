package taskmanager.jdbc;

import taskmanager.logic.domain.Status;
import taskmanager.logic.domain.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDao {
    private final String ADD_TASK = "INSERT INTO tasks(name, status, date_added, owner) VALUES(?, ?, ?, ?);";
    private final String UPD_TASK = "UPDATE tasks SET status = ? WHERE name = ?;";
    private final String DEL_TASK = "DELETE FROM tasks WHERE name = ?;";
    private final String GET_ALL_TASKS = "SELECT * FROM tasks";

    public void add(Task task) {
        try (PreparedStatement stmt = DBUtils.getConnection().prepareStatement(ADD_TASK)) {
            stmt.setString(1, task.getDesc());
            stmt.setInt(2, task.getStatus().ordinal());
            stmt.setString(3, task.getDateAdded().toString());
            stmt.setString(4, task.getOwner());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Task task, Status status) {
        try (PreparedStatement stmt = DBUtils.getConnection().prepareStatement(UPD_TASK)) {
            stmt.setInt(1, status.ordinal());
            stmt.setString(2, task.getDesc());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(Task task) {
        try (PreparedStatement stmt = DBUtils.getConnection().prepareStatement(DEL_TASK)) {
            stmt.setString(1, task.getDesc());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<>();
        try (Statement stmt = DBUtils.getConnection().createStatement()) {
            ResultSet resultSet = stmt.executeQuery(GET_ALL_TASKS);

            while (resultSet.next()) {
                String desc = resultSet.getString(2);
                Status status = Status.values()[resultSet.getInt(3)];
                Timestamp date = Timestamp.valueOf(resultSet.getString(4));
                String owner = resultSet.getString(5);
                taskList.add(new Task(desc, status, date, owner));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taskList;
    }
}
