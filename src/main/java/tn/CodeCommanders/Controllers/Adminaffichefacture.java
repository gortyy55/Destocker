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
import tn.CodeCommanders.Transaction.Transactions;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Adminaffichefacture implements Initializable {
    @FXML
    private TableColumn<Facture, Integer> idFature;

    @FXML
    private TableColumn<Facture, Integer> idPanier;



    @FXML
    private  TableColumn<Facture, String> Fname;


    @FXML
    private TableColumn<Facture, Void> ActionsColumn;

    @FXML
    private TableView<Facture> Table;















    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Transactions t = new Transactions();
        ArrayList<Facture> str = t.getAllF();
        ObservableList<Facture> list = FXCollections.observableArrayList(str);

        idFature.setCellValueFactory(new PropertyValueFactory<>("id_facture"));
        idPanier.setCellValueFactory(new PropertyValueFactory<>("id_panier"));
        //idUser.setCellValueFactory(new PropertyValueFactory<>("id_acteur"));
        Fname.setCellValueFactory(new PropertyValueFactory<>("acteurFirstName"));

        // Add custom column for actions
        ActionsColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");
            private final Button updateButton = new Button("Update");

            {
                deleteButton.setOnAction(event -> {
                    Facture facture = getTableView().getItems().get(getIndex());
                    // Call your delete method here
                    deleteFacture(facture);
                });

                updateButton.setOnAction(event -> {
                    Facture facture = getTableView().getItems().get(getIndex());
                    // Call your update method here
                    updateFacture(facture);
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

    private void deleteFacture(Facture facture) {
        Transactions t = new Transactions();
        int id_facture = facture.getId_facture();

        t.DeleteF(id_facture);
        System.out.println("Facture with ID " + id_facture + " deleted successfully.");
        refreshTable();
    }

    private void updateFacture(Facture facture) {
        System.out.println("Update facture with ID: " + facture.getId_facture());

        try {
            // Load the Update Facture FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/updateFacture.fxml"));
            Parent root = loader.load();

            // Get the controller of the Update Facture FXML file
            AdminupdatefactureController updateFactureController = loader.getController();

            // Pass the selected Facture to the Update Facture controller
            updateFactureController.setFacture(facture);

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
        ArrayList<Facture> updatedData = t.getAllF();
        ObservableList<Facture> updatedList = FXCollections.observableArrayList(updatedData);

        // Clear existing data and add the updated data
        Table.getItems().clear();
        Table.setItems(updatedList);
    }

    @FXML
    void adminajouteer() {
        try {
            // Load the Update Facture FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Adminaddfacture.fxml"));
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
