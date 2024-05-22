package com.zenbrowser.a1.model.BrowserUsage;

import java.util.List;

public interface IHistoryRecordDAO {
    void insertHistoryRecord(HistoryRecord record);
    void updateActivityEndDateTime(String HistoryRecordEndDateTime);

    List<HistoryRecord> getUserHistoryRecords(String username);
}
