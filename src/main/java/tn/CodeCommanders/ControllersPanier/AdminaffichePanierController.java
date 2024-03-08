package tn.CodeCommanders.ControllersPanier;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tn.CodeCommanders.Panier.Panier;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class AdminaffichePanierController implements Initializable {

    @FXML
    private TableColumn<Panier, Void> Action;

    @FXML
    private TableColumn<Panier, Double> Price;

    @FXML
    private TableView<Panier> Table;

    @FXML
    private TableColumn<Panier, Date> dateEnchere;

    @FXML
    private TableColumn<Panier, Integer> idPanier;

    @FXML
    private TableColumn<Panier, Integer> idUser;

    @FXML
    private TableColumn<Panier, Integer> idenchere;

    @FXML
    private TextField searchPanier;
    private Scene scene;
    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tn.CodeCommanders.Transaction.TransactionsPanier t = new tn.CodeCommanders.Transaction.TransactionsPanier();
        ArrayList<Panier> str = t.getAll();
        ObservableList<Panier> list = FXCollections.observableArrayList(str);

        idPanier.setCellValueFactory(new PropertyValueFactory<>("id_panier"));
        idenchere.setCellValueFactory(new PropertyValueFactory<>("produit"));
        idUser.setCellValueFactory(new PropertyValueFactory<>("acteurFirstName"));
        Price.setCellValueFactory(new PropertyValueFactory<>("prixTotal"));
        dateEnchere.setCellValueFactory(new PropertyValueFactory<>("Date_Enchere"));

        // Add custom column for actions
        Action.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");
            private final Button updateButton = new Button("Update");

            {
                deleteButton.setOnAction(event -> {
                    Panier panier = getTableView().getItems().get(getIndex());
                    // Call your delete method here
                    try {
                        deletePanier(panier);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });

                updateButton.setOnAction(event -> {
                    Panier panier = getTableView().getItems().get(getIndex());
                    // Call your update method here
                    updatePanier(panier);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox buttons = new HBox(deleteButton, updateButton);
                    buttons.setSpacing(5);
                    setGraphic(buttons);
                }
            }
        });

        // Add a listener to the searchPanier TextField
        searchPanier.textProperty().addListener((observable, oldValue, newValue) -> {
            filterPanierList(newValue);
        });

        Table.setItems(list);
    }

    private void filterPanierList(String keyword) {
        tn.CodeCommanders.Transaction.TransactionsPanier t = new tn.CodeCommanders.Transaction.TransactionsPanier();
        ArrayList<Panier> allPaniers = t.getAll();
        ObservableList<Panier> filteredList = FXCollections.observableArrayList();

        for (Panier panier : allPaniers) {
            if (String.valueOf(panier.getActeurFirstName()).contains(keyword) ||  String.valueOf(panier.getProduit()).contains(keyword)) {
                filteredList.add(panier);
            }
        }

        Table.setItems(filteredList);
    }

    private void deletePanier(Panier panier) throws SQLException {
        tn.CodeCommanders.Transaction.TransactionsPanier t = new tn.CodeCommanders.Transaction.TransactionsPanier();
        int id_panier = panier.getId_panier();

        t.Delete(id_panier);
        System.out.println("Panier with ID " + id_panier + " deleted successfully.");
        refreshTable();
    }

    private void updatePanier(Panier panier) {
        System.out.println("Update Panier with ID: " + panier.getId_panier());

        try {
            // Load the Update Facture FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Update.fxml"));
            Parent root = loader.load();

            // Get the controller of the Update Facture FXML file
            Update updateP = loader.getController();

            // Pass the selected Facture to the Update Facture controller
            updateP.setPanier(panier);

            // Create a new scene with the loaded FXML file
            Scene scene = new Scene(root);

            // Get the current stage from the existing button
            Stage stage = new Stage(); // Create a new stage for the new scene
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        }
    }

    private void refreshTable() {
        tn.CodeCommanders.Transaction.TransactionsPanier t = new tn.CodeCommanders.Transaction.TransactionsPanier();
        ArrayList<Panier> updatedData = t.getAll();
        ObservableList<Panier> updatedList = FXCollections.observableArrayList(updatedData);

        // Clear existing data and add the updated data
        Table.getItems().clear();
        Table.setItems(updatedList);
    }

    @FXML
    void AdminajoutPanier() {
        try {
            // Load the Update Facture FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Panier.fxml"));
            Parent root = loader.load();

            // Get the controller of the Update Facture FXML file


            // Pass the selected Facture to the Update Facture controller


            // Create a new scene with the loaded FXML file
            Scene scene = new Scene(root);

            // Get the current stage from the existing button
            Stage stage = new Stage(); // Create a new stage for the new scene
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately
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