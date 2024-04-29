package com.zenbrowser.a1.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class HomePageController {
    @FXML
    private TextField searchField;

    @FXML
    private ListView<String> historyList;

    @FXML
    private Button searchButton;

    // Add any other variables or methods you need here

    @FXML
    private void initialize() {
    }

    @FXML
    private void handleSearch() {
        // Add functionality to handle search action
        String query = searchField.getText();
        // Perform search operation with the query
        System.out.println("Performing search for: " + query);
    }

    // Add more event handler methods as needed
}
