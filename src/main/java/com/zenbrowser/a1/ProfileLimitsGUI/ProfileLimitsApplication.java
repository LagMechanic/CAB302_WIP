package com.zenbrowser.a1.ProfileLimitsGUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProfileLimitsApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ProfileLimitsApplication.class.getResource("ProfileLimits.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Limits Browser");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }




}
