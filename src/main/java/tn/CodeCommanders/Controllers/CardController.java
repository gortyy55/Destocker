package tn.CodeCommanders.Controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tn.CodeCommanders.Panier.Panier;
import tn.CodeCommanders.Transaction.Transactions;
import tn.CodeCommanders.test.Main;
import tn.CodeCommanders.test.MainFX;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CardController {

    @FXML
    private Label encherelabel;

    @FXML
    private ImageView img;

    @FXML
    private Label prixlabel;
    private Panier panier;


    public void setData(Panier panier) {
        this.panier = panier;
        encherelabel.setText(String.valueOf(panier.getId_enchere()));
        prixlabel.setText(Main.CURRENCY + panier.getPrixTotal());

    }


}
