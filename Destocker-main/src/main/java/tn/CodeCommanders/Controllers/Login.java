package tn.CodeCommanders.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tn.CodeCommanders.Entities.User;
import tn.CodeCommanders.Service.ServiceUser;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class Login {

    ServiceUser serviceUser = new ServiceUser();


    @FXML
    private Button btncreate;

    @FXML
    private TextField emailLabel;

    @FXML
    private Text errorMessage;

    @FXML
    private PasswordField passLabel;

    @FXML
    private RadioButton rdBtnAssociation;

    @FXML
    private RadioButton rdBtnClient;

    @FXML
    private RadioButton rdBtnEntreprise;

    @FXML
    private ToggleGroup role;

    @FXML
    private Label echecLoginLabel;
    private Stage stage;
    private Scene scene;

    private void handleLogin(ActionEvent event) throws SQLException {
            if (!serviceUser.userLogIn(emailLabel.getText(), passLabel.getText())) {
                echecLoginLabel.setTextFill(Color.RED);
                echecLoginLabel.setText("Pseudo or Password is incorrect");
            } else {
                if(serviceUser.getCurrentUser().getRole().equals("Admin")){
                    serviceUser.changeScreen(event,"/Dashboard.fxml","Dashboard");

                }else{
                    serviceUser.changeScreen(event,"/Window.fxml","Dashboard");
                }
            }



    }
@FXML
    public void addUser(ActionEvent event) throws SQLException {
        if (!passLabel.isVisible()){
            if (!emailLabel.getText().isBlank() && !passLabel.getText().isBlank()) {
                passLabel.setText(passLabel.getText());
                handleLogin(event);
            } else {
                echecLoginLabel.setTextFill(Color.RED);
                echecLoginLabel.setText(" Pseudo or Password is invalid ");
            }
        }
        else {
            if (!emailLabel.getText().isBlank()&& !passLabel.getText().isBlank()){
                handleLogin(event);
            }
            else {
                echecLoginLabel.setTextFill(Color.RED);
                echecLoginLabel.setText("Pseudo or Password is invalid");
            }

        }

    }

    public void back(ActionEvent event) {
        try{

            Parent root= FXMLLoader.load(getClass().getResource("/Window.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
