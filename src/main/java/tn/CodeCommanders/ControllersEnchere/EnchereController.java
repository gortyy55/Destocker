package tn.CodeCommanders.ControllersEnchere;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tn.CodeCommanders.Enchere.Enchere;
import tn.CodeCommanders.Transactions.TransactionsEnchere;

import java.io.IOException;
import java.util.Objects;

public class EnchereController {

    private Stage stage;
    private Scene scene;
    private Parent parent;

    @FXML
    private static Label err;

    @FXML
    private Label confirm;

    @FXML
    private TextField eproduit;

    @FXML
    private TextField eprixinit;

    @FXML
    private TextField estock;

    @FXML
    private TextField eid;





    @FXML
    void addenchere(ActionEvent event) {




        if (Objects.equals(estock.getText(), "")){
            confirm.setTextFill(Color.RED);
            confirm.setText("Merci de metre un stock valide");

        } else if (Objects.equals(eprixinit.getText(), "")) {
            confirm.setTextFill(Color.RED);
            confirm.setText("Merci de metre un prix valide");
        } else if (Objects.equals(eproduit.getText(), "")) {
            confirm.setTextFill(Color.RED);
            confirm.setText("Merci de metre un lot valide");

        }else {

            int stock = Integer.parseInt(estock.getText());
            String produit = eproduit.getText();
            double prixinit = Double.parseDouble(eprixinit.getText());

            Enchere e = new Enchere(stock, produit, prixinit);
            TransactionsEnchere t = new TransactionsEnchere();
            t.add(e);

            estock.setText("");
            eproduit.setText("");
            eprixinit.setText("");
            confirm.setTextFill(Color.GREEN);
            confirm.setText("Added With Success");
        }

    }

    @FXML
    public void retour(ActionEvent event){
        try{

            Parent root= FXMLLoader.load(getClass().getResource("/MainWindow.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
@FXML
    public void clear(KeyEvent event) {
    confirm.setText("");

    }
}
