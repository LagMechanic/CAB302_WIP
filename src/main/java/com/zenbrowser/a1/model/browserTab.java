package com.zenbrowser.a1.model;


import com.zenbrowser.a1.Controller.MainControllers.TabController;
import com.zenbrowser.a1.model.FocusProfile.ProfileDAO;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class browserTab extends Tab {
    private WebView webView;
    private WebEngine webEngine;
    private WebHistory webHistory;
    private Node page;
    private static List<String> blockedSites;

    // TODO: Do this another way
    private Runnable GoToPageBlocked;
    public void setGoToPageBlocked(Runnable c) {
        GoToPageBlocked = c;
    }

    public browserTab(String tabname) {
        super(tabname);
        webView = new WebView();
        webEngine = webView.getEngine();
        webHistory = webEngine.getHistory();
        page = new Pane();

        blockListener();
    }

    public final void setPage(BorderPane container, Node content) {
        page = content;
        container.centerProperty().set(content);
    }

    public final Node getPage(){    return page;}


    public WebEngine getWebEngine() {
        return webEngine;
    }

    public WebView getWebView() {
        return webView;
    }

    public WebHistory getHistory() {
        return webEngine.getHistory();
    }

    public WebHistory.Entry getRecentHistory() {
        ObservableList<WebHistory.Entry> entries = webHistory.getEntries();
        if (!entries.isEmpty()) {
            return entries.get(entries.size() - 1); // Returns the last entry
        } else {
            System.err.println("WebHistory is empty. No history entries found.");
            return null;
        }
    }

    public void setBlocked(List<String> blockedSites) { this.blockedSites = blockedSites;}

    private void blockListener(){
        webEngine.setOnStatusChanged((WebEvent<String> event) -> {
            String url = event.getData();
            if (isBlocked(url)) {
                Platform.runLater(() -> {
                    // TODO: Do this another way
//                    webEngine.loadContent("Page Blocked");
                    GoToPageBlocked.run();
                });
                // Cancel the load
                event.consume();
            }
        });
    }

    private boolean isBlocked(String url) {
        return blockedSites.stream().anyMatch(ProfileDAO.parseURL(url)::equals);
//        return blockedSites.stream().anyMatch(url::contains);
    }

    /**
    public void setBlocklist(ISiteDAO SiteDAO, IProfileDAO ProfileDAO) {
        webEngine.locationProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("setBlockList: " + newValue);
            Site site = SiteDAO.getSiteByURL(newValue);
            // if site is null, there is no limit set for it
            if (site == null) return;

            // TODO: change "Work" to the current profile
            Profile profile = ProfileDAO.getProfileByNameAndSite("Work", site);
            if (profile == null) return;

            if (profile.isBlocked()) {
                Platform.runLater(() -> {
                    // TODO: Add redirection page
                    webEngine.load("http://example.com");
                });
            }
        });

    }**/
}