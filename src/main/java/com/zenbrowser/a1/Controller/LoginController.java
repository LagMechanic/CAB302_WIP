package com.zenbrowser.a1.Controller;
import com.zenbrowser.a1.BrowserApplication;
import com.zenbrowser.a1.model.Authentication.Authentication;
import com.zenbrowser.a1.model.Authentication.InvalidCredentials;
import com.zenbrowser.a1.model.User.User;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.*;
import java.io.IOException;
import javafx.scene.control.Button;
import com.zenbrowser.a1.Controller.SharedModel;

import static com.zenbrowser.a1.BrowserApplication.currentInstance;

public class LoginController extends ControllerAbstract{
    private SharedModel model;

    public void initModel(SharedModel model) {
        this.model = model;
    }


    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;


    @FXML
    public Button LoginButton;

    @FXML
    private Button RegisterPageButton;

    LoginController(){

    }

    @FXML
    protected void onLoginButtonClick() throws IOException {
        try {
            Authentication.getInstance().login(new User(usernameTextField.getText(), passwordTextField.getText()));
        } catch (InvalidCredentials e) {
            // TODO: Tell the user their username or password is incorrect
            return;
        }

        Stage stage = (Stage) LoginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(BrowserApplication.class.getResource("Home-Page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), BrowserApplication.WIDTH, BrowserApplication.HEIGHT);
        stage.setTitle(BrowserApplication.TITLE);
        stage.setScene(scene);
    }

    @FXML
    private void onRegisterPageButtonClick() throws IOException {

        navigatePage("/com/zenbrowser/a1/Home-Page.fxml", "Register");


    }


}
