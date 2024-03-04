package tn.CodeCommanders.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tn.CodeCommanders.Panier.Facture;
import tn.CodeCommanders.Panier.Panier;
import tn.CodeCommanders.Transaction.Transactions;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Transactions t = new Transactions();
        ArrayList<Panier> str = t.getAll();
        ObservableList< Panier> list = FXCollections.observableArrayList(str);

        idPanier.setCellValueFactory(new PropertyValueFactory<>("id_panier"));
        idenchere.setCellValueFactory(new PropertyValueFactory<>("id_enchere"));
        idUser.setCellValueFactory(new PropertyValueFactory<>("id_acteur"));
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

        Table.setItems(list);
}
    private void deletePanier(Panier panier) throws SQLException {
        Transactions t = new Transactions();
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
        Transactions t = new Transactions();
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





}

