package com.zenbrowser.a1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AuthenticationApplication extends Application {
    public static final String TITLE = "ZenBrowser";
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 800;

    @Override
    public void start(Stage stage) throws IOException {
        String fxmlFileName = "login-view.fxml"; // Default FXML file
        if (!getParameters().getRaw().isEmpty()) {
            // Check if command-line arguments are provided
            fxmlFileName = getParameters().getRaw().get(0); // Get the first command-line argument
        }

        FXMLLoader loader = new FXMLLoader(BrowserApplication.class.getResource(fxmlFileName));
        Scene scene = new Scene(loader.load(), WIDTH, HEIGHT);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}