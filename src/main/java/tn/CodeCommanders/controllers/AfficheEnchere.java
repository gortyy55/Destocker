package tn.CodeCommanders.controllers;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.CodeCommanders.Transaction.Transactions;
import tn.CodeCommanders.enchere.Enchere;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AfficheEnchere implements Initializable{

    private Stage stage;
    private Scene scene;
    private Parent parent;


    @FXML
    private TableColumn<Enchere, Integer> ide;

    @FXML
    private TableColumn<Enchere, Double> prixinit;

    @FXML
    private TableColumn<Enchere, String> produit;

    @FXML
    private TableColumn<Enchere, Integer> stock;

    @FXML
    private TableView<Enchere> table;


        Transactions t =new Transactions();
    ArrayList<Enchere> str=t.getAll();


        ObservableList<Enchere> list =FXCollections.observableArrayList(str);




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ide.setCellValueFactory(new PropertyValueFactory<Enchere,Integer>("id"));
        prixinit.setCellValueFactory(new PropertyValueFactory<Enchere,Double>("prixint"));
        produit.setCellValueFactory(new PropertyValueFactory<Enchere,String>("produit"));
        stock.setCellValueFactory(new PropertyValueFactory<Enchere,Integer>("stock"));
        table.setItems(list);

    }

    @FXML
    public void retour(ActionEvent event){
        try{

            Parent root= FXMLLoader.load(getClass().getResource("/MainWindow.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
