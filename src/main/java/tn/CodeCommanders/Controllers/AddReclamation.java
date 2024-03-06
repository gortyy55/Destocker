package tn.CodeCommanders.Controllers;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.*;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import tn.CodeCommanders.Reclamation.Reclamation;
import tn.CodeCommanders.Transactions.Transactions;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import java.io.File;

public class AddReclamation {
    public Button pj;
    @FXML
    private Stage stage;

    @FXML
    private TextField DescTextField;

    @FXML
    private Button add;


    @FXML
    private TextField titreTextField;

    @FXML
    private ChoiceBox<String> typeChoice;

    private String cheminFichierJoint;
    private String[] type = {"Produit défectueux","Problème de livraison","Service client insatisfaisant","Article manquant","Autres"};


    @FXML
    void AjouterReclamation(ActionEvent event) {
        String titre = titreTextField.getText();
        String type = typeChoice.getValue();
        String description=DescTextField.getText();

        Reclamation rec = new Reclamation(titre,description,type,cheminFichierJoint);

        if (cheminFichierJoint != null && !cheminFichierJoint.isEmpty()) {
            rec.setCheminFichierJoint(cheminFichierJoint);
        }

        Transactions T = new Transactions();
        T.add(rec);
        cheminFichierJoint = null;
        Alert alert =new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Réclamation établie");
        alert.show();

        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(even -> alert.close());
        delay.play();

    }

    @FXML
    private void handleJoindreFichier(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Joindre un fichier");

        File fichierSelectionne = fileChooser.showOpenDialog(stage);

        if (fichierSelectionne != null) {
            cheminFichierJoint = fichierSelectionne.getAbsolutePath();
            handleFileUpload(fichierSelectionne);
        }
    }
    private void handleFileUpload(File file) {

        String destinationPath = "C:\\Users\\Syrine Kamoun\\Downloads\\reclamation\\src\\main\\java\\tn\\CodeCommanders\\Pieces jointes";
        File destinationFile = new File(destinationPath, file.getName());

        try {
            Files.copy(file.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            String filePathInDatabase = destinationFile.getPath();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        typeChoice.getItems().addAll(type);
        pj.setOnAction(this::handleJoindreFichier);
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    @FXML
    private void retourButtonClicked(ActionEvent event ) {
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


