package com.zenbrowser.a1.model.BrowserUsage;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//  this is class was made only so this object can be put into an observable list-
//  -for the javafx tableview, which will access the properties for its columns.
public class HistoryRecord {
    private final IntegerProperty activityID;
    private final StringProperty username;
    private final StringProperty site;
    private final StringProperty historyRecordDateTime;
    private final StringProperty historyRecordEndDateTime;

    public HistoryRecord(int activityID, String username, String site, String historyRecordDateTime, String historyRecordEndDateTime) {
        this.activityID = new SimpleIntegerProperty(activityID);
        this.username = new SimpleStringProperty(username);
        this.site = new SimpleStringProperty(site);
        this.historyRecordDateTime = new SimpleStringProperty(historyRecordDateTime);
        this.historyRecordEndDateTime = new SimpleStringProperty(historyRecordEndDateTime);
    }

    // Getters
    public int getActivityID() {
        return activityID.get();
    }

    public IntegerProperty activityIDProperty() {
        return activityID;
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public String getSite() {
        return site.get();
    }

    public StringProperty siteProperty() {
        return site;
    }

    public String getHistoryRecordDateTime() {
        return historyRecordDateTime.get();
    }

    public StringProperty historyRecordDateTimeProperty() {
        return historyRecordDateTime;
    }

    public String getHistoryRecordEndDateTime() {
        return historyRecordEndDateTime.get();
    }

    public StringProperty historyRecordEndDateTimeProperty() {
        return historyRecordEndDateTime;
    }
}
