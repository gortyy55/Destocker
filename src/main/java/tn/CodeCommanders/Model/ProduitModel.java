package tn.CodeCommanders.Model;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import tn.CodeCommanders.JDBC.JDBC;
import tn.CodeCommanders.Transaction.Transactions;
/*import tn.CodeCommanders.controllers.CardDeletedEvent;*/
import tn.CodeCommanders.stock.Stock;

import java.sql.Connection;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import static com.sun.javafx.event.EventUtil.fireEvent;

public class ProduitModel {
    @FXML
    public Button update;
    @FXML
    public Button delete;
    @FXML
    private Label mail;

    @FXML
    private Label produitname;

    @FXML
    private Label quantite;
    private Stock stock;
    private Connection cnx;

    public void ServiceStock() {
        cnx = JDBC.getInstance().getCnx();
    }

    public void initialize() {

    }
    public void setData(Stock stock) {
        Transactions t = new Transactions();
        ArrayList<Stock> stocks = t.getAll();

        if (!stocks.isEmpty()) {
            this.stock = stock;
            quantite.setText(String.valueOf(stock.getQuantite()));
            produitname.setText(String.valueOf(stock.getProduitname()));
            mail.setText(String.valueOf(stock.getMail()));
        }
    }

   /* public void delete(ActionEvent actionEvent) {
        if (stock != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Delete Stock");
            alert.setContentText("Are you sure you want to delete this Stock?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Transactions t = new Transactions();
                boolean deleted = t.delete(stock);
                if (deleted) {
                    fireEvent(new CardDeletedEvent(stock)); // Fire event indicating card deletion
                    showAlert("Success", "Stock deleted successfully.");
                    // Clear UI fields or update UI as needed
                    clearFields();
                } else {
                    showAlert("Error", "Failed to delete stock.");
                }
            }
        } else {
            showAlert("No Stock Selected", "Please select a stock to delete.");
        }

    }

    private void clearFields() {
        produitname.clear();
        quantite.clear();
        mail.clear();
    }


    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
*/


}





