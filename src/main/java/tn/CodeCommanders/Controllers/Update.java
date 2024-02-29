package tn.CodeCommanders.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import tn.CodeCommanders.Panier.Panier;
import tn.CodeCommanders.Transaction.Transactions;

import java.sql.Date;
import java.sql.SQLException;

public class Update {

    @FXML
    private TextField idpanier;

    @FXML
    private TextField idenchereU;

    @FXML
    private TextField idacteurU;
    @FXML
    private DatePicker dateU;
    @FXML
    private TextField prixU;
    private  Panier panier;


    public int idPanierToUpdate;
    public void setIdPanierToUpdate(int idPanier) {
        this.idPanierToUpdate = idPanier;
        System.out.println("hihaaa." + idPanier );
    }
    @FXML
    void updatePanier() throws SQLException {
        try {
            int id_Panier = Integer.parseInt(idpanier.getText());
            int id_acteur = Integer.parseInt(idacteurU.getText());
            int id_enchere = Integer.parseInt(idenchereU.getText());
            double prixTotal = Double.parseDouble(prixU.getText());
            Date Date_Enchere = java.sql.Date.valueOf(dateU.getValue());

            // Perform the update in the database
            Transactions t = new Transactions();
            t.update(id_Panier, id_enchere, id_acteur, prixTotal, Date_Enchere);

            System.out.println("Panier updated successfully."); // Debugging statement

            // Clear UI fields
            idacteurU.setText("");
            idenchereU.setText("");
            idpanier.setText("");
            prixU.setText("");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format for numeric fields."); // Handle invalid input format
        }
    }
    public void setPanier(Panier panier) {
        this.panier = panier;
        idpanier.setText(String.valueOf(panier.getId_panier()));
        idenchereU.setText(String.valueOf(panier.getId_enchere()));
        idacteurU.setText(String.valueOf(panier.getId_acteur()));
        prixU.setText(String.valueOf(panier.getPrixTotal()));
    }


}
