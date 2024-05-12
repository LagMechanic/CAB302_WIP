package com.zenbrowser.a1.Controller;

import com.zenbrowser.a1.BrowserApplication;
import com.zenbrowser.a1.model.Authentication.Authentication;
import com.zenbrowser.a1.model.Authentication.UserAlreadyExists;
import com.zenbrowser.a1.model.User.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;


public class RegisterController extends ParentController {
    @FXML
    private Label errorPromptLabel;
    @FXML
    private TextField firstNameTXT;
    @FXML
    private TextField lastNameTXT;
    @FXML
    private TextField usernameTXT;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField emailTextField;
    @FXML
    private Button registerButton;



    @FXML
    protected void onRegisterButtonClick() throws IOException {
        try {
            Authentication.getInstance().signup(new User(
                    usernameTXT.getText(),
                    passwordField.getText(),
                    firstNameTXT.getText(),
                    lastNameTXT.getText(),
                    emailTextField.getText())
            );

            setCurrentUser(usernameTXT.getText());
            BrowserApplication.currentController.navigatePage("/com/zenbrowser/a1/Home-Page.fxml", "Home");
        } catch (UserAlreadyExists e) {
            errorPromptLabel.setText("Username already exists. Select a new username to continue.");
        }
    }


    @FXML
    protected void onLoginPageButtonClick() throws IOException {
        BrowserApplication.currentController.navigatePage("/com/zenbrowser/a1/login-view.fxml", "Login");
    }

    @FXML
    private void EnableRegisterButton() throws IOException{
        Boolean validButton = (usernameTXT.getText().isEmpty() || passwordField.getText().isEmpty());
        registerButton.setDisable(validButton);
    }




}
