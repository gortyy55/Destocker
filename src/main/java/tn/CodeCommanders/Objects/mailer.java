package tn.CodeCommanders.Objects;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class mailer extends Application {

    private static final String SMTP_HOST = "smtp.example.com"; // SMTP server host
    private static final String SMTP_PORT = "587"; // SMTP server port
    private static final String EMAIL_USERNAME = "your_email@example.com"; // Your email address
    private static final String EMAIL_PASSWORD = "your_email_password"; // Your email password

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        TextField toField = new TextField();
        toField.setPromptText("To:");

        TextField subjectField = new TextField();
        subjectField.setPromptText("Subject:");

        TextField messageField = new TextField();
        messageField.setPromptText("Message:");

        Button sendButton = new Button("Send");
        sendButton.setOnAction(event -> {
            String to = toField.getText();
            String subject = subjectField.getText();
            String message = messageField.getText();

            sendEmail(to, subject, message);
        });

        VBox root = new VBox(10, toField, subjectField, messageField, sendButton);
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Email Sender");
        primaryStage.show();
    }

    private void sendEmail(String to, String subject, String message) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
            }
        });

        try {
            Message emailMessage = new MimeMessage(session);
            emailMessage.setFrom(new InternetAddress(EMAIL_USERNAME));
            emailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            emailMessage.setSubject(subject);
            emailMessage.setText(message);

            Transport.send(emailMessage);

            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }
}