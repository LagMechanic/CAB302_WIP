package com.zenbrowser.a1.Controller;

import com.zenbrowser.a1.BrowserApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.*;

import java.io.IOException;

public class LoginController extends ControllerAbstract {

    @FXML
    private TextField username;
    @FXML
    private TextField password;


    @FXML
    protected void onLoginButtonClick()  {

    }

    @FXML
    protected void onRegisterButtonClick() {

    }

    @FXML
    private Button GoToProfileButton;

    @FXML
    private Button GoToHomePageButton;


    @FXML
    protected void onGoToProfileLimits() throws IOException {
        Stage stage = (Stage) GoToProfileButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(BrowserApplication.class.getResource("ProfileLimits.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Limits Browser");
        stage.setScene(scene);
    }

    @FXML
    protected void onGoToHomePage() throws IOException {
        Stage stage = (Stage) GoToHomePageButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(BrowserApplication.class.getResource("Home-Page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Home Page");
        stage.setScene(scene);
    }
}
