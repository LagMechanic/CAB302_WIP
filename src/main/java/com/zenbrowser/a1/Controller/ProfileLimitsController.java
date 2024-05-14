package com.zenbrowser.a1.Controller;

import com.zenbrowser.a1.model.ProfileLimits.UrlLimit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;


public class ProfileLimitsController{
    @FXML
    public TextField limitField;

    @FXML
    public TextField urlField;

    @FXML
    public Button UrlLimitData;

    @FXML
    public Button GoToProfileButton;

    @FXML
    public ComboBox<String> profileBox;

    @FXML
    private TableView<UrlLimit> tbData;

    @FXML
    public TableColumn<UrlLimit, String> profile;

    @FXML
    public TableColumn<UrlLimit, String> url;

    @FXML
    public TableColumn<UrlLimit, String> limit;

    private final ObservableList<UrlLimit> profileLimitsData = FXCollections.observableArrayList();

    public void initialize(){
        url.setCellValueFactory(new PropertyValueFactory<>("Url"));
        limit.setCellValueFactory(new PropertyValueFactory<>("Limit"));
        profile.setCellValueFactory(new PropertyValueFactory<>("Profile"));

        tbData.setItems(profileLimitsData);
    }

    public void addUrlAndLimit() {
        String url = urlField.getText();
        String limit = limitField.getText();
        String profile = profileBox.getSelectionModel().getSelectedItem();
        if (!url.isEmpty() && !limit.isEmpty() && profile != null && !profile.isEmpty()) {
            UrlLimit newUrlLimit = new UrlLimit(url, limit, profile);
            profileLimitsData.add(newUrlLimit);

            urlField.clear();
            limitField.clear();
            profileBox.getSelectionModel().clearSelection();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(null);
            alert.setContentText("URL, Limit, or Profile cannot be empty");
        alert.showAndWait();
        }

    }

}
