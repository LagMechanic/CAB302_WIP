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
    protected void onLoginButtonClick() {
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
}

