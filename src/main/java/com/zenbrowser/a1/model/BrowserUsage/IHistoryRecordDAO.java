package com.zenbrowser.a1.model.BrowserUsage;

import javafx.collections.ObservableList;

public interface IHistoryRecordDAO {
    public void insertHistoryRecord(String username, String site, String historyRecordDateTime);
    public void updateActivityEndDateTime(String HistoryRecordEndDateTime);
    public ObservableList<HistoryRecord> getAllUserHistoryRecords(String username);
}
