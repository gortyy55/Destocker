package tn.CodeCommanders.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import tn.CodeCommanders.Panier.Panier;
import tn.CodeCommanders.Transaction.Transactions;

import java.sql.SQLException;

public class PanierController {



    @FXML
    private TextField numenchere;

    @FXML
    private TextField numlots;

    @FXML
    private TextField price;

    @FXML
    private void addpanier() throws SQLException {

        int id_lots = Integer.parseInt(numlots.getText());
        int id_enchere = Integer.parseInt(numenchere.getText());
        double prixTotal = Double.parseDouble(price.getText());

        Panier p = new Panier(id_lots, id_enchere, prixTotal);
        Transactions t = new Transactions();
        t.add(p);


        numlots.setText("");
        numenchere.setText("");
        price.setText("");
    }
}