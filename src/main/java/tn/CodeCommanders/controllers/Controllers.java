package tn.CodeCommanders.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tn.CodeCommanders.Transaction.Transactions;
import tn.CodeCommanders.stock.Stock;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


public class Controllers implements Initializable  {
    @FXML
    private AnchorPane opacityPane,drawerPane;
    @FXML
    private ImageView exit;
    private Stage stage;
    private Scene scene;
    private Parent parent;
    @FXML
    private Label resultat;
    @FXML
    private TextField idproduit;
    @FXML
    private TextField idqq;
    @FXML
    private TextField mail;
    String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";
@FXML
     void addP(ActionEvent event) throws InterruptedException {
    if (!verifyEmail()) {
        System.out.println("correct this");
        return ;
    }
    String produit = idproduit.getText();
    int  qq= Integer.parseInt(idqq.getText());
    String Mail = mail.getText();
    Stock s = new Stock(produit,qq,Mail);
    Transactions t = new Transactions();
t.add(s);
    final String ACCOUNT_SID = "AC98bdf891b664839b0e404cc157821a3a";
    final String AUTH_TOKEN = "6a790e56f1033265def2244d74b9d708";
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

//        Envoyer le SMS
    String toPhoneNumber = "+21622097795";
    String fromPhoneNumber = "+14159911333";
    String messageBody = "vous avez un nouveau produit a ajouter ";
    Message message = Message.creator(new PhoneNumber(toPhoneNumber), new PhoneNumber(fromPhoneNumber), messageBody).create();
idproduit.setText("");
idqq.setText("");
mail.setText("");
resultat.setText("saved");

}
@Override
public void  initialize(URL location, ResourceBundle resource){
   exit.setOnMouseClicked(event -> {System.exit(0);} );

}
@FXML
    public void clear(KeyEvent event) {
    resultat.setText("");
    }
    public boolean verifyEmail(){
        if(mail.getText().matches(emailRegex)){
            mail.setStyle("-fx-border-color: green; -fx-border-width: 2px;");
            return true;}
        else
        {mail.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            return false ;}
    }
@FXML
    public void change(ActionEvent event) {
    try{

        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/affichestock.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }catch (IOException e){
        System.out.println(e.getMessage());
    }
    }

    public void change2(ActionEvent event) {
        try{

            Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Affichuser.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }


}
