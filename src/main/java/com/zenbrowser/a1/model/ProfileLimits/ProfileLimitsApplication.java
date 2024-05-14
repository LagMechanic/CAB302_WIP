package com.zenbrowser.a1.model.ProfileLimits;

import javafx.beans.property.SimpleStringProperty;

import static javafx.application.Application.launch;

public class ProfileLimitsApplication {
    private final SimpleStringProperty url;
    private final SimpleStringProperty limit;
    private final SimpleStringProperty profile;

    public ProfileLimitsApplication(String Url, String Limit, String Profile) {
        this.url = new SimpleStringProperty(Url);
        this.limit = new SimpleStringProperty(Limit);
        this.profile = new SimpleStringProperty(Profile);
    }

    public String getUrl() {
        return url.get();
    }

    public void setUrl(String url) {
        this.url.set(url);
    }

    public String getLimit() {
        return limit.get();
    }

    public void setLimit(String limit) {
        this.limit.set(limit);
    }

    public String getProfile() {
        return profile.get();
    }

    public void setProfile(String profile) {
        this.profile.set(profile);
    }

    public static void main(String[] args) {
        launch(args);
    }


}