package tn.CodeCommanders.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class UserRoleController {

   // @FXML
   // private Button Admin;
    @FXML
    void AdminRole() {
        try {
           // Stage currentStage = (Stage) Admin.getScene().getWindow();
            // Load the Update Facture FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Adminchoice.fxml"));
            Parent root = loader.load();

            // Get the controller of the Update Facture FXML file
            //AdminaffichePanierController adminaffiche = loader.getController();

            // Pass the selected Facture to the Update Facture controller


            // Create a new scene with the loaded FXML file
            Scene scene = new Scene(root);

            // Get the current stage from the existing button
            Stage stage = new Stage(); // Create a new stage for the new scene
            stage.setScene(scene);
            stage.show();
            //currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }

    }

    @FXML
    void UserRole() {
        try {
            // Load the Update Facture FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/afficheCard.fxml"));
            Parent root = loader.load();

            // Get the controller of the Update Facture FXML file
            lotitemController adminaffiche = loader.getController();

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
