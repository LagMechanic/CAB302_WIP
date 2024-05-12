package com.zenbrowser.a1.Controller;
import com.zenbrowser.a1.model.browserTab;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebHistory;

public class HistoryController extends ParentController {
    @FXML
    private VBox historyContainer;

    public void initialize() {
        for (int i = browserTab.getAllEntries().size() - 1; i >= 0 ; i--) {
            WebHistory.Entry entry = browserTab.getAllEntries().get(i);
            Label entryLabel = new Label(entry.toString());
            historyContainer.getChildren().add(entryLabel);
        }
    }
}