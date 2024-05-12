package com.zenbrowser.a1.model;

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

    private static List<WebHistory> fullHistory = new ArrayList<>();

    public browserTab(String tabname) {

        super(tabname);
        webView = new WebView();
        webEngine = webView.getEngine();
        webHistory = webEngine.getHistory();
        fullHistory.add(webHistory);
    }

    public WebEngine getWebEngine() {
        return webEngine;
    }

    public WebView getWebView() {
        return webView;
    }

    public WebHistory getHistory() {
        return webHistory;
    }

    public static List<WebHistory.Entry> getAllEntries() {
        List<WebHistory.Entry> allEntries = new ArrayList<>();
        for (WebHistory history : fullHistory) {
            allEntries.addAll(history.getEntries());
        }
        return allEntries;
    }
}