package com.zenbrowser.a1.Controller.MainControllers;

import com.zenbrowser.a1.model.ProfileLimits.UrlLimit;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;


public class ProfileLimitsController{

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
    }

    public void addUrlAndLimit() {
        String url = urlField.getText();
        String hours = hoursBox.getSelectionModel().getSelectedItem();
        String minutes = minutesBox.getSelectionModel().getSelectedItem();
        String profile = profileBox.getSelectionModel().getSelectedItem();
        if (!url.isEmpty() && hours != null && !hours.isEmpty() && minutes != null && !minutes.isEmpty() && profile != null && !profile.isEmpty()) {
            UrlLimit newUrlLimit = new UrlLimit(url, hours, minutes, profile);
            profileLimitsData.add(newUrlLimit);

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
