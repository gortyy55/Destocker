package tn.CodeCommanders.ControllersPanier;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tn.CodeCommanders.Panier.Panier;
import tn.CodeCommanders.test.Main;

import javax.swing.text.html.ImageView;
import java.sql.SQLException;
import java.util.Optional;

public class CardController {
    private Stage stage;
    private Scene scene;
    private Parent parent;
    @FXML
    private Label encherelabel;

    @FXML
    private ImageView img;

    @FXML
    private Label prixlabel;
private Panier panier;
    private lotitemController LotitemController;


    public void setLotitemController(lotitemController lotitemController) {
        this.LotitemController = lotitemController;
    }

    public void setData(Panier panier) {
        this.panier = panier;
        encherelabel.setText(panier.getProduit());
        prixlabel.setText(Main.CURRENCY + panier.getPrixTotal());

    }


    @FXML
    private void Delete() throws SQLException {
        if (panier != null) {
            // Create confirmation dialog
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete?");

            // Show dialog and wait for user's response
            Optional<ButtonType> result = alert.showAndWait();

            // Check if user clicked OK
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // User clicked OK, proceed with delete
                tn.CodeCommanders.Transaction.TransactionsPanier t = new tn.CodeCommanders.Transaction.TransactionsPanier();
                System.out.println("id in function delete: " + panier.getId_panier());
                t.Delete(panier.getId_panier());

                // Refresh data (replace this line with your actual refresh logic)
                // For example, if you have a method to update the gridPane, call it here.
                LotitemController.refreshGrid();
            }
        }
    }


    @FXML
    private void update() {
        try {
            // Load the Update FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Update.fxml"));
            Parent root = loader.load();

            // Get the controller of the Update.fxml file


            // Pass the Panier object to the Update controller


            // Create a new stage
            Stage stage = new Stage();
            stage.setTitle("Update");
            stage.setScene(new Scene(root));

            // Show the stage
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading Update.fxml: " + e.getMessage());
        }
    }



}
