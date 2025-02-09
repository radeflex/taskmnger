package taskmanager.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import taskmanager.logic.User;
import taskmanager.logic.UserStorage;

public class RegisterController {
    @FXML
    private VBox root;
    @FXML
    private TextField userNameField;
    @FXML
    private PasswordField passwdField;
    @FXML
    private PasswordField confPasswdField;
    @FXML
    private Label errorLabel;

    private static LoginController loginController;
    private static MainViewController mainViewController;

    @FXML
    public void initialize() {
        LoginController.setRegisterController(this);

        root.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) registerUser();
        });
    }

    private void clear() {
        userNameField.clear();
        confPasswdField.clear();
        passwdField.clear();
    }

    public void registerUser() {
        errorLabel.setText("");
        if (passwdField.getText().isBlank() || !passwdField.getText().equals(confPasswdField.getText())) {
            errorLabel.setText("Passwords doesn't match");
            return;
        }

        User user = new User(userNameField.getText(), passwdField.getText());
        try {
            UserStorage.add(user);
        } catch (RuntimeException e) {
            errorLabel.setText(e.getMessage());
            return;
        }
        mainViewController.setCurrentUser(user);
        mainViewController.redirect();

        clear();
    }

    public void toSignIn() {
        errorLabel.setText("");
        clear();
        loginController.redirect();
    }

    public void redirect() {
        Application.setRoot(root);
    }

    public static void setLoginController(LoginController controller) {
        loginController = controller;
    }

    public static void setMainViewController(MainViewController controller) {
        mainViewController = controller;
    }
}
