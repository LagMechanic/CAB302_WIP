package com.zenbrowser.a1.Controller;

import com.zenbrowser.a1.model.ProfileLimits.UrlLimit;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;


public class ProfileLimitsController{
    @FXML
    public TextField limitField;

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
    public TableColumn<UrlLimit, String> limit;

    private final ObservableList<UrlLimit> profileLimitsData = FXCollections.observableArrayList();

    public void initialize(){
        url.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().Url()));
        limit.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().Limit()));
        profile.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().Profile()));

        tbData.setItems(profileLimitsData);
    }


    private ProfileLimitsController.ButtonPressedListener buttonPressedListener;
    public interface ButtonPressedListener {
        void onButtonPressed(String destination);
    }
    public void setButtonPressedListener(ProfileLimitsController.ButtonPressedListener listener) {this.buttonPressedListener = listener;}

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
