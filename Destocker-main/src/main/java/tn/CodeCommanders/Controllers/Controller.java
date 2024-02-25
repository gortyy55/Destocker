package tn.CodeCommanders.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import tn.CodeCommanders.Entities.User;
import tn.CodeCommanders.Service.ServiceUser;

import javax.management.relation.Role;
import javafx.scene.control.*;
import java.sql.SQLException;

public class Controller {
    @FXML
    private TextField nameLabel;
    @FXML private TextField lastnameLabel;
    @FXML
    private TextField emailLabel;
    @FXML
    private TextField passLabel;
    @FXML
    private TextField addLabel;
    @FXML
    private TextField phoneLabel;


    @FXML
    void addUser(ActionEvent event) throws SQLException {

        String Firstname = nameLabel.getText();
        String Lastname = lastnameLabel.getText();
        String Email= emailLabel.getText();
        String Password = passLabel.getText();
        String Address= addLabel.getText();

        int Telephone = Integer.parseInt(phoneLabel.getText());


        User user = new User(Email,Password,Firstname,Lastname, Address,Telephone);
        ServiceUser Ser = new ServiceUser();
        Ser.ajouter(user);


        nameLabel.setText("");
        lastnameLabel.setText("");
        emailLabel.setText("");
        passLabel.setText("");
        addLabel.setText("");
        phoneLabel.setText("");

    }
}
