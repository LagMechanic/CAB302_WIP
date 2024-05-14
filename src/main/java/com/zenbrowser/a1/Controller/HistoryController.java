package com.zenbrowser.a1.Controller;
import com.zenbrowser.a1.model.BrowserUsage.HistoryRecord;
import com.zenbrowser.a1.model.BrowserUsage.HistoryRecordDAO;
import com.zenbrowser.a1.model.browserTab;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebHistory;

import java.util.List;

public class HistoryController extends ParentController {
    @FXML
    private VBox historyContainer;

    private List<HistoryRecord> records;
    public void initialize() {
        records = HistoryDAO.getAllUserHistoryRecords(super.getCurrentUser());
        for (int i = records.size() - 1; i >= 0 ; i--) {
            Label entryLabel = new Label(records.get(i).toString());
            historyContainer.getChildren().add(entryLabel);
        }
    }
}