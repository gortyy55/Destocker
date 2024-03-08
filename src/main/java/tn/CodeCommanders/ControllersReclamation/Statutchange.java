package tn.CodeCommanders.ControllersReclamation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import tn.CodeCommanders.Reclamation.Reclamation;
import tn.CodeCommanders.Transactions.TransactionsReclamation;

public class Statutchange {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> statusComboBox;

    private TransactionsReclamation transactionsReclamation;

    private Reclamation selectedReclamation;

    // Method to initialize the controller
    public void initialize() {
        // Initialize ComboBox with status options
        statusComboBox.setItems(FXCollections.observableArrayList("En attente", "En cours", "Résolu"));
    }

    // Method to set the selected reclamation
    public void setSelectedReclamation(Reclamation reclamation) {
        this.selectedReclamation = reclamation;
    }



    public class SendEmail {
        public static void send(String recipientEmail, String subject, String body) throws IOException {
            Email from = new Email("syrinekamooun23@gmail.com");
            String apiKey = "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";
            SendGrid sg = new SendGrid(apiKey);
            Email to = new Email(recipientEmail);
            Content content = new Content("text/plain", body);
            Mail mail = new Mail(from, subject, to, content);

            Request request = new Request();
            try {
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(mail.build());
                Response response = sg.api(request);
                System.out.println(response.getStatusCode());
                System.out.println(response.getBody());
                System.out.println(response.getHeaders());
            } catch (IOException ex) {
                throw ex;
            }
        }

        // Method to handle the change status button action

    }
    public void changeStatus(ActionEvent event) {
        if (selectedReclamation != null && statusComboBox.getValue() != null) {
            // Set the new status for the selected reclamation
            selectedReclamation.setStatut(statusComboBox.getValue());

            // Update the status in the database
            transactionsReclamation = new TransactionsReclamation();
            transactionsReclamation.update(selectedReclamation);
            // Send email about the status change
            String recipientEmail = "syrine.kamoun@esprit.tn"; // Replace with the method to get the user's email
            String subject = "Reclamation Status Update";
            String body = "Dear User,\n\nYour reclamation status has been updated to: " + selectedReclamation.getStatut();

            try {
                SendEmail.send(recipientEmail, subject, body);
                System.out.println("Email sent successfully.");
            } catch (IOException e) {
                System.out.println("Error sending email: " + e.getMessage());
            }

            // Optionally, you can close the window or perform other actions here
        }
    }
}
