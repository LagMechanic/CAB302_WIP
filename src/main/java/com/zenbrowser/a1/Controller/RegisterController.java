package com.zenbrowser.a1.Controller;

import com.zenbrowser.a1.BrowserApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController extends ControllerAbstract{

    @FXML
    private TextField username;
    @FXML
    private TextField password;

    @FXML
    private Button AccountRegisterButton;

    @FXML
    private Button LoginPageButton;


    @FXML
    protected void onLoginPageButtonClick() throws IOException {
        Stage stage = (Stage) LoginPageButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(BrowserApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), BrowserApplication.WIDTH, BrowserApplication.HEIGHT);
        stage.setTitle(BrowserApplication.TITLE);
        stage.setScene(scene);
    }

    public void onRegisterButtonClick() throws IOException {

    }
}
