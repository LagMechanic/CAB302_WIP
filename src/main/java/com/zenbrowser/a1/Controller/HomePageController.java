package com.zenbrowser.a1.Controller;

import com.zenbrowser.a1.BrowserApplication;
import com.zenbrowser.a1.model.BrowserUsage.HistoryRecord;
import com.zenbrowser.a1.model.browserTab;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebHistory;

import java.io.IOException;
import java.sql.Timestamp;

public class HomePageController extends ParentController{

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

    public void loadPage(String urlStr, browserTab tab)
    {
        try{
            tab.getWebEngine().load(urlStr);
            homePane.setCenter(tab.getWebView());

            tab.getWebEngine().getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
                if (newState == Worker.State.SUCCEEDED) {

                    WebHistory.Entry entry = tab.getRecentHistory();
                    HistoryDAO.insertHistoryRecord(new HistoryRecord(
                            getCurrentUser(),
                            entry.getTitle(),
                            entry.getUrl(),
                            new Timestamp(entry.getLastVisitedDate().getTime())));

                } else if (newState == Worker.State.FAILED) {
                    System.out.println("Page loading failed!");
                }
            });
        }catch (Exception e){
            System.out.println("Page loading failed!");
        }
    }
}