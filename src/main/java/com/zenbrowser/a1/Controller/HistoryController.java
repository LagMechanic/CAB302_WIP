package com.zenbrowser.a1.Controller;

import javafx.event.EventHandler;

import java.net.URL;
import java.util.ResourceBundle;
import com.zenbrowser.a1.model.browserTab;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebHistory;

public class HistoryController extends ParentController {
    @FXML
    private VBox historyContainer;

    public void initialize() {
        for (int i = 0; i < browserTab.getAllEntries().size(); i++) {
            WebHistory.Entry entry = browserTab.getAllEntries().get(i);
            Label entryLabel = new Label(entry.toString());
            historyContainer.getChildren().add(entryLabel);
        }
    }
}