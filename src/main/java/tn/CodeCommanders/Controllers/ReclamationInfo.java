package tn.CodeCommanders.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import tn.CodeCommanders.Reclamation.Reclamation;
import tn.CodeCommanders.Transactions.Transactions;


public class ReclamationInfo implements Initializable {
    @FXML
    private Stage stage;
    @FXML
    private Button switchToAddReclamation;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void switchToAdd(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddReclamation.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}



