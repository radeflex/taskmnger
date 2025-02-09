package taskmanager.logic;

import java.util.Map;
import java.util.stream.Collectors;

import org.mindrot.jbcrypt.BCrypt;

import taskmanager.jdbc.UserTable;

public class UserStorage {
    private static final Map<String, User> userMap = UserTable.getAllUsers()
            .stream()
            .collect(Collectors.toMap(User::getUserName, user -> user));

    private static void validateUser(User user) throws RuntimeException {
        if (user.getUserName().isBlank() || user.getUserName().length() < 3) {
            throw new RuntimeException("Username must have at least 3 characters");
        } else if (userMap.containsKey(user.getUserName())) {
            throw new RuntimeException("User with such name already exists.");
        }
    }

    public static void add(User user) throws RuntimeException {
        validateUser(user);
        UserTable.add(user.getUserName(), user.getPasswordHash());
        userMap.put(user.getUserName(), user);
    }

    public static User getUser(String userName, String password) throws RuntimeException {
        User user = userMap.get(userName);
        if (user == null || !BCrypt.checkpw(password, user.getPasswordHash()))
            throw new RuntimeException("Invalid login or password");
        return user;
    }
}
