package com.zenbrowser.a1.Controller;

import com.zenbrowser.a1.BrowserApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.*;

import java.io.IOException;

public class LoginController extends ControllerAbstract {

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField emailTextField;
    @FXML
    private TextField phoneTextField;


    @FXML
    protected void onLoginButtonClick()  {
    }

    /**private void selectUser(User user) {
        usernameTextField.setText(contact.getFirstName());
        passwordTextField.setText(contact.getLastName());
        emailTextField.setText(contact.getEmail());
        phoneTextField.setText(contact.getPhone());


     private void checkUser(User user) {

     }
    }**/


    @FXML
    private Button AccountLoginButton;

    @FXML
    private Button RegisterPageButton;


    @FXML
    protected void onGoToProfileLimits() throws IOException {
        Stage stage = (Stage) AccountLoginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(BrowserApplication.class.getResource("Home-Page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Limits Browser");
        stage.setScene(scene);
    }

    @FXML
    protected void onRegisterPageButtonClick() throws IOException {
        Stage stage = (Stage) RegisterPageButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(BrowserApplication.class.getResource("register-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Home Page");
        stage.setScene(scene);
    }
}
