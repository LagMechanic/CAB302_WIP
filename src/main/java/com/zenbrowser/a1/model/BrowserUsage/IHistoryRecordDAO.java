package com.zenbrowser.a1.model.BrowserUsage;

import java.sql.SQLException;
import java.util.List;

public interface IHistoryRecordDAO {
    void insertHistoryRecord(HistoryRecord record);
    void updateActivityEndDateTime(String HistoryRecordEndDateTime);

    List<HistoryRecord> getUserHistoryRecords(String username);

    void deleteUserHistory(String username) throws SQLException;
}
