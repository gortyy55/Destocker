package tn.CodeCommanders.ControllersReclamation;
import tn.CodeCommanders.Reclamation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import tn.CodeCommanders.Transactions.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class avisClient {

    @FXML
    private TextArea coment;

    @FXML
    private Button envoyer;

    @FXML
    private RadioButton non;

    @FXML
    private RadioButton oui;

    @FXML
    private ChoiceBox<String> choice;

    @FXML
    private Button toAffich;

    private List<String> getTitresReclamations() {
        List<String> titres = new ArrayList<>();
        List<Reclamation> reclamations = TransactionsReclamation.getAll();
        for (Reclamation reclamation : reclamations) {
            titres.add(reclamation.getTitre());
        }
        return titres;
    }
    @FXML
    private void initialize() {
        List<String> titresReclamations = getTitresReclamations();
        choice.getItems().addAll(titresReclamations);
    }

    @FXML
    private void envoyer() {
        String comment = coment.getText();
        boolean satisfaction = oui.isSelected();
        String titreReclamation = choice.getValue();


            TransactionsReclamation t = new TransactionsReclamation();
            int idReclamation = t.getIdReclamationByTitre(titreReclamation);
            t.enregistrerAvis(idReclamation, satisfaction,comment);

            // Effacer les champs ou effectuer d'autres actions nécessaires après l'envoi de l'avis
            coment.clear();
            oui.setSelected(false);
            non.setSelected(false);
            choice.setValue(null);
        }



    @FXML
    private void ajouterAvis() {
        String titreReclamationSelectionne = choice.getValue();}
    @FXML
    void retourButtonClicked(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ReclamationInfo.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("ReclamationInfos");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

