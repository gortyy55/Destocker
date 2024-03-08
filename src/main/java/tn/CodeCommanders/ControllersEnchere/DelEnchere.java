package tn.CodeCommanders.ControllersEnchere;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tn.CodeCommanders.Transactions.TransactionsEnchere;

import java.io.IOException;

public class DelEnchere {

    private Stage stage;
    private Scene scene;
    private Parent parent;

    @FXML
    private Label result;

    @FXML
    private TextField delid;

    @FXML
    void delenchere(ActionEvent event) {

     String id = delid.getText();
        TransactionsEnchere t = new TransactionsEnchere();

        t.Delete(id);

        delid.setText("");

        result.setText("Deleted "+id+" With Success");
        result.setTextFill(Color.GREEN);


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

}
