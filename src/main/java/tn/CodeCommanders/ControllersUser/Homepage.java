package tn.CodeCommanders.ControllersUser;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Homepage {
    private Stage stage;
    private Scene scene;
    public void bidclient(MouseEvent event) {
        try{

            Parent root= FXMLLoader.load(getClass().getResource("/AfficheEnchereUser.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public void stockclient(MouseEvent event) {
        try{

            Parent root= FXMLLoader.load(getClass().getResource("/StockClient.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void reclamtionclient(MouseEvent event) {
        try{

            Parent root= FXMLLoader.load(getClass().getResource("/AddReclamation.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public void logout(MouseEvent event) {
        try{

            Parent root= FXMLLoader.load(getClass().getResource("/Login.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}



