package tn.CodeCommanders.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import tn.CodeCommanders.Objects.Enchere;
import tn.CodeCommanders.test.Main;

import java.io.IOException;

public class modulecontroller {

    private Stage stage;
    private Scene scene;
    private Parent parent;

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

    public void showlot(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Affichelot.fxml"));
            Parent root = loader.load();

            // Access the controller if needed
            Affichelot afficherlot = loader.getController();
            afficherlot.setId(this.enchere.getId());

            scene = new Scene(root);
            stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading affichelot.fxml: " + e.getMessage());
            e.printStackTrace();
        }

    }

}
