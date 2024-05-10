package com.zenbrowser.a1;

import com.zenbrowser.a1.Controller.BrowserMain;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BrowserApplication extends Application {
    public static final String TITLE = "ZenBrowser";

    public static BrowserMain currentController;
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 800;

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(BrowserApplication.class.getResource("browserTab.fxml"));
        Parent root = loader.load();
        currentController = loader.getController();

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();

        currentController.navigatePage("/com/zenbrowser/a1/Home-Page.fxml", "Home");
    }

    public static void main(String[] args) {
        launch(args);
    }
}