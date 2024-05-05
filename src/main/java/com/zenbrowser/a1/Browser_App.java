package com.zenbrowser.a1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Browser_App extends Application {
    public void start(Stage stage) throws Exception {
        Parent root = (Parent)FXMLLoader.load(getClass().getResource("Browser-Main.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Zen Browser");
        stage.getIcons().add(new Image("file:Resources/icons/icon256.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}