package com.zenbrowser.a1.Controller;
import com.zenbrowser.a1.BrowserApplication;
import com.zenbrowser.a1.model.Authentication.Authentication;
import com.zenbrowser.a1.model.Authentication.InvalidCredentials;
import com.zenbrowser.a1.model.User.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
        BrowserApplication.currentController.navigatePage("/com/zenbrowser/a1/Home-Page.fxml", "Home");
    }

    @FXML
    private void onRegisterPageButtonClick() throws IOException {
        BrowserApplication.currentController.navigatePage("/com/zenbrowser/a1/register-view.fxml", "Register");
    }

    @FXML
    private void EnableLoginButton() throws IOException{
        Boolean validButton = (usernameTextField.getText().isEmpty() || passwordField.getText().isEmpty());
        LoginButton.setDisable(validButton);
    }
}
