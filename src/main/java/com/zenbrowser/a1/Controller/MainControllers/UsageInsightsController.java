package com.zenbrowser.a1.Controller.MainControllers;

import com.zenbrowser.a1.Controller.ParentController;
import com.zenbrowser.a1.model.BrowserUsage.HistoryRecord;
import com.zenbrowser.a1.model.BrowserUsage.HistoryRecordDAO;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

    import java.net.MalformedURLException;
    import java.net.URL;
    import java.time.DayOfWeek;
    import java.time.LocalDate;
    import java.time.format.DateTimeFormatter;
    import java.util.*;

    public class UsageInsightsController extends ParentController {

        @FXML
        private LineChart<String,Number> historyChart;
        @FXML
        private BarChart<String,Number> topVisitedChart;
        @FXML
        private BarChart<String,Number> dayOfWeekChart;
        @FXML
        private BarChart<String,Number> timeOfDayChart;

        private List<HistoryRecord> records = new ArrayList<>();

        public void initialize() {


            records = new HistoryRecordDAO().getAllUserHistoryRecords(super.getCurrentUser());

            populateLineChart();
            populateTopUrlsChart();
            populateDayOfWeekChart();
            populateTimeOfDayChart();
        }
        private void populateLineChart() {
            Map<String, Integer> dateCountMap = new HashMap<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            for (HistoryRecord record : records) {
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
        private void populateTopUrlsChart() {
            List<Map.Entry<String, Integer>> topUrls = getTopUrls();

            XYChart.Series<String,Number> series = new XYChart.Series<>();
            series.setName("Visit Count");

            for (Map.Entry<String,Integer> entry : topUrls) {
                series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
            }

            topVisitedChart.getData().add(series);
        }
        private List<Map.Entry<String, Integer>> getTopUrls() {
            Map<String, Integer> urlVisitCounts = new HashMap<>();

            for (HistoryRecord record : records) {
                String url = record.getURL();
                String baseDomain = getBaseDomain(url);
                urlVisitCounts.put(baseDomain, urlVisitCounts.getOrDefault(baseDomain, 0) + 1);
            }

            List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(urlVisitCounts.entrySet());
            sortedEntries.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

            return sortedEntries.subList(0, Math.min(5, sortedEntries.size()));
        }
        private String getBaseDomain(String urlString) {
            try{
                URL url = new URL(urlString);
                String host = url.getHost();

                if (host.startsWith("www.")) {
                    host = host.substring(4);
                }
                return host;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return urlString;
            }
        }
        private void populateDayOfWeekChart() {
            Map<DayOfWeek, Integer> dayOfWeekCountMap = new HashMap<>();

            for (HistoryRecord record : records) {
                DayOfWeek dayOfWeek = record.getHistoryRecordDateTime().toLocalDateTime().getDayOfWeek();
                dayOfWeekCountMap.put(dayOfWeek, dayOfWeekCountMap.getOrDefault(dayOfWeek, 0 ) + 1);
            }

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Visits by Day of Week");

            for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
                series.getData().add(new XYChart.Data<>(dayOfWeek.toString(), dayOfWeekCountMap.getOrDefault(dayOfWeek,0)));
            }
            dayOfWeekChart.getData().add(series);
        }
        private void populateTimeOfDayChart() {
            Map<Integer, Integer> hourCountMap = new HashMap<>();

            for (HistoryRecord record : records) {
                int hour = record.getHistoryRecordDateTime().toLocalDateTime().getHour();
                hourCountMap.put(hour,hourCountMap.getOrDefault(hour, 0 ) + 1);
            }

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Visits by Hour of Day");

            for (int i = 0; i < 24; i++) {
                series.getData().add(new XYChart.Data<>(String.format("%02d:00", i), hourCountMap.getOrDefault(i, 0)));
            }

            timeOfDayChart.getData().add(series);
        }
    }
