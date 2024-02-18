package tn.CodeCommanders.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import tn.CodeCommanders.Transaction.Transactions;
public class UpdateEnchere {
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


}
