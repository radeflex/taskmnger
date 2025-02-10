package taskmanager.logic;

import org.mindrot.jbcrypt.BCrypt;

import taskmanager.jdbc.UserDao;
import taskmanager.logic.domain.User;

public class UserService {
    private static UserService userService;
    private final UserDao userDao = new UserDao();

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    private void validateUser(User user) throws RuntimeException {
        if (user.getUserName().isBlank() || user.getUserName().length() < 3) {
            throw new RuntimeException("Username must have at least 3 characters");
        } else if (userDao.getByUsername(user.getUserName()).isPresent()) {
            throw new RuntimeException("User with such name already exists.");
        }
    }

    public void add(User user) throws RuntimeException {
        validateUser(user);
        userDao.add(user);
    }

    public User getUser(String userName, String password) throws RuntimeException {
        return userDao.getByUsername(userName)
                .filter(u -> BCrypt.checkpw(password, u.getPasswordHash()))
                .orElseThrow(() -> new RuntimeException("Invalid login or password"));
    }
}
