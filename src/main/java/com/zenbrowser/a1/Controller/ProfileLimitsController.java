package com.zenbrowser.a1.Controller;

import com.zenbrowser.a1.BrowserApplication;
import com.zenbrowser.a1.model.ProfileLimits.ProfileLimitsDBManager;
import com.zenbrowser.a1.model.ProfileLimits.UrlLimit;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ProfileLimitsController extends ParentController {
    @FXML
    private ComboBox<String> limitComboBox;

    @FXML
    private TextField urlField;

    @FXML
    private TableView<com.zenbrowser.a1.model.ProfileLimits.UrlLimit> urlTable;

    private final ProfileLimitsDBManager profileLimitsDBManager;

    public ProfileLimitsController() {
        this.profileLimitsDBManager = new ProfileLimitsDBManager();
    }

    public void addUrlAndLimit() {
        String url = urlField.getText();
        String limit = limitComboBox.getValue();
        if (!url.isEmpty() && !limit.isEmpty()) {
            urlTable.getItems().add(new UrlLimit(url, limit));
            profileLimitsDBManager.addUrlAndLimit(url, limit);
            urlField.clear();
            limitComboBox.getSelectionModel().clearSelection();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("URL or Limit cannot be empty");
            alert.showAndWait();
        }
    }

    @FXML
    protected void onGoToProfileLimits() {
        BrowserApplication.currentController.navigatePage("/com/zenbrowser/a1/register-view.fxml", "Register");
    }


}
