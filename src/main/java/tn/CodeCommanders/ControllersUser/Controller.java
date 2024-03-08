package tn.CodeCommanders.ControllersUser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tn.CodeCommanders.User.User;
import tn.CodeCommanders.Service.ServiceUser;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class Controller {

    @FXML
    public Label Captchaa;
    @FXML
    public TextField CaptchaField;
    @FXML
    public Label errorCaptcha;
    ServiceUser serviceUser = new ServiceUser();

    @FXML
    private Button newCaptcha;
    @FXML
    private TextField nameLabel;
    @FXML
    private TextField lastnameLabel;
    @FXML
    private TextField emailLabel;
    @FXML
    private PasswordField passLabel;
    @FXML
    private PasswordField confirmPassLabel;
    @FXML
    private TextField addLabel;
    @FXML
    private TextField phoneLabel;
    @FXML
    private RadioButton rdBtnClient;
    @FXML
    private RadioButton rdBtnAssociation;

    @FXML
    private Text errorMessage;

    String phoneNumberRegex = "\\d{8}";
    String textRequiredRegex = ".{3,}";
    String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
    String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";

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
            message.setSubject("Registration confirmation");
            message.setText("Thank you for registering!");

            // Send the email
            Transport.send(message);

            System.out.println("Registration confirmation email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Failed to send registration confirmation email: " + e.getMessage());
        }
    }

    @FXML
    void addUser(ActionEvent event) throws SQLException {
        if (!verifyEmail() || !verifyPhoneNumber() || !verifyConfirmPassword() || !verifyPassword() || !verifyAddressRequired() || !verifyFirstnameRequired() || !verifyLastnameRequired()) {
            System.out.println("Correct these errors");
            return;
        }

        String firstname = nameLabel.getText();
        String lastname = lastnameLabel.getText();
        String email = emailLabel.getText();
        String password = passLabel.getText();
        String address = addLabel.getText();
        int telephone = Integer.parseInt(phoneLabel.getText());
        String role;

        if (rdBtnClient.isSelected())
            role = "Client";
        else if (rdBtnAssociation.isSelected())
            role = "Association";
        else
            role = "Entreprise";

        User user = new User(email, password, role, firstname, lastname, address, telephone);
        ServiceUser Ser = new ServiceUser();

        if (Ser.isEmailUnique(email)) {
            Ser.ajouter(user);
            resetRegisterForm();
        } else {
            emailLabel.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            errorMessage.setFill(javafx.scene.paint.Color.RED);
            errorMessage.setText("Email Address already exists!");
        }

        sendConfirmationEmail(email);
    }

    private void resetRegisterForm() {
        nameLabel.setText("");
        lastnameLabel.setText("");
        emailLabel.setText("");
        passLabel.setText("");
        addLabel.setText("");
        phoneLabel.setText("");
        confirmPassLabel.setText("");
    }

    // Remaining methods for field verification...

    public boolean verifyPhoneNumber() {
        if (phoneLabel.getText().matches(phoneNumberRegex)) {
            phoneLabel.setStyle("-fx-border-color: green; -fx-border-width: 2px;");
            return true;
        } else {
            phoneLabel.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            return false;
        }
    }

    public boolean verifyFirstnameRequired() {
        if (nameLabel.getText().matches(textRequiredRegex)) {
            nameLabel.setStyle("-fx-border-color: green; -fx-border-width: 2px;");
            return true;
        } else {
            nameLabel.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            return false;
        }
    }

    public boolean verifyLastnameRequired() {
        if (lastnameLabel.getText().matches(textRequiredRegex)) {
            lastnameLabel.setStyle("-fx-border-color: green; -fx-border-width: 2px;");
            return true;
        } else {
            lastnameLabel.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            return false;
        }
    }

    public boolean verifyAddressRequired() {
        if (addLabel.getText().matches(textRequiredRegex)) {
            addLabel.setStyle("-fx-border-color: green; -fx-border-width: 2px;");
            return true;
        } else {
            addLabel.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            return false;
        }
    }

    public boolean verifyPassword() {
        if (passLabel.getText().matches(passwordRegex)) {
            passLabel.setStyle("-fx-border-color: green; -fx-border-width: 2px;");
            errorMessage.setText("");
            return true;
        } else {
            passLabel.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            errorMessage.setFill(javafx.scene.paint.Color.RED);
            errorMessage.setText("Ensure that a password has at least: \n* One uppercase letter \n* One lowercase letter\n* One digit \n* Minimum length of 8 characters");
            return false;
        }
    }

    public boolean verifyConfirmPassword() {
        if (confirmPassLabel.getText().equals(passLabel.getText())) {
            confirmPassLabel.setStyle("-fx-border-color: green; -fx-border-width: 2px;");
            return true;
        } else {
            confirmPassLabel.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            return false;
        }
    }

    public boolean verifyEmail() {
        if (emailLabel.getText().matches(emailRegex)) {
            emailLabel.setStyle("-fx-border-color: green; -fx-border-width: 2px;");
            return true;
        } else {
            emailLabel.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            return false;
        }
    }
   /* public boolean getErrors() {
        errorCaptcha.setText("");

        if (CaptchaField.getText().isBlank()) {
            errorCaptcha.setTextFill(Color.RED);
            errorCaptcha.setText("Verify u r not a robot");
            return true;
        }
        if (!Objects.equals(Captchaa.getText(), CaptchaField.getText())) {
            errorCaptcha.setText("CAPTCHA INCORRECT");
            return true;
        }
        return false;
}*/

    @FXML
    public void goToLoginPage(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
            Parent root = loader.load();
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setScene(new Scene(root));

        } catch (IOException e) {
            System.out.println("Error loading Login.fxml: " + e.getMessage());
        }

    }


  /* public static String createCaptcha()
    {
        Random random =new Random();
        int length =7+(Math.abs(random.nextInt())%3);
        StringBuffer captchaBuffer = new StringBuffer();
        for(int i = 0; i <length ; i++) {
            int baseChar = Math.abs(random.nextInt()) % 62;
            int charNumber = 0;
            if (baseChar < 26) {
                charNumber = 65 + baseChar;
            } else if (baseChar < 52) {
                charNumber = 97 + (baseChar - 26);
            } else {
                charNumber = 48 + (baseChar - 52);
            }
            captchaBuffer.append((char) charNumber);
        }
        return captchaBuffer.toString() ;
    }


  public void newCaptchaOnClick()
    {
        Captchaa.setText(createCaptcha());
    }

*/
}
