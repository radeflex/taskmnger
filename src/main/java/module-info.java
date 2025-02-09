module taskmanager.ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jbcrypt;


    opens taskmanager.ui to javafx.fxml;
    exports taskmanager.ui;
}