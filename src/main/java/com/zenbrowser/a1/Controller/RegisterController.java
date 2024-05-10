package com.zenbrowser.a1.Controller;

import com.zenbrowser.a1.BrowserApplication;
import com.zenbrowser.a1.model.Authentication.Authentication;
import com.zenbrowser.a1.model.Authentication.UserAlreadyExists;
import com.zenbrowser.a1.model.User.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;


public class RegisterController extends ParentController {
    @FXML
    private TextField firstNameTXT;
    @FXML
    private TextField lastNameTXT;

    @FXML
    private TextField usernameTXT;
    @FXML
    private TextField passwordTXT;
    @FXML
    private TextField emailTextField;

    @FXML
    private Button RegisterButton;


    @FXML
    protected void onRegisterButtonClick() throws IOException {
        try {
            Authentication.getInstance().signup(new User(
                    usernameTXT.getText(),
                    passwordTXT.getText(),
                    firstNameTXT.getText(),
                    lastNameTXT.getText(),
                    emailTextField.getText())
            );

            BrowserApplication.currentController.navigatePage("/com/zenbrowser/a1/register-view.fxml", "Register");
        } catch (UserAlreadyExists e) {
            // TODO: Tell there is already a user with that username
            return;
        }
    }


    @FXML
    protected void onLoginPageButtonClick() throws IOException {
        BrowserApplication.currentController.navigatePage("/com/zenbrowser/a1/login-view.fxml", "Login");
    }
}
