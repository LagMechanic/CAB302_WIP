/**
 The TabController class is part of the ZenBrowser application and manages the functionality and user interactions of
 individual browser tabs. Extending ParentController and implementing Initializable, it initializes the browser tabs,
 handles navigation, and manages user interactions such as loading web pages, switching tabs,
 and setting search engine preferences.
 **/
package com.zenbrowser.a1.Controller.MainControllers;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.zenbrowser.a1.AuthenticationApplication;
import com.zenbrowser.a1.Controller.ParentController;
import com.zenbrowser.a1.model.BrowserUsage.HistoryRecord;
import com.zenbrowser.a1.model.FocusProfile.Profile;
import javafx.concurrent.Worker;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.web.WebHistory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.zenbrowser.a1.model.browserTab;

public class TabController extends ParentController implements Initializable {
    //Set default search engine url
    private String defaultEngine = "https://www.google.com";

    //Inject FXML Fields
    @FXML private TextField URLBox;
    @FXML MenuItem homePageBackgroundImg;
    @FXML private RadioMenuItem googleMenuItm;
    @FXML private RadioMenuItem bingMenuItm;
    @FXML private Label homeLabel;
    @FXML private Label historyLabel;
    @FXML private Label ProfileLabel;
    @FXML private Label settingsLabel;
    @FXML private BorderPane borderPane;
    @FXML private TabPane tabPane;
    @FXML private Label greetingLabel;

    //Current Browser Tab
    private browserTab currentTab;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Create a new tab upon initialization
        newTabFunction();

        // Add a listener to tab selection event.
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            if (newTab != null) {
                UpdatePage();
            } else {
                //Close the Application if no tabs are open
                Stage stageInstanance = (Stage) borderPane.getScene().getWindow();
                stageInstanance.close();
            }
        });
        // Display Greeting message to user
        if (super.getCurrentUser() != null){
            String greeting = String.format("Welcome to zenbrowser, %s!", getCurrentUser());
            greetingLabel.setText(greeting);
        }
        else {  greetingLabel.setText("Welcome to zenbrowser!");}
    }

    //Create new tab function.
    @FXML
    private void newTabFunction() {
        //Create a new tab and add to tab pane control.
        browserTab tab = new browserTab("New Tab");
        setupTabCloseHandler(tab);
        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().select(tab);


        AdjustTabpane();
        //Listener for when tab successfully loads.
        loadingListener(tab);


        UpdatePage();
        testProfileSelector();
        //if current tab is blocked change to Page Blocked
        currentTab.setGoToPageBlocked(() -> navigatePage("/com/zenbrowser/a1/PageBlocked.fxml","Page Blocked"));
        loadPage(defaultEngine);

    }
    //Method for selecting profiles
    private void testProfileSelector(){
        List<String> blockedURLs = new ArrayList<>();
        for (Profile profile : ProfileDAO.getUserProfiles(getCurrentUser())){
            blockedURLs.add(profile.getSiteURL());
        }
        currentTab.setBlocked(blockedURLs);
    }

    //Load a page into the parent BrowserTab.fxml with parameters of child source fxml file and name of tab.
    public void navigatePage(String pathway, String tabName){
        try {
            //load source fxml file and assign to a borderpane variable.
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pathway));
            BorderPane content = loader.load();
            currentTab.setPage(borderPane, content);
            currentTab.setText(tabName);

        } catch (IOException e) {e.printStackTrace();}
    }

    // Load a page with the specified URL
    private void loadPage(String urlStr)
    {
        try {
            // Add a listener for the load worker state
            currentTab.getWebEngine().getLoadWorker().stateProperty().addListener((observable, oldState, newState) -> {
                if (newState == Worker.State.SUCCEEDED) {
                    currentTab.setText(currentTab.getWebEngine().getTitle());
                    URLBox.setText(currentTab.getWebEngine().getLocation());
                } else if (newState == Worker.State.FAILED || newState == Worker.State.CANCELLED) {
                    System.out.println("Page failed to load.");
                }
            });
            currentTab.getWebEngine().setJavaScriptEnabled(true); // Ensure JavaScript is enabled
            currentTab.getWebEngine().setUserAgent("ZenBrowser 1.0"); // Set custom user agent
            currentTab.getWebEngine().load(urlStr);
            currentTab.setPage(borderPane, currentTab.getWebView());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Update the current tab's content
    private void UpdatePage(){
        currentTab = (browserTab) tabPane.getSelectionModel().getSelectedItem();
        borderPane.setCenter(currentTab.getPage());
    }

    // Add a listener for when the tab successfully loads
    private void loadingListener(browserTab tab){
        tab.getWebEngine().getLoadWorker().stateProperty().addListener((observable, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                currentTab.setText("");
                currentTab.setText(currentTab.getWebEngine().getTitle());
                URLBox.setText(currentTab.getWebEngine().getLocation());
                WebHistory.Entry entry = tab.getRecentHistory();
                HistoryDAO.insertHistoryRecord(new HistoryRecord(
                        getCurrentUser(),
                        entry.getTitle(),
                        entry.getUrl(),
                        new java.sql.Timestamp(entry.getLastVisitedDate().getTime())));
            } else if (newState == Worker.State.FAILED || newState == Worker.State.CANCELLED) {
                System.out.println("Page failed to load.");
            }
        });
    }

    // Handler for closing tabs
    private void setupTabCloseHandler(Tab tab) {
        tab.setOnCloseRequest(event -> {
            tabPane.setMinWidth(tabPane.getWidth() - tabPane.getTabMaxWidth() - 13);
        });
    }

    // Adjust the tab pane width dynamically
    private void AdjustTabpane(){
        //Create a buffer so tabs can be dynamically extended on browser.
        if (tabPane.getWidth() < borderPane.getWidth() * 0.8){
            tabPane.setMinWidth(tabPane.getWidth() + tabPane.getTabMaxWidth() + 13);
        }
    }

    // Format the URL with the search engine name and query
    private String formatUrl(String engineName, String query) {
        if (query.startsWith(engineName))
            return query;
        else {
            return engineName + "/search?q=" + query;
        }
    }


    // Navigate to the home page
    @FXML
    private void GoToHomePage() { loadPage(defaultEngine);}

    // Logout the user and start the authentication application
    @FXML
    private void LogoutUser() throws IOException {
        new AuthenticationApplication().start(new Stage());
        ((Stage) borderPane.getScene().getWindow()).close();
    }

    // Navigate to the history page
    @FXML
    private void GoToHistoryPage() {navigatePage("/com/zenbrowser/a1/history-view.fxml","History");}

    // Navigate to the usage reports page
    @FXML
    private void goUsageReports() {navigatePage("/com/zenbrowser/a1/usageInsights.fxml","Usage Report");}

    // Navigate to the profile limits page
    @FXML
    private void goProfileLimits() {navigatePage("/com/zenbrowser/a1/ProfileLimits.fxml","Zen Profiles");}

    // Navigate to the previous page in the browser history
    @FXML
    private void GoToPreviousPage() {
        ObservableList<WebHistory.Entry> entryList = currentTab.getHistory().getEntries();
        int currentIndex = currentTab.getHistory().getCurrentIndex();
        Platform.runLater(() -> currentTab.getHistory().go(entryList.size() > 1 && currentIndex > 0 ? -1 : 0));
    }

    // Navigate to the next page in the browser history
    @FXML
    private void GotoNextPage() {
        ObservableList<WebHistory.Entry> entryList = currentTab.getHistory().getEntries();
        int currentIndex = currentTab.getHistory().getCurrentIndex();
        Platform.runLater(() -> currentTab.getHistory().go(entryList.size() > 1 && currentIndex < entryList.size() - 1 ? 1 : 0));
    }

    // Reload the current page
    @FXML
    private void GoReloadPage() {
        currentTab.getWebEngine().reload();
    }

    // Function to select a background image
    @FXML
    private void backgroundImgFunction() {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(stage);
        System.out.println("You chose this file: " + file.getAbsolutePath());
    }

    // Set the default search engine
    @FXML
    private String setEngine() {
        if (googleMenuItm.isSelected()) {
            System.out.println("Google is the default engine.");
            return defaultEngine = "https://www.google.com";

        } else if (bingMenuItm.isSelected()) {
            System.out.println("Bing is the default engine.");
            return defaultEngine ="https://www.bing.com";
        }
        return null;
    }

    // Handle the go button press event
    @FXML
    private void goButtonPressed() {
        String PromptedSearch = URLBox.getText();
        System.out.println("PS: " + PromptedSearch);
        if (PromptedSearch != "") {

            if (PromptedSearch.startsWith("https")){    }

            else if (PromptedSearch.startsWith("www.")){
                PromptedSearch = "https://" + PromptedSearch;
            }
            else{
                PromptedSearch= (formatUrl(defaultEngine, PromptedSearch));
            }
        }
        loadPage(PromptedSearch);
    }



    // Hover event handlers for button tooltips
    @FXML private void settingsBtnHover() {settingsLabel.setText("Settings");}
    @FXML private void settingsBtnHoverExit() {settingsLabel.setText("");}
    @FXML private void homeBtnHover() {homeLabel.setText("Home");}
    @FXML private void homeBtnHoverExit() {homeLabel.setText("");}
    @FXML private void historyBtnHover() {historyLabel.setText("History");}
    @FXML private void historyBtnHoverExit() {historyLabel.setText("");}
    @FXML private void logoutBtnHover() {ProfileLabel.setText("Logout User");}
    @FXML private void logoutBtnHoverExit() {ProfileLabel.setText("");}


}

