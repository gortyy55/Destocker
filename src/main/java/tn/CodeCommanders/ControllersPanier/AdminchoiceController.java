package tn.CodeCommanders.ControllersPanier;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class AdminchoiceController {

    @FXML
    void Panierchoice() {
        try {
            // Load the Update Facture FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdminaffichePanier.fxml"));
            Parent root = loader.load();

            // Get the controller of the Update Facture FXML file


            // Pass the selected Facture to the Update Facture controller


            // Create a new scene with the loaded FXML file
            Scene scene = new Scene(root);

            // Get the current stage from the existing button
            Stage stage = new Stage(); // Create a new stage for the new scene
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }



    @FXML
    void facturechoice() {
        try {
            // Load the Update Facture FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/affichefacture.fxml"));
            Parent root = loader.load();

            // Get the controller of the Update Facture FXML file


            // Pass the selected Facture to the Update Facture controller


            // Create a new scene with the loaded FXML file
            Scene scene = new Scene(root);

            // Get the current stage from the existing button
            Stage stage = new Stage(); // Create a new stage for the new scene
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }
}
