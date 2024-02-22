package tn.CodeCommanders.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tn.CodeCommanders.enchere.Enchere;
import tn.CodeCommanders.test.Main;

public class modulecontroller {

    @FXML
    private Label idlabel;

    @FXML
    private ImageView img;

    @FXML
    private Label namelabel;

    @FXML
    private Label prixlabel;

    private Enchere enchere;

    public void setData(Enchere enchere){
        this.enchere=enchere;
        idlabel.setText(String.valueOf(enchere.getId()));
        prixlabel.setText(enchere.getPrixint() + Main.CURRENCY);
        namelabel.setText(enchere.getProduit());
        //Image image = new Image(getClass().getResourceAsStream(enchere.getImgSrc()));
        //img.setImage(image);



    }

}
