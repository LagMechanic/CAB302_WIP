package com.zenbrowser.a1.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ProfileLimitsController {

    @FXML
    private TextField urlField;

    @FXML
    private TextField limitField;

    @FXML
    private TableView<com.zenbrowser.a1.OliverBrowsingLimitsGUI.UrlLimit> urlTable;

    public void addUrlAndLimit() {
        String url = urlField.getText();
        String limit = limitField.getText();
        if (!url.isEmpty() && !limit.isEmpty()) {
            urlTable.getItems().add(new com.zenbrowser.a1.OliverBrowsingLimitsGUI.UrlLimit(url, limit));
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

}
