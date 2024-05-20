package com.zenbrowser.a1.Controller.MainControllers;

import com.zenbrowser.a1.BrowserApplication;
import com.zenbrowser.a1.Controller.ParentController;
import com.zenbrowser.a1.model.ProfileLimits.UrlLimit;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ProfileLimitsController extends ParentController {
    @FXML
    public TextField limitField;

    @FXML
    public TextField urlField;

    private record UrlLimit(String url, String limit) { }


    @FXML
    private TableView<UrlLimit> urlTable;

//    private final ObservableList<UrlLimit> profileLimitsData = FXCollections.observableArrayList();

//    public void initialize(){
//        url.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().Url()));
//        limit.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().Limit()));
//        profile.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().Profile()));
//
//        tbData.setItems(profileLimitsData);
//    }

    public void addUrlAndLimit() {
        String url = urlField.getText();
        String limit = limitField.getText();
        if (!url.isEmpty() && !limit.isEmpty()) {
            urlTable.getItems().add(new UrlLimit(url, limit));
            urlField.clear();
            limitField.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("URL or Limit cannot be empty");
            alert.showAndWait();
        }
    }

    @FXML
    protected void onGoToProfileLimits()  {
        BrowserApplication.tabController.navigatePage("/com/zenbrowser/a1/register-view.fxml", "Register");
    }
}
