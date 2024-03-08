package tn.CodeCommanders.ControllersReclamation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.CodeCommanders.Reclamation.Reclamation;
import tn.CodeCommanders.Transactions.TransactionsReclamation;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.List;

public class admin {

    @FXML
    private TableView<Reclamation> reclamationTable;

    @FXML
    private ComboBox<String> statutComboBox;
    @FXML
    private TableColumn<Reclamation, String> titreColumn;

    @FXML
    private TableColumn<Reclamation, String> typeColumn;

    @FXML
    private TableColumn<Reclamation, Integer> produitColumn;

    @FXML
    private TableColumn<Reclamation, String> descriptionColumn;

    @FXML
    private TableColumn<Reclamation, Integer> userColumn;

    @FXML
    private TableColumn<Reclamation, String> filePathColumn;

    @FXML
    private TableColumn<Reclamation, String> statutColumn;

    private TransactionsReclamation transactionsReclamation;
    private Scene scene;
    private Stage stage;

    @FXML
    private Button toAffich;

    @FXML
    void retour(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Stat.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Statistiques");
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void initialize() {

        transactionsReclamation = new TransactionsReclamation();
        List<Reclamation> reclamations = transactionsReclamation.getAll();


        reclamationTable.getItems().addAll(reclamations);
    }





    public void changeStatut(ActionEvent event) {
        Reclamation selectedReclamation = reclamationTable.getSelectionModel().getSelectedItem();
        if (selectedReclamation != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/statutchange.fxml"));
                Parent root = loader.load();
                Statutchange controller = loader.getController();
                controller.setSelectedReclamation(selectedReclamation); // Pass selected reclamation
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Change Status");
                stage.show();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public void home(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Dashboard.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    }


