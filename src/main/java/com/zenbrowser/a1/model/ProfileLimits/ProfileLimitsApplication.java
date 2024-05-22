/**package com.zenbrowser.a1.model.ProfileLimits;

import javafx.beans.property.SimpleStringProperty;

import static javafx.application.Application.launch;

public class ProfileLimitsApplication {
    private final SimpleStringProperty url;
    private final SimpleStringProperty hours;
    private final SimpleStringProperty minutes;
    private final SimpleStringProperty profile;

    public ProfileLimitsApplication(String Url, String Hours, String Minutes, String Profile) {
        this.url = new SimpleStringProperty(Url);
        this.hours = new SimpleStringProperty(Hours);
        this.minutes = new SimpleStringProperty(Minutes);
        this.profile = new SimpleStringProperty(Profile);
    }

    public String getUrl() {
        return url.get();
    }

    public void setUrl(String url) {
        this.url.set(url);
    }

    public String getHours() { return hours.get(); }

    public void setHours(String hours) { this.hours.set(hours); }

    public String getMinutes() { return minutes.get(); }

    public void setMinutes(String minutes){ this.minutes.set(minutes); }

    public String getProfile() { return profile.get(); }

    public void setProfile(String profile) { this.profile.set(profile); }

    public static void main(String[] args) {
        launch(args);
    }


}**/