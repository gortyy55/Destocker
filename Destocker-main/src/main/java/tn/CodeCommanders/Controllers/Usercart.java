package tn.CodeCommanders.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tn.CodeCommanders.Entities.User;
import tn.CodeCommanders.JDBC.JDBC;
import tn.CodeCommanders.Service.ServiceUser;
import javafx.scene.control.Label;
import tn.CodeCommanders.Transaction.Transaction;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

public class Usercart {

    @FXML
    private Label fname;
    @FXML
    private Label lname;
    @FXML
    private Label role;
    @FXML
    private Label addr;
    @FXML
    private Label email;
    @FXML
    private Label mdp;
    @FXML
    private Label num;

    private User user;
    private Connection cnx;

    ServiceUser su = new ServiceUser();

    public void ServiceStock() {
        cnx = JDBC.getInstance().getCnx();
    }

    public void initialize() {

    }

    public void setData(User user) {
        Transaction t = new Transaction();
        ArrayList<User> users= t.getAll();

        if(!users.isEmpty())

            {
                this.user = user;
                fname.setText(String.valueOf(user.getFirstname()));
                lname.setText(String.valueOf(user.getLastname()));
                role.setText(String.valueOf(user.getRole()));
                addr.setText(String.valueOf(user.getAddress()));
                email.setText(String.valueOf(user.getEmail()));
                mdp.setText(String.valueOf(user.getPassword()));
                num.setText(String.valueOf(user.getTelephone()));
            }
        }

    public void delete(ActionEvent event) throws IOException {
        su.supprimer(user);
        Dashboard.getInstance().FetchAll();
    }


    @FXML
    private void update(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/updateUserItem.fxml"));

        Parent root = loader.load();
        UpdateUserWindow controller = loader.getController();
        controller.setEdit(user);
        Stage stage = new Stage();
        stage.setTitle("test");
        stage.setScene(new Scene(root));
        stage.show();



    }
}

