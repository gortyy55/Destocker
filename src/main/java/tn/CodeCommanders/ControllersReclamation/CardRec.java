package tn.CodeCommanders.ControllersReclamation;
import javafx.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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

    public void ServiceReclamation() {
        cnx = JDBC.getInstance().getCnx();
    }

    @FXML
    void initialize() {
    }

    public void setData(Reclamation reclamation) {
        //Transactions t = new Transactions();
        this.reclamation = reclamation;
        TypeRec.setText(reclamation.getType());
        titreRec.setText(reclamation.getTitre());
        DescRec.setText(reclamation.getDescription());
    }

    public Reclamation getReclamation() {
        return this.reclamation;
    }

    void setReclamation(Reclamation reclamation) {
        this.reclamation = reclamation;
    }

    @FXML
    void effacerReclamation(ActionEvent actionEvent) {
        int id = reclamation.getId_reclamation();
        TransactionsReclamation T = new TransactionsReclamation();
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
        void updateRec(ActionEvent actionEvent) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Modifier la réclamation");
            dialog.setHeaderText(null);
            dialog.setContentText("Modifier la description:");
            // n'ffichi l boite o nestana
            Optional<String> result = dialog.showAndWait();

            // Vérifier
            result.ifPresent(nouveauDescription -> {
                // Mettez à jour reclamation mte3y
                reclamation.setDescription(nouveauDescription);

                // Mettez à jour fl interface utilisateur
                DescRec.setText(nouveauDescription);
            });
        }
    }
