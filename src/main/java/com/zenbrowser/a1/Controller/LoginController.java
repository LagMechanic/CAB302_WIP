package com.zenbrowser.a1.Controller;

import com.zenbrowser.a1.BrowserApplication;
import com.zenbrowser.a1.model.Authentication.Authentication;
import com.zenbrowser.a1.model.Authentication.InvalidCredentials;
import com.zenbrowser.a1.model.User.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.*;
import lombok.extern.java.Log;

import java.io.IOException;

public class LoginController {
    @FXML
    public TextField usernameTXT;
    @FXML
    public TextField passwordTXT;
    @FXML
    public Button LoginButton;
    @FXML
    public Button RegisterButton;



    @FXML
    protected void onLoginButtonClick() throws IOException {
        try {
            Authentication.getInstance().login(new User(usernameTXT.getText(), passwordTXT.getText()));
        } catch (InvalidCredentials e) {
            // TODO: Tell the user their username or password is incorrect
            return;
        }

        Stage stage = (Stage) LoginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(BrowserApplication.class.getResource("Home-Page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Limits Browser");
        stage.setScene(scene);
    }

    @FXML
    protected void onRegisterButtonClick() throws IOException {
        Stage stage = (Stage) RegisterButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(BrowserApplication.class.getResource("register-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Limits Browser");
        stage.setScene(scene);
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
