package com.zenbrowser.a1.model.BrowserUsage;

import java.sql.Date;
import java.sql.Timestamp;

//  this is class was made only so this object can be put into an observable list-
//  -for the javafx tableview, which will access the properties for its columns.
public class HistoryRecord {
    private Integer activityID;
    private final String username;
    private final String url;
    private final String site;
    private final Timestamp historyRecordDate;
    //private final StringProperty historyRecordEndDateTime;


    public HistoryRecord(String username, String site, String url, Timestamp historyRecordDate) {
        this.username = username;
        this.site = site;
        this.url = url;
        this.historyRecordDate = historyRecordDate;
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

    public Timestamp getHistoryRecordDate() {
        return historyRecordDate;
    }


    /**
    public String getHistoryRecordEndDateTime() {
        return historyRecordEndDateTime.get();
    }

    public StringProperty historyRecordEndDateTimeProperty() {
        return historyRecordEndDateTime;
    }**/
}
