package com.zenbrowser.a1.Controller.MainControllers;

import com.zenbrowser.a1.Controller.ParentController;
import com.zenbrowser.a1.model.FocusProfile.Profile;
import com.zenbrowser.a1.model.Website.Site;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;


public class ProfileLimitsController extends ParentController {

    @FXML
    public ComboBox<Integer> minutesBox;
    @FXML
    public ComboBox<Integer> hoursBox;

    @FXML
    public TextField urlField;

    @FXML
    public Button UrlLimitData;

    @FXML
    public ComboBox<String> profileBox;

    @FXML
    private TableView<Profile> tbData;

    @FXML
    public TableColumn<Profile, String> profileColumn;

    @FXML
    public TableColumn<Profile, String> urlColumn;

    @FXML
    public TableColumn<Profile, Date> limitColumn;


    private ObservableList<Profile> profileData = FXCollections.observableArrayList();

    public void initialize() {
        profileColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getProfileName()));
        urlColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getWebsite().getURL()));
        limitColumn.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getBlockedUntil()));

        tbData.setItems(profileData);
        loadProfilesTable();
    }

    private void loadProfilesTable() {
        System.out.println("bye");
        for (Profile profile : ProfileDAO.getUserProfiles(super.getCurrentUser())) {

            profileData.add(profile);
            System.out.println(tbData.getColumns());
            System.out.println("hi");
            String[] blockedDuration = profile.getBlockedDuration();
            // Limit has expired
            if (blockedDuration == null) {
                try {
                    ProfileDAO.deleteProfile(profile.getId());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            profileData.add(profile);
        }
    }

    @FXML
    public void addUrlAndLimit() {
        String url = urlField.getText();
        Time blockTime = new Time(0);
        blockTime.setHours(hoursBox.getSelectionModel().getSelectedItem());
        blockTime.setMinutes(minutesBox.getSelectionModel().getSelectedItem());

        String profile = profileBox.getSelectionModel().getSelectedItem();
        if (!url.isEmpty() && blockTime.getTime()!=0 && profile != null && !profile.isEmpty()) {

            // Add to database
            // Check if site isn't already in the database
            Site site = SiteDAO.getSiteByURL(url);
            if (site == null) {
                site = new Site(url, "", "", true);
                site = SiteDAO.insertSite(site);
            }


            ProfileDAO.insertProfile(new Profile(
                    getCurrentUser(),
                    profile,
                    site,
                    blockTime));
            tbData.setItems(profileData);

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
