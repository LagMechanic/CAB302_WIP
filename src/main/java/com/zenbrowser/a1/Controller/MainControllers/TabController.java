package com.zenbrowser.a1.Controller.MainControllers;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.EventListener;
import java.util.ResourceBundle;

import com.zenbrowser.a1.Controller.ParentController;
import com.zenbrowser.a1.model.BrowserUsage.HistoryRecord;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.application.Platform;
import javafx.collections.ObservableList;
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
import javafx.scene.web.WebHistory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import com.zenbrowser.a1.model.browserTab;

public class TabController extends ParentController implements Initializable {

    private String defaultEngine = "https://www.google.com";
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
    private void historyBtnHover() {historyLabel.setText("History");}

    @FXML
    private void historyBtnHoverExit() {historyLabel.setText("");}

    @FXML
    private void profileBtnHover() {ProfileLabel.setText("Profile");}

    @FXML
    private void profileBtnHoverExit() {ProfileLabel.setText("");}

    private void setupTabCloseHandler(Tab tab) {
        tab.setOnCloseRequest(event -> {
            tabPane.setMinWidth(tabPane.getWidth() - tabPane.getTabMaxWidth() - 13);
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        newTabFunction();

        // Add a listener to tab selection event.
        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            if (newTab != null) {
                switchPage();
            } else {
                Stage stageInstanance = (Stage) borderPane.getScene().getWindow();
                stageInstanance.close();
            }
        });

        this.colorPicker.setOnAction((EventHandler) t -> System.out.println("Color chosen: " + TabController.this.colorPicker.getValue()));
    }

    //Create new tab function.
    @FXML
    private void newTabFunction() {
        //Create a new tab and add to tab pane control.
        browserTab tab = new browserTab("New Tab");
        setupTabCloseHandler(tab);
        tabPane.getTabs().add(tab);
        tabPane.getSelectionModel().select(tab);
        //Create a buffer so tabs can be dynamically extended on browser.
        if (tabPane.getWidth() < borderPane.getWidth() * 0.8){
            tabPane.setMinWidth(tabPane.getWidth() + tabPane.getTabMaxWidth() + 13);
        }

        loadPage(defaultEngine);
    }


    @FXML
    protected void GoToHomePage() { loadPage(defaultEngine);}

    @FXML
    protected void GoToLoginPage() {navigatePage("/com/zenbrowser/a1/login-view.fxml", "Login");}

    @FXML
    protected void GoToHistoryPage() {navigatePage("/com/zenbrowser/a1/history-view.fxml","History");}


    private browserTab currentTab()    {return (browserTab) tabPane.getSelectionModel().getSelectedItem();}




    private void switchPage(){
        borderPane.setCenter(currentTab().getPage());
    }

    //Load a page into the parent BrowserTab.fxml with parameters of child source fxml file and name of tab.
    public void navigatePage(String pathway, String tabName){
        try {
            //load source fxml file and assign to a borderpane variable.
            FXMLLoader loader = new FXMLLoader(getClass().getResource(pathway));
            BorderPane content = loader.load();
            currentTab().setPage(borderPane, content);

            currentTab().contentController = loader.getController();
            currentTab().setText(tabName);

        } catch (IOException e) {e.printStackTrace();}
    }

    private void loadPage(String urlStr)
    {
        navigatePage("/com/zenbrowser/a1/Home-Page.fxml", "Home");

        try{
            currentTab().getWebEngine().load(urlStr);
            checkController().updateLoading(currentTab().getWebView());

            ChangeListener<Worker.State> listener = new ChangeListener<>() {
                @Override
                public void changed(ObservableValue<? extends Worker.State> obs, Worker.State oldState, Worker.State newState) {
                    if (newState == Worker.State.SUCCEEDED) {
                        WebHistory.Entry entry = currentTab().getRecentHistory();
                        HistoryDAO.insertHistoryRecord(new HistoryRecord(
                                getCurrentUser(),
                                entry.getTitle(),
                                entry.getUrl(),
                                new Timestamp(entry.getLastVisitedDate().getTime())));
                    } else if (newState == Worker.State.FAILED) {
                        System.out.println("Page loading failed!");
                        currentTab().getWebEngine().getLoadWorker().stateProperty().removeListener(this);
                    }
                }
            };

            currentTab().getWebEngine().getLoadWorker().stateProperty().addListener(listener);


        }catch (Exception e){
            System.out.println("Page loading failed!");
        }
    }



    @FXML
    private void GoToPreviousPage() {
        ObservableList<WebHistory.Entry> entryList = currentTab().getHistory().getEntries();
        int currentIndex = currentTab().getHistory().getCurrentIndex();
        Platform.runLater(() -> currentTab().getHistory().go(entryList.size() > 1 && currentIndex > 0 ? -1 : 0));
    }

    @FXML
    private void GotoNextPage() {
        ObservableList<WebHistory.Entry> entryList = currentTab().getHistory().getEntries();
        int currentIndex = currentTab().getHistory().getCurrentIndex();
        Platform.runLater(() -> currentTab().getHistory().go(entryList.size() > 1 && currentIndex < entryList.size() - 1 ? 1 : 0));
    }

    @FXML
    private void GoReloadPage() {
        currentTab().getWebEngine().reload();
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
        return null;
    }
  
    private String formatUrl(String engineName, String query) {
            if (query.startsWith(engineName))
                return query;
            else {
                return engineName + "/search?q=" + query;
            }
    }

    private HomePageController checkController(){
        if (currentTab().contentController instanceof HomePageController){
            HomePageController homeController = (HomePageController) currentTab().contentController;
            return homeController;
        }
        return null;
    }



    @FXML
    private void goButtonPressed() {
        navigatePage("/com/zenbrowser/a1/Home-Page.fxml", "Home");

            promptLabel.setText("");
            String PromptedSearch = URLBox.getText();
            if (PromptedSearch != "") {
                if (PromptedSearch.startsWith("https")){
                    loadPage(PromptedSearch);
                } else if (PromptedSearch.startsWith("www.")){
                    loadPage("https://" + PromptedSearch);
                }
                else{
                    loadPage(formatUrl(defaultEngine, PromptedSearch));
                }
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
            currentTab().getWebEngine().getLoadWorker().stateProperty().addListener((ov, oldState, newState) -> {
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
                    currentTab().setText(currentTab().getWebEngine().getTitle());
                    URLBox.setText(currentTab().getWebEngine().getLocation());
                    currentTab().setGraphic(loadFavicon(url));
                    ReloadButton.setText("↺");
                    EventListener var10000 = new EventListener() {
                        public void handleEvent(Event ev) {
                            System.out.println("You pressed on a link");
                        }
                    };
                    Document doc = currentTab().getWebEngine().getDocument();
                    NodeList el = doc.getElementsByTagName("a");

                    for(int i = 0; i < el.getLength(); ++i) {
                    }
                }
            });

            currentTab().getWebEngine().setCreatePopupHandler((config) -> {
                currentTab().getWebView().setFontScale(0.8);
                if (!this.getChildren().contains(currentTab().getWebView())) {
                    this.getChildren().add(currentTab().getWebView());
                }

                return currentTab().getWebView().getEngine();
            });
            currentTab().getWebEngine().load(url);
        }

        public ImageView loadFavicon(String location) {
            try {
                String faviconUrl;
                if (currentTab().getWebEngine().getTitle().equalsIgnoreCase("Google")) {
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
