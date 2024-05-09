package com.zenbrowser.a1.Controller;

import com.zenbrowser.a1.BrowserApplication;
import com.zenbrowser.a1.model.Authentication.Authentication;
import com.zenbrowser.a1.model.Authentication.InvalidCredentials;
import com.zenbrowser.a1.model.Authentication.UserAlreadyExists;
import com.zenbrowser.a1.model.User.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {
    @FXML
    public TextField usernameTXT;
    @FXML
    public TextField passwordTXT;
    @FXML
    public TextField emailTXT;
    @FXML
    public TextField phoneTXT;
    @FXML
    public Button RegisterButton;
    @FXML
    public Button LoginButton;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    private RegisterController.ButtonPressedListener buttonPressedListener;
    public interface ButtonPressedListener {
        void onButtonPressed(String destination);
    }
    public void setButtonPressedListener(RegisterController.ButtonPressedListener listener) {this.buttonPressedListener = listener;}


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

        if (buttonPressedListener != null) { //This lets the main browser controller know which page to switch to
            buttonPressedListener.onButtonPressed("Home");
        }
    }

    @FXML
    protected void onLoginButtonClick() throws IOException {
        if (buttonPressedListener != null) { //This lets the main browser controller know which page to switch to
            buttonPressedListener.onButtonPressed("Login");
        }
    }




}
