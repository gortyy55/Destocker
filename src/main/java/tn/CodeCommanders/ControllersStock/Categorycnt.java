package tn.CodeCommanders.ControllersStock;
import com.sun.javafx.event.EventUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import tn.CodeCommanders.stock.Category;
import tn.CodeCommanders.Transactions.TransactionsStock;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

import java.io.IOException;



public class Categorycnt {
    @FXML
    private TextField categoryNameField;
    private Stage stage;
    private Scene scene;
    private Parent parent;

@FXML
    public void addCategory(ActionEvent event) {

        String categoryName = categoryNameField.getText().trim();
        if (categoryName.isEmpty()) {
            showAlert("Error", "Category name cannot be empty.");
            return;
        }


    TransactionsStock transaction = new TransactionsStock();
        if (transaction.categoryExists(categoryName)) {
            showAlert("Error", "Category name already exists.");
            return;
        }


        Category category = new Category(categoryName);
        transaction.addcat(category);
    categoryNameField.fireEvent(new CategoryAddedEvent());
        showAlert("Success", "Category added successfully.");
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void back(ActionEvent event) {
        try{

            Parent root= FXMLLoader.load(getClass().getResource("/affichestock.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public void catV(ActionEvent event) {
        try{

            Parent root= FXMLLoader.load(getClass().getResource("/affcat.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
