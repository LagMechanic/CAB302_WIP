/**
 The HistoryController class manages the browsing history functionality for the ZenBrowser application.
 It extends the ParentController and is responsible for displaying and managing the user's browsing history.
 **/
package com.zenbrowser.a1.Controller.MainControllers;
import com.zenbrowser.a1.Controller.ParentController;
import com.zenbrowser.a1.model.BrowserUsage.HistoryRecord;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class HistoryController extends ParentController {
    // FXML-injected field for the GridPane that displays the history records
    @FXML private GridPane historyGrid;

    // List to hold the history records
    private List<HistoryRecord> records;

    // Initialize method called when the controller is created
    public void initialize() {
        // Retrieve history records for the current user
        records = HistoryDAO.getUserHistoryRecords(super.getCurrentUser());
        // Populate the history records in the GridPane
        PopulateRecords();
    }

    // Method to populate the history records in the GridPane
    private void PopulateRecords(){
        int row = 1;  // Start from the second row (first row are headers)
        // Loop through the records in reverse order to show the most recent first
        for (int i = records.size() - 1; i >= 0; i--) {
            HistoryRecord record = records.get(i);

            // Create labels for each piece of information
            Label siteLabel = new Label(record.getSite());
            Label URLLabel = new Label(record.getURL());
            Label timeLabel = new Label(formatDateTime(record.getHistoryRecordDateTime()));

            // Add labels to the GridPane
            historyGrid.add(siteLabel, 0, row);
            historyGrid.add(URLLabel, 1, row);
            historyGrid.add(timeLabel, 2, row);

            row++;
        }
    }

    // Helper method to format the date and time of the history record
    private String formatDateTime(Timestamp dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss        dd/MM/yyyy ");
        return dateTime.toLocalDateTime().format(formatter);
    }

    // Method to delete all history records for the current user
    @FXML
    public void deleteHistory() throws SQLException {
        // Delete history records from the database
        HistoryDAO.deleteUserHistory(getCurrentUser());
        // Refresh the GridPane with updated history records
        PopulateRecords();
    }
}
