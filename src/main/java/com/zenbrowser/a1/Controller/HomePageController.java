package com.zenbrowser.a1.Controller;

import com.zenbrowser.a1.BrowserApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HomePageController {
    @FXML
    private TextField searchField;

    @FXML
    private ListView<String> historyList;

    @FXML
    private Button searchButton;

    @FXML
    private Button GoToProfileButton;

    private HomePageController.ButtonPressedListener buttonPressedListener;
    public interface ButtonPressedListener {
        void onButtonPressed(String destination);
    }
    public void setButtonPressedListener(HomePageController.ButtonPressedListener listener) {this.buttonPressedListener = listener;}

    @FXML
    private void initialize() {
    }

    @FXML
    private void handleSearch() {
        String query = searchField.getText();
        System.out.println("Performing search for: " + query);
    }

    @FXML
    protected void onGoToProfileLimits() throws IOException {
        if (buttonPressedListener != null) { //This lets the main browser controller know which page to switch to
            buttonPressedListener.onButtonPressed("ProfileLimits");
        }
    }
}
