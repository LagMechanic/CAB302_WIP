package com.zenbrowser.a1.Controller;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.EventListener;
import java.util.ResourceBundle;

import javafx.concurrent.Worker;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import com.zenbrowser.a1.model.FocusProfile.Profile;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class BrowserMain extends ParentController implements Initializable {

    @FXML
    private WebView webView;

    private WebHistory history;
    protected WebEngine webEngine;
    private String defaultEngine;
    @FXML
    private Label promptLabel;
    @FXML
    private TextField URLBox;
    @FXML
    private Button ReloadButton;

    @FXML
    private ColorPicker colorPicker;
    @FXML
    MenuItem homePageBackgroundImg;
    @FXML
    private RadioMenuItem googleMenuItm;
    @FXML
    private RadioMenuItem bingMenuItm;
    @FXML
    private Label homeLabel;
    @FXML
    private Label historyLabel;
    @FXML
    private Label ProfileLabel;
    @FXML
    private BorderPane borderPane;
    @FXML
    private TabPane tabPane;


    @FXML
    private void homeBtnHover() {homeLabel.setText("Home");}

    @FXML
    private void homeBtnHoverExit() {homeLabel.setText("");}

    @FXML
    private void historyBtnHover() {historyLabel.setText("Downloads");}

    @FXML
    private void historyBtnHoverExit() {historyLabel.setText("");}

    @FXML
    private void profileBtnHover() {ProfileLabel.setText("Profile");}

    @FXML
    private void profileBtnHoverExit() {ProfileLabel.setText("");}

    public void initialize(URL url, ResourceBundle rb) {
        webEngine = webView.getEngine();

        newTabFunction();

        // Add a listener to the selectionModel property
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            if (newTab != null) {
                switchPage();
            }
        });

        this.colorPicker.setOnAction((EventHandler) t -> System.out.println("Color chosen: " + BrowserMain.this.colorPicker.getValue()));
    }


    @FXML
    protected void GoToHomePage() {navigatePage("/com/zenbrowser/a1/Home-Page.fxml", "Home");}

    @FXML
    protected void GoToLoginPage() {navigatePage("/com/zenbrowser/a1/login-view.fxml", "Login");}

    @FXML
    protected void GoToHistoryPage() {navigatePage("/com/zenbrowser/a1/History-Page.fxml","History");}


    private Tab currentTab()    {return tabPane.getSelectionModel().getSelectedItem();}

    public void loadPage(String urlStr)
    {
        try{
            webEngine.load(urlStr);
        }catch (Exception e){
            promptLabel.setText("You entered an invalid URL.");
        }

    }

    private void switchPage(){
        BorderPane content = (BorderPane) currentTab().getContent();
        borderPane.setCenter(content);
    }

    //Load a page into the parent BrowserTab.fxml with parameters of child source fxml file and name of tab.
    public void navigatePage(String pathway, String tabName){
        try {
            //load source fxml file and assign to a borderpane variable.
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pathway));
            BorderPane content = loader.load();

            //Attach source file to currently viewing tab and refresh display for user to see current page.
            currentTab().setContent(content);
            switchPage();
            currentTab().setText(tabName);

        } catch (IOException e) {e.printStackTrace();}
    }

    //Create new tab function.
    @FXML
    private void newTabFunction() {
        //Create a new tab and add to tab pane control.
        Tab tab = new Tab("New Tab");
        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().select(tab);
        //Create a buffer so tabs can be dynamically extended on browser.
        if (tabPane.getWidth() < borderPane.getWidth() * 0.8){
            tabPane.setMinWidth(tabPane.getWidth() + tabPane.getTabMaxWidth() + 10);
        }

        loadPage(setEngine());
    }

    @FXML
    private void GoToPreviousPage(ActionEvent actionEvent) {
        WebHistory history = webEngine.getHistory();
        ObservableList<WebHistory.Entry> entryList = history.getEntries();
        int currentIndex = history.getCurrentIndex();
        Platform.runLater(() -> history.go(entryList.size() > 1 && currentIndex > 0 ? -1 : 0));
    }

    @FXML
    private void GotoNextPage(ActionEvent actionEvent) {
        WebHistory history = webEngine.getHistory();
        ObservableList<WebHistory.Entry> entryList = history.getEntries();
        int currentIndex = history.getCurrentIndex();
        Platform.runLater(() -> history.go(entryList.size() > 1 && currentIndex < entryList.size() - 1 ? 1 : 0));
    }

    @FXML
    private void GoReloadPage(ActionEvent actionEvent) {
        webEngine.reload();
    }

    @FXML
    private void backgroundImgFunction() {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(stage);
        System.out.println("You chose this file: " + file.getAbsolutePath());
    }

    @FXML
    private String setEngine() {
        if (googleMenuItm.isSelected()) {
            System.out.println("Google is the default engine.");
            return defaultEngine = "https://www.google.com";

        } else if (bingMenuItm.isSelected()) {
            System.out.println("Bing is the default engine.");
            return defaultEngine ="https://www.bing.com";
        }
        return defaultEngine = "https://www.google.com";
    }

    private String formatUrl(String engineName, String query) {
            if (query.startsWith(engineName))
                return query;
            else {
                return engineName + "/search?q=" + query;
            }
    }

    @FXML
    private void goButtonPressed(ActionEvent actionEvent) {
        promptLabel.setText("");
        String PromptedSearch = URLBox.getText();
        if (PromptedSearch != null) {
            loadPage(formatUrl(defaultEngine, PromptedSearch));
        }
        else {promptLabel.setText("You didn't enter anything : (");}
    }

    public void setTabBackground(String imageFileLocation) {
        ImageView iv = new ImageView();
        Image img = new Image(imageFileLocation);
        iv.setImage(img);
        this.borderPane.setCenter(iv);
    }

    class MyBrowser extends Region {
        public MyBrowser(final String url) {
            history = webEngine.getHistory();
            webEngine.getLoadWorker().stateProperty().addListener((ov, oldState, newState) -> {
                ProgressIndicator progInd = new ProgressIndicator(-1.0);
                progInd.setPrefHeight(17.0);
                progInd.setPrefWidth(25.0);
                currentTab().setGraphic(progInd);
                ReloadButton.setText("X");
                ReloadButton.setOnAction((e) -> {
                    currentTab().setText("Aborted!");
                    promptLabel.setText("You have aborted loading the page.");
                    currentTab().setGraphic((Node)null);
                });
                if (newState == Worker.State.SUCCEEDED) {
                    promptLabel.setText("");
                    currentTab().setText(webEngine.getTitle());
                    URLBox.setText(webEngine.getLocation());
                    currentTab().setGraphic(loadFavicon(url));
                    ReloadButton.setText("↺");
                    EventListener var10000 = new EventListener() {
                        public void handleEvent(Event ev) {
                            System.out.println("You pressed on a link");
                        }
                    };
                    Document doc = webEngine.getDocument();
                    NodeList el = doc.getElementsByTagName("a");

                    for(int i = 0; i < el.getLength(); ++i) {
                    }
                }
            });

            webEngine.setCreatePopupHandler((config) -> {
                webView.setFontScale(0.8);
                if (!this.getChildren().contains(webView)) {
                    this.getChildren().add(webView);
                }

                return webView.getEngine();
            });
            webEngine.load(url);
        }

        public ImageView loadFavicon(String location) {
            try {
                String faviconUrl;
                if (webEngine.getTitle().equalsIgnoreCase("Google")) {
                    faviconUrl = "https://www.google.com/s2/favicons?domain_url=www.gmail.com";
                } else {
                    faviconUrl = String.format("http://www.google.com/s2/favicons?domain_url=%s", URLEncoder.encode(location, "UTF-8"));
                }

                Image favicon = new Image(faviconUrl, true);
                ImageView iv;
                if (favicon.equals(new Image("http://www.google.com/s2/favicons?domain_url=abc"))) {
                    iv = new ImageView(new Image("file:Resources/home.png"));
                    return iv;
                } else {
                    iv = new ImageView(favicon);
                    return iv;
                }
            } catch (UnsupportedEncodingException var5) {
                throw new RuntimeException(var5);
            }
        }
    }
}

