package tn.CodeCommanders.Controllers;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import tn.CodeCommanders.Panier.Facture;
import tn.CodeCommanders.Transaction.Transactions;

import java.awt.*;
import java.sql.Date;

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
                Transactions t = new Transactions();
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
