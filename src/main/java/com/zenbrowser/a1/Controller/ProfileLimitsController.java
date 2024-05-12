package com.zenbrowser.a1.Controller;

import com.zenbrowser.a1.BrowserApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class ProfileLimitsController extends ParentController {

    private record UrlLimit(String url, String limit) { }

    @FXML
    private TextField urlField;

    @FXML
    private TextField limitField;

    @FXML
    private TableView<UrlLimit> urlTable;

    private ProfileLimitsController.ButtonPressedListener buttonPressedListener;
    public interface ButtonPressedListener {
        void onButtonPressed(String destination);
    }
    public void setButtonPressedListener(ProfileLimitsController.ButtonPressedListener listener) {this.buttonPressedListener = listener;}

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
    protected void onGoToProfileLimits() throws IOException {
        BrowserApplication.currentController.navigatePage("/com/zenbrowser/a1/register-view.fxml", "Register");
    }
}
