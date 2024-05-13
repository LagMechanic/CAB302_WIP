package com.zenbrowser.a1.Controller;

import com.zenbrowser.a1.AuthenticationApplication;
import com.zenbrowser.a1.BrowserApplication;
import com.zenbrowser.a1.model.Authentication.Authentication;
import com.zenbrowser.a1.model.Authentication.UserAlreadyExists;
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
    protected void onRegisterButtonClick()  {
        try {
            Authentication.getInstance().signup(new User(
                    usernameTXT.getText(),
                    passwordField.getText(),
                    firstNameTXT.getText(),
                    lastNameTXT.getText(),
                    emailTextField.getText())
            );

            setCurrentUser(usernameTXT.getText());
            BrowserApplication browserApplication = new BrowserApplication();
            browserApplication.start(new Stage());

            Stage stage = (Stage) registerButton.getScene().getWindow();
            stage.close();
        } catch (UserAlreadyExists e) {
            errorPromptLabel.setText("Username already exists. Select a new username to continue.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    protected void onLoginPageButtonClick() throws IOException {
        Stage stage = (Stage) registerButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(AuthenticationApplication.class.getResource("/com/zenbrowser/a1/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), AuthenticationApplication.WIDTH, AuthenticationApplication.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    private void EnableRegisterButton() {
        boolean validButton = (usernameTXT.getText().isEmpty() || passwordField.getText().isEmpty());
        registerButton.setDisable(validButton);
    }
}
