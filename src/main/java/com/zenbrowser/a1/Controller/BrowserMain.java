package com.zenbrowser.a1.Controller;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Time;
import java.time.LocalDate;
import java.util.EventListener;
import java.util.ResourceBundle;

import com.zenbrowser.a1.model.Authentication.Authentication;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class BrowserMain implements Initializable {
    @FXML
    private TabPane tabPane;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    MenuItem homePageBackgroundImg;

    SearchEngine srcEng = new SearchEngine("google", "https://www.google.com");
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
    private Button ProfileButton;
    private NewTab aTab = new NewTab();
    HistoryObject histObj;
    @FXML
    private Button GoToHistoryPageButton;



    @FXML
    Label historyLabl;

    @FXML
    private Button GoToHomePageButton;

    public BrowserMain() {
    }



    @FXML
    private void setEngine() {
        if (this.googleMenuItm.isSelected() && this.bingMenuItm.isSelected()) {
            if (this.EtG > this.EtB) {
                this.googleMenuItm.setSelected(false);
                this.EtG = 0;
                this.EtB = 1;
                this.srcEng.setEngine("bing");
                System.out.println("Bing is the eninge and Google is disabled.");
            } else {
                this.bingMenuItm.setSelected(false);
                this.EtG = 1;
                this.EtB = 0;
                this.srcEng.setEngine("google");
                System.out.println("Google is the eninge and Bing is disabled.");
            }
        } else if (this.googleMenuItm.isSelected()) {
            System.out.println("Inside google");
            this.srcEng.setEngine("google");
            System.out.println("Google is the eninge and Bing is disabled.");
            this.EtG = 1;
        } else if (this.bingMenuItm.isSelected()) {
            System.out.println("Inside Bing.");
            this.srcEng.setEngine("bing");
            System.out.println("Bing is the eninge and Google is disabled.");
            this.EtB = 1;
        }

    }

    @FXML
    private void newTabFunction(ActionEvent event) {
        NewTab aTab = new NewTab();
        Tab tab = aTab.createTab();
        this.tabPane.getTabs().add(tab);
        this.tabPane.getSelectionModel().select(tab);
        this.newTabBtnPosRight();
    }

    private void newTabBtnPosRight() {
        this.newTabLeftPadding += 91.0;
        AnchorPane.setLeftAnchor(this.newTabBtn, (double) (this.newTabLeftPadding++));
    }

    private void newTabBtnPosLeft() {
        this.newTabLeftPadding -= 91.0;
        AnchorPane.setLeftAnchor(this.newTabBtn, (double) (this.newTabLeftPadding--));
        if (this.newTabLeftPadding < 102.0) {
            System.out.println("All tabs closed.");
            Platform.exit();
        }

    }

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
        this.aTab.setTabBackground("file:src/main/resources/Icons/b46c8e1cde764e377f0ed9399e6380a6.jpg");
        Tab tab = this.aTab.createTab();
        tab.setText("Home Tab");
        this.tabPane.getTabs().add(tab);
        ImageView iv = new ImageView();
        Image img = new Image("file:src/main/resources/Icons/home.png");
        iv.setImage(img);
        iv.setFitHeight(21.0);
        iv.setFitWidth(20.0);
        this.GoToHomePageButton.setGraphic(iv);
        ImageView iv2 = new ImageView();
        Image img2 = new Image("file:src/main/resources/Icons/history.png");
        iv2.setImage(img2);
        iv2.setFitHeight(21.0);
        iv2.setFitWidth(20.0);
        this.GoToHistoryPageButton.setGraphic(iv2);
        ImageView iv3 = new ImageView();
        Image img3 = new Image("file:src/main/resources/Icons/UserIcon.png");
        iv3.setImage(img3);
        iv3.setFitHeight(21.0);
        iv3.setFitWidth(20.0);
        this.ProfileButton.setGraphic(iv3);


        this.colorPicker.setOnAction((EventHandler) t -> System.out.println("Color choosed: " + BrowserMain.this.colorPicker.getValue()));
        this.histObj = new HistoryObject();
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
    protected void GoToHomePage() {
        if (Authentication.getInstance().userLoggedIn()) {
            try {
                // Load the content of the home page
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zenbrowser/a1/Home-Page.fxml"));
                Parent homePageContent = loader.load();
                HomePageController homeViewController = loader.getController();
                homeViewController.setButtonPressedListener(this::HandleButtonPressed);
                // Get the content container from the existing tab structure
                this.aTab = new NewTab();
                Tab tab = this.aTab.createTab();
                tab.setText("Home Tab");
                AnchorPane contentPane = (AnchorPane) tab.getContent();
                BorderPane borderPane = (BorderPane) contentPane.getChildren().get(3); // Assuming the BorderPane is at index 3

                // Add the content of the home page to the center of the BorderPane
                borderPane.setCenter(homePageContent);
                this.tabPane.getTabs().add(tab);
                this.tabPane.getSelectionModel().select(tab);
                this.newTabBtnPosRight();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            GoToLoginPage();
        }
    }
    @FXML
    protected void GoToLoginPage() {
        if (Authentication.getInstance().userLoggedIn())
            GoToHomePage();
        else {
            try {
                // Load the content of the home page
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zenbrowser/a1/login-view.fxml"));
                Parent LoginPageContent = loader.load();
                LoginController loginViewController = loader.getController();
                loginViewController.setButtonPressedListener(this::HandleButtonPressed);
                // Get the content container from the existing tab structure
                this.aTab = new NewTab();
                Tab tab = this.aTab.createTab();
                tab.setText("Login");
                AnchorPane contentPane = (AnchorPane) tab.getContent();
                BorderPane borderPane = (BorderPane) contentPane.getChildren().get(3); // Assuming the BorderPane is at index 3

                // Add the content of the home page to the center of the BorderPane
                borderPane.setCenter(LoginPageContent);
                this.tabPane.getTabs().add(tab);
                this.tabPane.getSelectionModel().select(tab);
                this.newTabBtnPosRight();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void HandleButtonPressed(String destination) {
        if ("Register".equals(destination)) {
            try {


                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zenbrowser/a1/register-view.fxml"));
                Parent PageContent = loader.load();
                // Get the content container from the existing tab structure
                RegisterController registerViewController = loader.getController();
                registerViewController.setButtonPressedListener(this::HandleButtonPressed);
                Tab tab = this.tabPane.getSelectionModel().getSelectedItem();

                tab.setText("Register"); // Update tab text if needed

                // Get the content container from the existing tab
                AnchorPane contentPane = (AnchorPane) tab.getContent();
                BorderPane borderPane = (BorderPane) contentPane.getChildren().get(3); // Assuming the BorderPane is at index 3

                // Replace the content of the current tab with the new content
                borderPane.setCenter(PageContent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if ("Login".equals(destination)) {
            try {


                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zenbrowser/a1/login-view.fxml"));
                Parent PageContent = loader.load();
                // Get the content container from the existing tab structure
                LoginController loginViewController = loader.getController();
                loginViewController.setButtonPressedListener(this::HandleButtonPressed);
                Tab tab = this.tabPane.getSelectionModel().getSelectedItem();

                tab.setText("Login"); // Update tab text if needed

                // Get the content container from the existing tab
                AnchorPane contentPane = (AnchorPane) tab.getContent();
                BorderPane borderPane = (BorderPane) contentPane.getChildren().get(3); // Assuming the BorderPane is at index 3

                // Replace the content of the current tab with the new content
                borderPane.setCenter(PageContent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if ("Home".equals(destination)) {
            try {


                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zenbrowser/a1/Home-Page.fxml"));
                Parent PageContent = loader.load();
                // Get the content container from the existing tab structure
                HomePageController homeViewController = loader.getController();
                homeViewController.setButtonPressedListener(this::HandleButtonPressed);
                Tab tab = this.tabPane.getSelectionModel().getSelectedItem();

                tab.setText("Home"); // Update tab text if needed

                // Get the content container from the existing tab
                AnchorPane contentPane = (AnchorPane) tab.getContent();
                BorderPane borderPane = (BorderPane) contentPane.getChildren().get(3); // Assuming the BorderPane is at index 3

                // Replace the content of the current tab with the new content
                borderPane.setCenter(PageContent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if ("ProfileLimits".equals(destination)) {
            try {


                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zenbrowser/a1/ProfileLimits.fxml"));
                Parent PageContent = loader.load();
                // Get the content container from the existing tab structure
                ProfileLimitsController profileViewController = loader.getController();
                profileViewController.setButtonPressedListener(this::HandleButtonPressed);
                Tab tab = this.tabPane.getSelectionModel().getSelectedItem();

                tab.setText("Limits"); // Update tab text if needed

                // Get the content container from the existing tab
                AnchorPane contentPane = (AnchorPane) tab.getContent();
                BorderPane borderPane = (BorderPane) contentPane.getChildren().get(3); // Assuming the BorderPane is at index 3

                // Replace the content of the current tab with the new content
                borderPane.setCenter(PageContent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @FXML
    protected void GoToHistoryPage() {
        try {
            // Load the content of the home page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/zenbrowser/a1/History-Page.fxml"));
            Parent historyPageContent = loader.load();

            // Get the content container from the existing tab structure
            this.aTab = new NewTab();
            Tab tab = this.aTab.createTab();
            tab.setText("History Tab");
            AnchorPane contentPane = (AnchorPane) tab.getContent();
            BorderPane borderPane = (BorderPane) contentPane.getChildren().get(3); // Assuming the BorderPane is at index 3

            // Add the content of the home page to the center of the BorderPane
            borderPane.setCenter(historyPageContent);
            this.tabPane.getTabs().add(tab);
            this.tabPane.getSelectionModel().select(tab);
            this.newTabBtnPosRight();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    class NewTab {
        private final Tab newTab = new Tab();
        private final AnchorPane smallAnchor = new AnchorPane();
        private final ToolBar toolBar = new ToolBar();
        private final Label label = new Label();
        private final MenuBar menuBar = new MenuBar();
        private final HBox hBox = new HBox();
        private final TextField urlBox = new TextField();
        private final Button goButton = new Button();
        private final Button backButton = new Button();
        private final Button forwardButton = new Button();
        private final Button reloadButton = new Button();
        private final BorderPane borderPane = new BorderPane();
        private MyBrowser myBrowser;

        public NewTab() {
        }

        public Tab createTab() {
            this.goButton.setText("Go");
            this.newTab.setText("New Tab");
            this.backButton.setText("◁");
            this.forwardButton.setText("▷");
            this.toolBar.getItems().addAll(new Node[]{this.backButton, this.forwardButton});
            this.toolBar.setPrefHeight(40.0);
            this.toolBar.setPrefWidth(549.0);
            AnchorPane.setTopAnchor(this.toolBar, 0.0);
            AnchorPane.setLeftAnchor(this.toolBar, 0.0);
            AnchorPane.setRightAnchor(this.toolBar, 0.0);
            this.smallAnchor.getChildren().add(this.toolBar);
            this.menuBar.setPrefWidth(190.0);
            this.menuBar.setPrefHeight(40.0);
            this.menuBar.setPadding(new Insets(6.0, 0.0, 0.0, 0.0));
            AnchorPane.setRightAnchor(this.menuBar, 0.0);
            this.urlBox.setPromptText("\ud83d\udd0e enter your url here or search something");
            this.urlBox.setPrefHeight(30.0);
            this.urlBox.setPrefWidth(400);
            this.goButton.setPrefHeight(30.0);
            this.goButton.setPrefWidth(32.0);
            this.reloadButton.setText("↺");
            this.reloadButton.setPrefHeight(30.0);
            this.reloadButton.setPrefWidth(24.0);
            this.hBox.getChildren().addAll(new Node[]{this.urlBox, this.goButton, this.reloadButton});
            this.hBox.setSpacing(5.0);
            AnchorPane.setTopAnchor(this.hBox, 5.0);
            AnchorPane.setLeftAnchor(this.hBox, 60.0);
            this.smallAnchor.getChildren().add(this.hBox);
            AnchorPane.setTopAnchor(this.label, 10.0);
            AnchorPane.setLeftAnchor(this.label, 520.0);
            this.smallAnchor.getChildren().add(this.label);
            AnchorPane.setTopAnchor(this.borderPane, 40.0);
            AnchorPane.setBottomAnchor(this.borderPane, 0.0);
            AnchorPane.setLeftAnchor(this.borderPane, 0.0);
            AnchorPane.setRightAnchor(this.borderPane, 0.0);
            this.smallAnchor.getChildren().add(this.borderPane);
            this.newTab.setContent(this.smallAnchor);
            this.newTab.setOnClosed((arg) -> {
                System.out.println("A tab closed.");
                BrowserMain.this.newTabBtnPosLeft();
                this.myBrowser.closeWindow();
            });
            this.backButton.setOnMouseClicked((me) -> {
                System.out.println("Back button has been pressed.");
                this.myBrowser.goBack();
                this.label.setText("");
            });
            this.forwardButton.setOnMouseClicked((me) -> {
                System.out.println("Forward button has been pressed.");
                this.myBrowser.goForward();
                this.label.setText("");
            });
            AnchorPane.setTopAnchor(BrowserMain.this.tabPane, 0.0);
            AnchorPane.setBottomAnchor(BrowserMain.this.tabPane, 0.0);
            AnchorPane.setLeftAnchor(BrowserMain.this.tabPane, 0.0);
            AnchorPane.setRightAnchor(BrowserMain.this.tabPane, 0.0);
            this.goButton.setOnAction((e) -> this.goButtonPressed());
            this.reloadButton.setOnAction((e) -> this.myBrowser.reloadWebPage());
            this.urlBox.setOnAction((e) -> this.goButtonPressed());
            return this.newTab;
        }

        void goButtonPressed() {
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
        }
    }



    public class HistoryObject implements Serializable {
        String url;
        LocalDate date;
        Time time;

        public String toString() {
            return "Url=" + this.url + " Date=" + this.date + " Time=" + this.time + '}';
        }

        public HistoryObject() {
        }



    }
}
