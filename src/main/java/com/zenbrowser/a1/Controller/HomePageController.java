package com.zenbrowser.a1.Controller;

import com.zenbrowser.a1.BrowserApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageController {
    @FXML
    private TextField searchField;

    @FXML
    private ListView<String> historyList;

    @FXML
    private Button searchButton;

    @FXML
    private Button GoToProfileButton;



    @FXML
    private void handleSearch() {
        String query = searchField.getText();
        System.out.println("Performing search for: " + query);
    }

    @FXML
    protected void onGoToProfileLimits() throws IOException {
        BrowserApplication.currentController.navigatePage("/com/zenbrowser/a1/ProfileLimits.fxml", "Profile Limits");
    }
}