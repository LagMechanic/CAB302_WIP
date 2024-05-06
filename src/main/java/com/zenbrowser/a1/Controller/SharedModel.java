package com.zenbrowser.a1.Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SharedModel {
    private final StringProperty currentPage = new SimpleStringProperty();

    public String getCurrentPage() {
        return currentPage.get();
    }

    public void setCurrentPage(String value) {
        currentPage.set(value);
    }

    public StringProperty currentPageProperty() {
        return currentPage;
    }
}
