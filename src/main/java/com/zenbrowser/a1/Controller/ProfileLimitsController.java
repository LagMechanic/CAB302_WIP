package com.zenbrowser.a1.Controller;

import com.zenbrowser.a1.BrowserApplication;
import com.zenbrowser.a1.model.ProfileLimits.ProfileLimitsApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.util.ResourceBundle;



public class ProfileLimitsController implements Initializable {
    @FXML
    public TextField limitField;

    @FXML
    public TextField urlField;

    @FXML
    public Button UrlLimitData;

    @FXML
    private TableView<ProfileLimitsApplication> tbData;

    @FXML
    public TableColumn<ProfileLimitsApplication, Integer> profile;

    @FXML
    public TableColumn<ProfileLimitsApplication, String> url;

    @FXML
    public TableColumn<ProfileLimitsApplication, String> limit;

    private final ObservableList<ProfileLimitsApplication> profileLimitsData = FXCollections.observableArrayList();

    public void initialize(URL location, ResourceBundle resources){
        url.setCellValueFactory(new PropertyValueFactory<>("Url"));
        limit.setCellValueFactory(new PropertyValueFactory<>("Limit"));
        profile.setCellValueFactory(new PropertyValueFactory<>("Profile"));

        tbData.setItems(ProfileLimitsApplication);
    }

    private final ObservableList<ProfileLimitsApplication> ProfileLimitsApplication = FXCollections.observableArrayList(
            new ProfileLimitsApplication("google.com", "30 minutes", 1),
            new ProfileLimitsApplication("bing.com", "30 minutes", 2)
    );


    @FXML
    protected void onGoToProfileLimits() {
        BrowserApplication.currentController.navigatePage("/com/zenbrowser/a1/register-view.fxml", "Register");
    }
}
