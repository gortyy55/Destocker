package tn.CodeCommanders.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import tn.CodeCommanders.Panier.Panier;
import tn.CodeCommanders.Transaction.Transactions;

import java.sql.SQLException;

public class Update {

    @FXML
    private TextField numenchere;

    @FXML
    private TextField numlots;

    @FXML
    private TextField idPanier;

    @FXML
    private TextField price;
    private  Panier panier;
    @FXML
    void updatePanier() throws SQLException {
        int id_Panier = Integer.parseInt(idPanier.getText());
        int id_lots = Integer.parseInt(numlots.getText());
        int id_enchere = Integer.parseInt(numenchere.getText());
        double prixTotal = Double.parseDouble(price.getText());

        // Retrieve the existing Panier by its ID
        Transactions t = new Transactions();
        Panier existingPanier = t.getPanierById(id_Panier);

        if (existingPanier != null) {
            System.out.println("Found existing Panier: " + existingPanier); // Debugging statement

            // Update the Panier properties
            existingPanier.setId_lots(id_lots);
            existingPanier.setId_enchere(id_enchere);
            existingPanier.setPrixTotal(prixTotal);

            // Perform the update in the database
            t.update(existingPanier);
            System.out.println("Panier updated successfully."); // Debugging statement
        } else {
            System.out.println("Panier with ID " + id_Panier + " not found."); // Debugging statement
        }

        numlots.setText("");
        numenchere.setText("");
        idPanier.setText(""); // Clear the ID field
        price.setText("");
    }
    public void setPanier(Panier panier) {
        this.panier = panier;
        idPanier.setText(String.valueOf(panier.getId_panier()));
        numlots.setText(String.valueOf(panier.getId_enchere()));
        numenchere.setText(String.valueOf(panier.getId_lots()));
        price.setText(String.valueOf(panier.getPrixTotal()));
    }
}
