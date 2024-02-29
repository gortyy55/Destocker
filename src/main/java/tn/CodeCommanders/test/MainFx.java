package tn.CodeCommanders.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;

import java.awt.*;
import java.io.IOException;

public class MainFx extends Application {
    double x,y = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/Stock.fxml"));
        try{
            primaryStage.initStyle(StageStyle.UNDECORATED);
            Parent root = loader.load();
            Image image =new Image("logo2.0.png");
            primaryStage.getIcons().add(image);
            Scene scene = new Scene(root);
            root.setOnMousePressed(event -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });

            primaryStage.setTitle("DeStocker");
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    }

