package com.zenbrowser.a1.model.BrowserUsage;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;

//  this is class was made only so this object can be put into an observable list-
//  -for the javafx tableview, which will access the properties for its columns.
public class HistoryRecord {
    private Integer activityID;
    private final String username;
    private final String url;
    private final String site;
    private final java.sql.Date historyRecordDateTime;
    //private final StringProperty historyRecordEndDateTime;


    public HistoryRecord(String username, String site, String url, java.sql.Date historyRecordDateTime) {
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

    public Date getHistoryRecordDateTime() {
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
