package tn.CodeCommanders.test2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFX extends Application{
    public static void main(String[] args) {
        launch(args);
    }





    @Override
    public void start(Stage stage) {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/MainWindow.fxml"));
        try{
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setTitle("Destocker");
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}