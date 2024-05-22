package com.zenbrowser.a1.Controller.MainControllers;
import com.zenbrowser.a1.Controller.ParentController;
import com.zenbrowser.a1.model.BrowserUsage.HistoryRecord;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.List;


public class HistoryController extends ParentController {
    @FXML
    private GridPane historyGrid;
    private List<HistoryRecord> records;


    public void initialize() {
        records = HistoryDAO.getUserHistoryRecords(super.getCurrentUser());

        PopulateRecords();
    }


    private void PopulateRecords(){
        int row = 1;

        for (int i = records.size() - 1; i >= 0; i--) {
            HistoryRecord record = records.get(i);

            Label siteLabel = new Label(record.getSite());
            Label URLLabel = new Label(record.getURL());
            Label timeLabel = new Label(record.getHistoryRecordDateTime().toString());

            historyGrid.add(siteLabel, 0, row);
            historyGrid.add(URLLabel, 1, row);
            historyGrid.add(timeLabel, 2, row);

            row++;
        }
    }
}
