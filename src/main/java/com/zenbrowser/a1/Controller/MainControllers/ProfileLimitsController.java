/**
 The ProfileLimitsController class manages the focus profile functionality in the ZenBrowser application.
 It extends the ParentController and is responsible for setting time limits on websites within different profiles.
 **/

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
import java.util.Arrays;
import java.util.List;


public class ProfileLimitsController extends ParentController {
    // FXML-injected fields for the UI components
    @FXML private ComboBox<Integer> minutesBox;
    @FXML private ComboBox<Integer> hoursBox;
    @FXML private TextField urlField;
    @FXML private ComboBox<String> profileBox;
    @FXML private TableView<Profile> tbData;
    @FXML private TableColumn<Profile, String> profileColumn;
    @FXML private TableColumn<Profile, String> urlColumn;
    @FXML private TableColumn<Profile, Time> limitColumn;
    @FXML private TableColumn <Profile,Void> deleteColumn;

    // Observable list to hold the profile data
    private final ObservableList<Profile> profileData = FXCollections.observableArrayList();



    // Initialize method called when the controller is created
    public void initialize() {
        // Populate the combo boxes for hours and minutes
        PopulateComboBox();
        // Link the table columns to the profile properties
        LinkTable();
        // Load the profiles into the table
        loadProfilesTable();
    }


    // Method to add a URL and limit to the profile
    @FXML
    private void addUrlAndLimit() {

        String url = urlField.getText();
        // Create a Time object for the block time
        Time blockTime = new Time(0);
        int timeOffset = 10;
        blockTime.setHours(hoursBox.getSelectionModel().getSelectedItem() + timeOffset);
        blockTime.setMinutes(minutesBox.getSelectionModel().getSelectedItem());

        String profile = profileBox.getSelectionModel().getSelectedItem();
        // Check if the URL, block time, and profile are valid
        if (!url.isEmpty() && blockTime.getTime()!=0 && profile != null) {
            // Insert the new profile into the database
            ProfileDAO.insertProfile(new Profile(
                    getCurrentUser(),
                    profile,
                    url,
                    blockTime));
            // Reload the profiles table
            loadProfilesTable();

            // Reset form
            urlField.clear();
            hoursBox.getSelectionModel().clearSelection();
            minutesBox.getSelectionModel().clearSelection();

        } else {
            // Show an error alert if any field is empty
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("Profile, Hours, Minutes, or URL cannot be empty");
            alert.showAndWait();
        }
    }

    // Method to handle changing the selected profile
    @FXML
    private void changeProfile() {  loadProfilesTable();}

    // Method to load the profiles into the table
    private void loadProfilesTable()  {
        String selectedprofile = profileBox.getSelectionModel().getSelectedItem();
        List<Profile> profilesEntries;
        // Get the profiles for the current user
        if (selectedprofile == null){
            profilesEntries = ProfileDAO.getUserProfiles(getCurrentUser());
        }else{
            profilesEntries = ProfileDAO.getSingleProfile(getCurrentUser(), selectedprofile);
        }
        // Clear the existing profile data
        profileData.clear();
        for (Profile profile : profilesEntries) {
            // Check if the block duration has expired
            String[] blockedDuration = profile.getBlockedDuration();

            if (blockedDuration == null) {
                try {
                    ProfileDAO.deleteProfile(profile.getId());
                    continue;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Add the profile to the observable list
            profileData.add(profile);
        }
    }

    // Method to link the table columns to the profile properties
    private void LinkTable() {
        profileColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getProfileName()));
        urlColumn.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().getSiteURL()));
        limitColumn.setCellValueFactory(cell -> new SimpleObjectProperty<>(cell.getValue().getBlockedUntil()));

        // Set up the delete button in the table
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
    // Set the items for the table
        tbData.setItems(profileData);
    }

    // Method to populate the combo boxes for hours and minutes
    private void PopulateComboBox(){
        hoursBox.setItems(FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,13,14,15,16,17,18,19,20,21,22,23,24));
        minutesBox.setItems(FXCollections.observableArrayList(0,5,10,15,20,25,30,35,40,45,50,55));
    }
}
