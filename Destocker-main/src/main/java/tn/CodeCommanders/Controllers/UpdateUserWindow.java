package tn.CodeCommanders.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tn.CodeCommanders.Entities.User;
import tn.CodeCommanders.Service.ServiceUser;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UpdateUserWindow implements Initializable {

    public TextField firstname;
    public TextField lastname;
    public TextField role;
    public TextField email;
    public TextField mdp;
    public TextField addr;
    public TextField telephone;
    public Button updatebtn;

    String phoneNumberRegex = "\\d{8}";
    String textRequiredRegex = ".{3,}";
    String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
    String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";

    private List<User> users = new ArrayList<>();
    ServiceUser su = new ServiceUser();

    User user;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setEdit(User e) {
        this.user = e;

        System.out.println(user);

        firstname.setText(user.getFirstname());
        lastname.setText(user.getLastname());
        mdp.setText(user.getPassword());
        email.setText(user.getEmail());
        addr.setText(user.getAddress());
        telephone.setText(String.valueOf(user.getTelephone()));
        role.setText(user.getRole());

    }


    public void update(ActionEvent event) throws IOException {


        String fname = firstname.getText();
        String lname = lastname.getText();
        String md = mdp.getText();
        String mail = email.getText();
        String address = addr.getText();
        int tel = Integer.parseInt(telephone.getText());
        String rol = role.getText();

        user.setFirstname(fname);
        user.setLastname(lname);
        user.setEmail(mail);
        user.setRole(rol);
        user.setAddress(address);
        user.setPassword(md);
        user.setTelephone(tel);

        su.modifier(user);

        Dashboard.getInstance().FetchAll();

    }



    public boolean verifyPhoneNumber(){
        if(telephone.getText().matches(phoneNumberRegex)){
            telephone.setStyle("-fx-border-color: green; -fx-border-width: 2px;");
            return true;}
        else{
            telephone.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            return false;}
    }
    public boolean verifyFirstnameRequired(){
        if(firstname.getText().matches(textRequiredRegex)){
            firstname.setStyle("-fx-border-color: green; -fx-border-width: 2px;");
            return true;}
        else{
            firstname.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            return false;}
    }

    public boolean verifyLastnameRequired(){
        if(lastname.getText().matches(textRequiredRegex)){
            lastname.setStyle("-fx-border-color: green; -fx-border-width: 2px;");
            return true;}
        else{
            lastname.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            return false;}
    }

    public boolean verifyAddressRequired(){
        if(addr.getText().matches(textRequiredRegex)){
            addr.setStyle("-fx-border-color: green; -fx-border-width: 2px;");
            return true;}
        else{
            addr.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            return false;}
    }

  /*  public boolean verifyPassword(){
        if(mdp.getText().matches(passwordRegex)){
            mdp.setStyle("-fx-border-color: green; -fx-border-width: 2px;");
            errorMessage.setText("");
            return true;}
        else{
            mdp.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            errorMessage.setFill(javafx.scene.paint.Color.RED);
            errorMessage.setText("Ensure that a password has at least: \n* One uppercase letter \n* One lowercase letter\n* One digit \n* Minimum length of 8 characters");
            return false;}
    }

 /*   public boolean verifyConfirmPassword(){
        if(confirmPassLabel.getText().equals(passLabel.getText())){
            confirmPassLabel.setStyle("-fx-border-color: green; -fx-border-width: 2px;");
            return true;}
        else
        { confirmPassLabel.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            return false;}
    }
*/
    public boolean verifyEmail(){
        if(email.getText().matches(emailRegex)){
            email.setStyle("-fx-border-color: green; -fx-border-width: 2px;");
            return true;}
        else
        {email.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            return false ;}
    }

}
