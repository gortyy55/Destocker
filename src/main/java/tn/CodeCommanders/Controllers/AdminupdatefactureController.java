package tn.CodeCommanders.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.CodeCommanders.Panier.Facture;
import tn.CodeCommanders.Transaction.Transactions;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

public class AdminupdatefactureController {

    @FXML
    private TextField UPCountry;

    @FXML
    private DatePicker UPDate;

    @FXML
    private TextField UPPanier;

    @FXML
    private TextField UPState;

    @FXML
    private TextField UPaddress;

    @FXML
    private TextField UPcity;

    @FXML
    private TextField UPnameC;

    @FXML
    private TextField UPsecCode;

    @FXML
    private TextField UPzip;


    @FXML
    private TextField upCCN;

    @FXML
    private TextField upIDfac;

    @FXML
    private TextField upUserFact;


    public void setFacture(Facture facture) {
        if (facture != null) {
            // Set values from the Facture object to the corresponding fields
            upIDfac.setText(String.valueOf(facture.getId_facture()));
            UPPanier.setText(String.valueOf(facture.getId_panier()));
            upUserFact.setText(String.valueOf(facture.getId_acteur()));
            UPnameC.setText(facture.getName_card());
            upCCN.setText(String.valueOf(facture.getCcn()));

            UPsecCode.setText(String.valueOf(facture.getSecurity_code()));
            UPaddress.setText(facture.getAddress());
            UPcity.setText(facture.getCity());
            UPState.setText(facture.getState());
            UPzip.setText(String.valueOf(facture.getZip_code()));
            UPCountry.setText(facture.getCountry());
        } else {
            // Handle null facture, if needed
        }
    }

    @FXML
    void updatefacture() {
        Facture facture = new Facture();
        facture.setId_facture(Integer.parseInt(upIDfac.getText()));
        facture.setId_panier(Integer.parseInt(UPPanier.getText()));
        facture.setId_acteur(Integer.parseInt(upUserFact.getText()));
        facture.setName_card(UPnameC.getText());
        facture.setCcn(Integer.parseInt(upCCN.getText()));
        facture.setSecurity_code(Integer.parseInt(UPsecCode.getText()));
        facture.setAddress(UPaddress.getText());
        facture.setCity(UPcity.getText());
        facture.setState(UPState.getText());
        facture.setZip_code(Integer.parseInt(UPzip.getText()));
        facture.setCountry(UPCountry.getText());
        // Convert LocalDate to Date
        Transactions t = new Transactions();

        // Call updateFacture method from Transactions
        t.updateFacture(Integer.parseInt(upIDfac.getText()), facture);
        showPopup("Update Successful");

        // Go back to affichefacture.fxml
        goBackToAfficheFacture();
    }

    void showPopup(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Update Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    void goBackToAfficheFacture() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/affichefacture.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) UPDate.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception if needed
        }
    }


}
