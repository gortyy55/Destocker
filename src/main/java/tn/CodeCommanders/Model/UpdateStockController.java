package tn.CodeCommanders.Model;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import tn.CodeCommanders.Transaction.Transactions;
import tn.CodeCommanders.stock.Stock;

import java.awt.*;

public class UpdateStockController {
    @FXML
    public TextField nomproduit;
    @FXML
    public TextField quantite;
    @FXML
    public TextField mail;


    private Stock selectedItem;

    public void initData(Stock selectedItem) {
        this.selectedItem = selectedItem;

        nomproduit.setText(selectedItem.getProduitname());
        quantite.setText(String.valueOf(selectedItem.getQuantite()));
        mail.setText(selectedItem.getMail());

    }

    public void save(ActionEvent event) {

        selectedItem.setProduitname(nomproduit.getText());
        selectedItem.setQuantite(Integer.parseInt(quantite.getText()));
        selectedItem.setMail(mail.getText());


        Transactions t = new Transactions();
        t.update(selectedItem);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    }

