package com.zenbrowser.a1.Controller.MainControllers;

import com.zenbrowser.a1.BrowserApplication;
import com.zenbrowser.a1.Controller.ParentController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;

import java.io.IOException;

public class HomePageController extends ParentController {

    @FXML
    private BorderPane homePane;
    @FXML
    private Button goUsageReports;
    @FXML
    private Button goNotificationPage;
    @FXML
    private Button goAccountSettings;
    @FXML
    private Label greetingLabel;


    @FXML
    public void initialize(){
        if (super.getCurrentUser() != null){
            String greeting = String.format("Welcome to zenbrowser, %s!", getCurrentUser());
            greetingLabel.setText(greeting);
        }
        else {  greetingLabel.setText("Welcome to zenbrowser!");}


    }


    @FXML
    protected void onGoToProfileLimits() throws IOException {
        BrowserApplication.tabController.navigatePage("/com/zenbrowser/a1/ProfileLimits.fxml", "Profile Limits");
    }

    public void updateLoading(WebView browserView){
        homePane.setCenter(browserView);
    }
}