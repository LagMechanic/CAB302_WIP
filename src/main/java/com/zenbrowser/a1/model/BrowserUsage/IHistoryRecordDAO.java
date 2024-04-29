package com.zenbrowser.a1.model.BrowserUsage;

import javafx.collections.ObservableList;

public interface IHistoryRecordDAO {
    void insertHistoryRecord(String username, String site, String historyRecordDateTime);
    void updateActivityEndDateTime(String HistoryRecordEndDateTime);
    ObservableList<HistoryRecord> getAllUserHistoryRecords(String username);
}
