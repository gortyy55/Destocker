package tn.CodeCommanders.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import tn.CodeCommanders.Objects.Enchere;
import tn.CodeCommanders.Transaction.Transactions;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class affichecard implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent parent;

    Transactions t = new Transactions();

    private ArrayList<Enchere> getlist(){

                return t.getAll();
    }

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    private List<Enchere> encheres = new ArrayList<>();

    private List<Enchere> getData(){

        List<Enchere> encheres = getlist();
        return encheres;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int column = 0;
        int row = 0;
        encheres.addAll(getData());


        try {
            for(int i=0;i<encheres.size();i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/module.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                modulecontroller modulecontroller = fxmlLoader.getController();
                modulecontroller.setData(encheres.get(i));

                if (column == 4){
                    column=0;
                    row++;
                }

                grid.add(anchorPane, column++, row);
                /*grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);*/


                GridPane.setMargin(anchorPane, new Insets(10));

            }} catch (IOException e) {
            throw new RuntimeException(e);
        }

        }


    public void back(ActionEvent event) {

        try{

            Parent root=FXMLLoader.load(getClass().getResource("/MainWindow.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}

