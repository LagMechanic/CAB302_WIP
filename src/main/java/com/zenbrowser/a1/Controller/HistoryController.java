package com.zenbrowser.a1.Controller;
import com.zenbrowser.a1.model.BrowserUsage.HistoryRecord;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import java.util.List;


public class HistoryController extends ParentController {
    @FXML
    private VBox historyContainer;

    private List<HistoryRecord> records;
    public void initialize() {
        records = HistoryDAO.getAllUserHistoryRecords(super.getCurrentUser());
        for (int i = records.size() - 1; i >= 0 ; i--) {
            Label Title = new Label(records.get(i).getSite());
            Label URL = new Label(records.get(i).getURL());
            Label Time = new Label(records.get(i).getHistoryRecordDateTime().toString());

            HBox entryContainer = new HBox();
            entryContainer.setSpacing(10);
            entryContainer.getChildren().addAll(Title, URL, Time);
            historyContainer.getChildren().add(entryContainer);
        }
    }
}