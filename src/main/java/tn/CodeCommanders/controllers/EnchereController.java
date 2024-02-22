package tn.CodeCommanders.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tn.CodeCommanders.Transaction.Transactions;
import tn.CodeCommanders.enchere.Enchere;
import tn.CodeCommanders.JDBC.JDBC;

import java.io.IOException;

public class EnchereController {

    private Stage stage;
    private Scene scene;
    private Parent parent;

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
       // t.add(e);

        eid.setText("");
        estock.setText("");
        eproduit.setText("");
        eprixinit.setText("");
        confirm.setTextFill(Color.GREEN);
        confirm.setText("Added With Success");

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
@FXML
    public void clear(KeyEvent event) {
    confirm.setText("");

    }
}
