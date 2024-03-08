package tn.CodeCommanders.ControllersPanier;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import tn.CodeCommanders.Panier.Facture;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Date;
import java.time.format.DateTimeFormatter;

public class FactureController  {

    @FXML
    private TextField addressB;

    @FXML
    private TextField city;

    @FXML
    private TextField cityB;

    @FXML
    private TextField countryB;

    @FXML
    private TextField creditnum;

    @FXML
    private DatePicker expdate;

    @FXML
    private TextField nameCard;

    @FXML
    private TextField seccode;

    @FXML
    private TextField stateB;

    @FXML
    private TextField zipcodeB;

    public int idPanierToUpdate;
    public int idact;

    public double price;

    @FXML
    private Label creditNumLabel;
    @FXML
    private HBox creditNumContainer; // Add this field

    @FXML
    void initialize() {
        initializeListeners();
    }


    private static final String ACCOUNT_SID = "AC9466a2cffe42ba83cbedbee55d67b8b4";
    private static final String AUTH_TOKEN = "46cc078831bd9a5fc32b246c85132770";
    private static final String TWILIO_PHONE_NUMBER = "+12549442164";

    static {
        // Initialize Twilio
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }
    private void initializeListeners() {
        // Add listener to creditnum TextField
        creditnum.textProperty().addListener((observable, oldValue, newValue) -> {
            if (isCreditCardNumberValid(newValue) && newValue.length() == 8) {
                // Reset to default style
                creditnum.setStyle("");
                creditNumLabel.setText("");
            } else {
                // Apply red border style
                creditnum.setStyle("-fx-border-color: red;");
                creditNumLabel.setText("Credit card number must be 8 digits");
                creditNumLabel.setStyle("-fx-text-fill: red;");
            }
        });

        // Add listener to seccode TextField
        seccode.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() == 3 && newValue.matches("\\d*")) {
                // Reset to default style
                seccode.setStyle("");

            } else {
                // Apply red border style
                seccode.setStyle("-fx-border-color: red;");

            }
        });

        // Add listener to zipcodeB TextField
        zipcodeB.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() == 4 && newValue.matches("\\d*")) {
                // Reset to default style
                zipcodeB.setStyle("");

            } else {
                // Apply red border style
                zipcodeB.setStyle("-fx-border-color: red;");

            }
        });

        // Add similar listeners for other fields...
        nameCard.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                // Reset to default style
                nameCard.setStyle("");
            } else {
                // Apply red border style
                nameCard.setStyle("-fx-border-color: red;");
            }
        });

        expdate.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Check if the selected date is after the current date
                if (newValue.isBefore(java.time.LocalDate.now())) {
                    // Apply red border style to expdate
                    expdate.setStyle("-fx-border-color: red;");

                    // Show popup indicating that the card is expired
                    showExpirationPopup();
                } else {
                    // Reset to default style
                    expdate.setStyle("");
                }
            }
        });
    }


    public void setIdPanierToUpdate(int idPanier) {
        this.idPanierToUpdate = idPanier;
        System.out.println("hihaaa." + idPanier);
    }

    public void setIdact(int idActeur) {
        this.idact = idActeur;
        System.out.println("hooooo." + idActeur);
    }

    public void setTotalPrice(double totalPrice) {
        this.price = totalPrice;
        System.out.println("Prixxx." + price);
    }

    private void showExpirationPopup() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Card Expiration Warning");
        alert.setHeaderText(null);
        alert.setContentText("The card has expired. Please use a valid card.");
        alert.showAndWait();
    }



// ...

    @FXML
    void pickup() {
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Set font and size (adjust as needed)
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

            // Add content to the PDF
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 750);
            contentStream.showText("Name on Card: " + nameCard.getText());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Address: " + addressB.getText());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("City: " + cityB.getText());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("State: " + stateB.getText());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Date of Expiry: " + expdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Zip Code: " + zipcodeB.getText());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Country: " + countryB.getText());
            contentStream.newLineAtOffset(0, -20);
            contentStream.showText("Total Price: " + price);  // Assuming 'price' is the total price variable
            contentStream.endText();

            // Generate QR Code for Google Maps location
            String googleMapsUrl = "https://www.google.com/maps/place/ESB+:+Esprit+School+of+Business/@36.8992352,10.1868701,17z/data=!3m1!4b1!4m6!3m5!1s0x12e2cb745e5c6f1b:0xf69a51ee3c65c12e!8m2!3d36.8992352!4d10.189445!16s%2Fg%2F11cs3ytq00?entry=ttu";
            BitMatrix bitMatrix = new MultiFormatWriter().encode(googleMapsUrl, BarcodeFormat.QR_CODE, 200, 200);

            // Draw QR Code on the PDF
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            PDImageXObject pdImage = LosslessFactory.createFromImage(document, bufferedImage);
            contentStream.drawImage(pdImage, 50, 50);  // Adjust the position as needed

            contentStream.close();

            // Save the PDF in the Downloads directory
            document.save(System.getProperty("user.home") + "/Downloads/pickup_receipt.pdf");

            // Show success message or any other actions

            document.close();
        } catch (IOException e) {
            // Handle exception
            e.printStackTrace();
            showErrorPopup("Error", "Failed to generate PDF.");
        } catch (Exception e) {
            // Handle ZXing exception
            e.printStackTrace();
            showErrorPopup("Error", "Failed to generate QR code.");
        }
    }

    @FXML
    void paybilling() {
        if (validatePaymentDetails()) {
            try {
                String name_card = nameCard.getText();
                int ccn = Integer.parseInt(creditnum.getText());
                Date exp_date = java.sql.Date.valueOf(expdate.getValue());
                int security_code = Integer.parseInt(seccode.getText());
                String address = addressB.getText();
                String city = cityB.getText();
                String state = stateB.getText();
                int zip_code = Integer.parseInt(zipcodeB.getText());
                String country = countryB.getText();

                sendConfirmationSMS();

                Facture f = new Facture(idact, idPanierToUpdate, name_card, ccn, exp_date, security_code, address, city, state, zip_code, country);
                tn.CodeCommanders.Transaction.TransactionsPanier t = new tn.CodeCommanders.Transaction.TransactionsPanier();
                t.addfacture(f);
                processPayment(price);

                // Show payment success popup
                showSuccessPopup("Payment Successful");

                // Reset text fields
                resetTextFields();
            } catch (NumberFormatException e) {
                showErrorPopup("Invalid Input", "Please enter valid numeric values for credit card number, security code, and zip code.");
            }
        }
    }


    private void processPayment(double amount) {
        try {
            // Set your secret key here
            Stripe.apiKey = "sk_test_51OquviDCmW43ltvEjgzNfYf8MRicGmOGc3evVEBzelH6rya65WxmIkrPWZVt1F9NloT5t0qg5GdaNOL6GGiTY9ts00Yb70kl3o";

            // Create a PaymentIntent with other payment details
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount((long) (amount * 100)) // Amount in cents (e.g., $10.00)
                    .setCurrency("usd")
                    .build();

            PaymentIntent intent = PaymentIntent.create(params);

            // If the payment was successful, display a success message
            System.out.println("Payment successful. PaymentIntent ID: " + intent.getId());
        } catch (StripeException e) {
            // If there was an error processing the payment, display the error message
            System.out.println("Payment failed. Error: " + e.getMessage());
        }
    }



    private void showSuccessPopup(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Payment Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorPopup(String title, String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void resetTextFields() {
        nameCard.clear();
        creditnum.clear();
        expdate.setValue(null);
        seccode.clear();
        addressB.clear();
        cityB.clear();
        stateB.clear();
        zipcodeB.clear();
        countryB.clear();
    }

    // Helper method to check if the credit card number is an integer with exactly 8 digits
    private boolean isCreditCardNumberValid(String creditNumStr) {
        try {
            int ccn = Integer.parseInt(creditNumStr);
            return creditNumStr.length() == 8;
        } catch (NumberFormatException e) {
            return false; // Not a valid integer
        }
    }
    private void sendConfirmationSMS() {
        try {
            // Replace "customerPhoneNumber" with the actual customer's phone number
            String customerPhoneNumber = "+21699183555"; // Example phone number format

            // Customize the SMS message
            String smsMessage = "Your order is confirmed! Thank you for shopping with us.";

            // Send SMS using Twilio
            Message message = Message.creator(
                    new PhoneNumber(customerPhoneNumber),
                    new PhoneNumber(TWILIO_PHONE_NUMBER),
                    smsMessage
            ).create();

            // Print the message SID to the console (optional)
            System.out.println("SMS sent successfully. SID: " + message.getSid());
        } catch (Exception e) {
            // Handle exception, e.g., if the SMS cannot be sent
            e.printStackTrace();
        }
    }
    private boolean validatePaymentDetails() {
        boolean isValid = true;

        // Validate credit card number
        String creditNumStr = creditnum.getText();
        if (!isCreditCardNumberValid(creditNumStr) || creditNumStr.length() != 8) {
            creditnum.setStyle("-fx-border-color: red;");
            creditNumLabel.setText("Credit card number must be 8 digits");
            creditNumLabel.setStyle("-fx-text-fill: red;");
            isValid = false;
        } else {
            creditnum.setStyle("");
            creditNumLabel.setText("");
        }

        // Validate other fields
        if (nameCard.getText().isEmpty() || expdate.getValue() == null || seccode.getText().length() != 3 ||
                addressB.getText().isEmpty() || cityB.getText().isEmpty() || stateB.getText().isEmpty() ||
                zipcodeB.getText().isEmpty() || countryB.getText().isEmpty()) {
            markEmptyFieldsRed();
            isValid = false;
        }

        return isValid;
    }

    private void markEmptyFieldsRed() {
        if (nameCard.getText().isEmpty()) {
            nameCard.setStyle("-fx-border-color: red;");
        } else {
            nameCard.setStyle("");
        }
        if (addressB.getText().isEmpty()) {
            addressB.setStyle("-fx-border-color: red;");
        } else {
            addressB.setStyle("");
        }
        if (cityB.getText().isEmpty()) {
            cityB.setStyle("-fx-border-color: red;");
        } else {
            cityB.setStyle("");
        }
        if (stateB.getText().isEmpty()) {
            stateB.setStyle("-fx-border-color: red;");
        } else {
            stateB.setStyle("");
        }

        if (seccode.getText().isEmpty()) {
            seccode.setStyle("-fx-border-color: red;");
        } else {
            seccode.setStyle("");
        }
        if (zipcodeB.getText().isEmpty()) {
            zipcodeB.setStyle("-fx-border-color: red;");
        } else {
            zipcodeB.setStyle("");
        }

        // Repeat the above block for other text fields...

        if (countryB.getText().isEmpty()) {
            countryB.setStyle("-fx-border-color: red;");
        } else {
            countryB.setStyle("");
        }
    }
}
