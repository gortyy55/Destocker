package tn.CodeCommanders.controllers;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
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

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import tn.CodeCommanders.Transaction.Transactions;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
    private int remainingSeconds = 5 * 60;

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

    private void sendConfirmationEmail(String recipientEmail) {
        // Replace these values with your own email configuration
        String host = "smtp-relay.brevo.com";
        String port = "587";
        String username = "karimgharbi66@gmail.com";
        String password = "pLPQ9CWmtzFyB0qr";

        // Create properties for the email session
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");


        // Create a Session object
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Set the sender and recipient addresses
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));

            // Set the email subject and content
            message.setSubject("Congratulations chrit el lot");
            message.setText("Congratulations chrit el lot");

            // Send the email
            Transport.send(message);

            System.out.println("purchase succesful");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Failed purchase: " + e.getMessage());
        }
    }
}