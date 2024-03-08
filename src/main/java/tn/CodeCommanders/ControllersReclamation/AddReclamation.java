package tn.CodeCommanders.ControllersReclamation;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import tn.CodeCommanders.Reclamation.Reclamation;
import tn.CodeCommanders.Transactions.TransactionsReclamation;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;

import java.io.File;

public class  AddReclamation {
    public Button pj;
    @FXML
    private Stage stage;

    @FXML
    private TextField DescTextField;

    @FXML
    private Button add;

    private Scene scene;

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

        TransactionsReclamation T = new TransactionsReclamation();
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

        String destinationPath = "C:\\Users\\amine\\IdeaProjects\\fullproject\\src\\main\\java\\tn\\CodeCommanders\\pdfs";
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
    public void home1(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/homepage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}


