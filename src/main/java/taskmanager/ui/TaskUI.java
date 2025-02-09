package taskmanager.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import taskmanager.logic.Status;
import taskmanager.logic.Task;

public class TaskUI extends VBox {
    private final Button demoteBtn, promoteBtn, removeBtn;

    public TaskUI(Task task) {
        super();

        setSpacing(10);
        setPadding(new Insets(20, 20, 20, 20));

        Label dateAdded = new Label("added at: " + task.getDateAdded().toString());
        Label desc = new Label(task.getDesc());
        Label owner = new Label("by " + task.getOwner());

        demoteBtn = new Button("<-");
        promoteBtn = new Button("->");
        removeBtn = new Button("X");

        demoteBtn.setDisable(task.getStatus().equals(Status.TODO));
        promoteBtn.setDisable(task.getStatus().equals(Status.DONE));

        HBox controlPanel = new HBox(demoteBtn, promoteBtn, removeBtn);
        controlPanel.setSpacing(10);

        getChildren().addAll(desc, owner, dateAdded, controlPanel);
    }

    public Button getDemoteBtn() {
        return demoteBtn;
    }

    public Button getPromoteBtn() {
        return promoteBtn;
    }

    public Button getRemoveBtn() {
        return removeBtn;
    }
}
