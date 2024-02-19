package tn.CodeCommanders.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tn.CodeCommanders.Transaction.Transactions;

import java.io.IOException;

public class UpdateEnchere {

    private Stage stage;
    private Scene scene;
    private Parent parent;

@FXML
    private Label result;
    @FXML
    private TextField id;
    @FXML
    private TextField stock;
    @FXML
    private TextField produit;
    @FXML
    private TextField prixinit;

    @FXML
    void modenchere(ActionEvent event) {



Transactions t = new Transactions();
        t.update(id.getText(),stock.getText(),produit.getText(),prixinit.getText());

        id.setText("");
        stock.setText("");
        produit.setText("");
        prixinit.setText("");
        result.setTextFill(Color.GREEN);
        result.setText("UPDATED !");
    }

    @FXML
    public void retour(ActionEvent event){
        try{

            Parent root= FXMLLoader.load(getClass().getResource("/MainWindow.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }


}
