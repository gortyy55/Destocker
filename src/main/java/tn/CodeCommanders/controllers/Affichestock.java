package tn.CodeCommanders.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.CodeCommanders.Model.UpdateStockController;
import tn.CodeCommanders.Transaction.Transactions;
import tn.CodeCommanders.stock.Stock;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Affichestock implements Initializable {
    @FXML
    public ListView<Stock> listview;


    private Stage stage;
    private Scene scene;
    private Parent parent;
@FXML
    public void back(ActionEvent event) {
        try{

            Parent root= FXMLLoader.load(getClass().getResource("/Stock.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }




    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Transactions t = new Transactions();
        ArrayList<Stock> items;
        items = t.getAll();

        listview.getItems().addAll(items);

    }



    public void delete(MouseEvent event) {
       // int selectedID = listview.getSelectionModel().getSelectedIndex();
       // listview.getItems().remove(selectedID);
        Stock selectedItem = listview.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {

            listview.getItems().remove(selectedItem);

            Transactions t = new Transactions();
            t.delete(selectedItem);
        }

    }

    public void update(ActionEvent event) {

        Stock selectedItem = listview.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {

            openUpdateWindow(selectedItem);
        }
    }

    private void openUpdateWindow(Stock selectedItem) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateStock.fxml"));
            Parent root = loader.load();
            UpdateStockController controller = loader.getController();
            controller.initData(selectedItem);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setOnHidden(e -> refreshListView());
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void refreshListView() {
        listview.getItems().clear();

        Transactions t = new Transactions();
        ArrayList<Stock> items = t.getAll();

        listview.getItems().addAll(items);
    }
    }

