package com.zenbrowser.a1.model.BrowserUsage;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

//  this is class was made only so this object can be put into an observable list-
//  -for the javafx tableview, which will access the properties for its columns.
public class HistoryRecord {
    private Integer activityID;
    private final String username;
    private final String url;
    private final String site;
    private final Timestamp historyRecordDateTime;
    //private final StringProperty historyRecordEndDateTime;


    public HistoryRecord(String username, String site, String url, Timestamp historyRecordDateTime) {
        this.username = username;
        this.site = site;
        this.url = url;
        this.historyRecordDateTime = historyRecordDateTime;
    }

    // Getters
    public int getActivityID() {
        return activityID;
    }
    public void setId(int id) {
        activityID = id;
    }

    public String getUsername() {
        return username;
    }

    public String getURL() {return url;}

    public String getSite() {
        return site;
    }

    public Timestamp getHistoryRecordDateTime() {
        return historyRecordDateTime;
    }


    /**
    public String getHistoryRecordEndDateTime() {
        return historyRecordEndDateTime.get();
    }

    public StringProperty historyRecordEndDateTimeProperty() {
        return historyRecordEndDateTime;
    }**/
}
