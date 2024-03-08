package tn.CodeCommanders.ControllersPanier;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import tn.CodeCommanders.Panier.Facture;

import java.sql.Date;
import java.sql.SQLException;

public class AdminaddfactureController {

    @FXML
    private TextField ccnadd;

    @FXML
    private TextField cityadd;

    @FXML
    private TextField countryAdmin;

    @FXML
    private DatePicker expdate;

    @FXML
    private TextField idPanier;

    @FXML
    private TextField idUser;

    @FXML
    private TextField idfac;

    @FXML
    private TextField nameC;

    @FXML
    private TextField seccode;

    @FXML
    private TextField stateadd;

    @FXML
    private TextField streeradd;

    @FXML
    private TextField zipcode;

    @FXML
    void AdminAddfacture() throws SQLException {

        int id_acteur = Integer.parseInt(idUser.getText());
        int id_panier = Integer.parseInt(idPanier.getText());
        String name_card = nameC.getText();
        int ccn = Integer.parseInt(ccnadd.getText());
        Date exp_date = java.sql.Date.valueOf(expdate.getValue());
        int security_code = Integer.parseInt(seccode.getText());
        String address = streeradd.getText();
        String city = cityadd.getText();
        String state = stateadd.getText();
        int zip_code = Integer.parseInt(zipcode.getText());
        String country = countryAdmin.getText();

        Facture f = new Facture(id_acteur, id_panier, name_card, ccn, exp_date, security_code, address, city, state, zip_code, country);
        tn.CodeCommanders.Transaction.TransactionsPanier t = new tn.CodeCommanders.Transaction.TransactionsPanier();
        t.addfactureAdmin(f);




    }
}
