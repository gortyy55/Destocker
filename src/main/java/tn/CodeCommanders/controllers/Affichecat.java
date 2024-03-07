package tn.CodeCommanders.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import tn.CodeCommanders.Model.UpdateCategory;
import tn.CodeCommanders.Model.UpdateStockController;
import tn.CodeCommanders.Transaction.Transactions;
import tn.CodeCommanders.stock.Category;
import tn.CodeCommanders.stock.Stock;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Affichecat implements Initializable {
    @FXML
    public ListView<Category> catlist;
    private Stage stage;
    private Scene scene;
    private Parent parent;



    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Transactions t = new Transactions();
        ArrayList<Category> items;
        items = t.getAllC();
        catlist.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Category> call(ListView<Category> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(Category item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {

                            setText("Id:" + item.getIdc() + "| category:" + item.getCategoryName() );
                        }
                    }
                };
            }
        });

        catlist.getItems().addAll(items);

    }
    public void catdel(ActionEvent event) {
        Category selectedItem = catlist.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {

            catlist.getItems().remove(selectedItem);

            Transactions t = new Transactions();
            t.deletecat(selectedItem);
        }
    }

    public void catup(ActionEvent event) {

        Category selectedItem = catlist.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {

            openUpdateWindow(selectedItem);
        }
    }

    private void openUpdateWindow(Category selectedItem) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateCat.fxml"));
            Parent root = loader.load();
            UpdateCategory controller = loader.getController();
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
        catlist.getItems().clear();

        Transactions t = new Transactions();
        ArrayList<Category> items = t.getAllC();

        catlist.getItems().addAll(items);
    }

    public void back(ActionEvent event) {
        try{

            Parent root= FXMLLoader.load(getClass().getResource("/categoryface.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
