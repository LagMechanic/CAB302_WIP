package com.zenbrowser.a1.Controller;
import com.zenbrowser.a1.model.BrowserUsage.HistoryRecord;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import java.util.List;


public class HistoryController extends ParentController {
    @FXML
    private GridPane historyGrid;

    @FXML
    private VBox historyContainer;

    private List<HistoryRecord> records;


    public void initialize() {
        records = HistoryDAO.getAllUserHistoryRecords(super.getCurrentUser());

        PopulateRecords();
    }


    private void PopulateRecords(){
        int row = 1;

        for (int i = records.size() - 1; i >= 0; i--) {
            HistoryRecord record = records.get(i);

            Label siteLabel = new Label(record.getSite());
            Label URLLabel = new Label(record.getURL());
            Label timeLabel = new Label(record.getHistoryRecordDateTime().toString());

            // Create a new GridPane for each record
            GridPane recordGrid = new GridPane();
            recordGrid.add(siteLabel, 0, 0);
            recordGrid.add(URLLabel, 0, 1);
            recordGrid.add(timeLabel, 0, 2);

            // Add the recordGrid to the historyContainer
            historyContainer.getChildren().add(recordGrid);
        }
    }
}
