package com.zenbrowser.a1.Controller.MainControllers;

import com.zenbrowser.a1.Controller.ParentController;
import com.zenbrowser.a1.model.BrowserUsage.HistoryRecord;
import com.zenbrowser.a1.model.BrowserUsage.HistoryRecordDAO;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsageInsightsController extends ParentController {

    @FXML
    private LineChart<String,Number> historyChart;

    private List<HistoryRecord> records;

    public void initialize() {
        records = new HistoryRecordDAO().getAllUserHistoryRecords(super.getCurrentUser());
    }
    private void populateLineChart() {
        Map<String, Integer> dateCountMap = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (HistoryRecord record : records) {
            System.out.println(record.getHistoryRecordDateTime());
            String date = record.getHistoryRecordDateTime().toLocalDateTime().toLocalDate().format(formatter);
            dateCountMap.put(date, dateCountMap.getOrDefault(date,0)+1);
        }

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("records");

        for (Map.Entry<String, Integer> entry : dateCountMap.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        historyChart.getData().add(series);
    }
}
