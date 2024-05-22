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


import java.sql.SQLException;
import java.sql.Time;
import java.util.List;


public class ProfileLimitsController extends ParentController {
    @FXML
    private ComboBox<Integer> minutesBox;
    @FXML
    private ComboBox<Integer> hoursBox;
    @FXML
    private TextField urlField;
    @FXML
    private ComboBox<String> profileBox;
    @FXML
    private TableView<Profile> tbData;
    @FXML
    private TableColumn<Profile, String> profileColumn;
    @FXML
    private TableColumn<Profile, String> urlColumn;
    @FXML
    private TableColumn<Profile, Time> limitColumn;
    @FXML
    private TableColumn <Profile,Void> deleteColumn;

    private final ObservableList<Profile> profileData = FXCollections.observableArrayList();



    //Methods


    public void initialize() {

        PopulateComboBox();

        LinkTable();

        loadProfilesTable();
    }



    @FXML
    private void addUrlAndLimit() {

        String url = urlField.getText();

        Time blockTime = new Time(0);
        int timeOffset = 10;
        blockTime.setHours(hoursBox.getSelectionModel().getSelectedItem() + timeOffset);
        blockTime.setMinutes(minutesBox.getSelectionModel().getSelectedItem());

        String profile = profileBox.getSelectionModel().getSelectedItem();

        if (!url.isEmpty() && blockTime.getTime()!=0 && profile != null) {
            ProfileDAO.insertProfile(new Profile(
                    getCurrentUser(),
                    profile,
                    url,
                    blockTime));

            loadProfilesTable();

            // Reset form
            urlField.clear();
            hoursBox.getSelectionModel().clearSelection();
            minutesBox.getSelectionModel().clearSelection();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Profile, Hours, Minutes, or URL cannot be empty");
            alert.showAndWait();
        }
    }

    @FXML
    private void changeProfile() {  loadProfilesTable();}


    private void loadProfilesTable()  {
        String selectedprofile = profileBox.getSelectionModel().getSelectedItem();
        List<Profile> profilesEntries;

        if (selectedprofile == null){
            profilesEntries = ProfileDAO.getUserProfiles(getCurrentUser());
        }else{
            profilesEntries = ProfileDAO.getSingleProfile(getCurrentUser(), selectedprofile);
        }

        profileData.clear();
        for (Profile profile : profilesEntries) {
            profileData.add(profile);

            String[] blockedDuration = profile.getBlockedDuration();
            // Limit has expired
            if (blockedDuration == null) {

            }
        }
    }

    private void LinkTable() {
        profileColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getProfileName()));
        urlColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getSiteURL()));
        limitColumn.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getBlockedUntil()));

        deleteColumn.setCellFactory(cell -> new TableCell<>() {
            private final Button deleteButton;
            {
                deleteButton = new Button("Delete");
                deleteButton.setOnAction(evt -> {
                    Profile entry = getTableRow().getItem();

                    try {
                        ProfileDAO.deleteProfile(entry.getId());
                        loadProfilesTable();

                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : deleteButton);
            }
        });

        tbData.setItems(profileData);
    }

    private void PopulateComboBox(){
        hoursBox.setItems(FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,13,14,15,16,17,18,19,20,21,22,23,24));
        minutesBox.setItems(FXCollections.observableArrayList(0,5,10,15,20,25,30,35,40,45,50,55));
    }
}
