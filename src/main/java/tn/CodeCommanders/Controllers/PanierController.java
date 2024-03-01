package tn.CodeCommanders.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import tn.CodeCommanders.Panier.Panier;
import tn.CodeCommanders.Transaction.Transactions;

import java.sql.SQLException;
import java.util.Date;

public class PanierController {



    @FXML
    private TextField numenchere;

    @FXML
    private TextField idacteur;

    @FXML
    private TextField price;
    @FXML
    private DatePicker datepick;

    @FXML
    private void addpanier() throws SQLException {

        int id_enchere = Integer.parseInt(numenchere.getText());
        int id_acteur = Integer.parseInt(idacteur.getText());
        double prixTotal = Double.parseDouble(price.getText());
        Date Date_Enchere = java.sql.Date.valueOf(datepick.getValue());


        Panier p = new Panier(id_enchere, id_acteur, prixTotal,Date_Enchere);
        Transactions t = new Transactions();
        t.add(p);


        idacteur.setText("");
        numenchere.setText("");
        price.setText("");
        datepick.setValue(null);
    }

    public void paybilling() {
    }
}