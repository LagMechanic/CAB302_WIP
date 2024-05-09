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
import java.io.IOException;
import javafx.scene.control.Button;

public class LoginController {


    @FXML
    public TextField usernameTXT;
    @FXML
    public TextField passwordTXT;
    @FXML
    public Button LoginButton;
    @FXML
    public Button RegisterButton;

    private ButtonPressedListener buttonPressedListener;
    public interface ButtonPressedListener {
        void onButtonPressed(String destination);
    }

    public void setButtonPressedListener(ButtonPressedListener listener) {
        this.buttonPressedListener = listener;
    }

    @FXML
    protected void onLoginButtonClick() throws IOException {
        try {
            Authentication.getInstance().login(new User(usernameTXT.getText(), passwordTXT.getText()));
        } catch (InvalidCredentials e) {
            // TODO: Tell the user their username or password is incorrect
            return;
        }

        if (buttonPressedListener != null) { //This lets the main browser controller know which page to switch to
            buttonPressedListener.onButtonPressed("Home");
        }
    }

    @FXML
    protected void onRegisterButtonClick() {
        if (buttonPressedListener != null) { //This lets the main browser controller know which page to switch to
            buttonPressedListener.onButtonPressed("Register");
        }
    }






    

}
