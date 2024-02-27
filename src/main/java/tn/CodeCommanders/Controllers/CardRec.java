package tn.CodeCommanders.Controllers;

import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tn.CodeCommanders.JDBC.JDBC;
import tn.CodeCommanders.Reclamation.Reclamation;
import tn.CodeCommanders.Transactions.Transactions;

public class CardRec {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView delete;

    @FXML
    private ImageView edit;

    @FXML
    private HBox hboxCard;

    @FXML
    private VBox vbox;
    @FXML
    private Label typeTextField;

    private Reclamation reclamation;
    private Connection cnx;
    public void ServiceReclamation(){cnx = JDBC.getInstance().getCnx();
    }

    public void setData(Reclamation reclamation){
        Transactions t = new Transactions();
        ArrayList<Reclamation> reclamations = t.getAll();

        this.reclamation = reclamation;
        typeTextField.setText(reclamation.getTitre());
    }
}

