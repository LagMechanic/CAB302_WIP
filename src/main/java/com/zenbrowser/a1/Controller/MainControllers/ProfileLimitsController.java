package com.zenbrowser.a1.Controller.MainControllers;

import com.zenbrowser.a1.Controller.ParentController;
import com.zenbrowser.a1.model.FocusProfile.Profile;
import com.zenbrowser.a1.model.ProfileLimits.UrlLimit;
import com.zenbrowser.a1.model.Website.Site;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;

import java.sql.Date;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;


public class ProfileLimitsController extends ParentController {
    @FXML
    public ComboBox<String> minutesBox;

    @FXML
    public ComboBox<String> hoursBox;

    @FXML
    public TextField urlField;

    @FXML
    public Button UrlLimitData;

    @FXML
    public ComboBox<String> profileBox;

    @FXML
    private TableView<UrlLimit> tbData;

    @FXML
    public TableColumn<UrlLimit, String> profile;

    @FXML
    public TableColumn<UrlLimit, String> url;

    @FXML
    public TableColumn<UrlLimit, String> hours;

    @FXML
    public TableColumn<UrlLimit, String> minutes;

    private final ObservableList<UrlLimit> profileLimitsData = FXCollections.observableArrayList();

    public void initialize(){
        url.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().Url()));
        hours.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().Hours()));
        minutes.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().Minutes()));
        profile.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().Profile()));

        tbData.setItems(profileLimitsData);


        loadProfilesTable();
    }

    private void loadProfilesTable() {
        for ( Profile p : ProfileDAO.getAllProfiles()) {
            String url = p.getWebsite().getURL();
            String profile = p.getProfileName();

            long blockedUntil = p.getBlockedUntil().getTime();
            long blockedDuration = blockedUntil - System.currentTimeMillis();
            // Limit has expired
            if (blockedDuration < 0) {
                try {
                    ProfileDAO.deleteProfile(p.getId());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                continue;
            }
            long mins = TimeUnit.MILLISECONDS.toMinutes(blockedDuration) % 60;
            long hrs = TimeUnit.MILLISECONDS.toHours(blockedDuration);
            UrlLimit newUrlLimit = new UrlLimit(url, Long.toString(hrs), Long.toString(mins), profile);

            profileLimitsData.add(newUrlLimit);
        }
    }

    public void addUrlAndLimit() {
        String url = urlField.getText();
        String hours = hoursBox.getSelectionModel().getSelectedItem();
        String minutes = minutesBox.getSelectionModel().getSelectedItem();
        String profile = profileBox.getSelectionModel().getSelectedItem();
        if (!url.isEmpty() && hours != null && !hours.isEmpty() && minutes != null && !minutes.isEmpty() && profile != null && !profile.isEmpty()) {
            UrlLimit newUrlLimit = new UrlLimit(url, hours, minutes, profile);
            // Add to table
            profileLimitsData.add(newUrlLimit);

            // Add to database
            // Check if site isn't already in the database
            Site site = SiteDAO.getSiteByURL(url);
            if (site == null) {
                site = new Site(url, "", "", true);
                site = SiteDAO.insertSite(site);
            }
            // hours and minutes will always be a valid integer
            long limitmilliseconds = TimeUnit.HOURS.toMillis(Integer.parseInt(hours))
                    + TimeUnit.MINUTES.toMillis(Integer.parseInt(minutes));

            ProfileDAO.insertProfile(new Profile(profile, site,
                    new Date(System.currentTimeMillis() + limitmilliseconds)));


            // Reset form
            urlField.clear();
            hoursBox.getSelectionModel().clearSelection();
            minutesBox.getSelectionModel().clearSelection();
            profileBox.getSelectionModel().clearSelection();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Profile, Hours, Minutes, or URL cannot be empty");
        alert.showAndWait();
        }

    }

}
