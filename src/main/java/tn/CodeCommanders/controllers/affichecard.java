package tn.CodeCommanders.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import tn.CodeCommanders.enchere.Enchere;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class affichecard implements Initializable {

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

    private List<Enchere> encheres = new ArrayList<>();

    private List<Enchere> getData(){
        List<Enchere> encheres = new ArrayList<>();
        Enchere enchere;
        for (int i=0;i<20;i++){
            enchere = new Enchere();
            enchere.setProduit("kiwi");
            enchere.setPrixint(8.22);
            enchere.setId(122);
            //enchere.setImgSrc("/resources/logo.png");
            encheres.add(enchere);
        }
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
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);


                GridPane.setMargin(anchorPane, new Insets(10));

            }} catch (IOException e) {
            throw new RuntimeException(e);
        }

        }
    }

