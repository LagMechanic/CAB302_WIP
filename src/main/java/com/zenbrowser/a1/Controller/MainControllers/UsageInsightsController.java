package com.zenbrowser.a1.Controller.MainControllers;

import com.zenbrowser.a1.Controller.ParentController;
import com.zenbrowser.a1.model.BrowserUsage.HistoryRecord;
import com.zenbrowser.a1.model.BrowserUsage.HistoryRecordDAO;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.sql.Timestamp;
import java.time.LocalDate;
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
        records2.add(new HistoryRecord("1", "https://example.com/page1", "https://example.com/page1", Timestamp.valueOf("2024-05-01 10:30:00")));
        records2.add(new HistoryRecord("2", "https://example.com/page2", "https://example.com/page2", Timestamp.valueOf("2024-05-02 11:15:00")));
        records2.add(new HistoryRecord("3", "https://example.com/page3", "https://example.com/page13",Timestamp.valueOf("2024-05-02 11:15:00")));
        records2.add(new HistoryRecord("4", "https://example.com/page4", "https://example.com/page4",Timestamp.valueOf("2024-05-05 15:30:00")));
        records2.add(new HistoryRecord("5", "https://example.com/page5", "https://example.com/page5",Timestamp.valueOf("2024-05-06 09:45:00")));
        records2.add(new HistoryRecord("6", "https://example.com/page6", "https://example.com/page6",Timestamp.valueOf("2024-05-06 11:15:00")));
        records2.add(new HistoryRecord("7", "https://example.com/page7", "https://example.com/page7",Timestamp.valueOf("2024-05-10 10:30:00")));
        records2.add(new HistoryRecord("8", "https://example.com/page8", "https://example.com/page8",Timestamp.valueOf("2024-05-10 11:15:00")));
        records2.add(new HistoryRecord("9", "https://example.com/page9", "https://example.com/page9",Timestamp.valueOf("2024-05-12 14:30:00")));
        records2.add(new HistoryRecord("10", "https://example.com/page10", "https://example.com/page10",Timestamp.valueOf("2024-05-13 15:45:00")));records = new HistoryRecordDAO().getAllUserHistoryRecords(super.getCurrentUser());
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

        String firstDate = dateCountMap.keySet().stream().min(String::compareTo).orElse(null);
        String lastDate = dateCountMap.keySet().stream().max(String::compareTo).orElse(null);

        List<String> allDates = new ArrayList<>();
        LocalDate startDate = LocalDate.parse(firstDate, formatter);
        LocalDate endDate = LocalDate.parse(lastDate, formatter);
        while (!startDate.isAfter(endDate)) {
            allDates.add(startDate.format(formatter));
            startDate = startDate.plusDays(1);
        }

        for (String date : allDates) {
            dateCountMap.putIfAbsent(date,0);
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
