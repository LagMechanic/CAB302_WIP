package com.zenbrowser.a1.model;

import com.zenbrowser.a1.Controller.ParentController;
import com.zenbrowser.a1.model.BrowserUsage.HistoryRecord;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class browserTab extends Tab {
    private WebView webView;
    private WebEngine webEngine;
    private WebHistory webHistory;
    private Boolean browsing;
    public ParentController contentController;
    private static List<WebHistory> fullHistory = new ArrayList<>();
    private Node page;

    public browserTab(String tabname) {
        super(tabname);
        webView = new WebView();
        webEngine = webView.getEngine();
        webHistory = webEngine.getHistory();
        fullHistory.add(webHistory);
        browsing = false;
        page = new Pane();
    }

    public final void setPage(BorderPane container, Node content) {
        page = content;
        container.centerProperty().set(content);
    }

    public final Node getPage(){    return page;}

    public Boolean getBrowsing() {  return browsing;}

    public void setBrowsing(Boolean browsing) { this.browsing = browsing;}

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

    public static List<WebHistory.Entry> getAllEntries() {
        List<WebHistory.Entry> allEntries = new ArrayList<>();
        for (WebHistory history : fullHistory) {
            allEntries.addAll(history.getEntries());
        }
        return allEntries;
    }

}