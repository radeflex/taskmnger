package taskmanager.jdbc;

import taskmanager.logic.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserTable {
    private static final String ADD_USER = "INSERT INTO users(username, password_hash) VALUES(?, ?)";
    private static final String GET_ALL_USERS = "SELECT * FROM users";

    public static void add(String userName, String passwordHash) {
        try (PreparedStatement stmt = DBUtils.getConnection().prepareStatement(ADD_USER)) {
            stmt.setString(1, userName);
            stmt.setString(2, passwordHash);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<User> getAllUsers() {
        List<User> userList = null;
        try (Statement stmt = DBUtils.getConnection().createStatement()) {
            ResultSet resultSet = stmt.executeQuery(GET_ALL_USERS);

            userList = new ArrayList<>();
            while (resultSet.next()) {
                String userName = resultSet.getString(2);
                String passwordHash = resultSet.getString(3);
                userList.add(new User(userName, passwordHash));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
