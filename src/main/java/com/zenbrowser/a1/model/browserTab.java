package com.zenbrowser.a1.model;

import javafx.scene.control.Tab;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

public class browserTab extends Tab {
    private WebView webView;
    private WebEngine webEngine;

    public browserTab(String tabname){
        super(tabname);
        webView = new WebView();
        webEngine = webView.getEngine();
    }

    public WebEngine getWebEngine() {
        return webEngine;
    }

    public WebView getWebView() {
        return webView;
    }

    public WebHistory getHistory() {
        return webEngine.getHistory();
    }
}