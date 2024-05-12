package com.zenbrowser.a1.Controller;

import javafx.event.EventHandler;

import java.net.URL;
import java.util.ResourceBundle;
import com.zenbrowser.a1.model.browserTab;
import javafx.scene.control.Label;
import javafx.scene.web.WebHistory;

public class HistoryController extends ParentController{
    public void initialize() {
        for (int i = 0; i < browserTab.getAllEntries().size(); i++) {
            WebHistory.Entry entry = browserTab.getAllEntries().get(i);
            new Label().setText(entry.toString());

        }
    }

}
