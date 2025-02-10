package taskmanager.ui.factory;

import javafx.scene.control.Alert;
import javafx.stage.Modality;

public class AlertFactory {
    public Alert createAlert(Alert.AlertType type, String desc) {
        Alert alert = new Alert(type);
        alert.setContentText(desc);
        alert.initModality(Modality.APPLICATION_MODAL);
        return alert;
    }
}
