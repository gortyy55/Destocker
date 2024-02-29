package tn.CodeCommanders.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import tn.CodeCommanders.Model.ProduitModel;
import tn.CodeCommanders.Transaction.Transactions;
import tn.CodeCommanders.stock.Stock;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Affichuser implements Initializable{
    @FXML
    public ComboBox byCbox;
    @FXML
    public TextField chercherTF;
    Transactions t = new Transactions();

    private ArrayList<Stock> getlist(){

        return t.getAll();
    }
    @FXML
    private ImageView exit;
    private Parent parent;
    private Stage stage;
    private Scene scene;

    @FXML
    private Button back;

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scrol;
    private List<Stock> stocks = new ArrayList<>();
    private ObservableList<Stock> filteredStock = FXCollections.observableArrayList();
    private List<Stock> getData(){

        List<Stock> stocks = getlist();
        return stocks;
    }


    @FXML
    void back(ActionEvent event) {
        try {

            Parent root = FXMLLoader.load(getClass().getResource("/Stock.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

            @Override
            public void initialize(URL url, ResourceBundle resourceBundle) {
                int column = 0;
                int row = 1;
                stocks.addAll(getData());


                try {
                    for (int i = 0; i < stocks.size(); i++) {
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("/produitModele.fxml"));
                        AnchorPane anchorPane = fxmlLoader.load();

                        ProduitModel produitModel = fxmlLoader.getController();
                        produitModel.setData(stocks.get(i));

                        if (column == 2) {
                            column = 0;
                            row++;
                        }

                        grid.add(anchorPane, column++, row);
                        GridPane.setMargin(anchorPane, new Insets(10));


                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                exit.setOnMouseClicked(event -> {
                            System.exit(0);
                        }
                );
                chercherTF.setOnKeyReleased(this::filterByProductId);
                byCbox.getItems().addAll( "nom","quantite","mail");
                byCbox.setValue("nom"); // Default selection

                // Set up event listener for ComboBox selection change
                byCbox.setOnAction(event -> filterBySelectedCriteria());

                // Set up event listener for text field change
                chercherTF.setOnKeyReleased(event -> filterBySelectedCriteria());
    }

    private void filterByProductId(javafx.scene.input.KeyEvent event) {
        String searchText = chercherTF.getText().trim();
        if (!searchText.isEmpty()) {
            filteredStock.clear();
            try {
                int productId = Integer.parseInt(searchText);
                for (Stock stock : stocks) {
                    if (String.valueOf(stock.getProduitname()).startsWith(searchText)) {
                        filteredStock.add(stock);
                    }
                }
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid product ID.");
                alert.showAndWait();
                ex.printStackTrace();
            }
        } else {
            filteredStock.addAll(stocks);
        }
        updateGridPane();
    }
    private void updateGridPane() {
        grid.getChildren().clear();
        int row = 1;
        int col = 0;
        for (Stock s : filteredStock) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/produitModele.fxml"));
                Pane anchorPane = fxmlLoader.load();
                ProduitModel prodtItemController = fxmlLoader.getController();
                prodtItemController.setData(s);
                if (col == 2) {
                    col = 0;
                    row++;
                }
                grid.add(anchorPane, col++, row);
                GridPane.setMargin(anchorPane, new Insets(10));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    private void filterBySelectedCriteria() {
        String searchText = chercherTF.getText().trim();
        String selectedCriteria = (String) byCbox.getValue();

        if (!searchText.isEmpty()) {
            filteredStock.clear();
            try {
                // Filtering based on selected criteria
                for (Stock stock : stocks) {
                    String fieldValue = "";
                    switch (selectedCriteria) {
                        case "nom":
                            fieldValue = String.valueOf(stock.getProduitname());
                            break;
                        case "quantite":
                            fieldValue = String.valueOf(stock.getQuantite());
                            break;
                        case "mail":
                            fieldValue = String.valueOf(stock.getMail());
                            break;
                    }
                    if (fieldValue.startsWith(searchText)) {
                        filteredStock.add(stock);
                    }
                }
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid field value.");
                alert.showAndWait();
                ex.printStackTrace();
            }
        } else {
            filteredStock.addAll(stocks);
        }
        updateGridPane();
    }
        }






