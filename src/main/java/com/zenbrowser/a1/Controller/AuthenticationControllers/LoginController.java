/**
 The LoginController class manages the login functionality for the ZenBrowser application.
 It extends the ParentController and handles user authentication
 by validating the username and password against stored credentials.
  **/
package com.zenbrowser.a1.Controller.AuthenticationControllers;
import com.zenbrowser.a1.AuthenticationApplication;
import com.zenbrowser.a1.BrowserApplication;
import com.zenbrowser.a1.Controller.ParentController;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

//Main Class
public class LoginController extends ParentController {
    // FXML-injected fields for UI elements
    @FXML private Label ErrorPromptLabel;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordField;
    @FXML private Button LoginButton;

    // Event handler for the login button click
    @FXML
    protected void onLoginButtonClick() throws IOException {

        try {
            // Attempt to login using provided username and password
            Authentication.getInstance().login(new User(usernameTextField.getText(), passwordField.getText()));
        } catch (InvalidCredentials e) {
            // Display error message for invalid credentials
            ErrorPromptLabel.setText("Username and password do not match our database records. Please try again.");
            return;
        } catch (Authentication.MissingUser e) {
            // Display error message for missing user
            ErrorPromptLabel.setText("Username does not exist. Input your correct username or create a new account.");
            return;
        }

        // Set the current user and start the browser application
        setCurrentUser(usernameTextField.getText());
        new BrowserApplication().start(new Stage());
        // Close the current login window
        ((Stage) LoginButton.getScene().getWindow()).close();
    }

    // Event handler for the register page button click
    @FXML
    private void onRegisterPageButtonClick() throws IOException {
        // Load the registration page and set it as the current scene
        Stage stage = (Stage) LoginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(AuthenticationApplication.class.getResource("/com/zenbrowser/a1/register-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), AuthenticationApplication.WIDTH, AuthenticationApplication.HEIGHT);
        stage.setScene(scene);


    }

    // Enable or disable the login button based on text field input
    @FXML
    private void EnableLoginButton() {
        boolean validButton = (usernameTextField.getText().isEmpty() || passwordField.getText().isEmpty());
        LoginButton.setDisable(validButton);
    }

    // Event handler to check login on pressing the Enter key
    @FXML
    public void CheckLogin(KeyEvent key) throws IOException {
        if (key.getCode() == KeyCode.ENTER && !LoginButton.isDisabled()){
            onLoginButtonClick();
        }
    }
}
