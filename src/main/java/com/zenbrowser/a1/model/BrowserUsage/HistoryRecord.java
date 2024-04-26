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
    private final StringProperty activity;
    private final StringProperty activityDateTime;
    private final StringProperty activityEndDateTime;

    public HistoryRecord(int activityID, String username, String activity, String activityDateTime, String activityEndDateTime) {
        this.activityID = new SimpleIntegerProperty(activityID);
        this.username = new SimpleStringProperty(username);
        this.activity = new SimpleStringProperty(activity);
        this.activityDateTime = new SimpleStringProperty(activityDateTime);
        this.activityEndDateTime = new SimpleStringProperty(activityEndDateTime);
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

    public String getActivity() {
        return activity.get();
    }

    public StringProperty activityProperty() {
        return activity;
    }

    public String getActivityDateTime() {
        return activityDateTime.get();
    }

    public StringProperty activityDateTimeProperty() {
        return activityDateTime;
    }

    public String getActivityEndDateTime() {
        return activityEndDateTime.get();
    }

    public StringProperty activityEndDateTimeProperty() {
        return activityEndDateTime;
    }
}
