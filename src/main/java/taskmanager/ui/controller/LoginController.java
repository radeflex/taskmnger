package taskmanager.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import taskmanager.logic.domain.User;
import taskmanager.logic.UserService;
import taskmanager.ui.Application;

public class LoginController {
    @FXML
    private VBox root;
    @FXML
    private TextField userNameField;
    @FXML
    private PasswordField passwdField;
    @FXML
    private Label errorLabel;

    private UserService userService = UserService.getInstance();

    private static MainViewController mainViewController;
    private static RegisterController registerController;

    @FXML
    public void initialize() {
        RegisterController.setLoginController(this);
        MainViewController.setLoginController(this);

        root.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) loginUser();
        });
    }

    private void clear() {
        userNameField.clear();
        passwdField.clear();
    }

    public void loginUser() {
        errorLabel.setText("");
        User user;
        try {
            user = userService.getUser(userNameField.getText(), passwdField.getText());
        } catch (RuntimeException e) {
            errorLabel.setText(e.getMessage());
            return;
        }
        mainViewController.setCurrentUser(user);
        mainViewController.redirect();

        clear();
    }

    public void toSignUp() {
        errorLabel.setText("");
        clear();
        registerController.redirect();
    }

    public void redirect() {
        Application.setRoot(root);
    }

    public static void setMainViewController(MainViewController controller) {
        mainViewController = controller;
    }

    public static void setRegisterController(RegisterController controller) {
        registerController = controller;
    }
}
