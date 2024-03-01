package tn.CodeCommanders.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import tn.CodeCommanders.Panier.Facture;
import tn.CodeCommanders.Transaction.Transactions;

import java.sql.Date;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class FactureController {

    @FXML
    private TextField addressB;

    @FXML
    private TextField city;

    @FXML
    private TextField cityB;

    @FXML
    private TextField countryB;

    @FXML
    private TextField creditnum;

    @FXML
    private DatePicker expdate;

    @FXML
    private TextField nameCard;

    @FXML
    private TextField seccode;

    @FXML
    private TextField stateB;

    @FXML
    private TextField zipcodeB;

    public int idPanierToUpdate;
    public int idact;

    public void setIdPanierToUpdate(int idPanier) {
        this.idPanierToUpdate = idPanier;
        System.out.println("hihaaa." + idPanier);
    }

    public void setIdact(int idActeur) {
        this.idact = idActeur;
        System.out.println("hooooo." + idActeur);
    }

    @FXML
    void paybilling() {
        try {
            String name_card = nameCard.getText();
            int ccn = Integer.parseInt(creditnum.getText());
            Date exp_date = java.sql.Date.valueOf(expdate.getValue());
            int security_code = Integer.parseInt(seccode.getText());
            String address = addressB.getText();
            String city = cityB.getText();
            String state = stateB.getText();
            int zip_code = Integer.parseInt(zipcodeB.getText());
            String country = countryB.getText();

            Facture f = new Facture(idact, idPanierToUpdate, name_card, ccn, exp_date, security_code, address, city, state, zip_code, country);
            Transactions t = new Transactions();
            t.addfacture(f);

            // Show payment success popup
            showSuccessPopup("Payment Successful");

            // Reset text fields
            resetTextFields();
        } catch (NumberFormatException e) {
            showErrorPopup("Invalid Input", "Please enter valid numeric values for credit card number, security code, and zip code.");
        }
    }

    private void showSuccessPopup(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Payment Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorPopup(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void resetTextFields() {
        nameCard.clear();
        creditnum.clear();
        expdate.setValue(null);
        seccode.clear();
        addressB.clear();
        cityB.clear();
        stateB.clear();
        zipcodeB.clear();
        countryB.clear();
    }
}
