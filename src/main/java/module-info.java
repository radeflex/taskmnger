module taskmanager.ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jbcrypt;


    opens taskmanager.ui to javafx.fxml;
    exports taskmanager.ui;
    exports taskmanager.ui.controller;
    opens taskmanager.ui.controller to javafx.fxml;
    exports taskmanager.ui.factory;
    opens taskmanager.ui.factory to javafx.fxml;
    exports taskmanager.ui.domain;
    opens taskmanager.ui.domain to javafx.fxml;
    exports taskmanager.ui.window;
    opens taskmanager.ui.window to javafx.fxml;
}