package tn.CodeCommanders.Controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import tn.CodeCommanders.Reclamation.Reclamation;
import tn.CodeCommanders.Transactions.Transactions;

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

    private Transactions transactions;

    @FXML
    void initialize() {
        transactions = new Transactions(); // Create an instance of Transactions

        // Initialize TableView columns
        titreColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitre()));
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        produitColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId_produit()).asObject());
        descriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        userColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId_user()).asObject());
        filePathColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCheminFichierJoint()));
        statutColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStatut()));

        // Fetch data and populate TableView
        updateTableView();

        // Call the function to populate ComboBox
        populateComboBox();
    }

    private void updateTableView() {

    }

    private void populateComboBox() {
        ObservableList<String> statutOptions = FXCollections.observableArrayList("En Attente", "En Cours", "Résolue");
        statutComboBox.setItems(statutOptions);
    }

}

