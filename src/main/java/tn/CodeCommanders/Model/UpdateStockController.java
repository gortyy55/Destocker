package tn.CodeCommanders.Model;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import tn.CodeCommanders.Transactions.TransactionsStock;
import tn.CodeCommanders.stock.Category;
import tn.CodeCommanders.stock.Stock;

import java.awt.*;
import java.util.ArrayList;

public class UpdateStockController {
    @FXML
    public TextField nomproduit;
    @FXML
    public TextField quantite;
    @FXML
    public TextField mail;
    @FXML
    public ComboBox<String> categoryComboBox;


    private Stock selectedItem;
            public void initialize() {
                // Initialize the ComboBox
                categoryComboBox.getItems().clear();

                // Fetch category names from the database
                TransactionsStock transactions = new TransactionsStock();
                ArrayList<Category> categoryList = transactions.getAllC();

                // Add category names to the ComboBox
                for (Category category : categoryList) {
                    categoryComboBox.getItems().add(category.getCategoryName());
                }
            }
    public void initData(Stock selectedItem) {
        this.selectedItem = selectedItem;

        nomproduit.setText(selectedItem.getProduitname());
        quantite.setText(String.valueOf(selectedItem.getQuantite()));
        mail.setText(selectedItem.getMail());
        categoryComboBox.getSelectionModel().select(selectedItem.getCategoryName());


    }

    public void save(ActionEvent event) {
        selectedItem.setProduitname(nomproduit.getText());
        selectedItem.setQuantite(Integer.parseInt(quantite.getText()));
        selectedItem.setMail(mail.getText());

        // Fetch the category ID based on the selected category name
        String selectedCategoryName = categoryComboBox.getValue();
        int categoryId = getCategoryId(selectedCategoryName);
        selectedItem.setIdCat(categoryId);

        TransactionsStock t = new TransactionsStock();
        t.update(selectedItem);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    // Helper method to get the category ID based on the category name
    private int getCategoryId(String categoryName) {
        TransactionsStock transactions = new TransactionsStock();
        ArrayList<Category> categoryList = transactions.getAllC();
        for (Category category : categoryList) {
            if (category.getCategoryName().equals(categoryName)) {
                return category.getIdc();
            }
        }
        return -1; // Return -1 if category not found (handle this case accordingly)
    }

}

