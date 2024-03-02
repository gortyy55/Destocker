package tn.CodeCommanders.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import tn.CodeCommanders.Entities.User;
import tn.CodeCommanders.Service.ServiceUser;
import javafx.stage.Stage;

import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class Controller {
    @FXML
    private TextField nameLabel;
    @FXML private TextField lastnameLabel;
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



    @FXML
    void addUser(ActionEvent event) throws SQLException {
        if (!verifyEmail()||!verifyPhoneNumber()||!verifyConfirmPassword()||!verifyPassword()||!verifyAddressRequired()||!verifyFirstnameRequired()||!verifyLastnameRequired()) {
            System.out.println("correct this");
            return ;
        }
        String firstname = nameLabel.getText();
        String lastname = lastnameLabel.getText();
        String email= emailLabel.getText();
        String password = passLabel.getText();
        String address= addLabel.getText();
        int telephone = Integer.parseInt(phoneLabel.getText());
        String role ;
        if(rdBtnClient.isSelected())
            role = "Client";
        else if(rdBtnAssociation.isSelected())
            role = "Association";
        else
            role = "Entreprise";


        User user = new User(email,password,role,firstname,lastname, address,telephone);
        ServiceUser Ser = new ServiceUser();
        if(Ser.isEmailUnique(email)){
            Ser.ajouter(user);
            resetRegisterForm();
        }else{
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

     public boolean verifyPhoneNumber(){
        if(phoneLabel.getText().matches(phoneNumberRegex)){
            phoneLabel.setStyle("-fx-border-color: green; -fx-border-width: 2px;");
            return true;}
        else{
            phoneLabel.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            return false;}
     }
    public boolean verifyFirstnameRequired(){
        if(nameLabel.getText().matches(textRequiredRegex)){
            nameLabel.setStyle("-fx-border-color: green; -fx-border-width: 2px;");
            return true;}
        else{
            nameLabel.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            return false;}
    }

    public boolean verifyLastnameRequired(){
        if(lastnameLabel.getText().matches(textRequiredRegex)){
            lastnameLabel.setStyle("-fx-border-color: green; -fx-border-width: 2px;");
            return true;}
        else{
            lastnameLabel.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            return false;}
    }

    public boolean verifyAddressRequired(){
        if(addLabel.getText().matches(textRequiredRegex)){
            addLabel.setStyle("-fx-border-color: green; -fx-border-width: 2px;");
            return true;}
        else{
            addLabel.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            return false;}
    }

    public boolean verifyPassword(){
        if(passLabel.getText().matches(passwordRegex)){
            passLabel.setStyle("-fx-border-color: green; -fx-border-width: 2px;");
            errorMessage.setText("");
            return true;}
        else{
            passLabel.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            errorMessage.setFill(javafx.scene.paint.Color.RED);
            errorMessage.setText("Ensure that a password has at least: \n* One uppercase letter \n* One lowercase letter\n* One digit \n* Minimum length of 8 characters");
        return false;}
    }

    public boolean verifyConfirmPassword(){
        if(confirmPassLabel.getText().equals(passLabel.getText())){
            confirmPassLabel.setStyle("-fx-border-color: green; -fx-border-width: 2px;");
            return true;}
        else
        { confirmPassLabel.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            return false;}
    }

    public boolean verifyEmail(){
        if(emailLabel.getText().matches(emailRegex)){
            emailLabel.setStyle("-fx-border-color: green; -fx-border-width: 2px;");
        return true;}
        else
        {emailLabel.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
        return false ;}
    }

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



}
