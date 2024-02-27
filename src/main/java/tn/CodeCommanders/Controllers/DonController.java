package tn.CodeCommanders.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import tn.CodeCommanders.Don.Don;
import tn.CodeCommanders.Transaction.Transactions;

import java.sql.SQLException;

    public class DonController {
        @FXML
    private TextField id;


    @FXML
    private TextField title;

    @FXML
    private TextField description;

    @FXML
    private TextField association;

    @FXML
    private void add() throws SQLException {
        String titleText = title.getText();
        String descriptionText = description.getText();
        String associationText = association.getText();


        Don p = new Don(titleText,descriptionText,associationText);
        Transactions t = new Transactions();
        t.add(p);


    }
}