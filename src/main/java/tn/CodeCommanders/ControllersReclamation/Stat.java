package tn.CodeCommanders.ControllersReclamation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tn.CodeCommanders.Transactions.TransactionsReclamation;

import java.io.IOException;

public class Stat {

    @FXML
    private Label nonSatisfaitsLabel;

    @FXML
    private Label satisfaitsLabel;

    @FXML
    private Button toAffich;

    public void initialize() {
        countAvis();
    }

    private void countAvis() {
        TransactionsReclamation transactionsReclamation = new TransactionsReclamation();
        int satisfaitCount = transactionsReclamation.countSatisfaitAvis();
        int nonSatisfaitCount = transactionsReclamation.countNonSatisfaitAvis();

        satisfaitsLabel.setText(String.valueOf(satisfaitCount));
        nonSatisfaitsLabel.setText(String.valueOf(nonSatisfaitCount));
    }


    @FXML
    void retourButtonClicked(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Les réclamations");
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }



}



