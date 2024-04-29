package com.zenbrowser.a1.Controller;

import com.zenbrowser.a1.HelloApplication;
import com.zenbrowser.a1.OliverBrowsingLimitsGUI.MainProfileLimits;
import com.zenbrowser.a1.model.Authentication.Authentication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.*;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField username;
    @FXML
    private TextField password;


    @FXML
    protected void onLoginButtonClick() {

    }

    @FXML
    protected void onRegisterButtonClick() {

    }

    @FXML
    private Button GoToProfileButton;


    @FXML
    protected void onGoToProfileLimits() throws IOException {
        Stage stage = (Stage) GoToProfileButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ProfileLimits.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Limits Browser");
        stage.setScene(scene);
    }
}
