package com.zenbrowser.a1.Controller;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import com.zenbrowser.a1.model.FocusProfile.Profile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class BrowserMain extends ParentController implements Initializable {

    @FXML
    private ColorPicker colorPicker;
    @FXML
    MenuItem homePageBackgroundImg;

    //SearchEngine srcEng = new SearchEngine("google", "https://www.google.com");
    @FXML
    private CheckMenuItem googleMenuItm;
    @FXML
    private CheckMenuItem bingMenuItm;
    int EtG = 1;
    int EtB = 0;
    private double newTabLeftPadding = 102.0;
    @FXML
    private Button newTabBtn;
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
    private void homeBtnHover() {
        this.homeLabel.setText("Home");
    }

    @FXML
    private void homeBtnHoverExit() {
        this.homeLabel.setText("");
    }

    @FXML
    private void historyBtnHover() {
        this.historyLabel.setText("Downloads");
    }

    @FXML
    private void historyBtnHoverExit() {
        this.historyLabel.setText("");

    }

    @FXML
    private void profileBtnHover() {
        this.ProfileLabel.setText("Profile");
    }

    @FXML
    private void profileBtnHoverExit() {
        this.ProfileLabel.setText("");
    }

    public void initialize(URL url, ResourceBundle rb) {

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
    protected void GoToHomePage() {
        navigatePage("/com/zenbrowser/a1/Home-Page.fxml", "Home");
    }

    @FXML
    protected void GoToLoginPage() {
        navigatePage("/com/zenbrowser/a1/login-view.fxml", "Login");
    }

    @FXML
    protected void GoToHistoryPage() {
        navigatePage("/com/zenbrowser/a1/History-Page.fxml","History");
    }


    private Tab currentTab(){return tabPane.getSelectionModel().getSelectedItem();}


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

        navigatePage("/com/zenbrowser/a1/Home-Page.fxml", "Home");
    }

    public void GoToPreviousPage(ActionEvent actionEvent) {
    }

    public void GotoNextPage(ActionEvent actionEvent) {
    }

    public void goButtonPressed(ActionEvent actionEvent) {
    }

    public void GoReloadPage(ActionEvent actionEvent) {
    }

    public void setEngine(ActionEvent actionEvent) {
    }

    @FXML
    private void backgroundImgFunction() {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(stage);
        System.out.println("You chose this file: " + file.getAbsolutePath());
    }

    /**@FXML
    private void setEngine() {
    if (this.googleMenuItm.isSelected() && this.bingMenuItm.isSelected()) {
    if (this.EtG > this.EtB) {
    this.googleMenuItm.setSelected(false);
    this.EtG = 0;
    this.EtB = 1;
    this.srcEng.setEngine("bing");
    System.out.println("Bing is the engine and Google is disabled.");
    } else {
    this.bingMenuItm.setSelected(false);
    this.EtG = 1;
    this.EtB = 0;
    this.srcEng.setEngine("google");
    System.out.println("Google is the engine and Bing is disabled.");
    }
    } else if (this.googleMenuItm.isSelected()) {
    System.out.println("Inside google");
    this.srcEng.setEngine("google");
    System.out.println("Google is the engine and Bing is disabled.");
    this.EtG = 1;
    } else if (this.bingMenuItm.isSelected()) {
    System.out.println("Inside Bing.");
    this.srcEng.setEngine("bing");
    System.out.println("Bing is the engine and Google is disabled.");
    this.EtB = 1;
    }
    }**/

    /**public void goButtonPressed(ActionEvent actionEvent) {
        this.label.setText("");
        if (this.urlBox.getText() != null && !this.urlBox.getText().isEmpty()) {
            String urlStr;
            if (!this.urlBox.getText().contains(".")) {
                BrowserMain.this.srcEng.setUrlStr(this.urlBox.getText());
                urlStr = BrowserMain.this.srcEng.getEngineSpecificUrl();
            } else if (!this.urlBox.getText().startsWith("http://")) {
                urlStr = "http://" + this.urlBox.getText();
            } else if (!this.urlBox.getText().startsWith("http://www.")) {
                urlStr = "http://www." + this.urlBox.getText();
            } else {
                urlStr = this.urlBox.getText();
            }

            this.myBrowser = new MyBrowser(urlStr);
            this.borderPane.setCenter(this.myBrowser);
        } else {
            this.label.setText("You didn't enter anything : (");
        }
    }

    public void GoReloadPage(ActionEvent actionEvent) {
    }




    public void setTabBackground(String imageFileLocation) {
        ImageView iv = new ImageView();
        Image img = new Image(imageFileLocation);
        iv.setImage(img);
        this.borderPane.setCenter(iv);
    }

    class MyBrowser extends Region {
        WebView browser = new WebView();
        final WebEngine webEngine;
        WebHistory history;

        public MyBrowser(final String url) {
            this.webEngine = this.browser.getEngine();
            this.history = this.webEngine.getHistory();
            this.webEngine.getLoadWorker().stateProperty().addListener((ov, oldState, newState) -> {
                ProgressIndicator progInd = new ProgressIndicator(-1.0);
                progInd.setPrefHeight(17.0);
                progInd.setPrefWidth(25.0);
                NewTab.this.newTab.setGraphic(progInd);
                NewTab.this.reloadButton.setText("X");
                NewTab.this.reloadButton.setOnAction((e) -> {
                    NewTab.this.myBrowser.closeWindow();
                    NewTab.this.newTab.setText("Aborted!");
                    NewTab.this.label.setText("You have aborted loading the page.");
                    NewTab.this.newTab.setGraphic((Node)null);
                });
                if (newState == State.SUCCEEDED) {
                    NewTab.this.label.setText("");
                    NewTab.this.newTab.setText(MyBrowser.this.webEngine.getTitle());
                    NewTab.this.urlBox.setText(MyBrowser.this.webEngine.getLocation());
                    NewTab.this.newTab.setGraphic(MyBrowser.this.loadFavicon(url));
                    NewTab.this.reloadButton.setText("↺");
                    NewTab.this.reloadButton.setOnAction((e) -> {
                        NewTab.this.myBrowser.reloadWebPage();
                    });
                    EventListener var10000 = new EventListener() {
                        public void handleEvent(Event ev) {
                            System.out.println("You pressed on a link");
                        }
                    };
                    Document doc = MyBrowser.this.webEngine.getDocument();
                    NodeList el = doc.getElementsByTagName("a");

                    for(int i = 0; i < el.getLength(); ++i) {
                    }
                }

            });

            this.webEngine.setCreatePopupHandler((config) -> {
                this.browser.setFontScale(0.8);
                if (!this.getChildren().contains(this.browser)) {
                    this.getChildren().add(this.browser);
                }

                return this.browser.getEngine();
            });
            new WebView();
            this.webEngine.load(url);
            this.getChildren().add(this.browser);
        }

        public void goBack() {
            WebHistory history = this.webEngine.getHistory();
            ObservableList<WebHistory.Entry> entryList = history.getEntries();
            int currentIndex = history.getCurrentIndex();
            Platform.runLater(() -> history.go(entryList.size() > 1 && currentIndex > 0 ? -1 : 0));
        }

        public void goForward() {
            WebHistory history = this.webEngine.getHistory();
            ObservableList<WebHistory.Entry> entryList = history.getEntries();
            int currentIndex = history.getCurrentIndex();
            Platform.runLater(() -> history.go(entryList.size() > 1 && currentIndex < entryList.size() - 1 ? 1 : 0));
        }

        public ImageView loadFavicon(String location) {
            try {
                String faviconUrl;
                if (this.webEngine.getTitle().equalsIgnoreCase("Google")) {
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

        public void closeWindow() {
            this.browser.getEngine().load((String)null);
            this.browser = null;
        }

        public void reloadWebPage() {
            this.webEngine.reload();
        }

        protected void layoutChildren() {
            double w = this.getWidth();
            double h = this.getHeight();
            this.layoutInArea(this.browser, 0.0, 0.0, w, h, 0.0, HPos.CENTER, VPos.CENTER);
        }

        protected double computePrefWidth(double height) {
            return 750.0;
        }

        protected double computePrefHeight(double width) {
            return 500.0;
        }
    }
}

class SearchEngine {
    private String urlStr;
    private String engineName;

    private String formatUrl() {
        if (this.engineName.equalsIgnoreCase("google")) {
            this.urlStr = this.urlStr.replace(" ", "+");
            return "https://www.google.com/?gws_rd=cr&ei=YpHvV47aK8vWvATsl5X4CQ#q=" + this.urlStr;
        } else if (this.engineName.equalsIgnoreCase("bing")) {
            return "http://www.bing.com/search?q=" + this.urlStr;
        } else {
            System.out.println("No search eninge by " + this.engineName + " found.");
            return null;
        }
    }

    public SearchEngine(String engineName, String urlStr) {
        this.engineName = engineName;
        this.urlStr = urlStr;
        this.formatUrl();
    }

    public void setEngine(String engineName) {
        this.engineName = engineName;
    }

    public void setUrlStr(String urlStr) {
        this.urlStr = urlStr;
    }


    public String getUrl() {
        return this.urlStr;
    }

    public String getEngineSpecificUrl() {
        return this.formatUrl();
    }**/
}
