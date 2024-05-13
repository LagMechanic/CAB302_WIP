package com.zenbrowser.a1.model;

import com.zenbrowser.a1.Controller.ParentController;
import javafx.collections.ObservableList;
import javafx.scene.control.Tab;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

import java.util.ArrayList;
import java.util.List;

public class browserTab extends Tab {
    private WebView webView;
    private WebEngine webEngine;
    private WebHistory webHistory;
    private Boolean browsing;
    public ParentController contentController;
    private static List<WebHistory> fullHistory = new ArrayList<>();

    public browserTab(String tabname) {
        super(tabname);
        webView = new WebView();
        webEngine = webView.getEngine();
        webHistory = webEngine.getHistory();
        fullHistory.add(webHistory);
        browsing = false;
    }

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