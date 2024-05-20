package com.zenbrowser.a1.Controller.MainControllers;

import com.zenbrowser.a1.Controller.ParentController;
import com.zenbrowser.a1.model.BrowserUsage.HistoryRecord;
import com.zenbrowser.a1.model.BrowserUsage.HistoryRecordDAO;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UsageInsightsController extends ParentController {

    @FXML
    private LineChart<String,Number> historyChart;
    private List<HistoryRecord> records;
    private List<HistoryRecord> records2 = new ArrayList<>();
    public void initialize() {



        // Add some mock history records
        records2.add(new HistoryRecord("1", "https://example.com/page1", "https://example.com/page1", Timestamp.valueOf("2024-05-20 10:30:00")));
        records2.add(new HistoryRecord("2", "https://example.com/page2", "https://example.com/page2", Timestamp.valueOf("2024-05-21 11:15:00")));
        records2.add(new HistoryRecord("3", "https://example.com/page3", "https://example.com/page13",Timestamp.valueOf("2024-05-21 11:15:00")));
        records2.add(new HistoryRecord("4", "https://example.com/page4", "https://example.com/page4",Timestamp.valueOf("2024-05-22 11:15:00")));
        records2.add(new HistoryRecord("5", "https://example.com/page5", "https://example.com/page5",Timestamp.valueOf("2024-05-22 11:15:00")));
        records2.add(new HistoryRecord("6", "https://example.com/page6", "https://example.com/page6",Timestamp.valueOf("2024-05-22 11:15:00")));
        records = new HistoryRecordDAO().getAllUserHistoryRecords(super.getCurrentUser());
        populateLineChart();
    }
    private void populateLineChart() {
        Map<String, Integer> dateCountMap = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (HistoryRecord record : records2) {
            System.out.println(record.getHistoryRecordDateTime());
            String date = record.getHistoryRecordDateTime().toLocalDateTime().toLocalDate().format(formatter);
            dateCountMap.put(date, dateCountMap.getOrDefault(date,0)+1);
        }

        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(dateCountMap.entrySet());
        sortedEntries.sort(Map.Entry.comparingByKey());



        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("records");

        for (Map.Entry<String, Integer> entry : sortedEntries) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        historyChart.getData().add(series);
    }
}
