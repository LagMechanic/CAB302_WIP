package com.zenbrowser.a1.Controller;
import com.zenbrowser.a1.AuthenticationApplication;
import com.zenbrowser.a1.BrowserApplication;
import com.zenbrowser.a1.model.Authentication.Authentication;
import com.zenbrowser.a1.model.Authentication.InvalidCredentials;
import com.zenbrowser.a1.model.User.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController extends ParentController {
    @FXML
    private Label ErrorPromptLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button LoginButton;


    @FXML
    protected void onLoginButtonClick() throws IOException {
        try {
            Authentication.getInstance().login(new User(usernameTextField.getText(), passwordField.getText()));
        } catch (InvalidCredentials e) {
            ErrorPromptLabel.setText("Username and password do not match our database records. Please try again.");
            return;
        } catch (Authentication.MissingUser e) {
            ErrorPromptLabel.setText("Username does not exist. Input your correct username or create a new account.");
            return;
        }

        setCurrentUser(usernameTextField.getText());
        BrowserApplication browserApplication = new BrowserApplication();
        browserApplication.start(new Stage());
        Stage stage = (Stage) LoginButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onRegisterPageButtonClick() throws IOException {
        Stage stage = (Stage) LoginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(AuthenticationApplication.class.getResource("/com/zenbrowser/a1/register-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), AuthenticationApplication.WIDTH, AuthenticationApplication.HEIGHT);
        stage.setScene(scene);


    }

    @FXML
    private void EnableLoginButton() {
        boolean validButton = (usernameTextField.getText().isEmpty() || passwordField.getText().isEmpty());
        LoginButton.setDisable(validButton);
    }
}
