package com.zenbrowser.a1.Controller;

import com.zenbrowser.a1.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileLimitsController {

    @FXML
    private TextField urlField;

    @FXML
    private TextField limitField;

    @FXML
    private TableView<com.zenbrowser.a1.ProfileLimitsGUI.UrlLimit> urlTable;

    public void addUrlAndLimit() {
        String url = urlField.getText();
        String limit = limitField.getText();
        if (!url.isEmpty() && !limit.isEmpty()) {
            urlTable.getItems().add(new com.zenbrowser.a1.ProfileLimitsGUI.UrlLimit(url, limit));
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
    private Button GoToProfileButton;

    @FXML
    protected void onGoToProfileLimits() throws IOException {
        Stage stage = (Stage) GoToProfileButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ProfileLimits.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Limits Browser");
        stage.setScene(scene);
    }


}
