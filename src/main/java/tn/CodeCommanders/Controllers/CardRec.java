package tn.CodeCommanders.Controllers;
import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import tn.CodeCommanders.Reclamation.*;
import tn.CodeCommanders.JDBC.*;
import tn.CodeCommanders.Transactions.*;

public class CardRec {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button delete;
    @FXML
    private Button edit;
    @FXML
    private VBox vbox;
    @FXML
    private Label TypeRec;
    @FXML
    private Label titreRec;
    @FXML
    private Label DescRec;
    private Reclamation reclamation;
    private Connection cnx;

    public void ServiceReclamation() {cnx = JDBC.getInstance().getCnx();}

    @FXML
    void initialize() {}

    public void setData(Reclamation reclamation){
        //Transactions t = new Transactions();
        this.reclamation = reclamation;
        TypeRec.setText(reclamation.getType());
        titreRec.setText(reclamation.getTitre());
        DescRec.setText(reclamation.getDescription());


    }
    public Reclamation getReclamation() {
        return this.reclamation;
    }
void setReclamation(Reclamation reclamation){
        this.reclamation=reclamation;
    }
    @FXML
    void effacerReclamation(ActionEvent actionEvent) {
            int id = reclamation.getId_reclamation();
        System.out.println(id);
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setContentText("Votre id réclamation "+id);
        alert1.show();
            Transactions T = new Transactions();
            if (T.delete(id)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Votre réclamation a été supprimée avec succès");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Erreur lors de la suppression de la réclamation");
                alert.show();
            }
}
    @FXML
    void updateRec (ActionEvent actionEvent) {
        Reclamation rec=this.reclamation;
        Transactions T = new Transactions();
        T.update(rec);
    }


}
