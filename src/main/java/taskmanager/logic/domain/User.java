package taskmanager.logic.domain;

import java.util.Objects;

import org.mindrot.jbcrypt.BCrypt;

public class User {
    private final String userName;
    private final String passwordHash;
    private String fullName;
    private String email;
    private String github;

    public User(String userName, String passwordHash, String fullName, String email, String github) {
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.fullName = fullName;
        this.email = email;
        this.github = github;
    }

    public User(String userName, String password) {
        this.userName = userName;
        passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getUserName() {
        return userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, passwordHash);
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", github='" + github + '\'' +
                '}';
    }
}
