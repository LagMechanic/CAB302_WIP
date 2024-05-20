package com.zenbrowser.a1.model;

import com.zenbrowser.a1.Controller.ParentController;
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
}