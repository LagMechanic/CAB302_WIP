package com.zenbrowser.a1.model.ProfileLimits;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ProfileLimitsApplication extends Application {
    private SimpleStringProperty url;
    private SimpleStringProperty limit;
    private SimpleStringProperty profile;

    public ProfileLimitsApplication(String Url, String Limit) {
        this.url = new SimpleStringProperty(url);
        this.limit = new SimpleStringProperty(limit);
        this.profile = new SimpleStringProperty(profile);
    }

    public String getUrl() {
        return url.get();
    }

    public void setUrl(String url) {
        this.url = new SimpleStringProperty(url);
    }

    public String getLimit() {
        return limit.get();
    }

    public void setLimit(String limit) {
        this.limit = new SimpleStringProperty(limit);
    }

    public String getProfile() {
        return profile.get();
    }

    public void setProfile(String profile) {
        this.profile = new SimpleStringProperty(profile);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Url Limits");
        Pane myPane = (Pane) FXMLLoader.load(getClass().getResource("ProfileLimits.fxml"));
        Scene myScene = new Scene(myPane);
        stage.setScene(myScene);
        stage.show();

    }
}