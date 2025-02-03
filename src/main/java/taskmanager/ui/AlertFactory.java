package taskmanager.ui;

import javafx.scene.control.Alert;
import javafx.stage.Modality;

public class AlertFactory {
    public static Alert createAlert(Alert.AlertType type, String desc) {
        Alert alert = new Alert(type);
        alert.setContentText(desc);
        alert.initModality(Modality.APPLICATION_MODAL);
        return alert;
    }
}
