package tn.CodeCommanders.ControllersEnchere;

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
import tn.CodeCommanders.Enchere.Produit;
import tn.CodeCommanders.Transactions.TransactionsEnchere;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Affichelot implements Initializable{

    private Stage stage;
    private Scene scene;
    private Parent parent;

    public int idp;

public void setId(int id){
    this.idp = id;
    initializeTableView();
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

    @FXML
    private TableColumn<Produit, Integer> ide;

    @FXML
    private TableColumn<Produit, String> name;

    @FXML
    private TableColumn<Produit, Integer> quantite;

    @FXML
    private TableView<Produit> table;









    private void initializeTableView() {
        TransactionsEnchere t = new TransactionsEnchere();
        ArrayList<Produit> str = t.getlot(idp);
        ObservableList<Produit> list = FXCollections.observableArrayList(str);

        ide.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        table.setItems(list);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
