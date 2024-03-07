package tn.CodeCommanders.Controllers;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import tn.CodeCommanders.Entities.User;
import tn.CodeCommanders.Service.ServiceUser;
import javafx.stage.Stage;

import javafx.scene.control.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.scene.control.Label;


public class Controller /*implements Initializable */ {

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


    /*   @Override
       public void initialize(URL location, ResourceBundle resources) {

           //Captchaa.setText(createCaptcha());}*/
  /*  private void MailB() throws Exception {
        try {
            String to = "gharbi.karim@esprit.tn";
            String from = "karim@gmail.com";
            String subject = "Registration Account CLINIMATE";
            String body = "BONJOUR MR/MME "
                    + firstnameP.getText()
                    + "this your mail for connecter to our application CLINIAMTE\n email :"
                    + emailP.getText()
                    + "\n password :"
                    + PasswordP.getText();
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("mohamed.jmai@esprit.tn", "wrshgovdrqjkqejb");
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(to));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailP.getText()));
                message.setSubject(subject);
                message.setText(body);
                System.out.println(body);
                Transport.send(message);

                System.out.println("registration sucedffly done ");
            } catch (MessagingException e) {
                System.err.println("Failed to send email: " + e.getMessage());
            }
        } catch (RuntimeException e) {
            if (e.getCause() instanceof InvocationTargetException) {
                Throwable targetException = e.getCause().getCause();
                targetException.printStackTrace();
            } else {
                e.printStackTrace();
            }
        }
    }*/


    private void sendConfirmationEmail(String recipientEmail) {
        // Create SendGrid object with your API key
        SendGrid sendgrid = new SendGrid("thot key lena");

        // Create Email object for the recipient
        Email recipient = new Email(recipientEmail);

        // Create Email object for the sender
        Email sender = new Email("thot l'email eli amalt bih sendgrid lena"); // Update with your email

        // Set subject and content of the email
        String subject = "Confirmation de commande";
        String messageContent = "Votre commande a été passée avec succès ! Vous allez recevoir votre commande Apres 48h";

        // Create Content object
        Content content = new Content("text/plain", messageContent);

        // Create Mail object
        Mail mail = new Mail(sender, subject, recipient, content);

        // Send the email using SendGrid API
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sendgrid.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void addUser(ActionEvent event) throws SQLException {
        if (!verifyEmail() || !verifyPhoneNumber() || !verifyConfirmPassword() || !verifyPassword() || !verifyAddressRequired() || !verifyFirstnameRequired() || !verifyLastnameRequired()) {
            System.out.println("correct this");
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
            errorMessage.setText("Email Address already exist !");
        }


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


    // verify text filed

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
