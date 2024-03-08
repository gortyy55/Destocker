package tn.CodeCommanders.Model;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import tn.CodeCommanders.stock.Category;


public class UpdateCategory {
    @FXML
    public TextField categryname;
    private Category selectedItem;

    public void initData(Category selectedItem) {
        this.selectedItem = selectedItem;

        categryname.setText(selectedItem.getCategoryName());



    }
    public void save(ActionEvent event) {
        selectedItem.setCategoryName(categryname.getText());
    }
}
