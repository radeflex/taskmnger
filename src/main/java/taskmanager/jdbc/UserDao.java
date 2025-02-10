package taskmanager.jdbc;

import taskmanager.logic.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao {
    private final String ADD_USER = "INSERT INTO users(username, password_hash) VALUES(?, ?)";
    private final String GET_ALL_USERS = "SELECT * FROM users";
    private final String GET_USER = "SELECT * FROM users WHERE username = ?";

    public void add(User user) {
        try (PreparedStatement stmt = DBUtils.getConnection().prepareStatement(ADD_USER)) {
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getPasswordHash());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<User> getByUsername(String name) {
        try (PreparedStatement stmt = DBUtils.getConnection().prepareStatement(GET_USER)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String userName = rs.getString("username");
                String passwordHash = rs.getString("password_hash");
                String fullName = rs.getString("full_name");
                String email = rs.getString("email");
                String github = rs.getString("github");
                return Optional.of(new User(userName, passwordHash, fullName, email, github));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Statement stmt = DBUtils.getConnection().createStatement()) {
            ResultSet resultSet = stmt.executeQuery(GET_ALL_USERS);

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
