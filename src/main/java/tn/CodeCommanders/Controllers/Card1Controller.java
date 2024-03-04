package tn.CodeCommanders.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tn.CodeCommanders.Panier.Enchere;
import tn.CodeCommanders.Panier.Panier;
import tn.CodeCommanders.Transaction.Transactions;
import tn.CodeCommanders.test.Main;

import javax.swing.text.html.ImageView;
import java.util.Date;

public class Card1Controller {


        private Stage stage;
        private Scene scene;
        private Parent parent;
        @FXML
        private Label encherelabel;


        @FXML
        private ImageView img;

        @FXML
        private Label prixlabel;

        private Enchere enchere;
        private afficheEnchereUser afficheEnchereUser;


        public void setAfficheEnchereUser(afficheEnchereUser afficheEnchereUser) {
            this.afficheEnchereUser = afficheEnchereUser;
        }

        public void setData(Enchere enchere) {
            this.enchere = enchere;
            encherelabel.setText(enchere.getProduit());
            prixlabel.setText(Main.CURRENCY + enchere.getPrixint());

        }


    public void addPanier( ) {
        int id_enchere = enchere.getId();
        int id_acteur = 1;
        double prixTotal = enchere.getPrixint();
        java.sql.Date Date_Enchere = new java.sql.Date(new Date().getTime());


        Panier p = new Panier(id_enchere, id_acteur, prixTotal,Date_Enchere);
        Transactions t = new Transactions();
        t.add(p);



        afficheEnchereUser.itemCount++;
        afficheEnchereUser.updateItemCountText(afficheEnchereUser.itemCount);
        afficheEnchereUser.refreshGrid();
        afficheEnchereUser.handleAddPanierEvent(id_acteur);




    }
}
