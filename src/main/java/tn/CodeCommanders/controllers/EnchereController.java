package tn.CodeCommanders.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import tn.CodeCommanders.Transaction.Transactions;
import tn.CodeCommanders.enchere.Enchere;
import tn.CodeCommanders.JDBC.JDBC;

public class EnchereController {

    @FXML
    private static Label err;

    @FXML
    private Label confirm;

    @FXML
    private TextField eproduit;

    @FXML
    private TextField eprixinit;

    @FXML
    private TextField estock;

    @FXML
    private TextField eid;



    @FXML
    void addenchere(ActionEvent event) {

        int id = Integer.parseInt(eid.getText());
        int stock = Integer.parseInt(estock.getText());
        String produit = eproduit.getText();
        double prixinit = Double.parseDouble(eprixinit.getText());

        Enchere e = new Enchere(id,stock,produit,prixinit);
        Transactions t = new Transactions();
        //t.add(e);

        eid.setText("");
        estock.setText("");
        eproduit.setText("");
        eprixinit.setText("");
        confirm.setTextFill(Color.GREEN);
        confirm.setText("Added With Success");

    }
}
