package tn.CodeCommanders.controllers;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.text.DecimalFormat;
import java.util.Timer;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

import tn.CodeCommanders.Transaction.Transactions;

public class Bidcontroller implements Initializable {


    @FXML
    private ScrollPane listprices;

    @FXML
    private Label timerLabel; // Ajout du Label pour afficher le minuteur

    @FXML
    private TextField PrixEntered;

    @FXML
    private Label resultat;


@FXML
    public int ide;




    private double currentPrice; // Prix initial en euros

    private ArrayList<Double> priceHistory = new ArrayList<>(); // Liste pour stocker l'historique des prix

    private Timeline timeline;
    private Timeline timeline2;
   private Duration auctionDuration = Duration.ofMinutes(5);

public void initlist(){
    Transactions t = new Transactions();
    // Initialiser la liste des prix avec le premier prix;
    currentPrice = t.prixactuel(ide);
    priceHistory.add(currentPrice);
    updatePriceHistory();

}

    public void setId(int id) {
        this.ide=id;
        initlist();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updatePriceHistory();
        Timeline timeline = new Timeline(
                new KeyFrame(javafx.util.Duration.seconds(3), event -> actuprix())
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }


    private void actuprix(){
        Transactions t = new Transactions();
    if (currentPrice!=t.prixactuel(ide)){
        priceHistory.add(t.prixactuel(ide));
        currentPrice=t.prixactuel(ide);
        updatePriceHistory();
    }
    }



    private void updatePriceHistory() {
        // Créer une boîte verticale pour contenir les étiquettes de prix
        VBox vBox = new VBox();
        for (Double price : priceHistory) {
            Label label = new Label("Prix actuel : " + price + " DT");
            vBox.getChildren().add(label);
        }
        listprices.setContent(vBox); // Définir la boîte verticale comme contenu du ScrollPane
    }
    @FXML
    public void buy(ActionEvent event) {
        Transactions t = new Transactions();
        double enteredPrice = Double.parseDouble(PrixEntered.getText());
        if (enteredPrice > t.prixactuel(ide)) {
            t.updateEnchere(this.ide,Double.valueOf(PrixEntered.getText()));
            resultat.setText("Enchère enregistrée à " + PrixEntered.getText());
        } else {
            resultat.setText("Prix inférieur");
        }
    }
}