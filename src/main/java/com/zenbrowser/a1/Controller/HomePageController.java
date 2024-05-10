package com.zenbrowser.a1.Controller;

import com.zenbrowser.a1.BrowserApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController extends ParentController{
    @FXML
    private Label greetingLabel;
    @FXML
    private TextField searchField;

    @FXML
    private ListView<String> historyList;

    @FXML
    private Button searchButton;

    @FXML
    private Button GoToProfileButton;

    @FXML
    public void initialize(){
        if (super.getCurrentUser() != null){
            String greeting = String.format("Welcome to zenbrowser%s!", getCurrentUser());
            greetingLabel.setText(greeting);
        }
        else {  greetingLabel.setText("Welcome to zenbrowser!");}
    }

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