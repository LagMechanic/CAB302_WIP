package com.zenbrowser.a1.Controller.MainControllers;

import com.zenbrowser.a1.Controller.ParentController;
import com.zenbrowser.a1.model.FocusProfile.Profile;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;


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
    public TableColumn<Profile, Time> limitColumn;


    private ObservableList<Profile> profileData = FXCollections.observableArrayList();

    public void initialize() {
        profileColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getProfileName()));
        urlColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getSiteURL()));
        limitColumn.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getBlockedUntil()));

        tbData.setItems(profileData);
        loadProfilesTable();
    }

    private void loadProfilesTable() {
        for (Profile profile : ProfileDAO.getUserProfiles(super.getCurrentUser())) {

            profileData.add(profile);

            String[] blockedDuration = profile.getBlockedDuration();
            // Limit has expired
            if (blockedDuration == null) {

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

            ProfileDAO.insertProfile(new Profile(
                    getCurrentUser(),
                    profile,
                    url,
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
