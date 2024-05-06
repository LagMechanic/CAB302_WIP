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
    public TextField emailTXT;
    @FXML
    public TextField phoneTXT;
    @FXML
    public Button RegisterButton;
    @FXML
    public Button LoginButton;

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

        Stage stage = (Stage) LoginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(BrowserApplication.class.getResource("Home-Page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Limits Browser");
        stage.setScene(scene);
    }

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
    }
}
