package tn.CodeCommanders.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import tn.CodeCommanders.Transaction.Transactions;
public class DelEnchere {

    @FXML
    private Label result;

    @FXML
    private TextField delid;

    @FXML
    void delenchere(ActionEvent event) {

     String id = delid.getText();
     Transactions t = new Transactions();

        t.Delete(id);

        delid.setText("");

        result.setText("Deleted "+id+" With Success");
        result.setTextFill(Color.GREEN);


    }

}
