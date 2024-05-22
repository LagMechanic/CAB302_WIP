package com.zenbrowser.a1.model;


import com.zenbrowser.a1.Controller.ParentController;
import com.zenbrowser.a1.model.BrowserUsage.HistoryRecord;
import com.zenbrowser.a1.model.BrowserUsage.IHistoryRecordDAO;
import com.zenbrowser.a1.model.FocusProfile.IProfileDAO;
import com.zenbrowser.a1.model.FocusProfile.Profile;
import com.zenbrowser.a1.model.Website.ISiteDAO;
import com.zenbrowser.a1.model.Website.Site;
import com.zenbrowser.a1.model.Website.SiteDAO;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;


public class browserTab extends Tab {
    private WebView webView;
    private WebEngine webEngine;
    private WebHistory webHistory;
    private Node page;

    public browserTab(String tabname) {
        super(tabname);
        webView = new WebView();
        webEngine = webView.getEngine();
        webHistory = webEngine.getHistory();
        page = new Pane();
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

    }
}