package com.zenbrowser.a1.Controller;

import com.zenbrowser.a1.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.*;
import java.io.IOException;
import javafx.scene.control.Button;
import com.zenbrowser.a1.Controller.SharedModel;
public class LoginController {
    private SharedModel model;

    public void initModel(SharedModel model) {
        this.model = model;
    }


    @FXML
    private TextField username;
    @FXML
    private TextField password;


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
    protected void onRegisterButtonClick() {

    }

    @FXML
    private Button GoToRegisterPageButton;
    @FXML
    private Button GoToProfileButton;






    private ButtonPressedListener buttonPressedListener;
    public interface ButtonPressedListener {
        void onButtonPressed(String result);
    }
    public void setButtonPressedListener(ButtonPressedListener listener) {
        this.buttonPressedListener = listener;
    }

    @FXML
    private void initialize() {
        GoToRegisterPageButton.setOnAction(event -> {
            // Notify the listener with the result
            if (buttonPressedListener != null) {
                buttonPressedListener.onButtonPressed("/com/zenbrowser/a1/register-view.fxml");
            }
        });
    }

    @FXML
    protected void onGoToProfileLimits() throws IOException {
        Stage stage = (Stage) GoToProfileButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ProfileLimits.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Limits Browser");
        stage.setScene(scene);
    }

}
