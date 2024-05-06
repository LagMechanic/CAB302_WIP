package com.zenbrowser.a1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Browser_App extends Application {

    public static final String TITLE = "ZenBrowser";
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public void start(Stage stage) throws Exception {
        Parent root = (Parent)FXMLLoader.load(getClass().getResource("Browser-Main.fxml"));
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setScene(scene);
        stage.setTitle("Zen Browser");
        stage.getIcons().add(new Image("file:Resources/icons/icon256.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}