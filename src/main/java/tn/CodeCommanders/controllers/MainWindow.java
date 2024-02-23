package tn.CodeCommanders.controllers;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindow{
    private Stage stage;
private Scene scene;
private Parent parent;

@FXML




    public void switchToEnchere(ActionEvent event){
        try{

            Parent root=FXMLLoader.load(getClass().getResource("/Enchere.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void switchToDelEnchere(ActionEvent event){
        try{

            Parent root=FXMLLoader.load(getClass().getResource("/DelEnchere.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public void switchToModEnchere(ActionEvent event){
        try{

            Parent root=FXMLLoader.load(getClass().getResource("/UpdateEnchere.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }



    public void switchToAffEnchere(ActionEvent event){
        try{

            Parent root=FXMLLoader.load(getClass().getResource("/Affichecard.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

}
