package com.zenbrowser.a1.Controller;

import com.zenbrowser.a1.BrowserApplication;
import com.zenbrowser.a1.model.Authentication.Authentication;
import com.zenbrowser.a1.model.Authentication.InvalidCredentials;
import com.zenbrowser.a1.model.Authentication.UserAlreadyExists;
import com.zenbrowser.a1.model.User.User;
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
    public TextField usernameTXT;
    @FXML
    public TextField passwordTXT;
    @FXML
    public Button RegisterButton;

    @FXML
    protected void onRegisterButtonClick() throws IOException {
        try {
            Authentication.getInstance().signup(new User(
                    usernameTXT.getText(),
                    passwordTXT.getText()
            ));
        } catch (UserAlreadyExists e) {
            // TODO: Tell there is already a user with that username
            return;
        }

        BrowserApplication.currentController.navigatePage("/com/zenbrowser/a1/register-view.fxml", "Register");
    }


    @FXML
    protected void onLoginPageButtonClick() throws IOException {
        BrowserApplication.currentController.navigatePage("/com/zenbrowser/a1/login-view.fxml", "Login");
    }
}
